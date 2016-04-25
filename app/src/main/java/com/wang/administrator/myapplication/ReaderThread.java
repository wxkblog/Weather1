package com.wang.administrator.myapplication;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 获取数据
 * Created by Administrator on 2016/4/24.
 */
public class ReaderThread extends Thread {
    private InputStream inputStream;

    public ReaderThread(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        Log.e("ReaderThread","ReaderThread已启动");
        if(inputStream == null){
            System.out.print("inputStream is null");
            return;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer sb = new StringBuffer();

        //从输入流中读取数据
        try {
            String str = null;
            while ((str = reader.readLine()) != null) {
                //"line.separator"平台独立的换行符
                sb.append(str).append(System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (sb.toString().length() == 0) {
            System.out.print("inputStream is null");
        }else {
            //获取到的数据
            String data=sb.toString().substring(0, sb.toString().length() -
                    System.getProperty("line.separator").length());
            Log.e("获取到的数据为：",data);
            new ParseThread(data).start();
        }

        Log.e("ReaderThread","ReaderThread已结束");
    }
}
