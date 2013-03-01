package fr.smardine.podcaster.thread;

import java.util.List;

import android.content.Context;
import android.widget.ListView;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.helper.log.LogCatBuilder;
import fr.smardine.podcaster.mdl.MlFlux;

/**
 * Boite de dialogue visuelle ouverte à partir d'une vue dont le context est passé en paramètre du constructeur. Cette boite présente un
 * composant de progression circulaire. Le processus métier ppd est exécuté dans un thread, il est interrompable . La boite de dialogue peut
 * être cachée pour que l'utilisateur procède à d'autres manipulations.
 * @author amsuc
 */
public class MajFluxProgressDialog {
	private final String TAG = this.getClass().getName();
	private Context mycontext = null;

	// private EnParserType type = EnParserType.SAX;
	// private EnMethodType method = EnMethodType.CREATE_FLUX;
	// private String numeroclient = null;
	// private String motpasse = null;

	// private String date_EtatSynchro = null;

	// private final Xml_Info synchroPlantra_xml_Info = null;

	/**
	 * Créer une boite de dialoque munie d'une barre de progression Lancer un thread qui exécute le téléchargement > déchiffrement >
	 * décompression > analyse > mémorisation base des données de synchronisation de type plantra.
	 * @param p_context
	 * @param p_type
	 * @param p_method
	 * @param p_dateEtatSynchro date de la synchro hébergée sur le serveur et que l'on veut télécharger
	 */
	public void synchroMajFluxProgressDialog(Context p_context, List<MlFlux> p_listeFlux, ListView p_listView) {
		mycontext = p_context;

		// method = p_method;
		// numeroclient = p_numeroclient;
		// motpasse = p_motpasse;
		// date_EtatSynchro = p_dateEtatSynchro;

		try {
			final HandlerMajFluxProgressDialog progressDialogHandler = new HandlerMajFluxProgressDialog(mycontext, EnMethodType.MAJ_FLUX,
					p_listView, false);

			new ThreadExecutionMajFlux(mycontext, progressDialogHandler, p_listeFlux).start();

		} catch (Exception e) {
			LogCatBuilder.WriteErrorToLog(mycontext, TAG, R.string.app_errGrave, e);

		}
	}

	/**
	 * Créer une boite de dialoque munie d'une barre de progression Lancer un thread qui exécute le téléchargement > déchiffrement >
	 * décompression > analyse > mémorisation base des données de synchronisation de type plantra.
	 * @param p_context
	 * @param p_type
	 * @param p_method
	 * @param p_dateEtatSynchro date de la synchro hébergée sur le serveur et que l'on veut télécharger
	 */
	public void synchroCreateFluxProgressDialog(Context p_context, List<String> p_listeUrl, ListView p_listView) {
		mycontext = p_context;

		// method = p_method;
		// numeroclient = p_numeroclient;
		// motpasse = p_motpasse;
		// date_EtatSynchro = p_dateEtatSynchro;

		try {
			final HandlerMajFluxProgressDialog progressDialogHandler = new HandlerMajFluxProgressDialog(mycontext,
					EnMethodType.CREATE_FLUX, p_listView, false);

			new ThreadExecutionCreateFlux(mycontext, progressDialogHandler, p_listeUrl).start();

		} catch (Exception e) {
			LogCatBuilder.WriteErrorToLog(mycontext, TAG, R.string.app_errGrave, e);

		}
	}

}
