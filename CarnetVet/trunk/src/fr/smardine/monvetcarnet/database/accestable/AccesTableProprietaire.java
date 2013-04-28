package fr.smardine.monvetcarnet.database.accestable;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import fr.smardine.monvetcarnet.database.structuretable.EnStructProprietaire;
import fr.smardine.monvetcarnet.database.structuretable.EnTable;
import fr.smardine.monvetcarnet.mdl.MlDetail;
import fr.smardine.monvetcarnet.mdl.MlProprietaire;

/**
 * Classe d'acces à la table Proprietaire
 * @author sims
 */
public class AccesTableProprietaire extends AccesTable<MlProprietaire> {

	/**
	 * Constructeur
	 * @param p_ctx
	 */
	public AccesTableProprietaire(Context p_ctx) {
		super(p_ctx, EnTable.PROPRIETAIRES);

	}

	/**
	 * methode permettant de creer un objet pour l'insertion en base a partir d'un MlMaladie
	 */
	@Override
	protected ContentValues createContentValueForObject(MlProprietaire p_object) {
		ContentValues values = new ContentValues();
		values.put(EnStructProprietaire.ADRESSE.getNomChamp(), p_object.getAdresse());
		values.put(EnStructProprietaire.ID_DETAIL_PARENT.getNomChamp(), p_object.getDetailParent().getId());
		// values.put(EnStructProprietaire.ID_PROPRIETAIRE.getNomChamp(), p_object.getIdProprietaire());
		values.put(EnStructProprietaire.MAIL.getNomChamp(), p_object.getMail());
		values.put(EnStructProprietaire.NOM.getNomChamp(), p_object.getNom());
		values.put(EnStructProprietaire.TEL.getNomChamp(), p_object.getTelephone());
		return values;
	}

	/**
	 * Obtenir un MlProprietaire à partir d'un MlDetail parent et d'un idEleveurOuProprio
	 * @param p_DetailParent
	 * @param idEleveurOuProprioRecherche
	 * @return
	 */
	public MlProprietaire getProprietaireParIdDetailEtIdProprietaire(MlDetail p_DetailParent, int idEleveurOuProprioRecherche) {
		List<ArrayList<Object>> listeDeChamp = requeteFact.getListeDeChampBis(EnTable.PROPRIETAIRES,
				EnStructProprietaire.ID_DETAIL_PARENT.toString() + "=" + p_DetailParent.getId() + " and "
						+ EnStructProprietaire.ID_PROPRIETAIRE.toString() + "=" + idEleveurOuProprioRecherche);

		MlProprietaire unProprietaire = null;
		for (ArrayList<Object> unEnregistrement : listeDeChamp) {

			unProprietaire = new MlProprietaire(p_DetailParent);

			unProprietaire.setAdresse((String) unEnregistrement.get(EnStructProprietaire.ADRESSE.getindex()));
			// unProprietaire.setIdDetailParent((Integer) unEnregistrement.get(EnStructProprietaire.ID_DETAIL_PARENT.getindex()));
			unProprietaire.setIdProprietaire((Integer) unEnregistrement.get(EnStructProprietaire.ID_PROPRIETAIRE.getindex()));
			unProprietaire.setMail((String) unEnregistrement.get(EnStructProprietaire.MAIL.getindex()));
			unProprietaire.setNom((String) unEnregistrement.get(EnStructProprietaire.NOM.getindex()));
			unProprietaire.setTelephone((String) unEnregistrement.get(EnStructProprietaire.TEL.getindex()));

		}
		return unProprietaire;
	}

	/**
	 * Inserer un MlProprietaire en base
	 * @param p_proprietaire
	 * @return
	 */
	protected boolean insertProprietaireEnBase(MlProprietaire p_proprietaire) {
		return super.insertObjectEnBase(p_proprietaire);
	}

	/**
	 * Mettre a jour un MlProprietaire en base
	 * @param p_proprietaire
	 * @return
	 */
	protected boolean majProprietaireEnBase(MlProprietaire p_proprietaire) {
		return super.majObjetEnBase(p_proprietaire);
	}

	/**
	 * Effacer la table
	 */
	@Override
	public boolean deleteTable() {
		return super.deleteTable();
	}
}
