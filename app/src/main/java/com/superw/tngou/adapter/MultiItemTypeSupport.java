package com.superw.tngou.adapter;

/**
 * Created by wangyc-e on 2016/5/30.
 */
public interface MultiItemTypeSupport<T> {
    int getLayoutId(int itemType);

    int getItemViewType(int position, T t);
}
