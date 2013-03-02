package fr.smardine.podcaster.helper;

import java.io.File;
import java.util.Calendar;

import android.content.Context;
import android.os.Environment;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.database.RequeteFactory;
import fr.smardine.podcaster.helper.log.EnNiveauLog;
import fr.smardine.podcaster.helper.log.LogCatBuilder;

public class SauvegardeRestaurationBdd {

	private Context context;
	private final String TAG = "SauvegardeRestaurationBdd";

	public SauvegardeRestaurationBdd(Context p_context) {
		this.context = p_context;
	}

	public boolean lanceSauvegardeSurCarteSD() {
		boolean result = false;

		RequeteFactory requeteFac = new RequeteFactory(this.context);

		String cheminBase = requeteFac.getDatabasePath();
		File baseDansTel = new File(cheminBase);
		String PATH = Environment.getExternalStorageDirectory() + "/Podcaster/";
		File path = new File(PATH);
		if (!path.exists()) {
			path.mkdirs();
		}

		int mYear;
		int mMonth;
		int mDay;
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH) + 1;
		mDay = c.get(Calendar.DAY_OF_MONTH);

		String sYear = "" + mYear;
		String sMonth;
		if (mMonth < 10) {
			sMonth = "0" + mMonth;
		} else {
			sMonth = "" + mMonth;
		}
		String sDay;
		if (mDay < 10) {
			sDay = "0" + mDay;
		} else {
			sDay = "" + mDay;
		}

		File fichierSurCarteSD = new File(PATH + "podacster_base" + sYear + sMonth + sDay);

		result = ManipFichier.copier(baseDansTel, fichierSurCarteSD);

		LogCatBuilder.WriteInfoToLog(context, EnNiveauLog.INFO, TAG, context.getResources().getInteger(R.string.s_fin_de_la_sauvegarde),
				result);
		return result;

	}
}
