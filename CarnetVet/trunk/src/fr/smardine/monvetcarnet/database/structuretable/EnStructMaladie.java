package fr.smardine.monvetcarnet.database.structuretable;

public enum EnStructMaladie implements IStructureTable {

	ID_MALADIE("ID_MALADIE", EnTypeChampsSQLite.INTEGER, null, 1), //

	ID_CARNET("ID_CARNET", EnTypeChampsSQLite.INTEGER, null, 2), //

	DATE("DATE", EnTypeChampsSQLite.LONG, null, 3), //

	TITRE("VALEUR", EnTypeChampsSQLite.VARCHAR, null, 4), //

	SYMPTOME("SYMPTOME", EnTypeChampsSQLite.VARCHAR, null, 5), //

	RDV_VETO("RDV_VETO", EnTypeChampsSQLite.VARCHAR, 1, 6), //

	TRAITEMENT("TRAITEMENT", EnTypeChampsSQLite.VARCHAR, null, 7);

	private final String nomChamp;
	private final EnTypeChampsSQLite typeClass;
	private final Integer tailleMax;
	private int index;

	EnStructMaladie(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax, int p_index) {
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