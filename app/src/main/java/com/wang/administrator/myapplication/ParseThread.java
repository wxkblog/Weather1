package com.wang.administrator.myapplication;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;

import static com.wang.administrator.myapplication.Util.PullWeatherParser.xmlDecoding;

/**
 * 解析数据的线程，将解析的数据发送到主线程
 * Created by Administrator on 2016/4/24.
 */
public class ParseThread extends Thread {
    private String data;

    public ParseThread(String data) {
        this.data = data;
    }

    @Override
    public void run() {
        Log.e("ParseThread", "parseThread已启动");
        //解析数据，发送给主线程
        if (data != null) {
            Message message = new Message();
            Bundle bundle = new Bundle();
//            由于两个网站返回的数据类型和格式不一样，所以解析文件也要重写
            bundle.putStringArrayList("list", (ArrayList<String>) xmlDecoding(data));

            message.setData(bundle);
            MainActivity.handler.sendMessage(message);
        } else {
            System.out.println("数据为空");
        }

        Log.e("ParseThread", "parseThread已结束");
    }


}
