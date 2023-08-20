package com.dei.ceo;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ImageView;

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

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Fecsen on 2018-01-05.
 */

public class ScheduleActivity extends AppCompatActivity {


    private RecyclerView rv;

    String url = "https://dei.hivecom.co.kr/dei/schedule.php";
    private static final String TAG_RESULTS="result";
    private static final String TAG_YEAR = "year";
    private static final String TAG_MONTH = "month";
    private static final String TAG_DAY = "day";
    private static final String TAG_s_TITLE = "title";
    private static final String TAG_SUB = "sub";

    JSONArray posts = null;
    ArrayList<HashMap<String,String>> scheduleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        if (activeNetwork != null) {
            // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE ) { // connected to wifi
                getData(url);
            }
        } else {
            Intent mintent = new Intent(this, NoInternetActivity.class);
            startActivity(mintent);
            finish();
        }

        rv=(RecyclerView)findViewById(R.id.schedule_rv);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(layoutManager);

        scheduleList = new ArrayList<HashMap<String, String>>();
    }

    /** JSON 파싱 메소드 **/
    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String,Void,String> {
            @Override
            protected String doInBackground(String... params) {
                //JSON 받아온다.
                String uri = params[0];
                BufferedReader br = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    String json;
                    while((json = br.readLine()) != null) {
                        sb.append(json+"\n");
                    }
                    return sb.toString().trim();
                }catch (Exception e) {
                    return null;
                }
            }
            @Override
            protected void onPostExecute(String myJSON) {
                makeList(myJSON); //리스트를 보여줌
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

    /** JSON -> LIST 가공 메소드 **/
    public void makeList(String myJSON) {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            posts = jsonObj.getJSONArray(TAG_RESULTS);
            for(int i=0; i<posts.length(); i++) {
                //JSON에서 각각의 요소를 뽑아옴
                JSONObject c = posts.getJSONObject(i);
                String year = c.getString(TAG_YEAR);
                String month = c.getString(TAG_MONTH);
                String day = c.getString(TAG_DAY);
                String title = c.getString(TAG_s_TITLE);
                String sub = c.getString(TAG_SUB);


                //HashMap에 붙이기
                HashMap<String,String> posts = new HashMap<String,String>();
                posts.put(TAG_YEAR,year);
                posts.put(TAG_MONTH,month);
                posts.put(TAG_DAY, day);
                posts.put(TAG_s_TITLE,title);
                posts.put(TAG_SUB,sub);
                //withshare(title,msg);
                //ArrayList에 HashMap 붙이기
                scheduleList.add(posts);

            }
            //카드 리스트뷰 어댑터에 연결
            ScheduleAdapter adapter = new ScheduleAdapter(this,scheduleList);
            rv.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }catch(JSONException e) {
            e.printStackTrace();
        }
    }


}