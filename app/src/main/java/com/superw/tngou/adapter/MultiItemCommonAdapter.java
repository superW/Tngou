package com.superw.tngou.adapter;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wangyc-e on 2016/5/30.
 */
public abstract class MultiItemCommonAdapter<T> extends CommonAdapter<T> {

    private MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public MultiItemCommonAdapter(Context context, List<T> datas, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, datas, -1);
        mMultiItemTypeSupport = multiItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position, datas.get(position));
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        CommonViewHolder holder = CommonViewHolder.get(context, parent, layoutId);
        return holder;
    }
}
