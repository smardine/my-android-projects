package fr.smardine.monvetcarnet.database.structuretable;

public enum EnStructProprietaire implements IStructureTable {

	ID_PROPRIETAIRE("ID_PROPRIETAIRE", EnTypeChampsSQLite.INTEGER, null), //

	ID_DETAIL("ID_DETAIL", EnTypeChampsSQLite.INTEGER, null), //

	NOM("NOM", EnTypeChampsSQLite.VARCHAR, null), //

	ADRESSE("ADRESSE", EnTypeChampsSQLite.VARCHAR, null), //

	TEL("TEL", EnTypeChampsSQLite.VARCHAR, null), //

	MAIL("MAIL", EnTypeChampsSQLite.VARCHAR, null); //

	private final String nomChamp;
	private final EnTypeChampsSQLite typeClass;
	private final Integer tailleMax;

	EnStructProprietaire(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax) {
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