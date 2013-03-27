package fr.smardine.monvetcarnet.database.structuretable;

public class EnStructProprietaire extends SuperStructureTable {

	public static EnStructProprietaire ID_PROPRIETAIRE = new EnStructProprietaire("ID_PROPRIETAIRE", EnTypeChampsSQLite.INTEGER, null, 1); //

	public static EnStructProprietaire ID_DETAIL = new EnStructProprietaire("ID_DETAIL", EnTypeChampsSQLite.INTEGER, null, 2); //

	public static EnStructProprietaire NOM = new EnStructProprietaire("NOM", EnTypeChampsSQLite.VARCHAR, null, 3); //

	public static EnStructProprietaire ADRESSE = new EnStructProprietaire("ADRESSE", EnTypeChampsSQLite.VARCHAR, null, 4); //

	public static EnStructProprietaire TEL = new EnStructProprietaire("TEL", EnTypeChampsSQLite.VARCHAR, null, 5); //

	public static EnStructProprietaire MAIL = new EnStructProprietaire("MAIL", EnTypeChampsSQLite.VARCHAR, null, 6); //

	EnStructProprietaire(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax, int p_index) {
		super(p_nomChamp, p_typeClass, p_tailleMax, p_index);
	}

	public static IStructureTable[] getListeChamp() {
		return new IStructureTable[] { ID_PROPRIETAIRE, ID_DETAIL, NOM, ADRESSE, TEL, MAIL };
	}

	@Override
	public SuperStructureTable getKeyChamp() {
		return ID_PROPRIETAIRE;
	}

}