package controller;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.meetus.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

import model.MySQLiteHelper;

/**
 * Created by bijou on 25/09/2015.
 */
public class CreateDataBase extends AsyncTask {


    private final String fichier = "ville.sql";
    public Context context;

    public CreateDataBase(Context con){
        context = con;
    }


    @Override
    protected Object doInBackground(Object[] objects) {

        VilleDataSource villeDataSource = new VilleDataSource(context);
        try {
            villeDataSource.open();

            InputStream input = context.getResources().openRawResource(R.raw.villes);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String data = null;

            try{
                while((data = reader.readLine()) != null){
                    villeDataSource.insertVille(data);
                }

            }catch (Exception e){

            }

            try{
                input.close();
                reader.close();
            }catch (IOException e){

            }

            villeDataSource.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
