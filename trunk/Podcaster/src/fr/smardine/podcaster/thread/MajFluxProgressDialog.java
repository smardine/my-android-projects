package fr.smardine.podcaster.thread;

import java.util.List;

import android.content.Context;
import android.widget.ListView;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.helper.log.LogCatBuilder;
import fr.smardine.podcaster.mdl.MlFlux;

/**
 * Boite de dialogue visuelle ouverte � partir d'une vue dont le context est pass� en param�tre du constructeur. Cette boite pr�sente un
 * composant de progression circulaire. Le processus m�tier ppd est ex�cut� dans un thread, il est interrompable . La boite de dialogue peut
 * �tre cach�e pour que l'utilisateur proc�de � d'autres manipulations.
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
	 * Cr�er une boite de dialoque munie d'une barre de progression Lancer un thread qui ex�cute le t�l�chargement > d�chiffrement >
	 * d�compression > analyse > m�morisation base des donn�es de synchronisation de type plantra.
	 * @param p_context
	 * @param p_type
	 * @param p_method
	 * @param p_dateEtatSynchro date de la synchro h�berg�e sur le serveur et que l'on veut t�l�charger
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
	 * Cr�er une boite de dialoque munie d'une barre de progression Lancer un thread qui ex�cute le t�l�chargement > d�chiffrement >
	 * d�compression > analyse > m�morisation base des donn�es de synchronisation de type plantra.
	 * @param p_context
	 * @param p_type
	 * @param p_method
	 * @param p_dateEtatSynchro date de la synchro h�berg�e sur le serveur et que l'on veut t�l�charger
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
