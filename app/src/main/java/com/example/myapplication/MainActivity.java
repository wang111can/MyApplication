package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar bar;

    public static boolean[] set_switch_check = new boolean[4]; // 记录 set 页面的 控制情况
    ImageButton note_book_button, music_button, shake_button;
    public static Intent background_music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        background_music = new Intent(this, background_music.class);
        // 顶部导航栏
        bar = findViewById(R.id.tite_bar);
        bar.inflateMenu(R.menu.title_menu);

        // list 栏 设置背景音乐 和查看 开发人员信息
        bar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.set) {
                    Intent intent = new Intent(MainActivity.this, SetActivity.class);

                    startActivity(intent);
                }
                else if (id == R.id.developer) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("开发员名单");
                    builder.setMessage(R.string.developer_list);
                    builder.setCancelable(true);
                    builder.show();

                }
                return false;
            }
        });


        // 图片 按钮 记事本 音乐盒 摇一摇
        note_book_button = (ImageButton) findViewById(R.id.note_book_button);
        music_button = (ImageButton) findViewById(R.id.music_button);
        shake_button = (ImageButton) findViewById(R.id.shake);

        // 给图片按钮 设置监听器 跳转到 note_book
        note_book_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Note_book.class);
                startActivity(intent);
            }
        });

        // 给图片按钮 设置监听器 跳转到 music_box
        music_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, music_box.class);
                startActivity(intent);
            }
        });

        shake_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, shake_shake.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}