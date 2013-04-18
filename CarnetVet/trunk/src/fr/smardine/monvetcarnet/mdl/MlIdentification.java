package fr.smardine.monvetcarnet.mdl;

import java.util.Date;

/**
 * MlIdentification
 * @author sims
 */
public class MlIdentification implements IMetaModel {

	private int idIdentification;
	// private int idCarnetParent;
	private String nomAnimal;
	private Date dateNaissance;
	private double ageCalcule;
	private EnTypeAnimal typeAnimal;
	private EnGenre genreAnimal;
	private MlDetail detail;
	private final MlCarnet carnetParent;

	public MlIdentification(MlCarnet p_carnetParent) {
		this.carnetParent = p_carnetParent;
	}

	@Override
	public int getId() {
		return idIdentification;
	}

	public int getIdIdentification() {
		return idIdentification;
	}

	public void setIdIdentification(int idIdentification) {
		this.idIdentification = idIdentification;
	}

	// public int getIdCarnetParent() {
	// return idCarnetParent;
	// }
	//
	// public void setIdCarnetParent(int idCarnetParent) {
	// this.idCarnetParent = idCarnetParent;
	// }

	public String getNomAnimal() {
		return nomAnimal;
	}

	public void setNomAnimal(String nomAnimal) {
		this.nomAnimal = nomAnimal;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public double getAgeCalcule() {
		return ageCalcule;
	}

	public EnTypeAnimal getTypeAnimal() {
		return typeAnimal;
	}

	public void setTypeAnimal(EnTypeAnimal typeAnimal) {
		this.typeAnimal = typeAnimal;
	}

	public EnGenre getGenreAnimal() {
		return genreAnimal;
	}

	public void setGenreAnimal(EnGenre genreAnimal) {
		this.genreAnimal = genreAnimal;
	}

	/**
	 * @return the detail
	 */
	public MlDetail getDetail() {
		return detail;
	}

	/**
	 * @param detail the detail to set
	 */
	public void setDetail(MlDetail detail) {
		this.detail = detail;
	}

	/**
	 * @return the carnetParent
	 */
	public MlCarnet getCarnetParent() {
		return carnetParent;
	}

}
