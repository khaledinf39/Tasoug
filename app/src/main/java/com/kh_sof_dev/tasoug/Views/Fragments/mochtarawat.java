package com.kh_sof_dev.tasoug.Views.Fragments;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.kh_sof_dev.tasoug.Controule.Info.Store_info;
import com.kh_sof_dev.tasoug.Controule.Routes.Orders;
import com.kh_sof_dev.tasoug.Controule.Routes.Products;
import com.kh_sof_dev.tasoug.Model.Classes.Order;
import com.kh_sof_dev.tasoug.Model.Classes.Product;
import com.kh_sof_dev.tasoug.Model.Classes.ProductOForder;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Activities.MainActivity;
import com.kh_sof_dev.tasoug.Views.Adapters.Mochtarawate_adapter;
import com.squareup.picasso.Picasso;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class mochtarawat extends Fragment implements View.OnClickListener {
    private static final int REQUEST_CODE_QR_SCAN = 101;
    private static final String LOGTAG ="QRcode" ;

    public mochtarawat() {
        // Required empty public constructor
    }

private ImageView scan_btn;
    private TextView total,rest;
    private RecyclerView RV;
    private List<ProductOForder> productOForderList;
    private Mochtarawate_adapter adapter;
    private Button contine,date;
    private EditText tcheck_out;
    private Double total_=0.0,rest_=0.0;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_mochtarawat, container, false);
        initialize(view);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initialize(View view) {
        scan_btn=view.findViewById(R.id.scan_btn);
        total=view.findViewById(R.id.total);
        rest=view.findViewById(R.id.rest);
        RV=view.findViewById(R.id.RV);
        date=view.findViewById(R.id.date_ins);
        contine=view.findViewById(R.id.contine);
        tcheck_out=view.findViewById(R.id.tcheck_out);
       /********************************Actions**********************/
        scan_btn.setOnClickListener(this);
        contine.setOnClickListener(this);
        date.setText(getToday_date());
        tcheck_out.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {


            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
try {
    rest_=Double.parseDouble(s.toString())-total_;
    rest.setText(rest_.toString());

}catch (Exception e){
    e.printStackTrace();
}
            }
        });
        /*************************RV***********************/
        productOForderList=new ArrayList<>();
        adapter=new Mochtarawate_adapter(getContext(),productOForderList);
        RV.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        RV.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.scan_btn:
                Intent i = new Intent(getActivity(), QrCodeActivity.class);
                startActivityForResult( i,REQUEST_CODE_QR_SCAN);
                break;

            case R.id.contine:
                Add_order();
                break;

        }
    }

    private void Add_order() {
        if (productOForderList.size()==0){
            Toast.makeText(getContext(),"قم بإضافة منتج واحد على الأقل",Toast.LENGTH_SHORT).show();
            return;
        }
        final ProgressDialog dialog=new ProgressDialog(getContext());
        dialog.setMessage("فع الطلب...");
        dialog.show();
        Order order=new Order();
        order.setStatus(1);
        order.setProducts(productOForderList);
        order.setStoreID(Store_info.storeID);
        order.setUserID(Store_info.storeID);
        order.setUser_name(getString(R.string.client_in_store));
        order.setUser_address(getString(R.string.noAddress));
        order.setUser_phone(Store_info.phone);
        order.setSub_total(total_.toString());
        order.setBayment_type(1);

        Orders orders=new Orders();
        orders.Post_Order(getContext(), order, new Orders.Get_Order() {
            @Override
            public void onstart() {

            }

            @Override
            public void onSuccess(Order order) {
                dialog.dismiss();
                Toast.makeText(getContext(),"تم إضافة الطلب بنجاح",Toast.LENGTH_SHORT).show();
                productOForderList.clear();
                adapter.notifyDataSetChanged();
                total_=0.0;
                rest_=0.0;
                total.setText("00");
                rest.setText("00");
                tcheck_out.setText("");
            }

            @Override
            public void onField(String msg) {

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getToday_date(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        return  dtf.format(now);
    }
    @SuppressLint("NewApi")
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode != Activity.RESULT_OK)
        {
            Log.d(LOGTAG,"COULD NOT GET A GOOD RESULT.");
            if(data==null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if( result!=null)
            {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setTitle("Scan Error");
                alertDialog.setMessage("QR Code could not be scanned");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            return;

        }
        if(requestCode == REQUEST_CODE_QR_SCAN)
        {
            if(data==null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
            Log.d(LOGTAG,"Have scan result in your app activity :"+ result);

            ProgressDialog dialog=new ProgressDialog(getContext());
            dialog.setMessage("بحث...");
            dialog.show();
            Get_product(dialog,result);
//            AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
//            alertDialog.setTitle("Scan result");
//            alertDialog.setMessage(result);
//            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    });
//            alertDialog.show();

        }
    }

    private void Get_product(final ProgressDialog dialog, String result) {
        Products products=new Products();
        products.Get_All_productByQRcode(getContext(), result, new Products.Get_product() {
            @Override
            public void onstart() {

            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSuccess(Product product) {
                if (product.getProductList().size()==0){
                    Toast.makeText(getContext(),"هذا المنتج غير موجود ",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return;
                }
                dialog.dismiss();
                Popu_saleProduct(product);

            }

            @Override
            public void onField(String msg) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void Popu_saleProduct(final Product product) {
        final BottomSheetDialog dialog=new BottomSheetDialog(getContext());
        dialog.setContentView(R.layout.popup_sale_product);
        ImageView img;
        TextView name,price,date,quantity,quantity_sale;
        final EditText quantity_want;
        Button save;
        name = dialog.findViewById(R.id.name);
        price = dialog.findViewById(R.id.price);
        date = dialog.findViewById(R.id.date);
        quantity = dialog.findViewById(R.id.quantity);
        quantity_sale = dialog.findViewById(R.id.quantity_sale);
        img = dialog.findViewById(R.id.img);
        quantity_want = dialog.findViewById(R.id.tcheck_out);
        save = dialog.findViewById(R.id.contine);

        name.setText(product.getProductList().get(0).getName());
        price.setText(product.getProductList().get(0).getUnity().get(0).getPrice()+" ر.س ");
        date.setText(getdate(product.getProductList().get(0).getCreate_at()));
        quantity.setText(product.getProductList().get(0).getQuantity()+"");
        quantity_sale.setText(product.getProductList().get(0).getQuantity_sold()+"");
        try {


            Picasso.with(getContext())
                    .load(product.getProductList().get(0).getImages().get(0))
                    .placeholder(getContext().getDrawable(R.drawable.prod))
                    .into(img);
        }catch (Exception e){
            e.printStackTrace();
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity_want.getText().toString().isEmpty()){
                    quantity_want.setError(quantity_want.getHint());
                    return;
                }
                int quan=Integer.parseInt(quantity_want.getText().toString());
                if (quan==0 || product.getProductList().get(0).getQuantity()<quan){
                    quantity_want.setError(quantity_want.getHint());
                    return;
                }
                ProductOForder p=new ProductOForder();
                try{
                    if (product.getProductList().get(0).getImages().get(0)==null)
                        p.setImage("no Image");
                    else
                    p.setImage(product.getProductList().get(0).getImages().get(0));

                }catch (Exception e){
                    e.printStackTrace();
                }
                p.set_id(product.productList.get(0).getId_());
                p.setName(product.getProductList().get(0).getName());
                p.setPrice(product.getProductList().get(0).getUnity().get(0).getPrice());
                p.setUnity(product.getProductList().get(0).getUnity().get(0).getUnity());
                p.setQuantity(quan);
                Boolean exist=false;
                for (ProductOForder pro:productOForderList
                     ) {
                    if (pro.getName().equals(p.getName())){
                        p.setQuantity(p.getQuantity()+pro.getQuantity());
                        productOForderList.remove(pro);
                        productOForderList.add(p);
                        adapter.notifyDataSetChanged();
                        exist=true;
                    }
                }
                if (!exist){
                    productOForderList.add(p);
                    adapter.notifyDataSetChanged();
                }

total_=total_+product.getProductList().get(0).getUnity().get(0).getPrice()*quan;
total.setText(total_.toString());
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getdate(String date_){
        Log.e("date_", date_.substring(0, date_.indexOf("T")));
        return date_.substring(0, date_.indexOf("T"));
    }
}
