package fr.smardine.podcaster.listener;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import fr.smardine.podcaster.adapter.EpisodeListAdapter.ViewHolderEpisode;
import fr.smardine.podcaster.mdl.MlEpisode;
import fr.smardine.podcaster.metier.progressdialog.DownloadEpisodeProgressDialog;

public class ButtonTelechargeEpisodeListener implements OnClickListener {

	private Context ctx;
	private MlEpisode episode;
	private ViewHolderEpisode viewholder;

	public ButtonTelechargeEpisodeListener(Context p_ctx, MlEpisode p_episode, ViewHolderEpisode p_holder) {
		this.ctx = p_ctx;
		this.episode = p_episode;
		this.viewholder = p_holder;
	}

	@Override
	public void onClick(View v) {
		DownloadEpisodeProgressDialog downloadEpisode = new DownloadEpisodeProgressDialog();
		downloadEpisode.downloadEpisodeProgressDialog((Activity) ctx, episode, viewholder);
	}

}
