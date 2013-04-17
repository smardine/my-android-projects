package fr.smardine.monvetcarnet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import fr.smardine.monvetcarnet.R;

/**
 * Array adapter utilisé sur la page "Vaccin" qui permet d'afficher sous forme de grille les vaccins
 * @author sims
 */
public class VaccinsAdapter extends ArrayAdapter<Object> {
	/**
	 * tableau de chaine avec les label des menus
	 */
	private static String[] labelMenu = new String[] { "mars 2008", "fevrier 2009", "juin 2009", "juillet 2010", "aout 2011",
			"decembre 2012" };
	/**
	 * tableau d'entier representant d'eventuels icones a mettre en face des menus.
	 */
	private final int[] images = new int[] { R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher };
	private final Context context;

	public VaccinsAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId, labelMenu);
		this.context = context;

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
		View v;
		if (convertView == null) {
			v = LayoutInflater.from(context).inflate(R.layout.vaccin, null);
			v.setLayoutParams(new GridView.LayoutParams(100, 100));

			TextView tvDate = (TextView) v.findViewById(R.id.tvDateValue);
			tvDate.setText(labelMenu[position]);

			ImageView imageViewVaccin = (ImageView) v.findViewById(R.id.ivLogoVaccin);
			imageViewVaccin.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageViewVaccin.setPadding(8, 8, 8, 8);
			imageViewVaccin.setImageResource(images[position]);

			ImageView imageViewVermifuge = (ImageView) v.findViewById(R.id.ivLogoVermifuge);
			imageViewVermifuge.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageViewVermifuge.setPadding(8, 8, 8, 8);
			imageViewVermifuge.setImageResource(images[position]);

		} else {
			v = convertView;
		}

		return v;
	}
}
