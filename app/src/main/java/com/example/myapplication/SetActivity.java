package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class SetActivity extends AppCompatActivity {

    Toolbar toolbar;
    Switch music_switch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set);

        // 代码使用方法来源于网络 start
        toolbar = findViewById(R.id.set_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//添加默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        // end



        music_switch = findViewById(R.id.music_switch);

        // 设置初始状态
        music_switch.setChecked(MainActivity.set_switch_check[0]);

        music_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                MainActivity.set_switch_check[0] = !MainActivity.set_switch_check[0];
                if (MainActivity.set_switch_check[0] == true) {
                    startService(MainActivity.background_music);
                }
                else {
                    stopService(MainActivity.background_music);
                }
            }
        });




    }


}