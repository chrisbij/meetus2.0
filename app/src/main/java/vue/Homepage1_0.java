package vue;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.meetus.MainActivity;
import com.example.meetus.R;

/**
 * Created by bijou on 30/07/2015.
 */
public class Homepage1_0 extends MainActivity {


    Button rencontre;
    Button event;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.homepage1_0);

        rencontre = (Button)findViewById(R.id.love);
        event = (Button)findViewById(R.id.party);

        rencontre.setOnClickListener(accesRencontre);
        event.setOnClickListener(accesEvent);
    }

    public View.OnClickListener accesRencontre = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            try{
                Intent intent = new Intent(getApplicationContext(), Homepage1_1_2.class);
                startActivity(intent);

            }catch (Exception e){
                Log.e("machin", e.toString());
            }

        }
    };


    public View.OnClickListener accesEvent = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            try{
                Intent intent = new Intent(getApplicationContext(), Homepage1_1_1.class);
                startActivity(intent);

            }catch (Exception e){
                Log.e("machin", e.toString());
            }
        }
    };
}
