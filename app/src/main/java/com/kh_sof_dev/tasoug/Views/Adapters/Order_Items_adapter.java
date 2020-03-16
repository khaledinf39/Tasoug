package com.kh_sof_dev.tasoug.Views.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.kh_sof_dev.tasoug.Model.Classes.Product;
import com.kh_sof_dev.tasoug.Model.Classes.ProductOForder;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Activities.Product_details;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 26/02/2020.
 */

public class Order_Items_adapter extends RecyclerView.Adapter<Order_Items_adapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private List<ProductOForder> mItems = new ArrayList<>();
    private Context mContext;
    private View mview;
    public interface Selected_item{
        void Onselcted(ProductOForder product);
    }
    Selected_item listenner;
    public Order_Items_adapter(Context context, List<ProductOForder> names, Selected_item listenner) {
        mItems = names;
        mContext = context;
        this.listenner=listenner;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //parent = theme type

        View view;

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_order_item, parent, false);

        mview=view;
        return new ViewHolder(view); // Inflater means reading a layout XML
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.name.setText(mItems.get(position).getName());
        holder.price.setText(mItems.get(position).getPrice()+" ر.س ");
        holder.quantity.setText(mItems.get(position).getQuantity()+" ");


       try {
           Picasso.with(mContext)
                   .load(mItems.get(position).getImage())
                   .placeholder(mContext.getDrawable(R.drawable.prod))
                   .into(holder.img);
       }catch (Exception e){
           e.printStackTrace();
       }

       mview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
ImageView img;
      TextView name,price,quantity;
      public ViewHolder(View itemView) {
            super(itemView);
          name = itemView.findViewById(R.id.name);
          price = itemView.findViewById(R.id.price);
          quantity = itemView.findViewById(R.id.nb);
          img = itemView.findViewById(R.id.img);



        }
    }
}