package fr.smardine.podcaster.alertDialog.clickListener.buttonClick;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import fr.smardine.podcaster.alertDialog.IAlertDialogClickListener;
import fr.smardine.podcaster.alertDialog.type.EnTypeAlertDialogAttention;

/**
 * Gestion des evenements lors du click le bouton Ok les dialogBox permettant
 * d'afficher un message d'avertissement � l'utilisateur
 * @author smardine
 */

public class AlertDialogAttentionClickListener implements
		IAlertDialogClickListener, OnClickListener {

	private final Context ctx;
	private final EnTypeAlertDialogAttention typeClick;

	/**
	 * Constructeur
	 * @param p_ctx - le Context qui a lanc� cette dialogBox
	 * @param p_type - le type de dialogBox � traiter
	 */
	public AlertDialogAttentionClickListener(Context p_ctx,
			EnTypeAlertDialogAttention p_type) {
		this.ctx = p_ctx;
		this.typeClick = p_type;
	}

	@Override
	public void onClick(DialogInterface p_arg0, int p_arg1) {
		switch (typeClick) {
			case AUCUNE_CAT:
			case AUCUNE_MARQUE:
				break;
			case PLUSIEUR_CAT:
				// AccesTableTrousseProduits accesTrousse = new
				// AccesTableTrousseProduits(
				// ctx);
				// accesTrousse.reinitProduitChoisi();
				break;

		}

	}

}
