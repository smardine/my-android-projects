package fr.smardine.podcaster.mdl;

import java.util.ArrayList;

public class MlListeFlux extends ArrayList<MlFlux> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1331250459788965471L;

	public MlListeEpisode GetAllEpisode() {
		MlListeEpisode lstRetour = new MlListeEpisode();
		for (MlFlux unFLux : this) {
			lstRetour.addAll(unFLux.getListeEpisode());
		}
		return lstRetour;
	}

}
