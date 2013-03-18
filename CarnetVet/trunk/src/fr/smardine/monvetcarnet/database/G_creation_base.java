/*
 * Copyright (c) 2009 nullwire aps Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software
 * is furnished to do so, subject to the following conditions: The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. Contributors: Mads
 * Kristiansen, mads.kristiansen@nullwire.com Glen Humphrey Evan Charlton Peter Hewitt
 */

package fr.smardine.monvetcarnet.database;

import java.util.ArrayList;
import java.util.List;

import fr.smardine.monvetcarnet.database.structuretable.EnStructCarnet;
import fr.smardine.monvetcarnet.database.structuretable.EnStructDetail;
import fr.smardine.monvetcarnet.database.structuretable.EnStructIdentification;
import fr.smardine.monvetcarnet.database.structuretable.EnStructMaladie;
import fr.smardine.monvetcarnet.database.structuretable.EnStructPoids;
import fr.smardine.monvetcarnet.database.structuretable.EnStructProprietaire;
import fr.smardine.monvetcarnet.database.structuretable.EnStructVaccin;
import fr.smardine.monvetcarnet.database.structuretable.EnTable;
import fr.smardine.monvetcarnet.database.structuretable.IStructureTable;

/**
 * @author smardine Contient les scripts de creation de la base
 */
public class G_creation_base {

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

		return allCreation;
	}

	/**
	 * Creation des tables
	 * @return liste de scripts
	 */
	public List<String> getScriptsDeCreationDesTables() {
		List<String> scriptsCreationTables = new ArrayList<String>();
		// "CREATE  TABLE  IF NOT EXISTS FLUX (ID_FLUX INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , TITRE VARCHAR, DATE_DERNIERE_SYNCHRO DATETIME, VIGNETTE_URL VARCHAR, VIGNETTE_FILE BLOB, ID_PARAMETRE INTEGER, ID_CATEGORIE INTEGER, URL VARCHAR)";
		scriptsCreationTables.add(createTable(EnTable.CARNETS, EnStructCarnet.values()));
		scriptsCreationTables.add(createTable(EnTable.DETAILS, EnStructDetail.values()));
		scriptsCreationTables.add(createTable(EnTable.IDENTIFICATIONS, EnStructIdentification.values()));
		scriptsCreationTables.add(createTable(EnTable.MALADIES, EnStructMaladie.values()));
		scriptsCreationTables.add(createTable(EnTable.POIDS, EnStructPoids.values()));
		scriptsCreationTables.add(createTable(EnTable.PROPRIETAIRES, EnStructProprietaire.values()));
		scriptsCreationTables.add(createTable(EnTable.VACCINS, EnStructVaccin.values()));

		return scriptsCreationTables;
	}

	public String createTable(EnTable p_table, IStructureTable[] p_listeChamp) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE  TABLE  IF NOT EXISTS " + p_table.getNomTable() + " (");
		IStructureTable[] listeChamp = p_listeChamp;
		for (int i = 0; i < listeChamp.length; i++) {
			IStructureTable unChamp = listeChamp[i];
			if (i == 0) {
				sb.append(unChamp.getNomChamp() + " " + unChamp.getTypeChamp().name() + " PRIMARY KEY AUTOINCREMENT  NOT NULL  UNIQUE , ");
			} else if (i == listeChamp.length - 1) {
				sb.append(unChamp.getNomChamp() + " " + unChamp.getTypeChamp().name() + ")");
			} else {
				sb.append(unChamp.getNomChamp() + " " + unChamp.getTypeChamp().name() + " , ");
			}
		}
		return sb.toString();

	}

}
