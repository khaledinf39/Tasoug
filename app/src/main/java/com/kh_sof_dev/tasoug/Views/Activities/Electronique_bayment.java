package com.kh_sof_dev.tasoug.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kh_sof_dev.tasoug.Model.Classes.Electronique_payment;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Adapters.Elec_payment_adapter;
import com.kh_sof_dev.tasoug.Views.Adapters.Expenses_bond_adapter;

import java.util.ArrayList;
import java.util.List;

public class Electronique_bayment extends AppCompatActivity implements View.OnClickListener {

    public static Electronique_payment elc_payment=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronique_bayment);
        initialize();
        Loaading();
    }

    private void Loaading() {
       if (elc_payment!=null){
           progressBar.setVisibility(View.GONE);
           electronique_paymentList.clear();
           electronique_paymentList.addAll(elc_payment.getElectronique_paymentList());
           if (electronique_paymentList.size()==0){
               noitem.setVisibility(View.VISIBLE);
           }
           adapter.notifyDataSetChanged();
           totla.setText(elc_payment.getToutal().toString()+" ر.س ");
       }

    }

    private RecyclerView RV;
    private TextView noitem,totla;
    private ProgressBar progressBar;
    private Elec_payment_adapter adapter;
    List<Electronique_payment> electronique_paymentList;
    private ImageView back;
    private void initialize() {
        /*********************declaration**************************/

        RV=findViewById(R.id.RV);
        noitem=findViewById(R.id.no_items);
        progressBar=findViewById(R.id.progress);
        back=findViewById(R.id.back_btn);
        totla=findViewById(R.id.total);

        /*************************actions*********************************/

        back.setOnClickListener(this);
        electronique_paymentList=new ArrayList<>();
        adapter=new Elec_payment_adapter(this, electronique_paymentList);
        RV.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        RV.setAdapter(adapter);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){


            case R.id.back_btn:
                finish();
                break;
        }
    }
}
