package fr.smardine.podcaster.mdl;

public enum EnStatutLecture {

	Lu("Lu"), NonLu("NonLu");
	
	private String text;
	EnStatutLecture(String p_text){
		this.text=p_text;
	}
	
	public String toStrin(){
		return this.text;
	}
	
	/**
	 * Retourver le type d'episode a partir de la valeur remonté dans le tag
	 * @param p_name
	 * @return
	 */
	public static EnStatutLecture GetStatutLectureByName(String p_name) {
		for (EnStatutLecture unType : EnStatutLecture.values()) {
			if (unType.text.equals(p_name)) {
				return unType;
			}
		}
		// si ce n'est ni audio, ni video, ce sera du text, html ou non
		return null;
	}
}
