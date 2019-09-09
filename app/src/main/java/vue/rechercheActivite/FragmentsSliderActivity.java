package vue.rechercheActivite;

import android.content.Intent;
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

import controller.MyPagerAdapter;
import vue.MainVue;

/**
 * Created by Doudou on 17/08/2015.
 */
public class FragmentsSliderActivity extends FragmentActivity {

    private PagerAdapter mPagerAdapter;
    Button annuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.homepage1_1_1);

        annuler = (Button)findViewById(R.id.annulerHP111);

        annuler.setOnClickListener(annuleRecherche);

        // Création de la liste de Fragments que fera défiler le PagerAdapter
        List fragments = new Vector();

       // fragments.add(android.support.v4.app.Fragment.instantiate(this, PageGaucheFragment.class.getName()));

        // Ajout des Fragments dans la liste
        fragments.add(android.support.v4.app.Fragment.instantiate(this, PageGaucheFragment.class.getName()));
        fragments.add(android.support.v4.app.Fragment.instantiate(this, PageMilieuFragement.class.getName()));
        fragments.add(android.support.v4.app.Fragment.instantiate(this, PageDroiteFragment.class.getName()));

        // Création de l'adapter qui s'occupera de l'affichage de la liste de
        // Fragments
        this.mPagerAdapter = new MyPagerAdapter(super.getSupportFragmentManager(), fragments);

        ViewPager pager = (ViewPager) super.findViewById(R.id.viewPager);
        // Affectation de l'adapter au ViewPager
        pager.setAdapter(this.mPagerAdapter);

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
