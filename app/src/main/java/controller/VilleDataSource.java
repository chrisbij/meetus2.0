package controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.MySQLiteHelper;
import model.Ville;

/**
 * Created by bijou on 25/09/2015.
 */
public class VilleDataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {MySQLiteHelper.colum_id, MySQLiteHelper.column_nom};
    private String[] column = {MySQLiteHelper.column_nom};


    public VilleDataSource(Context context){
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public Ville createVille(String ville){

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.column_nom, ville);

        long insertId = database.insert(MySQLiteHelper.TABLE_VILLE, null, values);

        Cursor cursor = database.query(MySQLiteHelper.TABLE_VILLE, allColumns, MySQLiteHelper.colum_id + " = " + insertId, null, null, null, null);

        cursor.moveToFirst();

        Ville newVille = cursorToVille(cursor);
        cursor.close();
        return newVille;
    }

    public void insertVille(String s){
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.column_nom, s);
        database.insert(MySQLiteHelper.TABLE_VILLE, null, values);
    }

   /* public List<Ville> getAllVilles(){

        List<Ville> villes = new ArrayList<Ville>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_VILLE, allColumns,null,null,null,null,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Ville ville = cursorToVille(cursor);
            villes.add(ville);
            cursor.moveToNext();
        }

        cursor.close();
        return villes;
    }*/

    public List<Ville> getVille(){
        List<Ville> citys = new ArrayList<Ville>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_VILLE, allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Ville city = cursorToVille(cursor);
            citys.add(city);
            cursor.moveToNext();
        }

        cursor.close();

        return citys;
    }

    private Ville cursorToVille(Cursor cursor){
        Ville ville = new Ville();
        ville.setId_ville(cursor.getLong(0));
        ville.setNom_ville(cursor.getString(1));
        return ville;
    }
}
