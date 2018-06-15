package vue.profil;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.meetus.R;

import java.util.List;
import java.util.Vector;

import controller.MyPagerAdapter;
import vue.Agenda;
import vue.MainVue;
import vue.rechercheActivite.PageDroiteFragment;
import vue.rechercheActivite.PageGaucheFragment;
import vue.rechercheActivite.PageMilieuFragement;

/**
 * Created by chrislet on 27/01/2016.
 */
public class Home_profil extends FragmentActivity {


    ImageView photoProfil;
    Bitmap bitmap;
    Bitmap output;
    Button  agenda, activity, profil;
    public String pronomPersonnel;

    private PagerAdapter mPagerAdapter;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profil_user);

        profil = (Button)findViewById(R.id.profil);

       // profil.setBackgroundColor(0xff888888);
        profil.setBackground(getResources().getDrawable(R.drawable.btn_grey_selected, null));
        activity = (Button)findViewById(R.id.activity);
        agenda = (Button)findViewById(R.id.agenda);

        photoProfil = (ImageView)findViewById(R.id.imgEntete);


       /* bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.chris);*/

       // photoProfil.setBackground(new BitmapDrawable(Resources.getSystem(), bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.chris)));

        bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.chris), 140, 140, true);
        bitmap = getCroppedBitmap(bitmap);

        photoProfil.setImageBitmap(bitmap);


        // Cr�ation de la liste de Fragments que fera d�filer le PagerAdapter
        List fragments = new Vector();

        // fragments.add(android.support.v4.app.Fragment.instantiate(this, PageGaucheFragment.class.getName()));

        // Ajout des Fragments dans la liste
        fragments.add(android.support.v4.app.Fragment.instantiate(this, Presentation_user.class.getName()));
        fragments.add(android.support.v4.app.Fragment.instantiate(this, Photo_user.class.getName()));
        fragments.add(android.support.v4.app.Fragment.instantiate(this, User_event.class.getName()));

        // Cr�ation de l'adapter qui s'occupera de l'affichage de la liste de
        // Fragments
        this.mPagerAdapter = new MyPagerAdapter(super.getSupportFragmentManager(), fragments);

        ViewPager pager = (ViewPager) super.findViewById(R.id.viewPager);
        // Affectation de l'adapter au ViewPager
        pager.setAdapter(this.mPagerAdapter);

        String testPager = pager.getTransitionName();
        CharSequence NomPage = mPagerAdapter.getPageTitle(pager.getCurrentItem());


        Log.e("Adpter", "Nom de la vue :" +testPager + NomPage);

        activity.setOnClickListener(openActivityVue);
        agenda.setOnClickListener(openAgendaVue);

    }

    public View.OnClickListener openActivityVue = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), MainVue.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }
    };

    public View.OnClickListener openAgendaVue = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), Agenda.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }
    };

    public Bitmap getCroppedBitmap(Bitmap bitmap){

        output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0,0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);

        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;

    }


    public boolean onKeyDown(int keyCode, KeyEvent event){
        if ((keyCode == KeyEvent.KEYCODE_BACK)){
            onBackPressed();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void onBackPressed(){
        Log.e("bitmap", "BitmapFree");
        super.onBackPressed();

        output.recycle();
        output = null;

        finish();
    }

}
