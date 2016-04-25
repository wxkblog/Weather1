package com.wang.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wang.administrator.myapplication.Util.HideKeyBoard;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    private Button getWeather;
    private EditText city;
    private static TextView city_name, weather;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        getWeather = (Button) findViewById(R.id.getWeather);
        city = (EditText) findViewById(R.id.city);
        city_name = (TextView) findViewById(R.id.city_name);
        weather = (TextView) findViewById(R.id.city_weather);

        city.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    getWeather();
                }
                return false;
            }
        });
        getWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWeather();
            }
        });
    }

    public static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ArrayList<String> list = null;
            list = msg.getData().getStringArrayList("list");
            if(list.size()>8) {//因为网站返回异常时，list.size会比比较小，那样就数组越界了
                city_name.setText(list.get(1));//常德
                weather.setText(list.get(7));//4月24日 多云转阵雨
            }
        }
    };

    public void getWeather(){

        //隐藏键盘
        HideKeyBoard.HideKeyBoard(this);

        String cityName = city.getText().toString();
        if (cityName != null && !cityName.equals("")) {
            new NetThread(cityName).start();
        }

    }
}



