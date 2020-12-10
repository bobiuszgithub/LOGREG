package org.gykrdolgozat.logreg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "felhasznalo_db";

    public static final String FELHASZNALO_TABLE = "Felhasznalo";
    public static final String COL_ID = "id";
    public static final String COL_EMAIL = "email";
    public static final String COL_FELHNEV = "felhnev";
    public static final String COL_JELSZO = "jelszo";
    public static final String COL_TELJESNEV = "teljesnev";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + FELHASZNALO_TABLE + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_EMAIL + " VARCHAR(255) NOT NULL, " +
                COL_FELHNEV + " VARCHAR(255) NOT NULL, " +
                COL_JELSZO + " VARCHAR(255) NOT NULL, " +
                COL_TELJESNEV + " VARCHAR(255) NOT NULL " +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS" + FELHASZNALO_TABLE;
        db.execSQL(sql);
        onCreate(db);
    }

    public boolean adatFelvetel(String email, String felhnev, String jelszo, String teljesnev) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EMAIL, email);
        values.put(COL_FELHNEV, felhnev);
        values.put(COL_JELSZO, jelszo);
        values.put(COL_TELJESNEV, teljesnev);
        long result = db.insert(FELHASZNALO_TABLE, null, values);

        return result != -1;
    }

    public Cursor adatLekerdezes() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(FELHASZNALO_TABLE, new String[]{COL_ID, COL_EMAIL, COL_FELHNEV, COL_JELSZO, COL_TELJESNEV}, null, null, null, null, null);
    }


    public boolean NevEllen(String felhemail) {
        SQLiteDatabase db= this.getReadableDatabase();
        String selection = "felhnev LIKE ?";
        String[] selectionArgs = {felhemail};
        Cursor result = db.query(FELHASZNALO_TABLE,new String[]{COL_ID,COL_EMAIL,COL_FELHNEV,COL_JELSZO, COL_TELJESNEV},selection,selectionArgs,null,null,null,null);
        result.moveToFirst();
        return (result.getCount() == 0);
    }
//    public boolean EmailEllen(String felhemail) {
//        SQLiteDatabase db= this.getReadableDatabase();
//        Cursor resul = db.rawQuery("SELECT COUNT(*) FROM "+FELHASZNALO_TABLE+" WHERE "+ COL_EMAIL +" LIKE ? ",new String[]{felhemail});
//        resul.moveToFirst();
//        return (resul.getCount() == 1);
//    }
//    public boolean JelszoEllen(String felhemail) {
//        SQLiteDatabase db= this.getReadableDatabase();
//        Cursor resul = db.rawQuery("SELECT COUNT(*) FROM "+FELHASZNALO_TABLE+" WHERE "+ COL_JELSZO +" LIKE ? ",new String[]{felhemail});
//        resul.moveToFirst();
//        return (resul.getCount() == 1);
//    }
}
