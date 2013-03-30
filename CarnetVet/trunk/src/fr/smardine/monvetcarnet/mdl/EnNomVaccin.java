package fr.smardine.monvetcarnet.mdl;

public enum EnNomVaccin {

	RC, P, CH, FelV, Rage;

	public static EnNomVaccin getEnumFromName(String p_name) {
		for (EnNomVaccin uneEnum : values()) {
			if (uneEnum.name().equals(p_name))
				return uneEnum;
		}
		return null;
	}

}
