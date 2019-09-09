package vue.createUser;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meetus.MainActivity;
import com.example.meetus.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import controller.Connexion;
import vue.MainVue;

/**
 * Created by bijou on 18/08/2015.
 */
public class CreateUser extends MainActivity {

    ArrayList<NameValuePair> arrayList;

    public JSONObject json_data;

    String resultat;

    Connexion connexion =new Connexion();

    String nom, prenom, adresse, tel, password, passConfirm, mail, codePostale, ville, pays;
    Button valider;

    TextView nomUser, prenomUser, adrUser, telUser, passwordUser, passConfirmUser, mailUser, cpUser, villeUser, paysUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.create_user);

        nomUser = (TextView)findViewById(R.id.nomUser);
        prenomUser = (TextView)findViewById(R.id.prenUser);
        adrUser = (TextView)findViewById(R.id.adrUser);
        telUser = (TextView)findViewById(R.id.telUser);
        passwordUser = (TextView)findViewById(R.id.passwordUser);
        passConfirmUser = (TextView)findViewById(R.id.confPassUser);
        mailUser = (TextView)findViewById(R.id.mailUser);
        cpUser = (TextView)findViewById(R.id.cpUser);
        villeUser = (TextView)findViewById(R.id.villeUser);
        paysUser = (TextView)findViewById(R.id.paysUser);

        valider = (Button)findViewById(R.id.validCreateUser);

        valider.setOnClickListener(addNewUser);

        arrayList = new ArrayList<NameValuePair>();

    }


    public View.OnClickListener addNewUser = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            arrayList.clear();

            if(!nomUser.getText().toString().equals("")){
                nom = nomUser.getText().toString();
                arrayList.add(new BasicNameValuePair("nomUser", nom));
            }else{
                Toast erreur = Toast.makeText(getApplicationContext(), "Veuillez renseigner votre nom", Toast.LENGTH_SHORT);
                erreur.show();
                return;
            }

            if(!prenomUser.getText().toString().equals("")){
                prenom = prenomUser.getText().toString();
                arrayList.add(new BasicNameValuePair("prenUser", prenom));
            }else{
                Toast erreur = Toast.makeText(getApplicationContext(), "Veuillez renseigner votre pr�nom", Toast.LENGTH_SHORT);
                erreur.show();
                return;
            }

          /*  if(!adrUser.getText().toString().equals("")){
                adresse = adrUser.getText().toString();
                arrayList.add(new BasicNameValuePair("adrUser", adresse));
            }else{
                Toast erreur = Toast.makeText(getApplicationContext(), "Veuillez renseigner votre adresse", Toast.LENGTH_SHORT);
                erreur.show();
                return;
            }

            if(!telUser.getText().toString().equals("")){
                tel = telUser.getText().toString();
                arrayList.add(new BasicNameValuePair("telUser", tel));
            }else{
                Toast erreur = Toast.makeText(getApplicationContext(), "Veuillez renseigner votre num�ro de t�l�phone", Toast.LENGTH_SHORT);
                erreur.show();
                return;
            }

            if(!mailUser.getText().toString().equals("")){
                mail = mailUser.getText().toString();
                arrayList.add(new BasicNameValuePair("mailUser", mail));
            }else{
                Toast erreur = Toast.makeText(getApplicationContext(), "Veuillez renseigner votre adresse mail", Toast.LENGTH_SHORT);
                erreur.show();
                return;
            }

            if(!passwordUser.getText().toString().equals("")){
                password = passwordUser.getText().toString();
            }else{
                Toast erreur = Toast.makeText(getApplicationContext(), "Veuillez entrer un mot de passe", Toast.LENGTH_SHORT);
                erreur.show();
                return;
            }

            if(!passConfirmUser.getText().toString().equals("")){
                if(passConfirmUser.getText().toString().equals(password)){
                    passConfirm = passConfirmUser.getText().toString();
                    arrayList.add(new BasicNameValuePair("passUser", passConfirm));
                }else{
                    Toast erreur = Toast.makeText(getApplicationContext(), "Les mots de passes ne correspondent pas", Toast.LENGTH_SHORT);
                    erreur.show();
                    return;
                }
            }else{
                Toast erreur = Toast.makeText(getApplicationContext(), "Veuillez confirmer votre mot de passe", Toast.LENGTH_SHORT);
                erreur.show();
                return;
            }

            if(!cpUser.getText().toString().equals("")){
                if(cpUser.getText().toString().matches("^([0-9]{5}$)")) {
                    codePostale = cpUser.getText().toString();
                    arrayList.add(new BasicNameValuePair("cpUser", codePostale));
                }else{
                    Toast erreur = Toast.makeText(getApplicationContext(), "Veuillez respecter le format du code postale.", Toast.LENGTH_SHORT);
                    erreur.show();
                    return;
                }
            }else{
                Toast erreur = Toast.makeText(getApplicationContext(), "Veuillez saisir un code postale.", Toast.LENGTH_SHORT);
                erreur.show();
                return;
            }

            if(!villeUser.getText().toString().equals("")){
                ville = villeUser.getText().toString();
                arrayList.add(new BasicNameValuePair("villeUser", ville));
            }else{
                Toast erreur = Toast.makeText(getApplicationContext(), "Veuillez saisir une ville", Toast.LENGTH_SHORT);
                erreur.show();
                return;
            }

            if(!paysUser.getText().toString().equals("")){

                pays = paysUser.getText().toString();
                arrayList.add(new BasicNameValuePair("paysUser", pays));
            }else{
                Toast erreur = Toast.makeText(getApplicationContext(), "Veuillez saisir un pays.", Toast.LENGTH_SHORT);
                erreur.show();
                return;
            }*/

            arrayList.add(new BasicNameValuePair("sexeUser", "M"));

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                       /* JSONArray jsonArray = connexion.createNewUser(arrayList);
                        if(!connexion.go) {
                            return;
                        }else{
                            json_data = jsonArray.getJSONObject(0);
                            resultat = json_data.getString("result");
                            Log.e("resultat", resultat);
                            if(resultat.equals("succes")){*/
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(getApplicationContext(), MainVue.class);
                                        intent.putExtra("Nom", nom);
                                        intent.putExtra("Prenom", prenom);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                          // }
                       // }
                    }catch (Exception e){

                    }
                }
            }).start();


        }
    };

}
