/**
 * 
 */
package fr.smardine.podcaster.thread.hanlder;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageButton;
import android.widget.Toast;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.adapter.EnStatutVisibilite;
import fr.smardine.podcaster.helper.log.EnNiveauLog;
import fr.smardine.podcaster.helper.log.LogCatBuilder;
import fr.smardine.podcaster.thread.EnThreadExecResult;

/**
 * @author smardine
 */
public class HandlerDownloadEpisodeProgressDialog extends Handler {
	private final String TAG = this.getClass().getSimpleName();
	private final Context context;
	private ProgressDialog myProgressDialog;

	private ImageButton imageButton;
	// private final String dateEtatSynchro;
	private static Boolean arreterAnalyseMemorise = false;

	private void setArreterAnalyseMemorise(boolean p_value) {
		arreterAnalyseMemorise = p_value;
	}

	public static boolean getArreterAnalyseMemorise() {
		return arreterAnalyseMemorise;
	}

	public HandlerDownloadEpisodeProgressDialog(Context p_context, boolean p_arreterAnalyseMemoire, ImageButton imdTelechargeEpisode) {
		this.context = p_context;
		this.imageButton = imdTelechargeEpisode;

		setArreterAnalyseMemorise(p_arreterAnalyseMemoire);
		initProgressDialog(p_context);
	}

	/**
	 * Initialisation de la boite de dialogue "progression".
	 * @param p_context - Context.
	 */
	private void initProgressDialog(Context p_context) {
		myProgressDialog = new ProgressDialog(p_context);
		myProgressDialog.setOnCancelListener(new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				setArreterAnalyseMemorise(true);
				sendMessage(obtainMessage(EnThreadExecResult.STOP.getCode()));// 3_STOP
			}
		});
		myProgressDialog.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
				// gotoAff_PagePrincipale();
				// Toast.makeText(context, "Tache annulée", Toast.LENGTH_LONG).show();// gotoAff_Tournees();
			}
		});
		// if (methode == EnMethodType.CREATE_FLUX) {
		// myProgressDialog.setTitle(R.string.progress_create_flux);
		// } else {
		myProgressDialog.setTitle(R.string.progress_download_episode);
		// }
		myProgressDialog.setIcon(android.R.drawable.ic_dialog_info);
		myProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		myProgressDialog.setButton(context.getText(R.string.b_cacher), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				Toast.makeText(context, context.getText(R.string.s_analyseMemoriseHide), Toast.LENGTH_SHORT).show();
			}
		});
		myProgressDialog.setButton2(context.getText(R.string.b_annuler), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				setArreterAnalyseMemorise(true);
				sendMessage(obtainMessage(EnThreadExecResult.STOP.getCode()));// 3_STOP
				LogCatBuilder.WriteInfoToLog(context, EnNiveauLog.INFO, TAG, R.string.info_synchrocancelmanuel);
			}
		});
		myProgressDialog.show();
	}

	@Override
	public void handleMessage(Message msg) {
		String info = "";
		EnThreadExecResult threadExecResult = EnThreadExecResult.fromCode(msg.what);
		switch (threadExecResult) {
			case CHANGE_TITRE:// -1
				myProgressDialog.setTitle("" + msg.obj);
				break;
			case ENCOURS: // 0:
				myProgressDialog.setMessage("" + msg.obj);
				break;
			case SUCCESS: // 1:
				metAJourVisibilite();
			case STOP: // 3:
				if (!arreterAnalyseMemorise) { // 1
					info = context.getString(R.string.s_analyseMemoriseTermine, (Object) null);
					Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
				}
				myProgressDialog.dismiss(); // 1 et 3
				// gotoAff_PagePrincipale();

				break;
			case SUCCESS_BUTEMPTY: // 4:
				info = context.getString(R.string.s_analyseTermineVide, (Object) null);
				Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
				myProgressDialog.dismiss(); // 4
				break;
			case ERROR: // 2:
				// sortie dismiss+indic erreur
				info = context.getString(R.string.s_analyseMemoriseError, (Object) null);
				Toast.makeText(context, info, Toast.LENGTH_LONG).show();
				myProgressDialog.dismiss();
				break;
		}
	}

	private void metAJourVisibilite() {
		this.imageButton.setVisibility(EnStatutVisibilite.INVISIBLE.getCode());
	}

}
