package fr.smardine.podcaster.thread;

public final class ExtrasEquinox {
	/**
	 * Constructeur prive pour classe utilitaire
	 */
	private ExtrasEquinox() {

	}

	// key name du bundle de passage de l'id du beneficiaire selectionne
	public static final String EXTRA_BNF_ID = "EXTRA_BNF_ID";
	// key name du bundle de passage du numero client EQUINOX du benef.
	// selectionne
	public static final String EXTRA_BNF_CODE = "EXTRA_BNF_CODE";
	// key name du bundle de passage du JJ/MM pour test anniversaire
	public static final String EXTRA_BNF_DATENAIS = "EXTRA_BNF_DATENAIS";
	// key name du bundle de passage de l'id de la tournee selectionnee
	public static final String EXTRA_TRN_ID = "EXTRA_TRN_ID";
	// key name du bundle de passage de l'id du RDV selectionne
	public static final String EXTRA_VST_ID = "EXTRA_VST_ID";
	// key name du bundle indicateur de premiere entree dans le logiciel
	public static final String EXTRA_ACCUEIL_1ERENTREE = "EXTRA_ACCUEIL_1ERENTREE";
	// key name du bundle de passage du RequestCode lors d'un
	// startActivityForResult() pour que l'activite demarree puisse le recuperer
	public static final String EXTRA_STARTACTIVITY_REQUESTCODE = "EXTRA_STARTACTIVITY_REQUESTCODE";
	// key name du bundle de passage du motif du uncaught exception (passe a
	// main)
	public static final String EXTRA_UNCAUGHTEXCEPTION_MESSAGE = "EXTRA_UNCAUGHTEXCEPTION_MESSAGE";
	// key name du bundle du chemin complet du fichier HTM
	public static final String EXTRA_CHEMIN_HTML = "EXTRA_CHEMIN_HTML";
	// key name du bundle index de la position de photo a afficher en detail
	public static final String EXTRA_BENEFPHOTO_POSITION = "EXTRA_BENEFPHOTO_POSITION";
	// key name du bundle d'indication du chemin relatif dans \assets
	public static final String EXTRA_ASSETS_RELATIVEPATH = "EXTRA_ASSETS_RELATIVEPATH";
}
