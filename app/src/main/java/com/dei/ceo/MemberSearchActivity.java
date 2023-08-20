package com.dei.ceo;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MemberSearchActivity extends AppCompatActivity {


    TextView tv_numberoflist;


    private static String TAG = "phpinfo";
    private static final String TAG_RESULTS = "result";
    private static final String TAG_NAME = "name";
    private static final String TAG_BIRTH = "birth";
    private static final String TAG_group_name = "group_name";
    private static final String TAG_group_position = "group_position";
    private static final String TAG_job = "job";
    private static final String TAG_job_addr = "job_addr";
    private static final String TAG_job_tel = "job_tel";
    private static final String TAG_job_fax = "job_fax";
    private static final String TAG_cellphone = "cellphone";
    private static final String TAG_home_addr = "home_addr";
    private static final String TAG_home_tel = "home_tel";
    private static final String TAG_profile = "profile";

    MemberAdapter MA;

    JSONArray posts = null;
    ArrayList<HashMap<String, String>> mArrayList;

    String mJsonString;
    private RecyclerView rv_search;
    Bitmap bitmap;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memeber_search);

        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();

        if (activeNetwork != null) {
            // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE ) { // connected to wifi
                onStart();
            }
        } else {
            Intent mintent = new Intent(this, NoInternetActivity.class);
            startActivity(mintent);
            finish();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();


        mArrayList = new ArrayList<>();
        rv_search=(RecyclerView)findViewById(R.id.rv_search);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        rv_search.setHasFixedSize(true);
        rv_search.setLayoutManager(layoutManager);
        final EditText et_search = (EditText) findViewById(R.id.et_name);
        tv_numberoflist = (TextView) findViewById(R.id.tv_numberoflist);
        final Button btn_search = (Button) findViewById(R.id.btn_go_search);
        final InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        EditText testEdit = (EditText) findViewById(R.id.et_name);
        testEdit.requestFocus();
        imm.showSoftInput(et_search, 0);
        testEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // 요기서 입력된 이벤트가 무엇인지 찾아서 실행해 줌
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        btn_search.performClick();
                        imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);

                        break;
                }
                return false;
            }
        });




        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search_query = et_search.getText().toString();
                mArrayList.clear();

                if (search_query.getBytes().length <= 0)    //edittext�뿉 鍮덇컪�씠 �뱾�뼱�삤硫�
                {
                    Toast toast = Toast.makeText(MemberSearchActivity.this, "빈칸을 입력하세요!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                } else {

                    imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);
                    GetData task = new GetData();
                    task.execute(search_query);
                }
            }
        });
    }

    private class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MemberSearchActivity.this,
                    null, "회원정보를 검색중 입니다.", true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();

            if (result == null) {

                   // tv_numberoflist.setText(errorString);
            } else {

                mJsonString = result;
                showResult();
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String searchKeyword1 = params[0];


            String serverURL = "https://dei.hivecom.co.kr/dei/search.php";
            String postParameters = "name=" + searchKeyword1;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString().trim();


            } catch (Exception e) {

                errorString = e.toString();

                return null;
            }

        }
    }

    private void showResult() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject c = jsonArray.getJSONObject(i);

                String name = c.getString(TAG_NAME);
                String birth = c.getString(TAG_BIRTH);
                String group_name = c.getString(TAG_group_name);
                String group_position = c.getString(TAG_group_position);
                String job = c.getString(TAG_job);
                String job_addr = c.getString(TAG_job_addr);
                String job_tel = c.getString(TAG_job_tel);
                String job_fax = c.getString(TAG_job_fax);
                String cellphone = c.getString(TAG_cellphone);
                String home_addr = c.getString(TAG_home_addr);
                String home_tel = c.getString(TAG_home_tel);
                String profile = c.getString(TAG_profile);


                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put(TAG_NAME, name);
                hashMap.put(TAG_BIRTH, birth);
                hashMap.put(TAG_group_name, group_name);
                hashMap.put(TAG_group_position, group_position);
                hashMap.put(TAG_job, job);
                hashMap.put(TAG_job_addr, job_addr);
                hashMap.put(TAG_job_tel, job_tel);
                hashMap.put(TAG_job_fax, job_fax);
                hashMap.put(TAG_cellphone, cellphone);
                hashMap.put(TAG_home_addr, home_addr);
                hashMap.put(TAG_home_tel, home_tel);
                hashMap.put(TAG_profile, profile);


                mArrayList.add(hashMap);
            }


            //카드 리스트뷰 어댑터에 연결
            MemberAdapter adapter = new MemberAdapter(this,mArrayList);
            //Log.e("onCreate[noticeList]", "" + mArrayList.size());
            rv_search.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            int totalItemRows = adapter.mArrayList.size();
            tv_numberoflist.setText("검색하신 결과는 " + totalItemRows + "명 입니다." );

        } catch (JSONException e) {


        }


    }

}


