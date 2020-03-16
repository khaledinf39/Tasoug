package com.kh_sof_dev.tasoug.Views.Adapters;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.kh_sof_dev.tasoug.Controule.Routes.Users;
import com.kh_sof_dev.tasoug.Model.Classes.Supplier;
import com.kh_sof_dev.tasoug.Model.Classes.User;
import com.kh_sof_dev.tasoug.R;
import com.kh_sof_dev.tasoug.Views.Activities.Client_details;
import com.kh_sof_dev.tasoug.Views.Activities.Fornisieur_details;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by User on 26/02/2020.
 */

public class Suppliers_adapter extends RecyclerView.Adapter<Suppliers_adapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private List<Supplier> mItems = new ArrayList<>();
    private Context mContext;
    private View mview;
    interface Selected_item{
        void Onselcted(Supplier supplier);
    }
    Selected_item listenner;
    private Users users;
    public Suppliers_adapter(Context context, List<Supplier> names, Selected_item listenner) {
        mItems = names;
        mContext = context;
        this.listenner=listenner;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //parent = theme type

        View view;

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_forniseur, parent, false);

        mview=view;
        return new ViewHolder(view); // Inflater means reading a layout XML
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.name.setText(mItems.get(position).getName());
        holder.comerc_name.setText(mItems.get(position).getCoumerce_name());
        holder.scoure.setText(mItems.get(position).getScoure()+" ر.س ");
        holder.phone.setText(mItems.get(position).getPhone());
        holder.status.setChecked(mItems.get(position).getStatus());

       mview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Fornisieur_details.supplier=mItems.get(position);
                Intent i=new Intent(mContext, Fornisieur_details.class);
                mContext.startActivity(i);

            }
        });
        holder.call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Callpermissien(position);
            }
        });
       holder.status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               mItems.get(position).setStatus(isChecked);
              users.Put_statusOFsupplier(mContext, mItems.get(position), new Users.Get_users() {
                  @Override
                  public void onstart() {

                  }

                  @Override
                  public void onSuccess(User user) {

                      notifyDataSetChanged();
                  }

                  @Override
                  public void onField(String msg) {

                  }
              });
           }
       });
    }
    ///Calling
    private String[] callPermissions = {Manifest.permission.CALL_PHONE};

    @SuppressLint("MissingPermission")
    private void Callpermissien(int potion) {

        if (EasyPermissions.hasPermissions(mContext, callPermissions)) {
            String posted_by = mItems.get(potion).getPhone();

            String uri = "tel:" + posted_by.trim();
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse(uri));
            mContext.startActivity(intent);


        } else {
            EasyPermissions.requestPermissions(this, "Access for Call phone",
                    1000, callPermissions);
        }


    }
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


      TextView name,phone,comerc_name,scoure;
      Switch status;
        ImageView call_btn;
      public ViewHolder(View itemView) {
            super(itemView);
          name = itemView.findViewById(R.id.name);
          phone = itemView.findViewById(R.id.phone);
          status = itemView.findViewById(R.id.status);
          comerc_name = itemView.findViewById(R.id.comerce_name);
          scoure = itemView.findViewById(R.id.scour);
          call_btn = itemView.findViewById(R.id.call_btn);

        }
    }
}