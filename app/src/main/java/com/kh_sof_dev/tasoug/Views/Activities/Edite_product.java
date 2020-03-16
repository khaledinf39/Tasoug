package com.kh_sof_dev.tasoug.Views.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kh_sof_dev.tasoug.Controule.Info.Store_info;
import com.kh_sof_dev.tasoug.Controule.Routes.Categories;
import com.kh_sof_dev.tasoug.Controule.Routes.Products;
import com.kh_sof_dev.tasoug.Controule.Routes.upload_img.upload;
import com.kh_sof_dev.tasoug.Model.Classes.Categorie;
import com.kh_sof_dev.tasoug.Model.Classes.Product;
import com.kh_sof_dev.tasoug.Model.Classes.ResizePickedImage;
import com.kh_sof_dev.tasoug.Model.Classes.Unity;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Adapters.Categories_adapter;
import com.kh_sof_dev.tasoug.Views.Adapters.Images_adapter;
import com.kh_sof_dev.tasoug.Views.Adapters.Unities_adapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

import static android.widget.LinearLayout.HORIZONTAL;

public class Edite_product extends AppCompatActivity  implements View.OnClickListener {
private ImageView add_price,edite_price,back_btn,add_categorie,edit_categorie;
private Button save,cancel,take_img;
private RecyclerView RV_images,RV_unity,RV_categories;
private EditText name,desc;
private FloatingActionButton  QRcode_btn;
private Unities_adapter unities_adapter;
private List<Unity> unityList;
private TextView price;
    private Categories_adapter categories_adapter;
    private List<Categorie> categorieList;
    private String categorieID=null;

private Unity unity_select=null;
    private static final int REQUEST_CODE_QR_SCAN = 101;
    private static final String LOGTAG ="QRcode" ;
    public static Product product=null ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initialize();
        fetch_data();
        LoadingData();
    }

    private void LoadingData() {
        if (product!=null){
            ////for images
           list_images.clear();
           list_images.addAll(product.getImages());
           adapter.notifyDataSetChanged();

           ///////for unity
            unityList.clear();
            unityList.addAll(product.getUnity());
            unities_adapter.notifyDataSetChanged();
            ///////////for more info

            name.setText(product.getName());
            desc.setText(product.getDiscription());
            QRcode=product.getQRcode();


        }
    }

    private void fetch_data() {
        Log.d("token", Store_info.token);
        Categories categories=new Categories();
        categories.Get_All_categories(this, new Categories.Get_categories() {
            @Override
            public void onstart() {

            }

            @Override
            public void onSuccess(Categorie categorie) {
                categorieList.clear();
categorieList.addAll(categorie.categorieList);
if (product!=null){
    for (int i=0;i<categorieList.size();i++
         ) {
        Categorie c=categorieList.get(i);
        if (c.get_id().equals(product.getCategorieID())){
            categories_adapter.item_select=i;
            categories_adapter.notifyDataSetChanged();
            categorieID=product.getCategorieID();
            return;
        }
    }
}

            }

            @Override
            public void onField(String msg) {

            }
        });
    }

    @SuppressLint("WrongConstant")
    private void initialize() {
        /*********************declaration**************************/
        add_price=findViewById(R.id.add_price);
        edite_price=findViewById(R.id.edite_price);
        add_categorie=findViewById(R.id.add_categorie);
        edit_categorie=findViewById(R.id.edite_categorie);
        save=findViewById(R.id.save_btn);
        cancel=findViewById(R.id.cancel);
        take_img=findViewById(R.id.take_img);
        RV_images=findViewById(R.id.RV_images);
        RV_unity=findViewById(R.id.RV);
        RV_categories=findViewById(R.id.RV_categories);
        name=findViewById(R.id.product_name);
        desc=findViewById(R.id.description);
        back_btn=findViewById(R.id.back_btn);
        price=findViewById(R.id.price);
        QRcode_btn=findViewById(R.id.fab_qr_code);
        /*************************actions*********************************/
        add_price.setOnClickListener(this);
        edite_price.setOnClickListener(this);
        add_categorie.setOnClickListener(this);
        edit_categorie.setOnClickListener(this);
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
        take_img.setOnClickListener(this);
        back_btn.setOnClickListener(this);
        QRcode_btn.setOnClickListener(this);
        /**********************************more**************************************/
        list_images=new ArrayList<>();
        adapter=new Images_adapter(this, list_images, new Images_adapter.Onselected() {
            @Override
            public void onitemselect(int potions) {

            }
        });
        RV_images.setLayoutManager(new LinearLayoutManager(this,HORIZONTAL,false));
        RV_images.setAdapter(adapter);

        ///*******
        unityList=new ArrayList<>();
        unities_adapter=new Unities_adapter(this, unityList, new Unities_adapter.Selected_item() {
            @Override
            public void Onselcted(Unity unity) {
                price.setText(unity.getPrice().toString()+getString(R.string.rs));
                unity_select=unity;

            }
        });
        RV_unity.setLayoutManager(new LinearLayoutManager(this,HORIZONTAL,false));
        RV_unity.setAdapter(unities_adapter);


        ///*******
        categorieList=new ArrayList<>();
        categories_adapter=new Categories_adapter(this, categorieList, new Categories_adapter.Selected_item() {
            @Override
            public void Onselcted(Categorie categorie) {
categorieID=categorie.get_id();
            }
        }
    );
        RV_categories.setLayoutManager(new LinearLayoutManager(this,HORIZONTAL,false));
        RV_categories.setAdapter(categories_adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_categorie:
                popup_addCategories();
                break;
            case R.id.edite_categorie:
                if (categorieID!=null){
                    popup_editCategories();
                }
                break;
            case R.id.add_price:
                popup_addPrice();
                break;

            case R.id.edite_price:
                if (unity_select!=null){
                    popup_editPrice();
                }
                break;
            case R.id.take_img:
                imageBrowse(list_images.size());
                break;
            case R.id.save_btn:
                save_fun();
                break;
                case R.id.back_btn:
                    Product_details.product=product;
                finish();
                break;

            case R.id.cancel:
                Product_details.product=product;
                finish();
                break;
            case R.id.fab_qr_code:
                Intent i = new Intent(this, QrCodeActivity.class);
                startActivityForResult( i,REQUEST_CODE_QR_SCAN);
                break;
        }
    }
    @SuppressLint("NewApi")
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode==list_images.size() ) {


            System.out.println("100000");
            Uri returnUri = data.getData();
            ResizePickedImage resizePickedImage = new ResizePickedImage();
            String realePath = resizePickedImage.getRealPathFromURI(returnUri, this);
            System.out.println(realePath);
            String compresedImagePath;
            Bitmap bitmapImage = null;
            try {
                Uri contentURI = data.getData();
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), contentURI);
                BitmapDrawable background = new BitmapDrawable(bitmap);


                compresedImagePath = resizePickedImage.resizeAndCompressImageBeforeSend
                        (this, realePath, "image" + requestCode);
                if (mPaths.contains(bitmap)) {
                    mPaths.remove(bitmap);

                }
                mPaths.add(bitmap);
                list_images.add(contentURI.toString());
                adapter.notifyDataSetChanged();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(resultCode != Activity.RESULT_OK)
        {
            Log.d(LOGTAG,"COULD NOT GET A GOOD RESULT.");
            if(data==null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if( result!=null)
            {
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
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
            final String result = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
            Log.d(LOGTAG,"Have scan result in your app activity :"+ result);
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Scan result");
            alertDialog.setMessage(result);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                           QRcode=result;
                        }
                    });
            alertDialog.show();

        }
    }
