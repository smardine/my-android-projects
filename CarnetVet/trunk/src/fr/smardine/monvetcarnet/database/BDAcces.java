package fr.smardine.monvetcarnet.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author smardine
 */
public class BDAcces {

	private static final String DATABASE_NAME = "carnetvet_base";
	private static final String TAG = "BDAcces";
	private static final int DATABASE_VERSION = 1;

	private final Context mCtx;

	/**
	 * 
	 */
	private DatabaseHelper mDbHelper;
	/**
	 * 
	 */
	private SQLiteDatabase mDb;

	/**
	 * 
	 */

	/**
	 * @param ctx
	 */
	protected BDAcces(Context ctx) {
		this.mCtx = ctx;
	}

	/**
	 * Classe helper d'acces a la bdd
	 * @author sims
	 */
	private class DatabaseHelper extends SQLiteOpenHelper {

		/**
		 * Constructeur
		 * @param context
		 */
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		/**
		 * Cette methode est lanc� par le systeme lors de la creation de la bdd
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {
			G_creation_base creation = new G_creation_base();
			for (String s : creation.getallCreation(mCtx)) {
				db.execSQL(s);
			}

			// G_maj_base maj = new G_maj_base();
			// for (String s : maj.getAllVersion()) {
			// db.execSQL(s);
			// }
		}

		/**
		 * Cette methode est lanc� par la systeme lors de la maj d'une bdd
		 */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
			// G_maj_base maj = new G_maj_base();
			// LanceMiseAJour(db, maj.getVersionx(oldVersion));

		}

		// private void LanceMiseAJour(SQLiteDatabase p_db,
		// List<String> p_lstScripts) {
		// for (String s : p_lstScripts) {
		// p_db.execSQL(s);
		// }
		// }
	}

	/**
	 * Ouverture de la bdd
	 */
	protected void open() {
		// si la base est deja ouverte, on la ferme.
		if (mDb != null && mDb.isOpen()) {
			close();
		}
		// ouverture du helper
		mDbHelper = new DatabaseHelper(mCtx);
		// ouverture de la base
		mDb = mDbHelper.getWritableDatabase();
	}

	/**
	 * @return le path de la database
	 */
	public String getPath() {
		if (mDb == null) {
			open();
		}
		return mDb.getPath();
	}

	/**
	 * ferme la connexion a la database
	 * @throws SQLException
	 */
	protected void close() {
		// si il reste des transaction active, on les ferme.
		if (mDb.inTransaction()) {
			mDb.endTransaction();
		}
		// on commence par fermer la base
		mDb.close();
		// on ferme le helper.
		mDbHelper.close();
	}

	/**
	 * 
	 */
	public void update_bdd() {
		int newversion = mDb.getVersion() + 1;
		mDbHelper.onUpgrade(mDb, mDb.getVersion(), newversion);

	}

	/**
	 * @return l'instance de connexion a la database
	 */
	protected SQLiteDatabase getMdb() {
		return mDb;
	}

	// **************************fin de la
	// classe**********************************
}
