package fr.smardine.podcaster.database.structure;

/**
 * @author smardine
 */
public enum EnTable {
	/**
	 * voir EnStructFlux pour la structure de la table
	 */
	FLUX("FLUX", EnStructFlux.class), //
	EPISODE("EPISODE", EnStructEpisode.class);

	private String nomTable;
	private Class<? extends IStructureTable> structureTable;

	EnTable(String p_nomTable, Class<? extends IStructureTable> p_structure) {
		this.nomTable = p_nomTable;
		this.structureTable = p_structure;
	}

	/**
	 * @return the nomTable
	 */
	public String getNomTable() {
		return nomTable;
	}

	@Override
	public String toString() {
		return nomTable;
	}

	public Class<? extends IStructureTable> getStructureTable() {
		return structureTable;
	}

}
