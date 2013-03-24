package fr.smardine.monvetcarnet.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import fr.smardine.monvetcarnet.R;

public class SpinerAdapter extends ArrayAdapter<Object> {

	private static String[] labelMenu = new String[] { "Mon vet'carnet", "Identification", "Vaccins", "Poids", "Maladie", "Utiles" };
	private final int[] images = new int[] { R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.collections_cloud,
			R.drawable.collections_go_to_today, R.drawable.collections_labels, R.drawable.collections_new_label };
	private final Context context;

	public SpinerAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId, labelMenu);
		this.context = context;

	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	private View getCustomView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View row = inflater.inflate(R.layout.spinner, parent, false);
		TextView label = (TextView) row.findViewById(R.id.textView1);
		label.setText(labelMenu[position]);

		ImageView icon = (ImageView) row.findViewById(R.id.imageView1);
		icon.setImageResource(images[position]);

		return row;
	}
}
