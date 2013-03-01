package fr.smardine.podcaster.listener;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import fr.smardine.podcaster.mdl.MlEpisode;
import fr.smardine.podcaster.mdl.MlListeFlux;
import fr.smardine.podcaster.thread.EnMethodType;
import fr.smardine.podcaster.thread.MajFluxProgressDialog;

public class ButtonMajFluxClickListener implements OnClickListener {

	private final Context context;
	// private final View view;
	private ListView listView;

	// private Handler handle;

	public ButtonMajFluxClickListener(Context baseContext, View p_view, ListView p_listView) {
		this.context = baseContext;
		this.listView = p_listView;
	}

	@Override
	public void onClick(View p_view) {
		final List<String> listeUrlAMettreAJour = new ArrayList<String>();
		final MlListeFlux listeFluxAMettreAJour = new MlListeFlux();

		for (int i = 0; i < listView.getCount(); i++) {
			MlEpisode e = (MlEpisode) this.listView.getItemAtPosition(i);
			if (!listeUrlAMettreAJour.contains(e.getFluxParent().getFluxUrl())) {
				listeUrlAMettreAJour.add(e.getFluxParent().getFluxUrl());
				listeFluxAMettreAJour.add(e.getFluxParent());
			}
		}

		// Builder ad = new AlertDialog.Builder(context);

		// // ad.setPositiveButton("ok", ouinon)
		// AlertDialogOuiNonClickListener ouinonClickListener = new AlertDialogOuiNonClickListener(this.context, this.listView,
		// listeUrlAMettreAJour);
		// ad.setPositiveButton("Ok", ouinonClickListener);
		// ad.setNegativeButton("Annuler", null);
		// // ad.setView(this.view);
		// ad.show();

		// ProgressDialog myPd_bar = new ProgressDialog(this.context);
		// myPd_bar.setMessage("Loading....");
		// myPd_bar.setTitle("Maj en cours..");
		// myPd_bar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		// myPd_bar.setProgress(0);
		// myPd_bar.setMax(100);
		// myPd_bar.show();

		MajFluxProgressDialog maj = new MajFluxProgressDialog();
		maj.synchroMajFluxProgressDialog(context, EnMethodType.MAJ_FLUX, listeFluxAMettreAJour, listView);

		// RSSReaderMajFluxAsynckTask majtask = new RSSReaderMajFluxAsynckTask(this.context, listeFluxAMettreAJour, myPd_bar,
		// this.listView);
		// majtask.execute();

	}
}
