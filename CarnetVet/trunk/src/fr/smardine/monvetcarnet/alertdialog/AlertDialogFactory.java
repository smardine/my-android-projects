package fr.smardine.monvetcarnet.alertdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import fr.smardine.monvetcarnet.R;
import fr.smardine.monvetcarnet.database.accestable.AccesTableCarnet;
import fr.smardine.monvetcarnet.database.accestable.AccesTableIdentification;
import fr.smardine.monvetcarnet.fragment.CouvertureFragment;
import fr.smardine.monvetcarnet.fragment.IdentificationFragment;
import fr.smardine.monvetcarnet.mdl.EnGenre;
import fr.smardine.monvetcarnet.mdl.EnTypeAnimal;
import fr.smardine.monvetcarnet.mdl.MlCarnet;
import fr.smardine.monvetcarnet.mdl.MlIdentification;

public class AlertDialogFactory {

	public static void creerEtAfficherIdentificationDialog(final Context p_ctx, final CouvertureFragment fragmentCouverture,
			final IdentificationFragment fragmentIdentification) {
		final Dialog dialog = new Dialog(p_ctx);
		dialog.setContentView(R.layout.alertdialog_identification);
		dialog.setTitle("Identification");

		// set the custom dialog components - text, image and button
		final EditText etNomAnimal = (EditText) dialog.findViewById(R.id.etNomAnimal);
		final RadioButton rbChat = (RadioButton) dialog.findViewById(R.id.rbChat);
		final RadioButton rbChien = (RadioButton) dialog.findViewById(R.id.rbChien);
		// final DatePicker dpDateNaiss = (DatePicker) dialog.findViewById(R.id.datePicker1);
		final RadioButton rbFemelle = (RadioButton) dialog.findViewById(R.id.rbFemelle);
		final RadioButton rbMale = (RadioButton) dialog.findViewById(R.id.rbMale);

		// dpDateNaiss.setCalendarViewShown(false);
		// dpDateNaiss.setSpinnersShown(true);
		etNomAnimal.setText("");
		// ImageView image = (ImageView) dialog.findViewById(R.id.image);
		// image.setImageResource(R.drawable.ic_launcher);
		Button boutonOk = (Button) dialog.findViewById(R.id.btOk);
		Button boutonEffacer = (Button) dialog.findViewById(R.id.btEffacer);

		// if button is clicked, close the custom dialog
		boutonOk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MlCarnet unCarnet = new MlCarnet();
				MlIdentification uneIdentification = new MlIdentification(unCarnet);
				uneIdentification.setNomAnimal(etNomAnimal.getText().toString().trim());
				if (rbChat.isChecked()) {
					uneIdentification.setTypeAnimal(EnTypeAnimal.CHAT);
				}
				if (rbChien.isChecked()) {
					uneIdentification.setTypeAnimal(EnTypeAnimal.CHIEN);
				}

				// uneIdentification.setDateNaissance(new Date(dpDateNaiss.getText().toString()));
				;
				// uneIdentification.setDateNaissance(newFragment.recupereDate());
				if (rbMale.isChecked()) {
					uneIdentification.setGenreAnimal(EnGenre.MALE);
				}
				if (rbFemelle.isChecked()) {
					uneIdentification.setGenreAnimal(EnGenre.FEMELLE);
				}
				AccesTableIdentification accesIdent = new AccesTableIdentification(dialog.getContext());
				AccesTableCarnet accesCarnet = new AccesTableCarnet(dialog.getContext());
				accesCarnet.insertCarnetEnBase(unCarnet);
				uneIdentification.setIdCarnetParent(unCarnet.getId());
				accesIdent.insertIdentificationEnBase(uneIdentification);

				unCarnet.setIdentificationAnimal(uneIdentification);

				fragmentCouverture.metAjourCouverture(unCarnet);
				fragmentIdentification.metAjourIdentification(unCarnet);

				dialog.dismiss();
			}
		});

		boutonEffacer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				etNomAnimal.setText("");
				rbChat.setChecked(false);
				rbChien.setChecked(false);
				rbFemelle.setChecked(false);
				rbMale.setChecked(false);
			}
		});

		dialog.show();
	}

}
