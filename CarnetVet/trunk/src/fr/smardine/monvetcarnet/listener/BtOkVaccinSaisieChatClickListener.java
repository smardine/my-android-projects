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

	private final MlVaccin vaccin;

	private final boolean isModeCreation;

	/**
	 * Constructeur
	 * @param p_ctx
	 * @param p_vaccinFragment
	 * @param p_dialog
	 * @param cbCoryza
	 * @param cbTyphus
	 * @param cbLeucose
	 * @param cbChlamydiose
	 * @param cbRage
	 * @param cbVermifuge
	 * @param p_datePicker
	 * @param p_carnet
	 * @param p_vaccin
	 * @param p_isModeCreation
	 */
	public BtOkVaccinSaisieChatClickListener(Context p_ctx, VaccinFragment p_vaccinFragment, Dialog p_dialog, CheckBox cbCoryza,
			CheckBox cbTyphus, CheckBox cbLeucose, CheckBox cbChlamydiose, CheckBox cbRage, CheckBox cbVermifuge, DatePicker p_datePicker,
			MlCarnet p_carnet, MlVaccin p_vaccin, boolean p_isModeCreation) {
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

		unNouveauVaccin.setCorysa(this.cbCoryza.isChecked());
		unNouveauVaccin.setTiphus(this.cbTyphus.isChecked());
		unNouveauVaccin.setLeucose(this.cbLeucose.isChecked());
		unNouveauVaccin.setChlamydiose(this.cbChlamydiose.isChecked());
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
