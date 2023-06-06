package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Random;

public class shake_shake extends AppCompatActivity {

    Toolbar toolbar;
    String[] result = {
            "大吉", "小吉", "大凶", "小凶", "无事发生"
    };
    SensorManager mSensorManager;
    Sensor mSensor;
    SensorEventListener listener;
    boolean is_shook = false;
    AlertDialog.Builder builder = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake_shake);

        // 代码使用方法来源于网络 start
        toolbar = findViewById(R.id.shake_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//添加默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        // end


        // 加速度 传感器
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // 监听
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                if (is_shook == false) {


                    float[] values = event.values;
                    if (values[0] > 15 || values[1] > 15 || values[2] > 20) {
                        is_shook = true;
                        Random random = new Random();
                        int randomint = random.nextInt(5); // 随机得到 0 ~ 5 之间的数字 来 提示运势
                        builder = new AlertDialog.Builder(shake_shake.this);
                        builder.setTitle("今日运势");
                        builder.setCancelable(false);
                        builder.setMessage(result[randomint]);
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                is_shook = false;
                            }
                        });
                        builder.show();

                    }
                }
            }


            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        mSensorManager.registerListener(listener, mSensor,  SensorManager.SENSOR_DELAY_UI);

    }

    // 来源于 网络
    @Override
    public void onDestroy() {
        super.onDestroy();
        mSensorManager.unregisterListener(listener, mSensor);
    }

}

