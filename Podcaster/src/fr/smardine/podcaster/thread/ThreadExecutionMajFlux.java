package fr.smardine.podcaster.thread;

import java.util.List;

import android.content.Context;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.database.accestable.AccesTableEpisode;
import fr.smardine.podcaster.helper.log.LogCatBuilder;
import fr.smardine.podcaster.mdl.MlEpisode;
import fr.smardine.podcaster.mdl.MlFlux;
import fr.smardine.podcaster.mdl.MlListeEpisode;
import fr.smardine.podcaster.metier.MajOuCreateFluxBuilder;

public class ThreadExecutionMajFlux extends Thread {

	private final String TAG = this.getClass().getSimpleName();
	private final Context context;
	// private final EnParserType type;
	// private final EnMethodType method;
	private final HandlerMajFluxProgressDialog progressDialogHandler;

	private final List<MlFlux> listeFlux;

	private AccesTableEpisode tableEpisode;

	public ThreadExecutionMajFlux(Context p_context, HandlerMajFluxProgressDialog p_handler, List<MlFlux> p_listeFlux) {
		this.context = p_context;

		// this.method = p_methode;
		this.progressDialogHandler = p_handler;

		this.listeFlux = p_listeFlux;

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

				if (unFlux != null) {
					for (MlEpisode uneEpisode : unFlux.getListeEpisode()) {
						if (uneEpisode.isStatutNouveau()) {
							uneEpisode.setIdFluxParent(unFlux.getIdFlux());
							uneEpisode.setVignetteTelechargee(unFlux.getVignetteTelechargee());
							tableEpisode.createEpisode(uneEpisode);
						} else {
							tableEpisode.majEpisode(uneEpisode);
						}
					}
				}
			}

			MlListeEpisode listeEpisode = new MlListeEpisode();
			for (MlFlux unFlux : this.listeFlux) {
				listeEpisode.addAll(tableEpisode.getListeDesEpisodeParIdFlux(unFlux));
			}
			progressDialogHandler.ValoriserListeEpisode(listeEpisode);

			progressDialogHandler.sendMessage(progressDialogHandler.obtainMessage(EnThreadExecResult.SUCCESS.getCode(),
					context.getString(R.string.s_analyseMemoriseTermine, (Object) null)));

		} catch (Exception e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
		}
	};

}
