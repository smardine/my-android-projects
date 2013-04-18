package fr.smardine.monvetcarnet.helper.log;

import android.content.Context;
import android.util.Log;

/**
 * Cette classe s'occupe de la gestion de l'ecriture dans le logcat
 * @author smardine
 */
public final class LogCatBuilder {

	/**
	 * Constructeur privé pour une classe utilitaire
	 */
	private LogCatBuilder() {

	}

	/**
	 * S'occupe d'ecrire les message d'errreur dans le logcat
	 * @param p_context - Context
	 * @param p_tag - String - le tag de la classe java
	 * @param p_resId - int - lien vers le fichier {@link string}
	 * @param p_exception - Exception - l'exception à logué
	 */
	public static void WriteErrorToLog(Context p_context, String p_tag, int p_resId, Exception p_exception, Object... p_formatArgs) {

		Log.e(p_tag, p_context.getString(p_resId, p_formatArgs));
		if (p_exception == null) {
			throw new RuntimeException("Avec un niveau de log Erreur, il est obligatoire de passer une exception en parametre");

		} else {
			// si egal null, marque "inconnu" sinon, marque le message
			String s = (p_exception.getMessage() == null ? "Exception inconnue" : p_exception.getMessage());
			Log.e(p_tag, s);

		}

	}

	/**
	 * Soccupe d'ecrire les messages dans le log cat <b> Sauf les erreurs</b>
	 * @param p_context - Context
	 * @param p_niveauLog - {@link EnNiveauLog} - le niveau d'information du message (info, debug...)
	 * @param p_tag - String - le tag de la classe java
	 * @param p_resId - int - le lien vers le fichier {@link string}
	 * @throws RuntimeException - si on utilise un niveau d'info {@link EnNiveauLog.ERROR}
	 */
	public static void WriteInfoToLog(Context p_context, EnNiveauLog p_niveauLog, String p_tag, int p_resId) {
		switch (p_niveauLog) {
			case VERBOSE:
				Log.v(p_tag, p_context.getString(p_resId));
				break;
			case DEBUG:
				Log.d(p_tag, p_context.getString(p_resId));
				break;
			case INFO:
				Log.i(p_tag, p_context.getString(p_resId));
				break;
			case WARNING:
				Log.w(p_tag, p_context.getString(p_resId));
				break;
			case ERROR:
				throw new RuntimeException("Cette methode n'est pas faites pour la gestion des erreurs, utilisez WriteErrorToLog");
		}
	}

	/**
	 * Ecrire un message p_resId formaté avec les indications p_formatArgs dans LogCat SAUF les ERROR
	 * @param p_context
	 * @param p_niveauLog niveau
	 * @param p_tag classe name
	 * @param p_resId strings.id
	 * @param p_formatArgs formatter
	 */
	public static void WriteInfoToLog(Context p_context, EnNiveauLog p_niveauLog, String p_tag, int p_resId, Object... p_formatArgs) {
		switch (p_niveauLog) {
			case VERBOSE:
				Log.v(p_tag, p_context.getString(p_resId, p_formatArgs));
				break;
			case DEBUG:
				Log.d(p_tag, p_context.getString(p_resId, p_formatArgs));
				break;
			case INFO:
				Log.i(p_tag, p_context.getString(p_resId, p_formatArgs));
				break;
			case WARNING:
				Log.w(p_tag, p_context.getString(p_resId, p_formatArgs));
				break;
			case ERROR:
				throw new RuntimeException("Cette methode n'est pas faites pour la gestion des erreurs, utilisez WriteErrorToLog");
		}
	}

}
