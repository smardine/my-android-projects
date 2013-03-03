package fr.smardine.podcaster.metier;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import android.os.Handler;
import fr.smardine.podcaster.helper.DownloadHelper;
import fr.smardine.podcaster.mdl.EnTypeEpisode;
import fr.smardine.podcaster.mdl.MlEpisode;
import fr.smardine.podcaster.mdl.MlFlux;
import fr.smardine.podcaster.thread.EnThreadExecResult;

public class RssParserMetier {

	private RssParserMetier() {

	}

	/**
	 * Construire l'entete d'un flux
	 * @param node
	 * @param unFlux
	 * @param p_context
	 * @param p_urlAParser
	 */
	public static void contruitEnteteFlux(Node node, MlFlux unFlux, Context p_context, String p_urlAParser) {
		unFlux.setTitre(readNode(node, new EnBaliseRSS[] { EnBaliseRSS.Channel, EnBaliseRSS.Title }));
		unFlux.setVignetteUrl(readNode(node, new EnBaliseRSS[] { EnBaliseRSS.Channel, EnBaliseRSS.Image, EnBaliseRSS.Url }));
		DownloadHelper.DownloadImageFluxFromUrl(p_context, unFlux.getVignetteUrl(), unFlux);
		unFlux.setDateDerniereSynchro(Calendar.getInstance().getTimeInMillis());
		unFlux.setFluxUrl(p_urlAParser);

	}

	public static Document OuvrirUrl(String p_url) {
		DocumentBuilder builder;
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			URL url = new URL(p_url);
			return builder.parse(url.openStream());
		} catch (ParserConfigurationException e) {
			Logger.getLogger(RssParserMetier.class.getName()).log(Level.SEVERE, null, e);
			return null;
		} catch (MalformedURLException e) {
			Logger.getLogger(RssParserMetier.class.getName()).log(Level.SEVERE, null, e);
			return null;
		} catch (SAXException e) {
			Logger.getLogger(RssParserMetier.class.getName()).log(Level.SEVERE, null, e);
			return null;
		} catch (IOException e) {
			Logger.getLogger(RssParserMetier.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}

	/*
	 * Afficher une Date GML au format francais
	 * @param gmtDate
	 * @return
	 */
	private static long GMTDateToFrench(String gmtDate) {
		try {
			SimpleDateFormat dfGMT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
			return dfGMT.parse(gmtDate).getTime();
			// SimpleDateFormat dfFrench = new SimpleDateFormat(
			// "EEEE, d MMMM yyyy HH:mm:ss", Locale.FRANCE);
			// return dfFrench.format(dfGMT.getCalendar().getTime());
		} catch (ParseException ex) {
			Logger.getLogger(RssParserMetier.class.getName()).log(Level.SEVERE, null, ex);
		}
		return 0;
	}

	/**
	 * Méthode permettant de retourner ce que contient d'un noeud
	 * @param _node le noeud principal
	 * @param _path suite des noms des noeud sans espace séparer par des "|"
	 * @return un string contenant le valeur du noeud voulut
	 */
	private static String readNode(Node _node, EnBaliseRSS[] _paths) {

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
	private static Node getChildByName(Node _node, String _name) {
		if (_node == null) {
			return null;
		}
		NodeList listChild = _node.getChildNodes();

		if (listChild != null) {
			for (int i = 0; i < listChild.getLength(); i++) {
				Node child = listChild.item(i);
				if (child != null) {
					// System.out.println(child.getNodeName());
					if ((child.getNodeName() != null && (_name.equals(child.getNodeName())))
							|| (child.getLocalName() != null && (_name.equals(child.getLocalName())))) {
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
	private static String readNodeValue(Node p_node, EnBaliseRSS p_path, String p_value) {

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

	/**
	 * Parcourir la liste des "noeuds" xml a la recherche des balises "Item" Lorsuq'une balise est trouvée, on contruit un MlEpisode a
	 * partir de cet "Item" Si l'episode est considéré comme "Nouveau" (n'existe pas en base), on le rajoute a la liste des episodes du flux
	 * parent
	 * @param p_fluxParent
	 * @param p_doc
	 * @param progressDialogHandler
	 */
	public static void parcourirNodeListeEtValoriseListeEpisode(MlFlux p_fluxParent, Document p_doc, Handler progressDialogHandler) {
		// on recupere les tag nommé "item"
		NodeList nodes = p_doc.getElementsByTagName(EnBaliseRSS.Item.toString());

		for (int i = 0; i < nodes.getLength(); i++) {
			// p_progressDialog.setProgress(i + 1);

			Element element = (Element) nodes.item(i);
			MlEpisode unEpisode = new MlEpisode(p_fluxParent);
			// comme on est deja sous "item", on peut ne passer que le tag
			// "Title"
			// on recupere le titre de l'episode
			unEpisode.setTitre(readNode(element, new EnBaliseRSS[] { EnBaliseRSS.Title }));
			progressDialogHandler.sendMessage(progressDialogHandler.obtainMessage(EnThreadExecResult.ENCOURS.getCode(),
					unEpisode.getTitre()));
			// publishProgress(unEpisode.getTitre());
			// p_progressDialog.setMessage(unEpisode.getTitre());
			// on recupere la descritpion
			unEpisode.setDescription(readNode(element, new EnBaliseRSS[] { EnBaliseRSS.Description }));
			// on recupere l'url du fichier xml qui contient la définition
			// du flux rss
			unEpisode.setUrlEpisode(readNode(element, new EnBaliseRSS[] { EnBaliseRSS.Link }));

			// on recupere la durée de l'element si audio ou video
			unEpisode.setDuree(readNode(element, new EnBaliseRSS[] { EnBaliseRSS.ItuneDuration }));
			// on recupere la date de publication
			unEpisode.setDatePublication(GMTDateToFrench(readNode(element, new EnBaliseRSS[] { EnBaliseRSS.PubDate })));

			// on recupere l'url du fichier media (mp3, mp4...)
			unEpisode.setGuid(readNode(element, new EnBaliseRSS[] { EnBaliseRSS.Guid }));

			// on recupere le type d'episode
			String typeEpisode = readNodeValue(element, EnBaliseRSS.Enclosure, EnBaliseRSS.Type.toString());
			unEpisode.setTypeEpisode(EnTypeEpisode.GetTypeEpisodeByName(typeEpisode));

			unEpisode.setUrlMedia(readNodeValue(element, EnBaliseRSS.Enclosure, EnBaliseRSS.Url.toString()));

			// String urlImage = this.readNode(element, new EnBaliseRSS[] { EnBaliseRSS.Channel, EnBaliseRSS.Image, EnBaliseRSS.Url });

			// if (urlImage != null) {
			// unEpisode.setVignetteUrl(urlImage);
			// DownloadHelper.DownloadImageEpisodeFromUrl(context, unEpisode.getVignetteUrl(), unFlux, unEpisode);
			// } else
			if (p_fluxParent.isVignetteTelechargee()) {
				unEpisode.setVignetteTelechargee(p_fluxParent.getVignetteTelechargee());
			} else {
				unEpisode.setVignetteTelechargee(null);
			}

			// on determine le statu de lecture
			// si l'episode n'est pas connu de la base, le statut est forcement "non lu"
			unEpisode.positionneStatutLecture();
			// on determine le statut de telechargement
			unEpisode.positionneStatutTelechargement();
			if (unEpisode.isNouveau()) {
				unEpisode.setStatutNouveau(true);
				p_fluxParent.getListeEpisode().add(unEpisode);
			}
		}
	}
}
