package fr.smardine.monvetcarnet.mdl;

public enum EnGenre {
	MALE("M�le"), //
	FEMELLE("Femelle");

	private String type;

	EnGenre(String p_type) {
		type = p_type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
}
