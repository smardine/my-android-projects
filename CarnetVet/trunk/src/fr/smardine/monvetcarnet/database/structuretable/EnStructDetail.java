package fr.smardine.monvetcarnet.database.structuretable;

public enum EnStructDetail implements IStructureTable {
	ID_DETAIL("ID_DETAIL", EnTypeChampsSQLite.INTEGER, null), //

	ID_IDENTIFICATION("ID_IDENTIFICATION", EnTypeChampsSQLite.INTEGER, null), //

	RACE("RACE", EnTypeChampsSQLite.VARCHAR, 250), //

	ROBE("ROBE", EnTypeChampsSQLite.VARCHAR, null), //

	NUM_TATOUAGE("NUM_TATOUAGE", EnTypeChampsSQLite.VARCHAR, null), //

	NUM_PUCE("NUM_PUCE", EnTypeChampsSQLite.VARCHAR, null), //

	ID_PROP1("ID_PROP1", EnTypeChampsSQLite.INTEGER, null), //

	ID_PROP2("ID_PROP2", EnTypeChampsSQLite.INTEGER, null), //

	ID_ELEVEUR("ID_ELEVEUR", EnTypeChampsSQLite.INTEGER, null);

	private final String nomChamp;
	private final EnTypeChampsSQLite typeClass;
	private final Integer tailleMax;

	EnStructDetail(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax) {
		this.nomChamp = p_nomChamp;
		this.typeClass = p_typeClass;
		this.tailleMax = p_tailleMax;
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

}
