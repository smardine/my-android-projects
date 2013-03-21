package fr.smardine.monvetcarnet.mdl;

import java.util.Date;

public class MlVaccin implements IMetaModel {

	private int idCarnetParent;
	private int idVaccin;
	private Date date;
	private EnNomVaccin nomVaccin;
	private boolean vermifuge;

	@Override
	public int getId() {
		return idVaccin;
	}

	public int getIdCarnetParent() {
		return idCarnetParent;
	}

	public void setIdCarnetParent(int idCarnetParent) {
		this.idCarnetParent = idCarnetParent;
	}

	public int getIdVaccin() {
		return idVaccin;
	}

	public void setIdVaccin(int idVaccin) {
		this.idVaccin = idVaccin;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public EnNomVaccin getNomVaccin() {
		return nomVaccin;
	}

	public void setNomVaccin(EnNomVaccin nomVaccin) {
		this.nomVaccin = nomVaccin;
	}

	public boolean isVermifuge() {
		return vermifuge;
	}

	public void setVermifuge(boolean vermifuge) {
		this.vermifuge = vermifuge;
	}

}
