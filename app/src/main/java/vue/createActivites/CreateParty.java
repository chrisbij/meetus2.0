package vue.createActivites;

import com.example.meetus.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;


import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import controller.CreatePartyController;
import controller.UploadFile;
import controller.VilleDataSource;
import model.Ville;

public class CreateParty extends Activity {



	private int annee;
	private int mois;
	private int jour;
	private int hour;
	private int minute;

    public String resultChoixActivite;

    public int idErrorMessage;

	AutoCompleteTextView pays;
	public EditText nameParty;
	public EditText nameOrganizer;
	public EditText adressParty;
	public EditText cpParty;
	public AutoCompleteTextView cityParty;
	public EditText dateParty;
	public EditText nomPhoto;
	public Button dateDebPickerParty;
	public Button heureDebPickerParty;
    public Button dateFinPickerParty;
    public Button heureFinPickerParty;
	public Button choixPhoto;
	public String resultDatePickerParty;

	public UploadFile uploadFile = new UploadFile();

	String selectedPath, idActivite;

	String[] countries = {"France", "United States"};

	public Button selectCatParty;

	public Button confirm;

	Activity activity;

    CreatePartyController createPartyController;
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	 activity = this;

		VilleDataSource ville = new VilleDataSource(this);

		try {
			ville.open();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		List<Ville> values = ville.getVille();

		ville.close();

		setContentView(R.layout.create_party);

		pays = (AutoCompleteTextView)findViewById(R.id.paysParty);
		nameParty = (EditText)findViewById(R.id.nameParty);
		adressParty = (EditText)findViewById(R.id.adressParty);
		cpParty = (EditText)findViewById(R.id.cpParty);
		cityParty = (AutoCompleteTextView)findViewById(R.id.cityParty);
		//dateParty = (EditText)findViewById(R.id.datePartyDeb);
		dateDebPickerParty = (Button)findViewById(R.id.datePickerButtonDeb);
		heureDebPickerParty = (Button)findViewById(R.id.heurePickerButtonDeb);
        dateFinPickerParty = (Button)findViewById(R.id.datePickerButtonFin);
        heureFinPickerParty = (Button)findViewById(R.id.heurePickerButtonFin);
		choixPhoto = (Button)findViewById(R.id.buttonChoixPhoto);
		nomPhoto = (EditText)findViewById(R.id.nomChoixPhoto);

		selectCatParty = (Button)findViewById(R.id.buttonSelectCat);



        ArrayAdapter<Ville> adapter = new ArrayAdapter<Ville>(this, R.layout.dropdown, values);

		cityParty.setAdapter(adapter);
		cityParty.setThreshold(1);

		dateDebPickerParty.setOnClickListener(choixJour);
		heureDebPickerParty.setOnClickListener(choixHeure);

        dateFinPickerParty.setOnClickListener(choixJourFin);
        heureFinPickerParty.setOnClickListener(choixHeureFin);

		confirm = (Button)findViewById(R.id.confirmButton);

		confirm.setOnClickListener(createActivite);

        try {
            createPartyController = new CreatePartyController(activity);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        selectCatParty.setOnClickListener(selectCatPartyListener);

		choixPhoto.setOnClickListener(choisirPhoto);

        setcurrentDate();
	}


	public OnClickListener choisirPhoto = new OnClickListener() {
		@Override
		public void onClick(View view) {
			openGalerry(2);
		}
	};

	public void openGalerry(int req_code){
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select file to upload "), req_code);
	}


	protected void onActivityResult(int requestCode,int resultCode, Intent data){
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                resultChoixActivite = data.getStringExtra("RESULT");
            }
        }

