package fr.smardine.podcaster.alertDialog.clickListener.buttonClick;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import fr.smardine.podcaster.alertDialog.IAlertDialogClickListener;
import fr.smardine.podcaster.alertDialog.clickListener.itemClick.AlertDialogSingleChoiceChoixCatItemClickListener;
import fr.smardine.podcaster.alertDialog.type.EnTypeAlertDialogChoixCat;

/**
 * Gestion des evenements lors du click sur le bouton "Choisir" pour les
 * dialogBox permettant de choisir une categorie d'un type defini
 * @author smardine
 */

public class AlertDialogChoixCatClickListener implements
		IAlertDialogClickListener, OnClickListener {

	private final EnTypeAlertDialogChoixCat type;
	private final Context ctx;
	private final AlertDialogSingleChoiceChoixCatItemClickListener choixCatClickListener;

	/**
	 * Constructeur
	 * @param p_ctx - le Context qui a lance la dialogBox
	 * @param p_type - le type de categorie traite
	 * @param p_choixCatSingleClickListener - le clickListener parent de celui
	 *        ci qui va gerer les actions lors des clicks sur les items de la
	 *        dialogBox
	 */
	public AlertDialogChoixCatClickListener(
			Context p_ctx,
			EnTypeAlertDialogChoixCat p_type,
			AlertDialogSingleChoiceChoixCatItemClickListener p_choixCatSingleClickListener) {
		this.ctx = p_ctx;
		this.type = p_type;
		this.choixCatClickListener = p_choixCatSingleClickListener;
	}

	@Override
	public void onClick(DialogInterface p_dialog, int p_item) {
		switch (type) {
			case VISAGE:
			case YEUX:
			case LEVRE:
			case AUTRE:
				majTable();
				break;
		}
	}

	private void majTable() {
		// AccesTableTrousseProduits accesTrousseProds = new
		// AccesTableTrousseProduits(
		// this.ctx);
		// accesTrousseProds.majSouscatChoisie(choixCatClickListener
		// .getSousCategorieChoisie());
	}
}
