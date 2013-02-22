package fr.smardine.podcaster.mdl;

import java.io.Serializable;

public class MlCategorie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 632465147531257854L;
	private int idCategorie;
	private String nomCategorie;

	public MlCategorie() {

	}

	/**
	 * @return the idCategorie
	 */
	public final int getIdCategorie() {
		return idCategorie;
	}

	/**
	 * @param idCategorie the idCategorie to set
	 */
	public final void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	/**
	 * @return the nomCategorie
	 */
	public final String getNomCategorie() {
		return nomCategorie;
	}

	/**
	 * @param nomCategorie the nomCategorie to set
	 */
	public final void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}
}
