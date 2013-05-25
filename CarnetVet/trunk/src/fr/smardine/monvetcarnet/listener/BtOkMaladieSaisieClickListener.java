package fr.smardine.monvetcarnet.listener;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import fr.smardine.monvetcarnet.R;
import fr.smardine.monvetcarnet.database.accestable.AccesTableMaladie;
import fr.smardine.monvetcarnet.fragment.MaladieFragment;
import fr.smardine.monvetcarnet.helper.AndroidHelper;
import fr.smardine.monvetcarnet.helper.log.EnNiveauLog;
import fr.smardine.monvetcarnet.helper.log.LogCatBuilder;
import fr.smardine.monvetcarnet.mdl.EnPictoMaladie;
import fr.smardine.monvetcarnet.mdl.MlCarnet;
import fr.smardine.monvetcarnet.mdl.MlMaladie;

public class BtOkMaladieSaisieClickListener implements OnClickListener {

	private final Context ctx;
	private final MaladieFragment maladieFragment;
	private final Dialog dialog;
	private final MlCarnet carnet;
	private final DatePicker datePicker;
	private final TextView titre;
	private final TextView symptomes;
	private final Button btPicto;
	private final TextView traitements;
	private final TextView notes;
	private final CheckBox cbRdvVeto;
	private final MlMaladie maladieSelectionnee;
	private final boolean isModeCreation;
	private final String TAG = this.getClass().getSimpleName();

	public BtOkMaladieSaisieClickListener(Context context, MaladieFragment maladieFragment, MlCarnet carnetParent, Dialog dialog,
			DatePicker datePicker, TextView titre, TextView symptomes, Button btPicto, CheckBox cbRdvVeto, TextView traitments,
			TextView notes, MlMaladie maladieSelectionnee, boolean isModeCreation) {
		this.ctx = context;
		this.maladieFragment = maladieFragment;
		this.dialog = dialog;
		this.carnet = carnetParent;
		this.datePicker = datePicker;
		this.titre = titre;
		this.symptomes = symptomes;
		this.btPicto = btPicto;
		this.cbRdvVeto = cbRdvVeto;
		this.traitements = traitments;
		this.notes = notes;
		this.maladieSelectionnee = maladieSelectionnee;
		this.isModeCreation = isModeCreation;
	}

	@Override
	public void onClick(View p_view) {
		MlMaladie uneMaladie = null;
		if (this.isModeCreation) {
			uneMaladie = new MlMaladie(carnet);
		} else {
			uneMaladie = this.maladieSelectionnee;
		}

		uneMaladie.setDate(AndroidHelper.getDateFromDatePicker(datePicker));
		uneMaladie.setNotes(notes.getText().toString());
		uneMaladie.setPictoMaladie(EnPictoMaladie.Nez);
		uneMaladie.setRdvVeto(cbRdvVeto.isChecked());
		uneMaladie.setSymptomes(symptomes.getText().toString());
		uneMaladie.setTitre(titre.getText().toString());
		uneMaladie.setTraitement(traitements.getText().toString());

		AccesTableMaladie tableMaladie = new AccesTableMaladie(ctx);
		if (this.isModeCreation) {
			if (tableMaladie.insertMaladieEnBase(uneMaladie)) {
				maladieFragment.metAjourListeDeMaladies(carnet);
				dialog.dismiss();
			} else {
				LogCatBuilder.WriteInfoToLog(ctx, EnNiveauLog.WARNING, TAG, R.string.insertion_en_base_echouee, TAG);
			}
		} else {
			if (tableMaladie.majMaladieEnBase(uneMaladie)) {
				maladieFragment.metAjourListeDeMaladies(carnet);
				dialog.dismiss();
			} else {
				LogCatBuilder.WriteInfoToLog(ctx, EnNiveauLog.WARNING, TAG, R.string.maj_en_base_echouee, TAG);
			}
		}
	}

}
