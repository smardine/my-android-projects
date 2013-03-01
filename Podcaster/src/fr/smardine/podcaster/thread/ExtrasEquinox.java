package fr.smardine.podcaster.thread;

public final class ExtrasEquinox {
	/**
	 * Constructeur priv� pour classe utilitaire
	 */
	private ExtrasEquinox() {

	}

	// key name du bundle de passage de l'id du b�n�ficiaire selectionn�
	public static final String EXTRA_BNF_ID = "EXTRA_BNF_ID";
	// key name du bundle de passage du num�ro client EQUINOX du b�n�f.
	// s�lectionn�
	public static final String EXTRA_BNF_CODE = "EXTRA_BNF_CODE";
	// key name du bundle de passage du JJ/MM pour test anniversaire
	public static final String EXTRA_BNF_DATENAIS = "EXTRA_BNF_DATENAIS";
	// key name du bundle de passage de l'id de la tourn�e selectionn�e
	public static final String EXTRA_TRN_ID = "EXTRA_TRN_ID";
	// key name du bundle de passage de l'id du RDV s�lectionn�
	public static final String EXTRA_VST_ID = "EXTRA_VST_ID";
	// key name du bundle indicateur de premi�re entr�e dans le logiciel
	public static final String EXTRA_ACCUEIL_1ERENTREE = "EXTRA_ACCUEIL_1ERENTREE";
	// key name du bundle de passage du RequestCode lors d'un
	// startActivityForResult() pour que l'activit� d�marr�e puisse le r�cup�rer
	public static final String EXTRA_STARTACTIVITY_REQUESTCODE = "EXTRA_STARTACTIVITY_REQUESTCODE";
	// key name du bundle de passage du motif du uncaught exception (pass� �
	// main)
	public static final String EXTRA_UNCAUGHTEXCEPTION_MESSAGE = "EXTRA_UNCAUGHTEXCEPTION_MESSAGE";
	// key name du bundle du chemin complet du fichier HTM
	public static final String EXTRA_CHEMIN_HTML = "EXTRA_CHEMIN_HTML";
	// key name du bundle index de la position de photo � afficher en d�tail
	public static final String EXTRA_BENEFPHOTO_POSITION = "EXTRA_BENEFPHOTO_POSITION";
	// key name du bundle d'indication du chemin relatif dans \assets
	public static final String EXTRA_ASSETS_RELATIVEPATH = "EXTRA_ASSETS_RELATIVEPATH";
}
