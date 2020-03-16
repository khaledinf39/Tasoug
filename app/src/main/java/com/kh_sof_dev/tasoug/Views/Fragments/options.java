package com.kh_sof_dev.tasoug.Views.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kh_sof_dev.tasoug.Model.Classes.Expense;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Activities.Bonod_expenses;
import com.kh_sof_dev.tasoug.Views.Activities.Box;
import com.kh_sof_dev.tasoug.Views.Activities.Expenses;
import com.kh_sof_dev.tasoug.Views.Activities.Store_info;
import com.kh_sof_dev.tasoug.Views.Activities.Term_and_conditions;

/**
 * A simple {@link Fragment} subclass.
 */
public class options extends Fragment implements View.OnClickListener {


    public options() {
        // Required empty public constructor
    }

private ConstraintLayout store_inf_btn,termNconditions_forns,termNconditions_client,bonod_expenses,expenses;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_options, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        store_inf_btn=view.findViewById(R.id.store_inf_btn);
        termNconditions_forns=view.findViewById(R.id.termNconditions_forns);
        termNconditions_client=view.findViewById(R.id.termNconditions_client);
        bonod_expenses=view.findViewById(R.id.bonod_expenses);
      expenses=view.findViewById(R.id.expenses);

        store_inf_btn.setOnClickListener(this);
        termNconditions_forns.setOnClickListener(this);
        termNconditions_client.setOnClickListener(this);

        bonod_expenses.setOnClickListener(this);
        expenses.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.store_inf_btn:
                Intent storeINFO=new Intent(getActivity(), Store_info.class);
                startActivity(storeINFO);
                break;

                case R.id.termNconditions_forns:
                    Term_and_conditions.type=1;//supplier
                Intent Term=new Intent(getActivity(), Term_and_conditions.class);
                startActivity(Term);
                break;

            case R.id.termNconditions_client:
                Term_and_conditions.type=2;//client

                Intent TermC=new Intent(getActivity(), Term_and_conditions.class);
                startActivity(TermC);
                break;




            case R.id.bonod_expenses:
                Intent bonod_expenses=new Intent(getActivity(), Bonod_expenses.class);
                startActivity(bonod_expenses);
                break;

            case R.id.expenses:
                Intent i=new Intent(getActivity(), Expenses.class);
                startActivity(i);
                break;
        }
    }
}
