package com.kh_sof_dev.tasoug.Views.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kh_sof_dev.tasoug.Model.Classes.Categorie;
import com.kh_sof_dev.tasoug.Model.Classes.Product;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Activities.Product_details;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 26/02/2020.
 */

public class Products_adapter extends RecyclerView.Adapter<Products_adapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private List<Product> mItems = new ArrayList<>();
    private Context mContext;
    private View mview;
    public interface Selected_item{
        void Onselcted(Product product);
    }
    Selected_item listenner;
    public Products_adapter(Context context, List<Product> names, Selected_item listenner) {
        mItems = names;
        mContext = context;
        this.listenner=listenner;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //parent = theme type

        View view;

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_products, parent, false);

        mview=view;
        return new ViewHolder(view); // Inflater means reading a layout XML
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.name.setText(mItems.get(position).getName());
      if (mItems.get(position).getUnity().size()==0){
          holder.price.setText("00.0"+" ر.س ");
      }else {
          holder.price.setText(mItems.get(position).getUnity().get(0).getPrice()+" ر.س ");
      }
        holder.date.setText(getdate(mItems.get(position).getCreate_at()));
        holder.quantity.setText(mItems.get(position).getQuantity()+" ");
        holder.quantity_sale.setText(mItems.get(position).getQuantity_sold()+"");

       try {
           Log.d("image_url",mItems.get(position).getImages().get(0));

           Picasso.with(mContext)
                   .load(mItems.get(position).getImages().get(0))
                   .placeholder(mContext.getDrawable(R.drawable.prod))
                   .into(holder.img);
       }catch (Exception e){
           e.printStackTrace();
       }

       mview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Product_details.product=mItems.get(position);
                Intent i =new Intent(mContext, Product_details.class);
                mContext.startActivity(i);

            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getdate(String date_){
        Log.e("date_", date_.substring(0, date_.indexOf("T")));
        return date_.substring(0, date_.indexOf("T"));
    }
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
ImageView img;
      TextView name,price,date,quantity,quantity_sale;
      public ViewHolder(View itemView) {
            super(itemView);
          name = itemView.findViewById(R.id.name);
          price = itemView.findViewById(R.id.price);
          date = itemView.findViewById(R.id.date);
          quantity = itemView.findViewById(R.id.quantity);
          quantity_sale = itemView.findViewById(R.id.quantity_sale);
          img = itemView.findViewById(R.id.img);



        }
    }
}