package com.yhx.pinglun;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by yhx on 2017/11/11.
 */

public class MyAdapter extends BaseAdapter {

    private Context context;
    private ImageView imageView;
    private TextView textNameView;
    private TextView textDateView;
    private TextView textSpecificationsView;
    private TextView textContentView;
    private Button thumbsUpClick;
    private Button commentClick;
    private List<Evaluate> evaluateList;
    private EditText tv;
    private ListView listView;
    MyAdapter(Context context, List<Evaluate> evaluateList) {
        this.context = context;
        this.evaluateList = evaluateList;
    }

    @Override
    public int getCount() {
        return evaluateList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Evaluate evaluate = evaluateList.get(position);
        View view = View.inflate(context,R.layout.item_view,null);

        imageView = view.findViewById(R.id.iv);
        textNameView = view.findViewById(R.id.tv_name);
        textDateView = view.findViewById(R.id.tv_date);
        textSpecificationsView = view.findViewById(R.id.tv_specifications);
        textContentView = view.findViewById(R.id.tv_content);
        imageView.setBackground(Drawable.createFromPath(evaluate.getImageUrl()));
        textNameView.setText(evaluate.getUsername());
        textDateView.setText(evaluate.getDate());
        textSpecificationsView.setText(evaluate.getSpecifications());
        textContentView.setText(evaluate.getContent());

        thumbsUpClick = view.findViewById(R.id.bt_thumbsUp);
        thumbsUpClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thumbsUpClick.setBackgroundColor(Color.RED);
            }
        });
        commentClick = view.findViewById(R.id.bt_comment);
        tv = view.findViewById(R.id.popup_live_comment_edit);
        listView = view.findViewById(R.id.content_listView);
        commentClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.showCommentEdit((Activity) context, v, new Utils.liveCommentResult() {
                    @Override
                    public void onResult(boolean confirmed, String comment) {
                        if (confirmed) {
                            //这句会报错，报错为不能在AdapterView里为listView添加view
                            // 我需要你给我一个思路，这块怎么做
                            //listView.addView(tv);
                            Toast.makeText(context,tv.getText(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        return view;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
