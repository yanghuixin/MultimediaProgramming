package com.yhx.pinglun;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Evaluate> evaluateList;
    private Evaluate evaluate1;
    private Evaluate evaluate2;
    private Evaluate evaluate3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        evaluateList = getEvaluateList();
        listView = (ListView) findViewById(R.id.lv);
        listView.setAdapter(new MyAdapter(this,evaluateList));
    }

    private List<Evaluate> getEvaluateList(){
        evaluateList = new ArrayList<Evaluate>();
        evaluate1 = new Evaluate();
        evaluate1.setImageUrl("");
        evaluate1.setUsername("Jasper");
        evaluate1.setDate("2017-10-27");
        evaluate1.setSpecifications("规格： 0.3g/粒");
        evaluate1.setContent("还蛮不错的");

        evaluate2 = new Evaluate();
        evaluate2.setImageUrl("");
        evaluate2.setUsername("Neinei");
        evaluate2.setDate("2017-10-27");
        evaluate2.setSpecifications("规格： 0.3g/粒");
        evaluate2.setContent("还蛮不错的");

        evaluate3 = new Evaluate();
        evaluate3.setImageUrl("");
        evaluate3.setUsername("Hehe");
        evaluate3.setDate("2017-10-27");
        evaluate3.setSpecifications("规格： 0.3g/粒");
        evaluate3.setContent("还蛮不错的");

        evaluateList.add(evaluate1);
        evaluateList.add(evaluate2);
        evaluateList.add(evaluate3);
        return evaluateList;
    }
}
