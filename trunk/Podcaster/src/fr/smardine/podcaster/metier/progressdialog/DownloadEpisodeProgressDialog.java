package fr.smardine.podcaster.metier.progressdialog;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.widget.ListView;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.adapter.EpisodeListAdapter.ViewHolderEpisode;
import fr.smardine.podcaster.helper.log.LogCatBuilder;
import fr.smardine.podcaster.mdl.MlEpisode;
import fr.smardine.podcaster.thread.EnMethodType;
import fr.smardine.podcaster.thread.ThreadExecutionCreateFlux;
import fr.smardine.podcaster.thread.ThreadExecutionDownloadEpisode;
import fr.smardine.podcaster.thread.hanlder.HandlerDownloadEpisodeProgressDialog;
import fr.smardine.podcaster.thread.hanlder.HandlerMajFluxProgressDialog;

/**
 * Boite de dialogue visuelle ouverte a partir d'une vue dont le context est passe en parametre du constructeur. Cette boite presente un
 * composant de progression circulaire. Le processus metier est execute dans un thread, il est interrompable . La boite de dialogue peut
 * etre cachee pour que l'utilisateur procede a d'autres manipulations.
 * @author s.mardine
 */
public class DownloadEpisodeProgressDialog {
	private final String TAG = this.getClass().getName();

	/**
	 * Creer une boite de dialoque munie d'une barre de progression Lancer un thread qui execute la mise a jour d'un flux, et memorise les
	 * informations en base
	 * @param p_context
	 * @param p_episode
	 * @param imdTelechargeEpisode
	 * @param tvTexteTelechargement
	 * @param p_listView
	 */
	public void downloadEpisodeProgressDialog(Activity p_context, MlEpisode p_episode, ViewHolderEpisode p_holder) {
		try {
			final HandlerDownloadEpisodeProgressDialog progressDialogHandler = new HandlerDownloadEpisodeProgressDialog(p_context, false,
					p_holder.ImdTelechargeEpisode, p_holder.ImdCorbeilleEpisode, p_holder.TvTexteTelechargement);

			new ThreadExecutionDownloadEpisode(p_context, progressDialogHandler, p_episode).start();

		} catch (Exception e) {
			LogCatBuilder.WriteErrorToLog(p_context, TAG, R.string.app_errGrave, e);
		}
	}

	/**
	 * Creer une boite de dialoque munie d'une barre de progression Lancer un thread qui execute la creation d'un flux, et memorise les
	 * infos en base
	 * @param p_context
	 * @param p_listeUrl
	 * @param p_listView
	 */
	public void synchroCreateFluxProgressDialog(Context p_context, List<String> p_listeUrl, ListView p_listView) {
		try {
			final HandlerMajFluxProgressDialog progressDialogHandler = new HandlerMajFluxProgressDialog(p_context,
					EnMethodType.CREATE_FLUX, p_listView, false);

			new ThreadExecutionCreateFlux(p_context, progressDialogHandler, p_listeUrl).start();

		} catch (Exception e) {
			LogCatBuilder.WriteErrorToLog(p_context, TAG, R.string.app_errGrave, e);
		}
	}

}
