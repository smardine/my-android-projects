package fr.smardine.podcaster;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import fr.smardine.podcaster.activity.SuperActivity;
import fr.smardine.podcaster.adapter.EpisodeListAdapter;
import fr.smardine.podcaster.adapter.FluxListAdapter;
import fr.smardine.podcaster.listener.ItemClickListenerTabListeFLux;
import fr.smardine.podcaster.mdl.MlFlux;
import fr.smardine.podcaster.mdl.MlListeEpisode;
import fr.smardine.podcaster.mdl.MlListeFlux;
import fr.smardine.podcaster.metier.RssFeeder;

public class MainTabActivity extends SuperActivity implements
		ActionBar.TabListener {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current tab position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	private static MlListeFlux listeFlux;
	private static ActionBar actionBar;
	public static OnItemClickListener itemclickListener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_tab);

		// Set up the action bar to show tabs.
		actionBar = getActionBar();

		// si l'action bar est caché, il est impossible de naviguer avec des
		// onglet
		// actionBar.hide();

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// For each of the sections in the app, add a tab to the action bar.
		actionBar.addTab(actionBar.newTab().setText(R.string.title_tab1)
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.title_tab2)
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.title_tab3)
				.setTabListener(this));

		if (itemclickListener == null) {
			itemclickListener = new ItemClickListenerTabListeFLux(actionBar);
		}
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current tab position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current tab position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
				.getSelectedNavigationIndex());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_tab, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, show the tab contents in the
		// container view.
		// RssFeeder feeder = new RssFeeder();
		// feeder.Test();

		Fragment fragment = new DummySectionFragment();
		Bundle args = new Bundle();
		args.putInt(DummySectionFragment.ARG_SECTION_NUMBER,
				tab.getPosition() + 1);

		fragment.setArguments(args);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.container, fragment).commit();
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	// private void transfereMlFluxToActivity(MlFlux unFluxATransferer) {
	// Intent intent = new Intent(this, FluxActivity.class);
	// intent.putExtra(MlFlux.class.getCanonicalName(),
	// SerialisableHelper.serialize(unFluxATransferer));
	//
	// intent.putExtra(ActivityParam.LaunchFromMainActivity, true);
	// startActivity(intent);
	// termineActivity();
	//
	// }

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
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
					listView.setOnItemClickListener(itemclickListener);
					// listView.setOnItemClickListener(new OnItemClickListener()
					// {
					//
					// @Override
					// public void onItemClick(AdapterView<?> p_adapterView,
					// View p_view, int p_position, long arg3) {
					// MlFlux leFluxClique = (MlFlux) p_adapterView
					// .getItemAtPosition(p_position);
					// fluxSelectionne = leFluxClique;
					// // En realité le numero de tab est en base 0
					// // si on à 3 tab, la deuxieme aura le numero 1
					// // 0,1,2
					// actionBar.setSelectedNavigationItem(1);
					//
					// }
					// });

					return v;
				case 2:
					View v1 = LayoutInflater.from(
							getActivity().getApplicationContext()).inflate(
							R.layout.activity_liste_episodes, null);

					ListView listViewEpisode = (ListView) v1
							.findViewById(R.id.listViewTabListeEpisode);
					MlListeEpisode listeEpisode = null;
					MlFlux fluxSelectionne = ((ItemClickListenerTabListeFLux) itemclickListener)
							.getFluxSelectionne();
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
