package fr.smardine.podcaster.mdl;

import java.io.File;
import java.io.Serializable;

public class MlEpisode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8478705652165819479L;
	private int idEpisode;
	private int idFluxParent;
	private String titre;
	private String description;
	private String urlEpisode;
	private EnStatutLecture statutLecture;
	private EnStatutTelechargement statutTelechargement;
	private String duree;
	private long datePublication;
	private EnTypeEpisode typeEpisode;
	private MlCategorie categorie;
	private File vignetteTelechargee;
	private String vignetteUrl;
	private final MlFlux fluxParent;

	/**
	 * Contient l'url du media
	 */
	private String guid;
	private boolean statutNouveau;

	public boolean isStatutNouveau() {
		return statutNouveau;
	}

	public MlEpisode(MlFlux p_fluxParent) {
		this.fluxParent = p_fluxParent;
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

	public int getIdFluxParent() {
		return idFluxParent;
	}

	public void setIdFluxParent(int idFluxParent) {
		this.idFluxParent = idFluxParent;
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

	public void setUrlEpisode(String link) {
		this.urlEpisode = link;
	}

	public String getUrlEpisode() {
		return urlEpisode;
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
	public final void setStatutTelechargement(EnStatutTelechargement statutTelechargement) {
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
		if (duree == null || duree.equals("")) {
			this.duree = "00:00:00";
		} else {
			this.duree = duree;
		}

	}

	public void setDatePublication(long datePublication) {
		this.datePublication = datePublication;
	}

	public long getDatePublication() {
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

	public boolean isVignetteTelechargee() {
		return vignetteTelechargee != null && vignetteTelechargee.exists();
	}

	/**
	 * @return the vignetteUrl
	 */
	public String getVignetteUrl() {
		return vignetteUrl;
	}

	/**
	 * @param vignetteUrl the vignetteUrl to set
	 */
	public void setVignetteUrl(String vignetteUrl) {
		this.vignetteUrl = vignetteUrl;
	}

	/**
	 * Positionne le statut de lecture d'une episode {@link EnStatutLecture} Si l'episode est deja connu en base (basé sur la date de
	 * publication, le titre et la durée) on positionne le statut de l'episode en cours par rappport a celui en base Sinon, le statut est
	 * "NonLu"
	 * @param p_context
	 */
	public void positionneStatutLecture() {
		MlListeEpisode listeEnbase = this.fluxParent.getListeEpisode();
		for (MlEpisode unEpisode : listeEnbase) {
			if (unEpisode.datePublication == this.datePublication && unEpisode.getTitre().equals(this.titre)
					&& unEpisode.getDuree().equals(this.duree)) {
				this.statutLecture = unEpisode.getStatutLecture();
				return;
			}
		}
		// on a parcouru tous les episodes sans trouver de reference en base, le statut est positionné à "NonLu"
		this.statutLecture = EnStatutLecture.NonLu;
	}

	/**
	 * /** Positionne le statut de telechargement d'une episode {@link EnStatutTelechargement} Si l'episode est deja connu en base (basé sur
	 * la date de publication, le titre et la durée) on positionne le statut de l'episode en cours par rappport a celui en base Sinon, le
	 * statut est "streaming"
	 * @param p_context
	 */

	public void positionneStatutTelechargement() {

		MlListeEpisode listeEnbase = this.fluxParent.getListeEpisode();
		for (MlEpisode unEpisode : listeEnbase) {
			if (unEpisode.datePublication == this.datePublication && unEpisode.getTitre().equals(this.titre)
					&& unEpisode.getDuree().equals(this.duree)) {
				this.statutTelechargement = unEpisode.getStatutTelechargement();
				return;
			}
		}
		// on a parcouru tous les episodes sans trouver de reference en base, le statut est positionné à "streaming"
		this.statutTelechargement = EnStatutTelechargement.Streaming;

	}

	public boolean isNouveau() {
		MlListeEpisode listeEnbase = this.fluxParent.getListeEpisode();

		for (MlEpisode unEpisode : listeEnbase) {
			if (unEpisode.datePublication == this.datePublication && unEpisode.getTitre().equals(this.titre)
					&& unEpisode.getDuree().equals(this.duree)) {
				return false;
			}
		}
		// on a parcouru tous les episode sans trouver de reference en bdd
		// L'episode est un nouveau
		return true;
	}

	/**
	 * @return the fluxParent
	 */
	public MlFlux getFluxParent() {
		return fluxParent;
	}

	public void setStatutNouveau(boolean b) {
		this.statutNouveau = b;

	}
}
