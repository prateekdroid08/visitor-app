package com.visitorapp.bloominfotech.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.adapters.CompanyAdapter;
import com.visitorapp.bloominfotech.adapters.CompanyFilterAdapter;
import com.visitorapp.bloominfotech.adapters.PurposeFilterAdapter;
import com.visitorapp.bloominfotech.interfaces.OnCompanyItemClick;
import com.visitorapp.bloominfotech.interfaces.OnPurposeItemClick;
import com.visitorapp.bloominfotech.models.PostResponse;
import com.visitorapp.bloominfotech.models.ResponseVistorForm;
import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;
import com.visitorapp.bloominfotech.models.eventbus.MessageEvent;
import com.visitorapp.bloominfotech.models.form_response.ResponseVisitorForm;
import com.visitorapp.bloominfotech.models.purpose.PurposeAPIResponse;
import com.visitorapp.bloominfotech.presenter.companies.CompanyListView;
import com.visitorapp.bloominfotech.presenter.companies.CompanylistPresenter;
import com.visitorapp.bloominfotech.presenter.companies.CompanylistPresenterImpl;
import com.visitorapp.bloominfotech.presenter.purpose_of_visit.PurposePresenter;
import com.visitorapp.bloominfotech.presenter.purpose_of_visit.PurposePresenterImpl;
import com.visitorapp.bloominfotech.presenter.purpose_of_visit.PurposeView;
import com.visitorapp.bloominfotech.presenter.visitor_form.VisitorPresenter;
import com.visitorapp.bloominfotech.presenter.visitor_form.VisitorPresenterImpl;
import com.visitorapp.bloominfotech.presenter.visitor_form.VisitorView;
import com.visitorapp.bloominfotech.utils.AppUtils;
import com.visitorapp.bloominfotech.utils.ProgressDialog;
import com.visitorapp.bloominfotech.utils.ViewUtils;
import com.visitorapp.bloominfotech.views.activity.HomeActivity;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by hp on 10/19/2016.
 */
