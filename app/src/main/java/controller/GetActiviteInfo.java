package controller;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.meetus.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import vue.rechercheActivite.SetInfoActivite;

/**
 * Created by bijou on 03/11/2015.
 */
public class GetActiviteInfo extends AsyncTask<String, Void, Void> {

    private String libelleActivite;
    private String adresseActivite;
    private String description;
    private String nbrParticipantMax;
    private String prixPpers;
    private String dateDebActivite;
    private String villeActivite;
    private String adr_image_activite;
    private Bitmap imageActivite;

    public View context;

    public JSONObject json_data;

    Connexion connexion = new Connexion();

    public GetActiviteInfo(View a) throws MalformedURLException {
        context = a;
    }


    protected Void doInBackground(String... idActivite){
        Bitmap mIcon11 = null;

        String id = idActivite[0];



        try{
            JSONArray jsonArray = connexion.getInfoActivite(id);

            json_data = jsonArray.getJSONObject(0);

            setLibelleActivite(json_data.getString("LIBELLE_ACTIV"));
            setAdresseActivite(json_data.getString("ADRESSE_ACTIV"));
            setDescription(json_data.getString("DESCRIPTION"));
            setNbrParticipantMax(json_data.getString("nbr_participant_max"));
            setPrixPpers(json_data.getString("budg_pr_pers"));
            String date = json_data.getString("date_deb_act");
            SimpleDateFormat dateTransform = new SimpleDateFormat("yyyy-MM-dd");
            Date maDate = dateTransform.parse(date);

            setDateDebActivite(new SimpleDateFormat("dd/MM/yyyy").format(maDate));

            setVilleActivite(json_data.getString("ville_nom_reel"));
            setAdr_image_activite(json_data.getString("adresse_img_act"));


            InputStream in = new java.net.URL(getAdr_image_activite()).openStream();

            setImageActivite(BitmapFactory.decodeStream(in));

        }catch (Exception e){

            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        return null;
    }


   protected void onPostExecute(Void v){
       SetInfoActivite setInfoActivite = new SetInfoActivite(context, getImageActivite());

       setInfoActivite.setInfoActivite();
   }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/


    public void setLibelleActivite(String string){
        libelleActivite = string;
    }

    public void setAdresseActivite(String string){
        adresseActivite = string;
    }

    public void setDescription(String string){
        description = string;
    }

    public void setNbrParticipantMax(String string){
        nbrParticipantMax = string;
    }

    public void setPrixPpers(String string){
        prixPpers = string;
    }

    public void setDateDebActivite(String string){
        dateDebActivite = string;
    }

    public void setVilleActivite(String string){
        villeActivite = string;
    }

    public void setAdr_image_activite(String string){adr_image_activite = string;}

    public void setImageActivite(Bitmap bitmap){ imageActivite = bitmap;}


    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    public String getLibelleActivite(){
        return libelleActivite;
    }

    public String getAdresseActivite(){
        return adresseActivite;
    }

    public String getDescription(){
        return description;
    }

    public String getNbrParticipantMax(){
        return nbrParticipantMax;
    }

    public String getPrixPpers(){
        return prixPpers;
    }

    public String getVilleActivite(){
        return villeActivite;
    }

    public String getDateDebActivite(){
        return dateDebActivite;
    }

    public String getAdr_image_activite(){ return adr_image_activite;}

    public Bitmap getImageActivite(){ return imageActivite;}
}
