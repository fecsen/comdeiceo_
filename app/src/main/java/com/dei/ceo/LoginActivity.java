package com.dei.ceo;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    Button b;
    EditText et, pass;
    TextView tv;
    HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;
public static Activity AActivity;
    SharedPreferences sp;
    SharedPreferences.Editor edit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AActivity = LoginActivity.this;
        b = (Button) findViewById(R.id.login);
        et = (EditText) findViewById(R.id.name);
        //tv = (TextView) findViewById(R.id.tv);
        String intxt = et.getText().toString();
        pass = (EditText) findViewById(R.id.phone);
        pass.setInputType(android.text.InputType.TYPE_CLASS_PHONE);
        pass.addTextChangedListener(new PhoneNumberFormattingTextWatcher()); //

        b.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = ProgressDialog.show(LoginActivity.this, "",
                        "로그인 중", true);
                new Thread(new Runnable() {
                    public void run() {
                        login();
                    }
                }).start();
            }
        });
    }

    void login() {

        try {

            httpclient = new DefaultHttpClient();
            httppost = new HttpPost("http://dei.hivecom.co.kr/dei/login.php"); // 로그인PHP
            // 정보불러오기
            nameValuePairs = new ArrayList<NameValuePair>(2);
            // EditText내용 불러오기
            nameValuePairs.add(new BasicNameValuePair("id", et.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("password", pass.getText().toString()));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));
            // httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);

            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
            System.out.println("결과 : " + response);
            runOnUiThread(new Runnable() {
                public void run() {
                    //tv.setText("PHP 결과 : " + response);
                    dialog.dismiss();
                }
            });

            if (response.equalsIgnoreCase("Found")) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        loginsuccess();
                    }
                });


            } else {
                loginfail();
            }

        } catch (Exception e) {
            dialog.dismiss();
            System.out.println("예외 : " + e.getMessage());
        }
    }

    public void loginfail() {
        LoginActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("로그인 실패!");
                builder.setIcon(R.drawable.failed);
                builder.setMessage("로그인에 실패 하였습니다. \n이름과 전화번호에 오류가 없는지 확인하여 주세요.")
                        .setCancelable(false)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                builder.setNegativeButton("문의 전화",

                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                Intent i_dial;
                                Uri uri;
                                i_dial = new Intent(Intent.ACTION_DIAL);
                                uri = Uri.parse("tel:01045618282");
                                i_dial.setData(uri);
                                //  intent.putExtra("sms_body", ed2.getText().toString());
                                startActivity(i_dial);

                            }

                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public void loginsuccess() {
        LoginActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                String inPutText = et.getText().toString()+"님 환영합니다.";
                builder.setTitle(inPutText);
                sp = getSharedPreferences("login",0);
                edit = sp.edit();
                builder.setIcon(R.drawable.successd);
                builder.setMessage("로그인에 성공하였습니다.")
                        .setCancelable(false)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                startActivity(new Intent(LoginActivity.this, PermissionActivity.class));
                                edit.putBoolean("login",true);
                                edit.commit();


                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialog.dismiss();
    }
}
