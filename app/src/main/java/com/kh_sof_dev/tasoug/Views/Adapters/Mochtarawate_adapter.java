package com.kh_sof_dev.tasoug.Views.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kh_sof_dev.tasoug.Model.Classes.Electronique_payment;
import com.kh_sof_dev.tasoug.Model.Classes.ProductOForder;
import com.kh_sof_dev.tasoug.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 26/02/2020.
 */

public class Mochtarawate_adapter extends RecyclerView.Adapter<Mochtarawate_adapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private List<ProductOForder> mItems = new ArrayList<>();
    private Context mContext;
    private View mview;
    interface Selected_item{
        void Onselcted(Electronique_payment item);
    }
    Selected_item listenner;
    public Mochtarawate_adapter(Context context, List<ProductOForder> names) {
        mItems = names;
        mContext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //parent = theme type

        View view;

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mochtarawat, parent, false);

        mview=view;
        return new ViewHolder(view); // Inflater means reading a layout XML
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.quantity.setText(mItems.get(position).getQuantity()+"");
        holder.price.setText(mItems.get(position).getPrice()+" ر.س ");
        holder.produt.setText(mItems.get(position).getName());
        holder.total.setText(mItems.get(position).getPrice()*mItems.get(position).getQuantity()+" ر.س ");
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


        TextView produt,price,quantity,total;
        public ViewHolder(View itemView) {
            super(itemView);
            produt = itemView.findViewById(R.id.product);
            price = itemView.findViewById(R.id.price);
            quantity = itemView.findViewById(R.id.quantity);
            total = itemView.findViewById(R.id.total);


        }
    }
}