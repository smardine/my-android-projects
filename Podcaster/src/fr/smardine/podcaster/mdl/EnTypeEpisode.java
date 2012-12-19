package fr.smardine.podcaster.mdl;

public enum EnTypeEpisode {
	Audio("audio/mpeg"), Video("video/mp4"), Text("");
	private String lib;

	EnTypeEpisode(String p_lib) {
		this.lib = p_lib;
	}

	/**
	 * Retourver le type d'episode a partir de la valeur remonté dans le tag
	 * @param p_name
	 * @return
	 */
	public static EnTypeEpisode GetTypeEpisodeByName(String p_name) {
		for (EnTypeEpisode unType : EnTypeEpisode.values()) {
			if (unType.lib.equals(p_name)) {
				return unType;
			}
		}
		// si ce n'est ni audio, ni video, ce sera du text, html ou non
		return Text;
	}
}
