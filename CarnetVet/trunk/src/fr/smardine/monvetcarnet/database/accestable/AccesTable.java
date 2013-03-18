package fr.smardine.monvetcarnet.database.accestable;

import android.content.ContentValues;
import android.content.Context;
import fr.smardine.monvetcarnet.database.RequeteFactory;
import fr.smardine.monvetcarnet.database.structuretable.EnTable;
import fr.smardine.monvetcarnet.database.structuretable.IStructureTable;
import fr.smardine.monvetcarnet.mdl.IMetaModel;

public abstract class AccesTable<T> {

	private final RequeteFactory requeteFact;
	private final EnTable table;
	private final IStructureTable structureTable;

	public AccesTable(Context p_ctx, EnTable p_Table, IStructureTable p_structureTable) {
		this.table = p_Table;
		this.structureTable = p_structureTable;
		this.requeteFact = new RequeteFactory(p_ctx);
	}

	/**
	 * @return obtenir le nombre d'enregistrement dans la table
	 */
	public int getNbEnregistrement() {
		return requeteFact.getNombreEnregistrement(table);
	}

	public boolean deleteTable() {
		return requeteFact.deleteTable(table, "1", null) != 0;
	}

	protected abstract ContentValues createContentValueForObject(T p_object);

	protected void insertObjectEnBase(T p_object) {
		ContentValues content = createContentValueForObject(p_object);
		requeteFact.insertDansTable(table, content);
	}

	protected void majObjetEnBase(T p_object) {
		ContentValues modifiedValue = createContentValueForObject(p_object);
		String whereClause = structureTable.getListeChamp()[0].getNomChamp() + "=?"; // id de l'objet
		int idObject = ((IMetaModel) p_object).getId();
		String[] whereArgs = { "" + idObject };
		requeteFact.majTable(table, modifiedValue, whereClause, whereArgs);
	}
}