private String QRcode="";
    private void save_fun() {
        if (categorieID==null){
            Toast.makeText(this,"يجب إختيار الصنف ",Toast.LENGTH_SHORT).show();
            return;
        }
        final String name_=name.getText().toString();
        final String desc_=desc.getText().toString();
        if (name_.isEmpty()){
            name.setError(name.getHint());
            return;
        }
        if (desc_.isEmpty()){
            desc.setError(desc.getHint());
            return;
        }
        if (unityList.size()==0){
            Toast.makeText(this,"يجب إضافة مبلغ على الأقل ",Toast.LENGTH_SHORT).show();
            return;
        }
        if (list_images.size()==0){
            Toast.makeText(this,"يجب إضافة صورة على الأقل ",Toast.LENGTH_SHORT).show();
            return;
        }
        if (QRcode.equals("")){
            Toast.makeText(this,"يجب إضافة QRcode ",Toast.LENGTH_SHORT).show();
            return;
        }
        list_images.clear();
        list_images.addAll(product.getImages());
        list_images=new ArrayList<>();
        upload_img(0, new upload_image() {
            @Override
            public void onsucc() {

                product.setName(name_);
                product.setDiscription(desc_);
                product.setImages(list_images);
                product.setUnity(unityList);
                product.setQuantity(0);
                product.setQuantity_sold(0);
                product.setCategorieID(categorieID);
                product.setQRcode(QRcode);
                Products products=new Products();
                products.Put_product(Edite_product.this,product , new Products.Get_product(){
                    @Override
                    public void onstart() {

                    }

                    @Override
                    public void onSuccess(Product product) {
                        Edite_product.this.finish();
                    }

                    @Override
                    public void onField(String msg) {

                    }
                });
            }
        });



    }
