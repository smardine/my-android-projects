package fr.smardine.podcaster.listener;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import fr.smardine.podcaster.R;

public class ButtonAjoutFluxClickListener implements OnClickListener {

	private final Context context;
	// private final View view;
	private ListView listView;

	// private Handler handle;

	public ButtonAjoutFluxClickListener(Context baseContext, View p_view, ListView p_listView) {
		this.context = baseContext;
		// this.view = p_view;
		this.listView = p_listView;

	}

	@Override
	public void onClick(View p_view) {
		// AlertDialogFactory adFactory = new AlertDialogFactory(context);
		Builder ad = new AlertDialog.Builder(context);
		final List<String> urlsChecked = new ArrayList<String>();
		ad.setTitle("Ajout de flux");
		ad.setIcon(R.drawable.ad_attention);
		final CharSequence[] listeDurls = new CharSequence[] { "http://www.rtl.fr/podcast/laurent-gerra.xml",
				"http://www.europe1.fr/podcasts/revue-de-presque.xml",
				"http://www.jeuxvideo.com/rss/itunes-le-cliq.xml",// };
				"http://rss.feedsportal.com/c/808/f/413811/index.rss", "http://rss.feedsportal.com/c/808/f/413810/index.rss",
				"http://radiofrance-podcast.net/podcast09/rss_11549.xml", "http://www.sesam-vitale.fr/__xml/rss.xml" };
		ad.setMultiChoiceItems(listeDurls, new boolean[7], new OnMultiChoiceClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				urlsChecked.add(listeDurls[which].toString());
			}
		});
		// ad.setPositiveButton("ok", ouinon)
		// AlertDialogOuiNonClickListener ouinonClickListener = new AlertDialogOuiNonClickListener(this.context, this.listView,
		// urlsChecked);
		// ad.setPositiveButton("Ok", ouinonClickListener);
		// ad.setNegativeButton("Annuler", null);
		// // ad.setView(this.view);
		// ad.show();

	}
}
