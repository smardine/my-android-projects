/*
 * Copyright (c) 2009 nullwire aps Permission is hereby granted, free of charge,
 * to any person obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit
 * persons to whom the Software is furnished to do so, subject to the following
 * conditions: The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software. THE SOFTWARE
 * IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR
 * IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * Contributors: Mads Kristiansen, mads.kristiansen@nullwire.com Glen Humphrey
 * Evan Charlton Peter Hewitt
 */

package fr.smardine.podcaster.database;

import java.util.ArrayList;
import java.util.List;

import fr.smardine.podcaster.database.structure.EnStructEpisode;
import fr.smardine.podcaster.database.structure.EnStructFlux;
import fr.smardine.podcaster.database.structure.EnTable;
import fr.smardine.podcaster.database.structure.IStructureTable;

/**
 * @author smardine Contient les scripts de creation de la base
 */
public class G_creation_base {
	// private List<String> allCreation;
	// private List<String> creationtable;
	// private List<String> creationCategorie;
	// private List<String> creationMarque;

	/**
	 * 
	 */
	public G_creation_base() {

	}

	/**
	 * @return l'ensemble des scripts de creation de la base.
	 */
	public List<String> getallCreation() {
		List<String> allCreation = new ArrayList<String>();
		allCreation.addAll(getScriptsDeCreationDesTables());
		// allCreation.addAll(getCategorie());
		// allCreation.addAll(getMarque());
		return allCreation;
	}

	/**
	 * Creation des tables
	 * @return liste de scripts
	 */
	public List<String> getScriptsDeCreationDesTables() {
		List<String> creationtable = new ArrayList<String>();
		// "CREATE  TABLE  IF NOT EXISTS FLUX (ID_FLUX INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , TITRE VARCHAR, DATE_DERNIERE_SYNCHRO DATETIME, VIGNETTE_URL VARCHAR, VIGNETTE_FILE BLOB, ID_PARAMETRE INTEGER, ID_CATEGORIE INTEGER, URL VARCHAR)";
		creationtable.add(createTable(EnTable.FLUX, EnStructFlux.values()));
		creationtable
				.add(createTable(EnTable.EPISODE, EnStructEpisode.values()));
		creationtable.add("INSERT INTO "+EnTable.FLUX+" (TITRE_FLUX, DATE_DERNIERE_SYNCHRO, VIGNETTE_URL, VIGNETTE, ID_PARAMETRE, ID_CATEGORIE, URL) "+
					"values " +
					"(Laurent Gerra, 01/01/2012,null,null,-1,-1,http://www.rtl.fr/podcast/laurent-gerra.xml");
		return creationtable;
	}

