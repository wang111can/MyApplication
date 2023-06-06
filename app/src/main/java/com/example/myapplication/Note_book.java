package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Note_book extends AppCompatActivity {


    // 新增 笔记的 标题 使用 跨类 来进行 传递信息
    public static String new_add_note_title;

    Toolbar toolbar;
    ListView note_ListView;
    FloatingActionButton add_note_button;
    MySQlite mySQliteHelper = null;

    // 限制记事本的 最大 记事本数量
    final int max_note = 1024;
    String[] data_bases_name = new String[max_note];
    int now_node_num = 0;

    // list view
    // 每项记事本要显示的信息 标题 + 创建日期
    NotepadAdapter node_describe = null;
    List<NotepadBean> note_struct_list = null; // 使用链表来动态删除



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

        // 打开数据库
        mySQliteHelper = new MySQlite(this);

        note_ListView = findViewById(R.id.list_note);
        add_note_button = findViewById(R.id.add_note);
        // 初始化
        init();


        // 添加笔记

        add_note_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Note_book.this, Note_book_edit_view.class);
                startActivityForResult(intent, 1);

            }
        });




        // 传入已有的数据
//        note_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                NotepadBean notepadBean = note_struct_list.get(position);
//                Intent intent = new Intent(Note_book.this, Note_book_edit_view.class);
//                intent.putExtra("id", notepadBean.getId());
//                intent.putExtra("content", notepadBean.getNotepadContent());
//                intent.putExtra("time", notepadBean.getNotepadTime());
//                startActivityForResult(intent, 1);
//
//
//            }
//        });

        // 长按删除
        note_ListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog dialog;
                AlertDialog.Builder builder= new AlertDialog.Builder(Note_book.this);
                builder.setMessage("是否删除");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NotepadBean notepadBean= note_struct_list.get(position);
                        if(mySQliteHelper.deleteData(notepadBean.getId())){
                            note_struct_list.remove(position);//删除对应的Item
                            node_describe.notifyDataSetChanged();//更新记事本页面
                            Toast.makeText(Note_book.this,"删除成功",Toast.LENGTH_LONG).show();
                        }

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog = builder.create();
                dialog.show();
                return true;
            }
        });




    }
    public  void init() {
        if (note_struct_list != null) {
            note_struct_list.clear();
        }
        note_struct_list = mySQliteHelper.query(); // 把数据库中的 内容 提取出来 如何显示到 listview 里

        node_describe = new NotepadAdapter(this, R.layout.activity_note_book_list_item, note_struct_list);

        // 对ListView 进行初始化
        note_ListView.setAdapter(node_describe);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            init();
        }

    }

}