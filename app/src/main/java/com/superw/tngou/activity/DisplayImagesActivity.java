package com.superw.tngou.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.superw.tngou.R;
import com.superw.tngou.adapter.CommonAdapter;
import com.superw.tngou.adapter.CommonViewHolder;
import com.superw.tngou.bean.ImageListBean;
import com.superw.tngou.constant.Classify;
import com.superw.tngou.constant.Constants;
import com.superw.tngou.helper.recycleview.OnRecycleViewItemTouchListener;
import com.superw.tngou.helper.swipeback.SwipeBackActivity;
import com.superw.tngou.http.AccessNetWork;
import com.superw.tngou.http.CustomResponseCallBack;
import com.superw.tngou.utils.L;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayImagesActivity extends SwipeBackActivity {

    public static final String EXTRA_POSITION = "position";

    private int page = 1;
    private int row = 20;
    private String id;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_images);

        int position = getIntent().getIntExtra(EXTRA_POSITION, 0);
        if (position != -1) {
            id = Classify.ID[position];
        }

        initViews();

        request();
    }

    private void request() {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("row", String.valueOf(row));
        params.put("id", id);

        AccessNetWork.post(Constants.LIST_URL, params, ImageListBean.class, new CustomResponseCallBack<ImageListBean>() {
            @Override
            public void response(ImageListBean bean) {
                L.e("response=" + bean.toString());
                setData(bean);
            }
        });
    }

    List<ImageListBean.TngouEntity> listTngouEntity;

    private void setData(ImageListBean bean) {
        listTngouEntity = bean.getTngou();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(new CommonAdapter<ImageListBean.TngouEntity>(getApplicationContext(), listTngouEntity, R.layout.item_layout_beautiful_image) {
            @Override
            protected void convert(CommonViewHolder holder, ImageListBean.TngouEntity tngouEntity, int position) {
                TextView textView = holder.getView(R.id.tv_item_beautiful_image_desc);
                textView.setText(tngouEntity.getTitle());

                SimpleDraweeView simpleDraweeView = holder.getView(R.id.iv_item_beautiful_image);
                String url = Constants.BASE_IMAGE_URL + tngouEntity.getImg();
//                L.e(url);
                Uri uri = Uri.parse(url);
                simpleDraweeView.setImageURI(uri);
            }
        });
        mRecyclerView.addOnItemTouchListener(new OnRecycleViewItemTouchListener(mRecyclerView) {
            @Override
            public void onItemClick(RecyclerView parent, RecyclerView.ViewHolder vh, int position) {
                super.onItemClick(parent, vh, position);
                ImageListBean.TngouEntity entity = listTngouEntity.get(position);
                Intent intent = new Intent(DisplayImagesActivity.this, DisplayImageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("entity", entity);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        mRecyclerView = getViewById(R.id.rv_actvitiy_display_image);
    }


}
