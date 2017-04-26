package com.superw.tngou.http;

import com.google.gson.Gson;
import com.superw.tngou.utils.L;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.builder.PostStringBuilder;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by wangyc-e on 2016/5/30.
 */
public class AccessNetWork {

    /***
     *  get请求方式
     * @param url 请求地址
     * @param callBack 请求回调
     * @param tClass 请求回调后的javabean
     * @param <T>
     */
    public static <T> void get (String url, Class<T> tClass, CustomResponseCallBack<T> callBack) {
        execute(getGetBuild(url, null), callBack, tClass);
    }

    public static <T> void get (String url, Map<String, String> params, Class<T> tClass, CustomResponseCallBack<T> callBack) {
        execute(getGetBuild(url, params), callBack, tClass);
    }

    /***
     *  post请求方式
     * @param url 请求地址
     * @param callBack 请求回调
     * @param tClass 请求回调后的javabean
     * @param <T>
     */
    public static <T> void post (String url, final Class<T> tClass, final CustomResponseCallBack<T> callBack) {
        execute(getPostBuild(url, null), callBack, tClass);
    }

    public static <T> void post (String url, Map<String, String> params, Class<T> tClass, CustomResponseCallBack<T> callBack) {
        execute(getPostBuild(url, params), callBack, tClass);
    }

    private static RequestCall getGetBuild(String url, Map<String, String> params) {
        GetBuilder builder = OkHttpUtils.get().url(url).params(params);
        return builder.build();
    }

    private static RequestCall getPostBuild(String url, Map<String, String> params) {
        PostFormBuilder builder = OkHttpUtils.post().url(url).params(params);
        return builder.build();
    }

    private static <T> void execute(RequestCall call, final CustomResponseCallBack<T> callBack, final Class<T> tClass) {
        call.execute(new Callback<T>() {
            @Override
            public T parseNetworkResponse(Response response) throws Exception {
                String json = response.body().string();
                Gson gson = new Gson();
                T t = gson.fromJson(json, tClass);
                return t;
            }

            @Override
            public void onError(Call call, Exception e) {
                L.e("exception message is " + e.getMessage());
            }

            @Override
            public void onResponse(T response) {
                callBack.response(response);
            }
        });
    }


}
