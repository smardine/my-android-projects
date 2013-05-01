/**
 * 
 */
package fr.smardine.monvetcarnet.listener;

import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * @author sims
 */
public class CbCheckChangeListenerVaccin implements OnCheckedChangeListener {

	private static int globalCount;
	private final Button button;

	/**
	 * 
	 */
	public CbCheckChangeListenerVaccin(Button butonOk) {
		this.button = butonOk;
	}

	/*
	 * (non-Javadoc)
	 * @see android.widget.CompoundButton.OnCheckedChangeListener#onCheckedChanged(android.widget.CompoundButton, boolean)
	 */
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			globalCount++;
			button.setEnabled(true);
		} else {
			globalCount--;
			if (globalCount <= 0) {
				button.setEnabled(false);
			}

		}

	}

}
