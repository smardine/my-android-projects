/**
 * 
 */
package fr.smardine.monvetcarnet.listener;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.DatePicker;
import fr.smardine.monvetcarnet.R;
import fr.smardine.monvetcarnet.database.accestable.AccesTableVaccin;
import fr.smardine.monvetcarnet.fragment.VaccinFragment;
import fr.smardine.monvetcarnet.helper.AndroidHelper;
import fr.smardine.monvetcarnet.helper.log.EnNiveauLog;
import fr.smardine.monvetcarnet.helper.log.LogCatBuilder;
import fr.smardine.monvetcarnet.mdl.MlCarnet;
import fr.smardine.monvetcarnet.mdl.MlVaccin;

/**
 * @author sims
 */
public class BtOkVaccinSaisieChatClickListener implements OnClickListener {

	private final DatePicker datePicker;

	private final Context ctx;
	private final VaccinFragment vaccinFragment;
	private final Dialog dialog;
	private final String TAG = this.getClass().getName();
	private final CheckBox cbVermifuge;
	private final MlCarnet carnet;

	private final CheckBox cbCoryza;

	private final CheckBox cbTyphus;

	private final CheckBox cbLeucose;

	private final CheckBox cbChlamydiose;

	private final CheckBox cbRage;

	/**
	 * Constructeur
	 * @param p_ctx
	 * @param p_identFragment
	 * @param p_dialog
	 * @param p_spinner
	 * @param p_rbMale
	 * @param p_rbFemelle
	 * @param p_datePicker
	 * @param p_textView
	 * @param p_carnet
	 */

	public BtOkVaccinSaisieChatClickListener(Context p_ctx, VaccinFragment p_vaccinFragment, Dialog p_dialog, CheckBox cbCoryza,
			CheckBox cbTyphus, CheckBox cbLeucose, CheckBox cbChlamydiose, CheckBox cbRage, CheckBox cbVermifuge, DatePicker p_datePicker,
			MlCarnet p_carnet) {
		this.ctx = p_ctx;
		this.vaccinFragment = p_vaccinFragment;
		this.dialog = p_dialog;
		this.cbCoryza = cbCoryza;
		this.cbTyphus = cbTyphus;
		this.cbLeucose = cbLeucose;
		this.cbChlamydiose = cbChlamydiose;
		this.cbRage = cbRage;
		this.cbVermifuge = cbVermifuge;
		this.datePicker = p_datePicker;
		this.carnet = p_carnet;
	}

	/*
	 * (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		MlVaccin unNouveauVaccin = new MlVaccin(this.carnet);

		unNouveauVaccin.setCorysa(this.cbCoryza.isChecked());
		unNouveauVaccin.setTiphus(this.cbTyphus.isChecked());
		unNouveauVaccin.setLeucose(this.cbLeucose.isChecked());
		unNouveauVaccin.setChlamydiose(this.cbChlamydiose.isChecked());
		unNouveauVaccin.setRage(this.cbRage.isChecked());

		unNouveauVaccin.setVermifuge(this.cbVermifuge.isChecked());
		unNouveauVaccin.setDate(AndroidHelper.getDateFromDatePicker(datePicker));

		if (new AccesTableVaccin(ctx).insertVaccinEnBase(unNouveauVaccin)) {
			vaccinFragment.metAjourListeDeVaccin(carnet);
			dialog.dismiss();
		} else {
			// Toast.makeText(ctx, "Impossible de rentrer les données en bases (" + this.getClass().getName() + ")",
			// Toast.LENGTH_LONG).show();
			LogCatBuilder.WriteInfoToLog(ctx, EnNiveauLog.WARNING, TAG, R.string.insertion_en_base_echouee, TAG);
		}

	}
}
