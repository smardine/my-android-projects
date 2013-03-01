package fr.smardine.podcaster.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.util.Log;

public final class StringHelper {
	/**
	 * Constructeur privé pour classe utilitaire
	 */
	private StringHelper() {

	}

	public static final String RES_STRING = "string";
	public static final String UNDERSCORE = "_";
	public static final String SLASH = "/";
	public static final String PLUS = "+";
	public static final String ESPACE = " ";
	private static String TAG = StringHelper.class.getSimpleName();

	/**
	 * Renvoyer une date p_datetime au format d'une chaine AAAAMMJJ
	 * @param p_datetime
	 * @return
	 */
	public static String DateToAAAAMMJJ(Date p_datetime) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(p_datetime);
	}

	/**
	 * Renvoit une chaine représentative des données p_bytes réprésentée en
	 * hexadécimal au format chaine de caractère ^par ex. le tableau d'octets A0
	 * 34 1E sera reprétenté par la chaine "A0341E" en sortie de cette procédure
	 * @param p_bytes
	 * @return String
	 */
	public static String BytesToHexaString(byte[] p_bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < p_bytes.length; i++) {
			String hex = Integer.toHexString(0xff & p_bytes[i]);
			if (hex.length() == 1) {
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	/**
	 * formate une date Date en "AAAA-MM-JJ HH:MM"
	 * @param p_datetime
	 * @return String au format "AAAA-MM-JJ HH:MM"
	 */
	public static String DateToIso8601(Date p_datetime) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return formatter.format(p_datetime);
	}

	/**
	 * traduit une chaine en date Date
	 * @param p_iso8601 au format "AAAA-MM-JJ HH:MM"
	 * @return null si format de date non respectée
	 */
	public static Date Iso8601ToDate(String p_iso8601) {
		Date retour = null;
		String iso8601_pattern = "yyyy-MM-dd HH:mm";
		if (p_iso8601 != null) {
			if (p_iso8601.length() == 10) {
				iso8601_pattern = "yyyy-MM-dd";
			}
			SimpleDateFormat sdfDate = new SimpleDateFormat(iso8601_pattern);
			try {
				retour = sdfDate.parse(p_iso8601);
			} catch (ParseException e1) {
				Log.e(TAG,
						">> Iso8601ToDate parse error: "
								+ e1.getLocalizedMessage());
			}
		}
		return retour;
	}

	/**
	 * Transformer une date au format iso8601 en format p_format
	 * @param p_iso8601
	 * @param p_format format de la date exemple "dd/MM/yyyy à HH:mm"
	 * @return
	 */
	public static String Iso8601ToFormat(String p_iso8601, String p_format) {
		String retour = "";
		if ((p_iso8601 != null) && (p_format != null)) {
			Date d = Iso8601ToDate(p_iso8601);
			SimpleDateFormat formatter = new SimpleDateFormat(p_format);
			retour = formatter.format(d);
		}
		return retour;
	}

	/**
	 * traduit une chaine en date Date
	 * @param p_jjMMaaaaHHmmSS au format "dd-MM-yyyy HH:mm:SS"
	 * @return null si format incorrect
	 */
	public static Date jjMMaaaaHHmmSSToDate(String p_jjMMaaaaHHmmSS) {
		Date retour = null;
		String iso8601_pattern = "dd-MM-yyyy HH:mm:SS";
		if (p_jjMMaaaaHHmmSS != null) {
			if (p_jjMMaaaaHHmmSS.length() == 10) {
				iso8601_pattern = "dd-MM-yyyy";
			}
			SimpleDateFormat sdfDate = new SimpleDateFormat(iso8601_pattern);
			try {
				retour = sdfDate.parse(p_jjMMaaaaHHmmSS);
			} catch (ParseException e1) {
				Log.e(TAG,
						">> jjMMaaaaHHmmSSToDate parse error: "
								+ e1.getLocalizedMessage());
			}
		}
		return retour;
	}

}
