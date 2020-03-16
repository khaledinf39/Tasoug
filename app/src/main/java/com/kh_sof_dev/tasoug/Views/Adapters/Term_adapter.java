package com.kh_sof_dev.tasoug.Views.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kh_sof_dev.tasoug.Model.Classes.Electronique_payment;
import com.kh_sof_dev.tasoug.Model.Classes.Term;
import com.kh_sof_dev.tasoug.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 26/02/2020.
 */

public class Term_adapter extends RecyclerView.Adapter<Term_adapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private List<Term> mItems = new ArrayList<>();
    private Context mContext;
    private View mview;
    interface Selected_item{
        void Onselcted(Term item);
    }
    Selected_item listenner;
    public Term_adapter(Context context, List<Term> names) {
        mItems = names;
        mContext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //parent = theme type

        View view;

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_term, parent, false);

        mview=view;
        return new ViewHolder(view); // Inflater means reading a layout XML
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.title.setText(mItems.get(position).getTitle());
        holder.body.setText(mItems.get(position).getBody());
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


        TextView title,body;
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);


        }
    }
}