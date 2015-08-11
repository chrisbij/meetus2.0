package vue;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.meetus.R;

import model.CustomOnItemSelectedListener;

/**
 * Created by bijou on 11/08/2015.
 */
public class SelectDay extends Activity{

    Spinner spin;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.selectday);

        spin = (Spinner)findViewById(R.id.spinner);

        String[] mois = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août",
                "Septembre", "Octobre", "Novembre", "Décembre"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, mois);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(adapter);

        spin.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }


    public void onItemSelected(AdapterView parent, View view, int pos, long id){

        Toast.makeText(getApplicationContext(),
                spin.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG)
                .show();
    }


    public void onNothingSelected(AdapterView arg0) {
// TODO Auto-generated method stub

    }

}
