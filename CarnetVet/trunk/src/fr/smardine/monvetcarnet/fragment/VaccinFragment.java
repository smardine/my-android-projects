package fr.smardine.monvetcarnet.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;
import fr.smardine.monvetcarnet.R;
import fr.smardine.monvetcarnet.adapter.VaccinsAdapter;

public class VaccinFragment extends Fragment implements OnItemSelectedListener {

	public Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// on est sur la page principale, on veut afficher la touche retour dans l'action bar

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v1 = inflater.inflate(R.layout.activity_vaccins, container, false);
		GridView choix = (GridView) v1.findViewById(R.id.listeVaccins);

		choix.setOnItemSelectedListener(this);
		choix.setAdapter(new VaccinsAdapter(this.getActivity(), R.layout.vaccin));// new ArrayAdapter<Semaine>(this,
																					// android.R.layout.simple_list_item_1,
		// Semaine.values()));

		return v1;

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
