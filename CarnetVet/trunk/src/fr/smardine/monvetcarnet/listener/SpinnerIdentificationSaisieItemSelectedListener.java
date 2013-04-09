package fr.smardine.monvetcarnet.listener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import fr.smardine.monvetcarnet.helper.EnStatutVisibilite;

public class SpinnerIdentificationSaisieItemSelectedListener implements OnItemSelectedListener {

	private final RadioButton rbMale;
	private final RadioButton rbFemelle;
	private final RadioGroup radioGroupSexe;
	private final DatePicker datePicker;
	private final TextView textView;

	public SpinnerIdentificationSaisieItemSelectedListener(RadioButton rbMale, RadioButton rbFemelle, RadioGroup radioGroupSexe,
			DatePicker datePicker, TextView textView) {
		this.rbMale = rbMale;
		this.rbFemelle = rbFemelle;
		this.radioGroupSexe = radioGroupSexe;
		this.datePicker = datePicker;
		this.textView = textView;
	}

	@Override
	public void onItemSelected(AdapterView<?> p_adapterView, View p_view, int p_itemPosition, long p_itemId) {
		afficheOuCacheElementEnFonctionDeLaPosition(p_itemPosition);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		afficheOuCacheElementEnFonctionDeLaPosition(-1);
	}

	private void afficheOuCacheElementEnFonctionDeLaPosition(int p_position) {
		// par defaut, tous les elements sont invisibles
		radioGroupSexe.setVisibility(EnStatutVisibilite.RETIRE.getCode());
		rbMale.setVisibility(EnStatutVisibilite.RETIRE.getCode());
		rbFemelle.setVisibility(EnStatutVisibilite.RETIRE.getCode());
		datePicker.setVisibility(EnStatutVisibilite.RETIRE.getCode());
		textView.setVisibility(EnStatutVisibilite.RETIRE.getCode());

		switch (p_position) {
			case 0:// sexe
				radioGroupSexe.setVisibility(EnStatutVisibilite.VISIBLE.getCode());
				rbMale.setVisibility(EnStatutVisibilite.VISIBLE.getCode());
				rbFemelle.setVisibility(EnStatutVisibilite.VISIBLE.getCode());
				break;
			case 1:// date de naissance
				datePicker.setVisibility(EnStatutVisibilite.VISIBLE.getCode());
				break;
			case 2:// race
			case 3:// robe
			case 4: // signes distinctifs
			case 5:// num puce
			case 6:// num tatouage
				textView.setVisibility(EnStatutVisibilite.VISIBLE.getCode());
				break;
			default:

		}
	}

}
