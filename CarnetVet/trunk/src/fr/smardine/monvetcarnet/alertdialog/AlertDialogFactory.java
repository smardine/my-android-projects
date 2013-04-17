package fr.smardine.monvetcarnet.alertdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import fr.smardine.monvetcarnet.R;
import fr.smardine.monvetcarnet.adapter.SpinnerIdentificationSaisieAdapter;
import fr.smardine.monvetcarnet.database.accestable.AccesTableCarnet;
import fr.smardine.monvetcarnet.database.accestable.AccesTableIdentification;
import fr.smardine.monvetcarnet.fragment.CouvertureFragment;
import fr.smardine.monvetcarnet.fragment.IdentificationFragment;
import fr.smardine.monvetcarnet.listener.BtOkIdentificationSaisieClickListener;
import fr.smardine.monvetcarnet.listener.SpinnerIdentificationSaisieItemSelectedListener;
import fr.smardine.monvetcarnet.mdl.EnTypeAnimal;
import fr.smardine.monvetcarnet.mdl.MlCarnet;
import fr.smardine.monvetcarnet.mdl.MlIdentification;

public class AlertDialogFactory {

	public static void ceerEtAfficheIdentificationSaisie(final Context p_ctx, final IdentificationFragment p_identFragment,
			final MlCarnet p_carnet) {
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
		// final Button boutonEffacer = (Button) dialog.findViewById(R.id.btEffacer);
		sp.setAdapter(new SpinnerIdentificationSaisieAdapter(p_ctx, R.layout.spinner_text_only));
		sp.setOnItemSelectedListener(new SpinnerIdentificationSaisieItemSelectedListener(rbMale, rbFemelle, radioGroupSexe, datePicker,
				textView, p_carnet));
		boutonOk.setOnClickListener(new BtOkIdentificationSaisieClickListener(p_ctx, p_identFragment, dialog, sp, rbMale, rbFemelle,
				datePicker, textView, p_carnet));
		dialog.show();
	}

	public static void creerEtAfficherIdentificationDialog(final Context p_ctx, final CouvertureFragment fragmentCouverture,
			final IdentificationFragment fragmentIdentification) {
		final Dialog dialog = new Dialog(p_ctx);
		dialog.setContentView(R.layout.alertdialog_identification);
		dialog.setTitle("Identification");

		// set the custom dialog components - text, image and button
		final EditText etNomAnimal = (EditText) dialog.findViewById(R.id.etNomAnimal);
		final RadioButton rbChat = (RadioButton) dialog.findViewById(R.id.rbChat);
		final RadioButton rbChien = (RadioButton) dialog.findViewById(R.id.rbChien);

		etNomAnimal.setText("");

		Button boutonOk = (Button) dialog.findViewById(R.id.btOk);
		Button boutonEffacer = (Button) dialog.findViewById(R.id.btEffacer);

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

		dialog.show();
	}

}
