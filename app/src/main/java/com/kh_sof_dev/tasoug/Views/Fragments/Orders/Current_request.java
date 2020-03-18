package com.kh_sof_dev.tasoug.Views.Fragments.Orders;


import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kh_sof_dev.tasoug.Controule.Routes.Orders;
import com.kh_sof_dev.tasoug.Controule.Routes.Users;
import com.kh_sof_dev.tasoug.Model.Classes.Order;
import com.kh_sof_dev.tasoug.Model.Classes.User;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Activities.Client_details;
import com.kh_sof_dev.tasoug.Views.Activities.Store_info;
import com.kh_sof_dev.tasoug.Views.Adapters.Orders_adapter;
import com.kh_sof_dev.tasoug.Views.Adapters.Users_adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Current_request extends Fragment {


    public Current_request() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_request, container, false);
        intitalize(view);
       if (type.equals("one")){
           Loding_dataONE();
       }else {
           Loding_dataALL();
       }

        com.kh_sof_dev.tasoug.Views.Fragments.Orders.Order.oncurent_listenner=new com.kh_sof_dev.tasoug.Views.Fragments.Orders.Order.onfilter() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void getDate(String date) {
//                Toast.makeText(getContext(),date+" current",Toast.LENGTH_SHORT).show();
                List<com.kh_sof_dev.tasoug.Model.Classes.Order> orderList_filter=new ArrayList<>();
                orders_adapter=null;
                orders_adapter=new Orders_adapter(getContext(),orderList_filter);
                RV.setAdapter(orders_adapter);
                for (Order or :orderList
                ) {
                    if (FilterByDate(getdate(or.getCreate_at()),date)){
                        orderList_filter.add(or);
                    }
                }

                if (orderList_filter.size()==0){
                    noitem.setVisibility(View.VISIBLE);
                }else {
                    noitem.setVisibility(View.GONE);

                }
                orders_adapter.notifyDataSetChanged();
            }
        };

        return view;
    }
    private boolean FilterByDate(String date1,String date2) {


        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date datein = sdf.parse(date1);
            Date dateto = sdf.parse(date2);

            if (datein.compareTo(dateto)==0 ){
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getdate(String date_){
        Log.e("date_", date_.substring(0, date_.indexOf("T")));
        return date_.substring(0, date_.indexOf("T"));
    }
    private int status=1;
    public static String type="one";

    private RecyclerView RV;
    private ProgressBar progressBar;
    private TextView noitem;
    private List<Order> orderList;
    private Orders_adapter orders_adapter;

    private void Loding_dataONE() {
        Orders order=new Orders();
        order.Get_All_orderByuser(getContext(), Client_details.user.getID(), status, new Orders.Get_Order() {
            @Override
            public void onstart() {

            }

            @Override
            public void onSuccess(com.kh_sof_dev.tasoug.Model.Classes.Order order) {
                progressBar.setVisibility(View.GONE);
                if (order.getOrderList().size()==0){
                    noitem.setVisibility(View.VISIBLE);
                }else {
                    orderList.clear();
                    orderList.addAll(order.getOrderList());
                    orders_adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onField(String msg) {

            }
        });
    }
    private void Loding_dataALL() {
        Orders order=new Orders();
        order.Get_All_orderBystore(getContext(), "", status, new Orders.Get_Order() {
            @Override
            public void onstart() {

            }

            @Override
            public void onSuccess(Order order) {
                progressBar.setVisibility(View.GONE);
                if (order.getOrderList().size()==0){
                    noitem.setVisibility(View.VISIBLE);
                }else {
                    orderList.clear();
                    orderList.addAll(order.getOrderList());
                    orders_adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onField(String msg) {

            }
        });
    }
    private void intitalize(View view) {
        RV=view.findViewById(R.id.RV);
        progressBar=view.findViewById(R.id.progress);
        noitem=view.findViewById(R.id.no_items);

        /***********************actions******/
        orderList=new ArrayList<>();
        orders_adapter=new Orders_adapter(getContext(),orderList);
        RV.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        RV.setAdapter(orders_adapter);
    }

}
