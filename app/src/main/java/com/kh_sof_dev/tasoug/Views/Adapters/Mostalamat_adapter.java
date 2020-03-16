package com.kh_sof_dev.tasoug.Views.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.kh_sof_dev.tasoug.Model.Classes.Electronique_payment;
import com.kh_sof_dev.tasoug.Model.Classes.ProductOFmost;
import com.kh_sof_dev.tasoug.Model.Classes.ProductOForder;
import com.kh_sof_dev.tasoug.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 26/02/2020.
 */

public class Mostalamat_adapter extends RecyclerView.Adapter<Mostalamat_adapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private List<ProductOFmost> mItems = new ArrayList<>();
    private Context mContext;
    private View mview;
    interface Selected_item{
        void Onselcted(Electronique_payment item);
    }
    Selected_item listenner;
    public Mostalamat_adapter(Context context, List<ProductOFmost> names) {
        mItems = names;
        mContext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //parent = theme type

        View view;

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mostalamat, parent, false);

        mview=view;
        return new ViewHolder(view); // Inflater means reading a layout XML
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.quantity.setText(mItems.get(position).getQuantity()+"");
        holder.nb.setText(mItems.get(position).get_id()+"");
        holder.produt.setText(mItems.get(position).getName());
        holder.total.setText(mItems.get(position).getPrice()*mItems.get(position).getQuantity()+" ر.س ");

        if (mItems.get(position).getStatus()==1){
            mview.setBackgroundColor(mContext.getColor(R.color.bk_red_white));
        }
        if (mItems.get(position).getStatus()==2){
            mview.setBackgroundColor(mContext.getColor(R.color.bk_green_white));
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


        TextView produt,price,quantity,total,nb;
        public ViewHolder(View itemView) {
            super(itemView);
            produt = itemView.findViewById(R.id.name);
            quantity = itemView.findViewById(R.id.quantity);
            total = itemView.findViewById(R.id.price);
            nb = itemView.findViewById(R.id.nb);


        }
    }
}