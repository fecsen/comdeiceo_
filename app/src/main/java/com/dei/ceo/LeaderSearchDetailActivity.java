package com.dei.ceo;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class LeaderSearchDetailActivity extends AppCompatActivity {
		ArrayList<HashMap<String, String>> mArrayList;
		String mJsonString;
		private RecyclerView lv_search;
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
		

		@SuppressLint("SuspiciousIndentation")
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_leader_detail);

			Intent intent = getIntent(); // 보내온 Intent를 얻는다
			String group_name = intent.getStringExtra("group_name");

			mArrayList = new ArrayList<>();
			lv_search=(RecyclerView)findViewById(R.id.lv_01);
			LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
			lv_search.setHasFixedSize(true);
			lv_search.setLayoutManager(layoutManager);

			int int_group_name= Integer.parseInt(group_name);
			final String str_chk_group_name= String.valueOf(int_group_name) ;

			if(int_group_name ==0) {
				this.setTitle("원장단");
			}
			else {
				this.setTitle(intent.getStringExtra("group_name") + "기");
			}
			ConnectivityManager manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo activeNetwork = manager.getActiveNetworkInfo();

			if (activeNetwork != null) {
				// connected to the internet
				if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE ) { // connected to wifi
					GetData task = new GetData();
					task.execute(str_chk_group_name);
				}
			} else {
				Intent mintent = new Intent(this, NoInternetActivity.class);
				startActivity(mintent);
				finish();
			}

		}


	private class GetData extends AsyncTask<String, Void, String> {

		ProgressDialog progressDialog;
		String errorString = null;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			progressDialog = ProgressDialog.show(LeaderSearchDetailActivity.this,
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


			String serverURL = "https://dei.hivecom.co.kr/dei/group_search.php";
			String postParameters = "group_name=" + searchKeyword1;


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
			lv_search.setAdapter(adapter);
			adapter.notifyDataSetChanged();

		} catch (JSONException e) {


		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mArrayList.clear();
	}
}
