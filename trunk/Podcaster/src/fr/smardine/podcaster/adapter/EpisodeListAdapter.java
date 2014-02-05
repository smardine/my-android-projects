package fr.smardine.podcaster.adapter;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.helper.BitmapCache;
import fr.smardine.podcaster.helper.ImageHelper;
import fr.smardine.podcaster.listener.ButtonSupprMediaEpisodeClickListener;
import fr.smardine.podcaster.listener.ButtonTelechargeEpisodeListener;
import fr.smardine.podcaster.mdl.EnStatutTelechargement;
import fr.smardine.podcaster.mdl.EnTypeEpisode;
import fr.smardine.podcaster.mdl.MlEpisode;
import fr.smardine.podcaster.mdl.MlListeEpisode;
import fr.smardine.tools.date.DateHelper;

/**
 * @author smardine
 */

public class EpisodeListAdapter extends BaseAdapter implements Filterable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1059285392877713970L;
	public MlListeEpisode lstEpisodes;
	// creer un layoutinflater pour integrer la listview dedans
	private final LayoutInflater myInflater;
	private BitmapCache cache;
	private Context ctx;
	private Filter mfilter;
	private MlListeEpisode mOriginalValues;
	private Object lock;

	/**
	 * @param p_ctx
	 * @param p_lstEpisodes
	 */
	public EpisodeListAdapter(Context p_ctx, MlListeEpisode p_lstEpisodes) {
		// parametrer le layout en prenant celui du context
		this.ctx = p_ctx;
		this.myInflater = LayoutInflater.from(p_ctx);
		this.lstEpisodes = p_lstEpisodes;
		this.mOriginalValues = p_lstEpisodes;
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
	 * astuce pour fluidifier au mieux l'affichage de la listview memoriser le contenu des composants visuels qui sont presents dans une
	 * ligne de la listview La classe peut etre declaree dans un autre module pour etre reutilisee
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

	/*
	 * cette methode est appelee a chaque fois que la listview doit afficher une ligne
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolderEpisode holder;
		final MlEpisode unEpisode = lstEpisodes.get(position);
		// EpisodeListCacheView viewCache;

		// convertView peut deja etre initialise sinon alors l'initialiser
		if (convertView == null) {
			// affecter un linearlayout propre a la ligne a afficher dans le
			// listview
			convertView = myInflater.inflate(R.layout.episodelistitem, null);
			// viewCache = new EpisodeListCacheView(convertView);

			holder = new ViewHolderEpisode();
			holder.TvTitreEpisode = (TextView) convertView.findViewById(R.id.tvTitreEpisode);
			holder.TvDateEpisode = (TextView) convertView.findViewById(R.id.tvDateEpisode);
			holder.VignetteFlux = (ImageView) convertView.findViewById(R.id.ivVignetteFlux);
			// holder.ImdCatFlux = (ImageView) convertView.findViewById(R.id.ivCategorieFlux);
			holder.ImdTelechargeEpisode = (ImageButton) convertView.findViewById(R.id.ibTelechargementEpisode);
			holder.TvTexteTelechargement = (TextView) convertView.findViewById(R.id.tvMessageTelechargement);
			holder.ImdCorbeilleEpisode = (ImageButton) convertView.findViewById(R.id.ibCorbeilleEpisode);
			// tagger le convertView avec ce Holder cree pour que l'association
			// se fasse
			convertView.setTag(holder);

		} else {
			// puisque deja valorise une fois alors recuperer le holder a partir
			// du tag pose a la creation
			holder = (ViewHolderEpisode) convertView.getTag();
		}

		bindView(holder, unEpisode);
		return convertView;
	}

	private void bindView(ViewHolderEpisode holder, final MlEpisode unEpisode) {
		// Application des donnees au element de la vue
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
			holder.VignetteFlux.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					MediaPlayer mp = new MediaPlayer();
					try {
						mp.setDataSource(unEpisode.getMediaTelecharge().getAbsolutePath());
						mp.prepare();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					mp.start();

				}
			});

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

			ButtonTelechargeEpisodeListener downloadFileListener = new ButtonTelechargeEpisodeListener(ctx, unEpisode, holder);
			holder.ImdTelechargeEpisode.setOnClickListener(downloadFileListener);
			ButtonSupprMediaEpisodeClickListener supprMediaListener = new ButtonSupprMediaEpisodeClickListener(ctx, unEpisode, holder);
			holder.ImdCorbeilleEpisode.setOnClickListener(supprMediaListener);
		}

		holder.TvTexteTelechargement.setVisibility(EnStatutVisibilite.RETIRE.getCode());

	}

	@Override
	public Filter getFilter() {
		if (mfilter == null) {
			mfilter = new EpisodeFilter();
		}
		return mfilter;
	}

	private class EpisodeFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence prefix) {
			FilterResults results = new FilterResults();

			if (mOriginalValues == null) {
				synchronized (lock) {
					mOriginalValues = lstEpisodes;
				}
			}

			if (prefix == null || prefix.length() == 0) {
				synchronized (lock) {
					MlListeEpisode list = new MlListeEpisode();
					results.values = list;
					results.count = list.size();
				}
			} else {
				final String prefixString = prefix.toString();

				MlListeEpisode values = mOriginalValues;
				int count = values.size();

				if (" ".equals(prefixString)) {
					results.values = mOriginalValues;
					results.count = mOriginalValues.size();
				} else {
					MlListeEpisode newValues = new MlListeEpisode();

					for (int i = 0; i < count; i++) {
						MlEpisode item = values.get(i);

						if (item.getFluxParent().getTitre().startsWith(prefixString)) {
							newValues.add(item);
						}
						// String[] words = item.getFluxParent().getTitre().split(" ");
						// int wordCount = words.length;
						//
						// for (int k = 0; k < wordCount; k++) {
						// final String word = words[k];
						//
						// if (word.startsWith(prefixString)) {
						// newValues.add(item);
						// break;
						// }
						// }
					}

					results.values = newValues;
					results.count = newValues.size();
				}

			}

			return results;
		}

		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {

			lstEpisodes = (MlListeEpisode) results.values;
			if (results.count > 0) {
				notifyDataSetChanged();
			} else {
				notifyDataSetInvalidated();
			}
		}
	}
}
