package fr.smardine.monvetcarnet.mdl;

import java.util.Date;

public class MlIdentification implements IMetaModel {

	private int idIdentification;
	private int idCarnetParent;
	private String nomAnimal;
	private Date dateNaissance;
	private double ageCalcule;
	private EnTypeAnimal typeAnimal;
	private EnGenre genreAnimal;
	private MlDetail detail;

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
