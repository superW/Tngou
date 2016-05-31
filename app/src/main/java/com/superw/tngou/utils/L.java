package com.superw.tngou.utils;

import android.util.Log;

/**日志工具类
 * Created by wangyc-e on 2015/12/23.
 */
public class L {

    public static boolean isDebug = true;
    public static final String TAG = "tngou";
    // 下面四个是默认tag的函数
    public static void i(String msg)
    {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void d(String msg)
    {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void e(String msg)
    {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void v(String msg)
    {
        if (isDebug)
            Log.v(TAG, msg);
    }

}
