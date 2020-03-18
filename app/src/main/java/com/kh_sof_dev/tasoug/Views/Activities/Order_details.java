package com.kh_sof_dev.tasoug.Views.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kh_sof_dev.tasoug.Controule.Routes.Mostalamat;
import com.kh_sof_dev.tasoug.Controule.Routes.Orders;
import com.kh_sof_dev.tasoug.Model.Classes.Order;
import com.kh_sof_dev.tasoug.Model.Classes.ProductOForder;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Adapters.Order_Items_adapter;

import java.util.ArrayList;
import java.util.List;

public class Order_details extends AppCompatActivity implements View.OnClickListener {

    public static Order order=null;
public  static String type="order";
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        initialize();
        LoadingData();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void LoadingData() {
        ordr_nb.setText(order.get_id());
        price.setText(order.getSub_total()+" ر.س ");
        date.setText(getdate(order.getCreate_at()));
        user_name.setText(order.getUser_name());
        phone.setText(order.getUser_phone());
        address.setText(order.getUser_address());

        productOForderList.addAll(order.getProducts());
        order_items_adapter.notifyDataSetChanged();

        switch (order.getStatus()){
            case 1:
             cancel.setVisibility(View.VISIBLE);
            contine.setVisibility(View.VISIBLE);
            break;
default:
    cancel.setVisibility(View.GONE);
    contine.setVisibility(View.GONE);
    break;

        }

    }

    private Button contine,cancel;
    private ImageView back_btn;
    private TextView ordr_nb,date,price,user_name,phone,address;
    private RecyclerView RV;
    private List<ProductOForder> productOForderList;
    private Order_Items_adapter order_items_adapter;
    private void initialize() {
        contine=findViewById(R.id.contine);
        cancel=findViewById(R.id.cancel);
        back_btn=findViewById(R.id.back_btn);
        ordr_nb=findViewById(R.id.order_nb);
        date=findViewById(R.id.date);
        price=findViewById(R.id.price);
        user_name=findViewById(R.id.user_name);
        phone=findViewById(R.id.phone);
        address=findViewById(R.id.address);
        RV=findViewById(R.id.RV);
        /*******************************Actions*******************/
        contine.setOnClickListener(this);
        cancel.setOnClickListener(this);
        back_btn.setOnClickListener(this);
        RV.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        productOForderList=new ArrayList<>();
        order_items_adapter=new Order_Items_adapter(this, productOForderList, new Order_Items_adapter.Selected_item() {
            @Override
            public void Onselcted(ProductOForder product) {

            }
        });
        RV.setAdapter(order_items_adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.contine:
               if (type.equals("order")){
                   change_order(2);
               }else {
                   change_mosta(2);
               }

                break;
                case R.id.cancel:
                    if (type.equals("order")){
                        change_order(3);
                    }else {
                        change_mosta(3);
                    }
                break;
            case R.id.back_btn:
                finish();
                break;

        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getdate(String date_){
        Log.e("date_", date_.substring(0, date_.indexOf("T")));
        return date_.substring(0, date_.indexOf("T"));
    }
    private void change_order(final int status) {
        Orders orders=new Orders();
        orders.Put_Order_status(this, order.get_id(), status, new Orders.Get_Order() {
            @Override
            public void onstart() {

            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSuccess(Order order) {
                cancel.setVisibility(View.GONE);
                contine.setVisibility(View.GONE);
            }

            @Override
            public void onField(String msg) {

            }
        });
    }
    private void change_mosta(final int status) {
        Mostalamat orders=new Mostalamat();
        orders.Put_Order_status(this, order.get_id(), status, new Mostalamat.Get_Order() {
            @Override
            public void onstart() {

            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSuccess(Order order) {
                cancel.setVisibility(View.GONE);
                contine.setVisibility(View.GONE);
            }

            @Override
            public void onField(String msg) {

            }
        });
    }
}
