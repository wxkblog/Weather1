package com.wang.administrator.myapplication.Util;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * pull解析器解析天气
 * Created by Administrator on 2016/4/24.
 */
public class PullWeatherParser {
    public static List<String> xmlDecoding(String xml) {
        XmlPullParser pullParser = Xml.newPullParser();//由android.util.Xml创建一个XmlPullParser实例

        StringReader sr = new StringReader(xml);
        List<String> list = new ArrayList<>();
        try {
            pullParser.setInput(sr);
            int eventType = pullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        String tag = pullParser.getName();
                        if (tag.equals("string")) {
                            list.add(pullParser.nextText());
                        }
                        break;
                    default:
                        break;
                }
                eventType = pullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Log.e("解析到的list", list.toString());
//        Log.e("parse解析", "结束");
//        解析到的list: [湖南 常德, 常德, 1662, 2016/04/24 13:12:28,
//                今日天气实况：气温：18℃；风向/风力：北风 2级；湿度：71%     ,
//                紫外线强度：弱。空气质量：中。                           ,
//        紫外线指数：弱，辐射较弱，涂擦SPF12-15、PA+护肤品。
//        感冒指数：较易发，天较凉，增加衣服，注意防护。
//        穿衣指数：较舒适，建议穿薄外套或牛仔裤等服装。
//        洗车指数：不宜，有雨，雨水和泥水会弄脏爱车。
//        运动指数：较适宜，气温较低，推荐您进行室内运动。
//        空气污染指数：中，易感人群应适当减少室外活动。                       ,
//         4月24日 多云转阵雨, 16℃/21℃, 北风微风, 1.gif, 3.gif]
        return list;
    }
}
