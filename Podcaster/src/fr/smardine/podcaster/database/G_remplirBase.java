package fr.smardine.podcaster.database;

/**
 * @author smardine rempli la base avec des donn�es de test
 */
public final class G_remplirBase {

	/**
	 * @return the scriptRemplirBaseTest
	 */
	public static String[] getScriptRemplirBaseTest() {
		return SCRIPT_REMPLIR_BASE_TEST;
	}

	/**
	 * Constructeur priv� pour classe utilitaire
	 */
	private G_remplirBase() {

	}

	/**
	 * scripts d'instertion en base
	 */
	private static final String[] SCRIPT_REMPLIR_BASE_TEST = {
			"INSERT INTO "
					+ "produit_Enregistre "
					+ "(nom_produit,nom_souscatergorie,nom_categorie,DateAchat,Duree_Vie,nom_marque) "
					+ "values "
					+ "(\"produit1\",\"Blush\",\"Yeux\",\"01-01-2012\",\"2\",\"Kiss me\")",
			"INSERT INTO "
					+ "produit_Enregistre "
					+ "(nom_produit,nom_souscatergorie,nom_categorie,DateAchat,Duree_Vie,nom_marque) "
					+ "values "
					+ "(\"produit2\",\"Poudres\",\"Yeux\",\"01-01-2010\",\"2\",\"Mac\")",
			"INSERT INTO "
					+ "produit_Enregistre "
					+ "(nom_produit,nom_souscatergorie,nom_categorie,DateAchat,Duree_Vie,nom_marque) "
					+ "values "
					+ "(\"produit3\",\"Blush\",\"Visage\",\"01-07-2010\",\"2\",\"Mac\")",
			"INSERT INTO "
					+ "produit_Enregistre "
					+ "(nom_produit,nom_souscatergorie,nom_categorie,DateAchat,Duree_Vie,nom_marque) "
					+ "values "
					+ "(\"produit4\",\"Poudres\",\"Visage\",\"01-09-2010\",\"2\",\"Mac\")",
			"INSERT INTO "
					+ "produit_Enregistre "
					+ "(nom_produit,nom_souscatergorie,nom_categorie,DateAchat,Duree_Vie,nom_marque) "
					+ "values "
					+ "(\"produit5\",\"Blush\",\"Yeux\",\"01-01-2011\",\"2\",\"Mac\")",
			"INSERT INTO "
					+ "produit_Enregistre "
					+ "(nom_produit,nom_souscatergorie,nom_categorie,DateAchat,Duree_Vie,nom_marque) "
					+ "values "
					+ "(\"produit6\",\"Poudres\",\"Visage\",\"01-07-2011\",\"2\",\"Mac\")",
			"INSERT INTO "
					+ "produit_Enregistre "
					+ "(nom_produit,nom_souscatergorie,nom_categorie,DateAchat,Duree_Vie,nom_marque) "
					+ "values "
					+ "(\"produit7\",\"Poudres\",\"Visage\",\"01-09-2011\",\"2\",\"Mac\")" };

}
