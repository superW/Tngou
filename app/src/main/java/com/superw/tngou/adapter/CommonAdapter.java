package com.superw.tngou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wangyc-e on 2016/5/30.
 */
public abstract class CommonAdapter<D> extends RecyclerView.Adapter<CommonViewHolder> {

    protected Context context;
    protected int layoutId;
    protected List<D> datas;

    public CommonAdapter(Context context, List<D> datas, int layoutId) {
        this.context = context;
        this.datas = datas;
        this.layoutId = layoutId;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonViewHolder.get(context, parent, layoutId);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        convert(holder, datas.get(position), position);
    }

    protected abstract void convert(CommonViewHolder holder, D d, int position);

    @Override
    public int getItemCount() {
        return datas.size();
    }

}
