package com.superw.tngou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wangyc-e on 2016/5/30.
 */
public class CommonViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private SparseArray<View> views;
    private View itemView;

    public CommonViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.itemView = itemView;
        this.views = new SparseArray<>();
    }

    public static CommonViewHolder get(Context context, ViewGroup parent, int layoutId) {
        View convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        CommonViewHolder holder = new CommonViewHolder(context, convertView);
        return holder;
    }

    public <T extends View> T getView(int resId) {
        View view = views.get(resId);
        if (view == null) {
            view = itemView.findViewById(resId);
            views.put(resId, view);
        }
        return (T) view;
    }
}
