package fr.smardine.monvetcarnet.adapter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class GalleryItemSelectedListener implements OnItemSelectedListener {

	private final ImageAdapter adapter;
	private int resourceDeLimage;

	public GalleryItemSelectedListener(ImageAdapter adapter) {
		this.adapter = adapter;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		resourceDeLimage = adapter.getRessourceImage(arg2);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {

	}

	public int getSelectedResource() {
		return resourceDeLimage;
	}

}
