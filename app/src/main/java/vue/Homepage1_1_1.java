package vue;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.meetus.R;

import java.util.List;
import java.util.Vector;

import controller.CustomOnClickListener;
import controller.MyPagerAdapter;
import vue.rechercheActivite.PageDroiteFragment;
import vue.rechercheActivite.PageGaucheFragment;
import vue.rechercheActivite.PageMilieuFragement;

/**
 * Created by bijou on 07/08/2015.
 */
public class Homepage1_1_1 extends FragmentActivity{

    Button annuler;
    Button vacances, musique, shopping, sport;

    private PagerAdapter mPagerAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.setContentView(R.layout.test);

        // Creation de la liste de Fragments que fera defiler le PagerAdapter
        List fragments = new Vector();

        // fragments.add(android.support.v4.app.Fragment.instantiate(this, PageGaucheFragment.class.getName()));

        // Ajout des Fragments dans la liste
        fragments.add(android.support.v4.app.Fragment.instantiate(this, PageGaucheFragment.class.getName()));
        fragments.add(android.support.v4.app.Fragment.instantiate(this, PageMilieuFragement.class.getName()));
        fragments.add(android.support.v4.app.Fragment.instantiate(this, PageDroiteFragment.class.getName()));

        // Creation de l'adapter qui s'occupera de l'affichage de la liste de
        // Fragments
        this.mPagerAdapter = new MyPagerAdapter(super.getSupportFragmentManager(), fragments);

        ViewPager pager = (ViewPager) super.findViewById(R.id.viewPager);
        // Affectation de l'adapter au ViewPager
        pager.setAdapter(this.mPagerAdapter);

        vacances = (Button)findViewById(R.id.vacances);
        musique = (Button)findViewById(R.id.musique);
        shopping = (Button)findViewById(R.id.shopping);
        sport = (Button)findViewById(R.id.photo);


        annuler = (Button)findViewById(R.id.annulerHP111);

        annuler.setOnClickListener(annuleRecherche);
        vacances.setOnClickListener(new CustomOnClickListener(vacances,getApplicationContext(),"shop"));
        musique.setOnClickListener(new CustomOnClickListener(musique,getApplicationContext(), "radio"));
        shopping.setOnClickListener(new CustomOnClickListener(shopping, getApplicationContext(), "shopping"));
        sport.setOnClickListener(new CustomOnClickListener(sport, getApplicationContext(), "polaroid"));

    }


    public View.OnClickListener annuleRecherche = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try{
                Intent intent = new Intent(getApplicationContext(), MainVue.class);
                startActivity(intent);

            }catch (Exception e){
                Log.e("machin", e.toString());
            }
        }
    };
}
