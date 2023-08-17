package com.dei.ceo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.StrictMode;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ImageView imgView;
    String myJSON;
    private static final String TAG_RESULTS="result";
    private static final String TAG_TITLE = "title";
    private static final String TAG_DATE ="date";
    Handler handler = new Handler();
    ImageView iv;
    JSONArray peoples = null;
    private int ver = 55;
    private ProgressDialog dialog;
    String xml;
    ArrayList<HashMap<String, String>> personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkupdate();
        mainimg();
        personList = new ArrayList<HashMap<String, String>>();
    }

    public void mainimg() {
        // 메인 이미지, 공지사항 출력
        imgView = (ImageView) findViewById(R.id.img01);
        ConnectivityManager manager = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        if (activeNetwork != null) {
            // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE ) { // 인터넷이 연결되었을 때
                getData("http://dei.hivecom.co.kr/dei/json.php"); // 메인 공지사항 받아오기 (제목, 날짜)

            }
        } else { // 아닐 때
            Toast.makeText(this, "네트워크에 연결되지 않았습니다 !\n일부 기능 사용에 제한을 받을 수 있습니다 !", Toast.LENGTH_LONG).show();
        }
    }

    public void checkupdate() {
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        dialog.setMessage(getString((R.string.check_version)));
        dialog.show();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        StringBuilder sBuffer = new StringBuilder();
        try {//Start Try
            String urlAddr = "http://dei.hivecom.co.kr/dei/check.xml";
            URL url = new URL(urlAddr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (conn != null) {//Start if
                conn.setConnectTimeout(20000);
                //conn.setUseCaches(false);
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {//Start if
                    InputStreamReader isr = new InputStreamReader(conn.getInputStream());
                    BufferedReader br = new BufferedReader(isr);
                    while (true) {//Start While
                        String line = br.readLine();
                        if (line == null) {//Start if
                            break;
                        }//end if
                        sBuffer.append(line);
                    }//end while
                    br.close();
                    conn.disconnect();
                }//end if
            }//end if
            xml = sBuffer.toString();
            CountDownTimer _timer = new CountDownTimer(1000, 1000) {
                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    dialog.dismiss();
                    if (Integer.parseInt(xml) == ver) {//new version
//                        Toast.makeText(getApplicationContext(), R.string.latest_version, Toast.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(xml) > ver) {//현재버전보다 서버버전이 높을때
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle(R.string.low_version);
                        builder.setMessage(R.string.plz_update);
                        builder.setCancelable(false);
                        builder.setNegativeButton(R.string.later, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        builder.setPositiveButton(R.string.update_now, new
                                DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent myIntent = new Intent
                                                (Intent.ACTION_VIEW, Uri.parse
                                                        ("https://play.google.com/store/apps/details?id=com.dei.ceo"));
                                        startActivity(myIntent);
                                    }
                                });
                        builder.show();
                    } else {//현재버전보다 서버 버전이 낮을때
                    }
                }
            };
            _timer.start();
        }//end try
        catch (Exception e) {//네트워크가 올바르지 않을때
            Toast.makeText(getApplicationContext(), R.string.offline, Toast.LENGTH_LONG).show();
            dialog.cancel();
        }

    }

    public void noticee(View v) {
        Intent mintent = new Intent(this, NoticeFragment.class);
        startActivity(mintent);
    }
    public void openn(View v) {
        Intent mintent = new Intent(this, NoticeFragment.class);
        startActivity(mintent);
    }
    public void title11(View v) {
        Intent mintent = new Intent(this, NoticeFragment.class);
        startActivity(mintent);
    }

    public void searchh(View v) {
        Intent mintent = new Intent(this, SearchActivity.class);
        startActivity(mintent);
    }

    public void advv(View v) {
        Toast.makeText(this, "개선중입니다.", Toast.LENGTH_SHORT).show();
    }

    public void schedulee(View v) {
        Intent mintent = new Intent(this, ScheduleActivity.class);
        startActivity(mintent);
    }

    public void laww(View v) {
        Intent mintent = new Intent(this, LawActivity.class);
        startActivity(mintent);
    }

    public void etherr(View v) {
        Intent mintent = new Intent(this, EthernetActivity.class);
        startActivity(mintent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialog.dismiss();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dialog.dismiss();
    }

    @Override
    protected void onStop() {
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        dialog.dismiss();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        dialog.dismiss();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dialog.dismiss();
    }
    @Override
    protected void onStart() {
        super.onStart();
        dialog.dismiss();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {    // 인트로 사진 불러오는 쓰레드 돌리기
                // TODO Auto-generated method stub
                try{

                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            ImageView imagView = (ImageView)findViewById(R.id.img01) ;
                            Glide.with(MainActivity.this).load("http://dei.hivecom.co.kr/dei/photo/intro.png").diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true).into(imagView);

                        }
                    });

                } catch(Exception e){

                }

            }
        });
        t.start();

    }

    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                String title = c.getString(TAG_TITLE);
                TextView ti1t = (TextView)findViewById(R.id.title1);
                TextView dat = (TextView)findViewById(R.id.date1);
                //   String msg = c.getString(TAG_MSG);
                String date = c.getString(TAG_DATE);
                HashMap<String,String> persons = new HashMap<String,String>();
                persons.put(TAG_TITLE,title);
                //    persons.put(TAG_MSG,msg);
                persons.put(TAG_DATE,date);

                personList.add(persons);
                if(title.length() > 18 ) {
                    title = title.substring(0,18) + "..."; //자르고 ... 붙이기
                }
                ti1t.setText(title);
                dat.setText(date);

            }

            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, personList, R.layout.item_notice_cardview,
                    new String[]{TAG_TITLE,TAG_DATE},
                    new int[]{R.id.tv_title, R.id.tv_date}
            );





        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getData(String url){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }
                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }



            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                showList();
                Collections.reverse(personList);
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }


        }



