/**
 * 
 */
package fr.smardine.monvetcarnet.listener;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import fr.smardine.monvetcarnet.R;
import fr.smardine.monvetcarnet.database.accestable.AccesTableIdentification;
import fr.smardine.monvetcarnet.fragment.IdentificationFragment;
import fr.smardine.monvetcarnet.helper.AndroidHelper;
import fr.smardine.monvetcarnet.helper.log.EnNiveauLog;
import fr.smardine.monvetcarnet.helper.log.LogCatBuilder;
import fr.smardine.monvetcarnet.mdl.EnGenre;
import fr.smardine.monvetcarnet.mdl.MlCarnet;

/**
 * @author sims
 */
public class BtOkIdentificationSaisieClickListener implements OnClickListener {

	private final Spinner spinner;
	private final RadioButton rbMale;
	private final RadioButton rbFemelle;
	private final DatePicker datePicker;
	private final EditText textView;
	private final MlCarnet carnet;
	private final Context ctx;
	private final IdentificationFragment identFragment;
	private final Dialog dialog;
	private final String TAG = this.getClass().getName();

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
	public BtOkIdentificationSaisieClickListener(Context p_ctx, IdentificationFragment p_identFragment, Dialog p_dialog, Spinner p_spinner,
			RadioButton p_rbMale, RadioButton p_rbFemelle, DatePicker p_datePicker, EditText p_textView, MlCarnet p_carnet) {
		this.ctx = p_ctx;
		this.identFragment = p_identFragment;
		this.dialog = p_dialog;
		this.spinner = p_spinner;
		this.rbMale = p_rbMale;
		this.rbFemelle = p_rbFemelle;
		this.datePicker = p_datePicker;
		this.textView = p_textView;
		this.carnet = p_carnet;
	}

	/*
	 * (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (this.spinner.getSelectedItemPosition()) {
			case 0:// sexe
				if (rbMale.isChecked()) {
					carnet.getIdentificationAnimal().setGenreAnimal(EnGenre.MALE);
				} else if (rbFemelle.isChecked()) {
					carnet.getIdentificationAnimal().setGenreAnimal(EnGenre.FEMELLE);
				}
				break;
			case 1:// date de naissance
				carnet.getIdentificationAnimal().setDateNaissance(AndroidHelper.getDateFromDatePicker(datePicker));
				break;
			case 2:// race
				carnet.getIdentificationAnimal().getDetail().setRace(textView.getText().toString());
				break;
			case 3:// robe
				carnet.getIdentificationAnimal().getDetail().setRobe(textView.getText().toString());
				break;
			case 4: // signes distinctifs
				carnet.getIdentificationAnimal().getDetail().setSignesDistinctifs(textView.getText().toString());
				break;
			case 5:// num puce
				carnet.getIdentificationAnimal().getDetail().setNumPuce(textView.getText().toString());
				break;
			case 6:// num tatouage
				carnet.getIdentificationAnimal().getDetail().setNumTatouage(textView.getText().toString());
				break;
		}

		if (new AccesTableIdentification(ctx).majIdenificationEnBase(carnet.getIdentificationAnimal())) {
			identFragment.metAjourIdentification(carnet);
			dialog.dismiss();
		} else {
			// Toast.makeText(ctx, "Impossible de rentrer les données en bases (" + this.getClass().getName() + ")",
			// Toast.LENGTH_LONG).show();
			LogCatBuilder.WriteInfoToLog(ctx, EnNiveauLog.WARNING, TAG, R.string.insertion_en_base_echouee, TAG);
		}

	}
}
