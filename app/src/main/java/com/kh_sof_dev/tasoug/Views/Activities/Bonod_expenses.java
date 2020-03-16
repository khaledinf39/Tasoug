package com.kh_sof_dev.tasoug.Views.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kh_sof_dev.tasoug.Controule.Routes.Expenses;
import com.kh_sof_dev.tasoug.Model.Classes.Expense;
import com.kh_sof_dev.tasoug.Model.Classes.Term;
import com.kh_sof_dev.tasoug.Model.LocalDatabase.DBManager;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Adapters.Expenses_bond_adapter;
import com.kh_sof_dev.tasoug.Views.Adapters.Term_adapter;

import java.util.ArrayList;
import java.util.List;

public class Bonod_expenses extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonod_expenses);
        initialize();
        Load_data();
    }
private FloatingActionButton add_band;
    private RecyclerView RV;
    private TextView noitem;
    private ProgressBar progressBar;
    private Expenses_bond_adapter adapter;
    List<String> expenseList;
    private ImageView back;
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
        expenseList=new ArrayList<>();
        adapter=new Expenses_bond_adapter(this, expenseList, new Expenses_bond_adapter.Selected_item() {
            @Override
            public void Onselcted(String item) {

            }
        });
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
        dialog.setContentView(R.layout.popup_add_band_expenses);
        final EditText bande=dialog.findViewById(R.id.bande);
        final EditText price=dialog.findViewById(R.id.price);
        price.setVisibility(View.GONE);
        final ImageView close=dialog.findViewById(R.id.back_btn);
        TextView save_btn=dialog.findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String titel_=bande.getText().toString();

                if (titel_.isEmpty() ){
                    bande.setError(bande.getHint());
                    return;
                }
                String price_=price.getText().toString();
//
//                if (price_.isEmpty() || Double.parseDouble(price_)==0.0 ){
//                    price.setError(price.getHint());
//                    return;
//                }
//                Expense expense=new Expense(Double.parseDouble(price_),titel_);
              DBManager manager=new DBManager(getApplicationContext());
              manager.insert_bonde(0.0,titel_);
              Load_data();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void Load_data(){
        expenseList.clear();
        expenseList.addAll(Expense.fech_expenses(this));
        if (expenseList.size()==0){
           noitem.setVisibility(View.VISIBLE);
        }
        progressBar.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
    }
}
