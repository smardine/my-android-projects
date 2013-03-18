package fr.smardine.monvetcarnet.database.accestable;

import android.content.ContentValues;
import android.content.Context;
import fr.smardine.monvetcarnet.database.structuretable.EnTable;
import fr.smardine.monvetcarnet.database.structuretable.IStructureTable;
import fr.smardine.monvetcarnet.mdl.MlCarnet;

public class AccesTableCarnet extends AccesTable<MlCarnet> {

	public AccesTableCarnet(Context p_ctx, EnTable p_Table, IStructureTable p_structureTable) {
		super(p_ctx, p_Table, p_structureTable);
	}

	@Override
	public boolean deleteTable() {
		return super.deleteTable();
	}

	@Override
	protected ContentValues createContentValueForObject(MlCarnet p_object) {
		// TODO Auto-generated method stub
		return null;
	}

	protected void insertCarnetEnBase(MlCarnet p_carnet) {
		super.insertObjectEnBase(p_carnet);
	}

	protected void majCarnetEnBase(MlCarnet p_carnet) {
		super.majObjetEnBase(p_carnet);
	}

}
