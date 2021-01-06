package com.dei.ceo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * Created by Fecsen on 2018-02-16.
 */

public class NoInternetActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet_connect);
        ImageView imagView = (ImageView)findViewById(R.id.nointernet) ;
        Glide.with(NoInternetActivity.this).load(R.drawable.nointernett).override(300,300).into(imagView);
    }
}


