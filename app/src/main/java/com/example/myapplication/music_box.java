package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.media.MediaPlayer;
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
    int[] media_id = new int[1024];
    int media_idx = 0;
    int img_idx = 0;
    int song_name_idx = 0;
    final int[] song_img_resource_id = {R.drawable.start, R.drawable.stop}; // 音乐状态图片
    int now_song_img_resource_id = 1;
// 当前音乐状态图片
    int now_start_song_idx = -1; // 当前播放音乐的 list 下标
    boolean is_clicked = false; // 来判断是否选择了歌曲
    ImageButton song_img, next_song, previous_song, song_state;

    MediaPlayer mediaPlayer;

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
                if (now_start_song_idx != -1) {
                    mediaPlayer.stop();
                }
                now_start_song_idx = position;
                Song _song = music_box_list_item_unit.get(position);
                // 更改播放栏图片
                song_state.setImageResource(R.drawable.start);
                now_song_img_resource_id = 0;
                song_img.setImageResource(_song.get_song_img_id());

                mediaPlayer = mediaPlayer.create(music_box.this, _song.get_song_media_id());
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
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
                    if (now_song_img_resource_id == 0) {
                        mediaPlayer.start();
                    }
                    else mediaPlayer.pause();
                }
            }
        });

        next_song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_clicked) {
                    now_start_song_idx = (now_start_song_idx + 1) % img_idx;

                    Song song = music_box_list_item_unit.get(now_start_song_idx);
                    mediaPlayer.stop();

                    mediaPlayer = MediaPlayer.create(music_box.this, song.get_song_media_id());
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                    // 更改播放栏图片
                    song_img.setImageResource(song.get_song_img_id());

                    now_song_img_resource_id = 0;
                    song_state.setImageResource(song_img_resource_id[now_song_img_resource_id]);

                }
            }
        });


        // 上一首歌曲
        previous_song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_clicked) {
                    now_start_song_idx -- ;
                    if (now_start_song_idx < 0) now_start_song_idx = img_idx - 1;

                    Song song = music_box_list_item_unit.get(now_start_song_idx);
                    mediaPlayer.stop();

                    mediaPlayer = MediaPlayer.create(music_box.this, song.get_song_media_id());
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                    // 更改播放栏图片
                    song_img.setImageResource(song.get_song_img_id());

                    now_song_img_resource_id = 0;
                    song_state.setImageResource(song_img_resource_id[now_song_img_resource_id]);
                }
            }
        });




//        bottom_bar = (Toolbar) findViewById(R.id.toolbar2);

    }

    // 初始化歌单 固定歌单
    private void init() {

        // 保存 歌曲图片 id
        img_id[img_idx ++ ] = R.drawable.changhuan;
        img_id[img_idx ++ ] = R.drawable.chuanqi;
        img_id[img_idx ++ ] = R.drawable.hongdou;
        img_id[img_idx ++ ] = R.drawable.qingfenxulai;
        img_id[img_idx ++ ] = R.drawable.renjian;
        img_id[img_idx ++ ] = R.drawable.ruyuan;
        img_id[img_idx ++ ] = R.drawable.youlancao;

        song_name[song_name_idx ++ ] = "偿还-王菲";
        song_name[song_name_idx ++ ] = "传奇-王菲";
        song_name[song_name_idx ++ ] = "红豆-王菲";
        song_name[song_name_idx ++ ] = "清风徐来-王菲";
        song_name[song_name_idx ++ ] = "人间-王菲";
        song_name[song_name_idx ++ ] = "如愿-王菲";
        song_name[song_name_idx ++ ] = "幽兰操-王菲";

        media_id[media_idx ++ ] = R.raw.changhuan;
        media_id[media_idx ++ ] = R.raw.chuanqi;
        media_id[media_idx ++ ] = R.raw.hongdou;
        media_id[media_idx ++ ] = R.raw.qingfenxulai;
        media_id[media_idx ++ ] = R.raw.renjian;
        media_id[media_idx ++ ] = R.raw.ruyuan;
        media_id[media_idx ++ ] = R.raw.youlancao;


        for (int i = 0;i < img_idx;i ++ )  {
            music_box_list_item_unit.add(new Song(img_id[i], media_id[i], song_name[i]));
        }
    }

    // 销毁时 停止音乐
    @Override
    public void  onDestroy() {
        super.onDestroy();
        if (now_start_song_idx != -1)  mediaPlayer.stop();
    }
}