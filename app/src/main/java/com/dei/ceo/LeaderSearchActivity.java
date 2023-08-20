package com.dei.ceo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class LeaderSearchActivity extends AppCompatActivity implements View.OnClickListener {

    //ArrayAdapter<String> adapter=null;

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
        Button b29 = (Button) findViewById(R.id.b29);
        b29.setOnClickListener(this);
        Button b30 = (Button) findViewById(R.id.b30);
        b30.setOnClickListener(this);

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

            case R.id.b29:

                intent.putExtra("group_name","29");
                startActivity(intent);
                break;

            case R.id.b30:

                intent.putExtra("group_name","30");
                startActivity(intent);
                break;
        }

    }
}