package fr.smardine.monvetcarnet.mdl;

public class MlCarnet implements IMetaModel {

	private int idCarnet;
	private String nomCarnet;

	public int getIdCarnet() {
		return idCarnet;
	}

	public void setIdCarnet(int idCarnet) {
		this.idCarnet = idCarnet;
	}

	public String getNomCarnet() {
		return nomCarnet;
	}

	public void setNomCarnet(String nomCarnet) {
		this.nomCarnet = nomCarnet;
	}

	@Override
	public int getId() {
		return getIdCarnet();
	}

}
