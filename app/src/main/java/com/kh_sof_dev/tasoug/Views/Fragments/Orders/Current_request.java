package com.kh_sof_dev.tasoug.Views.Fragments.Orders;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kh_sof_dev.tasoug.Controule.Routes.Orders;
import com.kh_sof_dev.tasoug.Controule.Routes.Users;
import com.kh_sof_dev.tasoug.Model.Classes.Order;
import com.kh_sof_dev.tasoug.Model.Classes.User;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Activities.Client_details;
import com.kh_sof_dev.tasoug.Views.Activities.Store_info;
import com.kh_sof_dev.tasoug.Views.Adapters.Orders_adapter;
import com.kh_sof_dev.tasoug.Views.Adapters.Users_adapter;

import java.util.ArrayList;
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
        return view;
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
