package com.kh_sof_dev.tasoug.Views.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.kh_sof_dev.tasoug.Model.Classes.Order;
import com.kh_sof_dev.tasoug.Model.Classes.Term;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Activities.Order_details;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 26/02/2020.
 */

public class Orders_adapter extends RecyclerView.Adapter<Orders_adapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private List<Order> mItems = new ArrayList<>();
    private Context mContext;
    private View mview;
    interface Selected_item{
        void Onselcted(Order order);
    }
    Selected_item listenner;
    public Orders_adapter(Context context, List<Order> names) {
        mItems = names;
        mContext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //parent = theme type

        View view;

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_order, parent, false);

        mview=view;
        return new ViewHolder(view); // Inflater means reading a layout XML
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.date.setText(getdate(mItems.get(position).getCreate_at()));
        holder.order_nb.setText(mItems.get(position).get_id());
        holder.price.setText(mItems.get(position).getSub_total()+" ر.س ");
        mview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Order_details.order=mItems.get(position);
                Intent i=new Intent(mContext, Order_details.class);
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getdate(String date_){
        Log.e("date_", date_.substring(0, date_.indexOf("T")));
        return date_.substring(0, date_.indexOf("T"));
    }
    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView date,price,order_nb;
        public ViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            order_nb = itemView.findViewById(R.id.order_nb);
            price = itemView.findViewById(R.id.price);


        }
    }
}