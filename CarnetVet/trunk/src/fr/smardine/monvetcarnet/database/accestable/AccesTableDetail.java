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
		values.put(EnStructDetail.ID_IDENTIFICATION_PARENT.getNomChamp(), p_object.getIdentificationParent().getId());
		values.put(EnStructDetail.NUM_PUCE.getNomChamp(), p_object.getNumPuce());
		values.put(EnStructDetail.SIGNES_DISTINCTIFS.getNomChamp(), p_object.getSignesDistinctifs());
		values.put(EnStructDetail.NUM_TATOUAGE.getNomChamp(), p_object.getNumTatouage());
		values.put(EnStructDetail.RACE.getNomChamp(), p_object.getRace());
		values.put(EnStructDetail.ROBE.getNomChamp(), p_object.getRobe());
		values.put(EnStructDetail.ID_ELEVEUR.getNomChamp(), p_object.getIdEleveur());
		values.put(EnStructDetail.ID_PROP1.getNomChamp(), p_object.getIdProprietaire1());
		values.put(EnStructDetail.ID_PROP2.getNomChamp(), p_object.getIdProprietaire2());
		return values;
	}

	public MlDetail getDetailParIdIdentification(MlIdentification p_Identification) {
		List<ArrayList<Object>> listeDeChamp = requeteFact.getListeDeChampBis(EnTable.DETAILS,
				EnStructDetail.ID_IDENTIFICATION_PARENT.toString() + "=" + p_Identification.getId());

		MlDetail unDetail = null;
		for (ArrayList<Object> unEnregistrement : listeDeChamp) {
			unDetail = new MlDetail(p_Identification);
			unDetail.setIdEleveur((Integer) unEnregistrement.get(EnStructDetail.ID_ELEVEUR.getindex()));
			unDetail.setIdProprietaire1((Integer) unEnregistrement.get(EnStructDetail.ID_PROP1.getindex()));
			unDetail.setIdProprietaire2((Integer) unEnregistrement.get(EnStructDetail.ID_PROP2.getindex()));
			unDetail.setIdDetail((Integer) unEnregistrement.get(EnStructDetail.ID_DETAIL.getindex()));

			unDetail.setEleveur(tableProprietaire.getProprietaireParIdDetailEtIdProprietaire(unDetail, unDetail.getIdEleveur()));
			unDetail.setProprietaire1(tableProprietaire.getProprietaireParIdDetailEtIdProprietaire(unDetail, unDetail.getIdProprietaire1()));
			unDetail.setProprietaire2(tableProprietaire.getProprietaireParIdDetailEtIdProprietaire(unDetail, unDetail.getIdProprietaire2()));

			// unDetail.setIdentificationParent(p_Identification);//setIdIdentificationParent(p_Identification.getId());
			unDetail.setNumPuce((String) unEnregistrement.get(EnStructDetail.NUM_PUCE.getindex()));
			unDetail.setNumTatouage((String) unEnregistrement.get(EnStructDetail.NUM_TATOUAGE.getindex()));
			unDetail.setSignesDistinctifs((String) unEnregistrement.get(EnStructDetail.SIGNES_DISTINCTIFS.getindex()));

			unDetail.setRace((String) unEnregistrement.get(EnStructDetail.RACE.getindex()));
			unDetail.setRobe((String) unEnregistrement.get(EnStructDetail.ROBE.getindex()));

		}
		return unDetail;
	}

	protected boolean insertDetailEnBase(MlDetail p_detail) {
		boolean result = super.insertObjectEnBase(p_detail);
		if (result) {
			p_detail.setIdDetail(Integer.parseInt(requeteFact.get1Champ("SELECT MAX (" + EnStructDetail.ID_DETAIL.toString() + ") FROM "
					+ EnTable.DETAILS.toString())));
		}
		return result;
	}

	protected boolean majDetailEnBase(MlDetail p_detail) {
		return super.majObjetEnBase(p_detail);
	}

	@Override
	public boolean deleteTable() {
		return super.deleteTable();
	}
}
