package fr.smardine.monvetcarnet.database.structuretable;

public enum EnStructMaladie implements IStructureTable {

	ID_MALADIE("ID_MALADIE", EnTypeChampsSQLite.INTEGER, null), //

	ID_CARNET("ID_CARNET", EnTypeChampsSQLite.INTEGER, null), //

	DATE("DATE", EnTypeChampsSQLite.LONG, null), //

	TITRE("VALEUR", EnTypeChampsSQLite.VARCHAR, null), //

	SYMPTOME("SYMPTOME", EnTypeChampsSQLite.VARCHAR, null), //

	RDV_VETO("RDV_VETO", EnTypeChampsSQLite.VARCHAR, 1), //

	TRAITEMENT("TRAITEMENT", EnTypeChampsSQLite.VARCHAR, null);

	private final String nomChamp;
	private final EnTypeChampsSQLite typeClass;
	private final Integer tailleMax;

	EnStructMaladie(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax) {
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