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

public class AccesTableVaccin extends AccesTable<MlVaccin> {

	public AccesTableVaccin(Context p_ctx) {
		super(p_ctx, EnTable.VACCINS);

	}

	@Override
	protected ContentValues createContentValueForObject(MlVaccin p_object) {
		ContentValues values = new ContentValues();
		values.put(EnStructVaccin.DATE.toString(), p_object.getDate().getTime());
		values.put(EnStructVaccin.ID_CARNET.toString(), p_object.getIdCarnetParent());
		values.put(EnStructVaccin.ID_VACCIN.toString(), p_object.getIdVaccin());
		values.put(EnStructVaccin.NOM.toString(), p_object.getNomVaccin().name());
		values.put(EnStructVaccin.VERMIFUGE.toString(), Boolean.toString(p_object.isVermifuge()));
		return values;
	}

	public List<MlVaccin> getListeDeVaccinsParIdCarnet(MlCarnet p_carnetParent) {
		List<ArrayList<Object>> listeDeChamp = requeteFact.getListeDeChampBis(EnTable.VACCINS, EnStructVaccin.class,
				EnStructVaccin.ID_CARNET.toString() + "=" + p_carnetParent.getId());

		List<MlVaccin> lstRetour = new ArrayList<MlVaccin>();

		for (ArrayList<Object> unEnregistrement : listeDeChamp) {

			MlVaccin unVaccin = new MlVaccin();
			unVaccin.setDate(new Date((Long) unEnregistrement.get(EnStructVaccin.DATE.getindex())));
			unVaccin.setIdCarnetParent((Integer) unEnregistrement.get(EnStructVaccin.ID_CARNET.getindex()));
			unVaccin.setIdVaccin((Integer) unEnregistrement.get(EnStructVaccin.ID_VACCIN.getindex()));
			unVaccin.setNomVaccin(EnNomVaccin.valueOf((String) unEnregistrement.get(EnStructVaccin.NOM.getindex())));
			unVaccin.setVermifuge(Boolean.parseBoolean((String) unEnregistrement.get(EnStructVaccin.VERMIFUGE.getindex())));
			lstRetour.add(unVaccin);
		}
		return lstRetour;
	}

	protected boolean insertVaccinEnBase(MlVaccin p_vaccin) {
		return super.insertObjectEnBase(p_vaccin);
	}

	protected boolean majVaccinEnBase(MlVaccin p_vaccin) {
		return super.majObjetEnBase(p_vaccin);
	}

	@Override
	public boolean deleteTable() {
		return super.deleteTable();
	}

}
