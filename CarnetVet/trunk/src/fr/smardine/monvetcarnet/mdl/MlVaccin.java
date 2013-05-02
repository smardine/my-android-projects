package fr.smardine.monvetcarnet.mdl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	 * Obtenir la liste des vaccins injectés
	 * @return
	 */
	public List<String> getListeDesVaccinsInjectes() {
		List<String> lst = new ArrayList<String>();
		if (isCorysa())
			lst.add(EnNomVaccin.Corysa.toString());
		if (isTiphus())
			lst.add(EnNomVaccin.Typhus.toString());
		if (isLeucose())
			lst.add(EnNomVaccin.Leucose.toString());
		if (isChlamydiose())
			lst.add(EnNomVaccin.Chlamydiose.toString());
		if (isRage())
			lst.add(EnNomVaccin.Rage.toString());
		if (isMaladieDeCarre())
			lst.add(EnNomVaccin.MaladieDeCarre.toString());
		if (isParvovirose())
			lst.add(EnNomVaccin.Parvovirose.toString());
		if (isHepatiteDeRubarth())
			lst.add(EnNomVaccin.HepatiteDeRubarth.toString());
		if (isLeptospirose())
			lst.add(EnNomVaccin.Leptospirose.toString());
		if (isTouxDuChenil())
			lst.add(EnNomVaccin.TouxDuChenil.toString());
		if (isPiroplasmose())
			lst.add(EnNomVaccin.Piroplasmose.toString());
		if (isVermifuge())
			lst.add("Vermifuge");

		return lst;
	}

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
