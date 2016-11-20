package com.visitorapp.bloominfotech.utils.datetimepicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hp on 11/20/2016.
 */

public class DateFormat {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    private Date mDate;
    private Calendar mCalendar;

    /**
     * Constructor with no parameters which will create DateTime
     * Object with the current date and time
     */
    public DateFormat() {

        this(new Date());

    }

    /**
     * Constructor with Date parameter which will initialize the
     * object to the date specified
     * @param date
     */
    public DateFormat(Date date) {

        mDate = date;
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(mDate);

    }

    /**
     * Constructor with DateFormat and DateString which will be
     * used to initialize the object to the date string specified
     * @param dateFormat String DateFormat
     * @param dateString Date in String
     */
    public DateFormat(String dateFormat, String dateString) {

        mCalendar = Calendar.getInstance();
        SimpleDateFormat mFormat = new SimpleDateFormat(dateFormat);

        try {
            mDate = mFormat.parse(dateString);
            mCalendar.setTime(mDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    /**
     * Constructor with DateString formatted as default DateFormat
     * defined in DATE_FORMAT variable
     * @param dateString
     */
    public DateFormat(String dateString) {

        this(DATE_FORMAT, dateString);

    }

    /**
     * Constructor with Year, Month, Day, Hour, Minute, and Second
     * which will be use to set the date to
     * @param year Year
     * @param monthOfYear Month of Year
     * @param dayOfMonth Day of Month
     * @param hourOfDay Hour of Day
     * @param minuteOfHour Minute
     * @param secondOfMinute Second
     */
    public DateFormat(int year, int monthOfYear, int dayOfMonth,
                    int hourOfDay, int minuteOfHour, int secondOfMinute) {

        mCalendar = Calendar.getInstance();
        mCalendar.set(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute);
        mDate = mCalendar.getTime();

    }

    /**
     * Constructor with Year, Month Day, Hour, Minute which
     * will be use to set the date to
     * @param year Year
     * @param monthOfYear Month of Year
     * @param dayOfMonth Day of Month
     * @param hourOfDay Hour of Day
     * @param minuteOfHour Minute
     */
    public DateFormat(int year, int monthOfYear, int dayOfMonth,
                    int hourOfDay, int minuteOfHour) {

        this(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, 0);

    }

    /**
     * Constructor with Date only (no time)
     * @param year Year
     * @param monthOfYear Month of Year
     * @param dayOfMonth Day of Month
     */
    public DateFormat(int year, int monthOfYear, int dayOfMonth) {

        this(year, monthOfYear, dayOfMonth, 0,0,0);

    }

    public Date getDate() {
        return mDate;
    }

    public Calendar getCalendar() {
        return mCalendar;
    }

    public String getDateString(String dateFormat) {

        SimpleDateFormat mFormat = new SimpleDateFormat(dateFormat);
        return mFormat.format(mDate);

    }

    public String getDateString() {

        return getDateString(DATE_FORMAT);

    }

    public int getYear() {

        return mCalendar.get(Calendar.YEAR);

    }

    public int getMonthOfYear() {

        return mCalendar.get(Calendar.MONTH);

    }

    public int getDayOfMonth() {

        return mCalendar.get(Calendar.DAY_OF_MONTH);

    }

    public int getHourOfDay() {

        return mCalendar.get(Calendar.HOUR_OF_DAY);

    }

    public int getMinuteOfHour() {

        return mCalendar.get(Calendar.MINUTE);

    }

    public int getSecondOfMinute() {

        return mCalendar.get(Calendar.SECOND);

    }

}
