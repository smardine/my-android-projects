package fr.smardine.podcaster.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.adapter.EpisodeListAdapter;
import fr.smardine.podcaster.helper.SerialisableHelper;
import fr.smardine.podcaster.mdl.MlEpisode;
import fr.smardine.podcaster.mdl.MlFlux;

public class FluxActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_liste_episodes);

		View v = LayoutInflater.from(this.getApplicationContext()).inflate(
				R.layout.activity_liste_episodes, null);

		ListView listView = (ListView) v
				.findViewById(R.id.listViewTabListeEpisode);

		MlFlux unFlux = recupereMlFluxfromPreviousActivity();

		EpisodeListAdapter adpt = new EpisodeListAdapter(this,
				unFlux.getListeEpisode());
		// parameter l'adapter sur la listview
		listView.setAdapter(adpt);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> p_adapterView, View p_view,
					int p_position, long arg3) {
				MlEpisode unEpisodeCLique = (MlEpisode) p_adapterView
						.getItemAtPosition(p_position);
//				System.out.println(((MlEpisode) p_adapterView
//						.getItemAtPosition(p_position)).getTitre());

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_tab1, menu);
		return true;
	}

	public MlFlux recupereMlFluxfromPreviousActivity() {
		MlFlux flux = null;
		byte[] extra = getIntent().getByteArrayExtra(
				MlFlux.class.getCanonicalName());
		if (extra != null) {
			Object o = SerialisableHelper.deserializeObject(extra);
			if (o instanceof MlFlux) {
				flux = (MlFlux) o;
			}
		}

		return flux;
	}

}
