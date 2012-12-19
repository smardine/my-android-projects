package fr.smardine.podcaster.mdl;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

public class MlEpisode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8478705652165819479L;
	private int idEpisode;
	private String titre;
	private String description;
	private String link;
	private EnStatutLecture statutLecture;
	private EnStatutTelechargement statutTelechargement;
	private String duree;
	private Date datePublication;
	private EnTypeEpisode typeEpisode;
	private MlCategorie categorie;
	private File vignetteTelechargee;
	private boolean isVignetteTelechargee;
	/**
	 * Contient l'url du media
	 */
	private String guid;

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

	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return link;
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
	public final String getDuree() {
		return duree;
	}

	/**
	 * @param duree the duree to set
	 */
	public final void setDuree(String duree) {
		this.duree = duree;
	}

	public void setDatePublication(Date datePublication) {
		this.datePublication = datePublication;
	}

	public Date getDatePublication() {
		return datePublication;
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

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getGuid() {
		return guid;
	}

	public void setVignetteTelechargee(File p_vignette) {
		this.vignetteTelechargee = p_vignette;
	}

	public File getVignetteTelechargee() {
		return vignetteTelechargee;
	}

	public void setVignetteTelechargee(boolean isVignetteTelechargee) {
		this.isVignetteTelechargee = isVignetteTelechargee;
	}

	public boolean isVignetteTelechargee() {
		return isVignetteTelechargee;
	}
}
