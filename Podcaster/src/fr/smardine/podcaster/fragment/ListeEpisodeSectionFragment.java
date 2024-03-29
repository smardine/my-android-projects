package fr.smardine.podcaster.fragment;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.adapter.EpisodeListAdapter;
import fr.smardine.podcaster.database.accestable.AccesTableFlux;
import fr.smardine.podcaster.mdl.MlFlux;
import fr.smardine.podcaster.mdl.MlListeEpisode;

/**
 * A dummy fragment representing a section of the app, but that simply displays dummy text.
 */
public class ListeEpisodeSectionFragment extends ListFragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";
	public static final String SELECTED_FLUX_ITEM = "selected_flux_item";
	private static final String LISTADAPTER = "episode_list_adapter";

	public ActionBar actionBar;
	public Context context;
	private EpisodeListAdapter adptEpisode;
	private MlFlux fluxSelectionne;

	public ListeEpisodeSectionFragment() {
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			adptEpisode = (EpisodeListAdapter) savedInstanceState.getSerializable(LISTADAPTER);
		} else {
			savedInstanceState = new Bundle();
			// MlFlux fluxSelectionne = ListeFluxSectionFragment.fluxSelectionne;// (MlFlux)
			// // getArguments().getSerializable(SELECTED_FLUX_ITEM);
			// //
			// if (fluxSelectionne == null) {
			// MlListeEpisode listeEpisode = new AccesTableFlux(context).getListeDesFlux().GetAllEpisode();
			// adptEpisode = new EpisodeListAdapter(getActivity(), listeEpisode);
			// actionBar.getTabAt(1).setText("Tous les episodes");
			// // adptEpisode.getFilter().filter(" ");
			// } else {
			// adptEpisode = new EpisodeListAdapter(getActivity(), fluxSelectionne.getListeEpisode());
			// actionBar.getTabAt(1).setText(fluxSelectionne.getTitre());
			// }

			// if (fluxSelectionne == null) {
			// adptEpisode.getFilter().filter(" ");
			// // listeEpisode = new AccesTableFlux(context).getListeDesFlux().GetAllEpisode();
			// } else {
			// // adptEpisode.getFilter().filter(fluxSelectionne.getTitre());
			// // listeEpisode = new AccesTableEpisode(context).getListeDesEpisodeParIdFlux(fluxSelectionne);
			// }

			savedInstanceState.putSerializable(LISTADAPTER, adptEpisode);
		}

	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		// int numeroDeTabActuel = getArguments().getInt(ARG_SECTION_NUMBER);
		View v1 = inflater.inflate(R.layout.activity_liste_episodes, container, false);
		// View v2 = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.activity_liste_episodes, null);
		// ListView listViewEpisode = (ListView) v1.findViewById(R.id.listViewTab1);

		// ImageButton boutonMajFlux = (ImageButton) v1.findViewById(R.id.imageButtonEpisode3);
		// View viewEpisodeListeItem = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.episodelistitem, null);

		// this.setListAdapter(adptEpisode);
		// parameter l'adapter sur la listview
		// listViewEpisode.setAdapter(adptEpisode);

		// boutonMajFlux.setOnClickListener(new ButtonMajFluxClickListener(this.getActivity(), viewEpisodeListeItem, listViewEpisode));

		return v1;

	}

	public void onViewStateRestored(Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState != null) {
			Toast.makeText(getActivity(), "Fragment Episode recree", Toast.LENGTH_LONG).show();
		}

		fluxSelectionne = ListeFluxSectionFragment.fluxSelectionne;// (MlFlux)
		// getArguments().getSerializable(SELECTED_FLUX_ITEM);

		if (fluxSelectionne == null) {
			MlListeEpisode listeEpisode = new AccesTableFlux(context).getListeDesFlux().GetAllEpisode();
			adptEpisode = new EpisodeListAdapter(getActivity(), listeEpisode);
			actionBar.getTabAt(1).setText("Tous les episodes");
			// adptEpisode.getFilter().filter(" ");
		} else {
			adptEpisode = new EpisodeListAdapter(getActivity(), fluxSelectionne.getListeEpisode());
			actionBar.getTabAt(1).setText(fluxSelectionne.getTitre());
		}
		this.setListAdapter(adptEpisode);
	}

	public void onStart() {
		super.onStart();
	}

	public void onResume() {
		super.onResume();
	}

	public void onPause() {
		super.onPause();
	}

	public void onStop() {
		super.onStop();
	}

	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putSerializable(LISTADAPTER, adptEpisode);
	}

	public void metAjoutListeEpisode(MlFlux fluxSelectionne) {
		if (fluxSelectionne != null) {
			this.fluxSelectionne = fluxSelectionne;
			this.adptEpisode.lstEpisodes = this.fluxSelectionne.getListeEpisode();
			this.adptEpisode.notifyDataSetChanged();
			this.actionBar.getTabAt(1).setText(this.fluxSelectionne.getTitre());
		}

	}
}
