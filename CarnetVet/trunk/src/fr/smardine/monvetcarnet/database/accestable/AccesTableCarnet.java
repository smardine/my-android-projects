package fr.smardine.monvetcarnet.database.accestable;

import android.content.ContentValues;
import android.content.Context;
import fr.smardine.monvetcarnet.database.structuretable.EnStructCarnet;
import fr.smardine.monvetcarnet.database.structuretable.EnTable;
import fr.smardine.monvetcarnet.mdl.MlCarnet;

public class AccesTableCarnet extends AccesTable<MlCarnet> {

	public AccesTableCarnet(Context p_ctx) {
		super(p_ctx, EnTable.CARNETS);
	}

	@Override
	public boolean deleteTable() {
		return super.deleteTable();
	}

	@Override
	protected ContentValues createContentValueForObject(MlCarnet p_object) {
		ContentValues values = new ContentValues();
		values.put(EnStructCarnet.ID_CARNET.toString(), p_object.getId());
		values.put(EnStructCarnet.NOM_CARNET.toString(), p_object.getNomCarnet());
		return values;
	}

	protected boolean insertCarnetEnBase(MlCarnet p_carnet) {
		return super.insertObjectEnBase(p_carnet);
	}

	protected boolean majCarnetEnBase(MlCarnet p_carnet) {
		return super.majObjetEnBase(p_carnet);
	}

}
