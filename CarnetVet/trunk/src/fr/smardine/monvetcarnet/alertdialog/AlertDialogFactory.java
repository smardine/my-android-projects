package fr.smardine.monvetcarnet.alertdialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import fr.smardine.monvetcarnet.R;
import fr.smardine.monvetcarnet.adapter.ListAdapterNomDesVaccins;
import fr.smardine.monvetcarnet.adapter.SpinnerIdentificationSaisieAdapter;
import fr.smardine.monvetcarnet.database.accestable.AccesTableCarnet;
import fr.smardine.monvetcarnet.database.accestable.AccesTableIdentification;
import fr.smardine.monvetcarnet.fragment.CouvertureFragment;
import fr.smardine.monvetcarnet.fragment.IdentificationFragment;
import fr.smardine.monvetcarnet.fragment.MaladieFragment;
import fr.smardine.monvetcarnet.fragment.VaccinFragment;
import fr.smardine.monvetcarnet.helper.DateHelper;
import fr.smardine.monvetcarnet.helper.EnStatutVisibilite;
import fr.smardine.monvetcarnet.helper.StringHelper;
import fr.smardine.monvetcarnet.listener.BtOkIdentificationSaisieClickListener;
import fr.smardine.monvetcarnet.listener.BtOkVaccinSaisieChatClickListener;
import fr.smardine.monvetcarnet.listener.BtOkVaccinSaisieChienClickListener;
import fr.smardine.monvetcarnet.listener.CbCheckChangeListenerVaccin;
import fr.smardine.monvetcarnet.listener.SpinnerIdentificationSaisieItemSelectedListener;
import fr.smardine.monvetcarnet.mdl.EnTypeAnimal;
import fr.smardine.monvetcarnet.mdl.MlCarnet;
import fr.smardine.monvetcarnet.mdl.MlIdentification;
import fr.smardine.monvetcarnet.mdl.MlMaladie;
import fr.smardine.monvetcarnet.mdl.MlVaccin;

public class AlertDialogFactory {

	/**
	 * Crée et affiche l'alert dialog qui permet de faire les saisies d'informations sur la page "Identification"
	 * @param p_ctx
	 * @param p_identFragment
	 * @param p_carnet
	 */
	public static void creerEtAfficherIdentificationSaisie(final Context p_ctx, final IdentificationFragment p_identFragment,
			final MlCarnet p_carnet, int indexASlectionner) {
		final Dialog dialog = new Dialog(p_ctx);
		dialog.setContentView(R.layout.alertdialog_identification_saisie);
		dialog.setTitle("Ajouter une information");
		final RadioButton rbMale = (RadioButton) dialog.findViewById(R.id.rbMale);
		final RadioButton rbFemelle = (RadioButton) dialog.findViewById(R.id.rbFemelle);
		final RadioGroup radioGroupSexe = (RadioGroup) dialog.findViewById(R.id.radioGroupGenre);
		final DatePicker datePicker = (DatePicker) dialog.findViewById(R.id.datePickerIdentificationSaisie);
		final EditText textView = (EditText) dialog.findViewById(R.id.editTextIdentificationSaisie);
		final Spinner sp = (Spinner) dialog.findViewById(R.id.spinnerIdentificationSaisie);
		final Button boutonOk = (Button) dialog.findViewById(R.id.btOk);

		sp.setAdapter(new SpinnerIdentificationSaisieAdapter(p_ctx, R.layout.spinner_text_only));
		sp.setOnItemSelectedListener(new SpinnerIdentificationSaisieItemSelectedListener(rbMale, rbFemelle, radioGroupSexe, datePicker,
				textView, p_carnet));
		boutonOk.setOnClickListener(new BtOkIdentificationSaisieClickListener(p_ctx, p_identFragment, dialog, sp, rbMale, rbFemelle,
				datePicker, textView, p_carnet));

		if (indexASlectionner >= 0) {
			sp.setSelection(indexASlectionner);
			dialog.setTitle("Modifier une information");
		}
		dialog.show();
	}

	public static void creerEtAfficherMaladieSaisie(Context context, MaladieFragment maladieFragment, MlCarnet carnetParent,
			MlMaladie maladieSelectionnee) {

	}

