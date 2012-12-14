package fr.smardine.podcaster.mdl;

import java.util.Date;

public class MlFlux {

	private MlListeEpisode listeEpisode;
	private int idFlux;
	private String titre;
	private Date dateDerniereSynchro;
	// private String dateDernierePublication;
	private String vignetteUrl;
	private MlParametre parametres;
	private MlCategorie categorie;
	private String url;

	public MlFlux() {
		listeEpisode = new MlListeEpisode();
	}

	/**
	 * @return the listeEpisode
	 */
	public final MlListeEpisode getListeEpisode() {
		return listeEpisode;
	}

	/**
	 * @param listeEpisode the listeEpisode to set
	 */
	public final void setListeEpisode(MlListeEpisode listeEpisode) {
		this.listeEpisode = listeEpisode;
	}

	/**
	 * @return the idFlux
	 */
	public final int getIdFlux() {
		return idFlux;
	}

	/**
	 * @param idFlux the idFlux to set
	 */
	public final void setIdFlux(int idFlux) {
		this.idFlux = idFlux;
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
	 * @return the dateDernierePublication
	 */
	public final Date getDateDernierePublication() {
		return this.listeEpisode.get(0).getDatePublication();
	}

	/**
	 * @return the dateDerniereSynchro
	 */
	public final Date getDateDerniereSynchro() {
		return dateDerniereSynchro;
	}

	/**
	 * @param dateDerniereSynchro the dateDerniereSynchro to set
	 */
	public final void setDateDerniereSynchro(Date dateDerniereSynchro) {
		this.dateDerniereSynchro = dateDerniereSynchro;
	}

	/**
	 * @return the vignette
	 */
	public final String getVignette() {
		return vignetteUrl;
	}

	/**
	 * @param vignette the vignette to set
	 */
	public final void setVignette(String vignetteUrl) {
		this.vignetteUrl = vignetteUrl;
	}

	/**
	 * @return the parametres
	 */
	public final MlParametre getParametres() {
		return parametres;
	}

	/**
	 * @param parametres the parametres to set
	 */
	public final void setParametres(MlParametre parametres) {
		this.parametres = parametres;
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

	/**
	 * @return the url
	 */
	public final String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public final void setUrl(String url) {
		this.url = url;
	}

}
