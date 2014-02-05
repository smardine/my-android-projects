package fr.smardine.podcaster.listener;

import android.app.ActionBar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import fr.smardine.podcaster.mdl.MlFlux;

public class ItemClickListenerTabListeFLux implements OnItemClickListener {

	private MlFlux fluxSelectionne;
	private final ActionBar actionBar;

	public ItemClickListenerTabListeFLux(ActionBar p_actionBar) {

		this.actionBar = p_actionBar;
	}

	@Override
	public void onItemClick(AdapterView<?> p_adapterVueParente,
			View pvueParente, int p_position, long arg3) {
		MlFlux leFluxClique = (MlFlux) p_adapterVueParente
				.getItemAtPosition(p_position);
		this.fluxSelectionne = leFluxClique;
		// En realite le numero de tab est en base 0
		// si on a 3 tab, la deuxieme aura le numero 1
		// 0,1,2
		actionBar.setSelectedNavigationItem(1);
	}

	public MlFlux getFluxSelectionne() {
		return fluxSelectionne;
	}

}
