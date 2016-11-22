package com.visitorapp.bloominfotech.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.interfaces.OnAdminDetailItemClick;
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

public class AdminDetailAdapter extends RecyclerView.Adapter<AdminDetailAdapter.MyViewHolder> {

    ArrayList<UserList> lstEvt;
    Context mContext;
    OnAdminDetailItemClick onAdminDetailItemClick;


    public AdminDetailAdapter(Context context, ArrayList<UserList> lstEvt, OnAdminDetailItemClick onAdminDetailItemClick) {
        this.lstEvt = lstEvt;
        this.mContext = context;
        this.onAdminDetailItemClick = onAdminDetailItemClick;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        @Nullable
        @Bind(R.id.visitor_id)
        TextView visitorId;



        @Nullable
        @Bind(R.id.name)
        TextView mName;



        @Nullable
        @Bind(R.id.admin_list_frame)
        LinearLayout mMain_frame;


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public AdminDetailAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_admin_detail_item, viewGroup, false);

        return new AdminDetailAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdminDetailAdapter.MyViewHolder contactViewHolder, final int position) {


        if (lstEvt.get(position).getUniqueKey() != null)
            contactViewHolder.visitorId.setText(Html.fromHtml("<b>" + mContext.getResources().getString(R.string.visitor_id) + "</b>"
                    + " " + lstEvt.get(position).getUniqueKey()));



        if (lstEvt.get(position).getUserDetails().getFirstName() != null) {
            if (lstEvt.get(position).getUserDetails().getLastName() != null)
                contactViewHolder.mName.setText(Html.fromHtml("<b>" + mContext.getResources().getString(R.string.name) + "</b>"
                        + " " +
                        lstEvt.get(position).getUserDetails().getFirstName() + " " +
                        lstEvt.get(position).getUserDetails().getLastName()));
            else
                contactViewHolder.mName.setText(Html.fromHtml("<b>" + mContext.getResources().getString(R.string.name)
                        + "</b>" + " " +
                        lstEvt.get(position).getUserDetails().getFirstName()));
        }




        contactViewHolder.mMain_frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAdminDetailItemClick.onAdminDetailItemClicked(lstEvt, position);
            }
        });


    }


    @Override
    public int getItemCount() {
        return lstEvt.size();
    }


}
