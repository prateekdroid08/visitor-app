package com.visitorapp.bloominfotech.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.models.admin_detail.FilterData;
import com.visitorapp.bloominfotech.models.eventbus.MessageEvent;
import com.visitorapp.bloominfotech.utils.datetimepicker.DateTime;
import com.visitorapp.bloominfotech.utils.datetimepicker.DateTimePicker;
import com.visitorapp.bloominfotech.utils.datetimepicker.SimpleDateTimePicker;
import com.visitorapp.bloominfotech.views.activity.HomeActivity;

import java.text.DateFormatSymbols;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by hp on 10/21/2016.
 */
public class FragmentFilter extends Fragment implements DateTimePicker.OnDateTimeSetListener {

    View view;


    public static FragmentFilter newInstance() {
        FragmentFilter fragmentFilter = new FragmentFilter();
        return fragmentFilter;
    }

    @Bind(R.id.dateFrom)
    EditText mDateFrom;

    @Bind(R.id.dateTo)
    EditText mDateTo;

    @Bind(R.id.company_filter)
    EditText companyFilter;

    @Bind(R.id.meeting_filter)
    EditText meetingFilter;

    String datepickerSelector = "";

    FilterData filterData = new FilterData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_filter, container, false);
 /*init butterknife*/
        ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }

        ButterKnife.unbind(this);
    }

    @OnClick(R.id.company_filter)
    void methodAddcompanyFilter(View view) {
        ((HomeActivity) getActivity()).visitorPresenter.navigateTo(FragmentCompanyList.newInstance());
    }

    @OnClick(R.id.meeting_filter)
    void methodAddMeetingFilter() {
        ((HomeActivity) getActivity()).visitorPresenter.navigateTo(FragmentMeetingList.newInstance());
    }

    @OnClick(R.id.dateFrom)
    public void methodDateFrom(View view) {
        datepickerSelector = "dateFrom";
        SimpleDateTimePicker simpleDateTimePicker = SimpleDateTimePicker.make(
                getString(R.string.set_date_time),
                new Date(),
                this,
                getFragmentManager()
        );

        simpleDateTimePicker.show();
    }

    @OnClick(R.id.dateTo)
    public void methodDateTo(View view) {
        datepickerSelector = "dateTo";
        SimpleDateTimePicker simpleDateTimePicker = SimpleDateTimePicker.make(
                getString(R.string.set_date_time),
                new Date(),
                this,
                getFragmentManager()
        );

        simpleDateTimePicker.show();
    }


    @Override
    public void DateTimeSet(Date date) {
        DateTime mDateTime = new DateTime(date);
        if (datepickerSelector.equalsIgnoreCase("dateFrom")) {
            mDateFrom.setText(mDateTime.getDayOfMonth() + " " +
                    new DateFormatSymbols().getMonths()[mDateTime.getMonthOfYear()] + ", " + mDateTime.getYear());

            filterData.setSrchDate(mDateTime.getDayOfMonth() + " " +
                    new DateFormatSymbols().getMonths()[mDateTime.getMonthOfYear()] + ", " + mDateTime.getYear());
        } else if (datepickerSelector.equalsIgnoreCase("dateTo")) {
            mDateTo.setText(mDateTime.getDayOfMonth() + " " +
                    new DateFormatSymbols().getMonths()[mDateTime.getMonthOfYear()] + ", " + mDateTime.getYear());

            filterData.setSrchDateTo(mDateTime.getDayOfMonth() + " " +
                    new DateFormatSymbols().getMonths()[mDateTime.getMonthOfYear()] + ", " + mDateTime.getYear());
        }
    }


    @OnClick(R.id.FilterSave)
    public void methodFilterSave(View view) {

        EventBus.getDefault().postSticky(filterData);

        ((HomeActivity) getActivity()).visitorPresenter.oneStepBack();

    }

    @Override
    public void onStart() {
        super.onStart();

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().registerSticky(this);
        }
    }

    String meetingId;

    public void onEventMainThread(MessageEvent event) {

        if (event.message.contains(getResources().getString(R.string.company_selected))) {
            EventBus.getDefault().removeStickyEvent(event);
            String[] companyInfo = event.message.split(",");
            String[] companyNameId = companyInfo[1].split("#");

            EventBus.getDefault().removeStickyEvent(event);

            companyFilter.setText(companyNameId[0]);

            filterData.setCompanyID(companyNameId[1]);

        } else if (event.message.contains(getResources().getString(R.string.meeting_selected))) {
            EventBus.getDefault().removeStickyEvent(event);
            String[] meetingInfo = event.message.split(",");
            String[] meetingNameId = meetingInfo[1].split("#");

            meetingId = meetingNameId[1];

            filterData.setMeetingID(meetingId);

            meetingFilter.setText(meetingNameId[0]);
            EventBus.getDefault().removeStickyEvent(event);
        }

    }
}

