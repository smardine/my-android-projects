package fr.smardine.monvetcarnet.fragment;

import java.util.Calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.smardine.monvetcarnet.MainActivity;
import fr.smardine.monvetcarnet.R;
import fr.smardine.monvetcarnet.alertdialog.AlertDialogFactory;
import fr.smardine.monvetcarnet.mdl.MlCarnet;
import fr.smardine.monvetcarnet.mdl.MlIdentification;

/**
 * Fragment "Couverture"
 * @author sims
 */
public class CouvertureFragment extends SuperFragment {

	private TextView tvNomBestiole;

	private TextView tvAgeBestiole;
	private MlCarnet carnetParent;

	public CouvertureFragment() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View v1 = inflater.inflate(R.layout.activity_couverture, container, false);
		tvNomBestiole = (TextView) v1.findViewById(R.id.tvNomBestiole);
		tvAgeBestiole = (TextView) v1.findViewById(R.id.tvAgeBestiole);

		context = this.getActivity();
		this.tvNomBestiole.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialogFactory.creerEtAfficherSaisieNomAnimal(context, carnetParent, MainActivity.fragmentCouverture,
						MainActivity.fragmentIdentification);
			}
		});

		return v1;

	}

	// public void showPopup(View v) {
	// PopupMenu popup = new PopupMenu(this.getActivity(), v);
	// MenuInflater inflater = popup.getMenuInflater();
	// inflater.inflate(R.menu.main, popup.getMenu());
	// popup.show();
	// }

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
