package fr.smardine.podcaster.mdl;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

public class MlFlux implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6060567456419685414L;
	private MlListeEpisode listeEpisode;
	private int idFlux;
	private String titre;
	private long dateDerniereSynchro;
	private File vignetteTelechargee;
	private String vignetteUrl;
	private MlParametre parametres;
	private MlCategorie categorie;
	private String fluxUrl;

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
		if (this.listeEpisode != null && this.listeEpisode.size() > 0) {
			return new Date(this.listeEpisode.get(0).getDatePublication());
		} else {
			return new Date(this.getDateDerniereSynchro());
		}

	}

	/**
	 * @return the dateDerniereSynchro
	 */
	public final long getDateDerniereSynchro() {
		return dateDerniereSynchro;
	}

	/**
	 * @param dateDerniereSynchro the dateDerniereSynchro to set
	 */
	public final void setDateDerniereSynchro(long dateDerniereSynchro) {
		this.dateDerniereSynchro = dateDerniereSynchro;
	}

	/**
	 * @return the vignette
	 */
	public final String getVignetteUrl() {
		return vignetteUrl;
	}

	/**
	 * @param vignette the vignette to set
	 */
	public final void setVignetteUrl(String vignetteUrl) {
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

	public void setVignetteTelechargee(File vignetteTelechargee) {
		this.vignetteTelechargee = vignetteTelechargee;
	}

	public File getVignetteTelechargee() {
		return vignetteTelechargee;
	}

	public boolean isVignetteTelechargee() {
		return vignetteTelechargee != null && vignetteTelechargee.exists();
	}

	public void setFluxUrl(String fluxUrl) {
		this.fluxUrl = fluxUrl;
	}

	public String getFluxUrl() {
		return fluxUrl;
	}

	public EnTypeEpisode getTypeEpisodes() {
		if (this.listeEpisode != null && this.listeEpisode.size() > 0) {
			return this.listeEpisode.get(0).getTypeEpisode();
		} else {
			return EnTypeEpisode.Text;
		}
	}

}
