package com.kh_sof_dev.tasoug.Views.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.kh_sof_dev.tasoug.Controule.Routes.Users;
import com.kh_sof_dev.tasoug.Model.Classes.Supplier;
import com.kh_sof_dev.tasoug.Model.Classes.Term;
import com.kh_sof_dev.tasoug.Model.Classes.User;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Fragments.Mostalamat.Cancel_request;
import com.kh_sof_dev.tasoug.Views.Fragments.Mostalamat.Compte_request;
import com.kh_sof_dev.tasoug.Views.Fragments.Mostalamat.Current_request;

import pub.devrel.easypermissions.EasyPermissions;

public class Fornisieur_details extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    public static Supplier supplier;



    private final String[] PAGE_TITLES = new String[]{

            "الحالية",

            "المكتملة",
            "ملغاة"

    };

    private final Fragment[] PAGES = new Fragment[]{

            new Current_request(),
            new Compte_request(),
            new Cancel_request(),
    };

    public static FragmentManager fragmentManager;
    public static FragmentTransaction fragmentTransaction;


    private ViewPager mViewPager;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fornisieur_details);
        initialize();
        Loading_data();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void Loading_data() {
        if (supplier != null) {
            name.setText(supplier.getName());
            date.setText(getdate(supplier.getCreate_at()));
            phone.setText(supplier.getPhone());
            commerce_name.setText(supplier.getCoumerce_name());
            scour.setText(supplier.getScoure().toString()+" ر.س ");

            adress.setText(supplier.getAddress());
            status.setChecked(supplier.getStatus());

            if (supplier.getStatus()) {
                status_txt.setText("إقاف الحساب");
            } else {
                status_txt.setText("تفعيل الحساب");
            }
        }
    }

    private TextView name, phone, adress, date, status_txt,scour,commerce_name;
    private ImageView back_btn, call_btn;
    private Switch status;
private Button empty;
    private void initialize() {
        name = findViewById(R.id.name);
        scour = findViewById(R.id.scour);
        phone = findViewById(R.id.phone);
        adress = findViewById(R.id.address);
        empty = findViewById(R.id.empty_btn);
        commerce_name = findViewById(R.id.comerce_name);
        date = findViewById(R.id.date);
        status_txt = findViewById(R.id.t2);
        status = findViewById(R.id.status);
        back_btn = findViewById(R.id.back_btn);
        call_btn = findViewById(R.id.call_btn);
        mViewPager = (ViewPager) findViewById(R.id.viewpage);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        /******************************Actions****************************/
        status.setOnCheckedChangeListener(this);
        back_btn.setOnClickListener(this);
        empty.setOnClickListener(this);
        call_btn.setOnClickListener(this);
        fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new Fornisieur_details.MyPagerAdapter(getSupportFragmentManager()));


//        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#56ce8b"));
        tabLayout.setSelectedTabIndicatorHeight((int) (4 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#ffffff"));

        tabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.call_btn:

                Callpermissien();

                break;

            case R.id.empty_btn:

                popup_emty();

                break;
        }
    }

    private void popup_emty() {
        final BottomSheetDialog dialog=new BottomSheetDialog(this);
        dialog.setContentView(R.layout.popup_empty);
        final EditText title=dialog.findViewById(R.id.tcheck_out);
       title.setText(supplier.getScoure().toString()+" ر.س ");
        TextView save_btn=dialog.findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String price=title.getText().toString();

                if (price.isEmpty() ){
                    title.setError(title.getHint());
                    return;
                }
                if (Double.parseDouble(price)==0.0 ){
                    title.setError(title.getHint());
                    return;
                }


                dialog.dismiss();
            }
        });
        dialog.show();

    }

    ///Calling
    private String[] callPermissions = {Manifest.permission.CALL_PHONE};

    @SuppressLint("MissingPermission")
    private void Callpermissien() {

        if (EasyPermissions.hasPermissions(getApplicationContext(), callPermissions)) {
            String posted_by = supplier.getPhone();

            String uri = "tel:" + posted_by.trim();
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse(uri));
            startActivity(intent);


        } else {
            EasyPermissions.requestPermissions(this, "Access for Call phone",
                    1000, callPermissions);
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getdate(String date_){
        Log.e("date_", date_.substring(0, date_.indexOf("T")));
        return date_.substring(0, date_.indexOf("T"));
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        supplier.setStatus(isChecked);
        Users users=new Users();
        users.Put_statusOFsupplier(this, supplier, new Users.Get_users() {
            @Override
            public void onstart() {

            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSuccess(User user) {
                Loading_data();
            }

            @Override
            public void onField(String msg) {

            }
        });


    }

    /* PagerAdapter for supplying the ViewPager with the pages (fragments) to display. */
    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return PAGES[position];
        }

        @Override
        public int getCount() {
            return PAGES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return PAGE_TITLES[position];
        }

    }
}
