package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.params.HttpConnectionParams;
import org.json.JSONArray;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class Connexion {
	
	
	private InputStream inptS = null;
	private JSONArray jArray = null;
	private String result ="";

	public URL urlServeur = new URL("http://meetus.noip.me/meetus/create_user.php");


	
	public Boolean go = false;
	
	public String src = "http://meetus.noip.me/meetus/media/images/image2.png";
	
	
	
	public Connexion() throws MalformedURLException {
		
	}
	
	
	public JSONArray getObjFromUrlTest(URL url, String IDparty, String prenom){

		HashMap<String, String> myMap = new HashMap<>();


		myMap.put("idParty", IDparty);
		myMap.put("prenom", "Chrislet");
		
		return getObjFromUrl(url, myMap);
	}


    /**********************************************************************************************/
    /**********************************************************************************************/

	public JSONArray createNewUser(HashMap<String, String> hMap){

		URL url= null;

		try {
			url = new URL("http://meetus.noip.me/meetus/create_user.php");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return getObjFromUrl(url, hMap);
	}


    /**********************************************************************************************/
    /**********************************************************************************************/

	public JSONArray createNewActivite(HashMap<String, String> hMap){
		URL url= null;

		try {
			url = new URL("http://meetus.noip.me/meetus/create_activite.php");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return getObjFromUrl(url, hMap);
	}


    /**********************************************************************************************/
    /**********************************************************************************************/
	
	public JSONArray getPartyFromUrl(String IDparty){
		URL url= null;

		try {
			url = new URL("http://meetus.noip.me/meetus/create_activite.php");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		HashMap<String, String> myMap = new HashMap<>();
		myMap.put("idParty", IDparty);

		return getObjFromUrl(url,myMap);
	}

    /**********************************************************************************************/
    /**********************************************************************************************/

    public JSONArray getInfoActivite(String idActivite){
        ContentValues contentValues = new ContentValues();
        contentValues.put("idActivite", idActivite);

        HashMap<String, String> myParams = new HashMap<>();

        myParams.put("idActivite", idActivite);

        return infoActivite("http://meetus.noip.me/meetus/chargementParty.php", myParams);
    }





    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/



	public JSONArray getObjFromUrl(URL url, HashMap<String, String> nameValuePairs){
		
		HttpURLConnection connection = null;

		try{

			connection = (HttpURLConnection) url.openConnection();
			connection.setReadTimeout(8000);
			connection.setConnectTimeout(3000);
			connection.setDoInput(true);
			connection.setRequestProperty("User-Agent", "");
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);



			OutputStream ouptS = connection.getOutputStream();
			BufferedWriter write = new BufferedWriter(new OutputStreamWriter(ouptS, "UTF-8"));
			write.write(getQuery(nameValuePairs));

			write.flush();
			write.close();
			ouptS.close();

			InputStream inputStream = connection.getInputStream();
			inptS = inputStream;
			go = true;
			
		}catch(Exception e){
			Log.e("connect", "Erreur lors de la connection : "+e.toString());
			
			go = false;
		}
		
		
		if (go!=false){



			try{
				BufferedReader read = new BufferedReader(new InputStreamReader(inptS, "ISO-8859-1"));
				StringBuilder sb = new StringBuilder();
				String line;
				while((line=read.readLine()) != null){
					sb.append(line);
				}
				inptS.close();
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




    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/



	public JSONArray infoActivite(String urlInfo, HashMap<String, String> hashmap){

        try{
            URL url = new URL(urlInfo);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("User-Agent", "");
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
			connection.setConnectTimeout(8000);
			connection.setReadTimeout(8000);

            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getQuery(hashmap));

            writer.flush();
            writer.close();
            os.close();

            InputStream inputStream = connection.getInputStream();

            BufferedReader read = new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-1"));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line=read.readLine()) != null){
                sb.append(line);
            }

            inputStream.close();
            result = sb.toString();
        }catch(Exception e){
			Log.e("Erro_infoActivite2", e.toString());
        }

        try{
            jArray = new JSONArray(result);
        }catch (Exception e){
            Log.e("Erro_infoActivite2", e.toString());
        }

        return jArray;

    }



    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/


    public String getQuery(HashMap<String, String> params) throws UnsupportedEncodingException{

        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }



    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/


	
	public static Bitmap getBitMap(String src){
		try{

			Log.e("Start2", "Début");
			URL url = new URL(src);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoInput(true);
	        connection.connect();
	        InputStream input = connection.getInputStream();
	        Bitmap myBitmap = BitmapFactory.decodeStream(input);
			Log.e("End2","fin");
	        return myBitmap;
		}catch(Exception e){
			
		}
		return null;
	}
	

}

