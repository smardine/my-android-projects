package fr.smardine.monvetcarnet.mdl;

/**
 * Vaccin
 * @author sims
 */
public enum EnNomVaccin {

	Corysa("Corysa"), //
	Typhus("Typhus"), //
	Leucose("Leucose"), //
	Chlamydiose("Chalmydiose"), //
	Rage("Rage"), //
	MaladieDeCarre("Maladie de Carré"), //
	Parvovirose("Parvovirose"), //
	HepatiteDeRubarth("Hépatite de Rubarth"), //
	Leptospirose("Leptospirose"), //
	TouxDuChenil("Toux du chenil"), //
	Piroplasmose("Piroplasmose");

	private String name;

	EnNomVaccin(String p_name) {
		this.name = p_name;
	}

	public static EnNomVaccin getEnumFromName(String p_name) {
		for (EnNomVaccin uneEnum : values()) {
			if (uneEnum.name().equals(p_name))
				return uneEnum;
		}
		return null;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
