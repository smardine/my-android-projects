package fr.smardine.monvetcarnet.mdl;

public class MlProprietaire implements IMetaModel {

	private int idDetailParent;
	private int idProprietaire;
	private String nom;
	private String adresse;
	private String telephone;
	private String mail;

	public int getIdDetailParent() {
		return idDetailParent;
	}

	public void setIdDetailParent(int idDetailParent) {
		this.idDetailParent = idDetailParent;
	}

	public int getIdProprietaire() {
		return idProprietaire;
	}

	public void setIdProprietaire(int idProprietaire) {
		this.idProprietaire = idProprietaire;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public int getId() {
		return idProprietaire;
	}

}
