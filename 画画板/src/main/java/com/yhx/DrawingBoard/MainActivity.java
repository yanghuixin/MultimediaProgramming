package com.yhx.DrawingBoard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private Canvas canvas;
    private Paint paint;
    private ImageView iv;
    private Bitmap bmCopy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap bmSrc = BitmapFactory.decodeResource(getResources(),R.drawable.bg);
        //白纸
        bmCopy = Bitmap.createBitmap(bmSrc.getWidth(),bmSrc.getHeight(),bmSrc.getConfig());
        //笔
        paint = new Paint();
        //画板
        canvas = new Canvas(bmCopy);
        //作画
        canvas.drawBitmap(bmSrc,new Matrix(),paint);

        iv = (ImageView) findViewById(R.id.iv);
        iv.setImageBitmap(bmCopy);

        iv.setOnTouchListener(new View.OnTouchListener() {
            //用户手指只要触摸屏幕，就会产生触摸事件
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //判断触摸事件的类型
                switch (event.getAction()){
                    //手指触摸
                    case MotionEvent.ACTION_DOWN:
                        //触摸事件中会包含手指触摸的坐标
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        break;
                    //手指滑动
                    case MotionEvent.ACTION_MOVE:
                        endX = (int) event.getX();
                        endY = (int) event.getY();
                        canvas.drawLine(startX,startY,endX,endY,paint);
                        iv.setImageBitmap(bmCopy);
                        //把本次画线的终点设置成下次画线的起点
                        startX = endX;
                        startY = endY;
                        break;
                    //手指抬起
                    case MotionEvent.ACTION_UP:
                        break;
                }
                //true表示告诉系统，这个事件由iv处理
                //false表示不处理该触摸事件，事件往上传递
                return true;
            }
        });
    }

    public void red(View view) {
        paint.setColor(Color.RED);
    }

    public void green(View view) {
        paint.setColor(Color.GREEN);
    }

    public void brush(View view) {
        //改变线条粗细
        paint.setStrokeWidth(8);
    }

    public void save(View view) {
        File file = new File("sdcard/dazuo2.jpg");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //把图片压缩到本地图片
            bmCopy.compress(Bitmap.CompressFormat.PNG,100,fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //手动发一个sd卡就绪广播
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4版本以上
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
            sendBroadcast(intent);
        } else {//4.4版本以下
            Intent intent = new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory()));
            sendBroadcast(intent);
        }
    }
}
