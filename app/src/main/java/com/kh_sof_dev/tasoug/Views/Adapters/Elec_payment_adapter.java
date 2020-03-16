package com.kh_sof_dev.tasoug.Views.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.kh_sof_dev.tasoug.Model.Classes.Box;
import com.kh_sof_dev.tasoug.Model.Classes.Categorie;
import com.kh_sof_dev.tasoug.Model.Classes.Electronique_payment;
import com.kh_sof_dev.tasoug.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 26/02/2020.
 */

public class Elec_payment_adapter extends RecyclerView.Adapter<Elec_payment_adapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private List<Electronique_payment> mItems = new ArrayList<>();
    private Context mContext;
    private View mview;
    interface Selected_item{
        void Onselcted(Electronique_payment item);
    }
    Selected_item listenner;
    public Elec_payment_adapter(Context context, List<Electronique_payment> names) {
        mItems = names;
        mContext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //parent = theme type

        View view;

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_elctronique_bay, parent, false);

        mview=view;
        return new ViewHolder(view); // Inflater means reading a layout XML
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.date.setText(getdate(mItems.get(position).getDate_time()));
        holder.price.setText(mItems.get(position).getPrice()+" ر.س ");
        holder.orderId.setText("الطلب رقم "+mItems.get(position).getOrderID());
        mview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

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


        TextView date,price,orderId;
        public ViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            price = itemView.findViewById(R.id.price);
            orderId = itemView.findViewById(R.id.order_id);


        }
    }
}