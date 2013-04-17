package fr.smardine.monvetcarnet.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import fr.smardine.monvetcarnet.R;

/**
 * Spinner adapter qui permet, sur la page d'identification, de presenter à l'utilisateur la liste des choix à sa disposition pour rentrer
 * des infos
 * @author sims
 */
public class SpinnerIdentificationSaisieAdapter extends ArrayAdapter<Object> {
	/**
	 * tableau de chaine avec les label des menus
	 */
	private static String[] labelMenu = new String[] { "Sexe", "Date de naissance", "Race", "Robe", "Signes distinctifs", "N° de puce",
			"N° de tatouage" };

	/**
	 * Context (activity) parent
	 */
	private final Context context;

	/**
	 * Constructeur
	 * @param context - le context parent
	 * @param textViewResourceId - l'id du layout representant le spinner
	 */
	public SpinnerIdentificationSaisieAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId, labelMenu);
		this.context = context;

	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

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
		label.setText(labelMenu[position]);
		return row;
	}

}
