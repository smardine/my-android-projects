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

		ProgressDialog myPd_bar = new ProgressDialog(ctx);
		myPd_bar.setMessage("Loading....");
		myPd_bar.setTitle("Synchro en cours..");
		myPd_bar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		myPd_bar.setProgress(0);
		myPd_bar.setMax(100);
		myPd_bar.show();
		RSSReaderAsynckTask runnableReaderAsynck = new RSSReaderAsynckTask(this.ctx, this.listeUrl, myPd_bar, this.listView);
		runnableReaderAsynck.execute();

	}

}
