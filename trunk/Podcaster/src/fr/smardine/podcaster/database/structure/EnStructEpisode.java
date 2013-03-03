package fr.smardine.podcaster.database.structure;

public enum EnStructEpisode implements IStructureTable {
	ID_EPISODE("ID_EPISODE", EnTypeChampsSQLite.INTEGER, null), //

	ID_FLUX("ID_FLUX", EnTypeChampsSQLite.INTEGER, null), //

	TITRE("TITRE_EPISODE", EnTypeChampsSQLite.VARCHAR, 250), //

	DESCRIPTION("DESCRIPTION", EnTypeChampsSQLite.VARCHAR, 250), //

	URL("URL_EPISODE", EnTypeChampsSQLite.VARCHAR, null), //

	STATUT_LECTURE("STATUT_LECTURE", EnTypeChampsSQLite.VARCHAR, 250), //

	STATUT_TELECHARGEMENT("STATUT_TELECHARGEMENT", EnTypeChampsSQLite.VARCHAR, 250), //

	DUREE("DUREE", EnTypeChampsSQLite.VARCHAR, null), //

	GUID("GUID", EnTypeChampsSQLite.VARCHAR, null), //

	DATE_PUBLICATION("DATE_PUBLICATION", EnTypeChampsSQLite.LONG, null), //

	TYPE_EPISODE("TYPE_EPISODE", EnTypeChampsSQLite.VARCHAR, 250), //

	ID_CATEGORIE("ID_CATEGORIE", EnTypeChampsSQLite.INTEGER, null), //

	VIGNETTE_TELECHARGEE("VIGNETTE_TELECHARGEE", EnTypeChampsSQLite.VARCHAR, null), //

	URL_MEDIA("URL_MEDIA", EnTypeChampsSQLite.VARCHAR, null);

	private final String nomChamp;
	private final EnTypeChampsSQLite typeClass;
	private final Integer tailleMax;

	EnStructEpisode(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax) {
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
	public String toString() {
		return nomChamp;
	}

	@Override
	public IStructureTable[] getListeChamp() {
		return values();
	}
}
