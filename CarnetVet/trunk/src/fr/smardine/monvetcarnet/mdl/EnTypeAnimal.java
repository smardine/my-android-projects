package fr.smardine.monvetcarnet.mdl;

public enum EnTypeAnimal {
	CHIEN("Chien"), //
	CHAT("Chat");

	private String type;

	EnTypeAnimal(String p_type) {
		type = p_type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	public static EnTypeAnimal getEnumFromName(String p_name) {
		for (EnTypeAnimal uneEnum : values()) {
			if (uneEnum.type.equals(p_name))
				return uneEnum;
		}
		return null;
	}

}
