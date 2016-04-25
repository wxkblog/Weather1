package com.wang.administrator.myapplication.Util;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * 消除输入法（软键盘）
 * Created by Administrator on 2016/4/24.
 */
public class HideKeyBoard {

    //注意，这里不是构造方法
    public static void HideKeyBoard(Activity activity){
        InputMethodManager inputMethodManager = (InputMethodManager)
                activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus()
                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
