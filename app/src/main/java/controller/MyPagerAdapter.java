package controller;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Doudou on 17/08/2015.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    private final List fragments;

    //On fournit à l'adapter la liste des fragments à afficher
    public MyPagerAdapter(FragmentManager fm, List fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return (android.support.v4.app.Fragment) this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }
}
