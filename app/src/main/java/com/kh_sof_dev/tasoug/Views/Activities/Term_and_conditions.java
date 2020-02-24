package com.kh_sof_dev.tasoug.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kh_sof_dev.tasoug.R;

public class Term_and_conditions extends AppCompatActivity implements View.OnClickListener {

    public static int type;
private FloatingActionButton add_band;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_and_conditions);
        initialize();
    }

    private void initialize() {
        /*********************declaration**************************/
        add_band=findViewById(R.id.add_band);

        /*************************actions*********************************/
        add_band.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_band:
                popup_addBand();
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
                //TODO add to DB

                dialog.dismiss();
            }
        });
        dialog.show();


    }
}
