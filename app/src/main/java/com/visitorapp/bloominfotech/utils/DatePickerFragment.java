package com.visitorapp.bloominfotech.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;

import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.models.admin_detail.FilterData;
import com.visitorapp.bloominfotech.models.eventbus.MessageEvent;
import com.visitorapp.bloominfotech.utils.datetimepicker.DateFormat;
import com.visitorapp.bloominfotech.views.fragments.FragmentFilter;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.greenrobot.event.EventBus;

/**
 * Created by hp on 11/20/2016.
 */

public class DatePickerFragment extends DialogFragment implements
        DatePickerDialog.OnDateSetListener {
    private DatePickerDialog datepic;
    FilterData filterData = new FilterData();


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        datepic = new DatePickerDialog(getActivity(), this, year, month, day);

        return datepic;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Activity a = getActivity();


        String sYear = Integer.toString(year).substring(2, 4);
        String sMonth = Integer.toString(month + 1);
        String sDay = Integer.toString(day);
        // dateDueButton.setText(sMonth + "/" + sDay + "/" + sYear);


        String dtStart = "";
        Date date = null;
        dtStart = sYear + "-" + sMonth + "-" + sDay + "";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = format.parse(dtStart);
            System.out.println(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        FragmentFilter.setmindate = date;
        DateFormat mDateTime = new DateFormat(date);
        if (FragmentFilter.datepickerSelector.equalsIgnoreCase("dateFrom")) {
            filterData.setSrchDate(mDateTime.getDayOfMonth() + " " +
                    new DateFormatSymbols().getMonths()[mDateTime.getMonthOfYear()] + ", " + mDateTime.getYear());

            EventBus.getDefault().postSticky(new MessageEvent(getResources().getString(R.string.datefrom_selected) + "-" + mDateTime.getDayOfMonth() + " " +
                    new DateFormatSymbols().getMonths()[mDateTime.getMonthOfYear()] + ", " + mDateTime.getYear()));

        } else if (FragmentFilter.datepickerSelector.equalsIgnoreCase("dateTo")) {

            filterData.setSrchDateTo(mDateTime.getDayOfMonth() + " " +
                    new DateFormatSymbols().getMonths()[mDateTime.getMonthOfYear()] + ", " + mDateTime.getYear());
            EventBus.getDefault().postSticky(new MessageEvent(getResources().getString(R.string.dateto_selected) + "-" + mDateTime.getDayOfMonth() + " " +
                    new DateFormatSymbols().getMonths()[mDateTime.getMonthOfYear()] + ", " + mDateTime.getYear()));


        }


    }

    public void setMinimumDate(Calendar mintime) {
        // datepic.getDatePicker().setCalendarViewShown(false);

   //     datepic.getDatePicker().setCalendarViewShown(false);
        long OFFSET = 1000; //milli seconds offset for your date.
        mintime.add(Calendar.YEAR, -120);
        datepic.getDatePicker().setMinDate(mintime.getTimeInMillis()- 1 * OFFSET);
    }

    public void setMaximumDate(Calendar maxtime) {
        datepic.getDatePicker().setMaxDate(maxtime.getTimeInMillis());
    }
}