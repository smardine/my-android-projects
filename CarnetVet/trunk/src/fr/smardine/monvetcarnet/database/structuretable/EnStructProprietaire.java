package fr.smardine.monvetcarnet.database.structuretable;

public enum EnStructProprietaire implements IStructureTable {

	ID_PROPRIETAIRE("ID_PROPRIETAIRE", EnTypeChampsSQLite.INTEGER, null, 1), //

	ID_DETAIL("ID_DETAIL", EnTypeChampsSQLite.INTEGER, null, 2), //

	NOM("NOM", EnTypeChampsSQLite.VARCHAR, null, 3), //

	ADRESSE("ADRESSE", EnTypeChampsSQLite.VARCHAR, null, 4), //

	TEL("TEL", EnTypeChampsSQLite.VARCHAR, null, 5), //

	MAIL("MAIL", EnTypeChampsSQLite.VARCHAR, null, 6); //

	private final String nomChamp;
	private final EnTypeChampsSQLite typeClass;
	private final Integer tailleMax;
	private int index;

	EnStructProprietaire(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax, int p_index) {
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