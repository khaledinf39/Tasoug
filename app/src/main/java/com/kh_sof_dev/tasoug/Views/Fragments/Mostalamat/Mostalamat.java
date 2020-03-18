package com.kh_sof_dev.tasoug.Views.Fragments.Mostalamat;


import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Color;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Activities.Order_details;
import com.kh_sof_dev.tasoug.Views.Fragments.Orders.Order;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class Mostalamat extends Fragment {


    public Mostalamat() {
        // Required empty public constructor
    }

    //    public static ImageView avatar;
    // Titles of the individual pages (displayed in tabs)
    private final String[] PAGE_TITLES = new String[] {

            "الحالية",

            "المكتملة",
            "ملغاة"

    };

    private final Fragment[] PAGES = new Fragment[] {

            new com.kh_sof_dev.tasoug.Views.Fragments.Mostalamat.Current_request(),
            new com.kh_sof_dev.tasoug.Views.Fragments.Mostalamat.Compte_request(),
            new com.kh_sof_dev.tasoug.Views.Fragments.Mostalamat.Cancel_request(),
    };

    public static FragmentManager fragmentManager;
    public static FragmentTransaction fragmentTransaction;
    public static Context context;

    private ViewPager mViewPager;


    ImageView alerts_icon, cancel_btn;

    private String email,password,firebaseToken,logoutMessage;

    private LocationManager locationManager = null;
    private LocationListener locationListener = null;
    private Boolean flag;
    public static int ReqType;

    private PendingIntent pendingIntent;
    private AlarmManager manager;


    public interface onfilter{
        void   getDate(String date);
    }

    public static Order.onfilter oncancel_listenner,oncomplet_listenner,oncurent_listenner;
    Calendar calendar;
    public DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            calendar= Calendar.getInstance();
            calendar.set(Calendar.YEAR,selectedYear);
            calendar.set(Calendar.DAY_OF_MONTH,selectedDay);
            calendar.set(Calendar.MONTH,selectedMonth);
//            SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

            view.setVisibility(view.isShown()
                    ?View.GONE
                    :View.VISIBLE);
            String formatedDate = format.format(calendar.getTime());
            switch (tabLayout.getSelectedTabPosition()){
                case 0:
                    oncurent_listenner.getDate(formatedDate);
                    break;

                case 1:
                    oncomplet_listenner.getDate(formatedDate);
                    break;

                case 2:
                    oncancel_listenner.getDate(formatedDate);
                    break;

            }





        }
    };


    public void showThePickers() {

        calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog;
        datePickerDialog = new DatePickerDialog(getContext(),
                R.style.TimePickerTheme,datePickerListener,
                calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    TabLayout tabLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_order, container, false);

        context = getContext();
        Current_request.type="all";
        Cancel_request.type="all";
        Compte_request.type="all";
        Order_details.type="mostalamat";
        /****************************definitions*****************************/
        FloatingActionButton filter;

        filter=view.findViewById(R.id.fab);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showThePickers();
            }
        });
        oncancel_listenner=new Order.onfilter() {
            @Override
            public void getDate(String date) {

            }
        };
        oncomplet_listenner=new Order.onfilter() {
            @Override
            public void getDate(String date) {

            }
        };
        oncurent_listenner=new Order.onfilter() {
            @Override
            public void getDate(String date) {

            }
        };




        fragmentManager = getActivity().getSupportFragmentManager();
        mViewPager = (ViewPager) view.findViewById(R.id.viewpage);
        mViewPager.setAdapter(new MyPagerAdapter(getActivity().getSupportFragmentManager()));

         tabLayout = (TabLayout) view.findViewById(R.id.tab);
//        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#56ce8b"));
        tabLayout.setSelectedTabIndicatorHeight((int) (4 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#ffffff"));

        tabLayout.setupWithViewPager(mViewPager);
//
        /*****************************Drawer Actions*******************************/


return view;

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
