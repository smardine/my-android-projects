package fr.smardine.podcaster.adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class EpisodeListCacheView {

	private View baseView;
	private TextView TvTitreEpisode;
	private TextView TvDateEpisode;
	private ImageView VignetteFlux;
	private ImageButton ImdTelechargeEpisode;
	private ImageButton ImdCorbeilleEpisode;
	private TextView TvTexteTelechargement;

	public EpisodeListCacheView(View p_baseView) {
		this.baseView = p_baseView;
	}

	public View getViewBase() {
		return baseView;
	}

	public TextView getTvTitreEpisode(int resource) {
		if (this.TvTitreEpisode == null) {
			this.TvTitreEpisode = (TextView) baseView.findViewById(resource);
		}
		return this.TvTitreEpisode;
	}
}
