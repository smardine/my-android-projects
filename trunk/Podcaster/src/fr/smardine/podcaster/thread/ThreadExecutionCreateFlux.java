package fr.smardine.podcaster.thread;

import java.util.List;

import android.content.Context;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.database.accestable.AccesTableEpisode;
import fr.smardine.podcaster.database.accestable.AccesTableFlux;
import fr.smardine.podcaster.helper.log.LogCatBuilder;
import fr.smardine.podcaster.mdl.MlEpisode;
import fr.smardine.podcaster.mdl.MlFlux;
import fr.smardine.podcaster.mdl.MlListeFlux;
import fr.smardine.podcaster.metier.builder.MajOuCreateFluxBuilder;
import fr.smardine.podcaster.thread.hanlder.HandlerMajFluxProgressDialog;

public class ThreadExecutionCreateFlux extends Thread {

	private final String TAG = this.getClass().getSimpleName();
	private final Context context;
	// private final EnParserType type;
	// //private final EnMethodType method;
	private final HandlerMajFluxProgressDialog progressDialogHandler;

	private final List<String> listeUrl;
	// private final ListView listeView;
	private AccesTableFlux tableFlux;
	private AccesTableEpisode tableEpisode;

	public ThreadExecutionCreateFlux(Context p_context, HandlerMajFluxProgressDialog p_handler, List<String> p_listeUrl) {
		this.context = p_context;
		// this.type = p_type;
		// this.method = p_methode;
		this.progressDialogHandler = p_handler;
		// this.numeroClient = p_numeroClient;
		// this.motdepasse = p_motDePasse;
		// this.context = p_context;
		this.listeUrl = p_listeUrl;
		// this.listeView = p_listView;
		tableEpisode = new AccesTableEpisode(this.context);
		tableFlux = new AccesTableFlux(this.context);

	}

	@Override
	public final void run() {
		try {

			MajOuCreateFluxBuilder builder = new MajOuCreateFluxBuilder(context, progressDialogHandler, this);

			for (String uneUrl : listeUrl) {
				MlFlux unFlux = new MlFlux();
				unFlux.setFluxUrl(uneUrl);
				builder.doMajOuCreateFlux(unFlux, EnMethodType.CREATE_FLUX);
				progressDialogHandler.sendMessage(progressDialogHandler.obtainMessage(EnThreadExecResult.ENCOURS.getCode(),
						"Enregistrement en base"));
				if (unFlux != null) {
					tableFlux.insertFlux(unFlux);
					for (MlEpisode uneEpisode : unFlux.getListeEpisode()) {
						uneEpisode.setIdFluxParent(unFlux.getIdFlux());
						uneEpisode.setVignetteTelechargee(unFlux.getVignetteTelechargee());
						tableEpisode.createEpisode(uneEpisode);
					}
				}
			}

			// on recupere la liste des flux en base et on rafraichi la liste presentee a l'ecran
			MlListeFlux listeFlux = tableFlux.getListeDesFlux();
			progressDialogHandler.ValoriserListeFlux(listeFlux);

			progressDialogHandler.sendMessage(progressDialogHandler.obtainMessage(EnThreadExecResult.SUCCESS.getCode(),
					context.getString(R.string.s_analyseMemoriseTermine, (Object) null)));

		} catch (Exception e) {
			LogCatBuilder.WriteErrorToLog(context, TAG, R.string.app_errGrave, e);
		}
	};

}
