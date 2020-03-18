package com.kh_sof_dev.tasoug.Views.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kh_sof_dev.tasoug.Controule.Routes.Users;
import com.kh_sof_dev.tasoug.Model.Classes.Supplier;
import com.kh_sof_dev.tasoug.Model.Classes.User;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Adapters.Suppliers_adapter;
import com.kh_sof_dev.tasoug.Views.Adapters.Users_adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class forniseure extends Fragment {


    public forniseure() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_request, container, false);
        intitalize(view);
        Loding_data();
        return view;
    }

    private RecyclerView RV;
    private ProgressBar progressBar;
    private TextView noitem;
    private List<Supplier> userList;
    private Suppliers_adapter users_adapter;

    private void Loding_data() {
        Users users=new Users();
        users.Get_All_supplier(getContext(), new Users.Get_supplier() {
            @Override
            public void onstart() {

            }

            @Override
            public void onSuccess(Supplier user) {
                progressBar.setVisibility(View.GONE);
                if (user.getSuppliers().size()==0){
                    noitem.setVisibility(View.VISIBLE);
                }else {
                    userList.clear();
                    userList.addAll(user.getSuppliers());
                    users_adapter.notifyDataSetChanged();
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
        userList=new ArrayList<>();
        users_adapter=new Suppliers_adapter(getContext(), userList, new Suppliers_adapter.Selected_item() {
            @Override
            public void Onselcted(Supplier supplier) {

            }
        });
        RV.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        RV.setAdapter(users_adapter);
    }

}
