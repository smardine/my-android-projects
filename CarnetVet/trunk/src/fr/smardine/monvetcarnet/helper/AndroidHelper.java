package fr.smardine.monvetcarnet.helper;

import java.util.Calendar;
import java.util.Date;

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

	public static void SetDateToDatePicker(DatePicker p_datePicker, Date p_date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(p_date);
		p_datePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
	}

}
