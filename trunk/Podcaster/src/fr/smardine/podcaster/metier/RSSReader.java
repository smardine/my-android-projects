package fr.smardine.podcaster.metier;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.Context;
import android.os.AsyncTask;
import fr.smardine.podcaster.helper.DownloadHelper;
import fr.smardine.podcaster.mdl.EnTypeEpisode;
import fr.smardine.podcaster.mdl.MlEpisode;
import fr.smardine.podcaster.mdl.MlFlux;

/**
 * Parser un flux RSS
 * @author Fobec 2010
 */

public class RSSReader extends AsyncTask<String, Void, MlFlux> {

	// private final List<String> listeRetour = new ArrayList<String>();
	private MlFlux unFlux;
	private final Context context;

	public RSSReader(Context p_context) {
		this.context = p_context;
	}

	/**
	 * Parser le fichier XML
	 * @param feedurl URL du flux RSS
	 */

	private MlFlux parse(String feedurl) {
		unFlux = new MlFlux();
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			URL url = new URL(feedurl);
			Document doc = builder.parse(url.openStream());
			NodeList nodes = null;
			Element element = null;
			/**
			 * Titre et date du flux
			 */
			// nodes = doc.getElementsByTagName("title");
			Node node = doc.getDocumentElement();

			unFlux.setTitre(this.readNode(node, new EnBaliseRSS[] {
					EnBaliseRSS.Channel, EnBaliseRSS.Title }));
			unFlux.setVignetteUrl(this.readNode(node, new EnBaliseRSS[] {
					EnBaliseRSS.Channel, EnBaliseRSS.Image, EnBaliseRSS.Url }));
			DownloadHelper.DownloadImageFluxFromUrl(context,
					unFlux.getVignetteUrl(), unFlux);
			// System.out.println();
			/**
			 * Elements du flux RSS
			 **/
			// on recupere les tag nomé "item"
			nodes = doc.getElementsByTagName(EnBaliseRSS.Item.toString());
			for (int i = 0; i < nodes.getLength(); i++) {
				element = (Element) nodes.item(i);
				MlEpisode unEpisode = new MlEpisode();
				// comme on est deja sous "item", on peut ne passer que le tag
				// "Title"
				// on recupere le titre de l'episode
				unEpisode.setTitre(readNode(element,
						new EnBaliseRSS[] { EnBaliseRSS.Title }));
				// on recupere l'url du fichier xml qui contient la définition
				// du flux rss
				unEpisode.setUrlEpisode(readNode(element,
						new EnBaliseRSS[] { EnBaliseRSS.Link }));

				// on recupere la durée de l'element si audio ou video
				unEpisode.setDuree(readNode(element,
						new EnBaliseRSS[] { EnBaliseRSS.ItuneDuration }));
				// on recupere la date de publication
				unEpisode.setDatePublication(GMTDateToFrench(readNode(element,
						new EnBaliseRSS[] { EnBaliseRSS.PubDate })));
				// on recupere la descritpion
				unEpisode.setDescription(readNode(element,
						new EnBaliseRSS[] { EnBaliseRSS.Description }));
				// on recupere l'url du fichier media (mp3, mp4...)
				unEpisode.setGuid(readNode(element,
						new EnBaliseRSS[] { EnBaliseRSS.Guid }));

				// on recupere le type d'episode
				String typeEpisode = readNodeValue(element,
						EnBaliseRSS.Enclosure, EnBaliseRSS.Type.toString());
				unEpisode.setTypeEpisode(EnTypeEpisode
						.GetTypeEpisodeByName(typeEpisode));
				if (unFlux.isVignetteTelechargee()) {
					unEpisode.setVignetteTelechargee(unFlux
							.getVignetteTelechargee());
					unEpisode.setVignetteTelechargee(true);
				} else {
					unEpisode.setVignetteTelechargee(null);
					unEpisode.setVignetteTelechargee(false);
				}

				unFlux.getListeEpisode().add(unEpisode);

			}
		} catch (SAXException ex) {
			Logger.getLogger(RSSReader.class.getName()).log(Level.SEVERE, null,
					ex);
		} catch (IOException ex) {
			Logger.getLogger(RSSReader.class.getName()).log(Level.SEVERE, null,
					ex);
		} catch (ParserConfigurationException ex) {
			Logger.getLogger(RSSReader.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return unFlux;
	}

	/**
	 * Méthode permettant de retourner ce que contient d'un noeud
	 * @param _node le noeud principal
	 * @param _path suite des noms des noeud sans espace séparer par des "|"
	 * @return un string contenant le valeur du noeud voulut
	 */
	public String readNode(Node _node, EnBaliseRSS[] _paths) {

		Node node = null;

		if (_paths != null && _paths.length > 0) {
			node = _node;

			for (int i = 0; i < _paths.length; i++) {
				node = getChildByName(node, _paths[i].toString().trim());
			}
		}

		if (node != null) {
			return node.getTextContent();
		} else {
			return "";
		}
	}

	/**
	 * renvoye le nom d'un noeud fils a partir de son nom
	 * @param _node noeud pricipal
	 * @param _name nom du noeud fils
	 * @return le noeud fils
	 */
	public Node getChildByName(Node _node, String _name) {
		if (_node == null) {
			return null;
		}
		NodeList listChild = _node.getChildNodes();

		if (listChild != null) {
			for (int i = 0; i < listChild.getLength(); i++) {
				Node child = listChild.item(i);
				if (child != null) {
					if ((child.getNodeName() != null && (_name.equals(child
							.getNodeName())))
							|| (child.getLocalName() != null && (_name
									.equals(child.getLocalName())))) {
						return child;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Méthode permettant de retourner ce que contient d'un noeud
	 * @param p_node le noeud principal
	 * @param _path suite des noms des noeud sans espace séparer par des "|"
	 * @param p_value
	 * @return un string contenant le valeur du noeud voulut
	 */
	public String readNodeValue(Node p_node, EnBaliseRSS p_path, String p_value) {

		Node node = null;

		node = p_node;
		node = getChildByName(node, p_path.toString().trim());
		if (node != null) {
			NamedNodeMap attributes = node.getAttributes();
			node = attributes.getNamedItem(p_value);
			if (node != null) {
				return node.getTextContent();
			}
		}
		return "";
	}

	public List<String> getAllChild(Node p_node) {
		List<String> listeRetour = new ArrayList<String>();
		NodeList listChild = p_node.getChildNodes();

		if (listChild != null) {
			for (int i = 0; i < listChild.getLength(); i++) {
				Node child = listChild.item(i);
				if (child != null) {
					if (!EnBaliseRSS.Item.toString()
							.equals(child.getNodeName())) {
						StringBuilder sb = new StringBuilder();
						sb.append("Node name: " + child.getNodeName());
						sb.append("Node value: " + child.getNodeValue());
						sb.append("Text content: " + child.getTextContent());

						listeRetour.add(sb.toString());
					}

				}
			}
		}
		return listeRetour;
	}

	/**
	 * Afficher une Date GML au format francais
	 * @param gmtDate
	 * @return
	 */
	public Date GMTDateToFrench(String gmtDate) {
		try {
			SimpleDateFormat dfGMT = new SimpleDateFormat(
					"EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
			return dfGMT.parse(gmtDate);
			// SimpleDateFormat dfFrench = new SimpleDateFormat(
			// "EEEE, d MMMM yyyy HH:mm:ss", Locale.FRANCE);
			// return dfFrench.format(dfGMT.getCalendar().getTime());
		} catch (ParseException ex) {
			// Logger.getLogger(RSSReader.class.getName()).log(Level.SEVERE,
			// null,
			// ex);
		}
		return null;
	}

	// /**
	// * Exemple
	// * @param args
	// */
	// public static void main(String[] args) {
	// RSSReader reader = new RSSReader();
	// reader.parse("http://fobec.com/CMS/fobec.xml");
	// }

	@Override
	protected MlFlux doInBackground(String... urls) {
		parse(urls[0]);
		return unFlux;
	}
}