package fr.smardine.podcaster.database.accestable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import fr.smardine.podcaster.database.RequeteFactory;
import fr.smardine.podcaster.database.structure.EnStructEpisode;
import fr.smardine.podcaster.database.structure.EnStructFlux;
import fr.smardine.podcaster.database.structure.EnTable;
import fr.smardine.podcaster.mdl.MlFlux;
import fr.smardine.podcaster.mdl.MlListeFlux;

/**
 * @author smardine Acces a la table des Notes enregistré en base
 */
public class AccesTableFlux {

	private final RequeteFactory requeteFact;
	private final Context ctx;

	/**
	 * @param p_ctx le contexte
	 */
	public AccesTableFlux(Context p_ctx) {
		this.ctx = p_ctx;
		requeteFact = new RequeteFactory(p_ctx);
	}

	/**
	 * @return obtenir le nombre d'enregistrement dans la table
	 */
	public int getNbEnregistrement() {
		return requeteFact.getNombreEnregistrement(EnTable.FLUX);
	}

	public void insertFlux(MlFlux p_flux) {
		ContentValues content = createContentValueForMlFlux(p_flux);
		// content.put(EnStructFlux.DATE_DERNIERE_SYNCHRO.toString(), p_flux.getDateDerniereSynchro());
		// content.put(EnStructFlux.TITRE.toString(), p_flux.getTitre());
		// content.put(EnStructFlux.VIGNETTE_URL.toString(), p_flux.getVignetteUrl());
		// if (p_flux.getVignetteTelechargee() != null) {
		// content.put(EnStructFlux.VIGNETTE_FILE.toString(), p_flux.getVignetteTelechargee().getAbsolutePath());
		// }
		//
		// content.put(EnStructFlux.URL.toString(), p_flux.getFluxUrl());
		requeteFact.insertDansTable(EnTable.FLUX, content);

		p_flux.setIdFlux(Integer.parseInt(requeteFact.get1Champ("SELECT MAX (" + EnStructFlux.ID_FLUX.toString() + ") FROM "
				+ EnTable.FLUX.toString())));

	}

	public void majFlux(MlFlux p_flux) {
		ContentValues modifiedValue = createContentValueForMlFlux(p_flux);
		String whereClause = EnStructFlux.ID_FLUX.getNomChamp() + "=?";
		String[] whereArgs = { "" + p_flux.getIdFlux() };
		requeteFact.majTable(EnTable.FLUX, modifiedValue, whereClause, whereArgs);
	}

	/**
	 * Creation des content Value, utilisaé a la fois pour l'insertion et la maj en bdd
	 * @param p_flux
	 * @return
	 */
	private ContentValues createContentValueForMlFlux(MlFlux p_flux) {
		ContentValues content = new ContentValues();
		content.put(EnStructFlux.DATE_DERNIERE_SYNCHRO.toString(), p_flux.getDateDerniereSynchro());
		content.put(EnStructFlux.TITRE.toString(), p_flux.getTitre());
		content.put(EnStructFlux.VIGNETTE_URL.toString(), p_flux.getVignetteUrl());
		if (p_flux.getVignetteTelechargee() != null) {
			content.put(EnStructFlux.VIGNETTE_FILE.toString(), p_flux.getVignetteTelechargee().getAbsolutePath());
		}

		content.put(EnStructFlux.URL.toString(), p_flux.getFluxUrl());
		return content;
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
		requeteFact.deleteTable(EnTable.FLUX, "1", null);

	}

	public MlListeFlux getListeDesFlux() {
		MlListeFlux lstRetour = new MlListeFlux();
		AccesTableEpisode tableEpisode = new AccesTableEpisode(ctx);
		List<ArrayList<Object>> listeDeChamp = requeteFact.getListeDeChampBis(EnTable.FLUX, EnStructFlux.class, null);
		for (ArrayList<Object> unEnregistrement : listeDeChamp) {
			MlFlux unFlux = new MlFlux();
			for (int i = 0; i < unEnregistrement.size(); i++) {
				if (i == 0) {
					unFlux.setIdFlux((Integer) unEnregistrement.get(i));
				} else if (i == 1) {
					unFlux.setTitre((String) unEnregistrement.get(i));
				} else if (i == 2) {
					unFlux.setDateDerniereSynchro((Long) unEnregistrement.get(i));
				} else if (i == 3) {
					unFlux.setVignetteUrl((String) unEnregistrement.get(i));
				} else if (i == 4) {
					String filePath = (String) unEnregistrement.get(i);
					if (filePath != null && !("").equals(filePath)) {
						unFlux.setVignetteTelechargee(new File(filePath));
					}

				} else if (i == 5) {
					// triaiter l'id parametre plus tard
				} else if (i == 6) {
					// traiter l'id categorie plus tard
				} else if (i == 7) {
					unFlux.setFluxUrl((String) unEnregistrement.get(i));
				}
			}

			unFlux.setListeEpisode(tableEpisode.getListeDesEpisodeParIdFlux(unFlux));
			lstRetour.add(unFlux);
		}
		return lstRetour;

	}

	public void deleteFluxEtEpisode(MlFlux fluxSelectionne) {
		String scriptDeleteEpisodes = "DELETE FROM " + EnTable.EPISODE + " WHERE " + EnStructEpisode.ID_FLUX.getNomChamp() + "="
				+ fluxSelectionne.getIdFlux();
		String scriptDeleteFlux = "DELETE FROM " + EnTable.FLUX + " WHERE " + EnStructFlux.ID_FLUX.getNomChamp() + "="
				+ fluxSelectionne.getIdFlux();
		// requeteFact.deleteTable(p_table, p_whereClause, p_whereArgs)

	}
}
