package vue;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.meetus.R;

import controller.CustomOnClickListener;

/**
 * Created by Doudou on 17/08/2015.
 */
public class PageMilieuFragement extends android.support.v4.app.Fragment implements View.OnClickListener {

    Context context;
    int colorId, imgId;

    Button vacances, musique, shopping, sport;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       context = getActivity().getApplicationContext();

        View inputFragmentView = inflater.inflate(R.layout.page_centre_layout, container, false);

        vacances = (Button)inputFragmentView.findViewById(R.id.buttonVacance);

        vacances.setOnClickListener(this);

        return inputFragmentView;

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.buttonVacance:
                colorId = ((ColorDrawable) view.getBackground()).getColor();
                imgId = context.getResources().getIdentifier("shop", "drawable", context.getPackageName());
            break;
        }
     /*   int colorId = ((ColorDrawable) view.getBackground()).getColor();
        int imgId = context.getResources().getIdentifier("shop", "drawable", context.getPackageName());*/

        try{
            Intent intent = new Intent(context, SelectDay.class);
            intent.putExtra("COULEUR", colorId);
            intent.putExtra("IMAGE", imgId);
            startActivity(intent);

        }catch (Exception e){
            Log.e("machin", e.toString());
        }
    }
}
