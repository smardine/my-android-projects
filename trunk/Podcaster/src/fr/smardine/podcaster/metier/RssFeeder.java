package fr.smardine.podcaster.metier;

public class RssFeeder {

	public void Test() {

		final String feedUrl = "http://fusion.google.com/add?feedurl=http://www.jeuxvideo.com/rss/itunes-le-cliq.xml";
		RSSReader reader = new RSSReader();
		reader.execute(feedUrl);
		// new RetreiveFeedTask().execute(feedUrl);
		// RssAtomFeedRetriever feedRetriever = new RssAtomFeedRetriever();
		// SyndFeed feed = feedRetriever.getMostRecentNews(feedUrl);
	}
}
