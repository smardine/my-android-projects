package fr.smardine.monvetcarnet.mdl;

import java.util.Date;

public class MlMaladie implements IMetaModel {

	private int idCarnetParent;
	private int idMaladie;
	private Date date;
	private String titre;
	private String symptomes;
	private boolean rdvVeto;
	private String traitement;

	@Override
	public int getId() {
		return idMaladie;
	}

	public int getIdCarnetParent() {
		return idCarnetParent;
	}

	public void setIdCarnetParent(int idCarnetParent) {
		this.idCarnetParent = idCarnetParent;
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

}
