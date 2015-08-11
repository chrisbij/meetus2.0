package vue;

import com.example.meetus.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class CreateParty extends Activity {

	
	private int annee;
	private int mois;
	private int jour;
	private int hour;
	private int minute;
	
	public EditText nameParty;
	public EditText nameOrganizer;
	public EditText adressParty;
	public EditText cpParty;
	public EditText cityParty;
	public EditText dateParty;
	public Button datePickerParty;
	public EditText heureParty;
	public Button heurePickerParty;
	public String resultDatePickerParty;
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.create_party);
		
		nameParty = (EditText)findViewById(R.id.nameParty);
		adressParty = (EditText)findViewById(R.id.adressParty);
		cpParty = (EditText)findViewById(R.id.cpParty);
		cityParty = (EditText)findViewById(R.id.cityParty);
		dateParty = (EditText)findViewById(R.id.dateParty);
		datePickerParty = (Button)findViewById(R.id.datePickerButton);
		heureParty = (EditText)findViewById(R.id.heureParty);
		heurePickerParty = (Button)findViewById(R.id.heurePickerButton);
		

		
		datePickerParty.setOnClickListener(choixJour);
		heurePickerParty.setOnClickListener(choixHeure);
	
	}
	
	
	
public OnClickListener choixJour = new OnClickListener() {
		
		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			showDialog(0);
		}
	};
	
public OnClickListener choixHeure = new OnClickListener() {
		
		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			showDialog(1);
		}
	};
	
	public Dialog onCreateDialog(int id){
		switch (id) {
		case 0:
			return new DatePickerDialog(this, maDatePicker, annee, mois, jour);
		case 1:
			return new TimePickerDialog(this, maTimePicker, hour, minute, false);
		}
		return null;
	}

	
	
	public DatePickerDialog.OnDateSetListener maDatePicker = new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			annee=year;
			mois=monthOfYear;
			jour=dayOfMonth;
			
			dateParty.setText(jour+"/"+mois+"/"+annee);
		}
	};
		
	
	public TimePickerDialog.OnTimeSetListener maTimePicker = new TimePickerDialog.OnTimeSetListener() {
		
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			
			hour = hourOfDay;
			CreateParty.this.minute = minute;
			
			heureParty.setText("\u00e0 " +hour+"h"+CreateParty.this.minute);
		}
	};
	
}
