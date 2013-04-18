package fr.smardine.monvetcarnet.database.structuretable;

/**
 * Classe abstraite représentant la structure d'une table de la base
 * @author sims
 */
public abstract class SuperStructureTable implements IStructureTable {

	private final String nomChamp;
	private final EnTypeChampsSQLite typeClass;
	private final Integer tailleMax;
	private final int index;

	/**
	 * Constructeur
	 * @param p_nomChamp
	 * @param p_typeClass
	 * @param p_tailleMax
	 * @param p_index
	 */
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

	/**
	 * Obtenir le champ "ClePrimaire" de la table
	 */
	public abstract SuperStructureTable getKeyChamp();

	/**
	 * Obtenir la liste des champs de la table
	 */
	public abstract IStructureTable[] getListeChamp();

}
