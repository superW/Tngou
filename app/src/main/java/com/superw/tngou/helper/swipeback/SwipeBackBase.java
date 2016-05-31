package com.superw.tngou.helper.swipeback;


/**
 * Created by wangyc-e on 2016/5/3.
 */
public interface SwipeBackBase {

    SwipeBackLayout getSwipeBackLayout();

    void setSwipeBackEnable(boolean enable);

    void scrollToFinishActivity();

}
