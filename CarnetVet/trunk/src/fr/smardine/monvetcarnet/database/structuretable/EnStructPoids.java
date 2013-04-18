package fr.smardine.monvetcarnet.database.structuretable;

/**
 * Classe représentant la structure de la table Poids
 * @author sims
 */
public class EnStructPoids extends SuperStructureTable {

	public static EnStructPoids ID_POID = new EnStructPoids("ID_POID", EnTypeChampsSQLite.INTEGER, null, 0); //

	public static EnStructPoids ID_CARNET_PARENT = new EnStructPoids("ID_CARNET", EnTypeChampsSQLite.INTEGER, null, 1); //

	public static EnStructPoids DATE = new EnStructPoids("DATE", EnTypeChampsSQLite.LONG, null, 2); //

	public static EnStructPoids VALEUR = new EnStructPoids("VALEUR", EnTypeChampsSQLite.VARCHAR, null, 3); //

	public static EnStructPoids UNITE = new EnStructPoids("UNITE", EnTypeChampsSQLite.VARCHAR, null, 4); //

	public static EnStructPoids NOTE = new EnStructPoids("NOTE", EnTypeChampsSQLite.VARCHAR, null, 5);

	/**
	 * Constructeur
	 * @param p_nomChamp
	 * @param p_typeClass
	 * @param p_tailleMax
	 * @param p_index
	 */
	EnStructPoids(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax, int p_index) {
		super(p_nomChamp, p_typeClass, p_tailleMax, p_index);
	}

	/**
	 * Obtenir la liste des champs de la table
	 */
	@Override
	public IStructureTable[] getListeChamp() {
		return new IStructureTable[] { ID_POID, ID_CARNET_PARENT, DATE, VALEUR, UNITE, NOTE };
	}

	/**
	 * Obtenir le champ "ClePrimaire" de la table
	 */
	@Override
	public SuperStructureTable getKeyChamp() {
		return ID_POID;
	}

	/**
	 * Constructeur sans parametre utilisé lors de la creation de la table au premier demarrage de l'appli
	 */
	public EnStructPoids() {
		super("Toto", EnTypeChampsSQLite.INTEGER, null, -1);
	}
}