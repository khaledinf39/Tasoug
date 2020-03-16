package com.kh_sof_dev.tasoug.Views.Fragments;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Color;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Fragments.Orders.Cancel_request;
import com.kh_sof_dev.tasoug.Views.Fragments.Orders.Compte_request;
import com.kh_sof_dev.tasoug.Views.Fragments.Orders.Current_request;

/**
 * A simple {@link Fragment} subclass.
 */
public class Order extends Fragment {


    public Order() {
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

            new Current_request(),
            new Compte_request(),
            new Cancel_request(),
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_order, container, false);

        context = getContext();
        Current_request.type="all";
        Cancel_request.type="all";
        Compte_request.type="all";

        /****************************definitions*****************************/




        fragmentManager = getActivity().getSupportFragmentManager();
        mViewPager = (ViewPager) view.findViewById(R.id.viewpage);
        mViewPager.setAdapter(new MyPagerAdapter(getActivity().getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab);
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
