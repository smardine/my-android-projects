package fr.smardine.podcaster.metier.builder;

import android.content.Context;
import fr.smardine.podcaster.R;
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

	/**
	 * Objet m�tier charg� : - du t�l�chargement - du d�chiffrement - de la d�compression - de l'analyse - de la m�morisation des donn�es
	 * b�n�ficiaires et des tourn�es
	 * @param p_type type d'analyseur utilis� (SAX ..)
	 * @param p_method provenance du flux XML � analyser (URL directement depuis connexion DATA depuis un fichier local
	 * @param p_context
	 * @param p_progressDialogHandler moteur li� � la boite de dialogue de progression
	 * @param p_owner
	 */
	public DownloadEpisodeBuilder(Context p_context, HandlerDownloadEpisodeProgressDialog p_progressDialogHandler, Object p_owner) {
		context = p_context;
		progressDialogHandler = p_progressDialogHandler;
	}

	private boolean getArreterAnalyseMemorise() {
		return HandlerMajFluxProgressDialog.getArreterAnalyseMemorise();
	}

	public void doDownloadEpisode(MlEpisode p_episode, EnMethodType p_methodeType) throws InterruptedException {
		boolean fautIlPoursuivre = false;

		if (getArreterAnalyseMemorise()) {
			throw new InterruptedException(STOP_SYNCHRO);
		}

		fautIlPoursuivre = DownloadHelper.DownloadEpisodeFromUrl(context, p_episode.getUrlMedia(), p_episode);

		if (fautIlPoursuivre) {
			progressDialogHandler.sendMessage(progressDialogHandler.obtainMessage(EnThreadExecResult.SUCCESS.getCode(),
					context.getString(R.string.s_telechargement_termine, (Object) null)));

		} else {
			progressDialogHandler.sendMessage(progressDialogHandler.obtainMessage(EnThreadExecResult.SUCCESS_BUTEMPTY.getCode()));
		}
	}

}
