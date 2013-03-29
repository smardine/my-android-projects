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

public class AccesTablePoids extends AccesTable<MlPoids> {

	public AccesTablePoids(Context p_ctx) {
		super(p_ctx, EnTable.POIDS);

	}

	@Override
	protected ContentValues createContentValueForObject(MlPoids p_object) {
		ContentValues values = new ContentValues();
		values.put(EnStructPoids.DATE.getNomChamp(), p_object.getDate().getTime());
		values.put(EnStructPoids.ID_CARNET.getNomChamp(), p_object.getIdCarnetParent());
		values.put(EnStructPoids.ID_POID.getNomChamp(), p_object.getIdPoid());
		values.put(EnStructPoids.NOTE.getNomChamp(), p_object.getNote());
		values.put(EnStructPoids.UNITE.getNomChamp(), p_object.getUnitePoids().name());
		values.put(EnStructPoids.VALEUR.getNomChamp(), p_object.getValeur());

		return values;
	}

	public List<MlPoids> getListeDePoidsParIdCarnet(MlCarnet p_carnetParent) {
		List<ArrayList<Object>> listeDeChamp = requeteFact.getListeDeChampBis(EnTable.POIDS, EnStructPoids.class,
				EnStructPoids.ID_CARNET.toString() + "=" + p_carnetParent.getId());

		List<MlPoids> lstRetour = new ArrayList<MlPoids>();

		for (ArrayList<Object> unEnregistrement : listeDeChamp) {

			MlPoids unPoids = new MlPoids();
			unPoids.setDate(new Date((Long) unEnregistrement.get(EnStructPoids.DATE.getindex())));
			unPoids.setIdCarnetParent((Integer) unEnregistrement.get(EnStructPoids.ID_CARNET.getindex()));
			unPoids.setIdPoid((Integer) unEnregistrement.get(EnStructPoids.ID_POID.getindex()));
			unPoids.setNote((String) unEnregistrement.get(EnStructPoids.NOTE.getindex()));
			unPoids.setUnitePoids(EnUnitePoids.valueOf((String) unEnregistrement.get(EnStructPoids.UNITE.getindex())));
			unPoids.setValeur(Double.valueOf((String) unEnregistrement.get(EnStructPoids.VALEUR.getindex())));
			lstRetour.add(unPoids);
		}
		return lstRetour;
	}

	protected boolean insertPoidEnBase(MlPoids p_poid) {
		return super.insertObjectEnBase(p_poid);
	}

	protected boolean majPoidEnBase(MlPoids p_poid) {
		return super.majObjetEnBase(p_poid);
	}

	@Override
	public boolean deleteTable() {
		return super.deleteTable();
	}

}
