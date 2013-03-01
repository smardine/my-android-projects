package fr.smardine.podcaster.thread;

public enum EnThreadExecResult {
	CHANGE_TITRE(-1, "en_threadexecresult_changetitre"), //
	ENCOURS(0, "en_threadexecresult_indefini"), //
	SUCCESS(1, "en_threadexecresult_ok"), //
	ERROR(2, "en_threadexecresult_error"), //
	STOP(3, "en_threadexecresult_stopit"), //
	SUCCESS_BUTEMPTY(4, "enthreadexecresult_okbutempty"), //
	INDEFINI(Integer.MAX_VALUE, ""); //

	private int code;
	private String resStringName;

	EnThreadExecResult(int p_code, String p_resStringName) {
		this.code = p_code;
		this.resStringName = p_resStringName;
	}

	/**
	 * Renvoie le EnSyncReqResult correspondant au code passé en paramètre
	 * @param p_code
	 * @return null si p_code pas trouvé
	 */
	public static EnThreadExecResult fromCode(int p_code) {
		for (EnThreadExecResult v : EnThreadExecResult.values()) {
			if (p_code == v.code) {
				return v;
			}
		}
		return INDEFINI;
	}

	public int getCode() {
		return code;
	}

	/**
	 * exemple de mise en oeuvre int resID = this.getResources().getIdentifier(EnSynchroType .PLANTRA.getResStringName(),
	 * StringHelper.RES_STRING, this.getPackageName()); String essai = this.getResources().getText(resID).toString();
	 * @return le 'name' de la ressource chaine déclarée dans string.xml
	 */
	public String getResStringName() {
		return resStringName;
	}
}
