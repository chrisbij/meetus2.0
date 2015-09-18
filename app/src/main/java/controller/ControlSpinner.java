package controller;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import model.CustomOnItemSelectedListener;

/**
 * Created by bijou on 12/08/2015.
 */
public class ControlSpinner extends Activity {

    Spinner spinner;
    Context context;
    List<?> liste;

    public ControlSpinner(Context con, Spinner spin, List<?> l){
        spinner = spin;
        context =con;
        liste = l;
    }


   public void addSpin(){
        ArrayAdapter adaptSpin = new ArrayAdapter(context,android.R.layout.simple_spinner_item,liste);

        adaptSpin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adaptSpin);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

}
