package fr.smardine.monvetcarnet.listener;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.TextView;
import fr.smardine.monvetcarnet.R;
import fr.smardine.monvetcarnet.database.accestable.AccesTablePoids;
import fr.smardine.monvetcarnet.fragment.PoidsFragment;
import fr.smardine.monvetcarnet.helper.AndroidHelper;
import fr.smardine.monvetcarnet.helper.log.EnNiveauLog;
import fr.smardine.monvetcarnet.helper.log.LogCatBuilder;
import fr.smardine.monvetcarnet.mdl.EnUnitePoids;
import fr.smardine.monvetcarnet.mdl.MlCarnet;
import fr.smardine.monvetcarnet.mdl.MlPoids;

public class BtOkPoidsSaisieClickListener implements OnClickListener {

	private final Context ctx;
	private final PoidsFragment poidFragment;
	private final Dialog dialog;
	private final MlCarnet carnet;
	private final DatePicker datePicker;

	private final TextView notes;

	private final MlPoids poidSelectionne;
	private final boolean isModeCreation;
	private final String TAG = this.getClass().getSimpleName();
	private final TextView poidEnKilo;
	private final TextView poidEnGramme;

	public BtOkPoidsSaisieClickListener(Context context, PoidsFragment poidFragment, MlCarnet carnetParent, Dialog dialog,
			DatePicker datePicker, TextView poidEnKilo, TextView poidEnGramme, TextView notes, MlPoids poidSelectionne,
			boolean isModeCreation) {
		this.ctx = context;
		this.poidFragment = poidFragment;
		this.dialog = dialog;
		this.carnet = carnetParent;
		this.datePicker = datePicker;
		this.poidEnKilo = poidEnKilo;
		this.poidEnGramme = poidEnGramme;
		this.notes = notes;
		this.poidSelectionne = poidSelectionne;
		this.isModeCreation = isModeCreation;
	}

	@Override
	public void onClick(View p_view) {
		MlPoids unPoid = null;
		if (this.isModeCreation) {
			unPoid = new MlPoids(carnet);
		} else {
			unPoid = this.poidSelectionne;
		}

		unPoid.setDate(AndroidHelper.getDateFromDatePicker(datePicker));
		unPoid.setNote(notes.getText().toString());
		unPoid.setUnitePoids(EnUnitePoids.Kilo);
		unPoid.setValeur(Double.parseDouble(this.poidEnKilo.getText().toString()));

		AccesTablePoids tablePoid = new AccesTablePoids(ctx);
		if (this.isModeCreation) {
			if (tablePoid.insertPoidEnBase(unPoid)) {
				poidFragment.metAjourListePoids(carnet);
				dialog.dismiss();
			} else {
				LogCatBuilder.WriteInfoToLog(ctx, EnNiveauLog.WARNING, TAG, R.string.insertion_en_base_echouee, TAG);
			}
		} else {
			if (tablePoid.majPoidEnBase(unPoid)) {
				poidFragment.metAjourListePoids(carnet);
				dialog.dismiss();
			} else {
				LogCatBuilder.WriteInfoToLog(ctx, EnNiveauLog.WARNING, TAG, R.string.maj_en_base_echouee, TAG);
			}
		}
	}
}
