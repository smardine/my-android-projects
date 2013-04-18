package fr.smardine.monvetcarnet.listener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import fr.smardine.monvetcarnet.helper.AndroidHelper;
import fr.smardine.monvetcarnet.helper.DateHelper;
import fr.smardine.monvetcarnet.helper.EnStatutVisibilite;
import fr.smardine.monvetcarnet.helper.StringHelper;
import fr.smardine.monvetcarnet.mdl.MlCarnet;

/**
 * @author sims
 */
public class SpinnerIdentificationSaisieItemSelectedListener implements OnItemSelectedListener {

	private final RadioButton rbMale;
	private final RadioButton rbFemelle;
	private final RadioGroup radioGroupSexe;
	private final DatePicker datePicker;
	private final EditText textView;
	private final MlCarnet carnet;

	/**
	 * Constructeur
	 * @param rbMale
	 * @param rbFemelle
	 * @param radioGroupSexe
	 * @param datePicker
	 * @param textView
	 * @param p_carnet
	 */
	public SpinnerIdentificationSaisieItemSelectedListener(RadioButton rbMale, RadioButton rbFemelle, RadioGroup radioGroupSexe,
			DatePicker datePicker, EditText textView, MlCarnet p_carnet) {
		this.rbMale = rbMale;
		this.rbFemelle = rbFemelle;
		this.radioGroupSexe = radioGroupSexe;
		this.datePicker = datePicker;
		this.textView = textView;
		this.carnet = p_carnet;
	}

	@Override
	public void onItemSelected(AdapterView<?> p_adapterView, View p_view, int p_itemPosition, long p_itemId) {
		afficheOuCacheElementEnFonctionDeLaPosition(p_itemPosition);
		valoriseLesElementsEnFonctionDeLaPosition(p_itemPosition);
	}

	/**
	 * @param p_itemPosition
	 */
	private void valoriseLesElementsEnFonctionDeLaPosition(int p_itemPosition) {
		switch (p_itemPosition) {
			case 0:// sexe
				if (carnet.getIdentificationAnimal().getGenreAnimal() != null) {
					switch (carnet.getIdentificationAnimal().getGenreAnimal()) {
						case FEMELLE:
							rbFemelle.setChecked(true);
							break;
						case MALE:
							rbMale.setChecked(true);
							break;
					}
				}
				break;
			case 1:// date de naissance
				if (carnet.getIdentificationAnimal().getDateNaissance() != null
						&& !DateHelper.IsDateVideOuDateMini(carnet.getIdentificationAnimal().getDateNaissance())) {
					AndroidHelper.SetDateToDatePicker(datePicker, carnet.getIdentificationAnimal().getDateNaissance());
				}
				break;
			case 2:// race
				if (carnet.getIdentificationAnimal().getDetail() != null
						&& !StringHelper.IsNullOrEmpty(carnet.getIdentificationAnimal().getDetail().getRace())) {
					textView.setText(carnet.getIdentificationAnimal().getDetail().getRace());
				}
				break;
			case 3:// robe
				if (carnet.getIdentificationAnimal().getDetail() != null
						&& !StringHelper.IsNullOrEmpty(carnet.getIdentificationAnimal().getDetail().getRobe())) {
					textView.setText(carnet.getIdentificationAnimal().getDetail().getRobe());
				}
				break;
			case 4: // signes distinctifs
				if (carnet.getIdentificationAnimal().getDetail() != null
						&& !StringHelper.IsNullOrEmpty(carnet.getIdentificationAnimal().getDetail().getSignesDistinctifs())) {
					textView.setText(carnet.getIdentificationAnimal().getDetail().getSignesDistinctifs());
				}
				break;
			case 5:// num puce
				if (carnet.getIdentificationAnimal().getDetail() != null
						&& !StringHelper.IsNullOrEmpty(carnet.getIdentificationAnimal().getDetail().getNumPuce())) {
					textView.setText(carnet.getIdentificationAnimal().getDetail().getNumPuce());
				}
				break;
			case 6:// num tatouage
				if (carnet.getIdentificationAnimal().getDetail() != null
						&& !StringHelper.IsNullOrEmpty(carnet.getIdentificationAnimal().getDetail().getNumTatouage())) {
					textView.setText(carnet.getIdentificationAnimal().getDetail().getNumTatouage());
				}
				break;

		}

	}

	/**
	 * 
	 */
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		afficheOuCacheElementEnFonctionDeLaPosition(-1);
	}

	/**
	 * @param p_position
	 */
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
