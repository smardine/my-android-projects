package fr.smardine.monvetcarnet.listener;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import fr.smardine.monvetcarnet.alertdialog.AlertDialogFactory;
import fr.smardine.monvetcarnet.fragment.IdentificationFragment;
import fr.smardine.monvetcarnet.mdl.MlCarnet;

public class TextViewIdentificationListener implements OnClickListener {

	private final int idexASelectionner;
	private final Context ctx;
	private final IdentificationFragment identFrag;
	private final MlCarnet carnetParent;

	public TextViewIdentificationListener(Context p_ctx, IdentificationFragment identFrag, MlCarnet p_carnetParent, int indexASelectionner) {
		this.idexASelectionner = indexASelectionner;
		this.ctx = p_ctx;
		this.identFrag = identFrag;
		this.carnetParent = p_carnetParent;
	}

	@Override
	public void onClick(View v) {
		AlertDialogFactory.creerEtAfficherIdentificationSaisie(ctx, identFrag, carnetParent, idexASelectionner);
	}

}
