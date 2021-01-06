package com.dei.ceo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class LeaderSearchActivity extends AppCompatActivity implements View.OnClickListener {

    //ArrayAdapter<String> adapter=null;
    ListView listview = null;
    DataBaseHelper myDbHelper;
    SQLiteDatabase db;
    String query;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_search);
        Button b1 = (Button) findViewById(R.id.b1);
        b1.setOnClickListener(this);
        Button b2 = (Button) findViewById(R.id.b2);
        b2.setOnClickListener(this);
        Button b3 = (Button) findViewById(R.id.b3);
        b3.setOnClickListener(this);
        Button b4 = (Button) findViewById(R.id.b4);
        b4.setOnClickListener(this);
        Button b5 = (Button) findViewById(R.id.b5);
        b5.setOnClickListener(this);
        Button b6 = (Button) findViewById(R.id.b6);
        b6.setOnClickListener(this);
        Button b7 = (Button) findViewById(R.id.b7);
        b7.setOnClickListener(this);
        Button b8 = (Button) findViewById(R.id.b8);
        b8.setOnClickListener(this);
        Button b9 = (Button) findViewById(R.id.b9);
        b9.setOnClickListener(this);
        Button b10 = (Button) findViewById(R.id.b10);
        b10.setOnClickListener(this);
        Button b11 = (Button) findViewById(R.id.b11);
        b11.setOnClickListener(this);
        Button b12 = (Button) findViewById(R.id.b12);
        b12.setOnClickListener(this);
        Button b13 = (Button) findViewById(R.id.b13);
        b13.setOnClickListener(this);
        Button b14 = (Button) findViewById(R.id.b14);
        b14.setOnClickListener(this);
        Button b15 = (Button) findViewById(R.id.b15);
        b15.setOnClickListener(this);
        Button b16 = (Button) findViewById(R.id.b16);
        b16.setOnClickListener(this);
        Button b17 = (Button) findViewById(R.id.b17);
        b17.setOnClickListener(this);
        Button b18 = (Button) findViewById(R.id.b18);
        b18.setOnClickListener(this);
        Button b19 = (Button) findViewById(R.id.b19);
        b19.setOnClickListener(this);
        Button b20 = (Button) findViewById(R.id.b20);
        b20.setOnClickListener(this);
        Button b21 = (Button) findViewById(R.id.b21);
        b21.setOnClickListener(this);
        Button b22 = (Button) findViewById(R.id.b22);
        b22.setOnClickListener(this);
        Button b23 = (Button) findViewById(R.id.b23);
        b23.setOnClickListener(this);
        Button b24 = (Button) findViewById(R.id.b24);
        b24.setOnClickListener(this);
        Button b25 = (Button) findViewById(R.id.b25);
        b25.setOnClickListener(this);
        Button b26 = (Button) findViewById(R.id.b26);
        b26.setOnClickListener(this);
        Button b27 = (Button) findViewById(R.id.b27);
        b27.setOnClickListener(this);
        Button b28 = (Button) findViewById(R.id.b28);
        b28.setOnClickListener(this);
  /*      myDbHelper = new DataBaseHelper(null);
        myDbHelper = new DataBaseHelper(this);

        try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            myDbHelper.openDataBase();

            selectDB();
            listview.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    cursor.moveToPosition(position);

                    String str_profile = cursor.getString(cursor.getColumnIndex("profile"));
                    String str_name = cursor.getString(cursor.getColumnIndex("name"));
                    String str_groupname = cursor.getString(cursor.getColumnIndex("groupname"));
                    String str_cellphone = cursor.getString(cursor.getColumnIndex("cellphone"));

                    Intent intent = new Intent(LeaderSearchActivity.this, MemberDetailActivity.class);

                    intent.putExtra("profile", str_profile);
                    intent.putExtra("name", str_name);
                    intent.putExtra("groupname", str_groupname);
                    intent.putExtra("cellphone", str_cellphone);
                    startActivity(intent);
                }
            });

        } catch (SQLException sqle) {

            throw sqle;

        }*/

    }

    @Override
    public void onClick(View v) {
        int id	= v.getId();
        Intent intent = new Intent(LeaderSearchActivity.this, LeaderSearchDetailActivity.class);
        switch (id)
        {
            case R.id.b1:

                intent.putExtra("group_name","1");
                startActivity(intent);
                break;

            case R.id.b2:

                intent.putExtra("group_name","2");
                startActivity(intent);
                break;
            case R.id.b3:

                intent.putExtra("group_name","3");
                startActivity(intent);
                break;

            case R.id.b4:

                intent.putExtra("group_name","4");
                startActivity(intent);
                break;
            case R.id.b5:

                intent.putExtra("group_name","5");
                startActivity(intent);
                break;

            case R.id.b6:

                intent.putExtra("group_name","6");
                startActivity(intent);
                break;
            case R.id.b7:

                intent.putExtra("group_name","7");
                startActivity(intent);
                break;

            case R.id.b8:

                intent.putExtra("group_name","8");
                startActivity(intent);
                break;
            case R.id.b9:

                intent.putExtra("group_name","9");
                startActivity(intent);
                break;

            case R.id.b10:

                intent.putExtra("group_name","10");
                startActivity(intent);
                break;
            case R.id.b11:

                intent.putExtra("group_name","11");
                startActivity(intent);
                break;

            case R.id.b12:

                intent.putExtra("group_name","12");
                startActivity(intent);
                break;
            case R.id.b13:

                intent.putExtra("group_name","13");
                startActivity(intent);
                break;

            case R.id.b14:

                intent.putExtra("group_name","14");
                startActivity(intent);
                break;
            case R.id.b15:

                intent.putExtra("group_name","15");
                startActivity(intent);
                break;

            case R.id.b16:

                intent.putExtra("group_name","16");
                startActivity(intent);
                break;
            case R.id.b17:

                intent.putExtra("group_name","17");
                startActivity(intent);
                break;

            case R.id.b18:

                intent.putExtra("group_name","18");
                startActivity(intent);
                break;
            case R.id.b19:

                intent.putExtra("group_name","19");
                startActivity(intent);
                break;

            case R.id.b20:

                intent.putExtra("group_name","20");
                startActivity(intent);
                break;
            case R.id.b21:

                intent.putExtra("group_name","21");
                startActivity(intent);
                break;

            case R.id.b22:

                intent.putExtra("group_name","22");
                startActivity(intent);
                break;
            case R.id.b23:

                intent.putExtra("group_name","23");
                startActivity(intent);
                break;

            case R.id.b24:

                intent.putExtra("group_name","24");
                startActivity(intent);
                break;
            case R.id.b25:

                intent.putExtra("group_name","25");
                startActivity(intent);
                break;
            case R.id.b26:

                intent.putExtra("group_name","26");
                startActivity(intent);
                break;
            case R.id.b27:

                intent.putExtra("group_name","27");
                startActivity(intent);
                break;
            case R.id.b28:

                intent.putExtra("group_name","28");
                startActivity(intent);
                break;
        }

    }
}
   /* private void selectDB()
	{
		db =  myDbHelper.getReadableDatabase();
		query="select * from tb_member";

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
			final ImageView img_view=(ImageView) arg0.findViewById(R.id.iv5);

			String graphUri=arg2.getString(arg2.getColumnIndex("profile"));

			Bitmap bitmap = loadBitmap(graphUri);
			img_profile.setImageBitmap(bitmap);


			tv_name.setText("�씠由�:"+ arg2.getString(arg2.getColumnIndex("name")));
			tv_group.setText("湲곗닔:"+ arg2.getString(arg2.getColumnIndex("group_name")));
			tv_cellphone.setText("HP:"+ arg2.getString(arg2.getColumnIndex("cellphone")));


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
*/
