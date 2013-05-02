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

	private final MlVaccin vaccin;

	private final boolean isModeCreation;

	/**
	 * Constructeur
	 * @param p_ctx
	 * @param p_vaccinFragment
	 * @param p_dialog
	 * @param cbMaladieDeCarre
	 * @param cbParvovirose
	 * @param cbHepatiteDeRubarth
	 * @param cbLeptospirose
	 * @param cbTouxDuChenil
	 * @param cbPiroplasmose
	 * @param cbRage
	 * @param cbVermifuge
	 * @param p_datePicker
	 * @param p_carnet
	 * @param p_vaccin
	 * @param p_isModeCreation
	 */
	public BtOkVaccinSaisieChienClickListener(Context p_ctx, VaccinFragment p_vaccinFragment, Dialog p_dialog, CheckBox cbMaladieDeCarre,
			CheckBox cbParvovirose, CheckBox cbHepatiteDeRubarth, CheckBox cbLeptospirose, CheckBox cbTouxDuChenil,
			CheckBox cbPiroplasmose, CheckBox cbRage, CheckBox cbVermifuge, DatePicker p_datePicker, MlCarnet p_carnet, MlVaccin p_vaccin,
			boolean p_isModeCreation) {
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
		this.vaccin = p_vaccin;
		this.isModeCreation = p_isModeCreation;
	}

	/**
	 * Lors du clic sur le bouton Ok, si on est en mode creation, on insere un nouveau vaccin en base, sinon, on le met a jour.
	 * (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		MlVaccin unNouveauVaccin = null;
		if (this.isModeCreation) {
			unNouveauVaccin = new MlVaccin(this.carnet);
		} else {
			unNouveauVaccin = this.vaccin;
		}

		unNouveauVaccin.setMaladieDeCarre(this.cbMaladieDeCarre.isChecked());
		unNouveauVaccin.setParvovirose(this.cbParvovirose.isChecked());
		unNouveauVaccin.setHepatiteDeRubarth(this.cbHepatiteDeRubarth.isChecked());
		unNouveauVaccin.setLeptospirose(this.cbLeptospirose.isChecked());
		unNouveauVaccin.setTouxDuChenil(this.cbTouxDuChenil.isChecked());
		unNouveauVaccin.setPiroplasmose(this.cbPiroplasmose.isChecked());

		unNouveauVaccin.setRage(this.cbRage.isChecked());
		unNouveauVaccin.setVermifuge(this.cbVermifuge.isChecked());
		unNouveauVaccin.setDate(AndroidHelper.getDateFromDatePicker(datePicker));

		AccesTableVaccin tableVaccin = new AccesTableVaccin(ctx);
		if (this.isModeCreation) {
			if (tableVaccin.insertVaccinEnBase(unNouveauVaccin)) {
				vaccinFragment.metAjourListeDeVaccin(carnet);
				dialog.dismiss();
			} else {
				LogCatBuilder.WriteInfoToLog(ctx, EnNiveauLog.WARNING, TAG, R.string.insertion_en_base_echouee, TAG);
			}
		} else {
			if (tableVaccin.majVaccinEnBase(unNouveauVaccin)) {
				vaccinFragment.metAjourListeDeVaccin(carnet);
				dialog.dismiss();
			} else {
				LogCatBuilder.WriteInfoToLog(ctx, EnNiveauLog.WARNING, TAG, R.string.maj_en_base_echouee, TAG);
			}
		}

	}
}
