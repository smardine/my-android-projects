package fr.smardine.podcaster.metier.builder;

import java.util.Calendar;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import android.content.Context;
import fr.smardine.podcaster.mdl.MlFlux;
import fr.smardine.podcaster.metier.RssParserMetier;
import fr.smardine.podcaster.thread.EnMethodType;
import fr.smardine.podcaster.thread.EnThreadExecResult;
import fr.smardine.podcaster.thread.hanlder.HandlerMajFluxProgressDialog;

public class MajOuCreateFluxBuilder {

	private Context context;
	private HandlerMajFluxProgressDialog progressDialogHandler;
	private final String STOP_SYNCHRO = "<< STOP SYNCHRO >>";

	/**
	 * Objet metier charge : - du telechargement - du dechiffrement - de la decompression - de l'analyse - de la memorisation des donnees
	 * beneficiaires et des tournees
	 * @param p_type type d'analyseur utilise (SAX ..)
	 * @param p_method provenance du flux XML a analyser (URL directement depuis connexion DATA depuis un fichier local
	 * @param p_context
	 * @param p_progressDialogHandler moteur lie a la boite de dialogue de progression
	 * @param p_owner
	 */
	public MajOuCreateFluxBuilder(Context p_context, HandlerMajFluxProgressDialog p_progressDialogHandler, Object p_owner) {
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
					progressDialogHandler.sendMessage(progressDialogHandler.obtainMessage(EnThreadExecResult.CHANGE_TITRE.getCode(),
							p_flux.getTitre()));

					RssParserMetier.parcourirNodeListeEtValoriseListeEpisode(p_flux, document, progressDialogHandler);
					break;

				case MAJ_FLUX:
					progressDialogHandler.sendMessage(progressDialogHandler.obtainMessage(EnThreadExecResult.CHANGE_TITRE.getCode(),
							p_flux.getTitre()));
					p_flux.setDateDerniereSynchro(Calendar.getInstance().getTimeInMillis());
					RssParserMetier.parcourirNodeListeEtValoriseListeEpisode(p_flux, document, progressDialogHandler);
					break;
				default:
					progressDialogHandler.sendMessage(progressDialogHandler.obtainMessage(EnThreadExecResult.ERROR.getCode()));
			}
		} else {
			progressDialogHandler.sendMessage(progressDialogHandler.obtainMessage(EnThreadExecResult.SUCCESS_BUTEMPTY.getCode()));
		}
	}

}
