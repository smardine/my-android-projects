package fr.smardine.monvetcarnet.fragment;

import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import fr.smardine.monvetcarnet.database.accestable.AccesTableIdentification;
import fr.smardine.monvetcarnet.helper.AndroidHelper;
import fr.smardine.monvetcarnet.helper.DateHelper;
import fr.smardine.monvetcarnet.mdl.MlIdentification;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

	private DatePicker datePicker;
	private TextView dateSpinner;
	private MlIdentification identification;
	private AccesTableIdentification tableIdentification;

	public DatePickerFragment() {

	}

	public void setButtonSpinner(TextView p_button) {
		this.dateSpinner = p_button;
	}

	public void setMlIdentification(MlIdentification p_identification) {
		this.identification = p_identification;
	}

	public void setTableIdentifiaction(AccesTableIdentification p_tableIdentification) {
		this.tableIdentification = p_tableIdentification;
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
		Date dateChoisie = AndroidHelper.getDateFromDatePicker(this.datePicker);
		this.dateSpinner.setText(DateHelper.ddMMMyyyy(dateChoisie));
		this.identification.setDateNaissance(dateChoisie);
		this.tableIdentification.majIdenificationEnBase(identification);

	}

	public Date recupereDate() {
		return AndroidHelper.getDateFromDatePicker(this.datePicker);
	}
}