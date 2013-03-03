package fr.smardine.podcaster.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import fr.smardine.podcaster.database.accestable.AccesTableEpisode;
import fr.smardine.podcaster.helper.DownloadHelper;
import fr.smardine.podcaster.mdl.MlEpisode;

/**
 * Parser une liste de flux RSS avec une barre de progression
 */

public class DownloadEpisodeAsynckTask extends AsyncTask<String, MlEpisode, Boolean> {

	// private MlFlux unFlux;
	private final Context context;
	private MlEpisode unEpisode;
	protected ProgressDialog progressDialog;

	// private AccesTableFlux tableFlux;
	private AccesTableEpisode tableEpisode;

	/**
	 * Constructeur
	 * @param p_context
	 * @param holder
	 * @param p_listeFlux
	 * @param p_progressDialog
	 * @param p_listView
	 */
	public DownloadEpisodeAsynckTask(Context p_context, MlEpisode p_episode) {
		this.context = p_context;
		this.unEpisode = p_episode;
	}

	@Override
	protected void onPreExecute() {
		// tableFlux = new AccesTableFlux(this.context);
		tableEpisode = new AccesTableEpisode(this.context);

		progressDialog = new ProgressDialog(this.context);
		// progressDialog.setMessage("");
		progressDialog.setTitle("Telechargement de l'episode");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setProgress(0);
		progressDialog.setMax(100);
		progressDialog.show();
	}

	@Override
	protected Boolean doInBackground(String... params) {

		return DownloadHelper.DownloadEpisodeFromUrl(context, unEpisode.getUrlMedia(), unEpisode);
	}

	@Override
	public void onProgressUpdate(MlEpisode... prog) {
		// À chaque avancement du téléchargement, on met à jour la boîte de dialogue
		this.progressDialog.setMessage(prog[0].getTitre());
	}

	@Override
	protected void onPostExecute(Boolean result) {

		this.progressDialog.dismiss();
		if (result.booleanValue()) {
			tableEpisode.majEpisode(unEpisode);
			// this.viewHolder.holder.ImdTelechargeEpisode.setVisibility(EnStatutVisibilite.INVISIBLE.getCode());
		}

	}

}