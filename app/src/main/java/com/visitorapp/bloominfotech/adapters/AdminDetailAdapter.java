package com.visitorapp.bloominfotech.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.interfaces.OnCompanyItemClick;
import com.visitorapp.bloominfotech.models.admin_detail.ResponseAdminDetail;
import com.visitorapp.bloominfotech.models.admin_detail.UserDetails;
import com.visitorapp.bloominfotech.models.admin_detail.UserList;
import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hp on 11/16/2016.
 */

public class AdminDetailAdapter extends RecyclerView.Adapter<CompanyAdapter.MyViewHolder> {

    ArrayList<UserList> lstEvt;
    private Context mContext;


    public AdminDetailAdapter(Context context,  ArrayList<UserList> lstEvt) {
        this.lstEvt = lstEvt;
        this.mContext = context;


    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        @Bind(R.id.name)
        TextView mName;


        @Bind(R.id.email)
        TextView email;

        @Bind(R.id.admin_list_frame)
        LinearLayout mMain_frame;


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public CompanyAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_admin_detail_item, viewGroup, false);

        return new CompanyAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final CompanyAdapter.MyViewHolder contactViewHolder, final int position) {


        if (lstEvt.get(position).getUserDetails().getFirstName() != null)
            contactViewHolder.mName.setText("Name : " + lstEvt.get(position).getUserDetails().getFirstName() + " " + lstEvt.get(position).getUserDetails().getLastName());

        if (lstEvt.get(position).getCompanyDetails().getCompanyName() != null)
            contactViewHolder.mName.setText("Company Name : " + lstEvt.get(position).getCompanyDetails().getCompanyName());


        contactViewHolder.mMain_frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


    @Override
    public int getItemCount() {
        return lstEvt.size();
    }


}
