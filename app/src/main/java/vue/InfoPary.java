package vue;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meetus.R;

import controller.Connexion;
import controller.GetActiviteInfo;


public class InfoPary extends Activity {
	
	final String url = "http://meetus.noip.me/meetus/chargementParty.php";
	Connexion getPartyInfo = new Connexion();

	public String IDParty; 
	public JSONObject json_data;

	private String nbrParticipant;
	private String prix;
	private String dateActivite;

	public Bitmap srcPic;
	public InputStream is;
	public Bitmap image;
    public View context;

    public JSONObject jsonData;

    public RelativeLayout enTete;
	public TextView information;

	public TextView titreActitive, adresseActivite, dateActivit, nbParticipant;

    Button valider, retour;

    Connexion connexion = new Connexion();




    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.party_homepage);

        enTete = (RelativeLayout)findViewById(R.id.entete);
		information = (TextView)findViewById(R.id.information);
		titreActitive = (TextView)findViewById(R.id.titreActivite);
        adresseActivite = (TextView)findViewById(R.id.textAdresse);
        dateActivit = (TextView)findViewById(R.id.date);
        nbParticipant = (TextView)findViewById(R.id.nbrParticipant);

        valider = (Button)findViewById(R.id.participerHP);
        retour = (Button)findViewById(R.id.retourHP);


		/*information.setText("Ayant toujours rêvé d'aller à New York, j'ai décidé d'économiser et de partir à l'aventure.\n" +
				"Pour profiter pleinement de ce voyage, je souhaite partir avec d'autre personnes, qui comme moi sont totalement \"In Love\" de cette ville. Bien évidemment tous les frais de location seront partagés"+
				"entre les différentes personnes qui souhaitent venir. J'espère que ce projet vous plaira. XoXo.");*/

		Intent intent = getIntent();
		
		if(intent != null){
			Toast msg = Toast.makeText(this, intent.getStringExtra("ID_PARTY"), Toast.LENGTH_LONG);
			msg.show();
		
			IDParty = intent.getStringExtra("ID_PARTY");
		}

		setInfoParty();


        retour.setOnClickListener(precedent);

	}

	
	public void setInfoParty(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    JSONArray jsonArray = connexion.getInfoActivite(IDParty);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        json_data = jsonArray.getJSONObject(i);

                        final String titreActivite = json_data.getString("LIBELLE_ACTIV");
                        final String adrActivite = json_data.getString("ADRESSE_ACTIV");
                        final String villeActivite = json_data.getString("ville_nom_reel");
						final String description = json_data.getString("DESCRIPTION");
						final String nbrParticipant = json_data.getString("nbr_participant_max");


/***************************************************************************************************/
                        String date = json_data.getString("date_deb_act");
                        SimpleDateFormat dateTransform = new SimpleDateFormat("yyyy-MM-dd");
                        Date maDate = dateTransform.parse(date);
                        final String dateDeb = new SimpleDateFormat("dd/MM/yyyy").format(maDate);

                        String date2 = json_data.getString("date_fin_act");
                        SimpleDateFormat dateTransform2 = new SimpleDateFormat("yyyy-MM-dd");
                        Date maDate2 = dateTransform2.parse(date2);
                        final String dateFin = new SimpleDateFormat("dd/MM/yyyy").format(maDate2);

/***************************************************************************************************/

                        String img = json_data.getString("adresse_img_act");

                        HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(img).openConnection();
                        InputStream inputStream = httpURLConnection.getInputStream();

                        final BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                        image = BitmapFactory.decodeStream(bufferedInputStream);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                enTete.setBackground(new BitmapDrawable(Resources.getSystem(), image));
                                titreActitive.setText(titreActivite);
                                adresseActivite.setText(adrActivite + ". " + villeActivite);
                                information.setText(description);
                                dateActivit.setText("Du " + dateDeb + " au " + dateFin);
                                nbParticipant.setText(nbrParticipant);

                            }
                        });

                    }
                }catch (Exception e){

                }
            }
        }).start();
    }


    public View.OnClickListener precedent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


/*	@SuppressLint("SimpleDateFormat")
	public void afficheInfoParty(){
		
		new Thread(new Runnable() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					JSONArray jArray = getPartyInfo.getPartyFromUrl(url, IDParty);
					
					for(int i=0;i<jArray.length();i++){
						json_data = jArray.getJSONObject(i);
						partyTitre = json_data.getString("LIBELLE_ACTIV");
						partyLieu = json_data.getString("ADRESSE_ACTIV");
						String date = json_data.getString("DATE_PARTY");
						
						SimpleDateFormat dateTransform = new SimpleDateFormat("yyyy-MM-dd");
						Date maDate = dateTransform.parse(date);
						
						
						partyDate = maDate.toLocaleString();
						
						srcPic = "http://meetus.noip.me/meetus/media/images/image1.png";
							
							is = (InputStream) new URL(srcPic).getContent();
							image = BitmapFactory.decodeStream(is);
					}
					
					
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							
						}
					});
				}
				catch(Exception e){
					
				}
				
			}
		}).start();
	}*/

    public InfoPary() throws MalformedURLException {
    }
	
}
