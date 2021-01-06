package com.dei.ceo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Fecsen on 2018-01-05.
 */

public class ScheduleActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        ImageView mImageView;
        PhotoViewAttacher mAttacher;
        mImageView = (ImageView) findViewById(R.id.schedule);
        Drawable bitmap = getResources().getDrawable(R.drawable.schedulelist5);
        mImageView.setImageDrawable(bitmap);
        mAttacher = new PhotoViewAttacher(mImageView);
    }
}