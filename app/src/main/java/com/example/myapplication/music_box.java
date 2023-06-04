package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class music_box extends AppCompatActivity {

    Toolbar toolbar, bottom_bar;


    List<Song> music_box_list_item_unit = null;
    Song_Adapter song_adapter = null;
    ListView music_list;
    int[] img_id = new int[1024];
    String[] song_name = new String[1024];
    int img_idx = 0;
    int song_name_idx = 0;
    final int[] song_img_resource_id = {R.drawable.start, R.drawable.stop};
    int now_song_img_resource_id = 1;
    boolean is_clicked = false; // 来判断是否选择了歌曲
    ImageButton song_img, next_song, previous_song, song_state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_box);

        // 代码使用方法来源于网络 start
        toolbar = findViewById(R.id.music_box_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//添加默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        // end


        // 设置list
        music_list = (ListView) findViewById(R.id.music_list);
        music_box_list_item_unit = new ArrayList<Song>();
        init();
        song_adapter = new Song_Adapter(music_box.this, R.layout.activity_music_list_item, music_box_list_item_unit);
        music_list.setAdapter(song_adapter);




        // 播放栏
        song_img = (ImageButton) findViewById(R.id.bar_img1);
        previous_song = (ImageButton) findViewById(R.id.bar_img2);
        song_state = (ImageButton) findViewById(R.id.bar_img3);
        next_song = (ImageButton) findViewById(R.id.bar_img4);



        music_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                is_clicked = true;
                Song _song = music_box_list_item_unit.get(position);
                song_state.setImageResource(R.drawable.start);
                now_song_img_resource_id = 0;
                song_img.setImageResource(_song.get_song_img_id());
            }
        });


        // 暂停 或者 播放 音乐
        song_state.setOnClickListener(new View.OnClickListener() {
            // 点击时 判断 当前 是否 有 在播放歌曲 在 对 状态图片进行变换
            @Override
            public void onClick(View v) {
                // 选择了歌曲才能对歌曲的状态进行更改
                if (is_clicked) {
                    song_state.setImageResource(song_img_resource_id[now_song_img_resource_id ^ 1]);
                    now_song_img_resource_id ^= 1;
                }
            }
        });

        next_song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        previous_song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




//        bottom_bar = (Toolbar) findViewById(R.id.toolbar2);

    }
    private void init() {

        // 保存 歌曲图片 id
        img_id[img_idx ++ ] = R.drawable.changhuan;
        img_id[img_idx ++ ] = R.drawable.chuanqi;
        img_id[img_idx ++ ] = R.drawable.hongdou;
        img_id[img_idx ++ ] = R.drawable.qingfenxulai;
        img_id[img_idx ++ ] = R.drawable.renjian;
        img_id[img_idx ++ ] = R.drawable.ruyuan;
        img_id[img_idx ++ ] = R.drawable.weifenxiyu;
        img_id[img_idx ++ ] = R.drawable.youlancao;

        song_name[song_name_idx ++ ] = "偿还-王菲";
        song_name[song_name_idx ++ ] = "传奇-王菲";
        song_name[song_name_idx ++ ] = "红豆-王菲";
        song_name[song_name_idx ++ ] = "清风徐来-王菲";
        song_name[song_name_idx ++ ] = "人间-王菲";
        song_name[song_name_idx ++ ] = "如愿-王菲";
        song_name[song_name_idx ++ ] = "微风细雨-王菲";
        song_name[song_name_idx ++ ] = "幽兰操-王菲";

        for (int i = 0;i < img_idx;i ++ )  {
            music_box_list_item_unit.add(new Song(img_id[i], song_name[i]));
        }
    }
}