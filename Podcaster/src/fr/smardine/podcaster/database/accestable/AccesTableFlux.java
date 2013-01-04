package fr.smardine.podcaster.database.accestable;

import android.content.ContentValues;
import android.content.Context;
import fr.smardine.podcaster.database.RequeteFactory;
import fr.smardine.podcaster.database.structure.EnStructFlux;
import fr.smardine.podcaster.database.structure.EnTable;
import fr.smardine.podcaster.mdl.MlFlux;

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

	public void createFlux(MlFlux p_flux) {
		ContentValues content = new ContentValues();
		content.put(EnStructFlux.DATE_DERNIERE_SYNCHRO.toString(), p_flux
				.getDateDerniereSynchro().toString());
		content.put(EnStructFlux.TITRE.toString(), p_flux.getTitre());
		content.put(EnStructFlux.VIGNETTE_URL.toString(),
				p_flux.getVignetteUrl());
		content.put(EnStructFlux.URL.toString(), p_flux.getFluxUrl());
		requeteFact.insertDansTable(EnTable.FLUX, content);

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
}
