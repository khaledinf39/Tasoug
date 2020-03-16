package com.kh_sof_dev.tasoug.Views.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kh_sof_dev.tasoug.Model.Classes.Categorie;
import com.kh_sof_dev.tasoug.Model.LocalDatabase.DBManager;
import com.kh_sof_dev.tasoug.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 26/02/2020.
 */

public class Expenses_bond_adapter extends RecyclerView.Adapter<Expenses_bond_adapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private List<String> mItems = new ArrayList<>();
    private Context mContext;
    private View mview;
    public interface Selected_item{
        void Onselcted(String item);
    }
    Selected_item listenner;
    public Expenses_bond_adapter(Context context, List<String> names, Selected_item listenner) {
        mItems = names;
        mContext = context;
        this.listenner=listenner;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //parent = theme type

        View view;

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bonod, parent, false);

        mview=view;
        return new ViewHolder(view); // Inflater means reading a layout XML
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

holder.name.setText(mItems.get(position));

       holder.delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DBManager manager=new DBManager(mContext);
                manager.open();
                manager.deleteBond(mItems.get(position));
                manager.close();

               mItems.remove(mItems.get(position));
               notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

      ImageView delete;
      TextView name;
      public ViewHolder(View itemView) {
            super(itemView);
          name = itemView.findViewById(R.id.name);
          delete = itemView.findViewById(R.id.delete);


        }
    }
}