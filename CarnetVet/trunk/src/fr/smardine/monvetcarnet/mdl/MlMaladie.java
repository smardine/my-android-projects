package fr.smardine.monvetcarnet.mdl;

import java.util.Date;

/**
 * MlMaladie
 * @author sims
 */
public class MlMaladie implements IMetaModel {

	private int idMaladie;
	private Date date;
	private String titre;
	private String symptomes;
	private boolean rdvVeto;
	private String traitement;
	private EnPictoMaladie pictoMaladie;
	private String notes;
	private final MlCarnet carnetParent;

	public MlMaladie(MlCarnet p_carnetParent) {
		this.carnetParent = p_carnetParent;
	}

	@Override
	public int getId() {
		return idMaladie;
	}

	public int getIdMaladie() {
		return idMaladie;
	}

	public void setIdMaladie(int idMaladie) {
		this.idMaladie = idMaladie;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getSymptomes() {
		return symptomes;
	}

	public void setSymptomes(String symptomes) {
		this.symptomes = symptomes;
	}

	public boolean isRdvVeto() {
		return rdvVeto;
	}

	public void setRdvVeto(boolean rdvVeto) {
		this.rdvVeto = rdvVeto;
	}

	public String getTraitement() {
		return traitement;
	}

	public void setTraitement(String traitement) {
		this.traitement = traitement;
	}

	/**
	 * @return the carnetParent
	 */
	public MlCarnet getCarnetParent() {
		return carnetParent;
	}

	/**
	 * @return the pictoMaladie
	 */
	public EnPictoMaladie getPictoMaladie() {
		return pictoMaladie;
	}

	/**
	 * @param pictoMaladie the pictoMaladie to set
	 */
	public void setPictoMaladie(EnPictoMaladie pictoMaladie) {
		this.pictoMaladie = pictoMaladie;
	}

	public boolean isPictoDefini() {
		return this.pictoMaladie != null;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

}
