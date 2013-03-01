package fr.smardine.podcaster.thread;

import java.util.List;

import android.content.Context;
import android.widget.ListView;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.database.accestable.AccesTableEpisode;
import fr.smardine.podcaster.helper.log.LogCatBuilder;
import fr.smardine.podcaster.mdl.MlEpisode;
import fr.smardine.podcaster.mdl.MlFlux;
import fr.smardine.podcaster.metier.MajOuCreateFluxBuilder;

public class ThreadExecutionMajFlux extends Thread {

	private final String TAG = this.getClass().getSimpleName();
	private final Context context;
	// private final EnParserType type;
	private final EnMethodType method;
	private final HandlerMajFluxProgressDialog progressDialogHandler;

	private final List<MlFlux> listeFlux;
	private final ListView listeView;
	// private AccesTableFlux tableFlux;
	private AccesTableEpisode tableEpisode;

	public ThreadExecutionMajFlux(Context p_context, EnMethodType p_methode, HandlerMajFluxProgressDialog p_handler,
			List<MlFlux> p_listeFlux, ListView p_listView) {
		this.context = p_context;
		// this.type = p_type;
		this.method = p_methode;
		this.progressDialogHandler = p_handler;
		// this.numeroClient = p_numeroClient;
		// this.motdepasse = p_motDePasse;
		// this.context = p_context;
		this.listeFlux = p_listeFlux;
		this.listeView = p_listView;
		tableEpisode = new AccesTableEpisode(this.context);

	}

	@Override
	public final void run() {
		try {

			MajOuCreateFluxBuilder builder = new MajOuCreateFluxBuilder(context, progressDialogHandler, this);

			for (MlFlux unFlux : listeFlux) {
				builder.doMajOuCreateFlux(unFlux, EnMethodType.MAJ_FLUX);
				progressDialogHandler.sendMessage(progressDialogHandler.obtainMessage(EnThreadExecResult.ENCOURS.getCode(),
						"Enregistrement en base"));
				// this.majUnFlux(unFlux);
				if (unFlux != null) {
					for (MlEpisode uneEpisode : unFlux.getListeEpisode()) {
						if (uneEpisode.isStatutNouveau()) {
							uneEpisode.setIdFluxParent(unFlux.getIdFlux());
							uneEpisode.setVignetteTelechargee(unFlux.getVignetteTelechargee());
							tableEpisode.createEpisode(uneEpisode);
						}
					}
				}
			}
			progressDialogHandler.sendMessage(progressDialogHandler.obtainMessage(EnThreadExecResult.SUCCESS.getCode(),
					context.getString(R.string.s_analyseMemoriseTermine, (Object) null)));

		} catch (Exception e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
		}
	};

	// private MlFlux parseUnFlux(Context p_context, String p_urlAParser) {
	// MlFlux unFlux = new MlFlux();
	// Document doc = RssParserMetier.OuvrirUrl(p_urlAParser);
	// if (doc != null) {
	// Node node = doc.getDocumentElement();
	//
	// RssParserMetier.contruitEnteteFlux(node, unFlux, p_context, p_urlAParser);
	// progressDialogHandler.sendMessage(progressDialogHandler.obtainMessage(EnThreadExecResult.ENCOURS.getCode(),
	// context.getString(R.string.s_telechargeTournees, (Object) null)));
	//
	// RssParserMetier.parcourirNodeListeEtValoriseListeEpisode(unFlux, doc);
	//
	// return unFlux;
	// }
	// return null;
	//
	// }
	//
	// /**
	// * Mettre un jour un Flux
	// * @param p_flux
	// */
	// private void majUnFlux(MlFlux p_flux) {
	//
	// Document doc = RssParserMetier.OuvrirUrl(p_flux.getFluxUrl());
	// if (doc != null) {
	// p_flux.setDateDerniereSynchro(Calendar.getInstance().getTimeInMillis());
	// // this.progressDialog.setTitle("Maj " + p_flux.getTitre());
	// progressDialogHandler.sendMessage(progressDialogHandler.obtainMessage(EnThreadExecResult.ENCOURS.getCode(), p_flux.getTitre()));
	// /**
	// * Elements du flux RSS
	// **/
	//
	// RssParserMetier.parcourirNodeListeEtValoriseListeEpisode(p_flux, doc);
	// }
	//
	// }

}
