package vue;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.meetus.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.ControlSpinner;
import model.CustomOnItemSelectedListener;

/**
 * Created by bijou on 11/08/2015.
 */
public class SelectDay extends Activity{

    Spinner spinMois;
    Spinner spinJours;
    Spinner spinAnnees;

    Spinner spinMoisIntervalDeb;
    Spinner spinJoursIntervalDeb;
    Spinner spinAnneesIntervalDeb;

    Spinner spinMoisIntervalFin;
    Spinner spinJoursIntervalFin;
    Spinner spinAnneesIntervalFin;

    ControlSpinner controlJours, controlMois, controlAnnees;

    ControlSpinner controlJoursIntervalDeb, controlMoisIntervalDeb, controlAnneesIntervalDeb;

    ControlSpinner controlJoursIntervalFin, controlMoisIntervalFin, controlAnneesIntervalFin;

    RadioGroup radio1;

    Button valider, annuler;

    RelativeLayout entete;

    ImageView imgEntete;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.selectday);

        Intent intent = getIntent();

        String couleur = "@android:color/holo_blue_dark";
        int id = intent.getIntExtra("COULEUR", 0); //getResources().getIdentifier(couleur, "drawable", getPackageName());
        int idImg = intent.getIntExtra("IMAGE", 0);
       // Drawable drawable = getResources().getDrawable(id);

        entete = (RelativeLayout)findViewById(R.id.entete);
        imgEntete = (ImageView)findViewById(R.id.imgEntete);
        entete.setBackgroundColor(id);
        imgEntete.setBackgroundResource(idImg);

        annuler = (Button)findViewById(R.id.annulerSelectDay);

        annuler.setOnClickListener(retourAccueil);

        spinMois = (Spinner)findViewById(R.id.mois);
        spinJours = (Spinner)findViewById(R.id.jours);
        spinAnnees = (Spinner)findViewById(R.id.annees);

        spinMoisIntervalDeb = (Spinner)findViewById(R.id.moisIntervalDeb);
        spinJoursIntervalDeb = (Spinner)findViewById(R.id.joursIntervalDeb);
        spinAnneesIntervalDeb = (Spinner)findViewById(R.id.anneesIntervalDeb);

        spinMoisIntervalFin = (Spinner)findViewById(R.id.moisIntervalFin);
        spinJoursIntervalFin = (Spinner)findViewById(R.id.joursIntervalFin);
        spinAnneesIntervalFin = (Spinner)findViewById(R.id.anneesIntervalFin);


        spinJours.setEnabled(false);
        spinJours.setClickable(false);

        spinJoursIntervalDeb.setEnabled(false);
        spinMoisIntervalDeb.setEnabled(false);
        spinAnneesIntervalDeb.setEnabled(false);

        spinMois.setEnabled(false);
        spinAnnees.setEnabled(false);


        spinJoursIntervalFin.setEnabled(false);
        spinMoisIntervalFin.setEnabled(false);
        spinAnneesIntervalFin.setEnabled(false);


        //  choixJours.setOnCheckedChangeListener(checkChoixJour);


        List<Integer> jours = new ArrayList<Integer>();

        for(Integer i = 1;i<=31;i++){
            jours.add(i);
        }

        List<Integer> annees = new ArrayList<Integer>();

        for(Integer i = 2015;i<=2025;i++){
            annees.add(i);
        }

        List<String> mois = new ArrayList<String>();

        mois.add("Janvier"); mois.add("Février"); mois.add("Mars"); mois.add("Avril"); mois.add("Mai"); mois.add("Juin");
        mois.add("Juillet"); mois.add("Août"); mois.add("Septembre"); mois.add("Octobre"); mois.add("Novembre"); mois.add("Décembre");


        controlJours = new ControlSpinner(getApplicationContext(),spinJours,jours);
        controlMois = new ControlSpinner(getApplicationContext(), spinMois, mois);
        controlAnnees = new ControlSpinner(getApplicationContext(),spinAnnees,annees);

        controlJoursIntervalDeb = new ControlSpinner(getApplicationContext(),spinJoursIntervalDeb,jours);
        controlMoisIntervalDeb = new ControlSpinner(getApplicationContext(), spinMoisIntervalDeb, mois);
        controlAnneesIntervalDeb = new ControlSpinner(getApplicationContext(),spinAnneesIntervalDeb,annees);

        controlJoursIntervalFin = new ControlSpinner(getApplicationContext(),spinJoursIntervalFin,jours);
        controlMoisIntervalFin = new ControlSpinner(getApplicationContext(), spinMoisIntervalFin, mois);
        controlAnneesIntervalFin = new ControlSpinner(getApplicationContext(),spinAnneesIntervalFin,annees);

        controlJours.addSpin();
        controlMois.addSpin();
        controlAnnees.addSpin();

        controlJoursIntervalDeb.addSpin();
        controlMoisIntervalDeb.addSpin();
        controlAnneesIntervalDeb.addSpin();

        controlJoursIntervalFin.addSpin();
        controlMoisIntervalFin.addSpin();
        controlAnneesIntervalFin.addSpin();
    }



    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.choixJour:
                if (checked)
                    // Pirates are the best
                    spinJours.setEnabled(true);
                    spinMois.setEnabled(true);
                    spinAnnees.setEnabled(true);


                    spinJoursIntervalDeb.setEnabled(false);
                    spinMoisIntervalDeb.setEnabled(false);
                    spinAnneesIntervalDeb.setEnabled(false);


                    spinJoursIntervalFin.setEnabled(false);
                    spinMoisIntervalFin.setEnabled(false);
                    spinAnneesIntervalFin.setEnabled(false);

                    break;
            case R.id.choixPeriode:
                if (checked)
                    // Ninjas rule

                    spinJoursIntervalDeb.setEnabled(true);
                    spinMoisIntervalDeb.setEnabled(true);
                    spinAnneesIntervalDeb.setEnabled(true);


                    spinJoursIntervalFin.setEnabled(true);
                    spinMoisIntervalFin.setEnabled(true);
                    spinAnneesIntervalFin.setEnabled(true);


                    spinJours.setEnabled(false);
                    spinMois.setEnabled(false);
                    spinAnnees.setEnabled(false);

                    break;
        }
    }


    public View.OnClickListener retourAccueil = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), MainVue.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    };

}
