package fr.smardine.monvetcarnet.database.structuretable;

public class EnStructVaccin extends SuperStructureTable {

	public static EnStructVaccin ID_VACCIN = new EnStructVaccin("ID_VACCIN", EnTypeChampsSQLite.INTEGER, null, 0); //

	public static EnStructVaccin ID_CARNET_PARENT = new EnStructVaccin("ID_CARNET", EnTypeChampsSQLite.INTEGER, null, 1); //

	public static EnStructVaccin DATE = new EnStructVaccin("DATE", EnTypeChampsSQLite.LONG, null, 2); //

	public static EnStructVaccin NOM = new EnStructVaccin("NOM", EnTypeChampsSQLite.VARCHAR, null, 3); //

	public static EnStructVaccin VERMIFUGE = new EnStructVaccin("VERMIFUGE", EnTypeChampsSQLite.VARCHAR, 1, 4);

	EnStructVaccin(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax, int p_index) {
		super(p_nomChamp, p_typeClass, p_tailleMax, p_index);
	}

	@Override
	public IStructureTable[] getListeChamp() {
		return new IStructureTable[] { ID_VACCIN, ID_CARNET_PARENT, DATE, NOM, VERMIFUGE };
	}

	@Override
	public SuperStructureTable getKeyChamp() {
		return ID_VACCIN;
	}

	public EnStructVaccin() {
		super("Toto", EnTypeChampsSQLite.INTEGER, null, -1);
	}
}