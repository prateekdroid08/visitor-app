package com.visitorapp.bloominfotech.views.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.models.admin_detail.FilterData;
import com.visitorapp.bloominfotech.models.eventbus.MessageEvent;
import com.visitorapp.bloominfotech.utils.DatePickerFragment;
import com.visitorapp.bloominfotech.utils.ViewUtils;
import com.visitorapp.bloominfotech.utils.datetimepicker.DateFormat;
import com.visitorapp.bloominfotech.utils.datetimepicker.DateTime;
import com.visitorapp.bloominfotech.utils.datetimepicker.DateTimePicker;
import com.visitorapp.bloominfotech.utils.datetimepicker.SimpleDateTimePicker;
import com.visitorapp.bloominfotech.views.activity.AdminActivity;
import com.visitorapp.bloominfotech.views.activity.HomeActivity;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by hp on 10/21/2016.
 */
public class FragmentFilter extends Fragment implements View.OnClickListener {

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

    public static String datepickerSelector = "";

    FilterData filterData = new FilterData();
    public static Date setmindate = null;

    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;

    Calendar newDateFrom;
    Calendar newDateTo;
    Date dateFrom = new Date(System.currentTimeMillis());


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_filter, container, false);
 /*init butterknife*/
        ButterKnife.bind(this, view);
        dateFormatter = new SimpleDateFormat("dd MMM , yy", Locale.US);


        setDateTimeField();
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
        ((AdminActivity) getActivity()).visitorPresenter.navigateTo(FragmentCompanyList.newInstance());
    }

    @OnClick(R.id.meeting_filter)
    void methodAddMeetingFilter() {
        ((AdminActivity) getActivity()).visitorPresenter.navigateTo(FragmentMeetingAdminList.newInstance());
    }


    private void setDateTimeField() {
        mDateFrom.setOnClickListener(this);
        mDateTo.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                newDateFrom = Calendar.getInstance();
                newDateFrom.set(year, monthOfYear, dayOfMonth);
                mDateFrom.setText(dateFormatter.format(newDateFrom.getTime()));
                filterData.setSrchDate(dateFormatter.format(newDateFrom.getTime()));
                String strDateFrom = dateFormatter.format(newDateFrom.getTime());
                try {
                    dateFrom = dateFormatter.parse(strDateFrom);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                newDateTo = Calendar.getInstance();
                newDateTo.set(year, monthOfYear, dayOfMonth);
                mDateTo.setText(dateFormatter.format(newDateTo.getTime()));
                filterData.setSrchDateTo(dateFormatter.format(newDateTo.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        toDatePickerDialog.getDatePicker().setMinDate(dateFrom.getTime());

    }

    @OnClick(R.id.FilterSave)
    public void methodFilterSave(View view) {

        EventBus.getDefault().postSticky(filterData);

        ((AdminActivity) getActivity()).visitorPresenter.oneStepBack();

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

    @Override
    public void onClick(View v) {
        if (v == mDateFrom) {
            fromDatePickerDialog.show();
        } else if (v == mDateTo) {

            if (mDateFrom.getText().toString().trim().equalsIgnoreCase("")) {
                ViewUtils.showMessage(getActivity(), "Please select dateFrom first.");
                return;
            }

            toDatePickerDialog.show();
        }
    }

}

