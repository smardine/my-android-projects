package fr.smardine.monvetcarnet.database.structuretable;

public class EnStructIdentification extends SuperStructureTable {

	public static EnStructIdentification ID_IDENTIFICATION = new EnStructIdentification("ID_IDENTIFICATION", EnTypeChampsSQLite.INTEGER,
			null, 1); //

	public static EnStructIdentification ID_CARNET = new EnStructIdentification("ID_CARNET", EnTypeChampsSQLite.INTEGER, null, 2); //

	public static EnStructIdentification NOM = new EnStructIdentification("NOM", EnTypeChampsSQLite.VARCHAR, null, 3); //

	public static EnStructIdentification DATE_NAISSANCE = new EnStructIdentification("DATE_NAISSANCE", EnTypeChampsSQLite.LONG, null, 4); //

	public static EnStructIdentification TYPE_ANIMAL = new EnStructIdentification("TYPE_ANIMAL", EnTypeChampsSQLite.VARCHAR, null, 5); //

	public static EnStructIdentification GENRE = new EnStructIdentification("GENRE", EnTypeChampsSQLite.VARCHAR, 250, 6); //

	EnStructIdentification(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax, int p_index) {
		super(p_nomChamp, p_typeClass, p_tailleMax, p_index);
	}

	public static IStructureTable[] getListeChamp() {
		return new IStructureTable[] { ID_IDENTIFICATION, ID_CARNET, NOM, DATE_NAISSANCE, TYPE_ANIMAL, GENRE };
	}

	@Override
	public SuperStructureTable getKeyChamp() {
		return ID_IDENTIFICATION;
	}

}
