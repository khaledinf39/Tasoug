package com.kh_sof_dev.tasoug.Views.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.kh_sof_dev.tasoug.Model.Classes.Expense;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Adapters.Expenses_adapter;
import com.kh_sof_dev.tasoug.Views.Adapters.Expenses_bond_adapter;

import java.util.ArrayList;
import java.util.List;

public class Expenses extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        initialize();
        Loading();
    }

    private void Loading() {
        com.kh_sof_dev.tasoug.Controule.Routes.Expenses expenses=new com.kh_sof_dev.tasoug.Controule.Routes.Expenses();
        expenses.Get_All_expenses(this, new com.kh_sof_dev.tasoug.Controule.Routes.Expenses.Get_Expenses() {
            @Override
            public void onstart() {

            }

            @Override
            public void onSuccess(Expense expense) {
                expenseList.clear();
                expenseList.addAll(expense.getExpenseList());
                progressBar.setVisibility(View.GONE);
                if (expenseList.size()==0){
                    noitem.setVisibility(View.VISIBLE);
                }
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
    private Expenses_adapter adapter;
    List<Expense> expenseList;
    private ImageView back;
    private Button save;
    private EditText title,price;
    private Spinner desc;
    private List<String> bonds;
    private void initialize() {
        /*********************declaration**************************/
        save=findViewById(R.id.save_btn);
        RV=findViewById(R.id.RV);
        noitem=findViewById(R.id.no_items);
        progressBar=findViewById(R.id.progress);
        back=findViewById(R.id.back_btn);
        desc=findViewById(R.id.desc);
        title=findViewById(R.id.title);
        price=findViewById(R.id.price);

        /*************************actions*********************************/

        save.setOnClickListener(this);
        back.setOnClickListener(this);
        expenseList=new ArrayList<>();
        adapter=new Expenses_adapter(this, expenseList);
        RV.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        RV.setAdapter(adapter);

       bonds=new ArrayList<>();
       bonds=Expense.fech_expenses(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bonds);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        desc.setAdapter(aa);


    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_btn:
              Add_expenses();
                break;

            case R.id.back_btn:
                finish();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void Add_expenses() {
        com.kh_sof_dev.tasoug.Controule.Routes.Expenses expenses =new com.kh_sof_dev.tasoug.Controule.Routes.Expenses();
       List<EditText> editTextList=new ArrayList<>();

        editTextList.add(price);
        if (!Verify(editTextList)){
            return;
        }
        Expense expense=new Expense(Double.parseDouble(price.getText().toString()),desc.getSelectedItem().toString());
        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage("رفع الى قاعدة البيانات...");
        dialog.show();
        expenses.Post_Expenses(this, expense, new com.kh_sof_dev.tasoug.Controule.Routes.Expenses.Get_Expenses() {
            @Override
            public void onstart() {

            }

            @Override
            public void onSuccess(Expense expense) {
                dialog.dismiss();
                Loading();
            }

            @Override
            public void onField(String msg) {

            }
        });

    }
    private boolean Verify(List<EditText> editTextList) {
        for (EditText e:editTextList
        ) {
            if (e.getText().toString().isEmpty()){
                e.setError("");
                return false;
            }
        }
        return true;
    }
}
