package com.kh_sof_dev.tasoug;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Seplesh_activity extends AppCompatActivity {

    private static final long SPLASH_TIME = 20000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seplesh_activity);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // run() method will be executed when 3 seconds have passed
                //Time to start Intro
                SharedPreferences sp1=getSharedPreferences("Login", MODE_PRIVATE);
                //startActivity(new Intent(LogoAct.this,Intro.class));


                if (!sp1.getBoolean("login", true)){
                    /***********/
                    //openIntroPage
                    startActivity(new Intent(Seplesh_activity.this,Login_activity.class));
                    finish();

                }else {
                    Intent intent = new Intent(Seplesh_activity.this, MainActivity.class);
                    startActivity(intent );
                    finish();
                }

            }
        }, SPLASH_TIME);
    }
}
