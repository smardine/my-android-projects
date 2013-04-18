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
import fr.smardine.monvetcarnet.mdl.MlDetail;
import fr.smardine.monvetcarnet.mdl.MlIdentification;

/**
 * Classe d'acces a la table Identification
 * @author sims
 */
public class AccesTableIdentification extends AccesTable<MlIdentification> {

	private final AccesTableDetail tableDetail;

	/**
	 * Constructeur
	 * @param p_ctx
	 */
	public AccesTableIdentification(Context p_ctx) {
		super(p_ctx, EnTable.IDENTIFICATIONS);
		tableDetail = new AccesTableDetail(p_ctx);
	}

	/**
	 * methode permettant de creer un objet pour l'insertion en base a partir d'un MlIdentification
	 */
	@Override
	protected ContentValues createContentValueForObject(MlIdentification p_object) {
		ContentValues values = new ContentValues();
		values.put(EnStructIdentification.ID_CARNET_PARENT.getNomChamp(), p_object.getCarnetParent().getId());
		// values.put(EnStructIdentification.ID_IDENTIFICATION.getNomChamp(), p_object.getIdIdentification());
		if (p_object.getDateNaissance() != null) {
			values.put(EnStructIdentification.DATE_NAISSANCE.getNomChamp(), p_object.getDateNaissance().getTime());
		}
		if (p_object.getGenreAnimal() != null && p_object.getGenreAnimal().getType() != null) {
			values.put(EnStructIdentification.GENRE.getNomChamp(), p_object.getGenreAnimal().getType());
		}

		values.put(EnStructIdentification.NOM.getNomChamp(), p_object.getNomAnimal());
		values.put(EnStructIdentification.TYPE_ANIMAL.getNomChamp(), p_object.getTypeAnimal().getType());
		return values;
	}

	/**
	 * Obtenir un MlIdentification a partir d'un MlCarnet
	 * @param p_carnetParent
	 * @return
	 */
	public MlIdentification getIdentificationParIdCarnet(MlCarnet p_carnetParent) {
		List<ArrayList<Object>> listeDeChamp = requeteFact.getListeDeChampBis(EnTable.IDENTIFICATIONS,
				EnStructIdentification.ID_CARNET_PARENT.toString() + "=" + p_carnetParent.getId());

		MlIdentification uneIdentification = new MlIdentification(p_carnetParent);

		for (ArrayList<Object> unEnregistrement : listeDeChamp) {

			uneIdentification.setDateNaissance(new Date((Long) unEnregistrement.get(EnStructIdentification.DATE_NAISSANCE.getindex())));

			uneIdentification
					.setGenreAnimal(EnGenre.getEnumFromName((String) unEnregistrement.get(EnStructIdentification.GENRE.getindex())));
			// uneIdentification.setIdCarnetParent((Integer) unEnregistrement.get(EnStructIdentification.ID_CARNET.getindex()));
			uneIdentification.setIdIdentification((Integer) unEnregistrement.get(EnStructIdentification.ID_IDENTIFICATION.getindex()));
			uneIdentification.setNomAnimal((String) unEnregistrement.get(EnStructIdentification.NOM.getindex()));
			uneIdentification.setTypeAnimal(EnTypeAnimal.getEnumFromName((String) unEnregistrement.get(EnStructIdentification.TYPE_ANIMAL
					.getindex())));
			MlDetail unDetail = tableDetail.getDetailParIdIdentification(uneIdentification);
			if (unDetail == null) {
				// si le detail remonté est null, il n'a jamais été créé, on le creer et on linsert en base
				unDetail = new MlDetail(uneIdentification);
				tableDetail.insertDetailEnBase(unDetail);
				uneIdentification.setDetail(unDetail);
			} else {
				uneIdentification.setDetail(unDetail);
			}

		}
		return uneIdentification;

	}

	/**
	 * Inserer un MlIdentification en base
	 * @param p_identification
	 * @return
	 */
	public boolean insertIdentificationEnBase(MlIdentification p_identification) {
		boolean result = super.insertObjectEnBase(p_identification);
		if (result) {
			p_identification.setIdIdentification(Integer.parseInt(requeteFact.get1Champ("SELECT MAX ("
					+ EnStructIdentification.ID_IDENTIFICATION.toString() + ") FROM " + EnTable.IDENTIFICATIONS.toString())));
		}
		return result;
	}

	/**
	 * Mettre a jour un MlIdentification en base
	 * @param p_identification
	 * @return
	 */
	public boolean majIdenificationEnBase(MlIdentification p_identification) {
		return tableDetail.majDetailEnBase(p_identification.getDetail()) && super.majObjetEnBase(p_identification);
	}

	/**
	 * Effacer la table
	 */
	@Override
	public boolean deleteTable() {
		return super.deleteTable();
	}

}