	public String createTable(EnTable p_table, IStructureTable[] p_listeChamp) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE  TABLE  IF NOT EXISTS " + p_table.getNomTable()
				+ " (");
		IStructureTable[] listeChamp = p_listeChamp;
		for (int i = 0; i < listeChamp.length; i++) {
			IStructureTable unChamp = listeChamp[i];
			if (i == 0) {
				sb.append(unChamp.getNomChamp() + " "
						+ unChamp.getTypeChamp().name()
						+ " PRIMARY KEY AUTOINCREMENT  NOT NULL  UNIQUE , ");
			}
			else if (i == listeChamp.length - 1) {
				sb.append(unChamp.getNomChamp() + " "
						+ unChamp.getTypeChamp().name() + ")");
			} else {
				sb.append(unChamp.getNomChamp() + " "
						+ unChamp.getTypeChamp().name() + " , ");
			}
		}
		return sb.toString();

	}

	/**
	 * @return l'ensemble des categories a inserer en base.
	 */
	public List<String> getCategorie() {
		List<String> creationCategorie = new ArrayList<String>();
		creationCategorie
				.add("INSERT INTO trousse_produits (nom_souscatergorie,nom_categorie,ISCHECKED) VALUES ('Fonds de teint','Visage','false')");
		creationCategorie
				.add("INSERT INTO trousse_produits (nom_souscatergorie,nom_categorie,ISCHECKED) VALUES ('Correcteurs - Bases','Visage','false')");
		creationCategorie
				.add("INSERT INTO trousse_produits (nom_souscatergorie,nom_categorie,ISCHECKED) VALUES ('Blush','Visage','false')");
		creationCategorie
				.add("INSERT INTO trousse_produits (nom_souscatergorie,nom_categorie,ISCHECKED) VALUES ('Poudres','Visage','false')");
		creationCategorie
				.add("INSERT INTO trousse_produits (nom_souscatergorie,nom_categorie,ISCHECKED) VALUES ('Crayons - Eyliners','Yeux','false')");
		creationCategorie
				.add("INSERT INTO trousse_produits (nom_souscatergorie,nom_categorie,ISCHECKED) VALUES ('Bases','Yeux','false')");
		creationCategorie
				.add("INSERT INTO trousse_produits (nom_souscatergorie,nom_categorie,ISCHECKED) VALUES ('Fards','Yeux','false')");
		creationCategorie
				.add("INSERT INTO trousse_produits (nom_souscatergorie,nom_categorie,ISCHECKED) VALUES ('Mascaras','Yeux','false')");
		creationCategorie
				.add("INSERT INTO trousse_produits (nom_souscatergorie,nom_categorie,ISCHECKED) VALUES ('Crayons contour','Levres','false')");
		creationCategorie
				.add("INSERT INTO trousse_produits (nom_souscatergorie,nom_categorie,ISCHECKED) VALUES ('Rouges � l�vres','Levres','false')");
		creationCategorie
				.add("INSERT INTO trousse_produits (nom_souscatergorie,nom_categorie,ISCHECKED) VALUES ('Vernis � ongles','Autres','false')");
		return creationCategorie;
	}

	// /**
	// * @return l'ensemble des marques de depart.
	// */
	// public List<String> getMarque() {
	// creationMarque = new ArrayList<String>();
	// for (String s : SCRIPT_INSERT_MARQUE) {
	// creationMarque.add(s);
	// }
	// return creationMarque;
	// }
	//
	// /**
	// *
	// */
	// private final String[] SCRIPT_INSERT_MARQUE = {
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('2B','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Agn�s b.','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Alverde','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Annabelle','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Annayak�','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Anne Faug�re','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Arcancil','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Artdeco','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Auri�ge','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Avon','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('B Never Too Busy To Be Beautiful','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('BS Beauty Success','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Bare Escentuals bareMinerals','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('BeYu','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('BeneFit','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Biotherm','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Black Up','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Blinc','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Bobbi Brown','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Body Nature','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Bourjois','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('By Terry','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Bys','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Calvin Klein','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Cargo','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Carlo di Roma','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Carrefour','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Casino','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Catrice','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Chanel','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Charmeen  Professional','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Christian Breton','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Christian Dior','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Clarins','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Clinique','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Coastal Scents','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Constance Carroll','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Corinne Cobson','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Cosmod','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Couleur Caramel','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('CoverGirl','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Daniel Jouvance','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Darphin','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Debby','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Dr Pierre Ricaud','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Dr Temt','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Dr.Hauschka','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('D�borah','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Eclipse Colours','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Ecrinal','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Elc�a','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Elite','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Elizabeth Arden','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Elysambre','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Elytis','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Essence','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Est�e Lauder','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Eye care','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Eyeko','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Fresh','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Fusion Beauty','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Galerie No�mie','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Gal�nic','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Gemey-Maybelline','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Giorgio Armani Cosmetics','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Givenchy','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Green Mama','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Guerlain','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('HC HighTech Cosmetics','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Hema','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('H�l�na Rubinstein','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Iman','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Imju','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Innoxa','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Jane Iredale','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Jean-Claude Biguine','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Kanebo','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Kiss Me','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('L Action Cosm�tique','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('L Or�al','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('LR','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('La Roche Posay','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Lancaster','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Lanc�me','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Laora','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Lash Plus Greenfields','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Laura','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Lavera','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Le Club de la Beaut� Naturelle','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Lena Bliss','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Les Copines','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Lise Watier','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Logona','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Longcils Boncza','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Mac','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Make Me Up','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Make Up For Ever','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Manhattan','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Marc Chancel','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Marcelle','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Margaret Astor','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Masters Colors','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Mavala','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Max Factor','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Maybelline Jade','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Miss Cop','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Miss Den','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Miss Helen - Monoprix','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Model Co','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Mod�lite','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Mosqueta s','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('NYX','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Nars','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Naturado','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Nina Ricci','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Nivea','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Nvey Eco','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Only Cosmetics','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Origins','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Paul et Joe','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Paula s Choice','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Pb Cosmetics','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Peggy Sage','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Phyt s','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Planter s','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Pout','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Professeur Christine Poelman','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Pupa','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Qualinat','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Revlon','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Rimmel','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('R�serve Naturelle','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('SLA Serge Louis Alvarez','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Sant�','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Sephora','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Serge Lutens','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Shiseido','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Shu Uemura','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Sisley','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Smashbox','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('So Bio Etic','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Sothys','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Stephane Marais','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Stila','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Suhada Lidl','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Swiss O Par','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('T.LeClerc','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Talika','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Terre d Oc','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('The Body Shop','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Thierry Mugler','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Tony et Tina','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Too Faced','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('UNE Natural Beauty','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Urban Decay','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Yours','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Yves Rocher','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Yves Saint Laurent','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('Zuii','false')",
	// "INSERT INTO trousse_marques (nom_marque,ISCHECKED) VALUES ('e.l.f. eyes lips face','false')"
	// };

}
