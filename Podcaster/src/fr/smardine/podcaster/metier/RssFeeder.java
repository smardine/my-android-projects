package fr.smardine.podcaster.metier;

import java.util.concurrent.ExecutionException;

import android.content.Context;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.helper.log.LogCatBuilder;
import fr.smardine.podcaster.mdl.MlFlux;
import fr.smardine.podcaster.mdl.MlListeFlux;

public class RssFeeder {
	private final Context context;
	private final String TAG = this.getClass().getSimpleName();

	public RssFeeder(Context p_context) {
		this.context = p_context;
	}

	public MlListeFlux Test() {

		final String[] feedUrl = new String[] {
				"http://www.rtl.fr/podcast/laurent-gerra.xml",
				"http://www.europe1.fr/podcasts/revue-de-presque.xml",
				"http://www.jeuxvideo.com/rss/itunes-le-cliq.xml",// };
				"http://rss.feedsportal.com/c/808/f/413811/index.rss",
				"http://rss.feedsportal.com/c/808/f/413810/index.rss",
				"http://radiofrance-podcast.net/podcast09/rss_11549.xml",

				"http://www.jeuxvideo.com/rss/itunes-le-cliq-hd.xml" };

		MlListeFlux listeRetour = new MlListeFlux();

		for (String s : feedUrl) {
			try {
				RSSReader reader = new RSSReader(context);
				reader.execute(s);
				MlFlux unFlux = reader.get();
				listeRetour.add(unFlux);
			} catch (InterruptedException e) {
				LogCatBuilder.WriteErrorToLog(context, TAG,
						R.string.interruption_de_la_tache, e);
			} catch (ExecutionException e) {
				LogCatBuilder.WriteErrorToLog(context, TAG,
						R.string.erreur_execution_tache, e);
			}

		}

		// RSSReaderPossible reader = RSSReaderPossible.getInstance();
		// reader.execute(new String[] { "Toto" });

		return listeRetour;

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

		// http://www.vogella.com/articles/RSSFeed/article.html
	}
}
