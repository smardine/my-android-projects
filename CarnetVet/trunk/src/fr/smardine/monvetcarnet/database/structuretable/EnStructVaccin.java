package fr.smardine.monvetcarnet.database.structuretable;

/**
 * Classe représentant la structure de la table Vaccin
 * @author sims
 */
public class EnStructVaccin extends SuperStructureTable {

	public static EnStructVaccin ID_VACCIN = new EnStructVaccin("ID_VACCIN", EnTypeChampsSQLite.INTEGER, null, 0); //

	public static EnStructVaccin ID_CARNET_PARENT = new EnStructVaccin("ID_CARNET", EnTypeChampsSQLite.INTEGER, null, 1); //

	public static EnStructVaccin DATE = new EnStructVaccin("DATE", EnTypeChampsSQLite.LONG, null, 2); //

	public static EnStructVaccin NOM = new EnStructVaccin("NOM", EnTypeChampsSQLite.VARCHAR, null, 3); //

	public static EnStructVaccin VERMIFUGE = new EnStructVaccin("VERMIFUGE", EnTypeChampsSQLite.VARCHAR, 1, 4);

	/**
	 * Constructeur
	 * @param p_nomChamp
	 * @param p_typeClass
	 * @param p_tailleMax
	 * @param p_index
	 */
	EnStructVaccin(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax, int p_index) {
		super(p_nomChamp, p_typeClass, p_tailleMax, p_index);
	}

	/**
	 * Obtenir la liste des champs de la table
	 */
	@Override
	public IStructureTable[] getListeChamp() {
		return new IStructureTable[] { ID_VACCIN, ID_CARNET_PARENT, DATE, NOM, VERMIFUGE };
	}

	/**
	 * Obtenir le champ "ClePrimaire" de la table
	 */
	@Override
	public SuperStructureTable getKeyChamp() {
		return ID_VACCIN;
	}

	/**
	 * Constructeur sans parametre utilisé lors de la creation de la table au premier demarrage de l'appli
	 */
	public EnStructVaccin() {
		super("Toto", EnTypeChampsSQLite.INTEGER, null, -1);
	}
}