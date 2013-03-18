package fr.smardine.monvetcarnet.database.accestable;

import android.content.ContentValues;
import android.content.Context;
import fr.smardine.monvetcarnet.database.RequeteFactory;
import fr.smardine.monvetcarnet.database.structuretable.EnTable;

public abstract class AccesTable {

	private final RequeteFactory requeteFact;
	private final EnTable table;

	public AccesTable(Context p_ctx, EnTable p_Table) {
		this.table = p_Table;
		// this.ctx = p_ctx;
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

	public abstract ContentValues createContentValueForObject(Object p_object);
}
