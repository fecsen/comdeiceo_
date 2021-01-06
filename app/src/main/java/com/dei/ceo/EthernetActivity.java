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

public class EthernetActivity extends AppCompatActivity {

    ImageView mImageView;
    PhotoViewAttacher mAttacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ether);
        // Any implementation of ImageView can be used!

        mImageView = (ImageView) findViewById(R.id.ether);

        // Set the Drawable displayed
        Drawable bitmap = getResources().getDrawable(R.drawable.etherr);
        mImageView.setImageDrawable(bitmap);

        // Attach a PhotoViewAttacher, which takes care of all of the zooming functionality.
        mAttacher = new PhotoViewAttacher(mImageView);
    }
    }
