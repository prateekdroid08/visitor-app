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
public class FragmentContractorAcknowledge extends Fragment {

    View view;


    public static FragmentContractorAcknowledge newInstance() {
        FragmentContractorAcknowledge fragmentContractorAcknowledge = new FragmentContractorAcknowledge();
        return fragmentContractorAcknowledge;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragments_acknowledge_contractor, container, false);
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

