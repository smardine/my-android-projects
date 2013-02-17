package fr.smardine.podcaster.database.accestable;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import fr.smardine.podcaster.database.RequeteFactory;
import fr.smardine.podcaster.database.structure.EnStructEpisode;
import fr.smardine.podcaster.database.structure.EnTable;
import fr.smardine.podcaster.mdl.EnStatutLecture;
import fr.smardine.podcaster.mdl.EnStatutTelechargement;
import fr.smardine.podcaster.mdl.EnTypeEpisode;
import fr.smardine.podcaster.mdl.MlEpisode;
import fr.smardine.podcaster.mdl.MlFlux;
import fr.smardine.podcaster.mdl.MlListeEpisode;

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
		ContentValues content = new ContentValues();
		content.put(EnStructEpisode.ID_FLUX.toString(), p_episode.getIdFluxParent());
		content.put(EnStructEpisode.TITRE.toString(), p_episode.getTitre());
		content.put(EnStructEpisode.DESCRIPTION.toString(), p_episode.getDescription());
		content.put(EnStructEpisode.URL.toString(), p_episode.getUrlEpisode());
		content.put(EnStructEpisode.STATUT_LECTURE.toString(), p_episode.getStatutLecture().name());
		content.put(EnStructEpisode.STATUT_TELECHARGEMENT.toString(), p_episode.getStatutTelechargement().name());
		content.put(EnStructEpisode.DUREE.toString(), p_episode.getDuree());
		content.put(EnStructEpisode.DATE_PUBLICATION.toString(), p_episode.getDatePublication().toString());
		content.put(EnStructEpisode.TYPE_EPISODE.toString(), p_episode.getTypeEpisode().name());
		content.put(EnStructEpisode.GUID.toString(), p_episode.getGuid());
		// content.put(EnStructEpisode.ID_CATEGORIE.toString(),p_episode.getCategorie().getIdCategorie());
		content.put(EnStructEpisode.VIGNETTE_TELECHARGEE.toString(), p_episode.getVignetteTelechargee().getAbsolutePath());
		requeteFact.insertDansTable(EnTable.EPISODE, content);

	}

	// /**
	// * @param p_note
	// */
	// public void majTitreEtMessage(MlNote p_note) {
	// ContentValues modifiedValues = new ContentValues();
	// modifiedValues.put("Titre", p_note.getTitre());
	// modifiedValues.put("Message", p_note.getMessage());
	// String whereClause = "id_note=?";
	// String[] whereArgs = { "" + p_note.getIdNote() };
	// requeteFact.majTable(EnTable.NOTES, modifiedValues, whereClause,
	// whereArgs);
	// }
	//
	// /**
	// * @param p_idNote
	// * @return une liste de tableau de string
	// */
	// public ArrayList<String> getDefNoteById(int p_idNote) {
	// String requete = "Select " + EnStructNotes.MESSAGE.getNomChamp() + " ,"
	// + EnStructNotes.TITRE.getNomChamp() + //
	// " From " + EnTable.NOTES.getNomTable() + //
	// " Where " + EnStructNotes.ID.getNomChamp() + "=" + p_idNote;
	// return requeteFact.getListeDeChamp(requete).get(0);
	//
	// }
	//
	// /**
	// * @return une liste de MlNote
	// */
	// public MlListeFlux getListeNote() {
	// MlListeFlux listeFlux = new MlListeFlux();
	// String requete = "SELECT " + EnStructFlux.ID_FLUX.getNomChamp()
	// + " FROM " + EnTable.NOTES.getNomTable() + " ORDER BY "
	// + EnStructNotes.ID.getNomChamp();
	//
	// List<ArrayList<String>> lstId = requeteFact.getListeDeChamp(requete);
	// for (ArrayList<String> anId : lstId) {
	// MlNote n = new MlNote(Integer.parseInt(anId.get(0)), ctx);
	// listeFlux.add(n);
	// }
	// return listeFlux;
	// }

	// /**
	// * @param p_flux
	// */
	// public void createNewNoteDepuisProduit(MlFlux p_flux) {
	// ContentValues values = new ContentValues();
	// values.put("Titre",
	// "[Auto] " + p_flux.getNomProduit() + " " + p_flux.getMarque());
	// values.put(
	// "Message",
	// "Produit acheté le: " + p_flux.getDateAchat() + "\n"
	// + "Catégorie du produit: "
	// + p_flux.getCategorie().getSousCategorie() + "\n"
	// + "Numéro de teinte: " + p_flux.getTeinte() + "\n"
	// + "Durée de vie du produit: " + p_flux.getDureeVie()
	// + " mois\n" + "Date de péremption: "
	// + p_flux.getDatePeremption() + "\n");
	//
	// requeteFact.insertDansTable(EnTable.NOTES, values);
	//
	// }

	// /**
	// * @param p_titre
	// * @param p_message
	// * @return true ou false
	// */
	// public boolean createNewNote(String p_titre, String p_message) {
	// ContentValues values = new ContentValues();
	// values.put("Titre", p_titre);
	// values.put("Message", p_message);
	// return requeteFact.insertDansTable(EnTable.NOTES, values);
	// }

	// /**
	// * @param p_idNote
	// * @return true ou false
	// */
	// public boolean deleteNote(int p_idNote) {
	// String whereClause = "id_note=?";
	// String[] WhereArgs = new String[] { "" + p_idNote };
	//
	// // objBd.open();
	// int nbChampEfface = requeteFact.deleteTable(EnTable.NOTES, whereClause,
	// WhereArgs);
	// if (nbChampEfface == 1) {
	// return true;
	// }
	// return false;
	// }

	/**
	 * 
	 */
	public void deleteTable() {
		requeteFact.deleteTable(EnTable.EPISODE, "1", null);

	}

	public MlListeEpisode getListeDesEpisodeParIdFlux(MlFlux p_flux) {
		MlListeEpisode lstRetour = new MlListeEpisode();
		List<ArrayList<Object>> listeDeChamp = requeteFact.getListeDeChampBis(EnTable.EPISODE, EnStructEpisode.class,
				EnStructEpisode.ID_FLUX.toString() + "=" + p_flux.getIdFlux());
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
					unEpisode.setDatePublication(new Date(Date.parse((String) unEnregistrement.get(i))));
				} else if (i == 10) {
					unEpisode.setTypeEpisode(EnTypeEpisode.GetTypeEpisodeByName((String) unEnregistrement.get(i)));
				} else if (i == 11) {
					// traiter l'id categorie plus tard
				} else if (i == 12) {
					unEpisode.setVignetteTelechargee(new File((String) unEnregistrement.get(i)));
				}
			}

			lstRetour.add(unEpisode);
		}
		return lstRetour;

	}

}
