package fr.smardine.podcaster.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class SuperActivity extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onRestart() {
		super.onRestart();
		popUp("onRestart()");
	}

	/**
	 * Execute lorsque l'activite devient visible a l'utilisateur. La fonction onStart() est suivie de la fonction onResume().
	 */
	@Override
	public void onStart() {
		super.onStart();
		popUp("onStart()");

	}

	/**
	 * detruit l'activity
	 */
	@Override
	public void onDestroy() {
		popUp("OnDestroy");
		super.onDestroy();

	}

	@Override
	public void onStop() {
		super.onStop();
		popUp("onStop");
	}

	/**
	 * La fonction onPause() est suivie : - d'un onResume() si l'activite passe a nouveau en premier plan - d'un onStop() si elle devient
	 * invisible a l'utilisateur L'execution de la fonction onPause() doit etre rapide, car la prochaine activite ne demarrera pas tant que
	 * l'execution de la fonction onPause() n'est pas terminee.
	 */
	@Override
	public void onPause() {
		super.onPause();

	}

	@Override
	public void onResume() {
		super.onResume();

	}

	public void termineActivity() {
		finish();
	}

	/**
	 * @param message
	 */
	public void popUp(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

	// /**
	// * A dummy fragment representing a section of the app, but that simply displays dummy text.
	// */
	// public static class ListeFluxSectionFragment extends Fragment {
	// /**
	// * The fragment argument representing the section number for this fragment.
	// */
	// public static final String ARG_SECTION_NUMBER = "section_number";
	// public static final String SELECTED_FLUX_ITEM = "selected_flux_item";
	// // public static MlListeFlux listeFlux = null;
	// public static MlFlux fluxSelectionne;
	// public static ActionBar actionBar;
	// public static Context context;
	// private static EpisodeListAdapter adptEpisode;
	//
	// public ListeFluxSectionFragment() {
	// }
	//
	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
	//
	// int numeroDeTabActuel = getArguments().getInt(ARG_SECTION_NUMBER);
	//
	// switch (numeroDeTabActuel) {
	// case 1:
	//
	// View v = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.activity_tab1, null);
	// final ListView listView = (ListView) v.findViewById(R.id.listViewTab1);
	//
	// ImageButton boutonAjouteFlux = (ImageButton) v.findViewById(R.id.imageButton3);
	// ImageButton boutonMajListeFlux = (ImageButton) v.findViewById(R.id.imageButton2);
	// View viewFluxListItem = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.fluxlistitem, null);
	//
	// boutonAjouteFlux.setOnClickListener(new ButtonAjoutFluxClickListener(this.getActivity(), viewFluxListItem, listView));
	//
	// final AccesTableFlux tableFlux = new AccesTableFlux(context);
	//
	// FluxListAdapter adpt = new FluxListAdapter(getActivity(), tableFlux.getListeDesFlux());
	//
	// // parameter l'adapter sur la listview
	// listView.setAdapter(adpt);
	//
	// listView.setOnItemClickListener(new OnItemClickListener() {
	//
	// @Override
	// public void onItemClick(AdapterView<?> p_adapterView, View p_view, int p_position, long arg3) {
	// MlFlux leFluxClique = (MlFlux) p_adapterView.getItemAtPosition(p_position);
	// fluxSelectionne = leFluxClique;
	// // En realite le numero de tab est en base 0
	// // si on a 3 tab, la deuxieme aura le numero 1
	// // 0,1,2
	// actionBar.setSelectedNavigationItem(1);
	//
	// }
	//
	// });
	//
	// boutonMajListeFlux.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	//
	// MajFluxProgressDialog majFlux = new MajFluxProgressDialog();
	// majFlux.synchroMajFluxProgressDialog(getActivity(), tableFlux.getListeDesFlux(), listView);
	//
	// }
	// });
	//
	// listView.setOnItemLongClickListener(new OnItemLongClickListener() {
	//
	// @Override
	// public boolean onItemLongClick(final AdapterView<?> p_adapterView, View p_view, final int p_position, long arg3) {
	// final MlFlux leFluxClique = (MlFlux) p_adapterView.getItemAtPosition(p_position);
	// fluxSelectionne = leFluxClique;
	// PopupMenu popup = new PopupMenu(context, p_view);
	// MenuInflater inflater = popup.getMenuInflater();
	// inflater.inflate(R.menu.menu_long_click_flux, popup.getMenu());
	// popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {
	//
	// @Override
	// public boolean onMenuItemClick(MenuItem p_item) {
	// switch (p_item.getItemId()) {
	// case R.id.menu_suppr_flux:
	// AccesTableFlux tableFlux = new AccesTableFlux(context);
	// tableFlux.deleteFluxEtEpisode(fluxSelectionne);
	// FluxListAdapter adpt = (FluxListAdapter) p_adapterView.getAdapter();
	// adpt.lstFlux.remove(leFluxClique);
	// adpt.notifyDataSetChanged();
	//
	// return true;
	// case R.id.menu_maj_flux:
	// MajFluxProgressDialog majFlux = new MajFluxProgressDialog();
	// List<MlFlux> lst = new ArrayList<MlFlux>();
	// lst.add(leFluxClique);
	// majFlux.synchroMajFluxProgressDialog(getActivity(), lst, listView);
	// return true;
	// default:
	// return false;
	// }
	// }
	// });
	// popup.show();
	// return true;
	// }
	// });
	//
	// return v;
	// case 2:
	// View v2 = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.activity_liste_episodes, null);
	// ListView listViewEpisode = (ListView) v2.findViewById(R.id.listViewTabListeEpisode);
	//
	// ImageButton boutonMajFlux = (ImageButton) v2.findViewById(R.id.imageButtonEpisode3);
	// View viewEpisodeListeItem = LayoutInflater.from(getActivity().getApplicationContext()).inflate(
	// R.layout.episodelistitem, null);
	//
	// MlListeEpisode listeEpisode = new AccesTableFlux(context).getListeDesFlux().GetAllEpisode();
	// MlFlux fluxSelectionne = (MlFlux) getArguments().getSerializable(SELECTED_FLUX_ITEM);
	// if (adptEpisode == null) {
	// adptEpisode = new EpisodeListAdapter(getActivity(), listeEpisode);
	// }
	//
	// if (fluxSelectionne == null) {
	// adptEpisode.getFilter().filter(" ");
	// // listeEpisode = new AccesTableFlux(context).getListeDesFlux().GetAllEpisode();
	// } else {
	// adptEpisode.getFilter().filter(fluxSelectionne.getTitre());
	// // listeEpisode = new AccesTableEpisode(context).getListeDesEpisodeParIdFlux(fluxSelectionne);
	// }
	//
	// // parameter l'adapter sur la listview
	// listViewEpisode.setAdapter(adptEpisode);
	//
	// boutonMajFlux.setOnClickListener(new ButtonMajFluxClickListener(this.getActivity(), viewEpisodeListeItem,
	// listViewEpisode));
	//
	// return v2;
	//
	// default:
	// throw new RuntimeException("Il y a une erreur de programmation, le numero d'onglet n'est pas gere");
	// }
	//
	// // TextView textView = new TextView(getActivity());
	// // textView.setGravity(Gravity.CENTER);
	// // textView.setText(Integer.toString(getArguments().getInt(
	// // ARG_SECTION_NUMBER)));
	// // int numeroDeTab = getArguments().getInt(ARG_SECTION_NUMBER);
	// // return textView;
	// }
	// }

}
