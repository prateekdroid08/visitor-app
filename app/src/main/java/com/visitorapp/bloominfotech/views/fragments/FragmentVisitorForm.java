package com.visitorapp.bloominfotech.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.models.eventbus.MessageEvent;
import com.visitorapp.bloominfotech.views.activity.HomeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

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
    LinearLayout mLLContainer;


    @Bind(R.id.tv_add_member)
    TextView mAddMember;


    @Bind(R.id.visitor_email)
    EditText visitor_email;

    @Bind(R.id.visitor_first_name)
    EditText visitor_first_name;

    @Bind(R.id.visitor_Last_name)
    EditText visitor_Last_name;

    @Bind(R.id.visitor_company_name)
    EditText mVisitor_company_name;

    @Bind(R.id.phone_number)
    EditText phone_number;

    @Bind(R.id.car_registration)
    EditText car_registration;

    @Bind(R.id.purpose_of_visit)
    EditText purposeOfVisit;

    @Bind(R.id.meeting_with)
    EditText meeting_with;

    @Bind(R.id.visitor_add_members)
    EditText visitor_add_members;

    @Bind(R.id.submit)
    TextView submit;

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
            visitor_add_members.setText("");

        }

    }

    @OnClick(R.id.visitor_company_name)
    void methodAddcompany(View view) {
        ((HomeActivity) getActivity()).visitorPresenter.navigateTo(FragmentCompanyList.newInstance());
    }

    @OnClick(R.id.purpose_of_visit)
    void methodAddPurpose() {
        ((HomeActivity) getActivity()).visitorPresenter.navigateTo(FragmentPurposeList.newInstance());
    }

    @OnClick(R.id.meeting_with)
    void methodAddMeeting() {
        ((HomeActivity) getActivity()).visitorPresenter.navigateTo(FragmentMeetingList.newInstance());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }

        ButterKnife.unbind(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().registerSticky(this);
        }
    }

    public void onEventMainThread(MessageEvent event) {

        if (event.message.contains(getResources().getString(R.string.company_selected))) {
            EventBus.getDefault().removeStickyEvent(event);
            String[] companyInfo = event.message.split(",");
            String[] companyNameId = companyInfo[1].split("-");
            if (mVisitor_company_name != null)
                mVisitor_company_name.setText(companyNameId[0]);
        } else if (event.message.contains(getResources().getString(R.string.purpose_selected))) {
            EventBus.getDefault().removeStickyEvent(event);
            String[] purposeInfo = event.message.split(",");
            String[] purposeNameId = purposeInfo[1].split("#");
            if (purposeOfVisit != null)
                purposeOfVisit.setText(purposeNameId[0]);
        }

    }

}


