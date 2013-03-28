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

public class AccesTableMaladie extends AccesTable<MlMaladie> {

	public AccesTableMaladie(Context p_ctx) {
		super(p_ctx, EnTable.MALADIES);
	}

	@Override
	protected ContentValues createContentValueForObject(MlMaladie p_object) {
		ContentValues values = new ContentValues();
		values.put(EnStructMaladie.DATE.toString(), p_object.getDate().getTime());
		values.put(EnStructMaladie.ID_CARNET.toString(), p_object.getIdCarnetParent());
		values.put(EnStructMaladie.ID_MALADIE.toString(), p_object.getIdMaladie());
		values.put(EnStructMaladie.RDV_VETO.toString(), Boolean.toString(p_object.isRdvVeto()));
		values.put(EnStructMaladie.SYMPTOME.toString(), p_object.getSymptomes());
		values.put(EnStructMaladie.TITRE.toString(), p_object.getTitre());
		values.put(EnStructMaladie.TRAITEMENT.toString(), p_object.getTraitement());
		return values;
	}

	public List<MlMaladie> getListeDeMaladiesParIdCarnet(MlCarnet p_carnetParent) {
		List<ArrayList<Object>> listeDeChamp = requeteFact.getListeDeChampBis(EnTable.MALADIES, EnStructMaladie.class,
				EnStructMaladie.ID_CARNET.toString() + "=" + p_carnetParent.getId());

		List<MlMaladie> lstRetour = new ArrayList<MlMaladie>();

		for (ArrayList<Object> unEnregistrement : listeDeChamp) {

			MlMaladie uneMaladie = new MlMaladie();
			uneMaladie.setDate(new Date((Long) unEnregistrement.get(EnStructMaladie.DATE.getindex())));
			uneMaladie.setIdCarnetParent((Integer) unEnregistrement.get(EnStructMaladie.ID_CARNET.getindex()));
			uneMaladie.setIdMaladie((Integer) unEnregistrement.get(EnStructMaladie.ID_MALADIE.getindex()));
			uneMaladie.setRdvVeto(Boolean.parseBoolean((String) unEnregistrement.get(EnStructMaladie.RDV_VETO.getindex())));
			uneMaladie.setSymptomes((String) unEnregistrement.get(EnStructMaladie.SYMPTOME.getindex()));
			uneMaladie.setTitre((String) unEnregistrement.get(EnStructMaladie.TITRE.getindex()));
			uneMaladie.setTraitement((String) unEnregistrement.get(EnStructMaladie.TRAITEMENT.getindex()));

			lstRetour.add(uneMaladie);
		}
		return lstRetour;
	}

	protected boolean insertMaladieEnBase(MlMaladie p_maladie) {
		return super.insertObjectEnBase(p_maladie);
	}

	protected boolean majMaladieEnBase(MlMaladie p_maladie) {
		return super.majObjetEnBase(p_maladie);
	}

	@Override
	public boolean deleteTable() {
		return super.deleteTable();
	}

}