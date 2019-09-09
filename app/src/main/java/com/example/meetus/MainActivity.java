package com.example.meetus;

import org.json.JSONObject;


import controller.CreateDataBase;
import controller.Connexion;
import controller.UploadFile;
import vue.MainVue;
import vue.createUser.SelectSex;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends Activity {

	public String url = "http://meetus.noip.me/meetus/connexion.php";
	public Button connect, createUser;
	public EditText login;
	public JSONObject json_data;
	public String nom;
	public String prenom;
	public  Handler mHandler;
    public CreateDataBase createDataBase;
    
	
	Connexion co = new Connexion();

    UploadFile uploadFile = new UploadFile();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);
        
        
        login = (EditText)findViewById(R.id.login);
        connect = (Button)findViewById(R.id.connection);
		createUser = (Button)findViewById(R.id.createUser);
        
        connect.setOnClickListener(logUser);
		createUser.setOnClickListener(createNewUser);



		/*createDataBase = new CreateDataBase(this);

        createDataBase.execute("");*/
        
    }
    
    
    public OnClickListener logUser = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub


			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
				
					try{
			//		 	JSONArray jArray = co.getObjFromUrlTest(url, "BIJOU", "Chrislet");
                        //uploadFile.uploadFileToServer();
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									/*Toast.makeText(MainActivity.this, "Bonjour Monsieur", Toast.LENGTH_SHORT).show();*/
									
									Intent intent = new Intent(getApplicationContext(),MainVue.class);
									
									startActivity(intent);
								}
							});
						
					}catch(Exception e){
						
					}
					
				}
			}).start();
				
			}
			
	};


	public OnClickListener createNewUser = new OnClickListener() {
		@Override
		public void onClick(View view) {
			try{
				Intent intent = new Intent(getApplicationContext(), SelectSex.class);
				startActivity(intent);
			}catch (Exception e){

			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public String readString(Context context){
		InputStream input = context.getResources().openRawResource(R.raw.ville);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		String data = null;

		try{
            while((data = reader.readLine()) != null){
                Toast.makeText(context, ""+data,Toast.LENGTH_SHORT).show();
            }


		}catch (Exception e){

		}

        try{
            input.close();
            reader.close();
        }catch (IOException e){

        }

		return data;
	}
  
}

