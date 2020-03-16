package com.kh_sof_dev.tasoug.Views.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.kh_sof_dev.tasoug.Controule.Routes.Mostalamat;
import com.kh_sof_dev.tasoug.Model.Classes.Expense;
import com.kh_sof_dev.tasoug.Model.Classes.ProductOFmost;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Adapters.Expenses_adapter;
import com.kh_sof_dev.tasoug.Views.Adapters.Mostalamat_adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class mostalamat extends Fragment {


    public mostalamat() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_mostalamat, container, false);
        initialize(view);
        Loading(1);
        return view;
    }

    private void Loading(int status) {
        Mostalamat mostalamat=new Mostalamat();
        mostalamat.Get_All_orderBystore(getContext(), new Mostalamat.Get_Most() {
            @Override
            public void onstart() {

            }

            @Override
            public void onSuccess(ProductOFmost productOFmost) {
productOFmostList.clear();
productOFmostList.addAll(productOFmost.getProductOFmostList());
progressBar.setVisibility(View.GONE);
if (productOFmostList.size()==0){
    noitem.setVisibility(View.VISIBLE);
}
adapter.notifyDataSetChanged();
            }

            @Override
            public void onField(String msg) {

            }
        });
    }

    private TextView order_act,order_des,tot_act,tot_des,total;
    private Spinner filter;
    private RecyclerView RV;

    private TextView noitem;
    private ProgressBar progressBar;
    private Mostalamat_adapter adapter;
    List<ProductOFmost> productOFmostList;

    private void initialize(View view) {
        /*********************declaration**************************/

        RV=view.findViewById(R.id.RV);
        noitem=view.findViewById(R.id.no_items);
        progressBar=view.findViewById(R.id.progress);
        order_act=view.findViewById(R.id.order_act);
        order_des=view.findViewById(R.id.order_desact);
        tot_act=view.findViewById(R.id.total_act);
        tot_des=view.findViewById(R.id.total_desact);
        total=view.findViewById(R.id.total_order);
        filter=view.findViewById(R.id.filter_spin);


        /*************************actions*********************************/

        productOFmostList=new ArrayList<>();
        adapter=new Mostalamat_adapter(getContext(), productOFmostList);
        RV.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        RV.setAdapter(adapter);
    }


}
