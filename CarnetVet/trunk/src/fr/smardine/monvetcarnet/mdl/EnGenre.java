package fr.smardine.monvetcarnet.mdl;

public enum EnGenre {
	MALE("Mâle"), //
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

	public static EnGenre getEnumFromName(String p_name) {
		for (EnGenre uneEnum : values()) {
			if (uneEnum.type.equals(p_name))
				return uneEnum;
		}
		return null;
	}
}
