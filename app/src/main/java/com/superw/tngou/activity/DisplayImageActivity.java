package com.superw.tngou.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.view.SimpleDraweeView;
import com.superw.tngou.R;
import com.superw.tngou.bean.ImageListBean;
import com.superw.tngou.constant.Constants;
import com.superw.tngou.helper.swipeback.SwipeBackActivity;

public class DisplayImageActivity extends SwipeBackActivity {

    ImageListBean.TngouEntity entity;
    private SimpleDraweeView iv_activity_display_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);
        initView();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            entity = (ImageListBean.TngouEntity) bundle.getSerializable("entity");
        }

        String url = Constants.BASE_IMAGE_URL + entity.getImg();
        Uri uri = Uri.parse(url);
        iv_activity_display_image.setImageURI(uri);
        setTitle(entity.getTitle());
    }

    private void initView() {
        iv_activity_display_image = (SimpleDraweeView) findViewById(R.id.iv_activity_display_image);
    }
}
