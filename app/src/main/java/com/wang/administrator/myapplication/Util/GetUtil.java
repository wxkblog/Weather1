package com.wang.administrator.myapplication.Util;

import com.wang.administrator.myapplication.ReaderThread;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * 连接到请求的地址
 * Created by Administrator on 2016/4/23.
 */
public class GetUtil {
    public static void get(final String url, final String params) {
        HttpURLConnection conn = null;
        String urlName = url + "?" + params;
        try {
            URL realUrl = new URL(urlName);
            //打开和URL之间的连接
            conn = (HttpURLConnection) realUrl.openConnection();
            //设置连接超时
            conn.setConnectTimeout(5 * 1000);
            //向指定URL发送GET方法的请求
            conn.setRequestMethod("GET");
            //设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0" +
                    "; Windows NT 5.1; SV1)");
            //建立实际的连接
            conn.connect();

            //获取响应所有的响应头字段，这里不需要用所以注释掉
//            Map<String,List<String>> map = conn.getHeaderFields();
//            for(String key:map.keySet()){
//                System.out.println(key+"--->"+map.get(key));
//            }

            InputStream in = conn.getInputStream();
            ReaderThread rt = new ReaderThread(in);
            rt.start();
            rt.join();//等待子线程完成，否则可能数据未读完，stream已经关闭，导致异常
            in.close();//关闭流
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                System.out.println("连接被关闭");
                conn.disconnect();
            }
        }
    }

}