package com.superw.tngou.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.superw.tngou.BaseFragment;
import com.superw.tngou.R;
import com.superw.tngou.activity.DisplayImagesActivity;
import com.superw.tngou.adapter.CommonAdapter;
import com.superw.tngou.adapter.CommonViewHolder;
import com.superw.tngou.constant.Classify;
import com.superw.tngou.helper.recycleview.OnRecycleViewItemTouchListener;
import com.superw.tngou.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class BeautifulImageFragment extends BaseFragment {

    private int[] resIds = {R.drawable.a1, R.drawable.a2, R.drawable.a3,
            R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7};

    private Activity mActivity;

    private RecyclerView mRecyclerView;

    @Override
    protected int getViewId() {
        return R.layout.fragment_beautiful_image;
    }

    @Override
    protected void preInitView() {
        super.preInitView();
        mActivity = getActivity();
    }

    @Override
    protected void initView(View fragmentView) {
        super.initView(fragmentView);

        mRecyclerView = getViewById(R.id.rv);
        setRecyclerView(mRecyclerView);

    }

    private void setRecyclerView(RecyclerView recyclerView) {
        List<Integer> list = getDatas();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));


        CommonAdapter<Integer> commonAdapter = new CommonAdapter<Integer>(mActivity, list, R.layout.item_layout_beautiful_image) {

            @Override
            protected void convert(CommonViewHolder holder, Integer integer, int position) {
                Uri uri = Uri.parse("res://drawable/" + integer);
                SimpleDraweeView imageView = holder.getView(R.id.iv_item_beautiful_image);
                imageView.setImageURI(uri);

//                ImageView imageView = holder.getView(R.id.iv_item_beautiful_image);
//                imageView.setImageResource(integer);
                TextView textView = holder.getView(R.id.tv_item_beautiful_image_desc);
                textView.setText(Classify.CLASSIFY[position]);
            }
        };

        recyclerView.setAdapter(commonAdapter);

        recyclerView.addOnItemTouchListener(new OnRecycleViewItemTouchListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView parent, RecyclerView.ViewHolder vh, int position) {
                Intent intent = new Intent(mActivity, DisplayImagesActivity.class);
                intent.putExtra(DisplayImagesActivity.EXTRA_POSITION, position);
                startActivity(intent);
            }
        });
    }

    private List<Integer> getDatas() {
        List<Integer> datas = new ArrayList();
        for (int id : resIds) {
            datas.add(id);
        }
        return datas;
    }
}
