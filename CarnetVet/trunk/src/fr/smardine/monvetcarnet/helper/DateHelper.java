package fr.smardine.monvetcarnet.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateHelper {

	static DateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRANCE);
	static DateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyy_MM_dd_HH_mm", Locale.FRANCE);
	static DateFormat ddMMMYYYY = new SimpleDateFormat("dd/MMM/yyyy", Locale.FRANCE);
	static DateFormat ddMMyyyy = new SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE);
	static DateFormat HHmmss = new SimpleDateFormat("hh:mm:ss", Locale.FRANCE);
	static DateFormat ddMMM = new SimpleDateFormat("dd MMM", Locale.FRANCE);
	public static DateFormat ddMMYYYYHHmmSS = new SimpleDateFormat("dd MM yyyy HH:mm:ss", Locale.FRANCE);

	public static String GetStringHHmmFromTimeInMilli(long p_timeInMillisecond) {
		long days = TimeUnit.MILLISECONDS.toDays(p_timeInMillisecond);
		p_timeInMillisecond -= TimeUnit.DAYS.toMillis(days);
		long hours = TimeUnit.MILLISECONDS.toHours(p_timeInMillisecond);
		p_timeInMillisecond -= TimeUnit.HOURS.toMillis(hours);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(p_timeInMillisecond);
		p_timeInMillisecond -= TimeUnit.MINUTES.toMillis(minutes);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(p_timeInMillisecond);

		StringBuilder sb = new StringBuilder(64);
		if (days > 0) {
			sb.append(days);
			sb.append(" Jour(s) ");
		}
		if (hours > 0) {
			sb.append(hours);
			sb.append(" Heure(s) ");
		}
		if (minutes > 0) {
			sb.append(minutes);
			sb.append(" Minute(s) ");
		}

		sb.append(seconds);
		sb.append(" Seconde(s)");

		return sb.toString();
	}

	/**
	 * Date systeme sous le format yyyy-MM-dd HH:mm:ss
	 * @return la date format�e -String
	 */
	public static String date() {
		String dat = yyyyMMddHHmmss.format(new Date());
		return dat;
	}

	public static String dateSeulement() {
		String dat = ddMMyyyy.format(new Date());
		return dat;
	}

	/**
	 * Date systeme sous le format yyyy_MM_dd_HH_mm_ss
	 * @return la date format�e -String
	 */
	public static String dateEtHeure() {
		String dat = yyyyMMddHHmm.format(new Date());
		return dat;
	}

	public static String ddMMM(Date p_date) {
		if (p_date == null) {
			return ddMMM.format(new Date());
		}
		return ddMMM.format(p_date);
	}

	public static String ddMMyyyyHHmmSS(Date p_date) {
		if (p_date == null) {
			return ddMMYYYYHHmmSS.format(new Date());
		}
		return ddMMYYYYHHmmSS.format(p_date);
	}

	public static String ddMMMyyyy(Date p_date) {
		if (p_date == null) {
			return ddMMMYYYY.format(new Date());
		}
		return ddMMMYYYY.format(p_date);
	}

	public static Date ddMMYYYYHHmmSSToDate(String p_date) {
		if (p_date == null) {
			try {
				return ddMMYYYYHHmmSS.parse(p_date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			return ddMMYYYYHHmmSS.parse(p_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
