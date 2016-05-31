package com.superw.tngou.fragments;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.superw.tngou.BaseFragment;
import com.superw.tngou.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {

    @Override
    protected int getViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        TextView textView = getViewById(R.id.tv_mine_top);
        textView.setText("this is mine introduction.");
    }
}
