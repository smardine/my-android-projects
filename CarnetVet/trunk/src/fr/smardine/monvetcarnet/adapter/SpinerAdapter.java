package fr.smardine.monvetcarnet.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import fr.smardine.monvetcarnet.R;

/**
 * Spinner adapter qui gere le menu déroulant présent sur toutes les pages et qui permet de naviguer d'une page à l'autre
 * @author sims
 */
public class SpinerAdapter extends ArrayAdapter<Object> {

	/**
	 * tableau de chaine avec les label des menus
	 */
	private static String[] labelMenu = new String[] { "Mon vet'carnet", "Identification", "Vaccins", "Poids", "Maladie", "Utiles" };
	/**
	 * tableau d'entier representant d'eventuels icones a mettre en face des menus.
	 */
	private final int[] images = new int[] { R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_vaccins_48px,
			R.drawable.ic_vermifuge_48px, R.drawable.ic_launcher, R.drawable.ic_launcher };

	/**
	 * Context (activity) parent
	 */
	private final Context context;

	/**
	 * Constructeur
	 * @param context - le context parent
	 * @param textViewResourceId - l'id du layout representant le spinner
	 */
	public SpinerAdapter(Context context, int textViewResourceId) {
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
		View row = inflater.inflate(R.layout.spinner, parent, false);
		TextView label = (TextView) row.findViewById(R.id.textView1);
		label.setText(labelMenu[position]);

		ImageView icon = (ImageView) row.findViewById(R.id.imageView1);
		icon.setImageResource(images[position]);

		return row;
	}

}
