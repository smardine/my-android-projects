package fr.smardine.podcaster.adapter;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.helper.BitmapCache;
import fr.smardine.podcaster.helper.ImageHelper;
import fr.smardine.podcaster.mdl.MlFlux;
import fr.smardine.podcaster.mdl.MlListeFlux;
import fr.smardine.tools.date.DateHelper;

/**
 * @author smardine
 */
public class FluxListAdapter extends BaseAdapter {

	private final MlListeFlux lstFlux;
	// créer un layoutinflater pour intégrer la listview dedans
	private final LayoutInflater myInflater;
	private BitmapCache cache;

	/**
	 * @param p_ctx
	 * @param p_lstFlux
	 */
	public FluxListAdapter(Context p_ctx, MlListeFlux p_lstFlux) {
		// paramètrer le layout en prenant celui du context
		this.myInflater = LayoutInflater.from(p_ctx);
		this.lstFlux = p_lstFlux;

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
		return this.lstFlux.size();
	}

	@Override
	public Object getItem(int p_idx) {
		return this.lstFlux.get(p_idx);
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
		TextView TvTitreFlux;
		TextView TvDateDerniereSynchro;
		ImageView VignetteFlux;
		ImageView ImdCatFlux;
	}

	/*
	 * cette méthode est appelée à chaque fois que la listview doit afficher une ligne
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		// convertView peut déjà être initialisé sinon alors l'initialiser
		if (convertView == null) {
			// affecter un linearlayout propre à la ligne à afficher dans le
			// listview
			convertView = myInflater.inflate(R.layout.fluxlistitem, null);
			holder = new ViewHolder();

			holder.TvTitreFlux = (TextView) convertView.findViewById(R.id.tvTitreFlux);
			holder.TvDateDerniereSynchro = (TextView) convertView.findViewById(R.id.tvDateDerniereSynchro);
			holder.VignetteFlux = (ImageView) convertView.findViewById(R.id.ivVignetteFlux);
			holder.ImdCatFlux = (ImageView) convertView.findViewById(R.id.ivCategorieFlux);
			// tagger le convertView avec ce Holder créé pour que l'association
			// se fasse
			convertView.setTag(holder);
		} else {
			// puisque déjà valorisé une fois alors récupérer le holder à partir
			// du tag posé à la création
			holder = (ViewHolder) convertView.getTag();
		}

		MlFlux unFlux = lstFlux.get(position);
		// Application des données au element de la vue
		holder.TvTitreFlux.setText(unFlux.getTitre());
		String dateStr = DateHelper.ddMMM(unFlux.getDateDernierePublication());
		holder.TvDateDerniereSynchro.setText(dateStr);

		// affichage des images
		// where imageUrl is what you pulled out from the rss feed
		if (unFlux.isVignetteTelechargee()) {

			Bitmap bitmapFromCache = cache.getBitmapFromMemCache(unFlux.getVignetteTelechargee().getAbsolutePath());
			if (bitmapFromCache != null) {
				// si le bitmap est deja en cache, on l'utilise,
				holder.VignetteFlux.setImageBitmap(bitmapFromCache);
			} else {
				// sinon on decode le fichier, est on push le bitmap en cache.
				Bitmap bitmapFromFile = ImageHelper.decodeBitmapFromFile(unFlux.getVignetteTelechargee(), 100, 100);
				cache.addBitmapToMemoryCache(unFlux.getVignetteTelechargee().getAbsolutePath(), bitmapFromFile);
				holder.VignetteFlux.setImageBitmap(bitmapFromFile);
			}
			holder.VignetteFlux.setAdjustViewBounds(true);
			holder.VignetteFlux.setMaxHeight(150);
			holder.VignetteFlux.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

		} else {
			holder.VignetteFlux.setImageResource(R.drawable.rss_std);
		}
		// determination de la categorie du flux
		switch (unFlux.getTypeEpisodes()) {
			case Audio:
				holder.ImdCatFlux.setImageResource(R.drawable.audio);
				break;
			case Video:
				holder.ImdCatFlux.setImageResource(R.drawable.video);
				break;
			case Text:
				holder.ImdCatFlux.setImageResource(R.drawable.txt);
				break;
		}

		return convertView;
	}
}
