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
public class BtOkVaccinSaisieChienClickListener implements OnClickListener {

	private final DatePicker datePicker;

	private final Context ctx;
	private final VaccinFragment vaccinFragment;
	private final Dialog dialog;
	private final String TAG = this.getClass().getName();
	private final CheckBox cbVermifuge;
	private final MlCarnet carnet;
	private final CheckBox cbRage;

	private final CheckBox cbMaladieDeCarre;

	private final CheckBox cbParvovirose;

	private final CheckBox cbHepatiteDeRubarth;

	private final CheckBox cbLeptospirose;

	private final CheckBox cbTouxDuChenil;

	private final CheckBox cbPiroplasmose;

	/**
	 * Constructeur
	 * @param p_ctx
	 * @param p_identFragment
	 * @param p_dialog
	 * @param cbVermifuge2
	 * @param cbRage2
	 * @param p_spinner
	 * @param p_rbMale
	 * @param p_rbFemelle
	 * @param p_datePicker
	 * @param p_textView
	 * @param p_carnet
	 */

	public BtOkVaccinSaisieChienClickListener(Context p_ctx, VaccinFragment p_vaccinFragment, Dialog p_dialog, CheckBox cbMaladieDeCarre,
			CheckBox cbParvovirose, CheckBox cbHepatiteDeRubarth, CheckBox cbLeptospirose, CheckBox cbTouxDuChenil,
			CheckBox cbPiroplasmose, CheckBox cbRage, CheckBox cbVermifuge, DatePicker p_datePicker, MlCarnet p_carnet) {
		this.ctx = p_ctx;
		this.vaccinFragment = p_vaccinFragment;
		this.dialog = p_dialog;
		this.cbMaladieDeCarre = cbMaladieDeCarre;
		this.cbParvovirose = cbParvovirose;
		this.cbHepatiteDeRubarth = cbHepatiteDeRubarth;
		this.cbLeptospirose = cbLeptospirose;
		this.cbTouxDuChenil = cbTouxDuChenil;
		this.cbPiroplasmose = cbPiroplasmose;
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

		unNouveauVaccin.setMaladieDeCarre(this.cbMaladieDeCarre.isChecked());
		unNouveauVaccin.setParvovirose(this.cbParvovirose.isChecked());
		unNouveauVaccin.setHepatiteDeRubarth(this.cbHepatiteDeRubarth.isChecked());
		unNouveauVaccin.setLeptospirose(this.cbLeptospirose.isChecked());
		unNouveauVaccin.setTouxDuChenil(this.cbTouxDuChenil.isChecked());
		unNouveauVaccin.setPiroplasmose(this.cbPiroplasmose.isChecked());

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
