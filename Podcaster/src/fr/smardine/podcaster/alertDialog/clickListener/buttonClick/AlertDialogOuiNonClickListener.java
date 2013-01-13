package fr.smardine.podcaster.alertDialog.clickListener.buttonClick;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.ListView;
import fr.smardine.podcaster.alertDialog.IAlertDialogClickListener;
import fr.smardine.podcaster.metier.RSSReaderAsynckTask;

/**
 * Gestion des evenements lors du click sur le bouton Oui sur une boite de dialogue "Oui/Non"
 * @author smardine
 */
public class AlertDialogOuiNonClickListener implements IAlertDialogClickListener, OnClickListener {

	private final Context ctx;
	private ListView listView;
	private List<String> listeUrl;

	/**
	 * Constructeur
	 * @param urlsChecked
	 * @param handle
	 * @param listView
	 * @param p_type - le type d'alertDialog concerné
	 * @param p_marque - eventuellement la marque à "pusher" vers le site
	 */
	public AlertDialogOuiNonClickListener(Context p_ctx, ListView p_listView, List<String> urlsChecked) {
		this.ctx = p_ctx;
		this.listView = p_listView;
		this.listeUrl = urlsChecked;

	}

	@Override
	public void onClick(DialogInterface p_arg0, int p_arg1) {
		// final AccesTableFlux tableFlux = new AccesTableFlux(this.ctx);

		ProgressDialog myPd_bar = new ProgressDialog(ctx);
		myPd_bar.setMessage("Loading....");
		myPd_bar.setTitle("Synchro en cours..");
		myPd_bar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		myPd_bar.setProgress(0);
		myPd_bar.setMax(100);
		myPd_bar.show();
		RSSReaderAsynckTask runnableReaderAsynck = new RSSReaderAsynckTask(this.ctx, this.listeUrl, myPd_bar, this.listView);
		runnableReaderAsynck.execute();
		// RSSReaderThread runnableReader = new RSSReaderThread(this.ctx, this.listeUrl, this.myPd_bar, this.listView);
		// runnableReader.start();

		// // on recupere la liste des flux en base et on rafraichi la liste presentée a l'ecran
		// MlListeFlux listeFlux = tableFlux.getListeDesFlux();
		//
		// FluxListAdapter adpt = new FluxListAdapter(ctx, listeFlux);
		// // paramèter l'adapter sur la listview
		// listView.setAdapter(adpt);

	}
	// /**
	// * Envoi la marque sur le serveur via une requette HTTP
	// * @param p_marque - la marque a envoyer
	// */
	// private void PostMarqueSurServeur(String p_marque) {
	// String TAG = "fr.smardine.matroussedemaquillage.remplir";
	// DefaultHttpClient httpClient = new DefaultHttpClient();
	// HttpPost httpPost = new HttpPost(
	// "http://simon.mardine.free.fr/trousse_maquillage/nouveautes/postmarque.php");
	// List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	//
	// nvps.add(new BasicNameValuePair("marque", p_marque));
	// try {
	// httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
	//
	// // We don't care about the response, so we just hope it went well
	// // and on
	// // with it
	//
	// HttpResponse response = httpClient.execute(httpPost);
	//
	// BufferedReader reader = null;
	//
	// reader = new BufferedReader(new InputStreamReader(response
	// .getEntity().getContent()));
	//
	// String strLine;
	//
	// while ((strLine = reader.readLine()) != null) {
	// Log.d(TAG, "reponse du post : " + strLine);
	// }
	// } catch (IOException e) {
	//
	// Log.d(TAG, "IOException: " + e);
	// return;
	// // e.printStackTrace();
	// } catch (IllegalStateException e1) {
	//
	// Log.d(TAG, "IllegalStateException: " + e1);
	// return;
	// }
	// }

}
