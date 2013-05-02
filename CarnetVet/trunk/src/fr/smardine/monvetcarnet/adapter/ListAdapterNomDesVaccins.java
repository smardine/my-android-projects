package fr.smardine.monvetcarnet.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import fr.smardine.monvetcarnet.R;
import fr.smardine.monvetcarnet.mdl.MlVaccin;

/**
 * Liste adapter pour gerer les noms des vaccins injectés a une date précise lors du clic sur un element de la grille de la liste des
 * MlVaccin du carnet de santé.
 * @author sims
 */
public class ListAdapterNomDesVaccins extends ArrayAdapter<String> {

	private final Context context;
	private final MlVaccin vaccin;

	/**
	 * Constructeur
	 * @param context
	 * @param p_vaccinParent
	 */
	public ListAdapterNomDesVaccins(Context context, MlVaccin p_vaccinParent) {
		super(context, R.layout.spinner_text_only, p_vaccinParent.getListeDesVaccinsInjectes());
		this.context = context;
		this.vaccin = p_vaccinParent;
	}

	/**
	 * Obtenir la "vue"
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	/**
	 * Customiser la view, permet, en fonction de la position dans la liste, de venir chercher les bonnes infos dans les tableau privé
	 * déclaré plus haut (label et icone)
	 * @param position
	 * @param convertView
	 * @param parent
	 * @return
	 */
	private View getCustomView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View row = inflater.inflate(R.layout.spinner_text_only, parent, false);
		TextView label = (TextView) row.findViewById(R.id.textViewSpinnerTextOnly);
		label.setText(vaccin.getListeDesVaccinsInjectes().get(position));

		return row;
	}
}
