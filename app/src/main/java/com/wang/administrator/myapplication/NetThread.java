package com.wang.administrator.myapplication;

import android.util.Log;

import com.wang.administrator.myapplication.Util.DataUtil;
import com.wang.administrator.myapplication.Util.GetUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/4/24.
 */
public class NetThread extends Thread {

    private String cityName;

    public NetThread(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public void run() {
        try {
            Log.e("NetThread", "NetThread已启动");

//            调用http://www.webxml.com.cn/zh_cn/web_services.aspx的数据
            GetUtil.get(DataUtil.Data.URL, DataUtil.Data.CITY_NAME +
                    URLEncoder.encode(cityName, "UTF-8") + DataUtil.Data.KEY);

            Log.e("NetThread", "NetThread已关闭");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
