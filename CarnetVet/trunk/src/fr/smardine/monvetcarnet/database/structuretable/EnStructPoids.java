package fr.smardine.monvetcarnet.database.structuretable;

public class EnStructPoids extends SuperStructureTable {

	public static EnStructPoids ID_POID = new EnStructPoids("ID_POID", EnTypeChampsSQLite.INTEGER, null, 0); //

	public static EnStructPoids ID_CARNET = new EnStructPoids("ID_CARNET", EnTypeChampsSQLite.INTEGER, null, 1); //

	public static EnStructPoids DATE = new EnStructPoids("DATE", EnTypeChampsSQLite.LONG, null, 2); //

	public static EnStructPoids VALEUR = new EnStructPoids("VALEUR", EnTypeChampsSQLite.VARCHAR, null, 3); //

	public static EnStructPoids UNITE = new EnStructPoids("UNITE", EnTypeChampsSQLite.VARCHAR, null, 4); //

	public static EnStructPoids NOTE = new EnStructPoids("NOTE", EnTypeChampsSQLite.VARCHAR, null, 5);

	EnStructPoids(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax, int p_index) {
		super(p_nomChamp, p_typeClass, p_tailleMax, p_index);
	}

	@Override
	public IStructureTable[] getListeChamp() {
		return new IStructureTable[] { ID_POID, ID_CARNET, DATE, VALEUR, UNITE, NOTE };
	}

	@Override
	public SuperStructureTable getKeyChamp() {
		return ID_POID;
	}

	public EnStructPoids() {
		super("Toto", EnTypeChampsSQLite.INTEGER, null, -1);
	}
}