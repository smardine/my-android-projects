package fr.smardine.monvetcarnet.database.structuretable;

public abstract class SuperStructureTable implements IStructureTable {

	private final String nomChamp;
	private final EnTypeChampsSQLite typeClass;
	private final Integer tailleMax;
	private final int index;

	SuperStructureTable(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax, int p_index) {
		this.nomChamp = p_nomChamp;
		this.typeClass = p_typeClass;
		this.tailleMax = p_tailleMax;
		this.index = p_index;
	}

	@Override
	public String getNomChamp() {
		return nomChamp;
	}

	@Override
	public EnTypeChampsSQLite getTypeChamp() {
		return typeClass;
	}

	@Override
	public Integer getTailleMax() {
		return tailleMax;
	}

	@Override
	public int getindex() {
		return index;
	}

	@Override
	public String toString() {
		return nomChamp;
	}

	public abstract SuperStructureTable getKeyChamp();

	public abstract IStructureTable[] getListeChamp();

}
