package fr.smardine.podcaster.database.structure;

public enum EnStructEpisode implements IStructureTable {
	ID_EPISODE("ID_EPISODE", EnTypeChampsSQLite.INTEGER, null), //

	TITRE("TITRE_EPISODE", EnTypeChampsSQLite.VARCHAR, 250), //

	DESCRIPTION("DESCRIPTION", EnTypeChampsSQLite.VARCHAR, 250), //

	URL("URL_EPISODE", EnTypeChampsSQLite.VARCHAR, null), //

	STATUT_LECTURE("STATUT_LECTURE", EnTypeChampsSQLite.VARCHAR, 250), //

	STATUT_TEELCHARGEMENT("STATUT_TELECHARGEMENT", EnTypeChampsSQLite.VARCHAR,
			250), //

	DUREE("DUREE", EnTypeChampsSQLite.DATE, null), //

	DATE_PUBLICATION("DATE_PUBLICATION", EnTypeChampsSQLite.DATE, null), //

	TYPE_EPISODE("TYPE_EPISODE", EnTypeChampsSQLite.VARCHAR, 250), //

	ID_CATEGORIE("ID_CATEGORIE", EnTypeChampsSQLite.INTEGER, null);

	private final String nomChamp;
	private final EnTypeChampsSQLite typeClass;
	private final Integer tailleMax;

	EnStructEpisode(String p_nomChamp, EnTypeChampsSQLite p_typeClass,
			Integer p_tailleMax) {
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
