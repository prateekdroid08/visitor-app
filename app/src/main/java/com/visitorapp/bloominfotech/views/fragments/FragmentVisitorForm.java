package com.visitorapp.bloominfotech.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.constants.Constants;
import com.visitorapp.bloominfotech.models.eventbus.MessageEvent;
import com.visitorapp.bloominfotech.views.activity.HomeActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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


    @Bind(R.id.visitor_email)
    public EditText visitor_email;

    @Bind(R.id.visitor_first_name)
    public EditText visitor_first_name;

    @Bind(R.id.visitor_Last_name)
    public EditText visitor_Last_name;

    @Bind(R.id.visitor_company_name)
    public EditText mVisitor_company_name;

    @Bind(R.id.phone_number)
    public EditText phone_number;


    @Bind(R.id.car_registration)
    public EditText car_registration;


    @Bind(R.id.purpose_of_visit)
    public EditText purpose_of_visit;


    @Bind(R.id.meeting_with)
    public EditText meeting_with;

    @Bind(R.id.visitor_add_members)
    public EditText visitor_add_members;

    @Bind(R.id.submit)
    public TextView submit;

    boolean addmembercontainer = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_visitor_form, container, false);
 /*init butterknife*/
        ButterKnife.bind(this, view);

        ((HomeActivity) getActivity()).toolbar.setVisibility(View.GONE);
        ((HomeActivity) getActivity()).mToolbarTitle.setText("Visitor Form");

        mVisitor_company_name.setText(Constants.DEFAULT_COMPANY_NAME);
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
            visitor_add_members.setText("");

        }

    }

    @OnClick(R.id.visitor_company_name)
    public void methodAddcompany(View view) {


        ((HomeActivity) getActivity()).visitorPresenter.navigateTo(FragmentCompanyList.newInstance());
    }

    @Override
    public void onResume() {
        super.onResume();
        mVisitor_company_name.setText(Constants.DEFAULT_COMPANY_NAME+"");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onStart() {
        super.onStart();


        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe
    public void onEventMainThread(MessageEvent event) {


        EventBus.getDefault().removeStickyEvent(event.message);
        if (event.message.equalsIgnoreCase("companyselected")) {
            mVisitor_company_name.setText(Constants.DEFAULT_COMPANY_NAME+"");
        }


    }


}


