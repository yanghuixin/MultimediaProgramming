package com.yhx.copyimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //加载原图
        Bitmap bmSrc = BitmapFactory.decodeResource(getResources(),R.drawable.dog);
        //创建副本
        //1.创建与原图一模一样大小的位图对象，该对象中内容目前是空白的
        Bitmap bmCopy = Bitmap.createBitmap(bmSrc.getWidth(),bmSrc.getHeight(),bmSrc.getConfig());
        //2.创建画笔对象
        Paint paint = new Paint();
        //3.创建画板
        Canvas canvas = new Canvas(bmCopy);
        //创建矩阵
        Matrix mt = new Matrix();
        //平移效果，指定平移距离
        //mt.setTranslate(80,50);
        //缩放效果
        //mt.setScale(2,0.5f,bmCopy.getWidth() / 2,bmCopy.getHeight() / 2);
        //旋转效果
        //mt.setRotate(45,bmCopy.getWidth() / 2,bmCopy.getHeight() / 2);
        //镜面效果
        //mt.setScale(-1,1);
        //mt.postTranslate(bmCopy.getWidth(),0);
        //倒影效果
        mt.setScale(1,-1);
        mt.postTranslate(0,bmCopy.getHeight());
        //4.开始作画
        canvas.drawBitmap(bmSrc,mt,paint);

        ImageView iv_src = (ImageView) findViewById(R.id.iv_src);
        iv_src.setImageBitmap(bmSrc);
        ImageView iv_copy = (ImageView) findViewById(R.id.iv_copy);
        iv_copy.setImageBitmap(bmCopy);
    }
}
