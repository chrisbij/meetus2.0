package vue;



import java.util.ArrayList;


import com.example.meetus.MainActivity;
import com.example.meetus.R;

import controller.MyTask;
import vue.profil.Home_profil;
import vue.rechercheActivite.Homepage1_0;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

public class MainVu2 extends Activity {

    public Activity activity = getParent();
    public String srcPic;
    public Drawable dra;
    public MyTask asyncTask;
    ImageView picProfil;
    Button newSearch, refresh, agenda, infoParty, profil;
    public int annee;
    public ListView list;
    public int jour;
    public ArrayList<String> text = new ArrayList<String>();
    public ArrayList<Bitmap> image = new ArrayList<Bitmap>() ;
    public int mois;
    public Context context;
    String nomPrenomUser;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        context = this;

        newSearch = (Button)findViewById(R.id.newSearch);
        refresh = (Button)findViewById(R.id.actualiser);

        newSearch.setOnClickListener(choixJour);
        refresh.setOnClickListener(actualiser);

        agenda = (Button)findViewById(R.id.agenda);
        agenda.setOnClickListener(openAgenda);

        profil = (Button)findViewById(R.id.profil);
        profil.setOnClickListener(openProfil);

        list = (ListView)findViewById(R.id.lvListe);

        //picProfil = (ImageView)findViewById(R.id.list_image);

        asyncTask = new MyTask(context, list);

        asyncTask.execute("");



    }


    public OnClickListener choixJour = new OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), Homepage1_0.class);
            startActivity(intent);
        }
    };


    public OnClickListener actualiser = new OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
            startActivity(getIntent());
        }
    };

  /*  public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_connect, menu);
        ActionBar actionBar = getActionBar();
        MenuItem item = menu.findItem(R.id.loginUser);
        item.setTitle(nomPrenomUser);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu:

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainVu2.this);

                alertDialogBuilder.setTitle("Déconnexion");
                alertDialogBuilder.setMessage("Vous souhaitez vous déconnecter?");
                alertDialogBuilder.setCancelable(true);

                alertDialogBuilder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });

                alertDialogBuilder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alertDialogBuilder.create();
                alertDialogBuilder.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    public OnClickListener openAgenda = new OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), Agenda.class);
            startActivity(intent);
        }
    };

    public OnClickListener openProfil = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), Home_profil.class);
            startActivity(intent);
        }
    };


}
