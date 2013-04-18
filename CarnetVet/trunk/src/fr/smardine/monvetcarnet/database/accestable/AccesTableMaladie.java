package fr.smardine.monvetcarnet.database.accestable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import fr.smardine.monvetcarnet.database.structuretable.EnStructMaladie;
import fr.smardine.monvetcarnet.database.structuretable.EnTable;
import fr.smardine.monvetcarnet.mdl.MlCarnet;
import fr.smardine.monvetcarnet.mdl.MlMaladie;

/**
 * Classe d'acces a la table Maladie
 * @author sims
 */
public class AccesTableMaladie extends AccesTable<MlMaladie> {

	/**
	 * Constructeur
	 * @param p_ctx
	 */
	public AccesTableMaladie(Context p_ctx) {
		super(p_ctx, EnTable.MALADIES);
	}

	/**
	 * methode permettant de creer un objet pour l'insertion en base a partir d'un MlMaladie
	 */
	@Override
	protected ContentValues createContentValueForObject(MlMaladie p_object) {
		ContentValues values = new ContentValues();
		values.put(EnStructMaladie.DATE.getNomChamp(), p_object.getDate().getTime());
		values.put(EnStructMaladie.ID_CARNET_PARENT.getNomChamp(), p_object.getCarnetParent().getId());
		values.put(EnStructMaladie.ID_MALADIE.getNomChamp(), p_object.getIdMaladie());
		values.put(EnStructMaladie.RDV_VETO.getNomChamp(), Boolean.toString(p_object.isRdvVeto()));
		values.put(EnStructMaladie.SYMPTOME.getNomChamp(), p_object.getSymptomes());
		values.put(EnStructMaladie.TITRE.getNomChamp(), p_object.getTitre());
		values.put(EnStructMaladie.TRAITEMENT.getNomChamp(), p_object.getTraitement());
		return values;
	}

	/**
	 * Obtenir la liste des MlMaladies en base a partir d'un MlCarnet
	 * @param p_carnetParent
	 * @return
	 */
	public List<MlMaladie> getListeDeMaladiesParIdCarnet(MlCarnet p_carnetParent) {
		List<ArrayList<Object>> listeDeChamp = requeteFact.getListeDeChampBis(EnTable.MALADIES, EnStructMaladie.ID_CARNET_PARENT.toString()
				+ "=" + p_carnetParent.getId());

		List<MlMaladie> lstRetour = new ArrayList<MlMaladie>();

		for (ArrayList<Object> unEnregistrement : listeDeChamp) {

			MlMaladie uneMaladie = new MlMaladie(p_carnetParent);
			uneMaladie.setDate(new Date((Long) unEnregistrement.get(EnStructMaladie.DATE.getindex())));
			// uneMaladie.setIdCarnetParent((Integer) unEnregistrement.get(EnStructMaladie.ID_CARNET_PARENT.getindex()));
			uneMaladie.setIdMaladie((Integer) unEnregistrement.get(EnStructMaladie.ID_MALADIE.getindex()));
			uneMaladie.setRdvVeto(Boolean.parseBoolean((String) unEnregistrement.get(EnStructMaladie.RDV_VETO.getindex())));
			uneMaladie.setSymptomes((String) unEnregistrement.get(EnStructMaladie.SYMPTOME.getindex()));
			uneMaladie.setTitre((String) unEnregistrement.get(EnStructMaladie.TITRE.getindex()));
			uneMaladie.setTraitement((String) unEnregistrement.get(EnStructMaladie.TRAITEMENT.getindex()));

			lstRetour.add(uneMaladie);
		}
		return lstRetour;
	}

	/**
	 * Inserer un MlMaladie en base
	 * @param p_maladie
	 * @return
	 */
	protected boolean insertMaladieEnBase(MlMaladie p_maladie) {
		return super.insertObjectEnBase(p_maladie);
	}

	/**
	 * Mettre a jour un MlMaladie en base
	 * @param p_maladie
	 * @return
	 */
	protected boolean majMaladieEnBase(MlMaladie p_maladie) {
		return super.majObjetEnBase(p_maladie);
	}

	/**
	 * Effacer la table
	 */
	@Override
	public boolean deleteTable() {
		return super.deleteTable();
	}

}
