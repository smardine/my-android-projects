package fr.smardine.monvetcarnet.database.structuretable;

/**
 * Classe repr�sentant la structure de la table Identification
 * @author sims
 */
public class EnStructIdentification extends SuperStructureTable {

	public static EnStructIdentification ID_IDENTIFICATION = new EnStructIdentification("ID_IDENTIFICATION", EnTypeChampsSQLite.INTEGER,
			null, 0); //

	public static EnStructIdentification ID_CARNET_PARENT = new EnStructIdentification("ID_CARNET", EnTypeChampsSQLite.INTEGER, null, 1); //

	public static EnStructIdentification NOM = new EnStructIdentification("NOM", EnTypeChampsSQLite.VARCHAR, null, 2); //

	public static EnStructIdentification DATE_NAISSANCE = new EnStructIdentification("DATE_NAISSANCE", EnTypeChampsSQLite.LONG, null, 3); //

	public static EnStructIdentification TYPE_ANIMAL = new EnStructIdentification("TYPE_ANIMAL", EnTypeChampsSQLite.VARCHAR, null, 4); //

	public static EnStructIdentification GENRE = new EnStructIdentification("GENRE", EnTypeChampsSQLite.VARCHAR, 250, 5); //

	/**
	 * Constructeur
	 * @param p_nomChamp
	 * @param p_typeClass
	 * @param p_tailleMax
	 * @param p_index
	 */
	EnStructIdentification(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax, int p_index) {
		super(p_nomChamp, p_typeClass, p_tailleMax, p_index);
	}

	/**
	 * Obtenir la liste des champs de la table
	 */
	@Override
	public IStructureTable[] getListeChamp() {
		return new IStructureTable[] { ID_IDENTIFICATION, ID_CARNET_PARENT, NOM, DATE_NAISSANCE, TYPE_ANIMAL, GENRE };
	}

	/**
	 * Obtenir le champ "ClePrimaire" de la table
	 */
	@Override
	public SuperStructureTable getKeyChamp() {
		return ID_IDENTIFICATION;
	}

	/**
	 * Constructeur sans parametre utilis� lors de la creation de la table au premier demarrage de l'appli
	 */
	public EnStructIdentification() {
		super("Toto", EnTypeChampsSQLite.INTEGER, null, -1);
	}

}