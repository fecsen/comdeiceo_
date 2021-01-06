package com.dei.ceo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Window;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class showAlert extends Activity {

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        String title, body;
        Bundle bun = getIntent().getExtras();
        title = bun.getString("title");
        body = bun.getString("body");


        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(showAlert.this);
        alert_confirm.setTitle(title);
        alert_confirm.setMessage(body).setCancelable(false).setPositiveButton("보기",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent mintent = new Intent(showAlert.this, NoticeFragment.class);
                        startActivity(mintent);
                        finish();
                    }
                }).setNegativeButton("확인",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(showAlert.this, "해당사항은 앱에서 다시 확인 하실 수 있습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
        AlertDialog alert = alert_confirm.create();
        alert.show();



        // 폰 설정의 조명시간을 가져와서 해당 시간만큼만 화면을 켠다.



    }

}