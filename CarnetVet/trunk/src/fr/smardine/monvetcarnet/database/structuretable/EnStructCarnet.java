package fr.smardine.monvetcarnet.database.structuretable;

public class EnStructCarnet extends SuperStructureTable {

	public static EnStructCarnet ID_CARNET = new EnStructCarnet("ID_CARNET", EnTypeChampsSQLite.INTEGER, null, 0);

	public static EnStructCarnet NOM_CARNET = new EnStructCarnet("NOM_CARNET", EnTypeChampsSQLite.VARCHAR, 250, 1);

	// private final String nomChamp;
	// private final EnTypeChampsSQLite typeClass;
	// private final Integer tailleMax;
	// private final int index;

	EnStructCarnet(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax, int p_index) {
		super(p_nomChamp, p_typeClass, p_tailleMax, p_index);
		// this.nomChamp = p_nomChamp;
		// this.typeClass = p_typeClass;
		// this.tailleMax = p_tailleMax;
		// this.index = p_index;
	}

	public EnStructCarnet() {
		super("Toto", EnTypeChampsSQLite.INTEGER, null, -1);
	}

	@Override
	public IStructureTable[] getListeChamp() {
		return new SuperStructureTable[] { ID_CARNET, NOM_CARNET };
	}

	@Override
	public SuperStructureTable getKeyChamp() {
		return ID_CARNET;
	}

}
