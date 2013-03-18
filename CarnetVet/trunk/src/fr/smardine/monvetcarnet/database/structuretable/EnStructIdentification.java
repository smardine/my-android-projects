package fr.smardine.monvetcarnet.database.structuretable;

public enum EnStructIdentification implements IStructureTable {

	ID_IDENTIFICATION("ID_IDENTIFICATION", EnTypeChampsSQLite.INTEGER, null), //

	ID_CARNET("ID_CARNET", EnTypeChampsSQLite.INTEGER, null), //

	NOM("NOM", EnTypeChampsSQLite.VARCHAR, null), //

	DATE_NAISSANCE("DATE_NAISSANCE", EnTypeChampsSQLite.LONG, null), //

	TYPE_ANIMAL("TYPE_ANIMAL", EnTypeChampsSQLite.VARCHAR, null), //

	GENRE("GENRE", EnTypeChampsSQLite.VARCHAR, 250), //

	ID_DETAIL("ID_DETAIL", EnTypeChampsSQLite.INTEGER, null);

	private final String nomChamp;
	private final EnTypeChampsSQLite typeClass;
	private final Integer tailleMax;

	EnStructIdentification(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax) {
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
