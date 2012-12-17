package fr.smardine.podcaster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.mdl.MlEpisode;
import fr.smardine.podcaster.mdl.MlListeEpisode;
import fr.smardine.tools.date.DateHelper;

/**
 * @author smardine
 */
public class EpisodeListAdapter extends BaseAdapter {

	private final MlListeEpisode lstFlux;
	// créer un layoutinflater pour intégrer la listview dedans
	private final LayoutInflater myInflater;

	/**
	 * @param p_ctx
	 * @param p_lstFlux
	 */
	public EpisodeListAdapter(Context p_ctx, MlListeEpisode p_lstFlux) {
		// paramètrer le layout en prenant celui du context
		this.myInflater = LayoutInflater.from(p_ctx);
		this.lstFlux = p_lstFlux;

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
	 * astuce pour fluidifier au mieux l'affichage de la listview mémoriser le
	 * contenu des composants visuels qui sont présents dans une ligne de la
	 * listview La classe peut être déclarée dans un autre module pour être
	 * réutilisée
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	/**
	 * @author smardine
	 */
	public static class ViewHolder {
		// TextView text01;
		TextView TvIdFlux;
		TextView TvTitreFlux;
		TextView TvDateDerniereSynchro;
		ImageView VignetteFlux;
		ImageView ImdCatFlux;
	}

	/*
	 * cette méthode est appelée à chaque fois que la listview doit afficher une
	 * ligne
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
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
			holder.TvIdFlux = (TextView) convertView
					.findViewById(R.id.tvIdFlux);
			holder.TvTitreFlux = (TextView) convertView
					.findViewById(R.id.tvTitreFlux);
			holder.TvDateDerniereSynchro = (TextView) convertView
					.findViewById(R.id.tvDateDerniereSynchro);
			holder.VignetteFlux = (ImageView) convertView
					.findViewById(R.id.ivVignetteFlux);
			holder.ImdCatFlux = (ImageView) convertView
					.findViewById(R.id.ivCategorieFlux);
			// tagger le convertView avec ce Holder créé pour que l'association
			// se fasse
			convertView.setTag(holder);
		} else {
			// puisque déjà valorisé une fois alors récupérer le holder à partir
			// du tag posé à la création
			holder = (ViewHolder) convertView.getTag();
		}

		MlEpisode unEpisode = lstFlux.get(position);
		// Application des données au element de la vue
		holder.TvIdFlux.setText("" + unEpisode.getIdEpisode());
		holder.TvTitreFlux.setText(unEpisode.getTitre());
		String dateStr = DateHelper.ddMMM(unEpisode.getDatePublication());
		holder.TvDateDerniereSynchro.setText(dateStr);

		// affichage des images
		holder.VignetteFlux.setImageResource(R.drawable.ic_launcher);
		holder.ImdCatFlux.setImageResource(R.drawable.ic_launcher);

		return convertView;
	}
}
