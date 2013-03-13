package fr.smardine.podcaster.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.adapter.FluxListAdapter.ViewHolder;

public class EpisodeListAdapterCursor extends SimpleCursorAdapter {
	private final Context context;
	private final Cursor episodes;
	private final LayoutInflater myInflater;
	private final Bitmap mEpisodeIcon;

	public EpisodeListAdapterCursor(Context p_context, int layout, Cursor p_episodesCursor, String[] from, int[] to) {
		super(p_context, layout, p_episodesCursor, from, to);
		episodes = p_episodesCursor;
		context = p_context;
		this.myInflater = LayoutInflater.from(context);
		mEpisodeIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.rss_std);
	}

	@Override
	public int getCount() {
		return episodes.getCount();
	}

	@Override
	// renvoyer le Cursor qui correspond à la ligne d'indice position dans la
	// listview
	public Object getItem(int position) {
		return super.getItem(position);
	}

	@Override
	public long getItemId(int position) {
		return super.getItemId(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		View retour = convertView;
		// convertView peut déjà être initialisé sinon alors l'initialiser
		if (retour == null) {
			// affecter un linearlayout propre à la ligne à afficher dans le
			// listview
			convertView = myInflater.inflate(R.layout.episodelistitem, null);
			holder = new ViewHolderEpisode();
			holder.TvTitreEpisode = (TextView) convertView.findViewById(R.id.tvTitreEpisode);
			holder.TvDateEpisode = (TextView) convertView.findViewById(R.id.tvDateEpisode);
			holder.VignetteFlux = (ImageView) convertView.findViewById(R.id.ivVignetteFlux);
			// holder.ImdCatFlux = (ImageView) convertView.findViewById(R.id.ivCategorieFlux);
			holder.ImdTelechargeEpisode = (ImageButton) convertView.findViewById(R.id.ibTelechargementEpisode);
			holder.TvTexteTelechargement = (TextView) convertView.findViewById(R.id.tvMessageTelechargement);
			holder.ImdCorbeilleEpisode = (ImageButton) convertView.findViewById(R.id.ibCorbeilleEpisode);
			// tagger le convertView avec ce Holder créé pour que l'association
			// se fasse
			convertView.setTag(holder);
			bindView(holder, unEpisode);
			// tagger le retour avec ce Holder créé pour que l'association
			// se fasse
			retour.setTag(holder);
		} else {
			// puisque déjà valorisé une fois alors récupérer le holder à partir
			// du tag posé à la création
			holder = (ViewHolder) retour.getTag();
		}
		Cursor c = (Cursor) super.getItem(position);
		if (c == null) {
			return retour;
		}
		holder.benefNameTextView.setText(c.getString(c.getColumnIndex(DBEqxAdapter.C_BNF_LIBELLE)));
		holder.benefDateNaisTextView.setText(c.getString(c.getColumnIndex(DBEqxAdapter.C_BNF_DATENAIS)));
		// holder.benefDetailImageView.setImageBitmap((position & 1) == 1 ?
		// mBenefDetailIcon1 : mBenefDetailIcon2);
		holder.benefDetailImageView.setImageBitmap(mBenefDetailIcon1);

		return retour;
	}

	/*
	 * astuce pour fluidifier au mieux l'affichage de la listview mémoriser le contenu des composants visuels qui sont présents dans une
	 * ligne de la listview La classe peut être déclarée dans un autre module pour être réutilisée
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	/**
	 * @author smardine
	 */
	public final class ViewHolderEpisode {
		public TextView TvTitreEpisode;
		public TextView TvDateEpisode;
		public ImageView VignetteFlux;
		public ImageButton ImdTelechargeEpisode;
		public ImageButton ImdCorbeilleEpisode;
		public TextView TvTexteTelechargement;
	}

}
