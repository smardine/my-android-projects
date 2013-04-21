package fr.smardine.monvetcarnet.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import fr.smardine.monvetcarnet.MainActivity;
import fr.smardine.monvetcarnet.R;
import fr.smardine.monvetcarnet.alertdialog.AlertDialogFactory;
import fr.smardine.monvetcarnet.helper.DateHelper;
import fr.smardine.monvetcarnet.helper.EnStatutVisibilite;
import fr.smardine.monvetcarnet.helper.StringHelper;
import fr.smardine.monvetcarnet.listener.TextViewIdentificationListener;
import fr.smardine.monvetcarnet.mdl.MlCarnet;
import fr.smardine.monvetcarnet.mdl.MlIdentification;

public class IdentificationFragment extends SuperFragment {

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

	protected Object mActionMode;

	private TextView tvSigneDistinctifValue;

	private LinearLayout layoutSignesDistinctifs;

	private LinearLayout layoutNom;

	public IdentificationFragment() {
		super();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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

		tvSigneDistinctifValue = (TextView) v1.findViewById(R.id.tvSignesDistinctifsValue);

		layoutNom = (LinearLayout) v1.findViewById(R.id.layoutNomAnimal);
		layoutDateNaissance = (LinearLayout) v1.findViewById(R.id.layoutDateNaissance);
		layoutEleveur = (LinearLayout) v1.findViewById(R.id.layoutEleveur);
		layoutProp1 = (LinearLayout) v1.findViewById(R.id.layoutProp1);
		layoutProp2 = (LinearLayout) v1.findViewById(R.id.layoutProp2);
		layoutNumPuce = (LinearLayout) v1.findViewById(R.id.layoutNumpuce);
		layoutNumTatouage = (LinearLayout) v1.findViewById(R.id.layoutNumtatouage);
		layoutRace = (LinearLayout) v1.findViewById(R.id.layoutRace);
		layoutRobe = (LinearLayout) v1.findViewById(R.id.layoutRobe);
		layoutSexe = (LinearLayout) v1.findViewById(R.id.layoutSexe);
		layoutSignesDistinctifs = (LinearLayout) v1.findViewById(R.id.layoutSigneDistinctif);

		return v1;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState != null) {
			Toast.makeText(getActivity(), "Fragment Identification recréé", Toast.LENGTH_LONG).show();
		}
	}

	private void afficheLayoutEnFonctionContenu(LinearLayout p_layout, Object p_contenuNullable, TextView p_textView,
			String p_valeurAafficher) {
		if (p_contenuNullable == null || ((p_contenuNullable instanceof String) && StringHelper.IsNullOrEmpty((String) p_contenuNullable))) {
			p_layout.setVisibility(EnStatutVisibilite.RETIRE.getCode());
		} else {
			p_layout.setVisibility(EnStatutVisibilite.VISIBLE.getCode());
			p_textView.setText(p_valeurAafficher);
		}
	}

	public void metAjourIdentification(MlCarnet p_carnetParent) {
		if (p_carnetParent != null) {
			// this.carnetParent = p_carnetParent;
			instancieToutLesClickListenerDeLaPage(p_carnetParent);
			MlIdentification identification = p_carnetParent.getIdentificationAnimal();
			if (DateHelper.IsDateVideOuDateMini(identification.getDateNaissance())) {
				this.afficheLayoutEnFonctionContenu(layoutDateNaissance, null, null, null);
			} else {
				this.afficheLayoutEnFonctionContenu(layoutDateNaissance, identification.getDateNaissance(), tvDateNaissance,
						DateHelper.ddMMMyyyy(identification.getDateNaissance()));
			}

			if (StringHelper.IsNullOrEmpty(identification.getNomAnimal())) {
				this.afficheLayoutEnFonctionContenu(layoutNom, null, null, null);
			} else {
				this.afficheLayoutEnFonctionContenu(layoutNom, identification.getNomAnimal(), tvNomBestiole, identification.getNomAnimal());
			}

			if (identification.getGenreAnimal() != null && identification.getGenreAnimal().getType() != null) {
				this.afficheLayoutEnFonctionContenu(layoutSexe, identification.getGenreAnimal(), tvSexeEnum, identification
						.getGenreAnimal().getType());
			} else {
				this.afficheLayoutEnFonctionContenu(layoutSexe, null, null, null);
			}

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

				this.afficheLayoutEnFonctionContenu(layoutSignesDistinctifs, identification.getDetail().getSignesDistinctifs(),
						tvSigneDistinctifValue, identification.getDetail().getSignesDistinctifs());
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
				this.afficheLayoutEnFonctionContenu(layoutSignesDistinctifs, null, null, null);
			}
		}

	}

	private void instancieToutLesClickListenerDeLaPage(final MlCarnet p_carnetParent) {
		layoutNom.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialogFactory.creerEtAfficherSaisieNomAnimal(context, p_carnetParent, MainActivity.fragmentCouverture,
						MainActivity.fragmentIdentification);

			}
		});
		layoutSexe.setOnClickListener(new TextViewIdentificationListener(context, MainActivity.fragmentIdentification, p_carnetParent, 0));
		layoutDateNaissance.setOnClickListener(new TextViewIdentificationListener(context, MainActivity.fragmentIdentification,
				p_carnetParent, 1));
		layoutRace.setOnClickListener(new TextViewIdentificationListener(context, MainActivity.fragmentIdentification, p_carnetParent, 2));
		layoutRobe.setOnClickListener(new TextViewIdentificationListener(context, MainActivity.fragmentIdentification, p_carnetParent, 3));
		layoutSignesDistinctifs.setOnClickListener(new TextViewIdentificationListener(context, MainActivity.fragmentIdentification,
				p_carnetParent, 4));

		layoutNumPuce
				.setOnClickListener(new TextViewIdentificationListener(context, MainActivity.fragmentIdentification, p_carnetParent, 5));
		layoutNumTatouage.setOnClickListener(new TextViewIdentificationListener(context, MainActivity.fragmentIdentification,
				p_carnetParent, 6));

	}
}
