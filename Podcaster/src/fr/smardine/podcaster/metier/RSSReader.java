package fr.smardine.podcaster.metier;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.os.AsyncTask;
import fr.smardine.podcaster.mdl.MlEpisode;
import fr.smardine.podcaster.mdl.MlFlux;

/**
 * Parser un flux RSS
 * @author Fobec 2010
 */

public class RSSReader extends AsyncTask<String, Void, MlFlux> {

	// private final List<String> listeRetour = new ArrayList<String>();
	private MlFlux unFlux;

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
			nodes = doc.getElementsByTagName("title");
			Node node = doc.getDocumentElement();

			unFlux.setTitre(this.readNode(node, new EnBaliseRSS[] {
					EnBaliseRSS.Channel, EnBaliseRSS.Title }));// "channel|title"));
			unFlux.setDateDernierePublication(GMTDateToFrench(this.readNode(
					node, new EnBaliseRSS[] { EnBaliseRSS.PubDate })));// "channel|lastBuildDate"))));

			// System.out.println("Flux RSS: "
			// + this.readNode(node, new EnBaliseRSS[] {
			// EnBaliseRSS.Channel, EnBaliseRSS.Title }));
			// System.out.println("Date de publication: "
			// + GMTDateToFrench(this.readNode(node, new EnBaliseRSS[] {
			// EnBaliseRSS.Channel, EnBaliseRSS.LastBuildDate })));
			// List<String> lstChild = getAllChild(node);
			// for (String s : lstChild) {
			// System.out.println(s);
			// }
			System.out.println();
			/**
			 * Elements du flux RSS
			 **/
			nodes = doc.getElementsByTagName(EnBaliseRSS.Item.toString());
			for (int i = 0; i < nodes.getLength(); i++) {
				element = (Element) nodes.item(i);
				MlEpisode unEpisode = new MlEpisode();
				unEpisode.setTitre(readNode(element,
						new EnBaliseRSS[] { EnBaliseRSS.Title }));
				// unEpisode.setLink(new URL(readNode(element,
				// new EnBaliseRSS[] { EnBaliseRSS.Link })));

				// unEpisode.setDatePublication(new
				// Date(GMTDateToFrench(readNode(
				// element, new EnBaliseRSS[] { EnBaliseRSS.PubDate }))));
				unEpisode.setDescription(readNode(element,
						new EnBaliseRSS[] { EnBaliseRSS.Description }));
				unEpisode.setGuid(readNode(element,
						new EnBaliseRSS[] { EnBaliseRSS.Guid }));
				unFlux.getListeEpisode().add(unEpisode);
				// listeRetour.addAll(getAllChild(element));

				// System.out.println("Titre: " + readNode(element, "title"));
				// System.out.println("Lien: " + readNode(element, "link"));
				// System.out.println("Date: "
				// + GMTDateToFrench(readNode(element, "pubDate")));
				// System.out.println("Description: "
				// + readNode(element, "description"));
				// System.out.println();
			} // for

			// for
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

		// String[] paths = _path.split("\\|");
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
	public String GMTDateToFrench(String gmtDate) {
		try {
			SimpleDateFormat dfGMT = new SimpleDateFormat(
					"EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
			dfGMT.parse(gmtDate);
			SimpleDateFormat dfFrench = new SimpleDateFormat(
					"EEEE, d MMMM yyyy HH:mm:ss", Locale.FRANCE);
			return dfFrench.format(dfGMT.getCalendar().getTime());
		} catch (ParseException ex) {
			// Logger.getLogger(RSSReader.class.getName()).log(Level.SEVERE,
			// null,
			// ex);
		}
		return "";
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