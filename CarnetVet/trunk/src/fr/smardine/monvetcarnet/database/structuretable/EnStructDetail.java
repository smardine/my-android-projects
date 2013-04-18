package fr.smardine.monvetcarnet.database.structuretable;

/**
 * Classe représentant la structure de la table Detail
 * @author sims
 */
public class EnStructDetail extends SuperStructureTable {

	public static EnStructDetail ID_DETAIL = new EnStructDetail("ID_DETAIL", EnTypeChampsSQLite.INTEGER, null, 0); //

	public static EnStructDetail ID_IDENTIFICATION_PARENT = new EnStructDetail("ID_IDENTIFICATION", EnTypeChampsSQLite.INTEGER, null, 1); //

	public static EnStructDetail RACE = new EnStructDetail("RACE", EnTypeChampsSQLite.VARCHAR, 250, 2); //

	public static EnStructDetail ROBE = new EnStructDetail("ROBE", EnTypeChampsSQLite.VARCHAR, null, 3);//

	public static EnStructDetail NUM_TATOUAGE = new EnStructDetail("NUM_TATOUAGE", EnTypeChampsSQLite.VARCHAR, null, 4); //

	public static EnStructDetail NUM_PUCE = new EnStructDetail("NUM_PUCE", EnTypeChampsSQLite.VARCHAR, null, 5);//

	public static EnStructDetail ID_PROP1 = new EnStructDetail("ID_PROP1", EnTypeChampsSQLite.INTEGER, null, 6); //

	public static EnStructDetail ID_PROP2 = new EnStructDetail("ID_PROP2", EnTypeChampsSQLite.INTEGER, null, 7); //

	public static EnStructDetail ID_ELEVEUR = new EnStructDetail("ID_ELEVEUR", EnTypeChampsSQLite.INTEGER, null, 8);

	public static EnStructDetail SIGNES_DISTINCTIFS = new EnStructDetail("SIGNES_DISTINCTIFS", EnTypeChampsSQLite.VARCHAR, null, 9);//

	/**
	 * Constructeur
	 * @param p_nomChamp
	 * @param p_typeClass
	 * @param p_tailleMax
	 * @param p_index
	 */
	EnStructDetail(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax, int p_index) {
		super(p_nomChamp, p_typeClass, p_tailleMax, p_index);
	}

	/**
	 * Obtenir la liste des champs de la table
	 */
	@Override
	public IStructureTable[] getListeChamp() {
		return new SuperStructureTable[] { ID_DETAIL, ID_IDENTIFICATION_PARENT, RACE, ROBE, NUM_TATOUAGE, NUM_PUCE, ID_PROP1, ID_PROP2,
				ID_ELEVEUR, SIGNES_DISTINCTIFS };
	}

	/**
	 * Obtenir le champ "ClePrimaire" de la table
	 */
	@Override
	public SuperStructureTable getKeyChamp() {
		return ID_DETAIL;
	}

	/**
	 * Constructeur sans parametre utilisé lors de la creation de la table au premier demarrage de l'appli
	 */
	public EnStructDetail() {
		super("Toto", EnTypeChampsSQLite.INTEGER, null, -1);
	}

}
