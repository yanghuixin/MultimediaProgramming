package com.yhx.multimediaprogramming;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import static android.graphics.BitmapFactory.decodeFile;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        //只请求图片宽高，不解析图片像素
        opts.inJustDecodeBounds = true;
        //返回null，获取图片宽高，保存在opts对象中
        decodeFile("sdcard/dog.jpg",opts);
        //获取图片宽高
        int imageWidth = opts.outWidth;
        int imageHeight = opts.outHeight;
        //获取屏幕宽高
        Display dp = getWindowManager().getDefaultDisplay();
        int screenWidth = dp.getWidth();
        int screenHeight = dp.getHeight();
        //计算缩放比例
        int scale = 1;
        int scaleWidth = imageWidth / screenWidth;
        int scaleHeight = imageHeight / screenHeight;
        //判断取哪个比例
        if (scaleWidth >= scaleHeight && scaleWidth > 1){
            scale = scaleWidth;
        }else if (scaleWidth < scaleHeight && scaleHeight > 1){
            scale = scaleHeight;
        }
        //设置缩小比例
        opts.inSampleSize = scale;
        opts.inJustDecodeBounds = false;
        //获取缩小后的图片的像素信息
        Bitmap bm = BitmapFactory.decodeFile("sdcard/dog.jpg",opts);
        ImageView iv = (ImageView) findViewById(R.id.iv);
        iv.setImageBitmap(bm);
    }
}
