package fr.smardine.monvetcarnet.mdl;

import java.util.Date;

/**
 * MlVaccin
 * @author sims
 */
public class MlVaccin implements IMetaModel {

	private int idVaccin;
	private Date date;
	private boolean vermifuge;
	private boolean corysa;
	private boolean tiphus;
	private boolean leucose;
	private boolean chlamydiose;
	private boolean rage;
	private boolean maladieDeCarre;
	private boolean parvovirose;
	private boolean hepatiteDeRubarth;
	private boolean leptospirose;
	private boolean touxDuChenil;
	private boolean piroplasmose;

	private final MlCarnet carnetParent;

	/**
	 * Constructeur
	 * @param p_carnetParent
	 */
	public MlVaccin(MlCarnet p_carnetParent) {
		this.carnetParent = p_carnetParent;
	}

	@Override
	public int getId() {
		return idVaccin;
	}

	public int getIdVaccin() {
		return idVaccin;
	}

	public void setIdVaccin(int idVaccin) {
		this.idVaccin = idVaccin;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isVermifuge() {
		return vermifuge;
	}

	public void setVermifuge(boolean vermifuge) {
		this.vermifuge = vermifuge;
	}

	/**
	 * @return the carnetParent
	 */
	public MlCarnet getCarnetParent() {
		return carnetParent;
	}

	public boolean isCorysa() {
		return corysa;
	}

	public void setCorysa(boolean corysa) {
		this.corysa = corysa;
	}

	public boolean isTiphus() {
		return tiphus;
	}

	public void setTiphus(boolean tiphus) {
		this.tiphus = tiphus;
	}

	public boolean isLeucose() {
		return leucose;
	}

	public void setLeucose(boolean leucose) {
		this.leucose = leucose;
	}

	public boolean isChlamydiose() {
		return chlamydiose;
	}

	public void setChlamydiose(boolean chlamydiose) {
		this.chlamydiose = chlamydiose;
	}

	public boolean isRage() {
		return rage;
	}

	public void setRage(boolean rage) {
		this.rage = rage;
	}

	public boolean isMaladieDeCarre() {
		return maladieDeCarre;
	}

	public void setMaladieDeCarre(boolean maladieDeCarre) {
		this.maladieDeCarre = maladieDeCarre;
	}

	public boolean isParvovirose() {
		return parvovirose;
	}

	public void setParvovirose(boolean parvovirose) {
		this.parvovirose = parvovirose;
	}

	public boolean isHepatiteDeRubarth() {
		return hepatiteDeRubarth;
	}

	public void setHepatiteDeRubarth(boolean hepatiteDeRubarth) {
		this.hepatiteDeRubarth = hepatiteDeRubarth;
	}

	public boolean isLeptospirose() {
		return leptospirose;
	}

	public void setLeptospirose(boolean leptospirose) {
		this.leptospirose = leptospirose;
	}

	public boolean isTouxDuChenil() {
		return touxDuChenil;
	}

	public void setTouxDuChenil(boolean touxDuChenil) {
		this.touxDuChenil = touxDuChenil;
	}

	public boolean isPiroplasmose() {
		return piroplasmose;
	}

	public void setPiroplasmose(boolean piroplasmose) {
		this.piroplasmose = piroplasmose;
	}

	/**
	 * Un vaccin au moins a t'il été injecté.
	 * @return
	 */
	public boolean isAuMoinsUnVaccin() {
		return this.isChlamydiose() || this.isCorysa() || this.isHepatiteDeRubarth() || this.isLeptospirose() || this.isLeucose()
				|| this.isMaladieDeCarre() || this.isParvovirose() || this.isPiroplasmose() || this.isRage() || this.isTiphus()
				|| this.isTouxDuChenil();
	}

}
