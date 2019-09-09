package vue;

import com.example.meetus.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

import controller.CreatePartyController;

public class CreateParty extends Activity {



	private int annee;
	private int mois;
	private int jour;
	private int hour;
	private int minute;

    public int idErrorMessage;

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

	public Button selectCatParty;

	public Button confirm;

    CreatePartyController createPartyController;
	
	
	
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
		
		selectCatParty = (Button)findViewById(R.id.buttonSelectCat);
		
		datePickerParty.setOnClickListener(choixJour);
		heurePickerParty.setOnClickListener(choixHeure);

		confirm = (Button)findViewById(R.id.confirmButton);

		confirm.setOnClickListener(createActivite);

        createPartyController = new CreatePartyController();
	}
	
	

	public OnClickListener createActivite = new OnClickListener() {
		@Override
		public void onClick(View view) {

            idErrorMessage = 0;

            if(!nameParty.getText().toString().equals("")){
                createPartyController.setLibelleActivite(nameParty.getText().toString());
            }else{
                Toast erreur = Toast.makeText(getApplicationContext(), "Veuillez renseigner un titre pour votre activité", Toast.LENGTH_SHORT);
                erreur.show();
                return;
            }

            if(!adressParty.getText().toString().equals("")){
                createPartyController.setAdressActivite(adressParty.getText().toString());
            }else{
                Toast erreur = Toast.makeText(getApplicationContext(), "Veuillez saisir une adresse pour votre activité", Toast.LENGTH_SHORT);
                erreur.show();
                return;
            }

           if(!cpParty.getText().toString().equals("")){
               createPartyController.setCpActivite(cpParty.getText().toString());
            }else{
                Toast erreur = Toast.makeText(getApplicationContext(), "Veuillez saisir un code postale", Toast.LENGTH_SHORT);
                erreur.show();
                return;
            }

            if(!cityParty.getText().toString().equals("")){
                createPartyController.setVilleActivite(cityParty.getText().toString());
            }else{
                Toast erreur = Toast.makeText(getApplicationContext(), "Veuillez saisir une ville", Toast.LENGTH_SHORT);
                erreur.show();
                return;
            }

            if(!dateParty.getText().toString().equals("")){
                createPartyController.setDateActivite(dateParty.getText().toString());
            }else{
                Toast erreur = Toast.makeText(getApplicationContext(), "Veuillez saisir une date", Toast.LENGTH_SHORT);
                erreur.show();
                return;
            }

            if(!heureParty.getText().toString().equals("")){
                createPartyController.setHeureActivite(heureParty.getText().toString());
            }else{
                Toast erreur = Toast.makeText(getApplicationContext(), "Veuillez saisir une heure", Toast.LENGTH_SHORT);
                erreur.show();
                return;
            }

           createPartyController.requestCreateActivite();



            switch (idErrorMessage){
                case -1:
                    Toast erreurCo = Toast.makeText(getApplicationContext(), "Erreur lors de la connexion au serveur", Toast.LENGTH_SHORT);
                    erreurCo.show();
                    break;
                case -2:
                    Toast erreur = Toast.makeText(getApplicationContext(), "Echec. Une erreur est survenu", Toast.LENGTH_SHORT);
                    erreur.show();
                    break;
                case 1:
                    //finish();
                    break;
                case 0:
                    Toast succes = Toast.makeText(getApplicationContext(), "Tout marche", Toast.LENGTH_SHORT);
                    succes.show();
                    break;
            }
		}
	};

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
			
			dateParty.setText(annee+"-"+mois+"-"+jour);
		}
	};
		
	
	public TimePickerDialog.OnTimeSetListener maTimePicker = new TimePickerDialog.OnTimeSetListener() {
		
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			
			hour = hourOfDay;
			CreateParty.this.minute = minute;
			
			heureParty.setText("" +hour+"h"+CreateParty.this.minute);
		}
	};
	
}
