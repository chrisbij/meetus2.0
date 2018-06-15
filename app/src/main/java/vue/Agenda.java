package vue;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.meetus.R;

import java.net.MalformedURLException;

import controller.MyTask;
import vue.createActivites.CreateParty;
import vue.profil.Home_profil;

/**
 * Created by Doudou on 02/09/2015.
 */
public class Agenda extends Activity {

    ListView listView;
    public TextView textView;
    Button creatActivite, agenda, activite, profil;
    final Context context = this;
    public MyTask myTask;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.agenda);

        agenda = findViewById(R.id.agenda);
        agenda.setBackground(getResources().getDrawable(R.drawable.btn_grey_selected, null));

        listView = findViewById(R.id.lvListeAgenda);
        textView = (TextView)findViewById(R.id.textView5);

        activite = (Button)findViewById(R.id.activity);
        profil = (Button)findViewById(R.id.profil);

        textView.setVisibility(View.INVISIBLE);


        try {
            myTask = new MyTask(context, listView);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        myTask.execute("");

        creatActivite = (Button)findViewById(R.id.createActivite);

        creatActivite.setOnClickListener(openCreateActivity);

        activite.setOnClickListener(openActivityVue);
        profil.setOnClickListener(openProfil);

    }

    public View.OnClickListener openCreateActivity = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), CreateParty.class);
            startActivity(intent);
        }
    };

    public View.OnClickListener openActivityVue = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), MainVue.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }
    };

    public View.OnClickListener openProfil = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), Home_profil.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }
    };

}
