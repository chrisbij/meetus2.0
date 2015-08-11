package vue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.meetus.R;

/**
 * Created by bijou on 07/08/2015.
 */
public class Homepage1_1_1 extends Activity{

    Button annuler;
    Button vacances;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.homepage1_1_1);

        annuler = (Button)findViewById(R.id.annulerHP111);
        vacances = (Button)findViewById(R.id.vacances);

        annuler.setOnClickListener(annuleRecherche);
        vacances.setOnClickListener(selectDay);
    }


    public View.OnClickListener annuleRecherche = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try{
                Intent intent = new Intent(getApplicationContext(), MainVue.class);
                startActivity(intent);

            }catch (Exception e){
                Log.e("machin", e.toString());
            }
        }
    };

    public View.OnClickListener selectDay = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try{
                Intent intent = new Intent(getApplicationContext(), SelectDay.class);
                startActivity(intent);

            }catch (Exception e){
                Log.e("machin", e.toString());
            }
        }
    };
}
