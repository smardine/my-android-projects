package fr.smardine.podcaster.metier;

public class RssFeeder {

	public void Test() {

		final String feedUrl = "http://www.rtl.fr/podcast/laurent-gerra.xml";
		// "http://www.jeuxvideo.com/rss/itunes-le-cliq.xml";
		RSSReader reader = new RSSReader();
		reader.execute(feedUrl);

		// balise commune aux podcast video et audio:
		// "title" => le titre du flux
		// "link" => le liens du flux
		// "descricption" => la description du flux
		// "enclosure" => ?
		// "pubDate" => date de publication
		// "guid" => a prioris, renvoi vers l'url du podcast, dans le cas d'un
		// podcast audio, j'avais directement l'url du .mp3
		// "itunes:duration" => durée
		// "itunes:Author" => auteur du flux
		// "itunes:Explicit" => boolean oui ou non
		// "itunes:Keyword" => des mots clé pour les moteur de recherche, séparé
		// par des virgules
		// "itunes:subtitle" => ??
		// "itunes:summary" => résumé du flux
	}
}