public class FragmentVisitorForm extends Fragment implements VisitorView,
        PurposeView, CompanyListView, OnCompanyItemClick, OnPurposeItemClick {

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
    AutoCompleteTextView mVisitor_company_name;

    @Bind(R.id.phone_number)
    EditText phone_number;

    @Bind(R.id.car_registration)
    EditText car_registration;

    @Bind(R.id.purpose_of_visit)
    AutoCompleteTextView purposeOfVisit;

    @Bind(R.id.meeting_with)
    EditText meeting_with;

    @Bind(R.id.visitor_add_members)
    EditText visitor_add_members;

    @Bind(R.id.submit)
    TextView submit;

    boolean addmembercontainer = false;
    String meetingId;

    View view;
    ProgressDialog progressDialog;

    VisitorPresenter visitorPresenter;

    CompanylistPresenter companylistPresenter;
    PurposePresenter purposePresenter;
    CompanyFilterAdapter companyFilterAdapter;
    PurposeFilterAdapter purposeFilterAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_visitor_form, container, false);
        /*init butterknife*/
        ButterKnife.bind(this, view);

        ((HomeActivity) getActivity()).toolbar.setVisibility(View.GONE);
        ((HomeActivity) getActivity()).mToolbarTitle.setText("Visitor Form");

        visitorPresenter = new VisitorPresenterImpl(getActivity(), this);

        companylistPresenter = new CompanylistPresenterImpl(getActivity(), this);
        companylistPresenter.getCompanyList("", 0, true);

        purposePresenter = new PurposePresenterImpl(getActivity(), this);
        purposePresenter.getAllPurpose();

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
            String[] companyNameId = companyInfo[1].split("#");
            if (mVisitor_company_name != null) {
                mVisitor_company_name.setText(companyNameId[0]);
            }
            EventBus.getDefault().removeStickyEvent(event);
        } else if (event.message.contains(getResources().getString(R.string.purpose_selected))) {
            EventBus.getDefault().removeStickyEvent(event);
            String[] purposeInfo = event.message.split(",");
            String[] purposeNameId = purposeInfo[1].split("#");
            if (purposeOfVisit != null) {
                purposeOfVisit.setText(purposeNameId[0]);
            }
            EventBus.getDefault().removeStickyEvent(event);
        } else if (event.message.contains(getResources().getString(R.string.meeting_selected))) {
            EventBus.getDefault().removeStickyEvent(event);
            String[] meetingInfo = event.message.split(",");
            String[] meetingNameId = meetingInfo[1].split("#");
            if (meeting_with != null) {
                meeting_with.setText(meetingNameId[0]);
            }

            meetingId = meetingNameId[1];
            EventBus.getDefault().removeStickyEvent(event);
        }

    }

    @Override
    public void onSuccess(ResponseVistorForm postResponse) {
        if (postResponse != null) {
            //    ViewUtils.showMessage(getActivity(), postResponse.getMessage());
            if (postResponse.getStatus().equalsIgnoreCase("accepted")) {
                Bundle bundle = new Bundle();
                bundle.putString("message", postResponse.getMessage());
                ((HomeActivity) getActivity()).visitorPresenter.navigateWithBundle(FragmentFinalReceipt.newInstance(), bundle);
            } else {
                ViewUtils.showMessage(getActivity(), postResponse.getStatus());
            }

        }

    }

    @Override
    public void onSuccess(ResponseCompanies responseCompanies) {
        if (responseCompanies != null) {
            companyFilterAdapter = new CompanyFilterAdapter(getActivity(), R.layout.fragment_visitor_form,
                    responseCompanies.getCompanyLists(), this);
            mVisitor_company_name.setThreshold(1);
            mVisitor_company_name.setAdapter(companyFilterAdapter);
        }
    }

    @Override
    public void onSuccess(PurposeAPIResponse purposeAPIResponse) {
        if (purposeAPIResponse != null) {
            purposeFilterAdapter = new PurposeFilterAdapter(getActivity(), R.layout.fragment_visitor_form,
                    purposeAPIResponse.getPurposeList(), this);
            purposeOfVisit.setThreshold(1);
            purposeOfVisit.setAdapter(purposeFilterAdapter);
        }
    }

    @Override
    public void onError(String message) {
        ViewUtils.showMessage(getActivity(), message);
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();
    }

    @Override
    public void hideProgressView() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @OnClick(R.id.submit)
    void onSubmitClick() {

        if (visitor_email.getText().toString().length() <= 0) {
            ViewUtils.showMessage(getActivity(), "Please enter your email id.");
            return;
        }

        if (!AppUtils.isEmailValid(visitor_email.getText().toString())) {
            ViewUtils.showMessage(getActivity(), "Please enter valid email id.");
            return;
        }

        if (visitor_first_name.getText().toString().length() <= 0) {
            ViewUtils.showMessage(getActivity(), "Please enter your first name.");
            return;
        }

        if (mVisitor_company_name.getText().toString().length() <= 0) {
            ViewUtils.showMessage(getActivity(), "Please select company name.");
            return;
        }

        if (phone_number.getText().toString().length() <= 0) {
            ViewUtils.showMessage(getActivity(), "Please enter phone number.");
            return;
        }

        if (!AppUtils.isPhoneNumber(phone_number.getText().toString())) {
            ViewUtils.showMessage(getActivity(), "Please enter valid phone number.");
            return;
        }

        if (purposeOfVisit.getText().toString().length() <= 0) {
            ViewUtils.showMessage(getActivity(), "Please select purpose of visit.");
            return;
        }

        if (meeting_with.getText().toString().length() <= 0) {
            ViewUtils.showMessage(getActivity(), "To whom you want to meet.");
            return;
        }


        visitorPresenter.submitVisitorForm(
                "True",
                mVisitor_company_name.getText().toString(),
                meetingId,
                purposeOfVisit.getText().toString(),
                visitor_email.getText().toString().trim(),
                visitor_first_name.getText().toString(),
                visitor_Last_name.getText().toString(),
                car_registration.getText().toString(),
                phone_number.getText().toString(),
                visitor_add_members.getText().toString());

    }

    @Override
    public void onCompanyItemSelected(ResponseCompanies responseCompanies, int position) {

    }

    @Override
    public void OnPurposeItemClick(PurposeAPIResponse purposeAPIResponse, int position) {

    }
}


