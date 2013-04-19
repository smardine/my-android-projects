package fr.smardine.monvetcarnet.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Cette classe est la classe mere de tout les fragments. Ils doivent obligatoirement l'etendre pour pouvoir recuperer la proprietée
 * protegée "context" qui permet d'afficher les alertDialog et egalement de generer les actions en base.
 * @author sims
 */
public class SuperFragment extends Fragment {

	/**
	 * Proprieté protegée accessible uniquement aux enfants
	 */
	protected Context context;

	/**
	 * Constructeur
	 */
	public SuperFragment() {
		this.context = this.getActivity();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
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

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

}
