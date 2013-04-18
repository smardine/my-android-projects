package fr.smardine.monvetcarnet.database.accestable;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import fr.smardine.monvetcarnet.database.structuretable.EnStructCarnet;
import fr.smardine.monvetcarnet.database.structuretable.EnTable;
import fr.smardine.monvetcarnet.mdl.MlCarnet;

/**
 * Classe d'acces a la table Carnet
 * @author sims
 */
public class AccesTableCarnet extends AccesTable<MlCarnet> {

	private final AccesTableVaccin tableVaccin;
	private final AccesTablePoids tablePoids;
	private final AccesTableIdentification tableIdentification;
	private final AccesTableMaladie tableMaladie;

	/**
	 * Constructeur
	 * @param p_ctx
	 */
	public AccesTableCarnet(Context p_ctx) {
		super(p_ctx, EnTable.CARNETS);
		tableVaccin = new AccesTableVaccin(p_ctx);
		tablePoids = new AccesTablePoids(p_ctx);
		tableIdentification = new AccesTableIdentification(p_ctx);
		tableMaladie = new AccesTableMaladie(p_ctx);
	}

	/**
	 * methode permettant de creer un objet pour l'insertion en base a partir d'un MlCarnet
	 */
	@Override
	protected ContentValues createContentValueForObject(MlCarnet p_object) {
		ContentValues values = new ContentValues();
		// values.put(EnStructCarnet.ID_CARNET.getNomChamp(), p_object.getId());
		values.put(EnStructCarnet.NOM_CARNET.getNomChamp(), p_object.getNomCarnet());
		return values;
	}

	/**
	 * Obtenir la liste des carnets en base
	 * @return
	 */
	public List<MlCarnet> getListeDesCarnets() {
		List<MlCarnet> lstRetour = new ArrayList<MlCarnet>();

		List<ArrayList<Object>> listeDeChamp = requeteFact.getListeDeChampBis(EnTable.CARNETS, null);

		for (ArrayList<Object> unEnregistrement : listeDeChamp) {
			MlCarnet unCarnet = new MlCarnet();
			unCarnet.setIdCarnet((Integer) unEnregistrement.get(EnStructCarnet.ID_CARNET.getindex()));
			unCarnet.setNomCarnet((String) unEnregistrement.get(EnStructCarnet.NOM_CARNET.getindex()));
			unCarnet.setIdentificationAnimal(tableIdentification.getIdentificationParIdCarnet(unCarnet));
			unCarnet.setListeDeMaladies(tableMaladie.getListeDeMaladiesParIdCarnet(unCarnet));
			unCarnet.setListeDeVaccins(tableVaccin.getListeDeVaccinsParIdCarnet(unCarnet));
			unCarnet.setListeDePoids(tablePoids.getListeDePoidsParIdCarnet(unCarnet));

			lstRetour.add(unCarnet);
		}
		return lstRetour;

	}

	/**
	 * Inserer un MlCarnet en base
	 * @param p_carnet
	 * @return
	 */
	public boolean insertCarnetEnBase(MlCarnet p_carnet) {
		boolean result = super.insertObjectEnBase(p_carnet);
		if (result) {
			p_carnet.setIdCarnet(Integer.parseInt(requeteFact.get1Champ("SELECT MAX (" + EnStructCarnet.ID_CARNET.toString() + ") FROM "
					+ EnTable.CARNETS.toString())));
		}
		return result;
	}

	/**
	 * Mettre a jour un MlCarnet en base
	 * @param p_carnet
	 * @return
	 */
	public boolean majCarnetEnBase(MlCarnet p_carnet) {
		return super.majObjetEnBase(p_carnet);
	}

	/**
	 * Effacer le contenu de la table
	 */
	@Override
	public boolean deleteTable() {
		return super.deleteTable();
	}

}
