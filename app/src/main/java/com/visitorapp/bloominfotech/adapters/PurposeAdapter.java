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
import com.visitorapp.bloominfotech.interfaces.OnPurposeItemClick;
import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;
import com.visitorapp.bloominfotech.models.purpose.PurposeAPIResponse;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hp on 10/25/2016.
 */
public class PurposeAdapter extends RecyclerView.Adapter<PurposeAdapter.MyViewHolder> {

    PurposeAPIResponse purposeAPIResponse;
    private Context mContext;
    OnPurposeItemClick onPurposeItemClick;


    public PurposeAdapter(Context context, PurposeAPIResponse purposeAPIResponse, OnPurposeItemClick onPurposeItemClick) {
        this.purposeAPIResponse = purposeAPIResponse;
        this.mContext = context;

        this.onPurposeItemClick = onPurposeItemClick;

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        @Bind(R.id.companyTV)
        TextView mName;


        @Bind(R.id.mainframe)
        LinearLayout mMain_frame;


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_companies_item, viewGroup, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder contactViewHolder, final int position) {


        if (purposeAPIResponse.getPurposeList().get(position).getPurposeName() != null)
            contactViewHolder.mName.setText(purposeAPIResponse.getPurposeList().get(position).getPurposeName());


        contactViewHolder.mMain_frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPurposeItemClick.OnPurposeItemClick(purposeAPIResponse, position);
            }
        });


    }


    @Override
    public int getItemCount() {
        return purposeAPIResponse.getPurposeList().size();
    }


}
