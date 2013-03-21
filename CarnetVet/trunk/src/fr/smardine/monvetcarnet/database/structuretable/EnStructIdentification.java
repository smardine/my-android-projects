package fr.smardine.monvetcarnet.database.structuretable;

public enum EnStructIdentification implements IStructureTable {

	ID_IDENTIFICATION("ID_IDENTIFICATION", EnTypeChampsSQLite.INTEGER, null, 1), //

	ID_CARNET("ID_CARNET", EnTypeChampsSQLite.INTEGER, null, 2), //

	NOM("NOM", EnTypeChampsSQLite.VARCHAR, null, 3), //

	DATE_NAISSANCE("DATE_NAISSANCE", EnTypeChampsSQLite.LONG, null, 4), //

	TYPE_ANIMAL("TYPE_ANIMAL", EnTypeChampsSQLite.VARCHAR, null, 5), //

	GENRE("GENRE", EnTypeChampsSQLite.VARCHAR, 250, 6); //

	private final String nomChamp;
	private final EnTypeChampsSQLite typeClass;
	private final Integer tailleMax;
	private int index;

	EnStructIdentification(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax, int p_index) {
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
