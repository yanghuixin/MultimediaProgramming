package com.example.videoplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer player;
    private int progress = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SurfaceView sv = (SurfaceView) findViewById(R.id.sv);
        //获取SurfaceView的控制器
        final SurfaceHolder holder = sv.getHolder();

        /*Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MediaPlayer player = new MediaPlayer();
                        player.reset();
                        try {
                            player.setDataSource("sdcard/2.3gp");
                            //指定视频播放在哪个SurfaceView上
                            player.setDisplay(holder);
                            player.prepare();
                            player.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
        t.start();*/

        holder.addCallback(new SurfaceHolder.Callback() {
            //SurfaceView创建后会调用
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (player == null){
                    player = new MediaPlayer();
                    player.reset();
                    try {
                        player.setDataSource("sdcard/2.3gp");
                        //指定视频播放在哪个SurfaceView上
                        player.setDisplay(holder);
                        player.prepare();
                        //跳转到上一次停止的地方继续播放
                        player.seekTo(progress);
                        player.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            //SurfaceView创建结构改变后会调用
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            //SurfaceView销毁后会调用
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (player != null){
                    //停止之前先保存播放进度
                    progress = player.getCurrentPosition();
                    player.stop();
                    player.release();
                    player = null;
                }
            }
        });
    }
}
