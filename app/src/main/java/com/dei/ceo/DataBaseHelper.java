package com.dei.ceo;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.preference.PreferenceManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.dei.ceo/databases/";
    private static String DB_NAME = "member.db";
    private static final int DB_VERSION  = 5;
    private static final String SP_KEY_DB_VER = "db_ver";
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    public DataBaseHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
        this.myContext=context;
    }


    public void createDataBase() throws IOException
    {
        boolean dbExist = checkDataBase();
        if(dbExist)
        {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(myContext);
            int dbVersion = prefs.getInt(SP_KEY_DB_VER, 18);
            if (DB_VERSION != dbVersion)
            {
                File dbFile = myContext.getDatabasePath(DB_PATH+DB_NAME);
                if (!dbFile.delete()) {

                }
                this.getReadableDatabase();
                try {
                    copyDataBase();

                }
                catch (IOException e) {
                    //throw new Error("Error copying database");

                }
            }
            //do nothing - database already exist
            else {
                //  Toast.makeText(myContext, "DB媛� 媛숇꽕�슂", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
            try {
                copyDataBase();

            }
            catch (IOException e) {
                throw new Error("Error copying database");

            }
        }

        // Shareprerece �뿉 DB 踰꾩쟾 �엯�젰�빐�꽌 李⑦썑 DB�� 鍮꾧탳�븯湲� �쐞�빐
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(myContext);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(SP_KEY_DB_VER, DB_VERSION);
        editor.commit();
    }
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY );
        }
        catch(SQLiteException e) {
            //database does't exist yet.

        }
        if(checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }
    private void copyDataBase() throws IOException {
        //Open your local db as the input stream
      //  SQLiteDatabase dbe = SQLiteDatabase.openDatabase("/data/data/com.fecsen.deiceo/member.db",null, 0);
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;
        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
              while ((length = myInput.read(buffer))>0)
        {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }
    public void openDataBase() throws SQLException
    {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close()
    {

        if(myDataBase != null)
            myDataBase.close();

        super.close();

    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        try {
            copyDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
