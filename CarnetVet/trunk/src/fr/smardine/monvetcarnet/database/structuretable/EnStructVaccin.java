package fr.smardine.monvetcarnet.database.structuretable;

public enum EnStructVaccin implements IStructureTable {

	ID_VACCIN("ID_VACCIN", EnTypeChampsSQLite.INTEGER, null), //

	ID_CARNET("ID_CARNET", EnTypeChampsSQLite.INTEGER, null), //

	DATE("DATE", EnTypeChampsSQLite.LONG, null), //

	NOM("NOM", EnTypeChampsSQLite.VARCHAR, null), //

	VERMIFUGE("VERMIFUGE", EnTypeChampsSQLite.VARCHAR, 1);

	private final String nomChamp;
	private final EnTypeChampsSQLite typeClass;
	private final Integer tailleMax;

	EnStructVaccin(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax) {
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