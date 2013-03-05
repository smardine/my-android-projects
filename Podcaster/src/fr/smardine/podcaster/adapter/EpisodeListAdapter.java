package fr.smardine.podcaster.adapter;

import java.util.Date;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.database.accestable.AccesTableEpisode;
import fr.smardine.podcaster.helper.BitmapCache;
import fr.smardine.podcaster.helper.ImageHelper;
import fr.smardine.podcaster.mdl.EnStatutTelechargement;
import fr.smardine.podcaster.mdl.EnTypeEpisode;
import fr.smardine.podcaster.mdl.MlEpisode;
import fr.smardine.podcaster.mdl.MlListeEpisode;
import fr.smardine.podcaster.metier.progressdialog.DownloadEpisodeProgressDialog;
import fr.smardine.tools.date.DateHelper;

/**
 * @author smardine
 */
public class EpisodeListAdapter extends BaseAdapter {

	private final MlListeEpisode lstEpisodes;
	// créer un layoutinflater pour intégrer la listview dedans
	private final LayoutInflater myInflater;
	private BitmapCache cache;
	private Context ctx;

	/**
	 * @param p_ctx
	 * @param p_lstEpisodes
	 */
	public EpisodeListAdapter(Context p_ctx, MlListeEpisode p_lstEpisodes) {
		// paramètrer le layout en prenant celui du context
		this.ctx = p_ctx;
		this.myInflater = LayoutInflater.from(p_ctx);
		this.lstEpisodes = p_lstEpisodes;

		// Initialisation du cache pour les images
		// Get memory class of this device, exceeding this amount will throw an
		// OutOfMemory exception.
		final int memClass = ((ActivityManager) p_ctx.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();

		// Use 1/8th of the available memory for this memory cache.
		final int cacheSize = 1024 * 1024 * memClass / 8;
		cache = new BitmapCache(cacheSize);

	}

	@Override
	public int getCount() {
		return this.lstEpisodes.size();
	}

	@Override
	public Object getItem(int p_idx) {
		return this.lstEpisodes.get(p_idx);
	}

	@Override
	public long getItemId(int p_idx) {
		return p_idx;
	}

	/*
	 * astuce pour fluidifier au mieux l'affichage de la listview mémoriser le contenu des composants visuels qui sont présents dans une
	 * ligne de la listview La classe peut être déclarée dans un autre module pour être réutilisée
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	/**
	 * @author smardine
	 */
	public static class ViewHolder {
		TextView TvTitreEpisode;
		TextView TvDateEpisode;
		ImageView VignetteFlux;
		ImageButton ImdTelechargeEpisode;
		ImageButton ImdCorbeilleEpisode;
		TextView TvTexteTelechargement;
	}

	/*
	 * cette méthode est appelée à chaque fois que la listview doit afficher une ligne
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		// convertView peut déjà être initialisé sinon alors l'initialiser
		if (convertView == null) {
			// affecter un linearlayout propre à la ligne à afficher dans le
			// listview
			convertView = myInflater.inflate(R.layout.episodelistitem, null);
			holder = new ViewHolder();
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
		} else {
			// puisque déjà valorisé une fois alors récupérer le holder à partir
			// du tag posé à la création
			holder = (ViewHolder) convertView.getTag();
		}

		final MlEpisode unEpisode = lstEpisodes.get(position);
		// Application des données au element de la vue
		holder.TvTitreEpisode.setText(unEpisode.getTitre());
		String dateStr = DateHelper.ddMMM(new Date(unEpisode.getDatePublication()));
		holder.TvDateEpisode.setText(dateStr);

		// affichage des images
		// where imageUrl is what you pulled out from the rss feed
		if (unEpisode.isVignetteTelechargee()) {
			Bitmap bitmapFromCache = cache.getBitmapFromMemCache(unEpisode.getVignetteTelechargee().getAbsolutePath());
			if (bitmapFromCache != null) {
				// si le bitmap est deja en cache, on l'utilise,
				holder.VignetteFlux.setImageBitmap(bitmapFromCache);
			} else {
				// sinon on decode le fichier, est on push le bitmap en cache.
				Bitmap bitmapFromFile = ImageHelper.decodeBitmapFromFile(unEpisode.getVignetteTelechargee(), 100, 100);
				cache.addBitmapToMemoryCache(unEpisode.getVignetteTelechargee().getAbsolutePath(), bitmapFromFile);
				holder.VignetteFlux.setImageBitmap(bitmapFromFile);
			}
			holder.VignetteFlux.setAdjustViewBounds(true);
			holder.VignetteFlux.setMaxHeight(150);
			holder.VignetteFlux.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

		} else {
			holder.VignetteFlux.setImageResource(R.drawable.rss_std);
		}
		if (unEpisode.getTypeEpisode() == EnTypeEpisode.Text) {
			holder.ImdCorbeilleEpisode.setVisibility(EnStatutVisibilite.RETIRE.getCode());
			holder.ImdTelechargeEpisode.setVisibility(EnStatutVisibilite.RETIRE.getCode());
		} else { // cas des type audio et video
			if (unEpisode.getStatutTelechargement() == EnStatutTelechargement.Streaming) {
				holder.ImdTelechargeEpisode.setVisibility(EnStatutVisibilite.VISIBLE.getCode());
				holder.ImdCorbeilleEpisode.setVisibility(EnStatutVisibilite.RETIRE.getCode());
			} else {
				holder.ImdTelechargeEpisode.setVisibility(EnStatutVisibilite.RETIRE.getCode());
				holder.ImdCorbeilleEpisode.setVisibility(EnStatutVisibilite.VISIBLE.getCode());
			}

			holder.ImdTelechargeEpisode.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					DownloadEpisodeProgressDialog downloadEpisode = new DownloadEpisodeProgressDialog();
					downloadEpisode.downloadEpisodeProgressDialog((Activity) ctx, unEpisode, holder.ImdTelechargeEpisode,
							holder.ImdCorbeilleEpisode, holder.TvTexteTelechargement);
				}

			});

			holder.ImdCorbeilleEpisode.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Builder ad = new AlertDialog.Builder(ctx);

					ad.setTitle("Suppression d'un flux");
					ad.setIcon(R.drawable.ad_attention);
					ad.setMessage("Etes vous sur de vouloir supprimer le fichier " + unEpisode.getMediaTelecharge().getAbsoluteFile());

					android.content.DialogInterface.OnClickListener onClickListener1 = new android.content.DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							if (!unEpisode.getMediaTelecharge().delete()) {
								unEpisode.getMediaTelecharge().deleteOnExit();
							}

							if (unEpisode.getMediaTelecharge().exists()) {
								// le fichier est tjrs là
								// laisse affiché l'icone de la poubelle
								holder.ImdCorbeilleEpisode.setVisibility(EnStatutVisibilite.VISIBLE.getCode());
								holder.ImdTelechargeEpisode.setVisibility(EnStatutVisibilite.RETIRE.getCode());
							} else {
								// le fichier à bien ete effacé
								// on reaffiche l'icone de telechargement et on cache celui de la corbeille
								holder.ImdCorbeilleEpisode.setVisibility(EnStatutVisibilite.RETIRE.getCode());
								holder.ImdTelechargeEpisode.setVisibility(EnStatutVisibilite.VISIBLE.getCode());
								// de plus on met a jour le statut de de l'episode
								unEpisode.setStatutTelechargement(EnStatutTelechargement.Streaming);
								unEpisode.setMediaTelecharge(null);
								new AccesTableEpisode(ctx).majEpisode(unEpisode);
							}

						}
					};
					ad.setPositiveButton("Ok", (android.content.DialogInterface.OnClickListener) onClickListener1);

					ad.setNegativeButton("Annuler", null);
					// ad.setView(this.view);
					ad.show();

				}
			});
		}

		holder.TvTexteTelechargement.setVisibility(EnStatutVisibilite.RETIRE.getCode());
		return convertView;
	}
}
