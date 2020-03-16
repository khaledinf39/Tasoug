package com.kh_sof_dev.tasoug.Views.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kh_sof_dev.tasoug.Controule.Routes.Products;
import com.kh_sof_dev.tasoug.Model.Classes.Product;
import com.kh_sof_dev.tasoug.Model.Classes.ProductOForder;
import com.kh_sof_dev.tasoug.Model.Classes.Unity;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Adapters.Unities_adapter;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.HORIZONTAL;

public class Product_details extends AppCompatActivity implements View.OnClickListener {

    public static Product product=null;
private Button active_btn,desactive_btn;
private FloatingActionButton edite_btn;
TextView quality,quality_sold,status,price,description,name,date;
RecyclerView unity_RV;
List<Unity> unityList;
Unities_adapter unities_adapter;
ImageView close_btn;
    private SliderLayout mDemoSlider;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        initialize();
        Loading_Data();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();
        Loading_Data();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void Loading_Data() {
        if (product!=null){
            unityList.clear();
            unityList.addAll(product.getUnity());
            unities_adapter.notifyDataSetChanged();
            /************************/
            quality.setText(product.getQuantity()+"");
            quality_sold.setText(product.getQuantity_sold()+"");
            description.setText(product.getDiscription());
            name.setText(product.getName());
            price.setText(product.getUnity().get(0).getPrice().toString()+ getString(R.string.rs));
            date.setText(getdate(product.getCreate_at()));

            /*******************
             * status of product
             */
           switch (product.getStatus())
            {
                case 1:
                    status.setText("بإنتظار الموافقة");
                    active_btn.setVisibility(View.VISIBLE);
                    desactive_btn.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    status.setText("مفعل");
                    active_btn.setVisibility(View.GONE);
                    desactive_btn.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    status.setText("موقف");
                    active_btn.setVisibility(View.VISIBLE);
                    desactive_btn.setVisibility(View.GONE);
                    break;

            }

/*************
 * Images of product
 */
            mDemoSlider.removeAllSliders();

            for(String name : product.getImages()){
                TextSliderView textSliderView = new TextSliderView(this);
                // initialize a SliderLayout
                textSliderView
                        .description(product.getName())
                        .image(name)
                        .setScaleType(BaseSliderView.ScaleType.Fit);

                Log.d("image_url",name);


                //add your extra information
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle()
                        .putString("extra",name);
                mDemoSlider.addSlider(textSliderView);
            }
            // Toast.makeText(getApplicationContext(),real_estate.getImagesURL().size()+"",Toast.LENGTH_SHORT).show();
            mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
            mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            mDemoSlider.setCustomAnimation(new DescriptionAnimation());
            mDemoSlider.setDuration(4000);
            mDemoSlider.setCustomIndicator((PagerIndicator) findViewById(R.id.app_indicator));
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getdate(String date_){
        Log.e("date_", date_.substring(0, date_.indexOf("T")));
        return date_.substring(0, date_.indexOf("T"));
    }
    @SuppressLint("WrongConstant")
    private void initialize() {
        active_btn=findViewById(R.id.active_btn);
        desactive_btn=findViewById(R.id.desactive_btn);
        edite_btn=findViewById(R.id.fab_edite);
        quality=findViewById(R.id.quantity);
        quality_sold=findViewById(R.id.quantity_sale);
        status=findViewById(R.id.status);
        price=findViewById(R.id.price);
        description=findViewById(R.id.description);
        name=findViewById(R.id.name);
        date=findViewById(R.id.date);
        unity_RV=findViewById(R.id.RV);
        close_btn=findViewById(R.id.back_btn);
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);
        /*****************************Actions***********************************/
        active_btn.setOnClickListener(this);
        desactive_btn.setOnClickListener(this);
        edite_btn.setOnClickListener(this);
        close_btn.setOnClickListener(this);

        /**********
         * unity list
         */
        unityList=new ArrayList<>();
        unities_adapter=new Unities_adapter(this, unityList, new Unities_adapter.Selected_item() {
            @Override
            public void Onselcted(Unity unity) {
               price.setText(unity.getPrice().toString()+getString(R.string.rs));
            }
        });

        unity_RV.setLayoutManager(new LinearLayoutManager(this,HORIZONTAL,false));
        unity_RV.setAdapter(unities_adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.active_btn:
                change_status(2);
                break;
            case R.id.desactive_btn:
                change_status(3);
                break;
            case R.id.fab_edite:
                Edite_product.product=product;
                Intent i=new Intent(Product_details.this,Edite_product.class);
                startActivity(i);
                break;
            case R.id.back_btn:
                Product_details.this.finish();
                break;
        }
    }

    private void change_status(int i) {
        Products products=new Products();
        product.setStatus(i);
        products.Put_statusOFproduct(this, product, new Products.Get_product() {
            @Override
            public void onstart() {

            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSuccess(Product pro) {

                Loading_Data();
            }

            @Override
            public void onField(String msg) {

            }
        });
    }
}
