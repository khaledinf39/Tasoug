package com.kh_sof_dev.tasoug.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kh_sof_dev.tasoug.Controule.Routes.Boxes;
import com.kh_sof_dev.tasoug.Model.Classes.Electronique_payment;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Adapters.Box_adapter;
import com.kh_sof_dev.tasoug.Views.Adapters.Expenses_bond_adapter;

import java.util.ArrayList;
import java.util.List;

public class Box extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box);
        initialize();
        Loading();
    }
Double total_elc_=0.0;
    private void Loading() {
        Boxes boxes=new Boxes();
        boxes.Get_All_box(this, new Boxes.Get_Box() {
            @Override
            public void onstart() {

            }

            @Override
            public void onSuccess(com.kh_sof_dev.tasoug.Model.Classes.Box box) {
                progressBar.setVisibility(View.GONE);
boxList.clear();
boxList.addAll(box.getBoxList());
adapter.notifyDataSetChanged();
if (boxList.size()==0){
    noitem.setVisibility(View.VISIBLE);
}
            }

            @Override
            public void onField(String msg) {

            }
        });

        /////for total elct payment
        final com.kh_sof_dev.tasoug.Controule.Routes.Electronique_bayment electronique_bayment=new com.kh_sof_dev.tasoug.Controule.Routes.Electronique_bayment();
        electronique_bayment.Get_All_expenses(this,
                new com.kh_sof_dev.tasoug.Controule.Routes.Electronique_bayment.Get_Electronique_bayment() {
                    @Override
                    public void onstart() {

                    }

                    @Override
                    public void onSuccess(Electronique_payment Electronique_bayment) {

                        total_elc.setText(Electronique_bayment.getToutal().toString());
                        total_elc_=Electronique_bayment.getToutal();
                        total.setText(Electronique_bayment.getToutal().toString());

                        com.kh_sof_dev.tasoug.Views.Activities.Electronique_bayment.elc_payment=Electronique_bayment;
                    }

                    @Override
                    public void onField(String msg) {

                    }
                });

    }

    private TextView goto_elc,total_elc,total;
    private EditText scoure;
    private Button terment;
    private RecyclerView RV;
    private TextView noitem;
    private ProgressBar progressBar;
    private Box_adapter adapter;
    private ImageView backbtn;
    List<com.kh_sof_dev.tasoug.Model.Classes.Box> boxList;
    private void initialize() {
        /*********************declaration**************************/
        terment=findViewById(R.id.terment);
        goto_elc=findViewById(R.id.go_payment);
        total=findViewById(R.id.total);
        total_elc=findViewById(R.id.total_elc);
        backbtn=findViewById(R.id.back_btn);

        RV=findViewById(R.id.RV);
        noitem=findViewById(R.id.no_items);
        progressBar=findViewById(R.id.progress);
        scoure=findViewById(R.id.scour);

        scoure.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {


            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    Double tot1=Double.parseDouble(s.toString());
//                    Double tot2=Double.parseDouble(total_elc.toString());
                    Double tot3=tot1+total_elc_;
                    total.setText(tot3.toString());

                }catch (Exception e){
                    total.setText(total_elc_.toString());
                    e.printStackTrace();
                }
            }
        });
        /*************************actions*********************************/
        terment.setOnClickListener(this);
        goto_elc.setOnClickListener(this);
        backbtn.setOnClickListener(this);
        boxList=new ArrayList<>();
        adapter=new Box_adapter(this, boxList);
        RV.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        RV.setAdapter(adapter);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.terment:
               add_TO_BOX();
                break;
            case R.id.go_payment:
                Intent intent=new Intent(Box.this,Electronique_bayment.class);
                startActivity(intent);
                break;
            case R.id.back_btn:
               finish();
                break;


        }
    }

    private void add_TO_BOX() {
        Boxes boxes=new Boxes();
        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage("رفع الى قاعدة البيانات...");
        dialog.show();
        boxes.Post_box(this,Double.parseDouble(total.getText().toString()) , new Boxes.Get_Box() {
            @Override
            public void onstart() {

            }

            @Override
            public void onSuccess(com.kh_sof_dev.tasoug.Model.Classes.Box box) {
                Loading();
                dialog.dismiss();
            }

            @Override
            public void onField(String msg) {

            }
        });
    }
}
