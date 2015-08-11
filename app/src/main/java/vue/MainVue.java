package vue;



import java.util.ArrayList;




import com.example.meetus.R;

import controller.MyTask;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;

public class MainVue extends AccueilVue {

	
	public String srcPic;
	public Bitmap bm; 
	public Drawable dra;
	public MyTask asyncTask;
	ImageView picProfil;
	Button newSearch;
	Button refresh;
	Button infoParty;
	public int annee;
	public ListView list;
	public int jour;
	public ArrayList<String> text = new ArrayList<String>();
	public ArrayList<Bitmap> image = new ArrayList<Bitmap>() ;
	public int mois;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		newSearch = (Button)findViewById(R.id.newSearch);
		refresh = (Button)findViewById(R.id.actualiser);

		newSearch.setOnClickListener(choixJour);
		refresh.setOnClickListener(actualiser);

		list = (ListView)findViewById(R.id.lvListe);
			
		picProfil = (ImageView)findViewById(R.id.list_image);
	
		asyncTask = new MyTask(context, list);
		
		asyncTask.execute("");
		
		
		
	}
	
	
	public OnClickListener choixJour = new OnClickListener() {
		@Override
		public void onClick(View view) {
			Intent intent = new Intent(getApplicationContext(), Homepage1_0.class);
			startActivity(intent);
		}
	};

	
	public OnClickListener actualiser = new OnClickListener() {
		@Override
		public void onClick(View view) {
			finish();
			startActivity(getIntent());
		}
	};

	
}
