package com.visitorapp.bloominfotech.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.visitorapp.bloominfotech.R;

import butterknife.ButterKnife;

/**
 * Created by hp on 10/19/2016.
 */
public class FragmentVistorAcknowledge extends Fragment {

    View view;


    public static FragmentVistorAcknowledge newInstance() {
        FragmentVistorAcknowledge fragmentVistorAcknowledge = new FragmentVistorAcknowledge();
        return fragmentVistorAcknowledge;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_acknowledge_visitor, container, false);
 /*init butterknife*/
        ButterKnife.bind(this, view);






        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}

