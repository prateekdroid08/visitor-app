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


    public AdminDetailAdapter(Context context, ArrayList<UserList> lstEvt) {
        this.lstEvt = lstEvt;
        this.mContext = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        @Nullable
        @Bind(R.id.visitor_id)
        TextView visitorId;

        @Nullable
        @Bind(R.id.date_of_visit)
        TextView dateOfVisit;

        @Nullable
        @Bind(R.id.name)
        TextView mName;

        @Nullable
        @Bind(R.id.company_name)
        TextView companyName;

        @Nullable
        @Bind(R.id.car_registration_number)
        TextView carRegistrationNo;

        @Nullable
        @Bind(R.id.purpose_of_visit)
        TextView purposeOfVisit;

        @Nullable
        @Bind(R.id.meeting_with)
        TextView meetingWith;

        @Nullable
        @Bind(R.id.time_in)
        TextView timeIn;

        @Nullable
        @Bind(R.id.time_out)
        TextView timeOut;

        @Nullable
        @Bind(R.id.time_spent)
        TextView timeSpent;

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

        if (lstEvt.get(position).getCreatedOn() != null)
            contactViewHolder.dateOfVisit.setText(Html.fromHtml("<b>" + mContext.getResources().getString(R.string.date_of_visit) + "</b>"
                    + " " + lstEvt.get(position).getCreatedOn()));

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

        if (lstEvt.get(position).getCompanyDetails().getCompanyName() != null)
            contactViewHolder.companyName.setText(Html.fromHtml("<b>" + mContext.getResources().getString(R.string.company_name) + "</b>"
                    + " " + lstEvt.get(position).getCompanyDetails().getCompanyName()));

        if (lstEvt.get(position).getUserDetails().getCarNumber() != null)
            contactViewHolder.carRegistrationNo.setText(Html.fromHtml("<b>" + mContext.getResources().
                    getString(R.string.car_registration_no) + "</b>"
                    + " " + lstEvt.get(position).getUserDetails().getCarNumber()));

        if (lstEvt.get(position).getPurposeDetails().getPurposeName() != null)
            contactViewHolder.purposeOfVisit.setText(Html.fromHtml("<b>"+ mContext.getResources().getString(R.string.purpose_of_visit)+"</b>"
                    + " " + lstEvt.get(position).getPurposeDetails().getPurposeName()));

        if (lstEvt.get(position).getMeetingDetails().getName() != null)
            contactViewHolder.meetingWith.setText(Html.fromHtml("<b>"+ mContext.getResources().getString(R.string.meeting_with)+"</b>"
                    + " " + lstEvt.get(position).getMeetingDetails().getName()));

        if (lstEvt.get(position).getTimeIn() != null)
            contactViewHolder.timeIn.setText(Html.fromHtml("<b>"+ mContext.getResources().getString(R.string.time_in_hh_mm_ss) + "</b>"
                    + " " + lstEvt.get(position).getTimeIn()));

        if (lstEvt.get(position).getTimeOut() != null)
            contactViewHolder.timeOut.setText(Html.fromHtml("<b>"+ mContext.getResources().getString(R.string.time_out_hh_mm_ss)+"</b>"
                    + " " + lstEvt.get(position).getTimeOut()));

        if (lstEvt.get(position).getTimeSpent() != null)
            contactViewHolder.timeSpent.setText(Html.fromHtml("<b>"+ mContext.getResources().getString(R.string.time_spent_hh_mm_ss)+"</b>"
                    + " " + lstEvt.get(position).getTimeSpent()));


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
