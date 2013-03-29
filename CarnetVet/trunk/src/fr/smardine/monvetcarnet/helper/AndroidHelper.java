package fr.smardine.monvetcarnet.helper;

import java.util.Calendar;

import android.widget.DatePicker;

public class AndroidHelper {

	public static java.util.Date getDateFromDatePicker(DatePicker datePicker) {
		int day = datePicker.getDayOfMonth();
		int month = datePicker.getMonth();
		int year = datePicker.getYear();

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);

		return calendar.getTime();
	}

}
