package com.yhx.siyifu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //加载图片
        Bitmap bmSrc = BitmapFactory.decodeResource(getResources(),R.drawable.big2);
        final Bitmap bmCopy = Bitmap.createBitmap(bmSrc.getWidth(),bmSrc.getHeight(),bmSrc.getConfig());
        Paint paint = new Paint();
        Canvas canvas = new Canvas(bmCopy);
        canvas.drawBitmap(bmSrc,new Matrix(),paint);

        final ImageView iv = (ImageView) findViewById(R.id.iv);
        iv.setImageBitmap(bmCopy);

        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        int x = (int) event.getX();
                        int y = (int) event.getY();
                        for (int i = -10 ; i < 10 ; i++){
                            for (int j = -10 ; j < 10 ; j++){
                                if (Math.sqrt(i * i + j * j) <= 10)
                                    if (x + i < bmCopy.getWidth() && y + j < bmCopy.getHeight()
                                            && x +i >= 0 && y + j >= 0){
                                        //把该坐标对应的像素，置为透明色
                                        bmCopy.setPixel(x + i,y + j, Color.TRANSPARENT);
                                    }
                            }
                        }
                        iv.setImageBitmap(bmCopy);
                        break;
                }
                return true;
            }
        });
    }
}
