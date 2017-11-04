package com.yhx.multimediaprogramming;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        //加载图片
        Bitmap bm = BitmapFactory.decodeFile("sdcard/dog.jpg");
        ImageView iv = (ImageView) findViewById(R.id.iv);
        iv.setImageBitmap(bm);
    }
}
