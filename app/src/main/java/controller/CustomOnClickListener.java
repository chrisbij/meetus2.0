package controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import vue.SelectDay;

/**
 * Created by Doudou on 16/08/2015.
 */
public class CustomOnClickListener implements View.OnClickListener {

    View bouton;
    Context context;
    String image;

    public Intent intentCustom;

    public CustomOnClickListener(View arg, Context con, String string){
        bouton = arg;
        context = con;
        image = string;
    }


    @Override
    public void onClick(View view) {
        int colorId = ((ColorDrawable) bouton.getBackground()).getColor();
        int imgId = context.getResources().getIdentifier(image, "drawable", context.getPackageName());

        try{
            Intent intent = new Intent(context, SelectDay.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("COULEUR", colorId);
            intent.putExtra("IMAGE", imgId);
            intentCustom = intent;

            context.startActivity(intent);

        }catch (Exception e){
            Log.e("machin", e.toString());
        }
    }

    public Intent getIntentCustom(){
     return intentCustom;
    }

}
