package com.kh_sof_dev.tasoug.Views.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Activities.Box;
import com.kh_sof_dev.tasoug.Views.Activities.Expenses;
import com.kh_sof_dev.tasoug.Views.Activities.MainActivity;
import com.kh_sof_dev.tasoug.Views.Fragments.Mostalamat.Mostalamat;
import com.kh_sof_dev.tasoug.Views.Fragments.Orders.Order;

/**
 * A simple {@link Fragment} subclass.
 */
public class main extends Fragment implements View.OnClickListener {


    public main() {
        // Required empty public constructor
    }

private Button btn_montagat,btn_mouch,btn_client,btn_forniseur,btn_soundok,btn_masroufat,btn_mabiat,btn_statistique
        ,btn_orders,btn_option,btn_mostalamat;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_main, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        btn_montagat=view.findViewById(R.id.btn_montagat);
        btn_mostalamat=view.findViewById(R.id.btn_mostalamat);
        btn_mouch=view.findViewById(R.id.btn_mouch);
        btn_client=view.findViewById(R.id.btn_client);
        btn_forniseur=view.findViewById(R.id.btn_forniseur);
        btn_soundok=view.findViewById(R.id.btn_soundok);
        btn_masroufat=view.findViewById(R.id.btn_masroufat);
        btn_mabiat=view.findViewById(R.id.btn_mabiat);
        btn_statistique=view.findViewById(R.id.btn_statistique);
        btn_orders=view.findViewById(R.id.btn_orders);
        btn_option=view.findViewById(R.id.btn_option);

        btn_montagat.setOnClickListener(this);
        btn_mouch.setOnClickListener(this);
        btn_client.setOnClickListener(this);
        btn_forniseur.setOnClickListener(this);
        btn_soundok.setOnClickListener(this);
        btn_masroufat.setOnClickListener(this);
        btn_mabiat.setOnClickListener(this);
        btn_statistique.setOnClickListener(this);
        btn_orders.setOnClickListener(this);
        btn_option.setOnClickListener(this);
        btn_mostalamat.setOnClickListener(this);



    }

    private void switchFGM(Fragment fragment){
    MainActivity.transaction = getFragmentManager().beginTransaction();
    MainActivity.transaction.replace(R.id.container_new, fragment);
    MainActivity.transaction.commit();
}

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.btn_montagat:
               Products products=new Products();
               switchFGM(products);
               MainActivity.titel.setText("المنتجات");
               break;

           case R.id.btn_mouch:
               mochtarawat mochtarawat=new mochtarawat();
               switchFGM(mochtarawat);
               MainActivity.titel.setText("المشتريات");

               break;
           case R.id.btn_mabiat:
               mabiat mabiat=new mabiat();
               switchFGM(mabiat);
               MainActivity.titel.setText("المبيعات");

               break;

           case R.id.btn_client:
               clients clients=new clients();
               switchFGM(clients);
               MainActivity.titel.setText("العملاء");

               break;

           case R.id.btn_forniseur:
               forniseure forniseure=new forniseure();
               switchFGM(forniseure);
               MainActivity.titel.setText("الموردين");

               break;

           case R.id.btn_soundok:
               Intent box=new Intent(getActivity(), Box.class);
               startActivity(box);
               break;

           case R.id.btn_masroufat:
               Intent exp=new Intent(getActivity(),Expenses.class);
               startActivity(exp);
               break;

           case R.id.btn_mostalamat:
               Mostalamat mostalamat=new Mostalamat();
               switchFGM(mostalamat);
               MainActivity.titel.setText("المستلمات");

               break;

           case R.id.btn_statistique:
//               Products products=new Products();
//               switchFGM(products);
               break;

           case R.id.btn_orders:
               Order order=new Order();
               switchFGM(order);
               MainActivity.titel.setText("الطلبات");

               break;

           case R.id.btn_option:
               options options=new options();
               switchFGM(options);
               MainActivity.titel.setText("الإعدادات");

               break;


       }
    }
}
