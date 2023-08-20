package com.dei.ceo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;


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
        Intent mintent = new Intent(this, LeaderSearchDetailActivity.class);
        mintent.putExtra("group_name","0");
        startActivity(mintent);
    }
}





