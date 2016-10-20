package com.visitorapp.bloominfotech.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.views.activity.HomeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hp on 10/19/2016.
 */
public class FragmentVisitorForm extends Fragment {

    View view;


    public static FragmentVisitorForm newInstance() {
        FragmentVisitorForm fragmentVisitorForm = new FragmentVisitorForm();
        return fragmentVisitorForm;
    }


    @Bind(R.id.ll_add_member_container)
    public LinearLayout mLLContainer;


    @Bind(R.id.tv_add_member)
    public TextView mAddMember;

    boolean addmembercontainer = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_visitor_form, container, false);
 /*init butterknife*/
        ButterKnife.bind(this, view);

        ((HomeActivity) getActivity()).toolbar.setVisibility(View.GONE);
        ((HomeActivity) getActivity()).mToolbarTitle.setText("Visitor Form");


        return view;
    }

    @OnClick(R.id.tv_add_member)
    public void methodAddmember(View view) {
        if (!addmembercontainer) {
            mLLContainer.setVisibility(View.VISIBLE);
            addmembercontainer = true;
        } else {
            mLLContainer.setVisibility(View.GONE);
            addmembercontainer = false;

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}


