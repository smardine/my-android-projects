package fr.smardine.podcaster.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import android.content.Context;

public class DateHelper {

	public static String formatDateTime(Context context, String timeToFormat) {

		String finalDateTime = "";

		SimpleDateFormat iso8601Format = new SimpleDateFormat("EEE dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);

		Date date = null;
		if (timeToFormat != null) {
			try {
				date = iso8601Format.parse(timeToFormat);
			} catch (ParseException e) {
				date = null;
			}

			if (date != null) {
				long when = date.getTime();
				int flags = 0;
				flags |= android.text.format.DateUtils.FORMAT_SHOW_TIME;
				flags |= android.text.format.DateUtils.FORMAT_SHOW_DATE;
				flags |= android.text.format.DateUtils.FORMAT_ABBREV_MONTH;
				flags |= android.text.format.DateUtils.FORMAT_SHOW_YEAR;

				finalDateTime = android.text.format.DateUtils.formatDateTime(context, when + TimeZone.getDefault().getOffset(when), flags);
			}
		}
		return finalDateTime;
	}
}
