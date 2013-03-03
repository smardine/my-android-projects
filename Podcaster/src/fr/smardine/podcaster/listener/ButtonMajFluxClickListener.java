package fr.smardine.podcaster.listener;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import fr.smardine.podcaster.mdl.MlEpisode;
import fr.smardine.podcaster.mdl.MlListeFlux;
import fr.smardine.podcaster.metier.progressdialog.MajFluxProgressDialog;

public class ButtonMajFluxClickListener implements OnClickListener {

	private final Activity context;
	// private final View view;
	private ListView listView;

	// private Handler handle;

	public ButtonMajFluxClickListener(Activity baseContext, View p_view, ListView p_listView) {
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

		MajFluxProgressDialog maj = new MajFluxProgressDialog();
		maj.synchroMajFluxProgressDialog(context, listeFluxAMettreAJour, listView);

	}
}
