package fr.smardine.monvetcarnet.database.accestable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import fr.smardine.monvetcarnet.database.RequeteFactory;
import fr.smardine.monvetcarnet.database.structuretable.EnTable;

public class AccesTableEpisode {

	private final RequeteFactory requeteFact;

	/**
	 * @param p_ctx le contexte
	 */
	public AccesTableEpisode(Context p_ctx) {
		requeteFact = new RequeteFactory(p_ctx);
	}

	/**
	 * @return obtenir le nombre d'enregistrement dans la table
	 */
	public int getNbEnregistrement() {
		return requeteFact.getNombreEnregistrement(EnTable.EPISODE);
	}

	public void createEpisode(MlEpisode p_episode) {
		ContentValues content = createValuesForMlEpisode(p_episode);
		requeteFact.insertDansTable(EnTable.EPISODE, content);
	}

	public void majEpisode(MlEpisode p_episode) {
		ContentValues modifiedValue = createValuesForMlEpisode(p_episode);
		String whereClause = EnStructEpisode.ID_EPISODE.getNomChamp() + "=?";
		String[] whereArgs = { "" + p_episode.getIdEpisode() };
		requeteFact.majTable(EnTable.EPISODE, modifiedValue, whereClause, whereArgs);
	}

	private ContentValues createValuesForMlEpisode(MlEpisode p_episode) {
		ContentValues content = new ContentValues();
		content.put(EnStructEpisode.ID_FLUX.toString(), p_episode.getIdFluxParent());
		content.put(EnStructEpisode.TITRE.toString(), p_episode.getTitre());
		content.put(EnStructEpisode.DESCRIPTION.toString(), p_episode.getDescription());
		content.put(EnStructEpisode.URL.toString(), p_episode.getUrlEpisode());
		content.put(EnStructEpisode.STATUT_LECTURE.toString(), p_episode.getStatutLecture().name());
		content.put(EnStructEpisode.STATUT_TELECHARGEMENT.toString(), p_episode.getStatutTelechargement().name());
		content.put(EnStructEpisode.DUREE.toString(), p_episode.getDuree());
		content.put(EnStructEpisode.DATE_PUBLICATION.toString(), p_episode.getDatePublication());
		content.put(EnStructEpisode.TYPE_EPISODE.toString(), p_episode.getTypeEpisode().name());
		content.put(EnStructEpisode.GUID.toString(), p_episode.getGuid());
		// content.put(EnStructEpisode.ID_CATEGORIE.toString(),p_episode.getCategorie().getIdCategorie());
		if (p_episode.getVignetteTelechargee() != null) {
			content.put(EnStructEpisode.VIGNETTE_TELECHARGEE.toString(), p_episode.getVignetteTelechargee().getAbsolutePath());
		}
		content.put(EnStructEpisode.URL_MEDIA.toString(), p_episode.getUrlMedia());
		if (p_episode.getMediaTelecharge() != null) {
			content.put(EnStructEpisode.MEDIA_TELECHARGEE.toString(), p_episode.getMediaTelecharge().getAbsolutePath());
		}

		return content;
	}

	/**
	 * 
	 */
	public void deleteTable() {
		requeteFact.deleteTable(EnTable.EPISODE, "1", null);
	}

	public MlListeEpisode getListeDesEpisodeParIdFlux(MlFlux p_flux) {
		MlListeEpisode lstRetour = new MlListeEpisode();
		List<ArrayList<Object>> listeDeChamp = requeteFact.getListeDeChampBis(EnTable.EPISODE, EnStructEpisode.class,
				EnStructEpisode.ID_FLUX.toString() + "=" + p_flux.getIdFlux() + " ORDER BY " + EnStructEpisode.DATE_PUBLICATION.toString()
						+ " DESC");
		for (ArrayList<Object> unEnregistrement : listeDeChamp) {
			MlEpisode unEpisode = new MlEpisode(p_flux);
			for (int i = 0; i < unEnregistrement.size(); i++) {
				if (i == 0) {
					unEpisode.setIdEpisode((Integer) unEnregistrement.get(i));
				} else if (i == 1) {
					unEpisode.setIdFluxParent((Integer) unEnregistrement.get(i));
				} else if (i == 2) {
					unEpisode.setTitre((String) unEnregistrement.get(i));
				} else if (i == 3) {
					unEpisode.setDescription((String) unEnregistrement.get(i));
				} else if (i == 4) {
					unEpisode.setUrlEpisode((String) unEnregistrement.get(i));
				} else if (i == 5) {
					unEpisode.setStatutLecture(EnStatutLecture.GetStatutLectureByName((String) unEnregistrement.get(i)));
				} else if (i == 6) {
					unEpisode.setStatutTelechargement(EnStatutTelechargement.valueOf((String) unEnregistrement.get(i)));
				} else if (i == 7) {
					unEpisode.setDuree((String) unEnregistrement.get(i));
				} else if (i == 8) {
					unEpisode.setGuid((String) unEnregistrement.get(i));
				} else if (i == 9) {
					unEpisode.setDatePublication((Long) unEnregistrement.get(i));
				} else if (i == 10) {
					unEpisode.setTypeEpisode(EnTypeEpisode.GetTypeEpisodeByName((String) unEnregistrement.get(i)));
				} else if (i == 11) {
					// traiter l'id categorie plus tard
				} else if (i == 12) {
					String filePath = (String) unEnregistrement.get(i);
					if (filePath != null && !("").equals(filePath)) {
						unEpisode.setVignetteTelechargee(new File(filePath));
					}
				} else if (i == 13) {
					unEpisode.setUrlMedia((String) unEnregistrement.get(i));
				} else if (i == 14) {
					String filePath = (String) unEnregistrement.get(i);
					if (filePath != null && !("").equals(filePath)) {
						unEpisode.setMediaTelecharge(new File(filePath));
					}
				}
			}

			lstRetour.add(unEpisode);
		}
		return lstRetour;

	}
}
