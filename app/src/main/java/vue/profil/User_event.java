package vue.profil;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.meetus.R;

/**
 * Created by bijou on 04/04/2017.
 */
public class User_event extends android.support.v4.app.Fragment implements View.OnClickListener {

    Context mContext;
    Button event;
    Home_profil home_profil = new Home_profil();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity().getApplicationContext();

        View inputFragmentView = inflater.inflate(R.layout.user_event, container, false);


        return inputFragmentView;

    }

    @Override
    public void onClick(View v) {

    }
}
