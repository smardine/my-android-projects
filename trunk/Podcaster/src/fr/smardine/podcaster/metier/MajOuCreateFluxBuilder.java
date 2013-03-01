package fr.smardine.podcaster.metier;

import java.util.Calendar;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import android.content.Context;
import android.os.Handler;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.mdl.MlFlux;
import fr.smardine.podcaster.thread.EnMethodType;
import fr.smardine.podcaster.thread.EnThreadExecResult;
import fr.smardine.podcaster.thread.HandlerMajFluxProgressDialog;

public class MajOuCreateFluxBuilder {

	// private Object owner;
	// private EnMethodType method;
	private Context context;
	private Handler progressDialogHandler;
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
	public MajOuCreateFluxBuilder(Context p_context, Handler p_progressDialogHandler, Object p_owner) {
		// owner = p_owner;
		// method = p_method;
		context = p_context;
		progressDialogHandler = p_progressDialogHandler;
	}

	private boolean getArreterAnalyseMemorise() {
		return HandlerMajFluxProgressDialog.getArreterAnalyseMemorise();
	}

	public void doMajOuCreateFlux(MlFlux p_flux, EnMethodType p_methodeType) throws InterruptedException {
		boolean fautIlPoursuivre = false;
		Document document = null;
		if (getArreterAnalyseMemorise()) {
			throw new InterruptedException(STOP_SYNCHRO);
		}
		document = RssParserMetier.OuvrirUrl(p_flux.getFluxUrl());
		fautIlPoursuivre = document != null;

		if (fautIlPoursuivre) {
			switch (p_methodeType) {
				case CREATE_FLUX:
					Node node = document.getDocumentElement();
					RssParserMetier.contruitEnteteFlux(node, p_flux, context, p_flux.getFluxUrl());
					progressDialogHandler.sendMessage(progressDialogHandler.obtainMessage(EnThreadExecResult.ENCOURS.getCode(),
							context.getString(R.string.s_telechargeTournees, (Object) null)));
					RssParserMetier.parcourirNodeListeEtValoriseListeEpisode(p_flux, document, progressDialogHandler);
					break;

				case MAJ_FLUX:
					progressDialogHandler.sendMessage(progressDialogHandler.obtainMessage(EnThreadExecResult.ENCOURS.getCode(),
							p_flux.getTitre()));
					p_flux.setDateDerniereSynchro(Calendar.getInstance().getTimeInMillis());
					RssParserMetier.parcourirNodeListeEtValoriseListeEpisode(p_flux, document, progressDialogHandler);
					break;
				default:
					progressDialogHandler.sendMessage(progressDialogHandler.obtainMessage(EnThreadExecResult.ERROR.getCode()));
			}
		} else {
			progressDialogHandler.sendMessage(progressDialogHandler.obtainMessage(EnThreadExecResult.ERROR.getCode()));
		}
	}

}
