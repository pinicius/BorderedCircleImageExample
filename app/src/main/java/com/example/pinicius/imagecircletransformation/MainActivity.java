package com.example.pinicius.imagecircletransformation;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class MainActivity extends AppCompatActivity {

    private static final String IMG_URL = "http://pinicius.com/wp-content/uploads/2016/04/The_Black-cover.jpeg";
    private static final int BORDER_WIDTH = 8;

    private ImageView originalCover;
    private ImageView borderedCircleCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        originalCover = (ImageView) findViewById(R.id.original_cover);
        borderedCircleCover = (ImageView) findViewById(R.id.transformed_cover);

        loadImages();

        getSupportActionBar().setTitle("BorderedCircleImageExample");
    }

    private void loadImages() {

        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                borderedCircleCover.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        borderedCircleCover.setTag(target);

        Picasso picasso = Picasso.with(this);
        picasso.setLoggingEnabled(true);

        picasso.load(IMG_URL)
                .into(originalCover);

        picasso.load(IMG_URL)
                .transform(new BorderedCircleTransform(BORDER_WIDTH, Color.BLACK))
                .into(target);
    }
}
