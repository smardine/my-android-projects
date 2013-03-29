package fr.smardine.monvetcarnet.database.accestable;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import fr.smardine.monvetcarnet.database.structuretable.EnStructDetail;
import fr.smardine.monvetcarnet.database.structuretable.EnTable;
import fr.smardine.monvetcarnet.mdl.MlDetail;
import fr.smardine.monvetcarnet.mdl.MlIdentification;

public class AccesTableDetail extends AccesTable<MlDetail> {

	private final AccesTableProprietaire tableProprietaire;

	public AccesTableDetail(Context p_ctx) {
		super(p_ctx, EnTable.DETAILS);
		tableProprietaire = new AccesTableProprietaire(p_ctx);

	}

	@Override
	protected ContentValues createContentValueForObject(MlDetail p_object) {
		ContentValues values = new ContentValues();
		// values.put(EnStructDetail.ID_DETAIL.getNomChamp(), p_object.getId());
		values.put(EnStructDetail.ID_IDENTIFICATION.getNomChamp(), p_object.getIdIdentificationParent());
		values.put(EnStructDetail.NUM_PUCE.getNomChamp(), p_object.getNumPuce());
		values.put(EnStructDetail.NUM_TATOUAGE.getNomChamp(), p_object.getNumTatouage());
		values.put(EnStructDetail.RACE.getNomChamp(), p_object.getRace());
		values.put(EnStructDetail.ROBE.getNomChamp(), p_object.getRobe());
		values.put(EnStructDetail.ID_ELEVEUR.getNomChamp(), p_object.getIdEleveur());
		values.put(EnStructDetail.ID_PROP1.getNomChamp(), p_object.getIdProprietaire1());
		values.put(EnStructDetail.ID_PROP2.getNomChamp(), p_object.getIdProprietaire2());
		return values;
	}

	public MlDetail getDetailParIdIdentification(MlIdentification p_Identification) {
		List<ArrayList<Object>> listeDeChamp = requeteFact.getListeDeChampBis(EnTable.DETAILS, EnStructDetail.class,
				EnStructDetail.ID_IDENTIFICATION.toString() + "=" + p_Identification.getId());

		MlDetail unDetail = new MlDetail(p_Identification);
		for (ArrayList<Object> unEnregistrement : listeDeChamp) {

			unDetail.setIdEleveur((Integer) unEnregistrement.get(EnStructDetail.ID_ELEVEUR.getindex()));
			unDetail.setIdProprietaire1((Integer) unEnregistrement.get(EnStructDetail.ID_PROP1.getindex()));
			unDetail.setIdProprietaire2((Integer) unEnregistrement.get(EnStructDetail.ID_PROP2.getindex()));
			unDetail.setIdDetail((Integer) unEnregistrement.get(EnStructDetail.ID_DETAIL.getindex()));

			unDetail.setEleveur(tableProprietaire.getProprietaireParIdDetailEtIdProprietaire(unDetail.getIdDetail(),
					unDetail.getIdEleveur()));
			unDetail.setProprietaire1(tableProprietaire.getProprietaireParIdDetailEtIdProprietaire(unDetail.getIdDetail(),
					unDetail.getIdProprietaire1()));
			unDetail.setProprietaire2(tableProprietaire.getProprietaireParIdDetailEtIdProprietaire(unDetail.getIdDetail(),
					unDetail.getIdProprietaire2()));

			unDetail.setIdIdentificationParent(p_Identification.getId());
			unDetail.setNumPuce((String) unEnregistrement.get(EnStructDetail.NUM_PUCE.getindex()));
			unDetail.setNumTatouage((String) unEnregistrement.get(EnStructDetail.NUM_TATOUAGE.getindex()));

			unDetail.setRace((String) unEnregistrement.get(EnStructDetail.RACE.getindex()));
			unDetail.setRobe((String) unEnregistrement.get(EnStructDetail.ROBE.getindex()));

		}
		return unDetail;
	}

	protected boolean insertDetailEnBase(MlDetail p_detail) {
		return super.insertObjectEnBase(p_detail);
	}

	protected boolean majDetailEnBase(MlDetail p_detail) {
		return super.majObjetEnBase(p_detail);
	}

	@Override
	public boolean deleteTable() {
		return super.deleteTable();
	}
}
