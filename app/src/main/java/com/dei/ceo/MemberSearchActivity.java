package com.dei.ceo;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MemberSearchActivity extends AppCompatActivity {

    ListView listview_search = null;
    DataBaseHelper myDbHelper_search;
    SQLiteDatabase db_search;
    String query_search;
    Cursor cursor_search;
    DBAdapter_search myadapter;
    TextView tv_numberoflist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memeber_search);

        final EditText et_search = (EditText) findViewById(R.id.et_name);
        tv_numberoflist = (TextView) findViewById(R.id.tv_numberoflist);
        final Button btn_search = (Button) findViewById(R.id.btn_go_search);
        final InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        EditText testEdit = (EditText) findViewById(R.id.et_name);
        testEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                                               @Override
                                               public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                                                   // 요기서 입력된 이벤트가 무엇인지 찾아서 실행해 줌
                                                   switch(actionId) {
                                                       case EditorInfo.IME_ACTION_DONE:
                                                           btn_search.performClick();
                                                           break;
                                                   }
                                                   return false;
                                               }
        });



        imm.showSoftInput(et_search, 0);


        myDbHelper_search = new DataBaseHelper(null);
        myDbHelper_search = new DataBaseHelper(this);

        btn_search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String search_query = et_search.getText().toString();


                if (search_query.getBytes().length <= 0)    //edittext�뿉 鍮덇컪�씠 �뱾�뼱�삤硫�
                {
                    Toast toast = Toast.makeText(MemberSearchActivity.this, "빈칸을 입력하세요!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                } else {
                    //�궎蹂대뱶瑜� �뾾�븻�떎.
                    imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);

                    try {

                        myDbHelper_search.createDataBase();
                    } catch (IOException ioe) {

                        throw new Error("데이터베이스를 생성할 수 없습니다.\n 개발자에게 문의하세요!\n010.4561.8282");

                    }

                    try {

                        myDbHelper_search.openDataBase();


                        selectDB(search_query);

                        if (listview_search != null) {

                            listview_search.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                                    cursor_search.moveToPosition(position);

                                    String str_profile = cursor_search.getString(cursor_search.getColumnIndex("profile"));
                                    String str_name = cursor_search.getString(cursor_search.getColumnIndex("name"));
                                    String str_group_name = cursor_search.getString(cursor_search.getColumnIndex("group_name"));
                                    String str_cellphone = cursor_search.getString(cursor_search.getColumnIndex("cellphone"));

                                    String str_birth = cursor_search.getString(cursor_search.getColumnIndex("birth"));
                                    String str_general_position = cursor_search.getString(cursor_search.getColumnIndex("general_position"));
                                    String str_group_position = cursor_search.getString(cursor_search.getColumnIndex("group_position"));
                                    String str_job = cursor_search.getString(cursor_search.getColumnIndex("job"));
                                    String str_jobaddr = cursor_search.getString(cursor_search.getColumnIndex("job_addr"));
                                    String str_jobtel = cursor_search.getString(cursor_search.getColumnIndex("job_tel"));
                                    String str_jobfax = cursor_search.getString(cursor_search.getColumnIndex("job_fax"));
                                    String str_hometel = cursor_search.getString(cursor_search.getColumnIndex("home_tel"));
                                    String str_homeaddr = cursor_search.getString(cursor_search.getColumnIndex("home_addr"));


                                    Intent intent = new Intent(MemberSearchActivity.this, MemberDetailActivity.class);

                                    intent.putExtra("profile", str_profile);
                                    intent.putExtra("name", str_name);
                                    intent.putExtra("group_name", str_group_name);
                                    intent.putExtra("cellphone", str_cellphone);
                                    intent.putExtra("birth", str_birth);
                                    intent.putExtra("general_position", str_general_position);
                                    intent.putExtra("group_position", str_group_position);
                                    intent.putExtra("job", str_job);
                                    intent.putExtra("job_addr", str_jobaddr);
                                    intent.putExtra("job_tel", str_jobtel);
                                    intent.putExtra("job_fax", str_jobfax);
                                    intent.putExtra("home_tel", str_hometel);
                                    intent.putExtra("home_addr", str_homeaddr);
                                    startActivity(intent);
                                }
                            });
                        } else {

                        }
                    } catch (SQLException sqle) {

                        throw sqle;

                    }

                }
            }
        });

    }


    private void selectDB(String srch_query) {

        db_search = myDbHelper_search.getReadableDatabase();
        query_search="select * from tb_member where name like '%"+srch_query.toString()+"%'";
        cursor_search = db_search.rawQuery(query_search, null);
        DBAdapter_search dbadapter_search= new DBAdapter_search(this,cursor_search);
        //	dbadapter_search.changeCursor(cursor_search);


        if(cursor_search.getCount()>0)
        {

            startManagingCursor(cursor_search);

            listview_search = (ListView)this.findViewById(R.id.lv_search);
            listview_search.setAdapter(dbadapter_search);
            tv_numberoflist.setText("검색하신 결과는 총"+cursor_search.getCount()+"명 입니다.");

        }
        else
        {
            startManagingCursor(cursor_search);

            listview_search = (ListView)this.findViewById(R.id.lv_search);
            listview_search.setAdapter(dbadapter_search);
            tv_numberoflist.setText("검색하신 결과는 총"+cursor_search.getCount()+"명 입니다.");

        }


    }


    public class DBAdapter_search extends CursorAdapter {

        public DBAdapter_search(Context context, Cursor c)
        {
            super(context, c);

        }


        @Override
        public void bindView(View arg0, Context arg1, Cursor arg2) {
            final ImageView img_profile = (ImageView) arg0.findViewById(R.id.img_profile);
            final TextView tv_name= (TextView) arg0.findViewById(R.id.tv_name);
            final TextView tv_group= (TextView) arg0.findViewById(R.id.tv_group);
            final TextView tv_cellphone= (TextView) arg0.findViewById(R.id.tv_cellphone);

            String graphUri=arg2.getString(arg2.getColumnIndex("profile"));
            graphUri="images/"+graphUri;

            Bitmap bitmap = loadBitmap(graphUri);
            img_profile.setImageBitmap(bitmap);


            tv_name.setText(arg2.getString(arg2.getColumnIndex("name")));
            tv_group.setText(arg2.getString(arg2.getColumnIndex("group_name"))+"기");
            tv_cellphone.setText(arg2.getString(arg2.getColumnIndex("cellphone")));


        }

        @Override
        public View newView(Context arg0, Cursor arg1, ViewGroup arg2) {
            LayoutInflater inflater = LayoutInflater.from(arg0);
            View v = inflater.inflate(R.layout.listlayout,arg2, false);
            return v;
        }



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
