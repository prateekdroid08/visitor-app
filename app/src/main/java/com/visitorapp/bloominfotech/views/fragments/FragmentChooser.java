package com.visitorapp.bloominfotech.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.views.activity.HomeActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hp on 10/19/2016.
 */
public class FragmentChooser extends Fragment {

    View view;


    public static FragmentChooser newInstance() {
        FragmentChooser fragmentChooser = new FragmentChooser();
        return fragmentChooser;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_choose_type, container, false);
 /*init butterknife*/
        ButterKnife.bind(this, view);

        ((HomeActivity) getActivity()).toolbar.setVisibility(View.GONE);
        ((HomeActivity) getActivity()).mToolbarTitle.setText("");


        return view;
    }


    @OnClick(R.id.contractor)
    public void methodContractor(View view) {

        ((HomeActivity) getActivity()).visitorPresenter.navigateTo(FragmentContractorAcknowledge.newInstance());
    }


    @OnClick(R.id.visitor)
    public void methodVistor(View view) {
        ((HomeActivity) getActivity()).visitorPresenter.navigateTo(FragmentVistorAcknowledge.newInstance());

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}

