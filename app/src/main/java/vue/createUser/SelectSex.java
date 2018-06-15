package vue.createUser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.meetus.R;

import java.net.MalformedURLException;

import controller.MyTask2;

/**
 * Created by bijou on 18/08/2015.
 */
public class SelectSex extends Activity {

    Button homme, femme;

    public MyTask2 asynctask;
    public Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
        setContentView(R.layout.select_sexe);

        homme = (Button)findViewById(R.id.homme);

        homme.setOnClickListener(createNewUser);

        try{
            asynctask = new MyTask2(context);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        asynctask.execute("");
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
