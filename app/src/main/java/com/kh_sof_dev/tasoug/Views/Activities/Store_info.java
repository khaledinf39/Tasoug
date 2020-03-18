package com.kh_sof_dev.tasoug.Views.Activities;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kh_sof_dev.tasoug.Controule.Routes.Stores;
import com.kh_sof_dev.tasoug.Controule.Routes.upload_img.upload;
import com.kh_sof_dev.tasoug.Model.Classes.ResizePickedImage;
import com.kh_sof_dev.tasoug.Model.Classes.Store;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Adapters.Images_adapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class Store_info extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_info);
        initialise();
        Loading_data();
    }



    private EditText user_name,commerce_name,store_name_ar,store_name_en,store_email,store_phone,store_city;
    private Button location_btn,logo_btn,save_btn;
    private ImageView back_btn;
    private void Loading_data() {
        user_name.setText(com.kh_sof_dev.tasoug.Controule.Info.Store_info.user_name);
        commerce_name.setText(com.kh_sof_dev.tasoug.Controule.Info.Store_info.commerceID);
        store_name_ar.setText(com.kh_sof_dev.tasoug.Controule.Info.Store_info.name_ar);
        store_name_en.setText(com.kh_sof_dev.tasoug.Controule.Info.Store_info.name_en);
        store_city.setText(com.kh_sof_dev.tasoug.Controule.Info.Store_info.address);
        store_email.setText(com.kh_sof_dev.tasoug.Controule.Info.Store_info.email);
        store_phone.setText(com.kh_sof_dev.tasoug.Controule.Info.Store_info.phone);
    }
    private void initialise() {
        user_name=findViewById(R.id.user_name);
        commerce_name=findViewById(R.id.comerce_name);
        store_city=findViewById(R.id.addres_name);
        store_name_ar=findViewById(R.id.store_name_ar);
        store_name_en=findViewById(R.id.store_name_en);
        store_email=findViewById(R.id.store_email);
        store_phone=findViewById(R.id.store_phone);
        location_btn=findViewById(R.id.store_location);
        logo_btn=findViewById(R.id.store_logo);
        save_btn=findViewById(R.id.save_btn);
        back_btn=findViewById(R.id.back_btn);
        /******************************************Actions***********************************/
        location_btn.setOnClickListener(this);
        logo_btn.setOnClickListener(this);
        save_btn.setOnClickListener(this);
        back_btn.setOnClickListener(this);

    }

    private static  int mapsPort=1,imageport=2;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.store_location:
                startActivityForResult(new Intent(Store_info.this,MapsActivity.class),mapsPort);
                break;
            case R.id.store_logo:
                imageBrowse();
                break;
            case R.id.save_btn:
                save_fun();
                break;
            case R.id.back_btn:
                finish();
                break;
        }
    }
public  static  Double lat=0.0,lng=0.0;
    private void save_fun() {

        List<EditText > editTextList=new ArrayList<>();
        editTextList.add(user_name);
        editTextList.add(commerce_name);
        editTextList.add(store_phone);
        editTextList.add(store_name_ar);
        editTextList.add(store_name_en);
        editTextList.add(store_email);
        editTextList.add(store_city);
        if (!Verify(editTextList)){
            return;
        }
        if (lat==0.0 || lng==0.0){
            location_btn.setError(location_btn.getText());
            return;
        }
if (bitmap==null){
    logo_btn.setError(logo_btn.getText());
    return;
}else {
    upload upload=new upload();
    upload.uploadImage(this, bitmap, new upload.Uploading() {
        @Override
        public void onstart() {

        }

        @Override
        public void onSuccess(String url) {

            Store store=new Store();
            store.setUser_name(user_name.getText().toString());
            store.setCommerceID(commerce_name.getText().toString());
            store.setPhone(store_phone.getText().toString());
            store.setStore_name_ar(store_name_ar.getText().toString());
            store.setStore_name_en(store_name_en.getText().toString());
            store.setEmail(store_email.getText().toString());
            store.setAddress(store_city.getText().toString());
            store.setLat(lat);
            store.setLng(lng);
            store.setImage(url);
            final ProgressDialog dialog=new ProgressDialog(Store_info.this);
            dialog.setMessage("رفع الى قاعدة البيانات...");
            dialog.show();
            Stores stores=new Stores();
            stores.Put_store(Store_info.this, store, new Stores.Get_Stores() {
                @Override
                public void onstart() {

                }

                @Override
                public void onSuccess(Store store) {
                    new com.kh_sof_dev.tasoug.Controule.Info.Store_info(store,getApplicationContext());
                    Toast.makeText(getApplicationContext(),"تم رفع معلومات المحل",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    finish();
                }

                @Override
                public void onField(String msg) {

                }
            });
        }

        @Override
        public void onField(String msg) {

        }
    });
}

    }

    private boolean Verify(List<EditText> editTextList) {
        for (EditText e:editTextList
             ) {
            if (e.getText().toString().isEmpty()){
                e.setError("");
                return false;
            }
        }
        return true;
    }


    ///images
    private String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private void imageBrowse() {

        if (EasyPermissions.hasPermissions(getApplicationContext(), galleryPermissions)) {
            Intent pickerPhotoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(pickerPhotoIntent, imageport);


        } else {
            EasyPermissions.requestPermissions(this, "Access for storage",
                    1000, galleryPermissions);

            Intent pickerPhotoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(pickerPhotoIntent, imageport);
        }


    }


    Bitmap bitmap=null;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==mapsPort && resultCode== Activity.RESULT_OK){
            lat=data.getDoubleExtra("lat",0.0);
            lng=data.getDoubleExtra("lng",0.0);
            if (lat==0.0 || lng ==0.0){
                location_btn.setBackground(getDrawable(R.drawable.bk_btn_save));
                location_btn.setText("تم أختيار المكان من الخريطة ");
            }
        }

        if (requestCode==imageport && resultCode== Activity.RESULT_OK){

            Uri returnUri = data.getData();
            ResizePickedImage resizePickedImage = new ResizePickedImage();
            String realePath = resizePickedImage.getRealPathFromURI(returnUri, this);
            System.out.println(realePath);
            String compresedImagePath;
            Bitmap bitmapImage = null;
            try {
                Uri contentURI = data.getData();
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), contentURI);
               if (bitmap!=null){
                   logo_btn.setBackground(getDrawable(R.drawable.bk_btn_save));
                   logo_btn.setText("تم إختيار شعار المحل ");
               }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
