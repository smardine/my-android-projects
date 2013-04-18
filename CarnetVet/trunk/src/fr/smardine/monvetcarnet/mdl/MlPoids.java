package fr.smardine.monvetcarnet.mdl;

import java.util.Date;

/**
 * MlPoids
 * @author sims
 */
public class MlPoids implements IMetaModel {

	// private int idCarnetParent;
	private int idPoid;
	private Date date;
	private EnUnitePoids unitePoids;
	private String note;
	private double valeur;
	private final MlCarnet carnetParent;

	public MlPoids(MlCarnet p_carnetParent) {
		this.carnetParent = p_carnetParent;
	}

	@Override
	public int getId() {
		return idPoid;
	}

	// public int getIdCarnetParent() {
	// return idCarnetParent;
	// }
	//
	// public void setIdCarnetParent(int idCarnetParent) {
	// this.idCarnetParent = idCarnetParent;
	// }

	public int getIdPoid() {
		return idPoid;
	}

	public void setIdPoid(int idPoid) {
		this.idPoid = idPoid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public EnUnitePoids getUnitePoids() {
		return unitePoids;
	}

	public void setUnitePoids(EnUnitePoids unitePoids) {
		this.unitePoids = unitePoids;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the valeur
	 */
	public double getValeur() {
		return valeur;
	}

	/**
	 * @param valeur the valeur to set
	 */
	public void setValeur(double valeur) {
		this.valeur = valeur;
	}

	/**
	 * @return the carnetParent
	 */
	public MlCarnet getCarnetParent() {
		return carnetParent;
	}

}
