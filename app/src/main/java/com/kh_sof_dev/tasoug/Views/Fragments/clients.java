package com.kh_sof_dev.tasoug.Views.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kh_sof_dev.tasoug.Controule.Routes.Users;
import com.kh_sof_dev.tasoug.Model.Classes.User;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Adapters.Users_adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class clients extends Fragment {


    public clients() {
        // Required empty public constructor
    }

private RecyclerView RV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_request, container, false);
        intitalize(view);
        Loding_data();
        return view;
    }

    private ProgressBar progressBar;
    private TextView noitem;
    private List<User> userList;
    private Users_adapter users_adapter;

    private void Loding_data() {
        Users users=new Users();
        users.Get_All_clients(getContext(), new Users.Get_users() {
            @Override
            public void onstart() {

            }

            @Override
            public void onSuccess(User user) {
                progressBar.setVisibility(View.GONE);
                if (user.getUserList().size()==0){
                    noitem.setVisibility(View.VISIBLE);
                }else {
                    userList.clear();
                    userList.addAll(user.getUserList());
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
        users_adapter=new Users_adapter(getContext(), userList, new Users_adapter.Selected_item() {
            @Override
            public void Onselcted(User user) {

            }
        });
        RV.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        RV.setAdapter(users_adapter);
    }

}
