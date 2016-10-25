package com.visitorapp.bloominfotech.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.interfaces.OnCompanyItemClick;
import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hp on 10/25/2016.
 */
public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.MyViewHolder> {

    ResponseCompanies lstEvt;
    private Context mContext;
    OnCompanyItemClick onCompanyItemClick;


    public CompanyAdapter(Context context, ResponseCompanies lstEvt, OnCompanyItemClick onCompanyItemClick) {
        this.lstEvt = lstEvt;
        this.mContext = context;

        this.onCompanyItemClick = onCompanyItemClick;

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


        if (lstEvt.getCompanyLists().get(position).getCompanyName() != null)
            contactViewHolder.mName.setText(lstEvt.getCompanyLists().get(position).getCompanyName());


        contactViewHolder.mMain_frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCompanyItemClick.onCompanyItemSelected(lstEvt, position);
            }
        });


    }


    @Override
    public int getItemCount() {
        return lstEvt.getCompanyLists().size();
    }


}