	/**
	 * Créer et afficher l'alert dialog permettant la saisie ou la modification d'un vaccin
	 * @param p_ctx
	 * @param p_vaccinFragment
	 * @param p_carnet
	 * @param isModeCreation
	 * @param p_vaccin
	 */
	public static void creerEtAfficherVaccinSaisie(final Context p_ctx, final VaccinFragment p_vaccinFragment, final MlCarnet p_carnet,
			boolean isModeCreation, final MlVaccin p_vaccin) {
		final Dialog dialog = new Dialog(p_ctx);
		dialog.setContentView(R.layout.alertdialog_vaccin_saisie);
		dialog.setTitle("Ajouter un vaccin");
		final DatePicker datePicker = (DatePicker) dialog.findViewById(R.id.datePickerVaccinSaisie);

		final CheckBox cbCoryza = (CheckBox) dialog.findViewById(R.id.cbCoryza);
		final CheckBox cbTyphus = (CheckBox) dialog.findViewById(R.id.cbTyphus);
		final CheckBox cbLeucose = (CheckBox) dialog.findViewById(R.id.cbLeucose);
		final CheckBox cbChlamydiose = (CheckBox) dialog.findViewById(R.id.cbChlamydiose);
		final LinearLayout layoutChat = (LinearLayout) dialog.findViewById(R.id.layoutVaccinChat);

		final CheckBox cbMaladieDeCarre = (CheckBox) dialog.findViewById(R.id.cbMaladieDeCarre);
		final CheckBox cbParvovirose = (CheckBox) dialog.findViewById(R.id.cbParvovirose);
		final CheckBox cbHepatiteDeRubarth = (CheckBox) dialog.findViewById(R.id.cbHepatiteDeRubarth);
		final CheckBox cbLeptospirose = (CheckBox) dialog.findViewById(R.id.cbLeptospirose);
		final CheckBox cbTouxDuChenil = (CheckBox) dialog.findViewById(R.id.cbTouxDuChenil);
		final CheckBox cbPiroplasmose = (CheckBox) dialog.findViewById(R.id.cbPiroplasmose);

		final LinearLayout layoutChien = (LinearLayout) dialog.findViewById(R.id.layoutVaccinChien);

		final CheckBox cbRage = (CheckBox) dialog.findViewById(R.id.cbRage);
		final CheckBox cbVermifuge = (CheckBox) dialog.findViewById(R.id.cbVermifuge);

		final Button boutonOk = (Button) dialog.findViewById(R.id.btOk);
		// Par defaut, les deux layout sont retiré,
		// puis en fonction du type d'animal, on affiche l'un ou l'autre
		layoutChat.setVisibility(EnStatutVisibilite.RETIRE.getCode());
		layoutChien.setVisibility(EnStatutVisibilite.RETIRE.getCode());

		// Par defaut, le bouton Ok est grisé car aucune des checkBox n'est selectionnée.
		boutonOk.setEnabled(false);

		// Faire en sorte que le bouton "Ok" ne soit accessible que si au moins une des checkbox est cochée
		CbCheckChangeListenerVaccin cbChangeListener = new CbCheckChangeListenerVaccin(boutonOk);
		cbCoryza.setOnCheckedChangeListener(cbChangeListener);
		cbTyphus.setOnCheckedChangeListener(cbChangeListener);
		cbLeucose.setOnCheckedChangeListener(cbChangeListener);
		cbChlamydiose.setOnCheckedChangeListener(cbChangeListener);
		cbMaladieDeCarre.setOnCheckedChangeListener(cbChangeListener);
		cbParvovirose.setOnCheckedChangeListener(cbChangeListener);
		cbHepatiteDeRubarth.setOnCheckedChangeListener(cbChangeListener);
		cbLeptospirose.setOnCheckedChangeListener(cbChangeListener);
		cbTouxDuChenil.setOnCheckedChangeListener(cbChangeListener);
		cbPiroplasmose.setOnCheckedChangeListener(cbChangeListener);
		cbRage.setOnCheckedChangeListener(cbChangeListener);
		cbVermifuge.setOnCheckedChangeListener(cbChangeListener);

		if (!isModeCreation) {
			dialog.setTitle("Modifier un vaccin");
			cbCoryza.setChecked(p_vaccin.isCorysa());
			cbTyphus.setChecked(p_vaccin.isTiphus());
			cbLeucose.setChecked(p_vaccin.isLeucose());
			cbChlamydiose.setChecked(p_vaccin.isChlamydiose());
			cbMaladieDeCarre.setChecked(p_vaccin.isMaladieDeCarre());
			cbParvovirose.setChecked(p_vaccin.isParvovirose());
			cbHepatiteDeRubarth.setChecked(p_vaccin.isHepatiteDeRubarth());
			cbLeptospirose.setChecked(p_vaccin.isLeptospirose());
			cbTouxDuChenil.setChecked(p_vaccin.isTouxDuChenil());
			cbPiroplasmose.setChecked(p_vaccin.isPiroplasmose());
			cbRage.setChecked(p_vaccin.isRage());
			cbVermifuge.setChecked(p_vaccin.isVermifuge());
		}

		switch (p_carnet.getIdentificationAnimal().getTypeAnimal()) {
			case CHAT:
				layoutChat.setVisibility(EnStatutVisibilite.VISIBLE.getCode());
				boutonOk.setOnClickListener(new BtOkVaccinSaisieChatClickListener(p_ctx, p_vaccinFragment, dialog, cbCoryza, cbTyphus,
						cbLeucose, cbChlamydiose, cbRage, cbVermifuge, datePicker, p_carnet, p_vaccin, isModeCreation));
				break;
			case CHIEN:
				layoutChien.setVisibility(EnStatutVisibilite.VISIBLE.getCode());
				boutonOk.setOnClickListener(new BtOkVaccinSaisieChienClickListener(p_ctx, p_vaccinFragment, dialog, cbMaladieDeCarre,
						cbParvovirose, cbHepatiteDeRubarth, cbLeptospirose, cbTouxDuChenil, cbPiroplasmose, cbRage, cbVermifuge,
						datePicker, p_carnet, p_vaccin, isModeCreation));
				break;
		}

		dialog.show();
	}

