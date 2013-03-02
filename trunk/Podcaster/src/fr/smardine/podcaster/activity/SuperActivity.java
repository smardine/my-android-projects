package fr.smardine.podcaster.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.adapter.EpisodeListAdapter;
import fr.smardine.podcaster.adapter.FluxListAdapter;
import fr.smardine.podcaster.database.accestable.AccesTableEpisode;
import fr.smardine.podcaster.database.accestable.AccesTableFlux;
import fr.smardine.podcaster.listener.ButtonAjoutFluxClickListener;
import fr.smardine.podcaster.listener.ButtonMajFluxClickListener;
import fr.smardine.podcaster.mdl.MlFlux;
import fr.smardine.podcaster.mdl.MlListeEpisode;
import fr.smardine.podcaster.thread.MajFluxProgressDialog;

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
	 * Exécuté lorsque l'activité devient visible à l'utilisateur. La fonction onStart() est suivie de la fonction onResume().
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
	 * La fonction onPause() est suivie : - d'un onResume() si l'activité passe à nouveau en premier plan - d'un onStop() si elle devient
	 * invisible à l'utilisateur L'exécution de la fonction onPause() doit être rapide, car la prochaine activité ne démarrera pas tant que
	 * l'exécution de la fonction onPause() n'est pas terminée.
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
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply displays dummy text.
	 */
	public static class ListeFluxSectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";
		public static final String SELECTED_FLUX_ITEM = "selected_flux_item";
		// public static MlListeFlux listeFlux = null;
		public static MlFlux fluxSelectionne;
		public static ActionBar actionBar;
		public static Context context;

		public ListeFluxSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

			int numeroDeTabActuel = getArguments().getInt(ARG_SECTION_NUMBER);

			switch (numeroDeTabActuel) {
				case 1:

					View v = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.activity_tab1, null);
					final ListView listView = (ListView) v.findViewById(R.id.listViewTab1);

					ImageButton boutonAjouteFlux = (ImageButton) v.findViewById(R.id.imageButton3);
					ImageButton boutonMajListeFlux = (ImageButton) v.findViewById(R.id.imageButton2);
					View viewFluxListItem = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.fluxlistitem, null);

					boutonAjouteFlux.setOnClickListener(new ButtonAjoutFluxClickListener(this.getActivity(), viewFluxListItem, listView));

					final AccesTableFlux tableFlux = new AccesTableFlux(context);
					// final MlListeFlux listeFluxEnBase = tableFlux.getListeDesFlux();

					FluxListAdapter adpt = new FluxListAdapter(getActivity(), tableFlux.getListeDesFlux());
					// ListeFluxSectionFragment.listeFlux = listeFluxEnBase;
					// paramèter l'adapter sur la listview
					listView.setAdapter(adpt);

					listView.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> p_adapterView, View p_view, int p_position, long arg3) {
							MlFlux leFluxClique = (MlFlux) p_adapterView.getItemAtPosition(p_position);
							fluxSelectionne = leFluxClique;
							// En realité le numero de tab est en base 0
							// si on à 3 tab, la deuxieme aura le numero 1
							// 0,1,2
							actionBar.setSelectedNavigationItem(1);

						}

					});

					boutonMajListeFlux.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {

							MajFluxProgressDialog majFlux = new MajFluxProgressDialog();
							majFlux.synchroMajFluxProgressDialog(getActivity(), tableFlux.getListeDesFlux(), listView);

						}
					});

					listView.setOnItemLongClickListener(new OnItemLongClickListener() {

						@Override
						public boolean onItemLongClick(AdapterView<?> p_adapterView, View p_view, int p_position, long arg3) {
							final MlFlux leFluxClique = (MlFlux) p_adapterView.getItemAtPosition(p_position);
							fluxSelectionne = leFluxClique;
							PopupMenu popup = new PopupMenu(context, p_view);
							MenuInflater inflater = popup.getMenuInflater();
							inflater.inflate(R.menu.menu_long_click_flux, popup.getMenu());
							popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {

								@Override
								public boolean onMenuItemClick(MenuItem p_item) {
									switch (p_item.getItemId()) {
										case R.id.menu_suppr_flux:
											AccesTableFlux tableFlux = new AccesTableFlux(context);
											tableFlux.deleteFluxEtEpisode(fluxSelectionne);
											return true;
										case R.id.menu_maj_flux:
											MajFluxProgressDialog majFlux = new MajFluxProgressDialog();
											List<MlFlux> lst = new ArrayList<MlFlux>();
											lst.add(leFluxClique);
											majFlux.synchroMajFluxProgressDialog(getActivity(), lst, listView);
											return true;
										default:
											return false;
									}
								}
							});
							popup.show();
							return true;
						}
					});

					return v;
				case 2:
					View v2 = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.activity_liste_episodes, null);
					ListView listViewEpisode = (ListView) v2.findViewById(R.id.listViewTabListeEpisode);

					ImageButton boutonMajFlux = (ImageButton) v2.findViewById(R.id.imageButtonEpisode3);
					View viewEpisodeListeItem = LayoutInflater.from(getActivity().getApplicationContext()).inflate(
							R.layout.episodelistitem, null);

					MlListeEpisode listeEpisode = null;
					MlFlux fluxSelectionne = (MlFlux) getArguments().getSerializable(SELECTED_FLUX_ITEM);

					if (fluxSelectionne == null) {
						listeEpisode = new AccesTableFlux(context).getListeDesFlux().GetAllEpisode();
					} else {
						listeEpisode = new AccesTableEpisode(context).getListeDesEpisodeParIdFlux(fluxSelectionne);
					}
					EpisodeListAdapter adptEpisode = new EpisodeListAdapter(getActivity(), listeEpisode);
					// paramèter l'adapter sur la listview
					listViewEpisode.setAdapter(adptEpisode);

					boutonMajFlux.setOnClickListener(new ButtonMajFluxClickListener(this.getActivity(), viewEpisodeListeItem,
							listViewEpisode));

					return v2;

				default:
					throw new RuntimeException("Il y a une erreur de programmation, le numero d'onglet n'est pas geré");
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
