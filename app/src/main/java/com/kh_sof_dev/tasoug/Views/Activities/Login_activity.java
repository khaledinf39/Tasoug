package com.kh_sof_dev.tasoug.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.kh_sof_dev.tasoug.Controule.Info.Store_info;
import com.kh_sof_dev.tasoug.Controule.Routes.Stores;
import com.kh_sof_dev.tasoug.Controule.Routes.Users;
import com.kh_sof_dev.tasoug.Model.Classes.Store;
import com.kh_sof_dev.tasoug.R;

public class Login_activity extends AppCompatActivity implements View.OnClickListener {
private EditText pass,email;
Button login;
ProgressBar progressBar;
ImageView close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        initialize();
    }

    private void initialize() {
        pass=findViewById(R.id.ed_pass);
        email=findViewById(R.id.ed_email);
        login=findViewById(R.id.login_btn);
        progressBar=findViewById(R.id.progress);
        close=findViewById(R.id.close_btn);
        close.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.login_btn:
               login_fun();
               break;
           case R.id.close_btn:
               finish();
               break;
       }

    }

    private void login_fun() {
        Stores stores = new Stores();
        String password=pass.getText().toString();
        String phone=email.getText().toString();
        if (password.isEmpty()){
            pass.setError(pass.getHint());
            return;
        }
        if (phone.isEmpty()){
            email.setError(email.getHint());
            return;
        }

        password="656464";
        phone="18886545465";
        progressBar.setVisibility(View.VISIBLE);
        stores.Login(this, phone, password, new Stores.Get_Stores() {
            @Override
            public void onstart() {

            }

            @Override
            public void onSuccess(Store store) {

                new Store_info(store,getApplicationContext());
                Intent i=new Intent(Login_activity.this,MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onField(String msg) {

            }
        });
    }
}
