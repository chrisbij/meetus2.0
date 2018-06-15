package vue.profil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meetus.R;

/**
 * Created by bijou on 15/03/2017.
 */
public class Photo_user extends android.support.v4.app.Fragment implements View.OnClickListener {

    Context mContext;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity().getApplicationContext();

        View inputFragmentView = inflater.inflate(R.layout.photo_user, container, false);

        return inputFragmentView;
    }

    @Override
    public void onClick(View v) {

    }
}
