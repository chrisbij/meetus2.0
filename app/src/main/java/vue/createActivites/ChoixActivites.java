package vue.createActivites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.meetus.R;

import vue.CreateParty;

/**
 * Created by Doudou on 30/09/2015.
 */
public class ChoixActivites extends CreateParty {

    Button vacances, musique, shopping, photo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.select_activite);

        vacances = (Button)findViewById(R.id.vacances);
        musique  = (Button)findViewById(R.id.musique);
        shopping = (Button)findViewById(R.id.shopping);
        photo    = (Button)findViewById(R.id.photo);

        vacances.setOnClickListener(choixActivite);
        musique.setOnClickListener(choixActivite);
        shopping.setOnClickListener(choixActivite);
        photo.setOnClickListener(choixActivite);


    }

    public View.OnClickListener choixActivite = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String result;
            Intent returnIntent = new Intent();

            switch (view.getId()){
                case R.id.vacances:
                    result = vacances.getText().toString();
                    returnIntent.putExtra("RESULT", result);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                    break;
                case R.id.musique:
                    result = musique.getText().toString();
                    returnIntent.putExtra("RESULT", result);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                    break;
                case R.id.shopping:
                    result = shopping.getText().toString();
                    returnIntent.putExtra("RESULT", result);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                    break;
                case R.id.photo:
                    result = photo.getText().toString();
                    returnIntent.putExtra("RESULT", result);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                    break;
            }
        }
    };

}
