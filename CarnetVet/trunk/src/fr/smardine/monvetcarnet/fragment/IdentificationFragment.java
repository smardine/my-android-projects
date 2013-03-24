package fr.smardine.monvetcarnet.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import fr.smardine.monvetcarnet.R;
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

	public void metAjourIdentification(MlCarnet p_carnetParent) {
		if (p_carnetParent != null) {
			this.carnetParent = p_carnetParent;
			MlIdentification identification = this.carnetParent.getIdentificationAnimal();
			this.tvDateNaissance.setText(DateHelper.ddMMMyyyy(identification.getDateNaissance()));
			if (identification.getDetail() != null) {
				if (identification.getDetail().getEleveur() != null) {
					this.tvEleveurValue.setText(identification.getDetail().getEleveur().getNom());
					this.tvEleveurValue.setVisibility(EnStatutVisibilite.VISIBLE.getCode());
				} else {
					this.tvEleveurValue.setVisibility(EnStatutVisibilite.RETIRE.getCode());
				}
				if (identification.getDetail().getProprietaire1() != null) {
					this.tvProp1Value.setText(identification.getDetail().getProprietaire1().getNom());
					this.tvProp1Value.setVisibility(EnStatutVisibilite.VISIBLE.getCode());
				} else {
					this.tvProp1Value.setVisibility(EnStatutVisibilite.RETIRE.getCode());
				}
				if (identification.getDetail().getProprietaire2() != null) {
					this.tvProp2Value.setText(identification.getDetail().getProprietaire2().getNom());
					this.tvProp2Value.setVisibility(EnStatutVisibilite.VISIBLE.getCode());
				} else {
					this.tvProp2Value.setVisibility(EnStatutVisibilite.RETIRE.getCode());
				}
				if (identification.getDetail().getNumPuce() != null) {
					this.tvNumPuceValue.setText(identification.getDetail().getNumPuce());
					this.tvNumPuceValue.setVisibility(EnStatutVisibilite.VISIBLE.getCode());
				} else {
					this.tvNumPuceValue.setVisibility(EnStatutVisibilite.RETIRE.getCode());
				}
				if (identification.getDetail().getNumTatouage() != null) {
					this.tvNumTatouageValue.setText(identification.getDetail().getNumTatouage());
					this.tvNumTatouageValue.setVisibility(EnStatutVisibilite.VISIBLE.getCode());
				} else {
					this.tvNumTatouageValue.setVisibility(EnStatutVisibilite.RETIRE.getCode());
				}
				if (identification.getDetail().getRace() != null) {
					this.tvRaceValue.setText(identification.getDetail().getRace());
					this.tvRaceValue.setVisibility(EnStatutVisibilite.VISIBLE.getCode());
				} else {
					this.tvRaceValue.setVisibility(EnStatutVisibilite.RETIRE.getCode());
				}
				if (identification.getDetail().getRobe() != null) {
					this.tvRobeValue.setText(identification.getDetail().getRobe());
					this.tvRobeValue.setVisibility(EnStatutVisibilite.VISIBLE.getCode());
				} else {
					this.tvRobeValue.setVisibility(EnStatutVisibilite.RETIRE.getCode());
				}
			}
			this.tvNomBestiole.setText(identification.getNomAnimal());
			this.tvSexeEnum.setText(identification.getGenreAnimal().getType());

		}

	}
}
