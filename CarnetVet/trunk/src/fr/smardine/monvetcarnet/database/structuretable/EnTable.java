package fr.smardine.monvetcarnet.database.structuretable;

/**
 * @author smardine
 */
public enum EnTable {
	/**
	 * voir EnStructCarnet pour la structure de la table
	 */
	CARNETS("CARNETS", EnStructCarnet.class), //
	DETAILS("DETAILS", EnStructDetail.class), //
	IDENTIFICATIONS("IDENTIFICATIONS", EnStructIdentification.class), //
	MALADIES("MALADIES", EnStructMaladie.class), //
	POIDS("POIDS", EnStructPoids.class), //
	PROPRIETAIRES("PROPRIETAIRES", EnStructProprietaire.class), //
	VACCINS("VACCINS", EnStructVaccin.class);

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
