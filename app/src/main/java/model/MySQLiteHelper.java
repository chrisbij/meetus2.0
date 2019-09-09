package model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by bijou on 25/09/2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_VILLE = "ville";
    public static final String colum_id = "ID_VILLE";
    public static final String column_nom = "ville_nom_reel";

    private static final String database_name = "converted.sqlite";
    private static final int database_version = 1;
    private String DATABASE_PATH;
    private final Context mycontext;

    // Commande sql pour la création de la base de données
    /*private static final String DATABASE_CREATE = "create table "
            + TABLE_VILLE + "(" + colum_id
            + " integer primary key autoincrement, " + column_nom
            + " text not null UNIQUE);";*/

    public MySQLiteHelper(Context context){
        super(context, database_name, null, database_version);
        this.mycontext=context;
        String filesDir = context.getFilesDir().getPath();

        DATABASE_PATH = filesDir.substring(0, filesDir.lastIndexOf("/")) + "/databases/";

        if(!checkdatabase()){
            copydatabase();
        }
    }


    private boolean checkdatabase(){
        File dbfile = new File(DATABASE_PATH + database_name);
        return dbfile.exists();
    }

    private void copydatabase(){
        final String outFileName = DATABASE_PATH + database_name;

        InputStream myInput;

        try {
            myInput = mycontext.getAssets().open(database_name);

            File pathFile = new File(DATABASE_PATH);
            if(!pathFile.exists()){
                if(!pathFile.mkdirs()){
                    Toast.makeText(mycontext, "Erreur", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            OutputStream myOutput = new FileOutputStream(outFileName);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = myInput.read(buffer)) > 0){
                myOutput.write(buffer, 0, length);
            }

            myOutput.flush();
            myOutput.close();
            myInput.close();
        }catch (IOException e){
            Toast.makeText(mycontext, "Bordel de merde", Toast.LENGTH_SHORT).show();
        }

        try{
            SQLiteDatabase chekdb = SQLiteDatabase.openDatabase(DATABASE_PATH + database_name, null, SQLiteDatabase.OPEN_READWRITE);
            chekdb.setVersion(database_version);
        }catch (SQLiteException e){

        }
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

       /* sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_VILLE);
        sqLiteDatabase.execSQL(DATABASE_CREATE);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldversion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(), "Upgradind database from version " + oldversion + " to " + newVersion + ", which will destroyall old data");

       // sqLiteDatabase.execSQL("DROP TABLE IF EXIST" + TABLE_VILLE);
        onCreate(sqLiteDatabase);
    }
}
