package fr.smardine.monvetcarnet.database.accestable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import fr.smardine.monvetcarnet.database.structuretable.EnStructPoids;
import fr.smardine.monvetcarnet.database.structuretable.EnTable;
import fr.smardine.monvetcarnet.mdl.EnUnitePoids;
import fr.smardine.monvetcarnet.mdl.MlCarnet;
import fr.smardine.monvetcarnet.mdl.MlPoids;

/**
 * Classe d'acces a la table Poids
 * @author sims
 */
public class AccesTablePoids extends AccesTable<MlPoids> {

	/**
	 * Constructeur
	 * @param p_ctx
	 */
	public AccesTablePoids(Context p_ctx) {
		super(p_ctx, EnTable.POIDS);

	}

	/**
	 * methode permettant de creer un objet pour l'insertion en base a partir d'un MlMaladie
	 */
	@Override
	protected ContentValues createContentValueForObject(MlPoids p_object) {
		ContentValues values = new ContentValues();
		values.put(EnStructPoids.DATE.getNomChamp(), p_object.getDate().getTime());
		values.put(EnStructPoids.ID_CARNET_PARENT.getNomChamp(), p_object.getCarnetParent().getId());
		// values.put(EnStructPoids.ID_POID.getNomChamp(), p_object.getIdPoid());
		values.put(EnStructPoids.NOTE.getNomChamp(), p_object.getNote());
		values.put(EnStructPoids.UNITE.getNomChamp(), p_object.getUnitePoids().name());
		values.put(EnStructPoids.VALEUR.getNomChamp(), p_object.getValeur());

		return values;
	}

	/**
	 * Obtenir la liste des MlPoids en base a partir d'un MlCarnet
	 * @param p_carnetParent
	 * @return
	 */
	public List<MlPoids> getListeDePoidsParIdCarnet(MlCarnet p_carnetParent) {
		List<ArrayList<Object>> listeDeChamp = new ArrayList<ArrayList<Object>>();
		if (p_carnetParent != null) {
			listeDeChamp = requeteFact.getListeDeChampBis(EnTable.POIDS,
					EnStructPoids.ID_CARNET_PARENT.toString() + "=" + p_carnetParent.getId());
		}

		List<MlPoids> lstRetour = new ArrayList<MlPoids>();

		for (ArrayList<Object> unEnregistrement : listeDeChamp) {

			MlPoids unPoids = new MlPoids(p_carnetParent);
			unPoids.setDate(new Date((Long) unEnregistrement.get(EnStructPoids.DATE.getindex())));
			// unPoids.setIdCarnetParent((Integer) unEnregistrement.get(EnStructPoids.ID_CARNET_PARENT.getindex()));
			unPoids.setIdPoid((Integer) unEnregistrement.get(EnStructPoids.ID_POID.getindex()));
			unPoids.setNote((String) unEnregistrement.get(EnStructPoids.NOTE.getindex()));
			unPoids.setUnitePoids(EnUnitePoids.getEnumFromName((String) unEnregistrement.get(EnStructPoids.UNITE.getindex())));
			unPoids.setValeur(Double.valueOf((String) unEnregistrement.get(EnStructPoids.VALEUR.getindex())));
			lstRetour.add(unPoids);
		}
		return lstRetour;
	}

	/**
	 * Inserer un MlPoids en base
	 * @param p_poid
	 * @return
	 */
	public boolean insertPoidEnBase(MlPoids p_poid) {
		return super.insertObjectEnBase(p_poid);
	}

	/**
	 * Mettre a jour un MlPoids en base
	 * @param p_poid
	 * @return
	 */
	public boolean majPoidEnBase(MlPoids p_poid) {
		return super.majObjetEnBase(p_poid);
	}

	/**
	 * Effacer la table
	 */
	@Override
	public boolean deleteTable() {
		return super.deleteTable();
	}

}
