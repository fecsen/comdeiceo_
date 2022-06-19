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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class ProfessionalActivity extends AppCompatActivity {
    //ArrayAdapter<String> adapter=null;
    ListView listview=null;
    DataBaseHelper myDbHelper;
    SQLiteDatabase db;
    String query;
    Cursor cursor;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_detail);


        //TextView tv_name = (TextView)findViewById(R.id.tv_01);

        Intent intent = getIntent(); // 보내온 Intent를 얻는다
        String group_name = intent.getStringExtra("group_name");

        int int_group_name= Integer.parseInt(group_name);
        final String str_chk_group_name= String.valueOf(int_group_name) ;
        if(int_group_name ==0)
        {
        }
        else

        myDbHelper = new DataBaseHelper(null);
        myDbHelper = new DataBaseHelper(this);

        try
        {

            myDbHelper.createDataBase();

        }
        catch (IOException ioe)
        {

            throw new Error("Unable to create database");

        }

        try
        {

            myDbHelper.openDataBase();

            selectDB(group_name);

            if(listview !=null)
            {

                listview.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        cursor.moveToPosition(position);


                        String str_name = cursor.getString(cursor.getColumnIndex("name"));
                        String str_cellphone = cursor.getString(cursor.getColumnIndex("cellphone"));
                        String str_birth = cursor.getString(cursor.getColumnIndex("birth"));
                        String str_general_order = cursor.getString(cursor.getColumnIndex("general_order"));
                        String str_general_position = cursor.getString(cursor.getColumnIndex("general_position"));
                        String str_group_name = cursor.getString(cursor.getColumnIndex("group_name"));
                        String str_group_order = cursor.getString(cursor.getColumnIndex("group_order"));
                        String str_group_position = cursor.getString(cursor.getColumnIndex("group_position"));
                        String str_job = cursor.getString(cursor.getColumnIndex("job"));
                        String str_jobaddr = cursor.getString(cursor.getColumnIndex("job_addr"));
                        String str_jobtel = cursor.getString(cursor.getColumnIndex("job_tel"));
                        String str_jobfax = cursor.getString(cursor.getColumnIndex("job_fax"));
                        String str_hometel = cursor.getString(cursor.getColumnIndex("home_tel"));
                        String str_homeaddr = cursor.getString(cursor.getColumnIndex("home_addr"));
                        String str_profile= cursor.getString(cursor.getColumnIndex("profile"));



                        Intent intent = new Intent(ProfessionalActivity.this, MemberDetailActivity.class);

                        intent.putExtra("chkgeneral", str_chk_group_name);

                        intent.putExtra("name",str_name);

                        intent.putExtra("cellphone",str_cellphone);
                        intent.putExtra("birth",str_birth);
                        intent.putExtra("general_order",str_general_order);
                        intent.putExtra("general_position",str_general_position);
                        intent.putExtra("group_name",str_group_name);
                        intent.putExtra("group_order",str_group_order);
                        intent.putExtra("group_position",str_group_position);

                        intent.putExtra("job",str_job);
                        intent.putExtra("job_addr",str_jobaddr);
                        intent.putExtra("job_tel",str_jobtel);
                        intent.putExtra("job_fax",str_jobfax);
                        intent.putExtra("home_tel",str_hometel);
                        intent.putExtra("home_addr",str_homeaddr);
                        intent.putExtra("profile",str_profile);
                        startActivity(intent);
                    }
                });
            }
        }
        catch(SQLException sqle)
        {

            throw sqle;

        }


    }

    private void selectDB(String group_name)
    {

        db =  myDbHelper.getReadableDatabase();

        Intent intent = getIntent(); // 보내온 Intent를 얻는다
        String chk_general = intent.getStringExtra("group_name");
        int int_chk_general= Integer.parseInt(chk_general);

        if(int_chk_general ==0)
            query="select * from tb_member where general_position is not null";
        else
            query="select * from tb_member where group_name="+group_name.toString();

        cursor = db.rawQuery(query, null);


        if(cursor.getCount()>0)
        {
            startManagingCursor(cursor);
            DBAdapter dbadapter= new DBAdapter(this,cursor);

            listview = (ListView)this.findViewById(R.id.lv_01);
            listview.setAdapter(dbadapter);

        }

    }



    public class DBAdapter extends CursorAdapter {

        public DBAdapter(Context context, Cursor c) {
            super(context, c);
            // TODO Auto-generated constructor stub
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
            tv_group.setText("원장단");
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
