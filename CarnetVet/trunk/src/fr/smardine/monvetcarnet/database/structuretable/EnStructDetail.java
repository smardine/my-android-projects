package fr.smardine.monvetcarnet.database.structuretable;

public class EnStructDetail extends SuperStructureTable {
	public static EnStructDetail ID_DETAIL = new EnStructDetail("ID_DETAIL", EnTypeChampsSQLite.INTEGER, null, 1); //

	public static EnStructDetail ID_IDENTIFICATION = new EnStructDetail("ID_IDENTIFICATION", EnTypeChampsSQLite.INTEGER, null, 2); //

	public static EnStructDetail RACE = new EnStructDetail("RACE", EnTypeChampsSQLite.VARCHAR, 250, 3); //

	public static EnStructDetail ROBE = new EnStructDetail("ROBE", EnTypeChampsSQLite.VARCHAR, null, 4);//

	public static EnStructDetail NUM_TATOUAGE = new EnStructDetail("NUM_TATOUAGE", EnTypeChampsSQLite.VARCHAR, null, 5); //

	public static EnStructDetail NUM_PUCE = new EnStructDetail("NUM_PUCE", EnTypeChampsSQLite.VARCHAR, null, 6);//

	public static EnStructDetail ID_PROP1 = new EnStructDetail("ID_PROP1", EnTypeChampsSQLite.INTEGER, null, 7); //

	public static EnStructDetail ID_PROP2 = new EnStructDetail("ID_PROP2", EnTypeChampsSQLite.INTEGER, null, 8); //

	public static EnStructDetail ID_ELEVEUR = new EnStructDetail("ID_ELEVEUR", EnTypeChampsSQLite.INTEGER, null, 9);

	EnStructDetail(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax, int p_index) {
		super(p_nomChamp, p_typeClass, p_tailleMax, p_index);
	}

	@Override
	public IStructureTable[] getListeChamp() {
		return new IStructureTable[] { ID_DETAIL, ID_IDENTIFICATION, RACE, ROBE, NUM_TATOUAGE, NUM_PUCE, ID_PROP1, ID_PROP2, ID_ELEVEUR };
	}

}
