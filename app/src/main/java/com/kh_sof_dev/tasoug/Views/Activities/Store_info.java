package com.kh_sof_dev.tasoug.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kh_sof_dev.tasoug.Controule.Routes.Stores;
import com.kh_sof_dev.tasoug.Model.Classes.Store;
import com.kh_sof_dev.tasoug.R;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.store_location:
                break;
            case R.id.store_logo:
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
//            return;
        }

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
        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage("رفع الى قاعدة البيانات...");
        dialog.show();
        Stores stores=new Stores();
        stores.Put_store(this, store, new Stores.Get_Stores() {
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
}
