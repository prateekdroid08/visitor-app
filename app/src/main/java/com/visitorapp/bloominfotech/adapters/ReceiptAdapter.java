package com.visitorapp.bloominfotech.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.interfaces.OnCompanyItemClick;
import com.visitorapp.bloominfotech.interfaces.OnPrintThePictureCommand;
import com.visitorapp.bloominfotech.interfaces.OnReceiptItemClickListener;
import com.visitorapp.bloominfotech.models.FinalReceiptModel;
import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hp on 11/8/2016.
 */

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.MyViewHolder> {

    ArrayList<FinalReceiptModel> rowItems;
    private Context mContext;
    OnReceiptItemClickListener onReceiptItemClickListener;
    OnPrintThePictureCommand onPrintThePictureCommand;

    private int i = 0;


    public ReceiptAdapter(Context context, ArrayList<FinalReceiptModel> rowItems,
                          OnReceiptItemClickListener onReceiptItemClickListener,
                          OnPrintThePictureCommand onPrintThePictureCommand) {
        this.rowItems = rowItems;
        this.mContext = context;
        this.onPrintThePictureCommand = onPrintThePictureCommand;
        this.onReceiptItemClickListener = onReceiptItemClickListener;

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        @Bind(R.id.name)
        TextView mName;

        @Bind(R.id.company)
        TextView mCompany;

        @Bind(R.id.purpose)
        TextView mPurpose;

        @Bind(R.id.id)
        TextView mID;

        @Bind(R.id.date)
        TextView mDate;

        @Bind(R.id.time)
        TextView mTime;


        @Bind(R.id.viewpdf)
        TextView mView;

        @Bind(R.id.barcodeimage)
        ImageView mImage;


        @Bind(R.id.ll_conatiner)
        LinearLayout mllContainer;


        @Bind(R.id.ll_subContainer)
        LinearLayout mllSubContainer;


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_final_recipt, viewGroup, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder contactViewHolder, final int position) {


        if (rowItems.get(position).getName() != null)
            contactViewHolder.mName.setText(rowItems.get(position).getName());


        if (rowItems.get(position).getCompany() != null)
            contactViewHolder.mCompany.setText(rowItems.get(position).getCompany());

        if (rowItems.get(position).getPurpose() != null)
            contactViewHolder.mPurpose.setText(rowItems.get(position).getPurpose());

        if (rowItems.get(position).getId() != null)
            contactViewHolder.mID.setText(rowItems.get(position).getId());

        if (rowItems.get(position).getDate() != null)
            contactViewHolder.mDate.setText(rowItems.get(position).getDate());

        if (rowItems.get(position).getTime() != null)
            contactViewHolder.mTime.setText(rowItems.get(position).getTime());

        if (rowItems.get(position).getImagelink() != null) {
            Glide.with(mContext)
                    .load(rowItems.get(position).getImagelink())
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            i++;
                            if (i == (rowItems.size() - 1))
                                onPrintThePictureCommand.startPrinting();
                            return false;
                        }
                    })
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .skipMemoryCache(false).dontAnimate()
                    .into(contactViewHolder.mImage);
        }


        contactViewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReceiptItemClickListener.onReceiptViewItemSelected(rowItems, position, contactViewHolder.mllContainer, contactViewHolder.mView, contactViewHolder.mllSubContainer);
            }
        });


    }


    @Override
    public int getItemCount() {
        return rowItems.size();
    }


}

