package vue;

import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import com.example.meetus.R;

import controller.Connexion;


public class InfoPary extends Activity {
	
	final String url = "http://meetus.noip.me/meetus/chargementParty.php";
	Connexion getPartyInfo = new Connexion();
	public String IDParty; 
	public JSONObject json_data;
	private String partyTitre;
	private String partyLieu;
	private String partyDate;
	private String srcPic;
	public InputStream is;
	public Bitmap image;
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.party_homepage);
		
		Intent intent = getIntent();
		
		if(intent != null){
			Toast msg = Toast.makeText(this, intent.getStringExtra("ID_PARTY"), Toast.LENGTH_LONG);
			msg.show();
		
			IDParty = intent.getStringExtra("ID_PARTY");
		}
	}

	
	
	@SuppressLint("SimpleDateFormat")
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
						partyTitre = json_data.getString("PARTY_TITRE");
						partyLieu = json_data.getString("VILLE_LIEU");
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
	}
	
}
