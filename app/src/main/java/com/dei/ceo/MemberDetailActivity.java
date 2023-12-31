package com.dei.ceo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;

public class MemberDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_detail);
        TextView tv_name = (TextView) findViewById(R.id.tv_detail_name);
        ImageView iv_profile = (ImageView) findViewById(R.id.iv_detail_profile);
        TextView tv_group_name = (TextView) findViewById(R.id.tv_detail_group_name);
        TextView tv_cellphone = (TextView) findViewById(R.id.tv_detail_cellphone);

        TextView tv_generall_position = (TextView) findViewById(R.id.tv_detail_general_position);

        TextView tv_group_position = (TextView) findViewById(R.id.tv_detail_group_position);
        TextView tv_birth = (TextView) findViewById(R.id.tv_detail_birth);
        TextView tv_job = (TextView) findViewById(R.id.tv_detail_job);
        TextView tv_jobaddr = (TextView) findViewById(R.id.tv_detail_jobaddr);
        TextView tv_jobtel = (TextView) findViewById(R.id.tv_detail_jobtel);
        TextView tv_jobfax = (TextView) findViewById(R.id.tv_detail_jobfax);
        TextView tv_homeaddr = (TextView) findViewById(R.id.tv_detail_homeaddr);
        TextView tv_hometel = (TextView) findViewById(R.id.tv_detail_hometel);
        ImageButton ib_sms = (ImageButton) findViewById(R.id.ib_detail_sms);
        ImageButton ib_dial = (ImageButton) findViewById(R.id.ib_detail_dial);
        Intent intent = getIntent(); // Intent를 Leadersearch,Membersearch에서 받아온다


        String detail_graphUri = intent.getStringExtra("profile");
        String profile_url = "https://dei.hivecom.co.kr/dei/profile/" + detail_graphUri;
        imgload(profile_url, iv_profile);

        tv_name.setText(intent.getStringExtra("name"));

        String group_name = intent.getStringExtra("group_name");
        int int_group_name = Integer.parseInt(group_name);
        final String str_chk_group_name = String.valueOf(int_group_name);
        if (int_group_name == 0) {
            // tv_name.setText("임원진");
            tv_group_name.setText("원장단");

        } else {
            //tv_name.setText(intent.getStringExtra("group_name")+"기");
            tv_group_name.setText(intent.getStringExtra("group_name") + "기");
    }

        tv_cellphone.setText(intent.getStringExtra("cellphone"));


        tv_birth.setText(intent.getStringExtra("birth"));

        if (intent.getStringExtra("chkgeneral") != null) {
            String str_chkgeneral = intent.getStringExtra("chkgeneral");

            int int_chkgeneral = Integer.parseInt(str_chkgeneral);


            if (int_chkgeneral == 0) {

                tv_generall_position.setVisibility(View.VISIBLE);
                tv_generall_position.setText(intent.getStringExtra("general_position"));
                tv_group_position.setVisibility(View.GONE);


            } else {
                tv_group_position.setVisibility(View.VISIBLE);
                tv_group_position.setText(intent.getStringExtra("group_position"));
                tv_generall_position.setVisibility(View.GONE);

            }
        } else {
            tv_generall_position.setText(intent.getStringExtra("general_position"));
            tv_group_position.setText(intent.getStringExtra("group_position"));
        }
        // tv_genearl_position.setText(intent.getStringExtra("general_position"));


        tv_job.setText(intent.getStringExtra("job"));
        tv_jobaddr.setText(intent.getStringExtra("job_addr"));
        tv_jobtel.setText(intent.getStringExtra("job_tel"));
        tv_jobfax.setText(intent.getStringExtra("job_fax"));
        tv_homeaddr.setText(intent.getStringExtra("home_addr"));
        tv_hometel.setText(intent.getStringExtra("home_tel"));

        final String phone_number = intent.getStringExtra("cellphone");


        ib_sms.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i_sms;
                Uri uri;

                i_sms = new Intent(Intent.ACTION_SENDTO);
                uri = Uri.parse("sms:" + phone_number.toString());
                i_sms.setData(uri);
                //  intent.putExtra("sms_body", ed2.getText().toString());
                startActivity(i_sms);

            }
        });

        ib_dial.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i_dial;
                Uri uri;

                i_dial = new Intent(Intent.ACTION_DIAL);
                uri = Uri.parse("tel:" + phone_number.toString());
                i_dial.setData(uri);
                //  intent.putExtra("sms_body", ed2.getText().toString());
                startActivity(i_dial);

            }
        });}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_member, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @SuppressLint("MissingPermission")
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_call:
                Intent i_dial;
                Uri uri;
                Intent intent = getIntent();
                String phone_number = intent.getStringExtra("cellphone");
                i_dial = new Intent(Intent.ACTION_DIAL);
                uri = Uri.parse("tel:" + phone_number.toString());
                i_dial.setData(uri);
                //  intent.putExtra("sms_body", ed2.getText().toString());
                startActivity(i_dial);

            case R.id.action_sms:
                Intent i_sms;
                Uri uri1;
                Intent intent1 = getIntent();
                String phone_number1 = intent1.getStringExtra("cellphone");
                i_sms = new Intent(Intent.ACTION_SENDTO);
                uri1 = Uri.parse("sms:" + phone_number1.toString());
                i_sms.setData(uri1);
                //  intent.putExtra("sms_body", ed2.getText().toString());
                startActivity(i_sms);

        }
        return false;
    }
    public void imgload(String profile_url, ImageView img_profile) {
        ProfileIMGLoadTask task = new ProfileIMGLoadTask(profile_url, img_profile);
        task.execute();
    }

}
