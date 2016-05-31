package com.superw.tngou.helper.recycleview;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by wangyc-e on 2016/5/31.
 */
public abstract class OnRecycleViewItemTouchListener implements RecyclerView.OnItemTouchListener {

    private GestureDetectorCompat mGestureDetector;
    private RecyclerView mRecyclerView;

    public OnRecycleViewItemTouchListener(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mGestureDetector = new GestureDetectorCompat(mRecyclerView.getContext(), new OnItemHelperGestureListener());
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    public void onItemClick(RecyclerView parent, RecyclerView.ViewHolder vh, int position) {}


    public void onItemLongClick(RecyclerView mRecyclerView, RecyclerView.ViewHolder vh, int position) {}


    private class OnItemHelperGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View child = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
                RecyclerView.ViewHolder vh = mRecyclerView.getChildViewHolder(child);
                int position = mRecyclerView.getChildAdapterPosition(child);
                onItemClick(mRecyclerView, vh, position);
            }
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            View child = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
                RecyclerView.ViewHolder vh = mRecyclerView.getChildViewHolder(child);
                int position = mRecyclerView.getChildAdapterPosition(child);
                onItemLongClick(mRecyclerView, vh, position);
            }
        }
    }

}

