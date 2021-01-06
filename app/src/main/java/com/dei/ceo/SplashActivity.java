package com.dei.ceo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.widget.LinearLayout;

public class SplashActivity extends Activity {

Animation ma;
    LinearLayout linear;
    SharedPreferences sp;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {

            @Override
            public void run() {
             sp = getSharedPreferences("login",0);
                //로그인 초기화시
          //  edit = sp.edit();
          //  edit.clear();
          //   edit.commit();
                //로그인 초기화시 끝
                if(sp.getBoolean("login",false) == false) {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }

                finish();
            }
        }, 1500);
    }

}

