package com.dei.ceo;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

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


/**
 * Created by Fecsen on 2018-02-13.
 */

public class NoticeFragment extends AppCompatActivity {
    //DATA parsing 관련
    String url = "http://dei.hivecom.co.kr/dei/json.php";
    private static final String TAG_RESULTS="result";
    private static final String TAG_TITLE = "title";
    private static final String TAG_DATE = "date";
    private static final String TAG_MSG = "msg";
    JSONArray posts = null;
    ArrayList<HashMap<String,String>> noticeList;
    LinearLayout linear1;
    LinearLayout linear2;
    // DBhelper helper;
    //UI 관련
    private RecyclerView rv;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
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


       rv=(RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(layoutManager);

        noticeList = new ArrayList<HashMap<String, String>>();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_settings, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                noticeList.clear();
                getData(url);
        }
        return false;
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
                Collections.reverse(noticeList); //반전으로 오름차순 정렬
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
                String title = c.getString(TAG_TITLE);
                String date = c.getString(TAG_DATE);
                String msg = c.getString(TAG_MSG);

                if(msg.length() > 50 ) {
                    //msg = msg.substring(0,50) + "..."; //50자 자르고 ... 붙이기
                }
                if(title.length() > 16 ) {
                    //title = title.substring(0,16) + "..."; //18자 자르고 ... 붙이기
                }

                //HashMap에 붙이기
                HashMap<String,String> posts = new HashMap<String,String>();
                posts.put(TAG_TITLE,title);
                posts.put(TAG_DATE,date);
                posts.put(TAG_MSG, msg);
                //withshare(title,msg);
                //ArrayList에 HashMap 붙이기
                noticeList.add(posts);

            }
            //카드 리스트뷰 어댑터에 연결
            NoticeAdapter adapter = new NoticeAdapter(this,noticeList);
            rv.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }catch(JSONException e) {
            e.printStackTrace();
        }
    }
}