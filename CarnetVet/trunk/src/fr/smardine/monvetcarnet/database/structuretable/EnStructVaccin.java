package fr.smardine.monvetcarnet.database.structuretable;

/**
 * Classe représentant la structure de la table Vaccin
 * @author sims
 */
public class EnStructVaccin extends SuperStructureTable {

	public static EnStructVaccin ID_VACCIN = new EnStructVaccin("ID_VACCIN", EnTypeChampsSQLite.INTEGER, null, 0); //

	public static EnStructVaccin ID_CARNET_PARENT = new EnStructVaccin("ID_CARNET", EnTypeChampsSQLite.INTEGER, null, 1); //

	public static EnStructVaccin DATE = new EnStructVaccin("DATE", EnTypeChampsSQLite.LONG, null, 2); //

	public static EnStructVaccin IS_VERMIFUGE = new EnStructVaccin("IS_VERMIFUGE", EnTypeChampsSQLite.BOOL, 1, 3);

	public static EnStructVaccin IS_CORYSA = new EnStructVaccin("IS_CORYSA", EnTypeChampsSQLite.BOOL, 1, 4);//

	public static EnStructVaccin IS_TYPHUS = new EnStructVaccin("IS_TYPHUS", EnTypeChampsSQLite.BOOL, 1, 5);//

	public static EnStructVaccin IS_LEUCOSE = new EnStructVaccin("IS_LEUCOSE", EnTypeChampsSQLite.BOOL, 1, 6);//

	public static EnStructVaccin IS_CHLAMYDIOSE = new EnStructVaccin("IS_CHLAMYDIOSE", EnTypeChampsSQLite.BOOL, 1, 7);//

	public static EnStructVaccin IS_RAGE = new EnStructVaccin("IS_RAGE", EnTypeChampsSQLite.BOOL, 1, 8);//

	/**********************************/
	/** Liste des vaccins pour chien **/
	/**********************************/

	public static EnStructVaccin IS_MALADIE_DE_CARRE = new EnStructVaccin("IS_MALADIE_DE_CARRE", EnTypeChampsSQLite.BOOL, 1, 9);//

	public static EnStructVaccin IS_PARVOVIROSE = new EnStructVaccin("IS_PARVOVIROSE", EnTypeChampsSQLite.BOOL, 1, 10);//

	public static EnStructVaccin IS_HEPATITE_DE_RUBARTH = new EnStructVaccin("IS_HEPATITE_DE_RUBARTH", EnTypeChampsSQLite.BOOL, 1, 11);//

	public static EnStructVaccin IS_LEPTOSPIROSE = new EnStructVaccin("IS_LEPTOSPIROSE", EnTypeChampsSQLite.BOOL, 1, 12);//

	public static EnStructVaccin IS_TOUX_DU_CHENIL = new EnStructVaccin("IS_TOUX_DU_CHENIL", EnTypeChampsSQLite.BOOL, 1, 13);//

	public static EnStructVaccin IS_PIROPLASMOSE = new EnStructVaccin("IS_PIROPLASMOSE", EnTypeChampsSQLite.BOOL, 1, 14);//

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
		return new IStructureTable[] { ID_VACCIN, ID_CARNET_PARENT, DATE, IS_VERMIFUGE, IS_CORYSA, IS_TYPHUS, IS_LEUCOSE, IS_CHLAMYDIOSE,
				IS_RAGE, IS_MALADIE_DE_CARRE, IS_PARVOVIROSE, IS_HEPATITE_DE_RUBARTH, IS_LEPTOSPIROSE, IS_TOUX_DU_CHENIL, IS_PIROPLASMOSE };
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