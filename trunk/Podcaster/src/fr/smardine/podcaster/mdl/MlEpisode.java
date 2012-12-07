package fr.smardine.podcaster.mdl;

import java.util.Date;

public class MlEpisode {

	private int idEpisode;
	private String titre;
	private String description;
	private EnStatutLecture statutLecture;
	private EnStatutTelechargement statutTelechargement;
	private Date duree;
	private EnTypeEpisode typeEpisode;
	private MlCategorie categorie;

	public MlEpisode() {

	}

	/**
	 * @return the idEpisode
	 */
	public final int getIdEpisode() {
		return idEpisode;
	}

	/**
	 * @param idEpisode the idEpisode to set
	 */
	public final void setIdEpisode(int idEpisode) {
		this.idEpisode = idEpisode;
	}

	/**
	 * @return the titre
	 */
	public final String getTitre() {
		return titre;
	}

	/**
	 * @param titre the titre to set
	 */
	public final void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return the description
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public final void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the statutLecture
	 */
	public final EnStatutLecture getStatutLecture() {
		return statutLecture;
	}

	/**
	 * @param statutLecture the statutLecture to set
	 */
	public final void setStatutLecture(EnStatutLecture statutLecture) {
		this.statutLecture = statutLecture;
	}

	/**
	 * @return the statutTelechargement
	 */
	public final EnStatutTelechargement getStatutTelechargement() {
		return statutTelechargement;
	}

	/**
	 * @param statutTelechargement the statutTelechargement to set
	 */
	public final void setStatutTelechargement(
			EnStatutTelechargement statutTelechargement) {
		this.statutTelechargement = statutTelechargement;
	}

	/**
	 * @return the duree
	 */
	public final Date getDuree() {
		return duree;
	}

	/**
	 * @param duree the duree to set
	 */
	public final void setDuree(Date duree) {
		this.duree = duree;
	}

	/**
	 * @return the typeEpisode
	 */
	public final EnTypeEpisode getTypeEpisode() {
		return typeEpisode;
	}

	/**
	 * @param typeEpisode the typeEpisode to set
	 */
	public final void setTypeEpisode(EnTypeEpisode typeEpisode) {
		this.typeEpisode = typeEpisode;
	}

	/**
	 * @return the categorie
	 */
	public final MlCategorie getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie the categorie to set
	 */
	public final void setCategorie(MlCategorie categorie) {
		this.categorie = categorie;
	}
}
