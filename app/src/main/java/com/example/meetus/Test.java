package com.example.meetus;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import controller.Connexion;

/**
 * Created by bijou on 06/11/2015.
 */


public class Test extends Activity {

    Button button;
    ImageView imageView;

    public Bitmap bitmap;
    Connexion connexion = new Connexion();

    InputStream input;

    public Test() throws MalformedURLException {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test);

        button = (Button)findViewById(R.id.button);
        imageView = (ImageView)findViewById(R.id.imageView2);

        button.setOnClickListener(affiche);

    }


    public View.OnClickListener affiche = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Log.e("Bonjour", "Bonjour");
            FileInputStream in ;

               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       try {
                           URL  url = new URL("http://meetus.noip.me/meetus/media/images/activites/images.jpg");
                           HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                           connection.setDoInput(true);

                           input = connection.getInputStream();

                           final BufferedInputStream bufferedInputStream = new BufferedInputStream(input);
                           bitmap = BitmapFactory.decodeStream(bufferedInputStream);


                          runOnUiThread(new Runnable() {
                              @Override
                              public void run() {
                                  imageView.setBackground(new BitmapDrawable(Resources.getSystem(), bitmap));
                              }
                          });
                       } catch (Exception e) {
                           Log.e("Erreur", e.toString());
                       }
                   }}).start();



              //  in = new FileInputStream("/sdcard/images.jpg");


        }
    };

}
