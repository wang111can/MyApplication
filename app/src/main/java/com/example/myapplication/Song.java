package com.example.myapplication;


// 自定义类型(歌曲图片 + 歌曲名字)
public class Song {
    private int _song_img_id;
    private int _song_media_id;
    private String _song_name;

    public Song(int song_img_id, int song_media_id, String song_name) {
        _song_img_id = song_img_id;
        _song_name = song_name;
        _song_media_id = song_media_id;
    }

    public int get_song_img_id() {
        return _song_img_id;
    }

    public int get_song_media_id() {
        return _song_media_id;
    }

    public String get_song_name() {
        return _song_name;
    }
}
