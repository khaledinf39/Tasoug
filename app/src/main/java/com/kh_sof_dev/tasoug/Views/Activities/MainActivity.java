package com.kh_sof_dev.tasoug.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.kh_sof_dev.tasoug.Controule.Info.Store_info;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Fragments.Mostalamat.Mostalamat;
import com.kh_sof_dev.tasoug.Views.Fragments.Orders.Order;
import com.kh_sof_dev.tasoug.Views.Fragments.clients;
import com.kh_sof_dev.tasoug.Views.Fragments.forniseure;
import com.kh_sof_dev.tasoug.Views.Fragments.mabiat;
import com.kh_sof_dev.tasoug.Views.Fragments.main;
import com.kh_sof_dev.tasoug.Views.Fragments.mochtarawat;
import com.kh_sof_dev.tasoug.Views.Fragments.mostalamat;
import com.kh_sof_dev.tasoug.Views.Fragments.options;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static FragmentManager fragmentManager;
    public static FragmentTransaction transaction;
    public static FragmentTransaction fragmentTransaction;
    DrawerLayout navDrawer;
    private Fragment fragment;
    public  static TextView titel;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Store_info(getApplicationContext());
        setContentView(R.layout.activity_main);
        titel=findViewById(R.id.title);


        /** TODO ***************************DRAWER new Library********************************/
         navigationView=findViewById(R.id.nv);



fragment=new main();
        switchFGM(fragment);
initialize();






    }
    private void Open(){
        if(!navDrawer.isDrawerOpen(GravityCompat.START))
            navDrawer.openDrawer(GravityCompat.START);
        else navDrawer.closeDrawer(GravityCompat.END);
    }
    private void switchFGM(Fragment fragment){
       transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_new, fragment);
      transaction.commit();
    }
    ImageView hamMenu;
    private Button fornisser_btn,client_btn,order,mostalamat;
    private LinearLayout home,option,servece,share_supp,share_client,logout,mabiat,moch;
    private void initialize() {
        fornisser_btn=findViewById(R.id.btn_forniseur);
        client_btn=findViewById(R.id.btn_client);
        order=findViewById(R.id.btn_orders);
        mostalamat=findViewById(R.id.btn_mostalamat);

        home=findViewById(R.id.btn_home);
        option=findViewById(R.id.btn_option);
        servece=findViewById(R.id.btn_service);
        share_client=findViewById(R.id.btn_share_client);
        share_supp=findViewById(R.id.btn_share_fornis);
        logout=findViewById(R.id.btn_logout);
        mabiat=findViewById(R.id.btn_mabiat);
        moch=findViewById(R.id.btn_mouch);

        ImageView hamMenu = findViewById(R.id.menu_btn);
        navDrawer = findViewById(R.id.drawer_layout);

     /**********************************Actions**********************/
        fornisser_btn.setOnClickListener(this);
        client_btn.setOnClickListener(this);
        order.setOnClickListener(this);
        mostalamat.setOnClickListener(this);

        home.setOnClickListener(this);
        option.setOnClickListener(this);
        servece.setOnClickListener(this);
        share_supp.setOnClickListener(this);
        share_client.setOnClickListener(this);
        logout.setOnClickListener(this);
        moch.setOnClickListener(this);
        mabiat.setOnClickListener(this);
        hamMenu.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_forniseur:
              switchFGM(new forniseure());
                navDrawer.closeDrawers();
                MainActivity.titel.setText("الموردين");

                break;
            case R.id.btn_home:
                switchFGM(new main());
                navDrawer.closeDrawers();
                MainActivity.titel.setText("الرئيسية");


                break;
            case R.id.btn_option:
                switchFGM(new options());
                navDrawer.closeDrawers();
                MainActivity.titel.setText("الإعدادات");


                break;
            case R.id.btn_orders:
                switchFGM(new Order());
                navDrawer.closeDrawers();
                MainActivity.titel.setText("الطلبات");


                break;
            case R.id.btn_mouch:
                switchFGM(new mochtarawat());
                navDrawer.closeDrawers();
                MainActivity.titel.setText("المشتريات");


                break;
            case R.id.btn_mabiat:
                switchFGM(new mabiat());
                navDrawer.closeDrawers();
                MainActivity.titel.setText("المبيعات");


                break;

            case R.id.btn_mostalamat:

                switchFGM(new Mostalamat());
                navDrawer.closeDrawers();
                MainActivity.titel.setText("المستلمات");


                break;
            case R.id.btn_client:
                switchFGM(new clients());
                navDrawer.closeDrawers();
                MainActivity.titel.setText("العملاء");


                break;
            case R.id.btn_share_client:
                navDrawer.closeDrawers();


                break;
            case R.id.btn_share_fornis:
                navDrawer.closeDrawers();


                break;
            case R.id.btn_logout:
                navDrawer.closeDrawers();


                break;
            case R.id.menu_btn:
                Open();
                break;

        }
    }
}
