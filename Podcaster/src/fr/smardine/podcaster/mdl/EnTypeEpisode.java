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
		EnTypeEpisode[] tabType = EnTypeEpisode.values();
		for (int i = 0; i < tabType.length; i++) {
			if (tabType[i].name().equals(p_name) || tabType[i].getLib().equals(p_name)) {
				return tabType[i];
			}
		}
		// si ce n'est ni audio, ni video, ce sera du text, html ou non
		return Text;
	}

	public String getLib() {
		return this.lib;
	}
}
