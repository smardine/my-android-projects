package fr.smardine.monvetcarnet.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import fr.smardine.monvetcarnet.R;
import fr.smardine.monvetcarnet.helper.DateHelper;
import fr.smardine.monvetcarnet.helper.EnStatutVisibilite;
import fr.smardine.monvetcarnet.mdl.MlVaccin;

/**
 * BaseAdapter utilisé sur la page "Vaccin" qui permet d'afficher sous forme de grille les vaccins
 * @author sims
 */
public class VaccinsAdapter extends BaseAdapter {

	private final int imageVermifuge = R.drawable.ic_vermifuge_64px;
	private final int imageVaccin = R.drawable.ic_vaccins_64px;
	private final Context context;
	public List<MlVaccin> listeDeVaccin;

	public VaccinsAdapter(Context context, List<MlVaccin> listeDeVaccins) {
		super();
		this.listeDeVaccin = listeDeVaccins;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	/**
	 * @author smardine
	 */
	public final class ViewHolderVaccin {
		public TextView TvDateVaccin;
		public ImageView IvVaccin;
		public ImageView IvVermifuge;

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
		final ViewHolderVaccin holder;
		MlVaccin unVaccin = this.listeDeVaccin.get(position);

		if (convertView == null) {

			convertView = LayoutInflater.from(context).inflate(R.layout.vaccin, null);
			convertView.setLayoutParams(new GridView.LayoutParams(200, 200));

			holder = new ViewHolderVaccin();

			holder.TvDateVaccin = (TextView) convertView.findViewById(R.id.tvDateValue);

			holder.IvVermifuge = (ImageView) convertView.findViewById(R.id.ivLogoVermifuge);

			holder.IvVaccin = (ImageView) convertView.findViewById(R.id.ivLogoVaccin);

			if (unVaccin.isVermifuge()) {
				holder.IvVermifuge.setScaleType(ImageView.ScaleType.CENTER_CROP);
				holder.IvVermifuge.setPadding(8, 8, 8, 8);
				holder.IvVermifuge.setImageResource(imageVermifuge);
			} else {
				holder.IvVermifuge.setVisibility(EnStatutVisibilite.RETIRE.getCode());
			}

			if (unVaccin.isAuMoinsUnVaccin()) {
				holder.IvVaccin.setScaleType(ImageView.ScaleType.CENTER_CROP);
				holder.IvVaccin.setPadding(8, 8, 8, 8);
				holder.IvVaccin.setImageResource(imageVaccin);
			} else {
				holder.IvVaccin.setVisibility(EnStatutVisibilite.RETIRE.getCode());
			}
			// tagger le convertView avec ce Holder créé pour que l'association
			// se fasse
			convertView.setTag(holder);

		} else {
			// puisque déjà valorisé une fois alors récupérer le holder à partir
			// du tag posé à la création
			holder = (ViewHolderVaccin) convertView.getTag();
		}
		holder.TvDateVaccin.setText(DateHelper.MMMYYYY(unVaccin.getDate()));
		return convertView;
	}

	@Override
	public int getCount() {
		return this.listeDeVaccin.size();
	}

	@Override
	public Object getItem(int p_position) {
		return this.listeDeVaccin.get(p_position);
	}

	@Override
	public long getItemId(int p_position) {
		return this.listeDeVaccin.get(p_position).getId();
	}
}
