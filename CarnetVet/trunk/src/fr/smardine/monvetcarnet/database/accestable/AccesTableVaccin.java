package fr.smardine.monvetcarnet.database.accestable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import fr.smardine.monvetcarnet.database.structuretable.EnStructVaccin;
import fr.smardine.monvetcarnet.database.structuretable.EnTable;
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

		values.put(EnStructVaccin.IS_VERMIFUGE.getNomChamp(), Boolean.toString(p_object.isVermifuge()));
		values.put(EnStructVaccin.IS_CORYSA.getNomChamp(), Boolean.toString(p_object.isCorysa()));
		values.put(EnStructVaccin.IS_TYPHUS.getNomChamp(), Boolean.toString(p_object.isTiphus()));
		values.put(EnStructVaccin.IS_LEUCOSE.getNomChamp(), Boolean.toString(p_object.isLeucose()));
		values.put(EnStructVaccin.IS_CHLAMYDIOSE.getNomChamp(), Boolean.toString(p_object.isChlamydiose()));
		values.put(EnStructVaccin.IS_RAGE.getNomChamp(), Boolean.toString(p_object.isRage()));
		values.put(EnStructVaccin.IS_MALADIE_DE_CARRE.getNomChamp(), Boolean.toString(p_object.isMaladieDeCarre()));
		values.put(EnStructVaccin.IS_PARVOVIROSE.getNomChamp(), Boolean.toString(p_object.isParvovirose()));
		values.put(EnStructVaccin.IS_HEPATITE_DE_RUBARTH.getNomChamp(), Boolean.toString(p_object.isHepatiteDeRubarth()));
		values.put(EnStructVaccin.IS_LEPTOSPIROSE.getNomChamp(), Boolean.toString(p_object.isLeptospirose()));
		values.put(EnStructVaccin.IS_TOUX_DU_CHENIL.getNomChamp(), Boolean.toString(p_object.isTouxDuChenil()));
		values.put(EnStructVaccin.IS_PIROPLASMOSE.getNomChamp(), Boolean.toString(p_object.isPiroplasmose()));

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
			unVaccin.setVermifuge(Boolean.parseBoolean((String) unEnregistrement.get(EnStructVaccin.IS_VERMIFUGE.getindex())));

			unVaccin.setCorysa(Boolean.parseBoolean((String) unEnregistrement.get(EnStructVaccin.IS_CORYSA.getindex())));
			unVaccin.setTiphus(Boolean.parseBoolean((String) unEnregistrement.get(EnStructVaccin.IS_TYPHUS.getindex())));
			unVaccin.setLeucose(Boolean.parseBoolean((String) unEnregistrement.get(EnStructVaccin.IS_LEUCOSE.getindex())));
			unVaccin.setChlamydiose(Boolean.parseBoolean((String) unEnregistrement.get(EnStructVaccin.IS_CHLAMYDIOSE.getindex())));
			unVaccin.setRage(Boolean.parseBoolean((String) unEnregistrement.get(EnStructVaccin.IS_RAGE.getindex())));
			unVaccin.setMaladieDeCarre(Boolean.parseBoolean((String) unEnregistrement.get(EnStructVaccin.IS_MALADIE_DE_CARRE.getindex())));
			unVaccin.setParvovirose(Boolean.parseBoolean((String) unEnregistrement.get(EnStructVaccin.IS_PARVOVIROSE.getindex())));
			unVaccin.setHepatiteDeRubarth(Boolean.parseBoolean((String) unEnregistrement.get(EnStructVaccin.IS_HEPATITE_DE_RUBARTH
					.getindex())));
			unVaccin.setLeptospirose(Boolean.parseBoolean((String) unEnregistrement.get(EnStructVaccin.IS_LEPTOSPIROSE.getindex())));
			unVaccin.setTouxDuChenil(Boolean.parseBoolean((String) unEnregistrement.get(EnStructVaccin.IS_TOUX_DU_CHENIL.getindex())));
			unVaccin.setPiroplasmose(Boolean.parseBoolean((String) unEnregistrement.get(EnStructVaccin.IS_PIROPLASMOSE.getindex())));

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
