package com.dei.ceo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

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

        Intent intent = getIntent(); // 蹂대궡�삩 Intent瑜� �뼸�뒗�떎


        String detail_graphUri = intent.getStringExtra("profile");
        detail_graphUri = "images/" + detail_graphUri;

        Bitmap bitmap = loadBitmap(detail_graphUri);

        iv_profile.setImageBitmap(bitmap);
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

    }
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
                i_dial = new Intent(Intent.ACTION_CALL);
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
public Bitmap loadBitmap(String urlStr) {
        Bitmap bitmap = null;
        AssetManager mngr = getResources().getAssets();
        try{
        InputStream is = mngr.open(urlStr);
        bitmap = BitmapFactory.decodeStream(is);

        }catch(Exception e){
        // Log.e(TAG, "loadDrawable exception" + e.toString());
        }

        return bitmap;
        }
        }
