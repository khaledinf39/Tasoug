package com.kh_sof_dev.tasoug.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kh_sof_dev.tasoug.Model.Classes.Supplier;
import com.kh_sof_dev.tasoug.R;

public class Fornisieur_details extends AppCompatActivity {

    public static Supplier supplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fornisieur_details);
    }
}
