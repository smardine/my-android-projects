package fr.smardine.podcaster.database;

import fr.smardine.podcaster.database.structure.EnTable;

/**
 * @author smardine rempli la base avec des donnees de test
 */
public final class G_remplirBase {

	/**
	 * @return the scriptRemplirBaseTest
	 */
	public static String[] getScriptRemplirBaseTest() {
		return SCRIPT_REMPLIR_BASE_TEST;
	}

	/**
	 * Constructeur prive pour classe utilitaire
	 */
	private G_remplirBase() {

	}

	/**
	 * scripts d'instertion en base
	 */
	private static final String[] SCRIPT_REMPLIR_BASE_TEST = {
			"INSERT INTO "+EnTable.FLUX+" (TITRE_FLUX, DATE_DERNIERE_SYNCHRO, VIGNETTE_URL, VIGNETTE, ID_PARAMETRE, ID_CATEGORIE, URL) "+
					"values " +
					"(Laurent Gerra, 01/01/2012,null,null,-1,-1,http://www.rtl.fr/podcast/laurent-gerra.xml"
					
			};

}
