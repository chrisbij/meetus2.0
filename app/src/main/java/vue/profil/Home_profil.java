package vue.profil;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;

import com.example.meetus.R;

/**
 * Created by chrislet on 27/01/2016.
 */
public class Home_profil extends Activity {


    ImageView photoProfil;
    Bitmap bitmap;
    Bitmap output;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profil_user);

        photoProfil = (ImageView)findViewById(R.id.imgEntete);


       /* bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.chris);*/

       // photoProfil.setBackground(new BitmapDrawable(Resources.getSystem(), bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.chris)));

        bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.chris), 140, 140,true);
        bitmap = getCroppedBitmap(bitmap);

        photoProfil.setImageBitmap(bitmap);



    }



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

  /*  public void onBackPressed(){
        Log.e("bitmap", "BitmapFree");
        super.onBackPressed();

        output.recycle();
        output = null;

        finish();
    }*/

}
