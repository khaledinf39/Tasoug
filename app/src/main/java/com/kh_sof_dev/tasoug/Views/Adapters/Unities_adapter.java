package com.kh_sof_dev.tasoug.Views.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kh_sof_dev.tasoug.Model.Classes.Categorie;
import com.kh_sof_dev.tasoug.Model.Classes.Unity;
import com.kh_sof_dev.tasoug.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 26/02/2020.
 */

public class Unities_adapter extends RecyclerView.Adapter<Unities_adapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private List<Unity> mItems = new ArrayList<>();
    private Context mContext;
    private View mview;
    public interface Selected_item{
        void Onselcted(Unity unity);
    }
    Selected_item listenner;
    private static int item_select;
    public Unities_adapter(Context context, List<Unity> names, Selected_item listenner) {
        mItems = names;
        mContext = context;
        this.listenner=listenner;
        item_select=0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //parent = theme type

        View view;

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_categories, parent, false);

        mview=view;
        return new ViewHolder(view); // Inflater means reading a layout XML
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

holder.name.setText(mItems.get(position).getUnity());
        if (position!=item_select){
            holder.name.setTextColor(mContext.getColor(R.color.text_grey));
            holder.card.setCardBackgroundColor(mContext.getColor(R.color.bk_white));
        }else {
            holder.card.setCardBackgroundColor(mContext.getColor(R.color.bk_green));
            holder.name.setTextColor(mContext.getColor(R.color.text_white));
        }
       mview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
             item_select=position;
               listenner.Onselcted(mItems.get(position));
               notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

      CardView card;
      TextView name;
      public ViewHolder(View itemView) {
            super(itemView);
          name = itemView.findViewById(R.id.name);
          card = itemView.findViewById(R.id.card);


        }
    }
}