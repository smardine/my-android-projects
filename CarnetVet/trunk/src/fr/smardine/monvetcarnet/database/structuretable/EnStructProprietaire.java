package fr.smardine.monvetcarnet.database.structuretable;

/**
 * Classe représentant la structure de la table Proprietaire
 * @author sims
 */
public class EnStructProprietaire extends SuperStructureTable {

	public static EnStructProprietaire ID_PROPRIETAIRE = new EnStructProprietaire("ID_PROPRIETAIRE", EnTypeChampsSQLite.INTEGER, null, 0); //

	public static EnStructProprietaire ID_DETAIL_PARENT = new EnStructProprietaire("ID_DETAIL", EnTypeChampsSQLite.INTEGER, null, 1); //

	public static EnStructProprietaire NOM = new EnStructProprietaire("NOM", EnTypeChampsSQLite.VARCHAR, null, 2); //

	public static EnStructProprietaire ADRESSE = new EnStructProprietaire("ADRESSE", EnTypeChampsSQLite.VARCHAR, null, 3); //

	public static EnStructProprietaire TEL = new EnStructProprietaire("TEL", EnTypeChampsSQLite.VARCHAR, null, 4); //

	public static EnStructProprietaire MAIL = new EnStructProprietaire("MAIL", EnTypeChampsSQLite.VARCHAR, null, 5); //

	/**
	 * Constructeur
	 * @param p_nomChamp
	 * @param p_typeClass
	 * @param p_tailleMax
	 * @param p_index
	 */
	EnStructProprietaire(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax, int p_index) {
		super(p_nomChamp, p_typeClass, p_tailleMax, p_index);
	}

	/**
	 * Obtenir la liste des champs de la table
	 */
	@Override
	public IStructureTable[] getListeChamp() {
		return new IStructureTable[] { ID_PROPRIETAIRE, ID_DETAIL_PARENT, NOM, ADRESSE, TEL, MAIL };
	}

	/**
	 * Obtenir le champ "ClePrimaire" de la table
	 */
	@Override
	public SuperStructureTable getKeyChamp() {
		return ID_PROPRIETAIRE;
	}

	/**
	 * Constructeur sans parametre utilisé lors de la creation de la table au premier demarrage de l'appli
	 */
	public EnStructProprietaire() {
		super("Toto", EnTypeChampsSQLite.INTEGER, null, -1);
	}

}