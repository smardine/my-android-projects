package fr.smardine.monvetcarnet.database.structuretable;

public class EnStructMaladie extends SuperStructureTable {

	public static EnStructMaladie ID_MALADIE = new EnStructMaladie("ID_MALADIE", EnTypeChampsSQLite.INTEGER, null, 0); //

	public static EnStructMaladie ID_CARNET_PARENT = new EnStructMaladie("ID_CARNET", EnTypeChampsSQLite.INTEGER, null, 1); //

	public static EnStructMaladie DATE = new EnStructMaladie("DATE", EnTypeChampsSQLite.LONG, null, 2);//

	public static EnStructMaladie TITRE = new EnStructMaladie("VALEUR", EnTypeChampsSQLite.VARCHAR, null, 3); //

	public static EnStructMaladie SYMPTOME = new EnStructMaladie("SYMPTOME", EnTypeChampsSQLite.VARCHAR, null, 4); //

	public static EnStructMaladie RDV_VETO = new EnStructMaladie("RDV_VETO", EnTypeChampsSQLite.VARCHAR, 1, 5); //

	public static EnStructMaladie TRAITEMENT = new EnStructMaladie("TRAITEMENT", EnTypeChampsSQLite.VARCHAR, null, 6);

	EnStructMaladie(String p_nomChamp, EnTypeChampsSQLite p_typeClass, Integer p_tailleMax, int p_index) {
		super(p_nomChamp, p_typeClass, p_tailleMax, p_index);
	}

	@Override
	public IStructureTable[] getListeChamp() {
		return new IStructureTable[] { ID_MALADIE, ID_CARNET_PARENT, DATE, TITRE, SYMPTOME, RDV_VETO, TRAITEMENT };
	}

	@Override
	public SuperStructureTable getKeyChamp() {
		return ID_MALADIE;
	}

	public EnStructMaladie() {
		super("Toto", EnTypeChampsSQLite.INTEGER, null, -1);
	}
}