package com.yhx.buttontext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);

        String[] strings = new String[]{
                "张三",
                "李四",
                "王五",
                "赵六"
        };

        for (String s :strings){
            Button button = new Button(this);
            button.setText(s);
            ll.addView(button);
        }
    }
}
