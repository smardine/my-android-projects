package fr.smardine.podcaster.database.structure;

/**
 * @author smardine
 */
public enum EnStructFlux implements IStructureTable {

	ID_FLUX("ID_FLUX", EnTypeChampsSQLite.INTEGER, null), //

	TITRE("TITRE_FLUX", EnTypeChampsSQLite.VARCHAR, 250), //

	DATE_DERNIERE_SYNCHRO("DATE_DERNIERE_SYNCHRO", EnTypeChampsSQLite.DATE,
			null), //

	VIGNETTE_URL("VIGNETTE_URL", EnTypeChampsSQLite.VARCHAR, null),

	VIGNETTE_FILE("VIGNETTE", EnTypeChampsSQLite.BLOB, null), //

	ID_PARAMETRE("ID_PARAMETRE", EnTypeChampsSQLite.INTEGER, null), //

	ID_CATEGORIE("ID_CATEGORIE", EnTypeChampsSQLite.VARCHAR, 250), //

	URL("URL", EnTypeChampsSQLite.VARCHAR, null);

	private final String nomChamp;
	private final EnTypeChampsSQLite typeClass;
	private final Integer tailleMax;

	EnStructFlux(String p_nomChamp, EnTypeChampsSQLite p_typeClass,
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
