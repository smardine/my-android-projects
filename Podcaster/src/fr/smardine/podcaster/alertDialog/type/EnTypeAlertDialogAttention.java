package fr.smardine.podcaster.alertDialog.type;

import fr.smardine.podcaster.alertDialog.IAlertDialogClickListener;
import fr.smardine.podcaster.alertDialog.clickListener.buttonClick.AlertDialogAttentionClickListener;

/**
 * Definition des differente DialogBox de type "Attention"
 * @author smardine
 */
public enum EnTypeAlertDialogAttention {

	PLUSIEUR_CAT("Attention", "Vous avez selectionne plus d'une categorie \n"
			+ "Veuillez n'en choisir qu'une.", "Ok",
			AlertDialogAttentionClickListener.class), //
	AUCUNE_CAT("Attention",
			"Vous n'avez rentre aucune categorie \nMerci d'en saisir une",
			"Ok", null), //
	AUCUNE_MARQUE("Attention",
			"Vous n'avez rentre aucune marque \nMerci d'en saisir une", "Ok",
			null), //
	MANQUE_INFO("Attention",
			"Vous avez oublie de rentrer certaines informations.", "Ok", null), //
	AUCUN_PRODUIT_ENREGISTRE(
			"Pour information",
			"Aucun produit n'est actuellement enregistre dans Ma p'tite trousse",
			"Ok", null);

	private String titre;
	private String message;
	private String txtOkBtton;
	private Class<? extends IAlertDialogClickListener> clickListenerClass;

	EnTypeAlertDialogAttention(String p_titre, String p_message,
			String p_textOkButton,
			Class<? extends IAlertDialogClickListener> p_class) {
		this.titre = p_titre;
		this.message = p_message;
		this.txtOkBtton = p_textOkButton;
		this.clickListenerClass = p_class;
	}

	public String getTitre() {
		return titre;
	}

	public String getMessage() {
		return message;
	}

	public String getTxtOkBtton() {
		return txtOkBtton;
	}

	public Class<? extends IAlertDialogClickListener> getClickListenerClass() {
		return clickListenerClass;
	}

}
