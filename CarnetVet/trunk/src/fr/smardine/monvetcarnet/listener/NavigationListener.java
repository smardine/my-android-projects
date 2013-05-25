package fr.smardine.monvetcarnet.listener;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.widget.Toast;
import fr.smardine.monvetcarnet.R;
import fr.smardine.monvetcarnet.fragment.CouvertureFragment;
import fr.smardine.monvetcarnet.fragment.IdentificationFragment;
import fr.smardine.monvetcarnet.fragment.MaladieFragment;
import fr.smardine.monvetcarnet.fragment.VaccinFragment;
import fr.smardine.monvetcarnet.helper.log.EnNiveauLog;
import fr.smardine.monvetcarnet.helper.log.LogCatBuilder;

public class NavigationListener implements OnNavigationListener {

	private final FragmentActivity ctx;
	private final CouvertureFragment fragmentCouverture;
	private final IdentificationFragment fragmentIdentification;
	private final VaccinFragment fragmentVaccin;
	private final ActionBar actionBar;
	private MenuItem menuPlusIdentification;
	private MenuItem menuPlusVaccin;
	private MenuItem menuPlusMaladie;
	private MenuItem menuPlusPoid;
	private final String TAG = this.getClass().getName();
	private final MaladieFragment fragmentMaladie;

	/**
	 * Constructeur
	 * @param p_ctx
	 * @param p_couvertureFragment
	 * @param p_identFragment
	 * @param p_vaccinFrragment
	 * @param fragmentMaladie
	 * @param p_actionbar
	 */
	public NavigationListener(FragmentActivity p_ctx, CouvertureFragment p_couvertureFragment, IdentificationFragment p_identFragment,
			VaccinFragment p_vaccinFrragment, MaladieFragment fragmentMaladie, ActionBar p_actionbar) {
		this.ctx = p_ctx;
		this.fragmentCouverture = p_couvertureFragment;
		this.fragmentIdentification = p_identFragment;
		this.fragmentVaccin = p_vaccinFrragment;
		this.fragmentMaladie = fragmentMaladie;
		this.actionBar = p_actionbar;
	}

	public MenuItem getMenuPlusIdentification() {
		return menuPlusIdentification;
	}

	public void setMenuPlusIdentification(MenuItem menuPlusIdentification) {
		this.menuPlusIdentification = menuPlusIdentification;
	}

	public MenuItem getMenuPlusVaccin() {
		return menuPlusVaccin;
	}

	public void setMenuPlusVaccin(MenuItem menuPlusVaccin) {
		this.menuPlusVaccin = menuPlusVaccin;
	}

	public MenuItem getMenuPlusMaladie() {
		return menuPlusMaladie;
	}

	public void setMenuPlusMaladie(MenuItem menuPlusMaladie) {
		this.menuPlusMaladie = menuPlusMaladie;
	}

	public MenuItem getMenuPlusPoid() {
		return menuPlusPoid;
	}

	public void setMenuPlusPoid(MenuItem menuPlusPoid) {
		this.menuPlusPoid = menuPlusPoid;
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		switch (itemPosition) {
			case 0:
			case 1:
			case 2:
			case 4:
				creerEtExecuterTransactionFromPosition(itemPosition);
				afficheOuCacheBoutonAjouterEnFonctionDeLaPosition(itemPosition);
				return true;
			default:
				LogCatBuilder.WriteInfoToLog(ctx, EnNiveauLog.WARNING, TAG, R.string.position_non_geree, TAG);
				Toast.makeText(ctx, R.string.position_non_geree, Toast.LENGTH_LONG).show();
				return false;
		}

	}

