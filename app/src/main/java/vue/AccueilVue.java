package vue;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.meetus.MainActivity;
import com.example.meetus.R;

public class AccueilVue extends MainActivity {

	
	public ImageButton createParty;
	public ImageButton accessParty;
	
	final String ExtraContext= "contexte de l'appli";
	
	final Context context = this;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.accueil_user);
		
		accessParty = (ImageButton)findViewById(R.id.imageButtonADeux);
		createParty = (ImageButton)findViewById(R.id.imageButtonGroupe);
		
		accessParty.setOnClickListener(accessPartyListener);
		createParty.setOnClickListener(createPartyListener);
		
	}
	
	
	
	
	
	public OnClickListener accessPartyListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
				
					
						
					try{
						
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									Intent intent = new Intent(getApplicationContext(), ChoixActivite.class);
								
									startActivity(intent);
								}
							});
						
					}catch(Exception e){
						
					}
					
				}
			}).start();
				
			}			
		};
	
	
	
	public OnClickListener createPartyListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
				
					try{
						
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									Intent intent = new Intent(getApplicationContext(), CreateParty.class);
									
									startActivity(intent);
								}
							});
						
					}catch(Exception e){
						
					}
					
				}
			}).start();
		}
	};
	
}
