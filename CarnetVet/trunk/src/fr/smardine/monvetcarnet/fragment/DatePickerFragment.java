package fr.smardine.monvetcarnet.fragment;

import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import fr.smardine.monvetcarnet.helper.AndroidHelper;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

	private DatePicker datePicker;
	private Button dateSpinner;

	public DatePickerFragment() {

	}

	public void setButtonSpinner(Button p_button) {
		this.dateSpinner = p_button;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	@Override
	public void onDateSet(DatePicker view, int year, int month, int day) {

		this.datePicker = view;
		this.dateSpinner.setText(AndroidHelper.getDateFromDatePicker(this.datePicker).toLocaleString());

	}

	public Date recupereDate() {
		return AndroidHelper.getDateFromDatePicker(this.datePicker);
	}
}