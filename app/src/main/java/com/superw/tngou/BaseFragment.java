package com.superw.tngou;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * all of fragment's parent. 
 */
public abstract class BaseFragment extends Fragment {

    private View view = null;

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(getViewId(), container, false);

        if (view != null) {
            preInitView();
            initView(view);
            return view;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    protected abstract int getViewId();

    protected void preInitView() {}

    protected void initView(View view) {}

    protected <T> T getViewById(int id) {
        return (T) view.findViewById(id);
    }
}
