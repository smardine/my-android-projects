package fr.smardine.monvetcarnet.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import fr.smardine.monvetcarnet.R;

public class SpinnerIdentificationSaisieAdapter extends ArrayAdapter<Object> {

	private static String[] labelMenu = new String[] { "Sexe", "Date de naissance", "Race", "Robe", "Signes distinctifs", "N° de puce",
			"N° de tatouage" };
	private final Context context;

	public SpinnerIdentificationSaisieAdapter(Context context, int textViewResourceId) {
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
		View row = inflater.inflate(R.layout.spinner_text_only, parent, false);
		TextView label = (TextView) row.findViewById(R.id.textViewSpinnerTextOnly);
		label.setText(labelMenu[position]);
		return row;
	}

}
