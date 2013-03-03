package fr.smardine.podcaster.thread.hanlder;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/**
 * S'occupe de faire passer les messages au handler de boite de dialogue
 * @author smardine
 */
public final class handlerUtility {

	/**
	 * Constructeur privé pour classe utilitaire
	 */
	private handlerUtility() {

	}

	/**
	 * Envoi un message d'erreur vers la dialogBox
	 * @param p_progressDialogHandler - Handler - le Handler de la dialogBox
	 * @param errorMessage - String - le message à afficher
	 */
	public static void showErrorDialog(Handler p_progressDialogHandler,
			String errorMessage) {
		Message m = new Message();
		m.what = 1;// erreur
		m.obj = errorMessage;
		p_progressDialogHandler.sendMessage(m);
	}

	/**
	 * Envoi un message de fin vers la dialogBox
	 * @param p_progressDialogHandler - Handler - le Handler de la dialogBox
	 * @param p_message - String - le message a afficher
	 */
	public static void showCompleteDialog(Handler p_progressDialogHandler,
			String p_message) {
		Message m = new Message();
		m.what = 2;// reussite
		m.obj = p_message;
		p_progressDialogHandler.sendMessage(m);
	}

	/**
	 * Envoi un message d'information vers la dialogbox
	 * @param p_progressDialogHandler - Handler - le Handler de la dialogBox
	 * @param p_message - String - le message a afficher
	 */
	public static void updateProgressDialog(Handler p_progressDialogHandler,
			String p_message) {
		Message m = new Message();
		m.what = 0;
		m.obj = p_message;
		p_progressDialogHandler.sendMessage(m);

	}

	public static void handleMessage(Context p_context,
			ProgressDialog p_myProgressDialog, Message p_msg, int p_idMessageErreur) {
		switch (p_msg.what) {
			case 0:// info utilisateur
				p_myProgressDialog.setMessage("" + p_msg.obj);
				break;
			case 1:// erreur survenue
				Toast.makeText(p_context, "" + p_msg.obj, Toast.LENGTH_LONG)
						.show();
				Toast.makeText(p_context, p_context.getString(p_idMessageErreur),
						Toast.LENGTH_LONG).show();
				p_myProgressDialog.dismiss();
				break;
			case 2:// execution terminée et reussie

				Toast.makeText(p_context, "" + p_msg.obj, Toast.LENGTH_LONG)
						.show();
				p_myProgressDialog.dismiss();
				break;
		}
	}

}
