package fr.smardine.monvetcarnet.helper;

/**
 * Type enumer� permettant de surcharger le mode de visibilit� d'un composant
 * @author sims
 */
public enum EnStatutVisibilite {
	VISIBLE(0), INVISIBLE(4), RETIRE(8);

	private int code;

	private EnStatutVisibilite(int p_visibilite) {
		this.code = p_visibilite;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

}
