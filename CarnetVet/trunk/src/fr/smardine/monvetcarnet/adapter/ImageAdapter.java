package fr.smardine.monvetcarnet.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import fr.smardine.monvetcarnet.mdl.EnPictoMaladie;

@SuppressWarnings("deprecation")
public class ImageAdapter extends BaseAdapter {
	/** The parent context */
	private final Context myContext;

	/**
	 * All images to be displayed. Put some images to project-folder: '/res/drawable/uvw.xyz' .
	 */
	private final int[] myImageIds = { EnPictoMaladie.Oreille.getRessourcePicto(), EnPictoMaladie.Yeux.getRessourcePicto(),
			EnPictoMaladie.Nez.getRessourcePicto(), EnPictoMaladie.Patte.getRessourcePicto() };

	/** Simple Constructor saving the 'parent' context. */
	public ImageAdapter(Context c) {
		this.myContext = c;
	}

	/** Returns the amount of images we have defined. */
	@Override
	public int getCount() {
		return this.myImageIds.length;
	}

	/* Use the array-Positions as unique IDs */
	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public int getRessourceImage(int position) {
		return this.myImageIds[position];
	}

	/**
	 * Returns a new ImageView to be displayed, depending on the position passed.
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView i = new ImageView(this.myContext);

		i.setImageResource(this.myImageIds[position]);
		/* Image should be scaled as width/height are set. */
		i.setScaleType(ImageView.ScaleType.FIT_XY);
		/* Set the Width/Height of the ImageView. */
		i.setLayoutParams(new Gallery.LayoutParams(150, 150));
		return i;
	}

	/**
	 * Returns the size (0.0f to 1.0f) of the views depending on the 'offset' to the center.
	 */
	public float getScale(boolean focused, int offset) {
		/* Formula: 1 / (2 ^ offset) */
		return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
	}
}