interface upload_image{
        void onsucc();
}
    private void upload_img(final int i, final upload_image listenner) {
        if (mPaths.size()==0){
            listenner.onsucc();
            return;
        }
        upload upload=new upload();
        upload.uploadImage(this, mPaths.get(i), new upload.Uploading() {
            @Override
            public void onstart() {

            }

            @Override
            public void onSuccess(String url) {
                list_images.add(url);
                if (i<mPaths.size()-1){
                    upload_img(i+1,listenner);
                }else {
                    listenner.onsucc();
                }
            }

            @Override
            public void onField(String msg) {

            }
        });
    }

    ///images
    private String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private void imageBrowse(int PICK_IMAGE_REQUEST) {

        if (EasyPermissions.hasPermissions(getApplicationContext(), galleryPermissions)) {
            Intent pickerPhotoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(pickerPhotoIntent, PICK_IMAGE_REQUEST);


        } else {
            EasyPermissions.requestPermissions(this, "Access for storage",
                    1000, galleryPermissions);

            Intent pickerPhotoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(pickerPhotoIntent, PICK_IMAGE_REQUEST);
        }


    }
    Images_adapter adapter;
    List<String> list_images;
    Bitmap bitmap=null;
    List<Bitmap> mPaths = new ArrayList<>();


    private void popup_addPrice() {
        final BottomSheetDialog dialog=new BottomSheetDialog(this);
        dialog.setContentView(R.layout.popup_add_unite);
        final EditText unity=dialog.findViewById(R.id.unity);
        final EditText price=dialog.findViewById(R.id.price);
        TextView save_btn=dialog.findViewById(R.id.save_btn);
        ImageView back_btn=dialog.findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String unity_=unity.getText().toString();
                String price_=price.getText().toString();
                if (unity_.isEmpty() ){
                    unity.setError(unity.getHint());
                    return;
                }
                if (price_.isEmpty()|| Double.parseDouble(price_)==0 ){
                    price.setError(price.getHint());
                    return;
                }
                //TODO add to DB
unityList.add(new Unity(unity_,Double.parseDouble(price_)));
                unities_adapter.notifyDataSetChanged();
                price.setText(unityList.get(0).getPrice().toString()+getString(R.string.rs));

                dialog.dismiss();
            }
        });
        dialog.show();


    }
    private void popup_editPrice() {
        final BottomSheetDialog dialog=new BottomSheetDialog(this);
        dialog.setContentView(R.layout.popup_add_unite);
        final EditText unity=dialog.findViewById(R.id.unity);
        final EditText price=dialog.findViewById(R.id.price);
        unity.setText(unity_select.getUnity());
        price.setText(unity_select.getPrice().toString());

        TextView save_btn=dialog.findViewById(R.id.save_btn);
        ImageView back_btn=dialog.findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String unity_=unity.getText().toString();
                String price_=price.getText().toString();
                if (unity_.isEmpty() ){
                    unity.setError(unity.getHint());
                    return;
                }
                if (price_.isEmpty()|| Double.parseDouble(price_)==0 ){
                    price.setError(price.getHint());
                    return;
                }
                //TODO add to DB
                unityList.remove(unity_select);
                unityList.add(new Unity(unity_,Double.parseDouble(price_)));
                unities_adapter.notifyDataSetChanged();
                price.setText(unityList.get(0).getPrice().toString()+getString(R.string.rs));

                dialog.dismiss();
            }
        });
        dialog.show();


    }
    private void popup_addCategories() {
        final BottomSheetDialog dialog=new BottomSheetDialog(this);
        dialog.setContentView(R.layout.popup_add_categoie);
        final EditText categorie=dialog.findViewById(R.id.categorie);

        TextView save_btn=dialog.findViewById(R.id.save_btn);
        ImageView back_btn=dialog.findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categorie_=categorie.getText().toString();

                if (categorie_.isEmpty() ){
                    categorie.setError(categorie.getHint());
                    return;
                }

                //TODO add to DB
                final ProgressDialog progressDialog=new ProgressDialog(Edite_product.this);
                progressDialog.setMessage("رفع البيانات...");
                Categories categories=new Categories();
                categories.Post_categorie(Edite_product.this, categorie_, new Categories.Get_categories() {
                    @Override
                    public void onstart() {
                        progressDialog.show();
                        dialog.dismiss();
                    }

                    @Override
                    public void onSuccess(Categorie categorie) {
                        progressDialog.dismiss();
                        fetch_data();
                    }

                    @Override
                    public void onField(String msg) {

                    }
                });

            }
        });
        dialog.show();


    }
    private void popup_editCategories() {
        final BottomSheetDialog dialog=new BottomSheetDialog(this);
        dialog.setContentView(R.layout.popup_add_categoie);
        final EditText categorie=dialog.findViewById(R.id.categorie);

        TextView save_btn=dialog.findViewById(R.id.save_btn);
        ImageView back_btn=dialog.findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categorie_=categorie.getText().toString();

                if (categorie_.isEmpty() ){
                    categorie.setError(categorie.getHint());
                    return;
                }

                //TODO add to DB
                final ProgressDialog progressDialog=new ProgressDialog(Edite_product.this);
                progressDialog.setMessage("رفع البيانات...");
                Categories categories=new Categories();
                categories.Put_categorie(Edite_product.this,categorieID, categorie_, new Categories.Get_categories() {
                    @Override
                    public void onstart() {
                        dialog.dismiss();
                        progressDialog.show();
                    }

                    @Override
                    public void onSuccess(Categorie categorie) {
                       progressDialog.dismiss();
                        fetch_data();
                    }

                    @Override
                    public void onField(String msg) {

                    }
                });

            }
        });
        dialog.show();


    }
}
