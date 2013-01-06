package fr.smardine.podcaster.alertDialog.clickListener.buttonClick;

import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.ListView;
import fr.smardine.podcaster.adapter.FluxListAdapter;
import fr.smardine.podcaster.alertDialog.IAlertDialogClickListener;
import fr.smardine.podcaster.database.accestable.AccesTableEpisode;
import fr.smardine.podcaster.database.accestable.AccesTableFlux;
import fr.smardine.podcaster.mdl.MlEpisode;
import fr.smardine.podcaster.mdl.MlFlux;
import fr.smardine.podcaster.mdl.MlListeFlux;
import fr.smardine.podcaster.metier.RSSReader;

/**
 * Gestion des evenements lors du click sur le bouton Oui sur une boite de dialogue "Oui/Non"
 * @author smardine
 */
public class AlertDialogOuiNonClickListener implements IAlertDialogClickListener, OnClickListener {

	private final String fluxUrl;
	private final Context ctx;
	private ListView listView;

	/**
	 * Constructeur
	 * @param listView
	 * @param p_type - le type d'alertDialog concerné
	 * @param p_marque - eventuellement la marque à "pusher" vers le site
	 */
	public AlertDialogOuiNonClickListener(String p_fluxUrl, Context p_ctx, ListView p_listView) {
		this.fluxUrl = p_fluxUrl;
		this.ctx = p_ctx;
		this.listView = p_listView;
	}

	@Override
	public void onClick(DialogInterface p_arg0, int p_arg1) {
		AccesTableFlux tableFlux = new AccesTableFlux(this.ctx);
		RSSReader reader = new RSSReader(ctx);
		reader.execute(fluxUrl);
		MlFlux unFlux = null;
		try {
			unFlux = reader.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tableFlux.createFlux(unFlux);
		AccesTableEpisode tableEpisode = new AccesTableEpisode(this.ctx);
		for (MlEpisode unEpisode : unFlux.getListeEpisode()) {
			unEpisode.setIdFluxParent(unFlux.getIdFlux());
			tableEpisode.createEpisode(unEpisode);
		}

		// on recupere la liste des flux en base et on rafraichi la liste presentée a l'ecran
		MlListeFlux listeFlux = new AccesTableFlux(this.ctx).getListeDesFlux();

		FluxListAdapter adpt = new FluxListAdapter(ctx, listeFlux);
		// paramèter l'adapter sur la listview
		listView.setAdapter(adpt);

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
