package fr.smardine.monvetcarnet.database.accestable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import fr.smardine.monvetcarnet.database.structuretable.EnStructVaccin;
import fr.smardine.monvetcarnet.database.structuretable.EnTable;
import fr.smardine.monvetcarnet.mdl.EnNomVaccin;
import fr.smardine.monvetcarnet.mdl.MlCarnet;
import fr.smardine.monvetcarnet.mdl.MlVaccin;

/**
 * Classe d'acces a la table Vaccin
 * @author sims
 */
public class AccesTableVaccin extends AccesTable<MlVaccin> {

	/**
	 * Constructeur
	 * @param p_ctx
	 */
	public AccesTableVaccin(Context p_ctx) {
		super(p_ctx, EnTable.VACCINS);

	}

	/**
	 * methode permettant de creer un objet pour l'insertion en base a partir d'un MlMaladie
	 */
	@Override
	protected ContentValues createContentValueForObject(MlVaccin p_object) {
		ContentValues values = new ContentValues();
		values.put(EnStructVaccin.DATE.getNomChamp(), p_object.getDate().getTime());
		values.put(EnStructVaccin.ID_CARNET_PARENT.getNomChamp(), p_object.getCarnetParent().getId());
		// values.put(EnStructVaccin.ID_VACCIN.getNomChamp(), p_object.getIdVaccin());
		if (p_object.getNomVaccin() != null) {
			values.put(EnStructVaccin.NOM.getNomChamp(), p_object.getNomVaccin().name());
		}
		values.put(EnStructVaccin.VERMIFUGE.getNomChamp(), Boolean.toString(p_object.isVermifuge()));
		return values;
	}

	/**
	 * Obtenir la listes des MlVaccins associé à un MlCarnet
	 * @param p_carnetParent
	 * @return
	 */
	public List<MlVaccin> getListeDeVaccinsParIdCarnet(MlCarnet p_carnetParent) {
		List<ArrayList<Object>> listeDeChamp = requeteFact.getListeDeChampBis(EnTable.VACCINS, EnStructVaccin.ID_CARNET_PARENT.toString()
				+ "=" + p_carnetParent.getId() + " ORDER BY " + EnStructVaccin.ID_VACCIN.toString() + " DESC");

		List<MlVaccin> lstRetour = new ArrayList<MlVaccin>();

		for (ArrayList<Object> unEnregistrement : listeDeChamp) {

			MlVaccin unVaccin = new MlVaccin(p_carnetParent);
			unVaccin.setDate(new Date((Long) unEnregistrement.get(EnStructVaccin.DATE.getindex())));
			// unVaccin.setIdCarnetParent((Integer) unEnregistrement.get(EnStructVaccin.ID_CARNET_PARENT.getindex()));
			unVaccin.setIdVaccin((Integer) unEnregistrement.get(EnStructVaccin.ID_VACCIN.getindex()));
			unVaccin.setNomVaccin(EnNomVaccin.getEnumFromName((String) unEnregistrement.get(EnStructVaccin.NOM.getindex())));
			unVaccin.setVermifuge(Boolean.parseBoolean((String) unEnregistrement.get(EnStructVaccin.VERMIFUGE.getindex())));
			lstRetour.add(unVaccin);
		}
		return lstRetour;
	}

	/**
	 * Inserer un MlVaccin en base
	 * @param p_vaccin
	 * @return
	 */
	public boolean insertVaccinEnBase(MlVaccin p_vaccin) {
		boolean result = super.insertObjectEnBase(p_vaccin);
		if (result) {
			p_vaccin.setIdVaccin(Integer.parseInt(requeteFact.get1Champ("SELECT MAX (" + EnStructVaccin.ID_VACCIN.toString() + ") FROM "
					+ EnTable.VACCINS.toString())));
		}
		return result;
	}

	/**
	 * Mettre a jour un MlVaccin en base
	 * @param p_vaccin
	 * @return
	 */
	protected boolean majVaccinEnBase(MlVaccin p_vaccin) {
		return super.majObjetEnBase(p_vaccin);
	}

	/**
	 * Effacer la table
	 */
	@Override
	public boolean deleteTable() {
		return super.deleteTable();
	}

}
