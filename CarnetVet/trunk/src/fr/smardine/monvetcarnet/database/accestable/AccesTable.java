package fr.smardine.monvetcarnet.database.accestable;

import android.content.ContentValues;
import android.content.Context;
import fr.smardine.monvetcarnet.database.RequeteFactory;
import fr.smardine.monvetcarnet.database.structuretable.EnTable;
import fr.smardine.monvetcarnet.database.structuretable.SuperStructureTable;
import fr.smardine.monvetcarnet.helper.ClassHelper;
import fr.smardine.monvetcarnet.mdl.IMetaModel;

/**
 * Classe mere d'acces aux table de la base
 * @author sims
 * @param <T>
 */
public abstract class AccesTable<T> {

	protected final RequeteFactory requeteFact;
	private final EnTable table;
	private final Class<? extends SuperStructureTable> structureTable;
	private final Context ctx;

	/**
	 * Constructeur
	 * @param p_ctx
	 * @param p_table
	 */
	public AccesTable(Context p_ctx, EnTable p_table) {
		this.ctx = p_ctx;
		this.table = p_table;
		this.structureTable = table.getStructureTable();
		this.requeteFact = new RequeteFactory(p_ctx);
	}

	/**
	 * @return obtenir le nombre d'enregistrement dans la table
	 */
	public int getNbEnregistrement() {
		return requeteFact.getNombreEnregistrement(table);
	}

	/**
	 * permet d'effacer le contenu d'une table
	 * @return true si au moins une ligne a été supprimée.
	 */
	protected boolean deleteTable() {
		return requeteFact.deleteTable(table, "1", null) != 0;
	}

	/**
	 * Methode abstraite a redefinir dans les classes filles
	 * @param p_object l'objet qui sert a creer les values
	 * @return les values pretes a etre insérée en base
	 */
	protected abstract ContentValues createContentValueForObject(T p_object);

	/**
	 * Inserer un objet en base de type @link <T>
	 * @param p_object
	 * @return renvoi true ou false si l'insertion a réussie
	 */
	protected boolean insertObjectEnBase(T p_object) {
		ContentValues content = createContentValueForObject(p_object);
		return requeteFact.insertDansTable(table, content);
	}

	/**
	 * Met a jour un objet en base
	 * @param p_object - l'objet a mettre a jour en base, de type @link <T>
	 * @return true ou false si la maj a reussie (au moins une ligne modifiée)
	 */
	protected boolean majObjetEnBase(T p_object) {
		ContentValues modifiedValue = createContentValueForObject(p_object);
		SuperStructureTable unetable = (SuperStructureTable) ClassHelper.createInstanceClassFromClass(structureTable, ctx);
		String whereClause = unetable.getKeyChamp().getNomChamp() + "=?"; // id de l'objet
		int idObject = ((IMetaModel) p_object).getId();
		String[] whereArgs = { "" + idObject };
		return requeteFact.majTable(table, modifiedValue, whereClause, whereArgs) != 0;
	}
}
