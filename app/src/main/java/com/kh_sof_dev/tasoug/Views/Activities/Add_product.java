package com.kh_sof_dev.tasoug.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.kh_sof_dev.tasoug.R;

public class Add_product extends AppCompatActivity  implements View.OnClickListener {
private ImageView add_price,edite_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initialize();
    }

    private void initialize() {
        /*********************declaration**************************/
        add_price=findViewById(R.id.add_price);
        edite_price=findViewById(R.id.edite_price);
        /*************************actions*********************************/
        add_price.setOnClickListener(this);
        edite_price.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_price:
                popup_addPrice();
                break;

            case R.id.edite_price:
                popup_addPrice();
                break;
        }
    }

    private void popup_addPrice() {
        final BottomSheetDialog dialog=new BottomSheetDialog(this);
        dialog.setContentView(R.layout.popup_add_unite);
        final EditText unity=dialog.findViewById(R.id.unity);
        final EditText price=dialog.findViewById(R.id.price);
        TextView save_btn=dialog.findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String unity_=unity.getText().toString();
                String price_=price.getText().toString();
                if (unity_.isEmpty() ){
                    unity.setError(unity.getHint());
                    return;
                }
                if (price_.isEmpty()|| Double.parseDouble(price_)==0 ){
                    price.setError(price.getHint());
                    return;
                }
                //TODO add to DB

                dialog.dismiss();
            }
        });
        dialog.show();


    }
}
