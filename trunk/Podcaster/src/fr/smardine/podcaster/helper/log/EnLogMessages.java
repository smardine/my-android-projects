package fr.smardine.podcaster.helper.log;

public enum EnLogMessages {

	//
	// FTP_CONNEXION_FTP_ERROR("Erreur � la connexion au serveur FTP"), //
	// FTP_CONNEXION_PROTOCOLE_ERROR(
	// "Erreur de protocole � la connexion au serveur FTP"), //
	// FTP_CONNEXION_OK("Connexion au serveur FTP r�ussie"), //
	//
	// FTP_DECONNEXION_ERROR("Erreur � la d�connexion du serveur FTP "), //
	// FTP_DECONNEXION_PROTOCOLE_ERROR_(
	// "Erreur de protocole � la deconnexion du serveur FTP"), //
	// FTP_DECONNEXION_OK("D�connexion du serveur FTP r�ussie "), //
	//
	// FTP_ASCII_FTP_ERROR("Erreur FTP au passage en mode ASCII"), //
	// FTP_ASCII_PROTOCOLE_ERROR(
	// "Erreur de protocole FTP au passage en mode ASCII"), //
	// FTP_ASCII_OK("Passage du serveur FTP en mode ASCII r�ussi"), //
	//
	// FTP_BINARYMODE_FTP_ERROR("Erreur FTP au passage en mode BINARY"), //
	// FTP_BINARYMODE_PROTOCOLE_ERROR(
	// "Erreur de protocole FTP au passage en mode BINARY"), //
	// FTP_BINARYMODE_OK("Passage du serveur FTP en mode BINARY r�ussi"), //
	// //
	// FTP_ACTIVE_ERROR("Erreur FTP au basculement en mode passif/actif"), //
	// FTP_PASSIVE_ERROR("Erreur FTP au basculement en mode passif/actif"), //
	// FTP_ACTIVE_OK("Basculement du serveur FTP en mode Actif r�ussi"), //
	// FTP_PASSIVE_OK("Basculement du serveur FTP en mode Passif r�ussi"), //
	// FTP_SUB_DIRECTORY_ERROR(
	// "Erreur FTP � la recuperation d'une liste de sous dossiers "), //
	// FTP_SUB_DIRECTORY_PROTOCOLE_ERROR(
	// "Erreur de protocole � la recuperation d'une liste de sous dossier"), //
	// FTP_DIRECTORY_ERROR(
	// "Erreur FTP � l'obtention du repertoire courant du serveur FTP"), //
	// FTP_DIRECTORY_PROTOCOLE_ERROR(
	// "Erreur de protocole � l'obtention du repertoire courant du serveur FTP"),
	// //
	// FTP_CHANGEDIR_ERROR("Erreur FTP au changement de r�pertoire"), //
	// FTP_CHANGEDIR_PROTOCOLE_ERROR(
	// "Erreur de protocole FTP au changement de r�pertoire"), //
	// FTP_CHANGEDIR_OK("Changement de r�pertoire r�ussi"), //
	// FTP_CHANGEDIR_NOK("Changement de r�pertoire echou�"), //
	// FTP_PARENTDIR_ERROR("Erreur FTP � la r�cuperation du dossier parent"), //
	// FTP_PARENTDIR_PROTOCOLE_ERROR(
	// "Erreur de protocole � la r�cup�ration du dossier parent"), //
	// FTP_PARENTDIR_OK("R�cuperation du dossier parent r�ussi"), //
	//
	// FTP_UPLOAD_ERROR("Erreur FTP � l'upload d'un fichier sur le serveur FTP"),
	// //
	// FTP_UPLOAD_PROTOCOLE_ERROR(
	// "Erreur de protocole � l'envoi d'un ficheir sur le serveur FTP"), //
	// FTP_UPLOAD_OK("Envoi du fichier vers le serveur FTP reussi"), //
	// FTP_DOWNLOAD_ERROR(
	// "Erreur FTP au t�l�chargement d'un fichier depuis le serveur FTP"), //
	// FTP_DOWNLOAD_PROTOCOLE_ERROR(
	// "Erreur de protocole au t�l�chargement d'un ficheir depuis le serveur FTP"),
	// //
	// FTP_DOWNLOAD_OK("T�l�chargement du fichier depuis le serveur OKs"), //
	// FTP_IS_DIR_EXIST_ERROR(
	// "Erreur FTP lors de la v�rification de la pr�sence d'un repertoire distant"),
	// //
	// FTP_IS_DIR_EXIST_PROTOCOLE_ERROR(
	// "Erreur de protocole lors de la v�rification de la pr�sence d'un repertoire distant"),
	// //
	// FTP_CREATE_DIR_ERROR(
	// "Erreur FTP � la cr�ation d'un repertoire sur le serveur"), //
	// FTP_CREATE_DIR_PROTOCOLE_ERROR(
	// "Erreur de protocole � la cr�ation d'un repertoire sur le serveur"), //
	// FTP_CREATE_DIR_OK("Cr�ation d'un repertoire sur le serveur FTP r�ussi"),
	// //
	// // FTP_CONNEXION_NOK("La connexion au serveur FTP � �chou�e"), //
	// FTP_UPLOAD_NOK("L'envoi du fichier trace vers le serveur � echou�"), //
	ENREGISTREMENT_SURVEILLANCE(
			"Enregistrement d'une instance de surveillance des exceptions");

	private String message;

	EnLogMessages(String p_message) {
		this.message = p_message;
	}

	/**
	 * Obtenir le message a passer au Logcat
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

}