	/**
	 * Créer et afficher l'alertdialog qui permet de consulter la liste des vaccins lors du clic sur un des elements de la grille des
	 * vaccins. S'affiche en mode consultation, puis au clic sur le bouton Modifier, lance une autre alertdialog (la meme que pour la saisie
	 * d'un vaccin)
	 * @param p_ctx
	 * @param p_vaccinFragment
	 * @param p_carnet
	 * @param p_vaccin
	 */
	public static void creerEtAfficherVaccinConsultation(final Context p_ctx, final VaccinFragment p_vaccinFragment,
			final MlCarnet p_carnet, final MlVaccin p_vaccin) {
		final Dialog dialog = new Dialog(p_ctx);
		dialog.setContentView(R.layout.alertdialog_vaccin_detail);
		dialog.setTitle("Détail d'un vaccin");
		TextView tvDate = (TextView) dialog.findViewById(R.id.tvdateVaccin);
		ListView lvListeVaccin = (ListView) dialog.findViewById(R.id.lvListeVaccins);
		Button boutonOk = recupererBoutonOk(dialog);
		Button boutonModifier = (Button) dialog.findViewById(R.id.btModifier);

		lvListeVaccin.setAdapter(new ListAdapterNomDesVaccins(p_ctx, p_vaccin));
		tvDate.setText(DateHelper.ddMMMyyyy(p_vaccin.getDate()));

		boutonOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		boutonModifier.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				boolean isModeCreation = false;
				dialog.dismiss();
				creerEtAfficherVaccinSaisie(p_ctx, p_vaccinFragment, p_carnet, isModeCreation, p_vaccin);
			}
		});

		dialog.show();
	}

	/**
	 * Crée et affiche l'alert diaolg qui est affiché lors de la premiere entrée dans le soft
	 * @param p_ctx
	 * @param fragmentCouverture
	 * @param fragmentIdentification
	 */
	public static void creerEtAfficherIdentificationDialog(final Context p_ctx, final CouvertureFragment fragmentCouverture,
			final IdentificationFragment fragmentIdentification) {
		final Dialog dialog = new Dialog(p_ctx);
		dialog.setContentView(R.layout.alertdialog_identification);
		dialog.setTitle("Identification");

		// set the custom dialog components - text, image and button
		final EditText etNomAnimal = (EditText) dialog.findViewById(R.id.etNomAnimal);
		final RadioButton rbChat = (RadioButton) dialog.findViewById(R.id.rbChat);
		final RadioButton rbChien = (RadioButton) dialog.findViewById(R.id.rbChien);
		rbChat.setChecked(false);
		rbChien.setChecked(false);

		final Button boutonOk = (Button) dialog.findViewById(R.id.btOk);
		final Button boutonEffacer = (Button) dialog.findViewById(R.id.btEffacer);

		etNomAnimal.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
			}

			@Override
			public void afterTextChanged(Editable editable) {
				// le bouton de validation sera actif seulement une fois le nom de l'animal saisi.
				boutonOk.setEnabled(!StringHelper.IsNullOrEmpty(etNomAnimal.getText().toString().trim()));
			}
		});
		etNomAnimal.setText("");

		// if button is clicked, close the custom dialog
		boutonOk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MlCarnet unCarnet = new MlCarnet();
				MlIdentification uneIdentification = new MlIdentification(unCarnet);
				uneIdentification.setNomAnimal(etNomAnimal.getText().toString().trim());
				unCarnet.setNomCarnet(uneIdentification.getNomAnimal());
				if (rbChat.isChecked()) {
					uneIdentification.setTypeAnimal(EnTypeAnimal.CHAT);
				}
				if (rbChien.isChecked()) {
					uneIdentification.setTypeAnimal(EnTypeAnimal.CHIEN);
				}

				AccesTableIdentification accesIdent = new AccesTableIdentification(dialog.getContext());
				AccesTableCarnet accesCarnet = new AccesTableCarnet(dialog.getContext());
				accesCarnet.insertCarnetEnBase(unCarnet);
				uneIdentification.getCarnetParent().setIdCarnet(unCarnet.getId());
				accesIdent.insertIdentificationEnBase(uneIdentification);

				unCarnet.setIdentificationAnimal(uneIdentification);

				fragmentCouverture.metAjourCouverture(unCarnet);
				fragmentIdentification.metAjourIdentification(unCarnet);
				// fermer le dialog
				dialog.dismiss();
			}
		});

		boutonEffacer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				etNomAnimal.setText("");
				rbChat.setChecked(false);
				rbChien.setChecked(false);
			}
		});
		// rendre l'alert dialog inssensible a la touche retour (ne peut pas etre annulé)
		dialog.setCancelable(false);

		dialog.show();
	}

	/**
	 * Creer et afficher l'aertdialog permettant de saisir/changer le nom de l'animal
	 * @param p_ctx
	 * @param p_carnet
	 * @param fragmentCouverture
	 * @param fragmentIdentification
	 */
	public static void creerEtAfficherSaisieNomAnimal(final Context p_ctx, final MlCarnet p_carnet,
			final CouvertureFragment fragmentCouverture, final IdentificationFragment fragmentIdentification) {

		final Dialog dialog = creerAlertDialogSaisieInfoTexte(p_ctx, "Nom de l'animal");

		final EditText etNomAnimal = recupererEditTextSaisie(dialog);
		final Button boutonOk = recupererBoutonOk(dialog);
		final Button boutonEffacer = recupererBoutonEffacer(dialog);

		etNomAnimal.setHint(R.string.nom_animal);

		if (!StringHelper.IsNullOrEmpty(p_carnet.getIdentificationAnimal().getNomAnimal())) {
			etNomAnimal.setText(p_carnet.getIdentificationAnimal().getNomAnimal());
		}

		boutonOk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				p_carnet.getIdentificationAnimal().setNomAnimal(etNomAnimal.getText().toString().trim());
				p_carnet.setNomCarnet(p_carnet.getIdentificationAnimal().getNomAnimal());

				AccesTableIdentification accesIdent = new AccesTableIdentification(dialog.getContext());
				AccesTableCarnet accesCarnet = new AccesTableCarnet(dialog.getContext());
				accesCarnet.majCarnetEnBase(p_carnet);

				accesIdent.majIdenificationEnBase(p_carnet.getIdentificationAnimal());

				fragmentCouverture.metAjourCouverture(p_carnet);
				fragmentIdentification.metAjourIdentification(p_carnet);
				// fermer le dialog
				dialog.dismiss();
			}
		});

		boutonEffacer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				etNomAnimal.setText("");
			}
		});

		dialog.show();
	}

	/**
	 * Creer l'alert dialog permettant la saisie d'info au format texte
	 * @param p_ctx
	 * @param p_titre
	 * @return
	 */
	private static Dialog creerAlertDialogSaisieInfoTexte(Context p_ctx, String p_titre) {
		final Dialog dialog = new Dialog(p_ctx);
		dialog.setContentView(R.layout.alertdialog_saisie_texte);
		dialog.setTitle(p_titre);
		return dialog;
	}

	/**
	 * Recuperer le composant EditText a partir du dialog parent
	 * @param p_dialogParent
	 * @return
	 */
	private static EditText recupererEditTextSaisie(Dialog p_dialogParent) {
		return (EditText) p_dialogParent.findViewById(R.id.etSaisie);
	}

	/**
	 * Recuperer le bouton Ok a partir du dialog parent
	 * @param p_dialogParent
	 * @return
	 */
	private static Button recupererBoutonOk(Dialog p_dialogParent) {
		return (Button) p_dialogParent.findViewById(R.id.btOk);
	}

	/**
	 * Recuperer le bouton Effacer a partir du dialog parent
	 * @param p_dialogParent
	 * @return
	 */
	private static Button recupererBoutonEffacer(Dialog p_dialogParent) {
		return (Button) p_dialogParent.findViewById(R.id.btEffacer);
	}

	public static void creerEtAfficherMaladieConsultation(final Context context, final MaladieFragment maladieFragment,
			final MlCarnet carnetParent, final MlMaladie maladieSelectionnee) {
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.alertdialog_maladie_detail);
		dialog.setTitle("Détail d'une maladie");
		TextView tvDate = (TextView) dialog.findViewById(R.id.tvdateMaladie);

		Button boutonOk = recupererBoutonOk(dialog);
		Button boutonModifier = (Button) dialog.findViewById(R.id.btModifier);
		// todo definir les autre composants de la fenetre (textView et checkBox ) et les valoriser (penser aussi au picto maladie)

		tvDate.setText(DateHelper.ddMMMyyyy(maladieSelectionnee.getDate()));

		boutonOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		boutonModifier.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				boolean isModeCreation = false;
				dialog.dismiss();
				creerEtAfficherMaladieSaisie(context, maladieFragment, carnetParent, isModeCreation, maladieSelectionnee);
			}
		});

		dialog.show();

	}

	public static void creerEtAfficherMaladieSaisie(Context context, MaladieFragment maladieFragment, MlCarnet carnetParent,
			boolean isModeCreation, MlMaladie maladieSelectionnee) {
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.alertdialog_maladie_saisie);
		dialog.setTitle("Ajouter une maladie");
		final DatePicker datePicker = (DatePicker) dialog.findViewById(R.id.datePickerMaladieSaisie);

		// final CheckBox cbCoryza = (CheckBox) dialog.findViewById(R.id.cbCoryza);
		// final CheckBox cbTyphus = (CheckBox) dialog.findViewById(R.id.cbTyphus);
		// final CheckBox cbLeucose = (CheckBox) dialog.findViewById(R.id.cbLeucose);
		// final CheckBox cbChlamydiose = (CheckBox) dialog.findViewById(R.id.cbChlamydiose);
		// final LinearLayout layoutChat = (LinearLayout) dialog.findViewById(R.id.layoutVaccinChat);
		//
		// final CheckBox cbMaladieDeCarre = (CheckBox) dialog.findViewById(R.id.cbMaladieDeCarre);
		// final CheckBox cbParvovirose = (CheckBox) dialog.findViewById(R.id.cbParvovirose);
		// final CheckBox cbHepatiteDeRubarth = (CheckBox) dialog.findViewById(R.id.cbHepatiteDeRubarth);
		// final CheckBox cbLeptospirose = (CheckBox) dialog.findViewById(R.id.cbLeptospirose);
		// final CheckBox cbTouxDuChenil = (CheckBox) dialog.findViewById(R.id.cbTouxDuChenil);
		// final CheckBox cbPiroplasmose = (CheckBox) dialog.findViewById(R.id.cbPiroplasmose);
		//
		// final LinearLayout layoutChien = (LinearLayout) dialog.findViewById(R.id.layoutVaccinChien);
		//
		// final CheckBox cbRage = (CheckBox) dialog.findViewById(R.id.cbRage);
		// final CheckBox cbVermifuge = (CheckBox) dialog.findViewById(R.id.cbVermifuge);

		final Button boutonOk = (Button) dialog.findViewById(R.id.btOk);

		// Par defaut, le bouton Ok est grisé car aucune des checkBox n'est selectionnée.
		boutonOk.setEnabled(false);

		// Faire en sorte que le bouton "Ok" ne soit accessible que si au moins une des checkbox est cochée
		CbCheckChangeListenerVaccin cbChangeListener = new CbCheckChangeListenerVaccin(boutonOk);
		// cbCoryza.setOnCheckedChangeListener(cbChangeListener);
		// cbTyphus.setOnCheckedChangeListener(cbChangeListener);
		// cbLeucose.setOnCheckedChangeListener(cbChangeListener);
		// cbChlamydiose.setOnCheckedChangeListener(cbChangeListener);
		// cbMaladieDeCarre.setOnCheckedChangeListener(cbChangeListener);
		// cbParvovirose.setOnCheckedChangeListener(cbChangeListener);
		// cbHepatiteDeRubarth.setOnCheckedChangeListener(cbChangeListener);
		// cbLeptospirose.setOnCheckedChangeListener(cbChangeListener);
		// cbTouxDuChenil.setOnCheckedChangeListener(cbChangeListener);
		// cbPiroplasmose.setOnCheckedChangeListener(cbChangeListener);
		// cbRage.setOnCheckedChangeListener(cbChangeListener);
		// cbVermifuge.setOnCheckedChangeListener(cbChangeListener);

		if (!isModeCreation) {
			dialog.setTitle("Modifier une maladie");
			// cbCoryza.setChecked(p_vaccin.isCorysa());
			// cbTyphus.setChecked(p_vaccin.isTiphus());
			// cbLeucose.setChecked(p_vaccin.isLeucose());
			// cbChlamydiose.setChecked(p_vaccin.isChlamydiose());
			// cbMaladieDeCarre.setChecked(p_vaccin.isMaladieDeCarre());
			// cbParvovirose.setChecked(p_vaccin.isParvovirose());
			// cbHepatiteDeRubarth.setChecked(p_vaccin.isHepatiteDeRubarth());
			// cbLeptospirose.setChecked(p_vaccin.isLeptospirose());
			// cbTouxDuChenil.setChecked(p_vaccin.isTouxDuChenil());
			// cbPiroplasmose.setChecked(p_vaccin.isPiroplasmose());
			// cbRage.setChecked(p_vaccin.isRage());
			// cbVermifuge.setChecked(p_vaccin.isVermifuge());
		}

		// switch (p_carnet.getIdentificationAnimal().getTypeAnimal()) {
		// case CHAT:
		// layoutChat.setVisibility(EnStatutVisibilite.VISIBLE.getCode());
		// boutonOk.setOnClickListener(new BtOkVaccinSaisieChatClickListener(p_ctx, p_vaccinFragment, dialog, cbCoryza, cbTyphus,
		// cbLeucose, cbChlamydiose, cbRage, cbVermifuge, datePicker, p_carnet, p_vaccin, isModeCreation));
		// break;
		// case CHIEN:
		// layoutChien.setVisibility(EnStatutVisibilite.VISIBLE.getCode());
		// boutonOk.setOnClickListener(new BtOkVaccinSaisieChienClickListener(p_ctx, p_vaccinFragment, dialog, cbMaladieDeCarre,
		// cbParvovirose, cbHepatiteDeRubarth, cbLeptospirose, cbTouxDuChenil, cbPiroplasmose, cbRage, cbVermifuge,
		// datePicker, p_carnet, p_vaccin, isModeCreation));
		// break;
		// }

		dialog.show();

	}

}
