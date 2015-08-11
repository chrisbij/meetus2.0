package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONArray;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class Connexion {
	
	
	private InputStream is = null;
	private JSONArray jArray = null;
	private String result ="";
	
	public Boolean go = false;
	
	public String src = "http://meetus.noip.me/meetus/media/images/image2.png";
	
	
	
	public Connexion(){
		
	}
	
	
	public JSONArray getObjFromUrlTest(String url, String IDparty, String prenom){
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("IdParty", IDparty));
		nameValuePairs.add(new BasicNameValuePair("prenom", prenom));
		
		return getObjFromUrl(url, nameValuePairs);
	}
	
	
	public JSONArray getPartyFromUrl(String url, String IDparty){
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("IdParty", IDparty));
		
		return getObjFromUrl(url, nameValuePairs);
	}
	
	
	public JSONArray getObjFromUrl(String url, ArrayList<NameValuePair> nameValuePairs){
		
		
		try{
			HttpClient client = new  DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			
			HttpConnectionParams.setConnectionTimeout(client.getParams(), 8000);
			HttpConnectionParams.setSoTimeout(client.getParams(), 8000);
			
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			
			go = true;
			
		}catch(Exception e){
			Log.e("connect", "Erreur lors de la connection : "+e.toString());
			
			go = false;
		}
		
		
		if (go!=false){ 
			try{
				BufferedReader read = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));
				StringBuilder sb = new StringBuilder();
				String line;
				while((line=read.readLine()) != null){
					sb.append(line);
				}
				is.close();
				result = sb.toString();
				Log.e("preResult", result);
			}catch(Exception e){
				Log.e("Recept", "Erreur lors de la reception : "+e.toString());
				go = false;
			}
		}
		
		
		if (go!=false){ 
			try{
				jArray = new JSONArray(result);
			}catch(Exception e){
				Log.e("test", e.toString());
			}
		}
		
		return jArray;
	}
	
	
	
	public JSONArray getAllFromUrl(String url){
		
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		
		return getObjFromUrl(url, nameValuePairs);
	}
	
	public static Bitmap getBitMap(String src){
		try{
			URL url = new URL(src);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoInput(true);
	        connection.connect();
	        InputStream input = connection.getInputStream();
	        Bitmap myBitmap = BitmapFactory.decodeStream(input);
	        return myBitmap;
		}catch(Exception e){
			
		}
		return null;
	}
	

}
