package fr.smardine.monvetcarnet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import fr.smardine.monvetcarnet.R;

public class VaccinsAdapter extends ArrayAdapter<Object> {

	private static String[] labelMenu = new String[] { "mars 2008", "fevrier 2009", "juin 2009", "juillet 2010", "aout 2011",
			"decembre 2012" };
	private final int[] images = new int[] { R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.collections_cloud,
			R.drawable.collections_go_to_today, R.drawable.collections_labels, R.drawable.collections_new_label };
	private final Context context;

	public VaccinsAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId, labelMenu);
		this.context = context;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v;
		if (convertView == null) {
			v = LayoutInflater.from(context).inflate(R.layout.vaccin, null);
			v.setLayoutParams(new GridView.LayoutParams(100, 100));

			TextView tvDate = (TextView) v.findViewById(R.id.tvDateValue);
			tvDate.setText(labelMenu[position]);

			ImageView imageViewVaccin = (ImageView) v.findViewById(R.id.ivLogoVaccin);
			imageViewVaccin.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageViewVaccin.setPadding(8, 8, 8, 8);
			imageViewVaccin.setImageResource(images[position]);

			ImageView imageViewVermifuge = (ImageView) v.findViewById(R.id.ivLogoVermifuge);
			imageViewVermifuge.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageViewVermifuge.setPadding(8, 8, 8, 8);
			imageViewVermifuge.setImageResource(images[position]);

		} else {
			v = convertView;
		}

		return v;

	}
}
