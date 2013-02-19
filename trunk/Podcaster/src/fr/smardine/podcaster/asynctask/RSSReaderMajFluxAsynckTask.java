package fr.smardine.podcaster.asynctask;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import fr.smardine.podcaster.adapter.EpisodeListAdapter;
import fr.smardine.podcaster.database.accestable.AccesTableEpisode;
import fr.smardine.podcaster.mdl.MlEpisode;
import fr.smardine.podcaster.mdl.MlFlux;
import fr.smardine.podcaster.mdl.MlListeEpisode;
import fr.smardine.podcaster.metier.RssParserMetier;

/**
 * Parser une liste de flux RSS avec une barre de progression
 */

public class RSSReaderMajFluxAsynckTask extends AsyncTask<Void, String, Void> {

	// private MlFlux unFlux;
	private final Context context;
	private List<MlFlux> listeFlux;
	private ProgressDialog progressDialog;
	private ListView listeView;
	private AccesTableEpisode tableEpisode;

	/**
	 * Constructeur
	 * @param p_context
	 * @param p_listeFlux
	 * @param p_progressDialog
	 * @param p_listView
	 */
	public RSSReaderMajFluxAsynckTask(Context p_context, List<MlFlux> p_listeFlux, ProgressDialog p_progressDialog, ListView p_listView) {
		this.context = p_context;
		this.listeFlux = p_listeFlux;
		this.progressDialog = p_progressDialog;
		this.listeView = p_listView;
	}

	@Override
	protected void onPreExecute() {
		tableEpisode = new AccesTableEpisode(this.context);
	}

	@Override
	protected Void doInBackground(Void... params) {
		RssParserMetier parserMetier = new RssParserMetier();
		for (MlFlux unFlux : listeFlux) {
			parserMetier.majUnFlux(unFlux);
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
		progressDialog.dismiss();
		return null;

	}

	@Override
	protected void onPostExecute(Void result) {
		// on recupere la liste des flux en base et on rafraichi la liste presentée a l'ecran
		MlListeEpisode listeEpisode = new MlListeEpisode();
		for (MlFlux unFlux : this.listeFlux) {
			listeEpisode.addAll(tableEpisode.getListeDesEpisodeParIdFlux(unFlux));
		}

		EpisodeListAdapter adpt = new EpisodeListAdapter(this.context, listeEpisode);
		// paramèter l'adapter sur la listview
		this.listeView.setAdapter(adpt);
	}

	@Override
	protected void onProgressUpdate(String... prog) {
		// À chaque avancement du téléchargement, on met à jour la boîte de dialogue
		this.progressDialog.setMessage(prog[0]);
	}

}