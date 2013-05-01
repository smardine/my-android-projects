package fr.smardine.monvetcarnet.mdl;

/**
 * Vaccin
 * @author sims
 */
public enum EnNomVaccin {

	Corysa, Typhus, Leucose, Chlamydiose, Rage, MaladieDeCarre, Parvovirose, HepatiteDeRubarth, Leptospirose, TouxDuChenil, Piroplasmose;

	public static EnNomVaccin getEnumFromName(String p_name) {
		for (EnNomVaccin uneEnum : values()) {
			if (uneEnum.name().equals(p_name))
				return uneEnum;
		}
		return null;
	}

}
