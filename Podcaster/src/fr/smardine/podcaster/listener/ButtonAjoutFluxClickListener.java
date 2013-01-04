package fr.smardine.podcaster.listener;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.alertDialog.clickListener.buttonClick.AlertDialogOuiNonClickListener;

public class ButtonAjoutFluxClickListener implements OnClickListener {

	private final Context context;
	private final View view;

	public ButtonAjoutFluxClickListener(Context baseContext, View p_view) {
		this.context = baseContext;
		this.view = p_view;
	}

	@Override
	public void onClick(View p_view) {
		// AlertDialogFactory adFactory = new AlertDialogFactory(context);
		Builder ad = new AlertDialog.Builder(context);

		ad.setTitle("Ajout de flux");
		ad.setIcon(R.drawable.ad_attention);
		ad.setMessage("http://www.rtl.fr/podcast/laurent-gerra.xml");

		AlertDialogOuiNonClickListener ouinonClickListener = new AlertDialogOuiNonClickListener(
				"http://www.rtl.fr/podcast/laurent-gerra.xml", this.context);
		ad.setPositiveButton("Ok", ouinonClickListener);
		ad.setNegativeButton("Annuler", null);
		ad.setView(this.view);
		ad.show();

	}
}
