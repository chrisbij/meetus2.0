package controller;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;



import vue.InfoPary;
import vue.rechercheActivite.MyAdapterList;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MyTask2 extends AsyncTask<String, Void, MyResult> {

    Context context;
    public String nom;
    public String prenom;
    Connexion co = new Connexion();
    public MyResult toto;
    public String s;
    public JSONObject json_data;
    public String srcPic;
    public URL url= new URL("http://meetus.noip.me/connexion.php");

    public ArrayList<String> idActivites = new ArrayList<String>();
    public ArrayList<String> titreActivites = new ArrayList<String>();
    public ArrayList<String> adresseActivites = new ArrayList<String>();
    public ArrayList<String> dateActivites = new ArrayList<String>();
    public ArrayList<String> imageActivites = new ArrayList<String>() ;

    ProgressDialog mProgressDialog;


    public MyTask2(Context a) throws MalformedURLException {
        context = a;

    }



    protected void onPreExecute(){


        mProgressDialog = ProgressDialog.show(context, "", "Chargement en cours...", true);
        mProgressDialog.setCancelable(true);
    }


    @SuppressLint("SimpleDateFormat")
    @SuppressWarnings("deprecation")
    @Override
    protected MyResult doInBackground(String... params) {
        // TODO Auto-generated method stub



        try{
            JSONArray jArray = co.getObjFromUrlTest(url, "BIJOU", "Chrislet");

            Log.e("img", co.go.toString());
            Log.e("TOTO", "toto");

            if(co.go == false){
                super.cancel(true);

            }else{
                for(int i=0;i<jArray.length();i++){

                    json_data = jArray.getJSONObject(i);

                    nom = json_data.getString("NOM_UTILISATEUR");
                    prenom = json_data.getString("PRENOM_UTILISATEUR");


				/*	HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(srcPic).openConnection();
					is = httpURLConnection.getInputStream();

                    final BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
					bm = BitmapFactory.decodeStream(is);*/

                    Log.e("TATA", "tata");

                    idActivites.add(""+nom);
                    titreActivites.add(""+prenom);
                }

                toto = addInfo();
            }
        }catch(Exception e){
            Log.e("img", e.toString());
        }

        return toto ;
    }

    protected void onPostExecute(MyResult result){


       // MyAdapterList adapter = new MyAdapterList(context,toto.getPartyID(), toto.getPartyTitre(), toto.getPartyLieu(), toto.getPartyDate(), toto.getImages());



        if(mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }

        Toast msg = Toast.makeText(context, nom, Toast.LENGTH_LONG);
        msg.show();

				/*new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Log.e("conio", "msg");

				final String idParty = String.valueOf(liste.getItemAtPosition(arg2));

				/*Toast msg = Toast.makeText(context, s, Toast.LENGTH_LONG);
				msg.show();* /

				Intent intent = new Intent(context, InfoPary.class);
				intent.putExtra("ID_PARTY", idParty);
				context.startActivity(intent);

			}
		});*/

    }

    protected void onCancelled(){

        String errorConnect = "Erreur lors de la connexion. R\u00e9essayer.";

        Toast msg = Toast.makeText(context, errorConnect, Toast.LENGTH_LONG);
        msg.show();
    }




    public MyResult addInfo(){
        ArrayList<String> idActiviteList = idActivites;
        ArrayList<String> titreActiviteList = titreActivites;
        ArrayList<String> adresseActiviteList = adresseActivites;
        ArrayList<String> dateActiviteList = dateActivites;
        ArrayList<String> imageActiviteList = imageActivites;

        return new MyResult(idActiviteList, titreActiviteList , adresseActiviteList, dateActiviteList, imageActiviteList);
    }


}
