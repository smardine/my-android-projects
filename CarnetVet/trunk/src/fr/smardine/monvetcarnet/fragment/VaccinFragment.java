package fr.smardine.monvetcarnet.fragment;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import fr.smardine.monvetcarnet.R;
import fr.smardine.monvetcarnet.adapter.VaccinsAdapter;
import fr.smardine.monvetcarnet.alertdialog.AlertDialogFactory;
import fr.smardine.monvetcarnet.database.accestable.AccesTableCarnet;
import fr.smardine.monvetcarnet.database.accestable.AccesTableVaccin;
import fr.smardine.monvetcarnet.mdl.MlCarnet;
import fr.smardine.monvetcarnet.mdl.MlVaccin;

public class VaccinFragment extends SuperFragment implements OnItemClickListener {

	private GridView choix;
	private MlCarnet carnetParent;

	public VaccinFragment() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v1 = inflater.inflate(R.layout.activity_vaccins, container, false);
		choix = (GridView) v1.findViewById(R.id.listeVaccins);

		choix.setOnItemClickListener(this);

		return v1;

	}

	/**
	 * Evenement lancé une fois l'activity créée
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		AccesTableCarnet tableCarnet = new AccesTableCarnet(context);
		List<MlCarnet> listeDeCarnet = tableCarnet.getListeDesCarnets();
		if (listeDeCarnet != null && listeDeCarnet.size() > 0) {
			carnetParent = listeDeCarnet.get(0);
			choix.setAdapter(new VaccinsAdapter(context, carnetParent.getListeDeVaccins())); // R.layout.vaccin
		}

	}

	/**
	 * Met a jour la liste des vaccins sur la grille
	 * @param p_carnetParent
	 */
	public void metAjourListeDeVaccin(MlCarnet p_carnetParent) {
		if (p_carnetParent != null) {
			List<MlVaccin> listeDeVaccin = new AccesTableVaccin(this.context).getListeDeVaccinsParIdCarnet(p_carnetParent);
			if (listeDeVaccin != null && listeDeVaccin.size() > 0) {
				choix.setAdapter(new VaccinsAdapter(context, listeDeVaccin));
			}
		}
	}

	/**
	 * Evenement lancé lorsque l'utilisateur clique sur un des vaccins de la grille
	 */
	@Override
	public void onItemClick(AdapterView<?> p_adapterView, View p_view, int p_position, long p_itemId) {
		MlVaccin vaccinSelectionne = (MlVaccin) choix.getItemAtPosition(p_position);
		AlertDialogFactory.creerEtAfficherVaccinConsultation(context, this, carnetParent, vaccinSelectionne);
	}

}
