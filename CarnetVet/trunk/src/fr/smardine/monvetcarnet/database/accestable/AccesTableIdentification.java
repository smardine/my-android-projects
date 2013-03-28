package fr.smardine.monvetcarnet.database.accestable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import fr.smardine.monvetcarnet.database.structuretable.EnStructIdentification;
import fr.smardine.monvetcarnet.database.structuretable.EnTable;
import fr.smardine.monvetcarnet.mdl.EnGenre;
import fr.smardine.monvetcarnet.mdl.EnTypeAnimal;
import fr.smardine.monvetcarnet.mdl.MlCarnet;
import fr.smardine.monvetcarnet.mdl.MlIdentification;

public class AccesTableIdentification extends AccesTable<MlIdentification> {

	private final AccesTableDetail tableDetail;

	public AccesTableIdentification(Context p_ctx) {
		super(p_ctx, EnTable.IDENTIFICATIONS);
		tableDetail = new AccesTableDetail(p_ctx);
	}

	@Override
	protected ContentValues createContentValueForObject(MlIdentification p_object) {
		ContentValues values = new ContentValues();
		values.put(EnStructIdentification.ID_CARNET.toString(), p_object.getIdCarnetParent());
		values.put(EnStructIdentification.ID_IDENTIFICATION.toString(), p_object.getIdIdentification());
		values.put(EnStructIdentification.DATE_NAISSANCE.toString(), p_object.getDateNaissance().getTime());
		values.put(EnStructIdentification.GENRE.toString(), p_object.getGenreAnimal().getType());
		values.put(EnStructIdentification.NOM.toString(), p_object.getNomAnimal());
		values.put(EnStructIdentification.TYPE_ANIMAL.toString(), p_object.getTypeAnimal().getType());
		return values;
	}

	public MlIdentification getIdentificationParIdCarnet(MlCarnet p_carnetParent) {
		List<ArrayList<Object>> listeDeChamp = requeteFact.getListeDeChampBis(EnTable.IDENTIFICATIONS, EnStructIdentification.class,
				EnStructIdentification.ID_CARNET.toString() + "=" + p_carnetParent.getId());

		MlIdentification uneIdentification = new MlIdentification(p_carnetParent);

		for (ArrayList<Object> unEnregistrement : listeDeChamp) {

			uneIdentification.setDateNaissance(new Date((Long) unEnregistrement.get(EnStructIdentification.DATE_NAISSANCE.getindex())));

			uneIdentification.setGenreAnimal(EnGenre.valueOf((String) unEnregistrement.get(EnStructIdentification.GENRE.getindex())));
			uneIdentification.setIdCarnetParent((Integer) unEnregistrement.get(EnStructIdentification.ID_CARNET.getindex()));
			uneIdentification.setIdIdentification((Integer) unEnregistrement.get(EnStructIdentification.ID_IDENTIFICATION.getindex()));
			uneIdentification.setNomAnimal((String) unEnregistrement.get(EnStructIdentification.NOM.getindex()));
			uneIdentification.setTypeAnimal(EnTypeAnimal.valueOf((String) unEnregistrement.get(EnStructIdentification.TYPE_ANIMAL
					.getindex())));
			uneIdentification.setDetail(tableDetail.getDetailParIdIdentification(uneIdentification));

		}
		return uneIdentification;

	}

	public boolean insertIdentificationEnBase(MlIdentification p_identification) {
		boolean result = super.insertObjectEnBase(p_identification);
		if (result) {
			p_identification.setIdIdentification(Integer.parseInt(requeteFact.get1Champ("SELECT MAX ("
					+ EnStructIdentification.ID_IDENTIFICATION.toString() + ") FROM " + EnTable.IDENTIFICATIONS.toString())));
		}
		return result;
	}

	public boolean majCarnetEnBase(MlIdentification p_identification) {
		return super.majObjetEnBase(p_identification);
	}

	@Override
	public boolean deleteTable() {
		return super.deleteTable();
	}

}
