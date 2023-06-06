package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Note_book_edit_view extends AppCompatActivity {


    MySQlite mSQLiteHelper = null;
    Toolbar toolbar;
    EditText title_edit, content_edit;
    String Note_book_id;
    Button save, delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_book_edit_view);

        content_edit = (EditText) findViewById(R.id.content_edit);
        init(); // 如果 是 从 item 进入 会 传入 数据库数据 来初始化界面
        // 代码使用方法来源于网络 start
        toolbar = findViewById(R.id.note_book_edit_view_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//添加默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
//        toolbar.inflateMenu(R.menu.note_book_menu);
        // end

        save = findViewById(R.id.save);
        delete = findViewById(R.id.delete);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteContent =content_edit.getText().toString().trim();
                // 已在数据库
                if(Note_book_id!=null){
                    //修改记录的功能
                    if(noteContent.length()>0){
                        if (mSQLiteHelper.updateData(Note_book_id,noteContent, mSQLiteHelper.get_time())){
                            Toast.makeText(Note_book_edit_view.this, "修改成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Note_book_edit_view.this, Note_book.class);
                            setResult(2);
                            finish();
                        }else{
                            Toast.makeText(Note_book_edit_view.this, "修改失败", Toast.LENGTH_SHORT).show();;
                        }
                    } else{
                        Toast.makeText(Note_book_edit_view.this, "修改记录不能是空", Toast.LENGTH_SHORT).show();;
                    }
                }else{
                    // 未在数据库
                    //添加记录的功能
                    if(noteContent.length()>0){
                        if (mSQLiteHelper.insertData(noteContent,mSQLiteHelper.get_time())){
                            Toast.makeText(Note_book_edit_view.this, "保存成功", Toast.LENGTH_SHORT).show();;

                            setResult(2);

                            finish();
                        }else{
                            Toast.makeText(Note_book_edit_view.this, "保存失败", Toast.LENGTH_SHORT).show();;

                        }
                    } else{
                        Toast.makeText(Note_book_edit_view.this, "保存不能为空", Toast.LENGTH_SHORT).show();;

                    }
                }

            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content_edit.setText(" ");
            }
        });
    }


    public void init() {
        mSQLiteHelper = new MySQlite(this);

        Intent intent=getIntent();
        if(intent != null){
            Note_book_id = intent.getStringExtra("id");
            if(Note_book_id != null){
                toolbar.setTitle("修改记录");
                content_edit.setText(intent.getStringExtra("content"));
//                note_time.setText(intent.getStringExtra("time"));
//                note_time.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}