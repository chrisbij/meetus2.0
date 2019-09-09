package vue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.meetus.R;

import controller.MyTask;

/**
 * Created by Doudou on 02/09/2015.
 */
public class Agenda extends Activity {

    ListView listView;
    public TextView textView;
    Button creatActivite, agenda;
    final Context context = this;
    public MyTask myTask;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.agenda);

        listView = (ListView)findViewById(R.id.lvListeAgenda);
        textView = (TextView)findViewById(R.id.textView5);

        textView.setVisibility(View.INVISIBLE);


        myTask = new MyTask(context, listView);

        myTask.execute("");

        creatActivite = (Button)findViewById(R.id.createActivite);

        creatActivite.setOnClickListener(openCreateActivity);

        agenda = (Button)findViewById(R.id.agenda);
        agenda.setBackgroundColor(Color.parseColor("#808080"));

    }

    public View.OnClickListener openCreateActivity = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), CreateParty.class);
            startActivity(intent);
        }
    };

}
