package fr.smardine.monvetcarnet;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import fr.smardine.monvetcarnet.adapter.SpinerAdapter;
import fr.smardine.monvetcarnet.alertdialog.AlertDialogFactory;
import fr.smardine.monvetcarnet.database.accestable.AccesTableCarnet;
import fr.smardine.monvetcarnet.fragment.CouvertureFragment;
import fr.smardine.monvetcarnet.fragment.IdentificationFragment;
import fr.smardine.monvetcarnet.fragment.VaccinFragment;
import fr.smardine.monvetcarnet.listener.NavigationListener;

public class MainActivity extends FragmentActivity {

	public static CouvertureFragment fragmentCouverture;
	public static IdentificationFragment fragmentIdentification;
	public static VaccinFragment fragmentVaccin;
	private ActionBar actionBar;
	private NavigationListener navListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		// recherche des fragments a partir de leur ID via le fragment manager
		fragmentCouverture = (CouvertureFragment) getSupportFragmentManager().findFragmentById(R.id.couvertureFragment);
		fragmentIdentification = (IdentificationFragment) getSupportFragmentManager().findFragmentById(R.id.identificationFragment);
		fragmentVaccin = (VaccinFragment) getSupportFragmentManager().findFragmentById(R.id.VaccinFragment);
		// valorisation de leurs propriétés
		// fragmentCouverture.context = this.getApplicationContext();
		// fragmentIdentification.context = this.getApplicationContext();
		// fragmentVaccin.context = this.getApplicationContext();

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
		navListener = new NavigationListener(this, fragmentCouverture, fragmentIdentification, fragmentVaccin, actionBar);

		// utilisation du bouton "Retour" lorsque l'on est pas sur la page principale de l'appli.
		// actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);

		// cacher le titre de l'appli.
		actionBar.setDisplayShowTitleEnabled(false);

		actionBar.setListNavigationCallbacks(new SpinerAdapter(this, R.layout.spinner), navListener);

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		AccesTableCarnet acces = new AccesTableCarnet(getApplicationContext());
		int nombreDeCarnet = acces.getNbEnregistrement();
		if (nombreDeCarnet == 0) {
			AlertDialogFactory.creerEtAfficherIdentificationDialog(this, fragmentCouverture, fragmentIdentification);
		} else {
			fragmentCouverture.metAjourCouverture(acces.getListeDesCarnets().get(0));
			fragmentIdentification.metAjourIdentification(acces.getListeDesCarnets().get(0));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		navListener.setMenuPlusIdentification(menu.findItem(R.id.menuPlusIdentification));
		navListener.setMenuPlusVaccin(menu.findItem(R.id.menuPlusVaccin));
		navListener.setMenuPlusMaladie(menu.findItem(R.id.menuPlusMaladie));
		navListener.setMenuPlusPoid(menu.findItem(R.id.menuPlusPoids));
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {

		if (menuItem.getItemId() == android.R.id.home) {
			actionBar.setSelectedNavigationItem(0);
			return true;
		} else if (menuItem.getItemId() == R.id.menuPlusIdentification) {
			AlertDialogFactory.creerEtAfficherIdentificationSaisie(this, fragmentIdentification, new AccesTableCarnet(this)
					.getListeDesCarnets().get(0));
			return true;
		}

		return false;

	}

}
