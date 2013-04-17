package fr.smardine.monvetcarnet.mdl;

public class MlDetail implements IMetaModel {

	private int idDetail;
	private String race;
	private String robe;
	private String numTatouage;
	private String numPuce;
	private String signesDistinctifs;
	private MlProprietaire proprietaire1;
	private MlProprietaire proprietaire2;
	private MlProprietaire eleveur;
	private final MlIdentification identificationParent;
	private int idProprietaire1;
	private int idProprietaire2;
	private int idEleveur;

	public MlDetail(MlIdentification p_Identification) {
		this.identificationParent = p_Identification;
	}

	@Override
	public int getId() {
		return idDetail;
	}

	public int getIdDetail() {
		return idDetail;
	}

	public void setIdDetail(int idDetail) {
		this.idDetail = idDetail;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getRobe() {
		return robe;
	}

	public void setRobe(String robe) {
		this.robe = robe;
	}

	public String getNumTatouage() {
		return numTatouage;
	}

	public void setNumTatouage(String numTatouage) {
		this.numTatouage = numTatouage;
	}

	public String getNumPuce() {
		return numPuce;
	}

	public void setNumPuce(String numPuce) {
		this.numPuce = numPuce;
	}

	/**
	 * @return the signesDistinctifs
	 */
	public String getSignesDistinctifs() {
		return signesDistinctifs;
	}

	/**
	 * @param signesDistinctifs the signesDistinctifs to set
	 */
	public void setSignesDistinctifs(String signesDistinctifs) {
		this.signesDistinctifs = signesDistinctifs;
	}

	public MlProprietaire getProprietaire1() {
		return proprietaire1;
	}

	public void setProprietaire1(MlProprietaire proprietaire1) {
		this.proprietaire1 = proprietaire1;
	}

	public MlProprietaire getProprietaire2() {
		return proprietaire2;
	}

	public void setProprietaire2(MlProprietaire proprietaire2) {
		this.proprietaire2 = proprietaire2;
	}

	public MlProprietaire getEleveur() {
		return eleveur;
	}

	public void setEleveur(MlProprietaire eleveur) {
		this.eleveur = eleveur;
	}

	/**
	 * @return the identificationParent
	 */
	public MlIdentification getIdentificationParent() {
		return identificationParent;
	}

	public int getIdProprietaire1() {
		return idProprietaire1;
	}

	public void setIdProprietaire1(int idProprietaire1) {
		this.idProprietaire1 = idProprietaire1;
	}

	public int getIdProprietaire2() {
		return idProprietaire2;
	}

	public void setIdProprietaire2(int idProprietaire2) {
		this.idProprietaire2 = idProprietaire2;
	}

	public int getIdEleveur() {
		return idEleveur;
	}

	public void setIdEleveur(int idEleveur) {
		this.idEleveur = idEleveur;
	}

}
