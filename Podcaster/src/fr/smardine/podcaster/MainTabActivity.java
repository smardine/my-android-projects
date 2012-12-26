package fr.smardine.podcaster;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import fr.smardine.podcaster.activity.SuperActivity;

public class MainTabActivity extends SuperActivity implements
		ActionBar.TabListener {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current tab position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_tab);

		// Set up the action bar to show tabs.
		ActionBar actionBar = getActionBar();

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

		ListeFluxSectionFragment.actionBar = actionBar;
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

		Fragment fragment = new ListeFluxSectionFragment();
		ListeFluxSectionFragment.context = this.getApplicationContext();
		Bundle args = new Bundle();
		args.putInt(ListeFluxSectionFragment.ARG_SECTION_NUMBER,
				tab.getPosition() + 1);
		if (tab.getPosition() == 0) {
			// quand on revient sur la premiere "tab" on reinitialise le flux
			// selectionné
			ListeFluxSectionFragment.fluxSelectionne = null;
		} else if (tab.getPosition() == 1) {
			// si on choisi la deuxieme "tab" (celle de liste d'épisode) on
			// passe le flux selectionné en tant que parametre
			// Pour que cela fonctionne, MlFlux implemente serializable, de meme
			// que toutes ses proprietés
			args.putSerializable(ListeFluxSectionFragment.SELECTED_FLUX_ITEM,
					ListeFluxSectionFragment.fluxSelectionne);
		}

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

}
