package com.kh_sof_dev.tasoug.Views.Fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kh_sof_dev.tasoug.Controule.Routes.Categories;
import com.kh_sof_dev.tasoug.Model.Classes.Categorie;
import com.kh_sof_dev.tasoug.Model.Classes.Product;
import com.kh_sof_dev.tasoug.Model.Classes.Unity;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Activities.Add_product;
import com.kh_sof_dev.tasoug.Views.Activities.Term_and_conditions;
import com.kh_sof_dev.tasoug.Views.Adapters.Categories_adapter;
import com.kh_sof_dev.tasoug.Views.Adapters.Products_adapter;
import com.kh_sof_dev.tasoug.Views.Adapters.Unities_adapter;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.HORIZONTAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class Products extends Fragment implements View.OnClickListener {


    public Products() {
        // Required empty public constructor
    }

private FloatingActionButton fab_plase,fab_qr_code_sale,fab_qr_code_bay;
    private RecyclerView categories_RV,product_RV;
    private ProgressBar progressBar1,progressBar2;
    private TextView noitem1,noitem2;


    private Products_adapter products_adapter;
    private List<Product> productList;

    private Categories_adapter categories_adapter;
    private List<Categorie> categorieList;
    private String categorieID=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_products, container, false);
        initialize(view);
        fetch_data();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        fetch_data();
    }

    private void fetch_data() {
        Categories categories=new Categories();
        categories.Get_All_categoriesBystore(getContext(), new Categories.Get_categories() {
            @Override
            public void onstart() {

            }

            @Override
            public void onSuccess(Categorie categorie) {
                progressBar1.setVisibility(View.GONE);
categorieList.clear();
                categorieList.addAll(categorie.categorieList);
                categories_adapter.notifyDataSetChanged();

                if (categorieList.size()==0) {
                    noitem1.setVisibility(View.VISIBLE);
                }else {
                    noitem1.setVisibility(View.GONE);
                }
            }

            @Override
            public void onField(String msg) {

            }
        });

        com.kh_sof_dev.tasoug.Controule.Routes.Products products=new com.kh_sof_dev.tasoug.Controule.Routes.Products();
        products.Get_All_productBystore(getContext(), new com.kh_sof_dev.tasoug.Controule.Routes.Products.Get_product() {
            @Override
            public void onstart() {

            }

            @Override
            public void onSuccess(Product product) {
                progressBar2.setVisibility(View.GONE);

                productList.clear();
                productList.addAll(product.productList);
                products_adapter.notifyDataSetChanged();
                if (productList.size()==0) {
                    noitem2.setVisibility(View.VISIBLE);

            }else {
                noitem2.setVisibility(View.GONE);
            }
            }

            @Override
            public void onField(String msg) {

            }
        });


    }

    @SuppressLint("WrongConstant")
    private void initialize(View view) {
        fab_plase=view.findViewById(R.id.fab_plase);
        fab_qr_code_sale=view.findViewById(R.id.fab_qr_code_sale);
        fab_qr_code_bay=view.findViewById(R.id.fab_qr_code_bay);
        categories_RV=view.findViewById(R.id.categories_RV);
        product_RV=view.findViewById(R.id.products_RV);
        progressBar1=view.findViewById(R.id.progress1);
        progressBar2=view.findViewById(R.id.progress2);
        noitem1=view.findViewById(R.id.noItem1);
        noitem2=view.findViewById(R.id.noItem2);
///////////////////////////////////******Action*****////////////////////////////////////////////////////
        fab_plase.setOnClickListener(this);
        fab_qr_code_sale.setOnClickListener(this);
        fab_qr_code_bay.setOnClickListener(this);

        ///*******
        categorieList=new ArrayList<>();
        categories_adapter=new Categories_adapter(getContext(), categorieList, new Categories_adapter.Selected_item() {
            @Override
            public void Onselcted(Categorie categorie) {
                progressBar2.setVisibility(View.VISIBLE);
                categorieID=categorie.get_id();
                com.kh_sof_dev.tasoug.Controule.Routes.Products products=new com.kh_sof_dev.tasoug.Controule.Routes.Products();
                products.Get_All_productBystoreANDcategories(getContext(),categorieID,
                        new com.kh_sof_dev.tasoug.Controule.Routes.Products.Get_product() {
                    @Override
                    public void onstart() {

                    }

                    @Override
                    public void onSuccess(Product product) {
                        progressBar2.setVisibility(View.GONE);

                        productList.clear();
                        productList.addAll(product.productList);
                        products_adapter.notifyDataSetChanged();
                        if (productList.size()==0) {
                            noitem2.setVisibility(View.VISIBLE);
                        }else {
                            noitem2.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onField(String msg) {

                    }
                });

            }
        }
        );
        categories_RV.setLayoutManager(new LinearLayoutManager(getContext(),HORIZONTAL,false));
        categories_RV.setAdapter(categories_adapter);
        /******************
         *
         */
productList=new ArrayList<>();
products_adapter=new Products_adapter(getContext(), productList, new Products_adapter.Selected_item() {
    @Override
    public void Onselcted(Product product) {

    }
});
product_RV.setLayoutManager(new GridLayoutManager(getContext(),2));
product_RV.setAdapter(products_adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_plase:
                Intent fab_plase=new Intent(getActivity(), Add_product.class);
                startActivity(fab_plase);
                break;
//
//            case R.id.fab_qr_code_sale:
//                Intent fab_qr_code_sale=new Intent(getActivity(), mochtarawat.class);
//                startActivity(fab_qr_code_sale);
//                break;
//
//            case R.id.fab_qr_code_bay:
//                Intent fab_qr_code_bay=new Intent(getActivity(), mostalamat.class);
//                startActivity(fab_qr_code_bay);
//                break;
        }
    }
}
