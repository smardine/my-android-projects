package fr.smardine.podcaster;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.Toast;
import fr.smardine.podcaster.database.accestable.AccesTableFlux;
import fr.smardine.podcaster.fragment.ListeEpisodeSectionFragment;
import fr.smardine.podcaster.fragment.ListeFluxSectionFragment;
import fr.smardine.podcaster.helper.BitmapCache;
import fr.smardine.podcaster.helper.SauvegardeRestaurationBdd;

public class MainTabActivity extends FragmentActivity implements ActionBar.TabListener {

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

		// Les deux fragment se trouve dans le layout principal (activity_main_tab)
		setContentView(R.layout.activity_main_tab);
		// recherche des fragments a partir de leur ID via le fragment manager
		fragmentFlux = (ListeFluxSectionFragment) getSupportFragmentManager().findFragmentById(R.id.fluxFragment);
		fragmentEpisode = (ListeEpisodeSectionFragment) getSupportFragmentManager().findFragmentById(R.id.episodeFragment);
		// valorisation de leurs proprietes
		fragmentFlux.context = this.getApplicationContext();
		fragmentEpisode.context = this.getApplicationContext();
		// Initialisation de l'action bar
		ActionBar actionBar = getActionBar();

		// si l'action bar est cache, il est impossible de naviguer avec des
		// onglet
		// actionBar.hide();

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// For each of the sections in the app, add a tab to the action bar.
		actionBar.addTab(actionBar.newTab().setText(R.string.title_tab1).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.title_tab2).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.title_tab3).setTabListener(this));
		actionBar.setTitle("");
		actionBar.setIcon(null);
		actionBar.setDisplayUseLogoEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);

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

			// Ouverture d'une transaction pour les fragment
			android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			// effet visuel lors de l'ouverture du fragment
			transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
			// ici, le fragment est deja ajoute car il a ete charge lors de la creation de l'activity
			// lorsque l'on a appele la methode "setContentView(R.layout.activity_main_tab);" dans le "onCreate"
			if (fragmentFlux.isAdded()) {
				// on cache le fragment "Episode"
				transaction.hide(fragmentEpisode);
				// et on affiche le fragment "Flux"
				transaction.show(fragmentFlux);
			} else {
				transaction.addToBackStack(tab.getPosition() + "stack_item");
				transaction.add(fragmentFlux, tab.getPosition() + ListeFluxSectionFragment.ARG_SECTION_NUMBER);
				transaction.show(fragmentFlux);
			}
			// Ne pas oublier de faire un "commit" sur la transaction
			transaction.commitAllowingStateLoss();

		} else if (tab.getPosition() == 1) {
			// fragmentEpisode.context = this.getApplicationContext();
			// on ouvre une trasaction sur le fragment
			android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.setTransition(FragmentTransaction.TRANSIT_NONE);

			if (fragmentEpisode.isAdded()) {
				transaction.hide(fragmentFlux);
				transaction.show(fragmentEpisode);
			} else {
				// permet de "stocker" la transaction dans une "pile" pour etre re-exploiter lorsque l'utilisateur clique
				// sur la touche "retour" de son telephone
				transaction.addToBackStack(tab.getPosition() + "stack_item");
				transaction.add(fragmentEpisode, tab.getPosition() + ListeEpisodeSectionFragment.ARG_SECTION_NUMBER);
				transaction.show(fragmentEpisode);
			}
			// Ne pas oublier de faire un "commit" sur la transaction
			transaction.commitAllowingStateLoss();
			// on met a jour la liste des episodes via le flux clique par l'utilisateur
			fragmentEpisode.metAjoutListeEpisode(ListeFluxSectionFragment.fluxSelectionne);

		}

	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

		if (tab.getPosition() == 1) {// on etait sur la tab "Episode"
			ListeFluxSectionFragment.fluxSelectionne = null;
			tab.setText(R.string.title_tab2);

		} else if (tab.getPosition() == 0) {// on etait sur la tab "Flux"

		}

	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		Toast.makeText(ctx, "onTabReselected", Toast.LENGTH_LONG).show();
	}

}
