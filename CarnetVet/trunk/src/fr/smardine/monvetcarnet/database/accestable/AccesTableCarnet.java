package fr.smardine.monvetcarnet.database.accestable;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import fr.smardine.monvetcarnet.database.structuretable.EnStructCarnet;
import fr.smardine.monvetcarnet.database.structuretable.EnTable;
import fr.smardine.monvetcarnet.mdl.MlCarnet;

public class AccesTableCarnet extends AccesTable<MlCarnet> {

	private final AccesTableVaccin tableVaccin;
	private final AccesTablePoids tablePoids;

	public AccesTableCarnet(Context p_ctx) {
		super(p_ctx, EnTable.CARNETS);
		tableVaccin = new AccesTableVaccin(p_ctx);
		tablePoids = new AccesTablePoids(p_ctx);
	}

	@Override
	protected ContentValues createContentValueForObject(MlCarnet p_object) {
		ContentValues values = new ContentValues();
		values.put(EnStructCarnet.ID_CARNET.toString(), p_object.getId());
		values.put(EnStructCarnet.NOM_CARNET.toString(), p_object.getNomCarnet());
		return values;
	}

	public List<MlCarnet> getListeDesCarnets() {
		List<MlCarnet> lstRetour = new ArrayList<MlCarnet>();

		List<ArrayList<Object>> listeDeChamp = requeteFact.getListeDeChampBis(EnTable.CARNETS, EnStructCarnet.class, null);

		for (ArrayList<Object> unEnregistrement : listeDeChamp) {
			MlCarnet unCarnet = new MlCarnet();
			unCarnet.setIdCarnet((Integer) unEnregistrement.get(EnStructCarnet.ID_CARNET.getindex()));
			unCarnet.setNomCarnet((String) unEnregistrement.get(EnStructCarnet.NOM_CARNET.getindex()));
			unCarnet.setListeDeVaccins(tableVaccin.getListeDeVaccinsParIdCarnet(unCarnet));
			unCarnet.setListeDePoids(tablePoids.getListeDePoidsParIdCarnet(unCarnet));

			lstRetour.add(unCarnet);
		}
		return lstRetour;

	}

	protected boolean insertCarnetEnBase(MlCarnet p_carnet) {
		return super.insertObjectEnBase(p_carnet);
	}

	protected boolean majCarnetEnBase(MlCarnet p_carnet) {
		return super.majObjetEnBase(p_carnet);
	}

	@Override
	public boolean deleteTable() {
		return super.deleteTable();
	}

}
