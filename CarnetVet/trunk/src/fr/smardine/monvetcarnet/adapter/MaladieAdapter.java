/**
 * 
 */
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
import fr.smardine.monvetcarnet.mdl.MlMaladie;

/**
 * @author sims
 */
public class MaladieAdapter extends BaseAdapter {

	private final int imageRdvVeto = R.drawable.ic_vaccins_64px;
	private final Context context;
	public List<MlMaladie> listeDeMaladies;

	public MaladieAdapter(Context context, List<MlMaladie> listeDeMaladies) {
		super();
		this.listeDeMaladies = listeDeMaladies;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	/**
	 * @author smardine
	 */
	public final class ViewHolderMaladie {
		public TextView TvDateMaladie;
		public ImageView IvPictoMaladie;
		public ImageView IvRdvVeto;
		public TextView TvTitreMaladie;

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
		final ViewHolderMaladie holder;
		MlMaladie uneMaladie = this.listeDeMaladies.get(position);

		if (convertView == null) {

			convertView = LayoutInflater.from(context).inflate(R.layout.maladie, null);
			convertView.setLayoutParams(new GridView.LayoutParams(200, 200));

			holder = new ViewHolderMaladie();

			holder.TvDateMaladie = (TextView) convertView.findViewById(R.id.tvDateValue);

			holder.IvRdvVeto = (ImageView) convertView.findViewById(R.id.ivLogoRdvVeto);

			holder.IvPictoMaladie = (ImageView) convertView.findViewById(R.id.ivPictoMaladie);

			holder.TvTitreMaladie = (TextView) convertView.findViewById(R.id.tvTitreMaladie);

			if (uneMaladie.isRdvVeto()) {
				holder.IvRdvVeto.setScaleType(ImageView.ScaleType.CENTER_CROP);
				holder.IvRdvVeto.setPadding(8, 8, 8, 8);
				holder.IvRdvVeto.setImageResource(imageRdvVeto);
			} else {
				holder.IvRdvVeto.setVisibility(EnStatutVisibilite.RETIRE.getCode());
			}

			if (uneMaladie.isPictoDefini()) {
				holder.TvTitreMaladie.setVisibility(EnStatutVisibilite.RETIRE.getCode());
				holder.IvPictoMaladie.setScaleType(ImageView.ScaleType.CENTER_CROP);
				holder.IvPictoMaladie.setPadding(8, 8, 8, 8);
				holder.IvPictoMaladie.setImageResource(uneMaladie.getPictoMaladie().getRessourcePicto());
			} else {
				holder.IvPictoMaladie.setVisibility(EnStatutVisibilite.RETIRE.getCode());
				holder.TvTitreMaladie.setText(uneMaladie.getTitre());
			}
			// tagger le convertView avec ce Holder créé pour que l'association
			// se fasse
			convertView.setTag(holder);

		} else {
			// puisque déjà valorisé une fois alors récupérer le holder à partir
			// du tag posé à la création
			holder = (ViewHolderMaladie) convertView.getTag();
		}
		holder.TvDateMaladie.setText(DateHelper.MMMYYYY(uneMaladie.getDate()));
		return convertView;
	}

	@Override
	public int getCount() {
		return this.listeDeMaladies.size();
	}

	@Override
	public Object getItem(int p_position) {
		return this.listeDeMaladies.get(p_position);
	}

	@Override
	public long getItemId(int p_position) {
		return this.listeDeMaladies.get(p_position).getId();
	}

}
