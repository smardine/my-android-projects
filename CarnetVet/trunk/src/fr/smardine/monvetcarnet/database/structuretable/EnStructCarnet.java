package fr.smardine.monvetcarnet.database.structuretable;

/**
 * Classe représentant la structure de la table Carnet
 * @author sims
 */
public class EnStructCarnet extends SuperStructureTable {

	public static EnStructCarnet ID_CARNET = new EnStructCarnet("ID_CARNET", EnTypeChampsSQLite.INTEGER, null, 0);

	public static EnStructCarnet NOM_CARNET = new EnStructCarnet("NOM_CARNET", EnTypeChampsSQLite.VARCHAR, 250, 1);

	/**
	 * Constructeur
	 * @param p_nomChamp
	 * @param p_typeClass
	 * @param p_tailleMax
	 * @param p_index
	 */
	EnStructCarnet(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax, int p_index) {
		super(p_nomChamp, p_typeClass, p_tailleMax, p_index);
	}

	/**
	 * Constructeur sans parametre utilisé lors de la creation de la table au premier demarrage de l'appli
	 */
	public EnStructCarnet() {
		super("Toto", EnTypeChampsSQLite.INTEGER, null, -1);
	}

	/**
	 * Obtenir la liste des champs de la table
	 */
	@Override
	public IStructureTable[] getListeChamp() {
		return new SuperStructureTable[] { ID_CARNET, NOM_CARNET };
	}

	/**
	 * Obtenir le champ "ClePrimaire" de la table
	 */
	@Override
	public SuperStructureTable getKeyChamp() {
		return ID_CARNET;
	}

}
