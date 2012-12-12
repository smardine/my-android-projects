package fr.smardine.podcaster.metier;

public enum EnBaliseRSS {
	// balise commune aux podcast video et audio:
	// "title" => le titre du flux
	// "link" => le liens du flux
	// "descricption" => la description du flux
	// "enclosure" => ?
	// "pubDate" => date de publication
	// "guid" => a prioris, renvoi vers l'url du podcast, dans le cas d'un
	// podcast audio, j'avais directement l'url du .mp3
	// "itunes:duration" => dur�e
	// "itunes:Author" => auteur du flux
	// "itunes:Explicit" => boolean oui ou non
	// "itunes:Keyword" => des mots cl� pour les moteur de recherche, s�par�
	// par des virgules
	// "itunes:subtitle" => ??
	// "itunes:summary" => r�sum� du flux
	Item("item"), //

	// LastBuildDate("lastBuildDate"), //
	Title("title"), //

	Description("description"), //
	Channel("channel"), //
	// Enclosure("enclosure"), //
	PubDate("pubDate"), //
	Guid("guid"), //
	ItuneDuration("itunes:duration"), //
	Link("link"), //
	ItuneAuthor("itunes:Author"), //
	ItuneSummary("itunes:summary");
	private String name;

	EnBaliseRSS(String p_name) {
		this.name = p_name;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
