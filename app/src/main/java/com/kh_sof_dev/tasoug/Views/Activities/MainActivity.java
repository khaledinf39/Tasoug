package com.kh_sof_dev.tasoug.Views.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Fragments.main;
import com.yarolegovich.slidingrootnav.SlideGravity;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    public static FragmentTransaction transaction;
    public static FragmentTransaction fragmentTransaction;
    DrawerLayout navDrawer;
    private Fragment fragment;
    public  static TextView titel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titel=findViewById(R.id.title);


        /** TODO ***************************DRAWER new Library********************************/
        ImageView hamMenu = findViewById(R.id.menu_btn);
         navDrawer = findViewById(R.id.drawer_layout);
        hamMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // If the navigation drawer is not open then open it, if its already open then close it.
                if(!navDrawer.isDrawerOpen(GravityCompat.START))
                    navDrawer.openDrawer(GravityCompat.START);
                else navDrawer.closeDrawer(GravityCompat.END);
            }
        });


        NavigationView navigation = (NavigationView) findViewById(R.id.nv);
fragment=new main();
        transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_new, fragment);
        transaction.commit();


        Button btn_forniseur=(Button)findViewById(R.id.btn_forniseur);
        btn_forniseur.setText("hi hi hi ");
        btn_forniseur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent box=new Intent(MainActivity.this, Box.class);
                startActivity(box);

                Log.d("onclickbutton" ,"hi khaled");
                Toast.makeText(getApplicationContext(),v.getId()+"",Toast.LENGTH_SHORT).show();
            }
        });




    }


    public void onLabelClick(View view) {
        // do stuff :)

        Intent box=new Intent(MainActivity.this, Box.class);
        startActivity(box);

        Log.d("onclickbutton" ,"hi khaled");
        Toast.makeText(getApplicationContext(),"hihihihihii",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(getApplicationContext(),item.getItemId()+"",Toast.LENGTH_SHORT).show();

        switch (item.getItemId()) {
            case android.R.id.home:
                navDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
