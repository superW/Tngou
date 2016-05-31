package com.superw.tngou.activity;

import android.os.Bundle;

import com.superw.tngou.BaseActivity;
import com.superw.tngou.R;
import com.superw.tngou.bean.ImageListBean;
import com.superw.tngou.constant.Classify;
import com.superw.tngou.constant.Constants;
import com.superw.tngou.helper.swipeback.SwipeBackActivity;
import com.superw.tngou.http.AccessNetWork;
import com.superw.tngou.http.CustomResponseCallBack;
import com.superw.tngou.utils.L;

import java.util.HashMap;
import java.util.Map;

public class DisplayImagesActivity extends SwipeBackActivity {

    public static final String EXTRA_POSITION = "position";

    private int page = 1;
    private int row = 20;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_images);
        int position = getIntent().getIntExtra(EXTRA_POSITION, 0);

        if (position != -1) {
            id = Classify.ID[position];
        }

        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("row", String.valueOf(row));
        params.put("id", id);

        AccessNetWork.post(Constants.LIST_URL, params, ImageListBean.class, new CustomResponseCallBack<ImageListBean>() {
            @Override
            public void response(ImageListBean bean) {
                L.e("response=" + bean.toString());
            }
        });
    }



}
