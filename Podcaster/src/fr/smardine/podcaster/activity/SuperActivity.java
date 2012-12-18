package fr.smardine.podcaster.activity;

import fr.smardine.podcaster.R;
import fr.smardine.podcaster.adapter.EpisodeListAdapter;
import fr.smardine.podcaster.adapter.FluxListAdapter;
import fr.smardine.podcaster.mdl.MlFlux;
import fr.smardine.podcaster.mdl.MlListeEpisode;
import fr.smardine.podcaster.mdl.MlListeFlux;
import fr.smardine.podcaster.metier.RssFeeder;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class SuperActivity extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onRestart() {
		super.onRestart();
		popUp("onRestart()");
	}

	/**
	 * Exécuté lorsque l'activité devient visible à l'utilisateur. La fonction
	 * onStart() est suivie de la fonction onResume().
	 */
	@Override
	public void onStart() {
		super.onStart();
		popUp("onStart()");

	}

	/**
	 * detruit l'activity
	 */
	@Override
	public void onDestroy() {
		popUp("OnDestroy");
		super.onDestroy();

	}

	@Override
	public void onStop() {
		super.onStop();
		popUp("onStop");
	}

	/**
	 * La fonction onPause() est suivie : - d'un onResume() si l'activité passe
	 * à nouveau en premier plan - d'un onStop() si elle devient invisible à
	 * l'utilisateur L'exécution de la fonction onPause() doit être rapide, car
	 * la prochaine activité ne démarrera pas tant que l'exécution de la
	 * fonction onPause() n'est pas terminée.
	 */
	@Override
	public void onPause() {
		super.onPause();

	}

	@Override
	public void onResume() {
		super.onResume();

	}

	public void termineActivity() {
		finish();
	}

	/**
	 * @param message
	 */
	public void popUp(String message) {
		 Toast.makeText(this, message, 1).show();
	}
	
	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class ListeFluxSectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";
		public static final String SELECTED_FLUX_ITEM = "selected_flux_item";
		public static MlListeFlux listeFlux=null;
		public static MlFlux fluxSelectionne;
		public static ActionBar actionBar;

		public ListeFluxSectionFragment() {
		}
		

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				final Bundle savedInstanceState) {

			int numeroDeTabActuel = getArguments().getInt(ARG_SECTION_NUMBER);

			switch (numeroDeTabActuel) {
				case 1:
					View v = LayoutInflater.from(
							getActivity().getApplicationContext()).inflate(
							R.layout.activity_tab1, null);

					ListView listView = (ListView) v
							.findViewById(R.id.listViewTab1);

					// constitution d'un flux de test
					RssFeeder feeder = new RssFeeder();

					listeFlux = feeder.Test();

					FluxListAdapter adpt = new FluxListAdapter(getActivity(),
							listeFlux);
					// paramèter l'adapter sur la listview
					listView.setAdapter(adpt);
					//listView.setOnItemClickListener(itemclickListener);
					 listView.setOnItemClickListener(new OnItemClickListener()
					 {
						

						@Override
						public void onItemClick(AdapterView<?> p_adapterView,
								 View p_view, int p_position, long arg3) {
							 MlFlux leFluxClique = (MlFlux) p_adapterView
									 .getItemAtPosition(p_position);
									 fluxSelectionne = leFluxClique;
									 // En realité le numero de tab est en base 0
									 // si on à 3 tab, la deuxieme aura le numero 1
									 // 0,1,2
									 actionBar.setSelectedNavigationItem(1);
							
						}
					 });

					return v;
				case 2:
					View v1 = LayoutInflater.from(
							getActivity().getApplicationContext()).inflate(
							R.layout.activity_liste_episodes, null);

					ListView listViewEpisode = (ListView) v1
							.findViewById(R.id.listViewTabListeEpisode);
					MlListeEpisode listeEpisode = null;
					MlFlux fluxSelectionne = (MlFlux) getArguments().getSerializable(SELECTED_FLUX_ITEM); 
//							((ItemClickListenerTabListeFLux) itemclickListener)
//							.getFluxSelectionne();
					if (fluxSelectionne == null) {
						listeEpisode = listeFlux.GetAllEpisode();
					} else {
						listeEpisode = fluxSelectionne.getListeEpisode();
					}
					EpisodeListAdapter adptEpisode = new EpisodeListAdapter(
							getActivity(), listeEpisode);
					// paramèter l'adapter sur la listview
					listViewEpisode.setAdapter(adptEpisode);

					return v1;

				default:
					throw new RuntimeException(
							"Il y a une erreur de programmation, le numero d'onglet n'est pas geré");
			}

			// TextView textView = new TextView(getActivity());
			// textView.setGravity(Gravity.CENTER);
			// textView.setText(Integer.toString(getArguments().getInt(
			// ARG_SECTION_NUMBER)));
			// int numeroDeTab = getArguments().getInt(ARG_SECTION_NUMBER);
			// return textView;
		}
	}

}
