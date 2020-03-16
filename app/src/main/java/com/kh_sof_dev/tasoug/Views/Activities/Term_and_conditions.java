package com.kh_sof_dev.tasoug.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kh_sof_dev.tasoug.Controule.Routes.Terms;
import com.kh_sof_dev.tasoug.Model.Classes.Term;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Adapters.Term_adapter;

import java.util.ArrayList;
import java.util.List;

public class Term_and_conditions extends AppCompatActivity implements View.OnClickListener {

    public static int type;
private FloatingActionButton add_band;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_and_conditions);
        initialize();
        Loading_data();
    }

    private void Loading_data() {
      if (type==1){
          loading_supp();

      }else {
          loading_client();
      }

    }

    private void loading_client() {
        Terms terms=new Terms();
        terms.Get_All_Term_client(this, new Terms.Get_Term() {
            @Override
            public void onstart() {

            }

            @Override
            public void onSuccess(Term term) {
                progressBar.setVisibility(View.GONE);
                if (term.getTermList().size()==0){
                    noitem.setVisibility(View.VISIBLE);
                }
                termList.clear();
                termList.addAll(term.getTermList());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onField(String msg) {

            }
        });
    }

    private void loading_supp() {
        Terms terms=new Terms();
        terms.Get_All_Term_supp(this, new Terms.Get_Term() {
            @Override
            public void onstart() {

            }

            @Override
            public void onSuccess(Term term) {
                progressBar.setVisibility(View.GONE);
                if (term.getTermList().size()==0){
                    noitem.setVisibility(View.VISIBLE);
                }
                termList.clear();
                termList.addAll(term.getTermList());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onField(String msg) {

            }
        });
    }

    private RecyclerView RV;
    private TextView noitem;
    private ProgressBar progressBar;
    private Term_adapter adapter;
    ImageView back;
    List<Term> termList;
    private void initialize() {
        /*********************declaration**************************/
        add_band=findViewById(R.id.add_band);
        RV=findViewById(R.id.RV);
        noitem=findViewById(R.id.no_items);
        progressBar=findViewById(R.id.progress);
        back=findViewById(R.id.back_btn);

        /*************************actions*********************************/
        add_band.setOnClickListener(this);
        back.setOnClickListener(this);
        termList=new ArrayList<>();
        adapter=new Term_adapter(this,termList);
        RV.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
RV.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_band:
                popup_addBand();
                break;
            case R.id.back_btn:
               finish();
                break;


        }
    }

    private void popup_addBand() {
        final BottomSheetDialog dialog=new BottomSheetDialog(this);
        dialog.setContentView(R.layout.popup_add_term);
        final EditText title=dialog.findViewById(R.id.title);
        final EditText body=dialog.findViewById(R.id.body);
        TextView save_btn=dialog.findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titel_=title.getText().toString();
                String body_=body.getText().toString();
                if (body_.isEmpty() ){
                    body.setError(body.getHint());
                    return;
                }
                if (titel_.isEmpty() ){
                    title.setError(title.getHint());
                    return;
                }
                Term term=new Term(titel_,body_,"");
                if (type==1){
                    add_term_supplier(term);
                }else {
                    add_term_client(term);

                }

                dialog.dismiss();
            }
        });
        dialog.show();


    }

    private void add_term_supplier(Term term) {
        Terms terms=new Terms();
        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage("رفع الى قاعدة البيانات...");
        dialog.show();
        terms.Post_term_supp(this, term, new Terms.Get_Term() {
            @Override
            public void onstart() {

            }

            @Override
            public void onSuccess(Term term) {
                dialog.dismiss();
            }

            @Override
            public void onField(String msg) {

            }
        });

    }

    private void add_term_client(Term term) {
        Terms terms=new Terms();
        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage("رفع الى قاعدة البيانات...");
        dialog.show();
        terms.Post_term_client(this, term, new Terms.Get_Term() {
            @Override
            public void onstart() {

            }

            @Override
            public void onSuccess(Term term) {
                dialog.dismiss();
            }

            @Override
            public void onField(String msg) {

            }
        });
    }
}
