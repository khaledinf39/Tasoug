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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kh_sof_dev.tasoug.Controule.Routes.Users;
import com.kh_sof_dev.tasoug.Model.Classes.Categorie;
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

public class Users_adapter extends RecyclerView.Adapter<Users_adapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private List<User> mItems = new ArrayList<>();
    private Context mContext;
    private View mview;
    public interface Selected_item{
        void Onselcted(User user);
    }
    Selected_item listenner;
    private Users users;
    public Users_adapter(Context context, List<User> names, Selected_item listenner) {
        mItems = names;
        mContext = context;
        this.listenner=listenner;
        users=new Users();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //parent = theme type

        View view;

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_clients, parent, false);

        mview=view;
        return new ViewHolder(view); // Inflater means reading a layout XML
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.name.setText(mItems.get(position).getName());
        holder.phone.setText(mItems.get(position).getPhone());
        holder.status.setChecked(mItems.get(position).getStatus());

       mview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Client_details.user=mItems.get(position);

                Intent i=new Intent(mContext, Client_details.class);
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
              users.Put_statusOFuser(mContext, mItems.get(position), new Users.Get_users() {
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


      TextView name,phone;
      Switch status;
      ImageView call_btn;
      public ViewHolder(View itemView) {
            super(itemView);
          name = itemView.findViewById(R.id.name);
          phone = itemView.findViewById(R.id.phone);
          status = itemView.findViewById(R.id.status);
          call_btn = itemView.findViewById(R.id.call_btn);


        }
    }
}