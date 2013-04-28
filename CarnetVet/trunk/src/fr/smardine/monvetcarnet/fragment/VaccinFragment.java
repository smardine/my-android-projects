package fr.smardine.monvetcarnet.fragment;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;
import fr.smardine.monvetcarnet.R;
import fr.smardine.monvetcarnet.adapter.VaccinsAdapter;
import fr.smardine.monvetcarnet.database.accestable.AccesTableCarnet;
import fr.smardine.monvetcarnet.database.accestable.AccesTableVaccin;
import fr.smardine.monvetcarnet.mdl.MlCarnet;
import fr.smardine.monvetcarnet.mdl.MlVaccin;

public class VaccinFragment extends SuperFragment implements OnItemSelectedListener {

	private GridView choix;

	public VaccinFragment() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v1 = inflater.inflate(R.layout.activity_vaccins, container, false);
		choix = (GridView) v1.findViewById(R.id.listeVaccins);

		choix.setOnItemSelectedListener(this);

		return v1;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		AccesTableCarnet tableCarnet = new AccesTableCarnet(context);
		List<MlCarnet> listeDeCarnet = tableCarnet.getListeDesCarnets();
		if (listeDeCarnet != null && listeDeCarnet.size() > 0) {
			choix.setAdapter(new VaccinsAdapter(context, listeDeCarnet.get(0).getListeDeVaccins())); // R.layout.vaccin
		}

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	public void metAjourListeDeVaccin(MlCarnet p_carnetParent) {
		if (p_carnetParent != null) {
			List<MlVaccin> listeDeVaccin = new AccesTableVaccin(this.context).getListeDeVaccinsParIdCarnet(p_carnetParent);
			if (listeDeVaccin != null && listeDeVaccin.size() > 0) {
				// ((VaccinsAdapter) choix.getAdapter()).listeDeVaccin = listeDeVaccin;
				// ((VaccinsAdapter) choix.getAdapter()).notifyDataSetChanged();
				choix.setAdapter(new VaccinsAdapter(context, listeDeVaccin));
			}
		}
	}
}
