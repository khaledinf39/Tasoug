package com.kh_sof_dev.tasoug.Views.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Activities.Add_product;
import com.kh_sof_dev.tasoug.Views.Activities.Term_and_conditions;

/**
 * A simple {@link Fragment} subclass.
 */
public class Products extends Fragment implements View.OnClickListener {


    public Products() {
        // Required empty public constructor
    }

private FloatingActionButton fab_plase,fab_qr_code_sale,fab_qr_code_bay;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_products, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        fab_plase=view.findViewById(R.id.fab_plase);
        fab_qr_code_sale=view.findViewById(R.id.fab_qr_code_sale);
        fab_qr_code_bay=view.findViewById(R.id.fab_qr_code_bay);

        fab_plase.setOnClickListener(this);
        fab_qr_code_sale.setOnClickListener(this);
        fab_qr_code_bay.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_plase:
                Intent fab_plase=new Intent(getActivity(), Add_product.class);
                startActivity(fab_plase);
                break;
//
//            case R.id.fab_qr_code_sale:
//                Intent fab_qr_code_sale=new Intent(getActivity(), mochtarawat.class);
//                startActivity(fab_qr_code_sale);
//                break;
//
//            case R.id.fab_qr_code_bay:
//                Intent fab_qr_code_bay=new Intent(getActivity(), mostalamat.class);
//                startActivity(fab_qr_code_bay);
//                break;
        }
    }
}
