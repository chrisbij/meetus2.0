package controller;

import java.io.InputStream;
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
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MyTaskAgenda extends AsyncTask<String, Void, MyResult> {

    Context context;
    public String partyTitre;
    public String partyLieu;
    public String partyDate;
    public String partyId;
    Connexion co = new Connexion();
    public ListView liste;
    public MyResult toto;
    public InputStream is;
    public Bitmap bm;
    public JSONObject json_data;
    public String srcPic;
    public URL url = new URL("http://meetus.noip.me/meetus/connexion2.php");

    public ArrayList<String> idParty = new ArrayList<String>();
    public ArrayList<String> titreParty = new ArrayList<String>();
    public ArrayList<String> lieuParty = new ArrayList<String>();
    public ArrayList<String> dateParty = new ArrayList<String>();
    public ArrayList<String> image = new ArrayList<String>() ;


    public MyTaskAgenda(Context a, ListView view) throws MalformedURLException {
        context = a;
        liste = view;
    }




    @SuppressLint("SimpleDateFormat")
    @SuppressWarnings("deprecation")
    @Override
    protected MyResult doInBackground(String... params) {
        // TODO Auto-generated method stub




        try{
            JSONArray jArray = co.getObjFromUrlTest(url, "BIJOU", "Chrislet");

            if(co.go == false){
                super.cancel(true);
            }else{
                for(int i=0;i<jArray.length();i++){

                    json_data = jArray.getJSONObject(i);
                    partyId = json_data.getString("PARTY_ID");
                    partyTitre = json_data.getString("PARTY_TITRE");
                    partyLieu = json_data.getString("VILLE_LIEU");
                    String date = json_data.getString("DATE_PARTY");

                    SimpleDateFormat dateTransform = new SimpleDateFormat("yyyy-MM-dd");
                    Date maDate = dateTransform.parse(date);


                    partyDate = maDate.toLocaleString();

                    srcPic = "http://meetus.noip.me/meetus/media/images/image1.png";

                    is = (InputStream) new URL(srcPic).getContent();
                    bm = BitmapFactory.decodeStream(is);

                    idParty.add(""+partyId);
                    titreParty.add(""+partyTitre);
                    lieuParty.add(""+partyLieu);
                    dateParty.add(""+partyDate);
                    image.add(srcPic);

                    toto = something();
                }
            }
        }catch(Exception e){
            Log.e("img", e.toString());
        }

        return toto ;
    }

    protected void onPostExecute(MyResult result){


        MyAdapterList adapter = new MyAdapterList(context,toto.getPartyID(), toto.getPartyTitre(), toto.getPartyLieu(), toto.getPartyDate(), toto.getImages());


        Log.e("eco", ""+ liste.getId());


        liste.setAdapter(adapter);

        liste.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Log.e("conio", "msg");

                final String idParty = String.valueOf(liste.getItemAtPosition(arg2));
				
				/*Toast msg = Toast.makeText(context, s, Toast.LENGTH_LONG);
				msg.show();*/

                Intent intent = new Intent(context, InfoPary.class);
                intent.putExtra("ID_PARTY", idParty);
                context.startActivity(intent);

            }
        });

    }

    protected void onCancelled(){

        String errorConnect = "Erreur lors de la connexion. R\u00e9essayer.";

        Toast msg = Toast.makeText(context, errorConnect, Toast.LENGTH_LONG);
        msg.show();
    }


    public MyResult something(){
        ArrayList<String> idPartyList = idParty;
        ArrayList<String> titrePartyList = titreParty;
        ArrayList<String> lieuPartyList = lieuParty;
        ArrayList<String> datePartyList = dateParty;
        ArrayList<String> imageList = image;

        return new MyResult(idPartyList, titrePartyList , lieuPartyList, datePartyList, imageList);
    }



}
