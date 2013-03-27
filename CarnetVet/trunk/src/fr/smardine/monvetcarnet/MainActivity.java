package fr.smardine.monvetcarnet;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import fr.smardine.monvetcarnet.adapter.SpinerAdapter;
import fr.smardine.monvetcarnet.database.accestable.AccesTableCarnet;
import fr.smardine.monvetcarnet.fragment.CouvertureFragment;
import fr.smardine.monvetcarnet.fragment.IdentificationFragment;
import fr.smardine.monvetcarnet.fragment.VaccinFragment;

public class MainActivity extends FragmentActivity {

	private CouvertureFragment fragmentCouverture;
	private IdentificationFragment fragmentIdentification;
	private VaccinFragment fragmentVaccin;
	private ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// recherche des fragments a partir de leur ID via le fragment manager
		fragmentCouverture = (CouvertureFragment) getSupportFragmentManager().findFragmentById(R.id.couvertureFragment);
		fragmentIdentification = (IdentificationFragment) getSupportFragmentManager().findFragmentById(R.id.identificationFragment);
		fragmentVaccin = (VaccinFragment) getSupportFragmentManager().findFragmentById(R.id.VaccinFragment);
		// valorisation de leurs propriétés
		fragmentCouverture.context = this.getApplicationContext();
		fragmentIdentification.context = this.getApplicationContext();
		fragmentVaccin.context = this.getApplicationContext();

		// au demarrage,on ne souhaite afficher que la couverture à l'utilisateur.

		android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.hide(fragmentIdentification);
		transaction.hide(fragmentVaccin);
		transaction.commit();

		// Initialisation de l'action bar
		actionBar = getActionBar();

		// si l'action bar est caché, il est impossible de naviguer avec des
		// onglet
		// actionBar.hide();

		// indiquer que l'on souhaite naviguer par le spinner
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		AccesTableCarnet acces = new AccesTableCarnet(getApplicationContext());
		acces.getNbEnregistrement();
		actionBar.setListNavigationCallbacks(new SpinerAdapter(this, R.layout.spinner), new OnNavigationListener() {

			@Override
			public boolean onNavigationItemSelected(int itemPosition, long itemId) {
				if (itemPosition == 0) {
					// Ouverture d'une transaction pour les fragment
					android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
					// effet visuel lors de l'ouverture du fragment
					transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
					// ici, le fragment est deja ajouté car il a été chargé lors de la creation de l'activity
					// lorsque l'on a appelé la methode "setContentView(R.layout.main);" dans le "onCreate"
					if (fragmentCouverture.isAdded()) {
						// on cache le fragment "Episode"
						transaction.hide(fragmentIdentification);
						transaction.hide(fragmentVaccin);
						// et on affiche le fragment "Flux"
						transaction.show(fragmentCouverture);
						actionBar.setDisplayHomeAsUpEnabled(false);
						actionBar.setDisplayShowHomeEnabled(false);
					} else {
						transaction.addToBackStack(itemPosition + "stack_item");
						transaction.add(fragmentCouverture, itemPosition + "stack_item");
						transaction.show(fragmentCouverture);
					}
					// Ne pas oublier de faire un "commit" sur la transaction
					transaction.commitAllowingStateLoss();
					return true;
				}

				if (itemPosition == 1) {
					// Ouverture d'une transaction pour les fragment
					android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
					// effet visuel lors de l'ouverture du fragment
					transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
					// ici, le fragment est deja ajouté car il a été chargé lors de la creation de l'activity
					// lorsque l'on a appelé la methode "setContentView(R.layout.main);" dans le "onCreate"
					if (fragmentIdentification.isAdded()) {
						// on cache le fragment "Episode"
						transaction.hide(fragmentCouverture);
						transaction.hide(fragmentVaccin);
						// et on affiche le fragment "Flux"
						transaction.show(fragmentIdentification);
						actionBar.setDisplayHomeAsUpEnabled(true);
						actionBar.setDisplayShowHomeEnabled(true);
					} else {
						transaction.addToBackStack(itemPosition + "stack_item");
						transaction.add(fragmentIdentification, itemPosition + "stack_item");
						transaction.show(fragmentIdentification);
					}
					// Ne pas oublier de faire un "commit" sur la transaction
					transaction.commitAllowingStateLoss();

					return true;
				} else if (itemPosition == 2) {
					// Ouverture d'une transaction pour les fragment
					android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
					// effet visuel lors de l'ouverture du fragment
					transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
					// ici, le fragment est deja ajouté car il a été chargé lors de la creation de l'activity
					// lorsque l'on a appelé la methode "setContentView(R.layout.main);" dans le "onCreate"
					if (fragmentVaccin.isAdded()) {
						// on cache le fragment "Episode"
						transaction.hide(fragmentCouverture);
						transaction.hide(fragmentIdentification);
						// et on affiche le fragment "Flux"
						transaction.show(fragmentVaccin);
						actionBar.setDisplayHomeAsUpEnabled(true);
						actionBar.setDisplayShowHomeEnabled(true);
					} else {
						transaction.addToBackStack(itemPosition + "stack_item");
						transaction.add(fragmentVaccin, itemPosition + "stack_item");
						transaction.show(fragmentVaccin);
					}
					// Ne pas oublier de faire un "commit" sur la transaction
					transaction.commitAllowingStateLoss();

					return true;
				}
				return false;
			}
		});
		actionBar.setDisplayShowTitleEnabled(false);

		// utilisation du bouton "Retour" lorsque l'on est pas sur la page principale de l'appli.
		// actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);

		// cacher le titre de l'appli.
		actionBar.setDisplayShowTitleEnabled(false);
		// actionBar.set

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {

		if (menuItem.getItemId() == android.R.id.home) {
			actionBar.setSelectedNavigationItem(0);
			return true;
		}

		return false;

	}
}
