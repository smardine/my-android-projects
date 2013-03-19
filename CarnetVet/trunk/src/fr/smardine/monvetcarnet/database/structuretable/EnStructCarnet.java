package fr.smardine.monvetcarnet.database.structuretable;

public enum EnStructCarnet implements IStructureTable {

	ID_CARNET("ID_CARNET", EnTypeChampsSQLite.INTEGER, null),

	NOM_CARNET("NOM_CARNET", EnTypeChampsSQLite.VARCHAR, 250);

	private final String nomChamp;
	private final EnTypeChampsSQLite typeClass;
	private final Integer tailleMax;

	EnStructCarnet(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax) {
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
