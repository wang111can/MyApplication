package com.example.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Song_Adapter extends ArrayAdapter<Song> {
    int resourceid;
    // 重写 ArrayAdapter 的构造函数
    public Song_Adapter(Context context, int textViewResourceId, List<Song> objects) {
        super(context, textViewResourceId, objects);

        resourceid = textViewResourceId;
    }

    // 重写 getView 方法
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Song song = getItem(position); // 获取当前 item 实例
        // 获取 ListView 里的 item 页面
        View view = LayoutInflater.from(getContext()).inflate(resourceid, parent, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.music_list_item_song_img);
        TextView song_name = (TextView) view.findViewById(R.id.music_list_item_song_name);
        imageView.setImageResource(song.get_song_img_id());
        song_name.setText(song.get_song_name());

        return  view;
    }
}