		if(requestCode == 2){
			if (resultCode == RESULT_OK){
				Uri selectedImageUri = data.getData();
				selectedPath = getPath(selectedImageUri);
				nomPhoto.setText(selectedPath);
				Toast.makeText(getApplicationContext(), selectedPath, Toast.LENGTH_SHORT).show();
			}
		}

    }

	public String getPath(Uri uri){
		String[] projection = {MediaStore.Images.Media.DATA};

		Cursor cursor = managedQuery(uri, projection, null, null, null);

		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

		cursor.moveToFirst();

		return  cursor.getString(column_index);
	}


    public OnClickListener selectCatPartyListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), ChoixActivites.class);
            startActivityForResult(intent, 1);
        }
    };


	public OnClickListener createActivite = new OnClickListener() {
		@Override
		public void onClick(View view) {


            createPartyController.setTypeActivite(resultChoixActivite);
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

            if(!dateDebPickerParty.getText().toString().equals("")){
                createPartyController.setDateActivite(dateDebPickerParty.getText().toString());
            }else{
                Toast erreur = Toast.makeText(getApplicationContext(), "Veuillez saisir une date", Toast.LENGTH_SHORT);
                erreur.show();
                return;
            }

            if(!heureDebPickerParty.getText().toString().equals("")){
                createPartyController.setHeureActivite(heureDebPickerParty.getText().toString());
            }else{
                Toast erreur = Toast.makeText(getApplicationContext(), "Veuillez saisir une heure", Toast.LENGTH_SHORT);
                erreur.show();
                return;
            }

			createPartyController.setPathFile(nomPhoto.getText().toString());

			String fileToServer = "http://meetus.noip.me/meetus/media/images/activites/" + selectedPath.substring(selectedPath.lastIndexOf("/")+1);

			createPartyController.setPathFile(fileToServer);

           createPartyController.requestCreateActivite();

			idActivite = createPartyController.getIdActivite();

			Log.e("problem", fileToServer);

			uploadFile.uploadFileToServer(nomPhoto.getText().toString(), idActivite);

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

    public OnClickListener choixJourFin = new OnClickListener() {

        @SuppressWarnings("deprecation")
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            showDialog(2);
        }
    };

    public OnClickListener choixHeureFin = new OnClickListener() {

        @SuppressWarnings("deprecation")
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            showDialog(3);
        }
    };
	
	public Dialog onCreateDialog(int id){
		switch (id) {
		case 0:
			return new DatePickerDialog(this, maDatePicker, annee, mois, jour);
		case 1:
			return new TimePickerDialog(this, maTimePicker, hour, minute, true);
        case 2:
            return new DatePickerDialog(this, maDatePickerFin, annee, mois, jour);
        case 3:
            return new TimePickerDialog(this, maTimePickerFin, hour, minute, true);
		}

		return null;
	}


/***************************************************************************************************/
/*************************************** Choix JOUR ************************************************/
/***************************************************************************************************/

	public DatePickerDialog.OnDateSetListener maDatePicker = new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub

            String moisDef;
            String jourDef;

			annee=year;
			mois=monthOfYear + 1;
			jour=dayOfMonth;

            if(mois < 10){
                moisDef = "0"+mois;
            }else{
                moisDef = ""+mois;
            }

            if(jour < 10){
                jourDef = "0"+jour;
            }else{
                jourDef = ""+jour;
            }

			dateDebPickerParty.setText(jourDef + "-" + moisDef + "-" + annee);
		}
	};
		
	
	public TimePickerDialog.OnTimeSetListener maTimePicker = new TimePickerDialog.OnTimeSetListener() {
		
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			String hourDef;
			String minuteDef;

			hour = hourOfDay;
			CreateParty.this.minute = minute;

			if(hourOfDay < 10){
				hourDef = "0"+hourOfDay;
			}else{
				hourDef = ""+hourOfDay;
			}

			if(minute < 10){
				minuteDef = "0"+minute;
			}else{
				minuteDef = ""+minute;
			}
			
			heureDebPickerParty.setText(hourDef + "h" + minuteDef);
		}
	};



    public DatePickerDialog.OnDateSetListener maDatePickerFin = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub

            String moisDef;
            String jourDef;

            annee=year;
            mois=monthOfYear + 1;
            jour=dayOfMonth;

            if(mois < 10){
                moisDef = "0"+mois;
            }else{
                moisDef = ""+mois;
            }

            if(jour < 10){
                jourDef = "0"+jour;
            }else{
                jourDef = ""+jour;
            }

            dateFinPickerParty.setText(jourDef+"-"+moisDef+"-"+annee);
        }
    };


    public TimePickerDialog.OnTimeSetListener maTimePickerFin = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // TODO Auto-generated method stub
            String hourDef;
            String minuteDef;

            hour = hourOfDay;
            CreateParty.this.minute = minute;

            if(hourOfDay < 10){
                hourDef = "0"+hourOfDay;
            }else{
                hourDef = ""+hourOfDay;
            }

            if(minute < 10){
                minuteDef = "0"+minute;
            }else{
                minuteDef = ""+minute;
            }

            heureFinPickerParty.setText(hourDef + "h" + minuteDef);
        }
    };

/***************************************************************************************************/
/***************************************************************************************************/
/***************************************************************************************************/


    public void setcurrentDate(){
        final Calendar c = Calendar.getInstance();

        annee = c.get(Calendar.YEAR);
        mois = c.get(Calendar.MONTH);
        jour = c.get(Calendar.DAY_OF_MONTH);
    }

	
}
