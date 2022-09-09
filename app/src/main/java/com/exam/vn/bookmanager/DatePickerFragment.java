package com.exam.vn.bookmanager;

import android.app.DialogFragment;

import java.util.Calendar;

/**
 * Created by admin on 6/18/18.
 */
public class DatePickerFragment extends DialogFragment {

    private int year, month, day;

    public DatePickerFragment() {
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
    }

}
