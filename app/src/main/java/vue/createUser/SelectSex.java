package vue.createUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.meetus.R;

/**
 * Created by bijou on 18/08/2015.
 */
public class SelectSex extends Activity {

    Button homme, femme;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.select_sexe);

        homme = (Button)findViewById(R.id.homme);

        homme.setOnClickListener(createNewUser);

    }

    public View.OnClickListener createNewUser = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String sexe="";
            switch (view.getId()){
                case R.id.homme:
                    sexe = "M";
                    break;
                case R.id.femme :
                    sexe = "F";
                    break;
            }

            Intent intent = new Intent(getApplicationContext(), CreateUser.class);
            intent.putExtra("SEXE", sexe);
            startActivity(intent);
            finish();
        }
    };
}
