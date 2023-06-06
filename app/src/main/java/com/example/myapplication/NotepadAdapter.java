package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NotepadAdapter extends ArrayAdapter<NotepadBean> {


    int resourceid;
    // 重写 ArrayAdapter 的构造函数
    public NotepadAdapter(Context context, int textViewResourceId, List<NotepadBean> objects) {
        super(context, textViewResourceId, objects);

        resourceid = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        NotepadBean notepadBean = getItem(position); // 获取当前 item 实例
        // 获取 ListView 里的 item 页面
        View view = LayoutInflater.from(getContext()).inflate(resourceid, parent, false);

        TextView title = (TextView) view.findViewById(R.id.item_content);
        TextView date = (TextView) view.findViewById(R.id.item_date);
        title.setText(notepadBean.getNotepadContent());
        date.setText(notepadBean.getNotepadTime());

        return  view;


    }
}
