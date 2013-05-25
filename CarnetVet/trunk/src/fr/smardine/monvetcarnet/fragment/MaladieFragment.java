/**
 * 
 */
package fr.smardine.monvetcarnet.fragment;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import fr.smardine.monvetcarnet.R;
import fr.smardine.monvetcarnet.adapter.MaladieAdapter;
import fr.smardine.monvetcarnet.alertdialog.AlertDialogFactory;
import fr.smardine.monvetcarnet.database.accestable.AccesTableCarnet;
import fr.smardine.monvetcarnet.database.accestable.AccesTableMaladie;
import fr.smardine.monvetcarnet.mdl.MlCarnet;
import fr.smardine.monvetcarnet.mdl.MlMaladie;

/**
 * @author sims
 */
public class MaladieFragment extends SuperFragment implements OnItemClickListener {

	private GridView choix;
	private MlCarnet carnetParent;

	public MaladieFragment() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v1 = inflater.inflate(R.layout.activity_maladies, container, false);
		choix = (GridView) v1.findViewById(R.id.listeMaladies);

		choix.setOnItemClickListener(this);

		return v1;

	}

	/**
	 * Evenement lancé une fois l'activity créée
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		AccesTableCarnet tableCarnet = new AccesTableCarnet(context);
		List<MlCarnet> listeDeCarnet = tableCarnet.getListeDesCarnets();
		if (listeDeCarnet != null && listeDeCarnet.size() > 0) {
			carnetParent = listeDeCarnet.get(0);
			choix.setAdapter(new MaladieAdapter(context, carnetParent.getListeDeMaladies())); // R.layout.vaccin
		}

	}

	/**
	 * Met a jour la liste des vaccins sur la grille
	 * @param p_carnetParent
	 */
	public void metAjourListeDeMaladies(MlCarnet p_carnetParent) {
		if (p_carnetParent != null) {
			List<MlMaladie> listeDeMaladie = new AccesTableMaladie(this.context).getListeDeMaladiesParIdCarnet(carnetParent);
			if (listeDeMaladie != null && listeDeMaladie.size() > 0) {
				choix.setAdapter(new MaladieAdapter(context, listeDeMaladie));
			}
		}
	}

	/**
	 * Evenement lancé lorsque l'utilisateur clique sur un des vaccins de la grille
	 */
	@Override
	public void onItemClick(AdapterView<?> p_adapterView, View p_view, int p_position, long p_itemId) {
		MlMaladie maladieSelectionnee = (MlMaladie) choix.getItemAtPosition(p_position);
		AlertDialogFactory.creerEtAfficherMaladieConsultation(context, this, carnetParent, maladieSelectionnee);
	}
}
