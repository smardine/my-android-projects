package fr.smardine.podcaster;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.Toast;
import fr.smardine.podcaster.activity.SuperActivity;
import fr.smardine.podcaster.database.accestable.AccesTableFlux;
import fr.smardine.podcaster.fragment.ListeEpisodeSectionFragment;
import fr.smardine.podcaster.fragment.ListeFluxSectionFragment;
import fr.smardine.podcaster.helper.BitmapCache;
import fr.smardine.podcaster.helper.SauvegardeRestaurationBdd;

public class MainTabActivity extends SuperActivity implements ActionBar.TabListener {

	public static BitmapCache cacheVignette;

	/**
	 * The serialization (saved instance state) Bundle key representing the current tab position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	Context ctx = MainTabActivity.this;

	private ListeFluxSectionFragment fragmentFlux;

	private ListeEpisodeSectionFragment fragmentEpisode;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_tab);

		// Set up the action bar to show tabs.
		ActionBar actionBar = getActionBar();

		// si l'action bar est caché, il est impossible de naviguer avec des
		// onglet
		// actionBar.hide();

		fragmentFlux = new ListeFluxSectionFragment();
		fragmentEpisode = new ListeEpisodeSectionFragment();

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// For each of the sections in the app, add a tab to the action bar.
		actionBar.addTab(actionBar.newTab().setText(R.string.title_tab1).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.title_tab2).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.title_tab3).setTabListener(this));

		fragmentFlux.actionBar = actionBar;
		fragmentEpisode.actionBar = actionBar;

		AccesTableFlux tableFlux = new AccesTableFlux(getBaseContext());

		tableFlux.getNbEnregistrement();

	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current tab position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getActionBar().setSelectedNavigationItem(savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current tab position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar().getSelectedNavigationIndex());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_tab, menu);
		menu.getItem(0).setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				SauvegardeRestaurationBdd save = new SauvegardeRestaurationBdd(getApplicationContext());
				save.lanceSauvegardeSurCarteSD();
				return true;
			}
		});

		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, show the tab contents in the
		// container view.
		if (tab.getPosition() == 0) {
			// fragmentFlux = new ListeFluxSectionFragment();
			fragmentFlux.context = this.getApplicationContext();

			android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

			transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			if (fragmentFlux.isAdded()) {
				transaction.hide(fragmentEpisode);
				transaction.show(fragmentFlux);
			} else {
				// Bundle args = new Bundle();
				// args.putInt(ListeFluxSectionFragment.ARG_SECTION_NUMBER, tab.getPosition() + 1);
				// fragmentFlux.fluxSelectionne = null;
				// fragmentFlux.setArguments(args);
				// transaction.addToBackStack(tab.getPosition() + "stack_item");
				// transaction.add(fragmentFlux, "FRAGMENT_FLUX");
				// transaction.show(fragmentFlux);
				transaction.replace(R.id.container, fragmentFlux);
			}
			transaction.commitAllowingStateLoss();

		} else if (tab.getPosition() == 1) {
			fragmentEpisode.context = this.getApplicationContext();

			android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

			if (fragmentEpisode.isAdded()) {
				transaction.hide(fragmentFlux);
				transaction.show(fragmentEpisode);
			} else {
				// Bundle args = new Bundle();
				// args.putInt(ListeFluxSectionFragment.ARG_SECTION_NUMBER, tab.getPosition() + 1);
				// args.putSerializable(ListeFluxSectionFragment.SELECTED_FLUX_ITEM, fragmentFlux.fluxSelectionne);
				// fragmentEpisode.setArguments(args);
				// transaction.addToBackStack(tab.getPosition() + "stack_item");
				// transaction.add(fragmentEpisode, "FRAGMENT_EPISODE");
				// transaction.remove(fragmentFlux);
				// transaction.show(fragmentEpisode);
				transaction.replace(R.id.container, fragmentEpisode);
			}
			transaction.commitAllowingStateLoss();

		}
		// Fragment fragment = new ListeFluxSectionFragment();
		// ListeFluxSectionFragment.context = this.getApplicationContext();
		// Bundle args = new Bundle();
		// args.putInt(ListeFluxSectionFragment.ARG_SECTION_NUMBER, tab.getPosition() + 1);
		// if (tab.getPosition() == 0) {
		// // quand on revient sur la premiere "tab" on reinitialise le flux
		// // selectionné
		// ListeFluxSectionFragment.fluxSelectionne = null;
		// } else if (tab.getPosition() == 1) {
		// si on choisi la deuxieme "tab" (celle de liste d'épisode) on
		// passe le flux selectionné en tant que parametre
		// Pour que cela fonctionne, MlFlux implemente serializable, de meme
		// que toutes ses proprietés
		// args.putSerializable(ListeFluxSectionFragment.SELECTED_FLUX_ITEM, ListeFluxSectionFragment.fluxSelectionne);
		// }

	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		// Toast.makeText(ctx, "onTabUnselected", Toast.LENGTH_LONG).show();
		// android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		//
		// if (tab.getPosition() == 1) {// on est sur la tab "Episode"
		// transaction.remove(fragmentEpisode);
		// } else if (tab.getPosition() == 0) {// on est sur la tab "Flux"
		// transaction.remove(fragmentFlux);
		// }
		// transaction.commitAllowingStateLoss();
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		Toast.makeText(ctx, "onTabReselected", Toast.LENGTH_LONG).show();
	}

}
