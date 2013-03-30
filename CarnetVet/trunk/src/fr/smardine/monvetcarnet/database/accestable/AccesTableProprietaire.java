package fr.smardine.monvetcarnet.database.accestable;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import fr.smardine.monvetcarnet.database.structuretable.EnStructProprietaire;
import fr.smardine.monvetcarnet.database.structuretable.EnTable;
import fr.smardine.monvetcarnet.mdl.MlProprietaire;

public class AccesTableProprietaire extends AccesTable<MlProprietaire> {

	public AccesTableProprietaire(Context p_ctx) {
		super(p_ctx, EnTable.PROPRIETAIRES);

	}

	@Override
	protected ContentValues createContentValueForObject(MlProprietaire p_object) {
		ContentValues values = new ContentValues();
		values.put(EnStructProprietaire.ADRESSE.getNomChamp(), p_object.getAdresse());
		values.put(EnStructProprietaire.ID_DETAIL.getNomChamp(), p_object.getIdDetailParent());
		values.put(EnStructProprietaire.ID_PROPRIETAIRE.getNomChamp(), p_object.getIdProprietaire());
		values.put(EnStructProprietaire.MAIL.getNomChamp(), p_object.getMail());
		values.put(EnStructProprietaire.NOM.getNomChamp(), p_object.getNom());
		values.put(EnStructProprietaire.TEL.getNomChamp(), p_object.getTelephone());
		return values;
	}

	public MlProprietaire getProprietaireParIdDetailEtIdProprietaire(int idDetailParent, int idEleveurRecherche) {
		List<ArrayList<Object>> listeDeChamp = requeteFact.getListeDeChampBis(EnTable.PROPRIETAIRES,

		EnStructProprietaire.ID_DETAIL.toString() + "=" + idDetailParent + " and " + EnStructProprietaire.ID_PROPRIETAIRE.toString() + "="
				+ idEleveurRecherche);

		MlProprietaire unProprietaire = new MlProprietaire();
		for (ArrayList<Object> unEnregistrement : listeDeChamp) {

			unProprietaire.setAdresse((String) unEnregistrement.get(EnStructProprietaire.ADRESSE.getindex()));
			unProprietaire.setIdDetailParent((Integer) unEnregistrement.get(EnStructProprietaire.ID_DETAIL.getindex()));
			unProprietaire.setIdProprietaire((Integer) unEnregistrement.get(EnStructProprietaire.ID_PROPRIETAIRE.getindex()));
			unProprietaire.setMail((String) unEnregistrement.get(EnStructProprietaire.MAIL.getindex()));
			unProprietaire.setNom((String) unEnregistrement.get(EnStructProprietaire.NOM.getindex()));
			unProprietaire.setTelephone((String) unEnregistrement.get(EnStructProprietaire.TEL.getindex()));

		}
		return unProprietaire;
	}

	protected boolean insertProprietaireEnBase(MlProprietaire p_proprietaire) {
		return super.insertObjectEnBase(p_proprietaire);
	}

	protected boolean majProprietaireEnBase(MlProprietaire p_proprietaire) {
		return super.majObjetEnBase(p_proprietaire);
	}

	@Override
	public boolean deleteTable() {
		return super.deleteTable();
	}
}
