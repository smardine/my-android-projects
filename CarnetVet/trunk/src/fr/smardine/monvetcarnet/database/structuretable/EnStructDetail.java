package fr.smardine.monvetcarnet.database.structuretable;

public enum EnStructDetail implements IStructureTable {
	ID_DETAIL("ID_DETAIL", EnTypeChampsSQLite.INTEGER, null, 1), //

	ID_IDENTIFICATION("ID_IDENTIFICATION", EnTypeChampsSQLite.INTEGER, null, 2), //

	RACE("RACE", EnTypeChampsSQLite.VARCHAR, 250, 3), //

	ROBE("ROBE", EnTypeChampsSQLite.VARCHAR, null, 4), //

	NUM_TATOUAGE("NUM_TATOUAGE", EnTypeChampsSQLite.VARCHAR, null, 5), //

	NUM_PUCE("NUM_PUCE", EnTypeChampsSQLite.VARCHAR, null, 6), //

	ID_PROP1("ID_PROP1", EnTypeChampsSQLite.INTEGER, null, 7), //

	ID_PROP2("ID_PROP2", EnTypeChampsSQLite.INTEGER, null, 8), //

	ID_ELEVEUR("ID_ELEVEUR", EnTypeChampsSQLite.INTEGER, null, 9);

	private final String nomChamp;
	private final EnTypeChampsSQLite typeClass;
	private final Integer tailleMax;
	private int index;

	EnStructDetail(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax, int p_index) {
		this.nomChamp = p_nomChamp;
		this.typeClass = p_typeClass;
		this.tailleMax = p_tailleMax;
		this.index = p_index;
	}

	@Override
	public String getNomChamp() {
		return nomChamp;
	}

	@Override
	public EnTypeChampsSQLite getTypeChamp() {
		return typeClass;
	}

	@Override
	public Integer getTailleMax() {
		return tailleMax;
	}

	@Override
	public IStructureTable[] getListeChamp() {
		return values();
	}

	@Override
	public int getindex() {
		return index;
	}

}
