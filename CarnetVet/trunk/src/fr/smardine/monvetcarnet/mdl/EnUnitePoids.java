package fr.smardine.monvetcarnet.mdl;

/**
 * Unit� de poids
 * @author sims
 */
public enum EnUnitePoids {

	Kilo("Kg"), //
	Livre("Livres");

	private String lib;

	EnUnitePoids(String p_lib) {
		this.lib = p_lib;
	}

	/**
	 * @return the lib
	 */
	public String getLib() {
		return lib;
	}

	public static EnUnitePoids getEnumFromName(String p_name) {
		for (EnUnitePoids uneEnum : values()) {
			if (uneEnum.lib.equals(p_name))
				return uneEnum;
		}
		return null;
	}

}
