package com.dei.ceo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class SearchActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search);

        }
    public void memberr(View v) {
        Intent mintent = new Intent(this, MemberSearchActivity.class);
        startActivity(mintent);
    }
    public void leaderr(View v) {
        Intent mintent = new Intent(this, LeaderSearchActivity.class);
        startActivity(mintent);
    }
    public void bosss(View v) {
        Intent mintent = new Intent(this, ProfessionalActivity.class);
        mintent.putExtra("group_name","0");
        startActivity(mintent);
    }
}





