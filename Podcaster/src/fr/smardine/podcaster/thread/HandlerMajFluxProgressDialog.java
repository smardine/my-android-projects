/**
 * 
 */
package fr.smardine.podcaster.thread;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.Toast;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.adapter.EpisodeListAdapter;
import fr.smardine.podcaster.adapter.FluxListAdapter;
import fr.smardine.podcaster.helper.log.EnNiveauLog;
import fr.smardine.podcaster.helper.log.LogCatBuilder;
import fr.smardine.podcaster.mdl.MlListeEpisode;
import fr.smardine.podcaster.mdl.MlListeFlux;

/**
 * @author smardine
 */
public class HandlerMajFluxProgressDialog extends Handler {
	private final String TAG = this.getClass().getSimpleName();
	private final Context context;
	private ProgressDialog myProgressDialog;
	private final EnMethodType methode;
	private final ListView listView;
	private MlListeFlux listeFlux;
	private MlListeEpisode listeEpisodes;
	// private final String dateEtatSynchro;
	private static Boolean arreterAnalyseMemorise = false;

	private void setArreterAnalyseMemorise(boolean p_value) {
		arreterAnalyseMemorise = p_value;
	}

	public static boolean getArreterAnalyseMemorise() {
		return arreterAnalyseMemorise;
	}

	public HandlerMajFluxProgressDialog(Context p_context, EnMethodType p_method, ListView p_listView, boolean p_arreterAnalyseMemoire) {
		this.context = p_context;
		this.methode = p_method;
		this.listView = p_listView;
		// this.dateEtatSynchro = p_dateEtatSynchro;
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
				Toast.makeText(context, "Tache annulée", Toast.LENGTH_LONG).show();// gotoAff_Tournees();
			}
		});
		if (methode == EnMethodType.CREATE_FLUX) {
			myProgressDialog.setTitle(R.string.progress_create_flux);
		} else {
			myProgressDialog.setTitle(R.string.progress_maj_flux);
		}
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

	public void ValoriserListeFlux(MlListeFlux p_listeFlux) {
		this.listeFlux = p_listeFlux;
	}

	public void ValoriserListeEpisode(MlListeEpisode p_listeEpisode) {
		this.listeEpisodes = p_listeEpisode;
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
				metAjourLaListeEnFonctionDuMode();
			case STOP: // 3:
				if (!arreterAnalyseMemorise) { // 1
					// memoriseDateSynchro(dateEtatSynchro);
					info = context.getString(R.string.s_analyseMemoriseTermine, (Object) null);
					Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
				}
				myProgressDialog.dismiss(); // 1 et 3

				break;
			case SUCCESS_BUTEMPTY: // 4:
				// memoriseDateSynchro(dateEtatSynchro);
				info = context.getString(R.string.s_analyseTermineVide, (Object) null);
				Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
				myProgressDialog.dismiss(); // 4
				break;
			case ERROR: // 2:
				// sortie dismiss+indic erreur
				info = context.getString(R.string.s_analyseMemoriseError, (Object) null);
				// if (synchroPlantra_xml_Info != null) {
				// // indication identifiant où data à
				// // synchro absente
				// int resID = context.getResources().getIdentifier(synchroPlantra_xml_Info.getEnInfoCode().getResStringName(),
				// StringHelper.RES_STRING, context.getPackageName());
				// info = context.getResources().getText(resID).toString();
				// }
				Toast.makeText(context, info, Toast.LENGTH_LONG).show();
				myProgressDialog.dismiss();
				break;
		}
	}

	private void metAjourLaListeEnFonctionDuMode() {
		if (methode == EnMethodType.MAJ_FLUX) {
			EpisodeListAdapter adpt = new EpisodeListAdapter(this.context, this.listeEpisodes);
			this.listView.setAdapter(adpt);
		} else if (methode == EnMethodType.CREATE_FLUX) {
			FluxListAdapter adpt = new FluxListAdapter(this.context, listeFlux);
			// paramèter l'adapter sur la listview
			this.listView.setAdapter(adpt);
		}

	}

	// /**
	// * Synchronisation terminée alors mémoriser la date de cette dernière synchro
	// * @param p_xmlInfo
	// */
	// private void memoriseDateSynchro(String p_dateEtatSynchro) {
	// if (p_dateEtatSynchro != null) {
	// // PrefsEquinox prefs = new PrefsEquinox(context);
	// // prefs.setdate_synchro(p_dateEtatSynchro);
	// // prefs.closeEquinoxPrefs();
	// }
	// }

	/**
	 * Grace au context passé à cet objet métier, aller directement dans la vue des tournées.
	 */
	// private void gotoAff_Tournees() {
	// if (context instanceof Activity) {
	// // pour démarrer cette nouvelle instance utiliser l'instance de
	// // l'activity qui a provoqué une synchro et passer son propre
	// // requestcode
	// Activity myactivity = ((Activity) context);
	//
	// int requestcode = (myactivity.getIntent().getExtras() == null ? 0 : myactivity.getIntent().getExtras()
	// .getInt(ExtrasEquinox.EXTRA_STARTACTIVITY_REQUESTCODE, 0));
	// // si l'ordre de synchronisation ne provient pas du menu de la vue
	// // des tournées alors aller dans les tournées
	// EnActivityCode enRequestCode = EnActivityCode.fromCode(requestcode);
	// switch (enRequestCode) {
	// case AFF_TOURNEES_REQUEST_CODE:
	// // la MEGA astuce pour provoquer le refresh de la vue des
	// // tournées suite à une synchro lancée à causse d'une
	// // modification de licence !
	// myactivity.onContentChanged();
	// break;
	// case AFF_VISITE_LIST_REQUEST_CODE:
	// myactivity.finish(); // retour dans aff_tournees
	// break;
	// default:
	// // aller dans la vue des tournées
	// Intent intent = new Intent(context, MainTabActivity.class);
	// myactivity.startActivityForResult(intent, requestcode);
	// myactivity.overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
	// }
	// }
	// }

}
