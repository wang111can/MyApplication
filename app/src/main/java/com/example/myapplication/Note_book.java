package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Note_book extends AppCompatActivity {


    // 新增 笔记的 标题和 文本文件 使用 跨类 来进行 传递信息
    public static String new_add_note_title;
    public static String new_add_note_content;

    Toolbar toolbar;
    ListView note_ListView;
    FloatingActionButton add_note_button;


    // 限制记事本的 最大 记事本数量
    final int max_note = 1024;
    String[] data_bases_name = new String[max_note];
    int now_node_num = 0;

    // list view
    // 每项记事本要显示的信息 标题 + 创建日期
    ContentArrayAdapter node_describe = null;
    List<Content> note_struct_list = null; // 使用链表来动态删除

    Content init = new Content("使用说明", "2023-06-03");


    IntentFilter intentFilter;
    Broad_send_data broad_send_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_book);

        // 代码使用方法来源于网络 start
        toolbar = findViewById(R.id.note_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//添加默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        // end

        note_ListView = findViewById(R.id.list_note);
        add_note_button = findViewById(R.id.add_note);


        // 初始化链表
        note_struct_list = new ArrayList<Content>();
        note_struct_list.add(init);

        node_describe = new ContentArrayAdapter(this, R.layout.activity_note_book_list_item, note_struct_list);

        // 对ListView 进行初始化
        note_ListView.setAdapter(node_describe);

        // 设置 ListView 的 item 点击监听器
        note_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Content now_item = note_struct_list.get(position); // ListView 的顺序是按照链表顺序来初始化的

            }
        });

        // 添加笔记
        add_note_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Note_book.this, Note_book_edit_view.class);
                startActivity(intent);
                Content new_content = new Content(new_add_note_title, new_add_note_content);

                // 本地数据库存储


            }
        });

    }


    class Broad_send_data extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(Note_book.this, "xxxxxx", Toast.LENGTH_SHORT).show();
        }
    }
}