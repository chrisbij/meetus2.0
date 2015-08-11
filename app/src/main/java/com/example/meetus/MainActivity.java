package com.example.meetus;

import org.json.JSONObject;




import vue.AccueilVue;
import controller.Connexion;
import vue.Homepage1_0;
import vue.MainVue;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {

	public String url = "http://meetus.noip.me/meetus/connexion.php";
	public Button connect;
	public EditText login;
	public JSONObject json_data;
	public String nom;
	public String prenom;
	public  Handler mHandler;
    
	
	Connexion co = new Connexion();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);
        
        
        login = (EditText)findViewById(R.id.login);
        connect = (Button)findViewById(R.id.connection);
        
        connect.setOnClickListener(bonjour);
        
        
    }
    
    
    public OnClickListener bonjour = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
				
					try{
			//		 	JSONArray jArray = co.getObjFromUrlTest(url, "BIJOU", "Chrislet");
						
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									/*Toast.makeText(MainActivity.this, "Bonjour Monsieur", Toast.LENGTH_SHORT).show();*/
									
									Intent intent = new Intent(getApplicationContext(), MainVue.class);
									
									startActivity(intent);
								}
							});
						
					}catch(Exception e){
						
					}
					
				}
			}).start();
				
			}
			
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
  
}

