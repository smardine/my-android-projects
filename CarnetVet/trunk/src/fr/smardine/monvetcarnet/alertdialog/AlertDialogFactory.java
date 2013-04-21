package fr.smardine.monvetcarnet.alertdialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
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
import fr.smardine.monvetcarnet.helper.StringHelper;
import fr.smardine.monvetcarnet.listener.BtOkIdentificationSaisieClickListener;
import fr.smardine.monvetcarnet.listener.SpinnerIdentificationSaisieItemSelectedListener;
import fr.smardine.monvetcarnet.mdl.EnTypeAnimal;
import fr.smardine.monvetcarnet.mdl.MlCarnet;
import fr.smardine.monvetcarnet.mdl.MlIdentification;

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

	// public static void creerEtAfficherSaisieRobe(final Context p_ctx, final MlCarnet p_carnet,
	// final IdentificationFragment fragmentIdentification) {
	// final Dialog dialog = creerAlertDialogSaisieInfoTexte(p_ctx, "Robe");
	//
	// final EditText editText = recupererEditTextSaisie(dialog);
	// final Button boutonOk = recupererBoutonOk(dialog);
	// final Button boutonEffacer = recupererBoutonEffacer(dialog);
	//
	// if (!StringHelper.IsNullOrEmpty(p_carnet.getIdentificationAnimal().getDetail().getRobe())) {
	// editText.setText(p_carnet.getIdentificationAnimal().getDetail().getRobe());
	// }
	//
	// boutonOk.setOnClickListener(new OnClickListener() {
	// @Override
	// public void onClick(View v) {
	//
	// p_carnet.getIdentificationAnimal().getDetail().setRobe(editText.getText().toString().trim());
	//
	// AccesTableIdentification accesIdent = new AccesTableIdentification(dialog.getContext());
	//
	// accesIdent.majIdenificationEnBase(p_carnet.getIdentificationAnimal());
	//
	// fragmentIdentification.metAjourIdentification(p_carnet);
	// // fermer le dialog
	// dialog.dismiss();
	// }
	// });
	//
	// boutonEffacer.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// editText.setText("");
	// }
	// });
	//
	// dialog.show();
	//
	// }
	//
	// public static void creerEtAfficherSaisieRace(final Context p_ctx, final MlCarnet p_carnet,
	// final IdentificationFragment fragmentIdentification) {
	// final Dialog dialog = creerAlertDialogSaisieInfoTexte(p_ctx, "Race");
	//
	// final EditText editText = recupererEditTextSaisie(dialog);
	// final Button boutonOk = recupererBoutonOk(dialog);
	// final Button boutonEffacer = recupererBoutonEffacer(dialog);
	//
	// if (!StringHelper.IsNullOrEmpty(p_carnet.getIdentificationAnimal().getDetail().getRace())) {
	// editText.setText(p_carnet.getIdentificationAnimal().getDetail().getRace());
	// }
	//
	// boutonOk.setOnClickListener(new OnClickListener() {
	// @Override
	// public void onClick(View v) {
	//
	// p_carnet.getIdentificationAnimal().getDetail().setRace(editText.getText().toString().trim());
	//
	// AccesTableIdentification accesIdent = new AccesTableIdentification(dialog.getContext());
	//
	// accesIdent.majIdenificationEnBase(p_carnet.getIdentificationAnimal());
	//
	// fragmentIdentification.metAjourIdentification(p_carnet);
	// // fermer le dialog
	// dialog.dismiss();
	// }
	// });
	//
	// boutonEffacer.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// editText.setText("");
	// }
	// });
	//
	// dialog.show();
	// }
	//
	// public static void creerEtAfficherSaisieNumTatouage(final Context p_ctx, final MlCarnet p_carnet,
	// final IdentificationFragment fragmentIdentification) {
	// final Dialog dialog = creerAlertDialogSaisieInfoTexte(p_ctx, "N° tatouage");
	//
	// final EditText editText = recupererEditTextSaisie(dialog);
	// final Button boutonOk = recupererBoutonOk(dialog);
	// final Button boutonEffacer = recupererBoutonEffacer(dialog);
	//
	// if (!StringHelper.IsNullOrEmpty(p_carnet.getIdentificationAnimal().getDetail().getNumTatouage())) {
	// editText.setText(p_carnet.getIdentificationAnimal().getDetail().getNumTatouage());
	// }
	//
	// boutonOk.setOnClickListener(new OnClickListener() {
	// @Override
	// public void onClick(View v) {
	//
	// p_carnet.getIdentificationAnimal().getDetail().setNumTatouage(editText.getText().toString().trim());
	//
	// AccesTableIdentification accesIdent = new AccesTableIdentification(dialog.getContext());
	//
	// accesIdent.majIdenificationEnBase(p_carnet.getIdentificationAnimal());
	//
	// fragmentIdentification.metAjourIdentification(p_carnet);
	// // fermer le dialog
	// dialog.dismiss();
	// }
	// });
	//
	// boutonEffacer.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// editText.setText("");
	// }
	// });
	//
	// dialog.show();
	// }
	//
	// public static void creerEtAfficherSaisieNumPuce(final Context p_ctx, final MlCarnet p_carnet,
	// final IdentificationFragment fragmentIdentification) {
	// final Dialog dialog = creerAlertDialogSaisieInfoTexte(p_ctx, "N° puce");
	//
	// final EditText editText = recupererEditTextSaisie(dialog);
	// final Button boutonOk = recupererBoutonOk(dialog);
	// final Button boutonEffacer = recupererBoutonEffacer(dialog);
	//
	// if (!StringHelper.IsNullOrEmpty(p_carnet.getIdentificationAnimal().getDetail().getNumPuce())) {
	// editText.setText(p_carnet.getIdentificationAnimal().getDetail().getNumPuce());
	// }
	//
	// boutonOk.setOnClickListener(new OnClickListener() {
	// @Override
	// public void onClick(View v) {
	//
	// p_carnet.getIdentificationAnimal().getDetail().setNumPuce(editText.getText().toString().trim());
	//
	// AccesTableIdentification accesIdent = new AccesTableIdentification(dialog.getContext());
	//
	// accesIdent.majIdenificationEnBase(p_carnet.getIdentificationAnimal());
	//
	// fragmentIdentification.metAjourIdentification(p_carnet);
	// // fermer le dialog
	// dialog.dismiss();
	// }
	// });
	//
	// boutonEffacer.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// editText.setText("");
	// }
	// });
	//
	// dialog.show();
	// }
	//
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
}
