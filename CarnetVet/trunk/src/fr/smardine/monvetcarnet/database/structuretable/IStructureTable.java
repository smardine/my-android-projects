package fr.smardine.monvetcarnet.database.structuretable;

/**
 * @author smardine
 */
public interface IStructureTable {

	/**
	 * @return le nom du champ
	 */
	String getNomChamp();

	/**
	 * @return le type de champ
	 */
	EnTypeChampsSQLite getTypeChamp();

	/**
	 * @return la taille max d'un champ
	 */
	Integer getTailleMax();

	int getindex();

	// IStructureTable[] getListeChamp();

	@Override
	String toString();

}