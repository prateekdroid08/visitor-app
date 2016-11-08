package com.visitorapp.bloominfotech.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.views.activity.HomeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hp on 10/19/2016.
 */
public class FragmentVistorAcknowledge extends Fragment {

    View view;


    public static FragmentVistorAcknowledge newInstance() {
        FragmentVistorAcknowledge fragmentVistorAcknowledge = new FragmentVistorAcknowledge();
        return fragmentVistorAcknowledge;
    }

    @Bind(R.id.text_visitor)
    public TextView mVisitortxt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_acknowledge_visitor, container, false);
 /*init butterknife*/
        ButterKnife.bind(this, view);

        ((HomeActivity) getActivity()).toolbar.setVisibility(View.GONE);
        ((HomeActivity) getActivity()).mToolbarTitle.setText("");


        return view;
    }

    @OnClick(R.id.agree_acknowledge_visitor)
    public void methodVisitorAcknowledge(View view) {

        ((HomeActivity) getActivity()).visitorPresenter.navigateTo(FragmentFinalReceipt.newInstance());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}

