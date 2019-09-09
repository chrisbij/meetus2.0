package vue.rechercheActivite;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import com.example.meetus.R;

/**
 * Created by Doudou on 12/11/2015.
 */
public class SetInfoActivite extends Activity{

    View context;
    RelativeLayout relativeLayout;
    Bitmap bitmap;

    public SetInfoActivite(View a, Bitmap bm){
        context = a;
        bitmap = bm;
    }

    public void setInfoActivite(){
        relativeLayout = (RelativeLayout)context.findViewById(R.id.entete);
        relativeLayout.setBackground(new BitmapDrawable(Resources.getSystem(), bitmap));
    }
}
