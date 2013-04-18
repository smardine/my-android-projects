package fr.smardine.monvetcarnet.mdl;

import java.util.List;

/**
 * MlCarnet
 * @author sims
 */
public class MlCarnet implements IMetaModel {

	private int idCarnet;
	private String nomCarnet;
	private MlIdentification identificationAnimal;
	private List<MlVaccin> listeDeVaccins;
	private List<MlPoids> listeDePoids;
	private List<MlMaladie> listeDeMaladies;

	public int getIdCarnet() {
		return idCarnet;
	}

	public void setIdCarnet(int idCarnet) {
		this.idCarnet = idCarnet;
	}

	public String getNomCarnet() {
		return nomCarnet;
	}

	public void setNomCarnet(String nomCarnet) {
		this.nomCarnet = nomCarnet;
	}

	@Override
	public int getId() {
		return getIdCarnet();
	}

	/**
	 * @return the identificationAnimal
	 */
	public MlIdentification getIdentificationAnimal() {
		return identificationAnimal;
	}

	/**
	 * @param identificationAnimal the identificationAnimal to set
	 */
	public void setIdentificationAnimal(MlIdentification identificationAnimal) {
		this.identificationAnimal = identificationAnimal;
	}

	public List<MlVaccin> getListeDeVaccins() {
		return listeDeVaccins;
	}

	public void setListeDeVaccins(List<MlVaccin> listeDeVaccins) {
		this.listeDeVaccins = listeDeVaccins;
	}

	public List<MlPoids> getListeDePoids() {
		return listeDePoids;
	}

	public void setListeDePoids(List<MlPoids> listeDePoids) {
		this.listeDePoids = listeDePoids;
	}

	public List<MlMaladie> getListeDeMaladies() {
		return listeDeMaladies;
	}

	public void setListeDeMaladies(List<MlMaladie> listeDeMaladies) {
		this.listeDeMaladies = listeDeMaladies;
	}

}
