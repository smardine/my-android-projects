package fr.smardine.podcaster.asynctask;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import fr.smardine.podcaster.adapter.FluxListAdapter;
import fr.smardine.podcaster.database.accestable.AccesTableEpisode;
import fr.smardine.podcaster.database.accestable.AccesTableFlux;
import fr.smardine.podcaster.mdl.MlEpisode;
import fr.smardine.podcaster.mdl.MlFlux;
import fr.smardine.podcaster.mdl.MlListeFlux;
import fr.smardine.podcaster.metier.RssParserMetier;

/**
 * Parser une liste de flux RSS avec une barre de progression
 */

public class RSSReaderAsynckTask extends AsyncTask<Void, Void, Void> {

	// private MlFlux unFlux;
	private final Context context;
	private List<String> listeFlux;
	private ProgressDialog progressDialog;
	private ListView listeView;
	private AccesTableFlux tableFlux;
	private AccesTableEpisode tableEpisode;

	/**
	 * Constructeur
	 * @param p_context
	 * @param p_listeFlux
	 * @param p_progressDialog
	 * @param p_listView
	 */
	public RSSReaderAsynckTask(Context p_context, List<String> p_listeFlux, ProgressDialog p_progressDialog, ListView p_listView) {
		this.context = p_context;
		this.listeFlux = p_listeFlux;
		this.progressDialog = p_progressDialog;
		this.listeView = p_listView;
	}

	@Override
	protected void onPreExecute() {
		tableFlux = new AccesTableFlux(this.context);
		tableEpisode = new AccesTableEpisode(this.context);
	}

	@Override
	protected Void doInBackground(Void... params) {
		RssParserMetier parserMetier = new RssParserMetier();
		for (String uneUrl : listeFlux) {
			MlFlux fluxParse = parserMetier.parseUnFlux(this.context, uneUrl);
			if (fluxParse != null) {
				tableFlux.createFlux(fluxParse);
				for (MlEpisode uneEpisode : fluxParse.getListeEpisode()) {
					uneEpisode.setIdFluxParent(fluxParse.getIdFlux());
					uneEpisode.setVignetteTelechargee(fluxParse.getVignetteTelechargee());
					tableEpisode.createEpisode(uneEpisode);
				}
			}
		}
		progressDialog.dismiss();
		return null;

	}

	@Override
	protected void onPostExecute(Void result) {
		// on recupere la liste des flux en base et on rafraichi la liste presentée a l'ecran
		MlListeFlux listeFlux = tableFlux.getListeDesFlux();

		FluxListAdapter adpt = new FluxListAdapter(this.context, listeFlux);
		// paramèter l'adapter sur la listview
		this.listeView.setAdapter(adpt);
	}

}