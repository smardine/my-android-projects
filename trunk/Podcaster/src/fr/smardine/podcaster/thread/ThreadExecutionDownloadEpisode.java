package fr.smardine.podcaster.thread;

import android.content.Context;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.helper.log.LogCatBuilder;
import fr.smardine.podcaster.mdl.MlEpisode;
import fr.smardine.podcaster.metier.builder.DownloadEpisodeBuilder;
import fr.smardine.podcaster.thread.hanlder.HandlerDownloadEpisodeProgressDialog;

public class ThreadExecutionDownloadEpisode extends Thread {

	private final String TAG = this.getClass().getSimpleName();
	private final Context context;
	private final HandlerDownloadEpisodeProgressDialog progressDialogHandler;

	private MlEpisode episode;

	public ThreadExecutionDownloadEpisode(Context p_context, HandlerDownloadEpisodeProgressDialog p_handler, MlEpisode p_episode) {
		this.context = p_context;
		this.progressDialogHandler = p_handler;
		this.episode = p_episode;
	}

	@Override
	public final void run() {
		try {

			DownloadEpisodeBuilder builder = new DownloadEpisodeBuilder(context, progressDialogHandler, this);

			progressDialogHandler.sendMessage(progressDialogHandler.obtainMessage(EnThreadExecResult.ENCOURS.getCode(),
					"Téléchargement en cours..."));

			builder.doDownloadEpisode(episode, EnMethodType.DOWNLOAD_EPISODE);

		} catch (Exception e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
		}
	};

}
