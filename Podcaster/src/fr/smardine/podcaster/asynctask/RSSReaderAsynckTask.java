package fr.smardine.podcaster.asynctask;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

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

public class RSSReaderAsynckTask extends AsyncTask<Void, MlFlux, Void> {

	// private MlFlux unFlux;
	private final Context context;
	private List<String> listeFlux;
	protected ProgressDialog progressDialog;
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
	public RSSReaderAsynckTask(Context p_context, List<String> p_listeFlux, ListView p_listView) {
		this.context = p_context;
		this.listeFlux = p_listeFlux;
		this.listeView = p_listView;
	}

	@Override
	protected void onPreExecute() {
		tableFlux = new AccesTableFlux(this.context);
		tableEpisode = new AccesTableEpisode(this.context);

		progressDialog = new ProgressDialog(this.context);
		// progressDialog.setMessage("");
		progressDialog.setTitle("Construction du flux");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setProgress(0);
		progressDialog.setMax(100);
		progressDialog.show();
	}

	@Override
	protected Void doInBackground(Void... params) {

		for (String uneUrl : listeFlux) {
			MlFlux fluxParse = this.parseUnFlux(this.context, uneUrl);
			if (fluxParse != null) {
				tableFlux.createFlux(fluxParse);
				for (MlEpisode uneEpisode : fluxParse.getListeEpisode()) {
					uneEpisode.setIdFluxParent(fluxParse.getIdFlux());
					uneEpisode.setVignetteTelechargee(fluxParse.getVignetteTelechargee());
					tableEpisode.createEpisode(uneEpisode);
				}
			}
		}
		return null;

	}

	@Override
	public void onProgressUpdate(MlFlux... prog) {
		// À chaque avancement du téléchargement, on met à jour la boîte de dialogue
		this.progressDialog.setMessage(prog[0].getTitre());
	}

	@Override
	protected void onPostExecute(Void result) {
		// on recupere la liste des flux en base et on rafraichi la liste presentée a l'ecran
		MlListeFlux listeFlux = tableFlux.getListeDesFlux();
		FluxListAdapter adpt = new FluxListAdapter(this.context, listeFlux);
		// paramèter l'adapter sur la listview
		this.listeView.setAdapter(adpt);
		this.progressDialog.dismiss();
	}

	private MlFlux parseUnFlux(Context p_context, String p_urlAParser) {
		MlFlux unFlux = new MlFlux();
		Document doc = RssParserMetier.OuvrirUrl(p_urlAParser);
		if (doc != null) {
			Node node = doc.getDocumentElement();

			RssParserMetier.contruitEnteteFlux(node, unFlux, p_context, p_urlAParser);
			publishProgress(unFlux);
			RssParserMetier.parcourirNodeListeEtValoriseListeEpisode(unFlux, doc);

			return unFlux;
		}
		return null;

	}

}