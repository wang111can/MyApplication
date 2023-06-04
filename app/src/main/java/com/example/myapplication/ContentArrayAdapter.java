package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;



public class ContentArrayAdapter extends ArrayAdapter<Content> {

    int resourceid;
    // 重写 ArrayAdapter 的构造函数
    public ContentArrayAdapter(Context context, int textViewResourceId, List<Content> objects) {
        super(context, textViewResourceId, objects);

            resourceid = textViewResourceId;
    }

    // 重写 getView 方法
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Content content = getItem(position); // 获取当前 item 实例
        // 获取 ListView 里的 item 页面
        View view = LayoutInflater.from(getContext()).inflate(resourceid, parent, false);

        TextView title = (TextView) view.findViewById(R.id.item_title);
        TextView date = (TextView) view.findViewById(R.id.item_created_date);
        title.setText(content.get_title());
        date.setText(content.get_date());

        return  view;
    }
}
