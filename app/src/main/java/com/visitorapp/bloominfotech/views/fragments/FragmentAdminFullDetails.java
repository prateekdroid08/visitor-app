package com.visitorapp.bloominfotech.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.visitorapp.bloominfotech.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hp on 11/19/2016.
 */

public class FragmentAdminFullDetails extends Fragment {

    View view;

    @Nullable
    @Bind(R.id.visitor_id)
    TextView visitorId;

    @Nullable
    @Bind(R.id.date_of_visit)
    TextView dateOfVisit;

    @Nullable
    @Bind(R.id.name)
    TextView mName;

    @Nullable
    @Bind(R.id.company_name)
    TextView companyName;

    @Nullable
    @Bind(R.id.car_registration_number)
    TextView carRegistrationNo;

    @Nullable
    @Bind(R.id.purpose_of_visit)
    TextView purposeOfVisit;

    @Nullable
    @Bind(R.id.meeting_with)
    TextView meetingWith;

    @Nullable
    @Bind(R.id.time_in)
    TextView timeIn;

    @Nullable
    @Bind(R.id.time_out)
    TextView timeOut;

    @Nullable
    @Bind(R.id.time_spent)
    TextView timeSpent;


    public static FragmentAdminFullDetails newInstance() {
        FragmentAdminFullDetails fragmentAdminFullDetails = new FragmentAdminFullDetails();
        return fragmentAdminFullDetails;
    }

    String visitor_id = "";
    String CreatedOn = "";

    String ComanyName = "", Carnumber = "";
    String name = "";
    String PurposeName = "";
    String MeetingWith = "";
    String TimeIN = "";
    String TimeOut = "";
    String TimeSpent = "";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_show_admin_detail, container, false);
 /*init butterknife*/
        ButterKnife.bind(this, view);

 /*get value from bundle*/
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            visitor_id = bundle.getString("visitor_id", "");
            CreatedOn = bundle.getString("CreatedOn", "");
            ComanyName = bundle.getString("ComanyName", "");
            Carnumber = bundle.getString("Carnumber", "");
            name = bundle.getString("name", "");
            PurposeName = bundle.getString("PurposeName", "");
            MeetingWith = bundle.getString("MeetingWith", "");
            TimeIN = bundle.getString("TimeIN", "");
            TimeOut = bundle.getString("TimeOut", "");
            TimeSpent = bundle.getString("TimeSpent", "");


            visitorId.setText(Html.fromHtml("<b>" + getActivity().getResources().getString(R.string.visitor_id) + "</b>"
                    + " " + visitor_id));


            dateOfVisit.setText(Html.fromHtml("<b>" + getActivity().getResources().getString(R.string.date_of_visit) + "</b>"
                    + " " + CreatedOn));


            mName.setText(Html.fromHtml("<b>" + getActivity().getResources().getString(R.string.name) + "</b>"
                    + " " + name));


            companyName.setText(Html.fromHtml("<b>" + getActivity().getResources().getString(R.string.company_name) + "</b>"
                    + " " + ComanyName));


            carRegistrationNo.setText(Html.fromHtml("<b>" + getActivity().getResources().
                    getString(R.string.car_registration_no) + "</b>"
                    + " " + Carnumber));


            purposeOfVisit.setText(Html.fromHtml("<b>" + getActivity().getResources().getString(R.string.purpose_of_visit) + "</b>"
                    + " " + PurposeName));


            meetingWith.setText(Html.fromHtml("<b>" + getActivity().getResources().getString(R.string.meeting_with) + "</b>"
                    + " " + MeetingWith));


            timeIn.setText(Html.fromHtml("<b>" + getActivity().getResources().getString(R.string.time_in_hh_mm_ss) + "</b>"
                    + " " + TimeIN));


            timeOut.setText(Html.fromHtml("<b>" + getActivity().getResources().getString(R.string.time_out_hh_mm_ss) + "</b>"
                    + " " + TimeOut));


            timeSpent.setText(Html.fromHtml("<b>" + getActivity().getResources().getString(R.string.time_spent_hh_mm_ss) + "</b>"
                    + " " + TimeSpent));


        }


        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}


