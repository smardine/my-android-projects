package fr.smardine.monvetcarnet.fragment;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import fr.smardine.monvetcarnet.R;
import fr.smardine.monvetcarnet.database.accestable.AccesTableIdentification;
import fr.smardine.monvetcarnet.helper.DateHelper;
import fr.smardine.monvetcarnet.helper.EnStatutVisibilite;
import fr.smardine.monvetcarnet.mdl.MlCarnet;
import fr.smardine.monvetcarnet.mdl.MlIdentification;

public class IdentificationFragment extends Fragment {

	public Context context;

	private MlCarnet carnetParent;

	private TextView tvNomBestiole;

	private TextView tvDateNaissance;

	private TextView tvSexeEnum;

	private TextView tvRaceValue;

	private TextView tvRobeValue;

	private TextView tvNumTatouageValue;

	private TextView tvNumPuceValue;

	private TextView tvProp1Value;

	private TextView tvProp2Value;

	private TextView tvEleveurValue;

	private LinearLayout layoutDateNaissance;

	private LinearLayout layoutEleveur;

	private LinearLayout layoutProp1;

	private LinearLayout layoutProp2;

	private LinearLayout layoutNumPuce;

	private LinearLayout layoutNumTatouage;

	private LinearLayout layoutRace;

	private LinearLayout layoutRobe;

	private LinearLayout layoutSexe;

	public IdentificationFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// on est sur la page principale, on veut afficher la touche retour dans l'action bar

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v1 = inflater.inflate(R.layout.activity_identification, container, false);
		tvNomBestiole = (TextView) v1.findViewById(R.id.tvNom);
		tvDateNaissance = (TextView) v1.findViewById(R.id.tvDateNaiss);
		tvSexeEnum = (TextView) v1.findViewById(R.id.tvSexeEnum);
		tvRaceValue = (TextView) v1.findViewById(R.id.tvRaceValue);
		tvRobeValue = (TextView) v1.findViewById(R.id.tvRobeValue);
		tvNumTatouageValue = (TextView) v1.findViewById(R.id.tvNumTatouageValue);
		tvNumPuceValue = (TextView) v1.findViewById(R.id.tvNumPuceValue);
		tvProp1Value = (TextView) v1.findViewById(R.id.tvProp1Value);
		tvProp2Value = (TextView) v1.findViewById(R.id.tvProp2Value);
		tvEleveurValue = (TextView) v1.findViewById(R.id.tvEleveurValue);
		layoutDateNaissance = (LinearLayout) v1.findViewById(R.id.layoutDateNaissance);
		layoutEleveur = (LinearLayout) v1.findViewById(R.id.layoutEleveur);
		layoutProp1 = (LinearLayout) v1.findViewById(R.id.layoutProp1);
		layoutProp2 = (LinearLayout) v1.findViewById(R.id.layoutProp2);
		layoutNumPuce = (LinearLayout) v1.findViewById(R.id.layoutNumpuce);
		layoutNumTatouage = (LinearLayout) v1.findViewById(R.id.layoutNumtatouage);
		layoutRace = (LinearLayout) v1.findViewById(R.id.layoutRace);
		layoutRobe = (LinearLayout) v1.findViewById(R.id.layoutRobe);
		layoutSexe = (LinearLayout) v1.findViewById(R.id.layoutSexe);

		return v1;

	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState != null) {
			Toast.makeText(getActivity(), "Fragment Identification recréé", Toast.LENGTH_LONG).show();
		}

		tvDateNaissance.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDatePickerDialog(v);
			}
		});
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		((DatePickerFragment) newFragment).setButtonSpinner(this.tvDateNaissance);
		((DatePickerFragment) newFragment).setMlIdentification(this.carnetParent.getIdentificationAnimal());
		AccesTableIdentification tableIdent = new AccesTableIdentification(context);
		((DatePickerFragment) newFragment).setTableIdentifiaction(tableIdent);
		newFragment.show(this.getActivity().getFragmentManager(), "Date");
	}

	private void afficheLayoutEnFonctionContenu(LinearLayout p_layout, Object p_contenuNullable, TextView p_textView,
			String p_valeurAafficher) {
		if (p_contenuNullable == null) {
			p_layout.setVisibility(EnStatutVisibilite.RETIRE.getCode());
		} else {
			p_layout.setVisibility(EnStatutVisibilite.VISIBLE.getCode());
			p_textView.setText(p_valeurAafficher);
		}
	}

	public void metAjourIdentification(MlCarnet p_carnetParent) {
		if (p_carnetParent != null) {
			this.carnetParent = p_carnetParent;
			MlIdentification identification = this.carnetParent.getIdentificationAnimal();
			this.afficheLayoutEnFonctionContenu(layoutDateNaissance, identification.getDateNaissance(), tvDateNaissance,
					DateHelper.ddMMMyyyy(identification.getDateNaissance()));
			this.afficheLayoutEnFonctionContenu(layoutSexe, identification.getGenreAnimal(), tvSexeEnum, identification.getGenreAnimal()
					.getType());

			if (identification.getDetail() != null) {
				if (identification.getDetail().getEleveur() != null) {
					this.afficheLayoutEnFonctionContenu(layoutEleveur, identification.getDetail().getEleveur(), tvEleveurValue,
							identification.getDetail().getEleveur().getNom());
				} else {
					this.afficheLayoutEnFonctionContenu(layoutEleveur, null, null, null);
				}
				if (identification.getDetail().getProprietaire1() != null) {
					this.afficheLayoutEnFonctionContenu(layoutProp1, identification.getDetail().getProprietaire1(), tvProp1Value,
							identification.getDetail().getProprietaire1().getNom());
				} else {
					this.afficheLayoutEnFonctionContenu(layoutProp1, null, null, null);
				}
				if (identification.getDetail().getProprietaire2() != null) {
					this.afficheLayoutEnFonctionContenu(layoutProp2, identification.getDetail().getProprietaire2(), tvProp2Value,
							identification.getDetail().getProprietaire2().getNom());
				} else {
					this.afficheLayoutEnFonctionContenu(layoutProp2, null, null, null);
				}

				this.afficheLayoutEnFonctionContenu(layoutNumPuce, identification.getDetail().getNumPuce(), tvNumPuceValue, identification
						.getDetail().getNumPuce());
				this.afficheLayoutEnFonctionContenu(layoutNumTatouage, identification.getDetail().getNumTatouage(), tvNumTatouageValue,
						identification.getDetail().getNumTatouage());
				this.afficheLayoutEnFonctionContenu(layoutRace, identification.getDetail().getRace(), tvRaceValue, identification
						.getDetail().getRace());
				this.afficheLayoutEnFonctionContenu(layoutRobe, identification.getDetail().getRobe(), tvRobeValue, identification
						.getDetail().getRobe());
			} else {
				this.afficheLayoutEnFonctionContenu(layoutEleveur, null, null, null);
				this.afficheLayoutEnFonctionContenu(layoutProp1, null, null, null);
				this.afficheLayoutEnFonctionContenu(layoutProp2, null, null, null);
				this.afficheLayoutEnFonctionContenu(layoutNumPuce, null, null, null);
				this.afficheLayoutEnFonctionContenu(layoutNumTatouage, null, null, null);
				this.afficheLayoutEnFonctionContenu(layoutRace, null, null, null);
				this.afficheLayoutEnFonctionContenu(layoutRobe, null, null, null);
			}

			this.tvNomBestiole.setText(identification.getNomAnimal());

		}

	}
}
