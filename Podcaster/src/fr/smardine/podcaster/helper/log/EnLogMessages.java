package fr.smardine.podcaster.helper.log;

public enum EnLogMessages {

	//
	// FTP_CONNEXION_FTP_ERROR("Erreur à la connexion au serveur FTP"), //
	// FTP_CONNEXION_PROTOCOLE_ERROR(
	// "Erreur de protocole à la connexion au serveur FTP"), //
	// FTP_CONNEXION_OK("Connexion au serveur FTP réussie"), //
	//
	// FTP_DECONNEXION_ERROR("Erreur à la déconnexion du serveur FTP "), //
	// FTP_DECONNEXION_PROTOCOLE_ERROR_(
	// "Erreur de protocole à la deconnexion du serveur FTP"), //
	// FTP_DECONNEXION_OK("Déconnexion du serveur FTP réussie "), //
	//
	// FTP_ASCII_FTP_ERROR("Erreur FTP au passage en mode ASCII"), //
	// FTP_ASCII_PROTOCOLE_ERROR(
	// "Erreur de protocole FTP au passage en mode ASCII"), //
	// FTP_ASCII_OK("Passage du serveur FTP en mode ASCII réussi"), //
	//
	// FTP_BINARYMODE_FTP_ERROR("Erreur FTP au passage en mode BINARY"), //
	// FTP_BINARYMODE_PROTOCOLE_ERROR(
	// "Erreur de protocole FTP au passage en mode BINARY"), //
	// FTP_BINARYMODE_OK("Passage du serveur FTP en mode BINARY réussi"), //
	// //
	// FTP_ACTIVE_ERROR("Erreur FTP au basculement en mode passif/actif"), //
	// FTP_PASSIVE_ERROR("Erreur FTP au basculement en mode passif/actif"), //
	// FTP_ACTIVE_OK("Basculement du serveur FTP en mode Actif réussi"), //
	// FTP_PASSIVE_OK("Basculement du serveur FTP en mode Passif réussi"), //
	// FTP_SUB_DIRECTORY_ERROR(
	// "Erreur FTP à la recuperation d'une liste de sous dossiers "), //
	// FTP_SUB_DIRECTORY_PROTOCOLE_ERROR(
	// "Erreur de protocole à la recuperation d'une liste de sous dossier"), //
	// FTP_DIRECTORY_ERROR(
	// "Erreur FTP à l'obtention du repertoire courant du serveur FTP"), //
	// FTP_DIRECTORY_PROTOCOLE_ERROR(
	// "Erreur de protocole à l'obtention du repertoire courant du serveur FTP"),
	// //
	// FTP_CHANGEDIR_ERROR("Erreur FTP au changement de répertoire"), //
	// FTP_CHANGEDIR_PROTOCOLE_ERROR(
	// "Erreur de protocole FTP au changement de répertoire"), //
	// FTP_CHANGEDIR_OK("Changement de répertoire réussi"), //
	// FTP_CHANGEDIR_NOK("Changement de répertoire echoué"), //
	// FTP_PARENTDIR_ERROR("Erreur FTP à la récuperation du dossier parent"), //
	// FTP_PARENTDIR_PROTOCOLE_ERROR(
	// "Erreur de protocole à la récupération du dossier parent"), //
	// FTP_PARENTDIR_OK("Récuperation du dossier parent réussi"), //
	//
	// FTP_UPLOAD_ERROR("Erreur FTP à l'upload d'un fichier sur le serveur FTP"),
	// //
	// FTP_UPLOAD_PROTOCOLE_ERROR(
	// "Erreur de protocole à l'envoi d'un ficheir sur le serveur FTP"), //
	// FTP_UPLOAD_OK("Envoi du fichier vers le serveur FTP reussi"), //
	// FTP_DOWNLOAD_ERROR(
	// "Erreur FTP au téléchargement d'un fichier depuis le serveur FTP"), //
	// FTP_DOWNLOAD_PROTOCOLE_ERROR(
	// "Erreur de protocole au téléchargement d'un ficheir depuis le serveur FTP"),
	// //
	// FTP_DOWNLOAD_OK("Téléchargement du fichier depuis le serveur OKs"), //
	// FTP_IS_DIR_EXIST_ERROR(
	// "Erreur FTP lors de la vérification de la présence d'un repertoire distant"),
	// //
	// FTP_IS_DIR_EXIST_PROTOCOLE_ERROR(
	// "Erreur de protocole lors de la vérification de la présence d'un repertoire distant"),
	// //
	// FTP_CREATE_DIR_ERROR(
	// "Erreur FTP à la création d'un repertoire sur le serveur"), //
	// FTP_CREATE_DIR_PROTOCOLE_ERROR(
	// "Erreur de protocole à la création d'un repertoire sur le serveur"), //
	// FTP_CREATE_DIR_OK("Création d'un repertoire sur le serveur FTP réussi"),
	// //
	// // FTP_CONNEXION_NOK("La connexion au serveur FTP à échouée"), //
	// FTP_UPLOAD_NOK("L'envoi du fichier trace vers le serveur à echoué"), //
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
