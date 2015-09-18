package controller;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import vue.CreateParty;

/**
 * Created by Doudou on 13/09/2015.
 */
public class CreatePartyController extends CreateParty {

    private ArrayList<NameValuePair> arrayList;
    private Connexion connexion = new Connexion();
    private JSONObject json_data = new JSONObject();
    private String resultat;

    int idMessage;
    int idUser;
    String libelleActivite, adressActivite, villeActivite, cpActivite, dateActivite, heureActivite;
    Activity myActivity;

    public CreatePartyController(Activity activity) {
        myActivity = activity;
    }


    public void requestCreateActivite(){

         idMessage = 0;

        arrayList = new ArrayList<NameValuePair>();

        arrayList.add(new BasicNameValuePair("libelleActivite", libelleActivite));
        arrayList.add(new BasicNameValuePair("adressActivite", adressActivite));
        arrayList.add(new BasicNameValuePair("villeActivite", villeActivite));
        arrayList.add(new BasicNameValuePair("cpActivite", cpActivite));
        arrayList.add(new BasicNameValuePair("dateActivite", dateActivite));
        arrayList.add(new BasicNameValuePair("heureActivite", heureActivite));


        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    JSONArray jsonArray = connexion.createNewActivite(arrayList);
                    if(!connexion.go) {


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast erreurCo = Toast.makeText(myActivity.getApplicationContext(), "Erreur lors de la connexion au serveur", Toast.LENGTH_SHORT);
                                erreurCo.show();
                            }
                        });


                    }

                    else {

                        json_data = jsonArray.getJSONObject(0);
                        resultat = json_data.getString("result");
                        Log.e("resultat", resultat);

                        if (resultat.equals("succes")) {

                            Log.e("resultat2", resultat);
                            idMessage = 1;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    myActivity.finish();
                                }
                            });

                        }

                        else{


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast erreurCo = Toast.makeText(myActivity.getApplicationContext(), "Erreur lors de la création de l'activité", Toast.LENGTH_SHORT);
                                    erreurCo.show();
                                }
                            });

                        }
                    }
                }catch (Exception e){
                    Log.e("erreur" , e.toString());
                }
            }
        }).start();
    }

    public void setLibelleActivite(String libelle) {
        libelleActivite = libelle;
    }

    public void setAdressActivite(String adresse) {
        adressActivite = adresse;
    }

    public void setVilleActivite(String ville) {
        villeActivite = ville;
    }

    public void setCpActivite(String string){
        cpActivite = string;
    }

    public void setDateActivite(String string){
        dateActivite = string;
    }

    public void setHeureActivite(String string){
        heureActivite = string;
    }
}