	private void afficheOuCacheBoutonAjouterEnFonctionDeLaPosition(int p_itemPosition) {
		// par defaut, tous les menu "plus" sont cach�.
		this.menuPlusIdentification.setVisible(false);
		this.menuPlusMaladie.setVisible(false);
		this.menuPlusPoid.setVisible(false);
		this.menuPlusVaccin.setVisible(false);

		switch (p_itemPosition) {
			case 0: // sur la page de couv, aucun bouton plus.
				break;
			case 1: // page d'identification
				this.menuPlusIdentification.setVisible(true);
				break;
			case 2:// page vaccin
				this.menuPlusVaccin.setVisible(true);
				break;
			case 3:// page poids
				this.menuPlusPoid.setVisible(true);
				break;
			case 4:// page maladie
				this.menuPlusMaladie.setVisible(true);
				break;
			default:
				LogCatBuilder.WriteInfoToLog(ctx, EnNiveauLog.WARNING, TAG, R.string.position_non_geree, TAG);
				Toast.makeText(ctx, "La position selectionn�e n'est pas encore g�r�e (NavigationListener)", Toast.LENGTH_LONG).show();

		}
	}

	private void creerEtExecuterTransactionFromPosition(int p_itemPosition) {

		// Ouverture d'une transaction pour les fragment
		android.support.v4.app.FragmentTransaction transaction = ctx.getSupportFragmentManager().beginTransaction();
		// effet visuel lors de l'ouverture du fragment
		transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
		// ici, le fragment est deja ajout� car il a �t� charg� lors de la creation de l'activity
		// lorsque l'on a appel� la methode "setContentView(R.layout.main);" dans le "onCreate"
		if (p_itemPosition == 0) {
			if (fragmentCouverture.isAdded()) {
				// on cache le fragment "Episode"
				transaction.hide(fragmentIdentification);
				transaction.hide(fragmentVaccin);
				transaction.hide(fragmentMaladie);
				// et on affiche le fragment "Flux"
				transaction.show(fragmentCouverture);
				actionBar.setDisplayHomeAsUpEnabled(false);
				actionBar.setDisplayShowHomeEnabled(false);
			} else {
				transaction.addToBackStack(p_itemPosition + "stack_item");
				transaction.add(fragmentCouverture, p_itemPosition + "stack_item");
				transaction.show(fragmentCouverture);
			}
		} else if (p_itemPosition == 1) {
			if (fragmentIdentification.isAdded()) {
				// on cache le fragment "Episode"
				transaction.hide(fragmentCouverture);
				transaction.hide(fragmentVaccin);
				transaction.hide(fragmentMaladie);
				// et on affiche le fragment "Flux"
				transaction.show(fragmentIdentification);
				actionBar.setDisplayHomeAsUpEnabled(true);
				actionBar.setDisplayShowHomeEnabled(true);

			} else {
				transaction.addToBackStack(p_itemPosition + "stack_item");
				transaction.add(fragmentIdentification, p_itemPosition + "stack_item");
				transaction.show(fragmentIdentification);
			}
		} else if (p_itemPosition == 2) {
			if (fragmentVaccin.isAdded()) {
				// on cache le fragment "Episode"
				transaction.hide(fragmentCouverture);
				transaction.hide(fragmentIdentification);
				transaction.hide(fragmentMaladie);
				// et on affiche le fragment "Flux"
				transaction.show(fragmentVaccin);
				actionBar.setDisplayHomeAsUpEnabled(true);
				actionBar.setDisplayShowHomeEnabled(true);
			} else {
				transaction.addToBackStack(p_itemPosition + "stack_item");
				transaction.add(fragmentVaccin, p_itemPosition + "stack_item");
				transaction.show(fragmentVaccin);
			}
		} else if (p_itemPosition == 4) {
			if (fragmentMaladie.isAdded()) {
				// on cache le fragment "Episode"
				transaction.hide(fragmentCouverture);
				transaction.hide(fragmentIdentification);
				transaction.hide(fragmentVaccin);
				// et on affiche le fragment "Flux"
				transaction.show(fragmentMaladie);
				actionBar.setDisplayHomeAsUpEnabled(true);
				actionBar.setDisplayShowHomeEnabled(true);
			} else {
				transaction.addToBackStack(p_itemPosition + "stack_item");
				transaction.add(fragmentMaladie, p_itemPosition + "stack_item");
				transaction.show(fragmentMaladie);
			}
		}

		// Ne pas oublier de faire un "commit" sur la transaction
		transaction.commitAllowingStateLoss();
	}
}
