package fr.smardine.podcaster.metier.builder;

import android.content.Context;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.database.accestable.AccesTableEpisode;
import fr.smardine.podcaster.helper.DownloadHelper;
import fr.smardine.podcaster.mdl.MlEpisode;
import fr.smardine.podcaster.thread.EnMethodType;
import fr.smardine.podcaster.thread.EnThreadExecResult;
import fr.smardine.podcaster.thread.hanlder.HandlerDownloadEpisodeProgressDialog;
import fr.smardine.podcaster.thread.hanlder.HandlerMajFluxProgressDialog;

public class DownloadEpisodeBuilder {

	private Context context;
	private HandlerDownloadEpisodeProgressDialog progressDialogHandler;
	private final String STOP_SYNCHRO = "<< STOP SYNCHRO >>";
	private AccesTableEpisode tableEpisode;

	/**
	 * Objet metier charge : - du telechargement - du dechiffrement - de la decompression - de l'analyse - de la memorisation des donnees
	 * beneficiaires et des tournees
	 * @param p_type type d'analyseur utilise (SAX ..)
	 * @param p_method provenance du flux XML a analyser (URL directement depuis connexion DATA depuis un fichier local
	 * @param p_context
	 * @param p_progressDialogHandler moteur lie a la boite de dialogue de progression
	 * @param p_owner
	 */
	public DownloadEpisodeBuilder(Context p_context, HandlerDownloadEpisodeProgressDialog p_progressDialogHandler, Object p_owner) {
		context = p_context;
		progressDialogHandler = p_progressDialogHandler;
		this.tableEpisode = new AccesTableEpisode(context);
	}

	private boolean getArreterAnalyseMemorise() {
		return HandlerMajFluxProgressDialog.getArreterAnalyseMemorise();
	}

	public void doDownloadEpisode(MlEpisode p_episode, EnMethodType p_methodeType) throws InterruptedException {
		boolean fautIlPoursuivre = false;

		if (getArreterAnalyseMemorise()) {
			throw new InterruptedException(STOP_SYNCHRO);
		}

		fautIlPoursuivre = DownloadHelper.DownloadEpisodeFromUrl(context, progressDialogHandler, p_episode.getUrlMedia(), p_episode);

		if (fautIlPoursuivre) {
			progressDialogHandler.sendMessage(progressDialogHandler.obtainMessage(EnThreadExecResult.SUCCESS.getCode(),
					context.getString(R.string.s_telechargement_termine, (Object) null)));
			tableEpisode.majEpisode(p_episode);
		} else {
			progressDialogHandler.sendMessage(progressDialogHandler.obtainMessage(EnThreadExecResult.SUCCESS_BUTEMPTY.getCode()));
		}
	}

}
