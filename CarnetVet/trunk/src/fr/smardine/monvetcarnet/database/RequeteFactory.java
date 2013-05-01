package fr.smardine.monvetcarnet.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import fr.smardine.monvetcarnet.database.structuretable.EnTable;
import fr.smardine.monvetcarnet.database.structuretable.IStructureTable;
import fr.smardine.monvetcarnet.database.structuretable.SuperStructureTable;
import fr.smardine.monvetcarnet.helper.ClassHelper;

/**
 * @author smardine S'occupe de gerer les requetes en bases.
 */
public class RequeteFactory {

	private final Context ctx;
	private final BDAcces bdAcces;

	/**
	 * @param p_ctx
	 */
	public RequeteFactory(Context p_ctx) {
		this.ctx = p_ctx;
		this.bdAcces = new BDAcces(ctx);
	}

	/**
	 * @return le chemin de la bdd
	 */
	public String getDatabasePath() {
		return bdAcces.getPath();
	}

	/**
	 * @param p_requete
	 * @return 1 champ la quete doit etre de type SELECT UNCHAMP FROM UNETABLE [where...]
	 */
	public String get1Champ(String p_requete) {
		String result = "";
		bdAcces.open();
		Cursor objCursor = bdAcces.getMdb().rawQuery(p_requete, null);
		if (objCursor.getCount() > 0) {
			objCursor.moveToFirst();
			result = objCursor.getString(0);
		}

		objCursor.close();
		bdAcces.close();
		return result;

	}

	/**
	 * @param p_table
	 * @return le nombre d'enregistrement(s) dans une table
	 */
	public int getNombreEnregistrement(fr.smardine.monvetcarnet.database.structuretable.EnTable p_table) {
		bdAcces.open();
		Cursor objCursor = bdAcces.getMdb().query(p_table.getNomTable(), null, null, null, null, null, null);
		int iNbChamp = objCursor.getCount();
		objCursor.close();
		bdAcces.close();
		return iNbChamp;
	}

	/**
	 * @param p_requete
	 * @return une liste de resultat
	 */
	public List<ArrayList<String>> getListeDeChamp(String p_requete) {
		ArrayList<ArrayList<String>> lstRetour = new ArrayList<ArrayList<String>>();
		bdAcces.open();
		Cursor c = bdAcces.getMdb().rawQuery(p_requete, null);
		while (c.moveToNext()) {
			ArrayList<String> lstIntermediaire = new ArrayList<String>();
			for (int i = 0; i < c.getColumnCount(); i++) {

				lstIntermediaire.add(c.getString(i));
			}
			lstRetour.add(lstIntermediaire);
		}
		c.close();
		bdAcces.close();
		return lstRetour;

	}

	/**
	 * Construit une requete a partir d'une classe implementant "IStructureTable"
	 * @param p_sctructureTable
	 * @return
	 */
	private String construitRequeteFromStructuretable(IStructureTable[] p_sctructureTable) {
		String requete = "Select ";
		for (int i = 0; i < p_sctructureTable.length; i++) {
			if (i == 0) {
				requete = requete + p_sctructureTable[i].getNomChamp();
			} else if (i > 0) {
				requete = requete + ", " + p_sctructureTable[i].getNomChamp();
			}
		}
		return requete;
	}

	/**
	 * Obtenir une liste de champs a partir d'un EnTable et d'une clause where sous forme de chaine
	 * @param p_table
	 * @param p_whereClause
	 * @return
	 */
	public List<ArrayList<Object>> getListeDeChampBis(EnTable p_table, String p_whereClause) {

		SuperStructureTable uneTable = (SuperStructureTable) ClassHelper
				.createInstanceClassFromClass(p_table.getStructureTable(), this.ctx);
		IStructureTable[] lstChamp = uneTable.getListeChamp();

		StringBuilder sb = new StringBuilder();
		sb.append(construitRequeteFromStructuretable(lstChamp));

		sb.append(" FROM " + p_table.getNomTable());
		if (p_whereClause != null && !p_whereClause.equals("")) {
			sb.append(" where " + p_whereClause);
		}
		ArrayList<ArrayList<Object>> lstRetour = new ArrayList<ArrayList<Object>>();
		bdAcces.open();
		Cursor c = bdAcces.getMdb().rawQuery(sb.toString(), null);
		while (c.moveToNext()) {
			ArrayList<Object> lstIntermediaire = new ArrayList<Object>();
			for (IStructureTable unChamp : lstChamp) {
				int idxColumn = c.getColumnIndex(unChamp.getNomChamp());
				switch (unChamp.getTypeChamp()) {
					case INTEGER:
						lstIntermediaire.add(c.getInt(idxColumn));
						break;
					case LONG:
						lstIntermediaire.add(c.getLong(idxColumn));
						break;
					case VARCHAR:
					case BOOL:
						lstIntermediaire.add(c.getString(idxColumn));
						break;
				}
			}
			lstRetour.add(lstIntermediaire);
		}
		c.close();
		bdAcces.close();
		return lstRetour;

	}

	/**
	 * @param p_table
	 * @param p_modifiedValue
	 * @param p_whereClause
	 * @param p_whereArgs
	 * @return le nombre d'enregistrement affect�
	 */
	public int majTable(EnTable p_table, ContentValues p_modifiedValue, String p_whereClause, String[] p_whereArgs) {
		bdAcces.open();
		int nb = bdAcces.getMdb().update(p_table.getNomTable(), p_modifiedValue, p_whereClause, p_whereArgs);
		bdAcces.close();
		return nb;
	}

	/**
	 * @param p_table
	 * @param p_whereClause
	 * @param p_whereArgs
	 * @return le nombre de ligne suprrim�e(s)
	 */
	public int deleteTable(EnTable p_table, String p_whereClause, String[] p_whereArgs) {
		bdAcces.open();
		int nb = bdAcces.getMdb().delete(p_table.getNomTable(), p_whereClause, p_whereArgs);
		bdAcces.close();
		return nb;
	}

	/**
	 * @param p_table
	 * @param p_values
	 * @return true ou false
	 */
	public boolean insertDansTable(EnTable p_table, ContentValues p_values) {
		bdAcces.open();
		long RowNumber = bdAcces.getMdb().insert(p_table.getNomTable(), null, p_values);
		if (RowNumber == -1) {
			bdAcces.close();
			return false;
		} else {
			bdAcces.close();
			return true;
		}

	}
}
