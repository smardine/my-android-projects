package fr.smardine.podcaster.listener;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.adapter.EnStatutVisibilite;
import fr.smardine.podcaster.adapter.EpisodeListAdapter.ViewHolderEpisode;
import fr.smardine.podcaster.database.accestable.AccesTableEpisode;
import fr.smardine.podcaster.mdl.EnStatutTelechargement;
import fr.smardine.podcaster.mdl.MlEpisode;

public class ButtonSupprMediaEpisodeClickListener implements OnClickListener {

	private MlEpisode episode;
	private ViewHolderEpisode viewholder;
	private Context ctx;

	public ButtonSupprMediaEpisodeClickListener(Context p_ctx, MlEpisode p_episode, ViewHolderEpisode p_holder) {
		this.ctx = p_ctx;
		this.episode = p_episode;
		this.viewholder = p_holder;
	}

	@Override
	public void onClick(View v) {

		AlertDialog.Builder ad = new AlertDialog.Builder(ctx);

		ad.setTitle("Suppression d'un flux");
		ad.setIcon(R.drawable.ad_attention);
		ad.setMessage("Etes vous sur de vouloir supprimer le fichier " + episode.getMediaTelecharge().getAbsoluteFile());

		android.content.DialogInterface.OnClickListener onClickListener1 = new android.content.DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (!episode.getMediaTelecharge().delete()) {
					episode.getMediaTelecharge().deleteOnExit();
				}

				if (episode.getMediaTelecharge().exists()) {
					// le fichier est tjrs là
					// laisse affiché l'icone de la poubelle
					viewholder.ImdCorbeilleEpisode.setVisibility(EnStatutVisibilite.VISIBLE.getCode());
					viewholder.ImdTelechargeEpisode.setVisibility(EnStatutVisibilite.RETIRE.getCode());
				} else {
					// le fichier à bien ete effacé
					// on reaffiche l'icone de telechargement et on cache celui de la corbeille
					viewholder.ImdCorbeilleEpisode.setVisibility(EnStatutVisibilite.RETIRE.getCode());
					viewholder.ImdTelechargeEpisode.setVisibility(EnStatutVisibilite.VISIBLE.getCode());
					// de plus on met a jour le statut de de l'episode
					episode.setStatutTelechargement(EnStatutTelechargement.Streaming);
					episode.setMediaTelecharge(null);
					new AccesTableEpisode(ctx).majEpisode(episode);
				}
			}
		};
		ad.setPositiveButton("Ok", (android.content.DialogInterface.OnClickListener) onClickListener1);

		ad.setNegativeButton("Annuler", null);

		ad.show();

	}

}
