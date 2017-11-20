package com.yhx.pinglun;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yhx on 2017/11/11.
 */

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<Evaluate> evaluateList;
    private List<Content> contentList;
    private ViewHolder viewHolder;
    MyAdapter(Context context, List<Evaluate> evaluateList) {
        this.context = context;
        this.evaluateList = evaluateList;
    }

    @Override
    public int getCount() {
        return evaluateList.size() == 0 ? 0 : evaluateList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Evaluate evaluate = evaluateList.get(position);
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.imageView.setBackground(Drawable.createFromPath(evaluate.getImageUrl()));
        viewHolder.textNameView.setText(evaluate.getUsername());
        viewHolder.textDateView.setText(evaluate.getDate());
        viewHolder.textSpecificationsView.setText(evaluate.getSpecifications());
        viewHolder.textContentView.setText(evaluate.getContent());

        viewHolder.thumbsUpClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.iv_thumbsUp.setImageResource(R.drawable.icon_praise);
                if (viewHolder.tv_thumbsUp.getText() == null || viewHolder.tv_thumbsUp.getText() == ""){
                    viewHolder.tv_thumbsUp.setText("自己");
                    System.out.println(viewHolder.tv_thumbsUp.getText() + "点赞");
                }else {
                    viewHolder.tv_thumbsUp.setText( viewHolder.tv_thumbsUp.getText() + "，他人");
                    System.out.println(viewHolder.tv_thumbsUp.getText() + "点赞");
                }
            }
        });

        contentList = new ArrayList<Content>();
        viewHolder.commentClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.showCommentEdit((Activity) context, v, new Utils.liveCommentResult() {
                    @Override
                    public void onResult(boolean confirmed, String comment) {
                        if (confirmed) {
                            //Toast.makeText(context,comment, Toast.LENGTH_LONG).show();
                            Content content = new Content();
                            content.setUsername("hehe");
                            content.setContent(comment);
                            contentList.add(content);
                            if (contentList.size() != 0){
                                viewHolder.listView.setAdapter(new ContentAdapter(context,contentList));
                                System.out.println("用户评论了");
                            }
                        }
                    }
                });
            }
        });
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return evaluateList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        private ImageView imageView;
        private TextView textNameView;
        private TextView textDateView;
        private TextView textSpecificationsView;
        private TextView textContentView;
        private TextView tv_thumbsUp;
        private ImageView iv_thumbsUp;
        private Button thumbsUpClick;
        private Button commentClick;
        private ListView listView;

        public ViewHolder(View convertView) {
            imageView = convertView.findViewById(R.id.iv);
            textNameView = convertView.findViewById(R.id.tv_name);
            textDateView = convertView.findViewById(R.id.tv_date);
            textSpecificationsView = convertView.findViewById(R.id.tv_specifications);
            textContentView = convertView.findViewById(R.id.tv_content);
            tv_thumbsUp = convertView.findViewById(R.id.tv_thumbsUp);
            iv_thumbsUp = convertView.findViewById(R.id.iv_thumbsUp);
            thumbsUpClick = convertView.findViewById(R.id.bt_thumbsUp);
            commentClick = convertView.findViewById(R.id.bt_comment);
            listView = convertView.findViewById(R.id.content_listView);
        }
    }

}
