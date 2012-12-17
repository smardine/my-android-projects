package fr.smardine.podcaster.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class SuperActivity extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onRestart() {
		super.onRestart();
		popUp("onRestart()-Page3");
	}

	/**
	 * Ex�cut� lorsque l'activit� devient visible � l'utilisateur. La fonction
	 * onStart() est suivie de la fonction onResume().
	 */
	@Override
	public void onStart() {
		super.onStart();
		popUp("onStart()-Page3");

	}

	/**
	 * detruit l'activity
	 */
	@Override
	public void onDestroy() {
		popUp("OnDestroy-Page1");
		super.onDestroy();

	}

	@Override
	public void onStop() {
		super.onStop();
		popUp("onStop-Page1");
	}

	/**
	 * La fonction onPause() est suivie : - d'un onResume() si l'activit� passe
	 * � nouveau en premier plan - d'un onStop() si elle devient invisible �
	 * l'utilisateur L'ex�cution de la fonction onPause() doit �tre rapide, car
	 * la prochaine activit� ne d�marrera pas tant que l'ex�cution de la
	 * fonction onPause() n'est pas termin�e.
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
		// Toast.makeText(this, message, 1).show();
	}

}
