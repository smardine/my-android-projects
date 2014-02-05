package fr.smardine.podcaster.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;
import fr.smardine.podcaster.R;
import fr.smardine.podcaster.adapter.FluxListAdapter;
import fr.smardine.podcaster.database.accestable.AccesTableFlux;
import fr.smardine.podcaster.listener.ButtonAjoutFluxClickListener;
import fr.smardine.podcaster.mdl.MlFlux;
import fr.smardine.podcaster.metier.progressdialog.MajFluxProgressDialog;

/**
 * A dummy fragment representing a section of the app, but that simply displays dummy text.
 */
public class ListeFluxSectionFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";
	public static final String SELECTED_FLUX_ITEM = "selected_flux_item";

	public static MlFlux fluxSelectionne;
	public ActionBar actionBar;
	public Context context;
	private ListView listView;
	private ImageButton boutonAjouteFlux;
	private ImageButton boutonMajListeFlux;

	public ListeFluxSectionFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

		View v = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.activity_tab1, null);

		listView = (ListView) v.findViewById(R.id.listViewTab1);

		boutonAjouteFlux = (ImageButton) v.findViewById(R.id.imageButton3);
		boutonMajListeFlux = (ImageButton) v.findViewById(R.id.imageButton2);
		View viewFluxListItem = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.fluxlistitem, null);

		boutonAjouteFlux.setOnClickListener(new ButtonAjoutFluxClickListener(this.getActivity(), viewFluxListItem, listView));

		// final AccesTableFlux tableFlux = new AccesTableFlux(context);
		//
		// FluxListAdapter adpt = new FluxListAdapter(getActivity(), tableFlux.getListeDesFlux());
		//
		// // parameter l'adapter sur la listview
		// listView.setAdapter(adpt);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> p_adapterView, View p_view, int p_position, long arg3) {
				MlFlux leFluxClique = (MlFlux) p_adapterView.getItemAtPosition(p_position);
				fluxSelectionne = leFluxClique;
				// En realite le numero de tab est en base 0
				// si on a 3 tab, la deuxieme aura le numero 1
				// 0,1,2
				actionBar.setSelectedNavigationItem(1);

			}

		});

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(final AdapterView<?> p_adapterView, View p_view, final int p_position, long arg3) {
				final MlFlux leFluxClique = (MlFlux) p_adapterView.getItemAtPosition(p_position);
				fluxSelectionne = leFluxClique;
				PopupMenu popup = new PopupMenu(context, p_view);
				MenuInflater inflater = popup.getMenuInflater();
				inflater.inflate(R.menu.menu_long_click_flux, popup.getMenu());
				popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem p_item) {
						switch (p_item.getItemId()) {
							case R.id.menu_suppr_flux:
								AccesTableFlux tableFlux = new AccesTableFlux(context);
								tableFlux.deleteFluxEtEpisode(leFluxClique);
								FluxListAdapter adpt = (FluxListAdapter) p_adapterView.getAdapter();
								adpt.lstFlux.remove(leFluxClique);
								adpt.notifyDataSetChanged();

								return true;
							case R.id.menu_maj_flux:
								MajFluxProgressDialog majFlux = new MajFluxProgressDialog();
								List<MlFlux> lst = new ArrayList<MlFlux>();
								lst.add(leFluxClique);
								majFlux.synchroMajFluxProgressDialog(getActivity(), lst, listView);
								return true;
							default:
								return false;
						}
					}
				});
				popup.show();
				return true;
			}
		});

		return v;

	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState != null) {
			Toast.makeText(getActivity(), "Fragment Episode recree", Toast.LENGTH_LONG).show();
		}

		final AccesTableFlux tableFlux = new AccesTableFlux(context);

		FluxListAdapter adpt = new FluxListAdapter(getActivity(), tableFlux.getListeDesFlux());

		// parameter l'adapter sur la listview
		listView.setAdapter(adpt);

		boutonMajListeFlux.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MajFluxProgressDialog majFlux = new MajFluxProgressDialog();
				majFlux.synchroMajFluxProgressDialog(getActivity(), tableFlux.getListeDesFlux(), listView);
			}
		});
	}
	// TextView textView = new TextView(getActivity());
	// textView.setGravity(Gravity.CENTER);
	// textView.setText(Integer.toString(getArguments().getInt(
	// ARG_SECTION_NUMBER)));
	// int numeroDeTab = getArguments().getInt(ARG_SECTION_NUMBER);
	// return textView;
}
