package fr.smardine.monvetcarnet.fragment;

import java.util.Calendar;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import fr.smardine.monvetcarnet.R;
import fr.smardine.monvetcarnet.mdl.MlCarnet;
import fr.smardine.monvetcarnet.mdl.MlIdentification;

/**
 * Fragment "Couverture"
 * @author sims
 */
public class CouvertureFragment extends Fragment {
	public Context context;
	private TextView tvNomBestiole;

	private TextView tvAgeBestiole;
	private MlCarnet carnetParent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v1 = inflater.inflate(R.layout.activity_couverture, container, false);
		tvNomBestiole = (TextView) v1.findViewById(R.id.tvNomBestiole);
		tvAgeBestiole = (TextView) v1.findViewById(R.id.tvAgeBestiole);
		this.tvNomBestiole.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showPopup(v);
			}
		});
		return v1;

	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState != null) {
			Toast.makeText(getActivity(), "Fragment Couverture recréé", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	public void showPopup(View v) {
		PopupMenu popup = new PopupMenu(this.getActivity(), v);
		MenuInflater inflater = popup.getMenuInflater();
		inflater.inflate(R.menu.main, popup.getMenu());
		popup.show();
	}

	public void metAjourCouverture(MlCarnet p_carnetParent) {
		if (p_carnetParent != null) {
			this.carnetParent = p_carnetParent;
			MlIdentification identification = this.carnetParent.getIdentificationAnimal();
			this.tvNomBestiole.setText(identification.getNomAnimal());

			if (identification.getDateNaissance() != null) {
				Calendar calendar = Calendar.getInstance();
				Calendar calendarAnimal = Calendar.getInstance();
				calendarAnimal.setTime(identification.getDateNaissance());
				int anneDuJour = calendar.get(Calendar.YEAR);
				int anneDateNaissAnimal = calendarAnimal.get(Calendar.YEAR);

				this.tvAgeBestiole.setText("" + (anneDuJour - anneDateNaissAnimal));
			}
		}

	}
}
