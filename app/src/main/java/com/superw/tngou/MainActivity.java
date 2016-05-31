package com.superw.tngou;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.superw.tngou.fragments.BeautifulImageFragment;
import com.superw.tngou.fragments.MineFragment;

import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private BeautifulImageFragment beautifulImageFragment;
    private MineFragment mineFragment;
    private TextView tabBeautifulImage;
    private TextView tabMine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragments(savedInstanceState);

    }

    /**对所有Fragment初始化
     * 内部判断是否 有‘内存重启’情况
     * */
    private void initFragments(Bundle savedInstanceState) {
        if (savedInstanceState != null) {  // “内存重启”时调用
            List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
            for (Fragment fragment : fragmentList) {
                if (fragment instanceof BeautifulImageFragment) {
                    beautifulImageFragment = (BeautifulImageFragment) fragment;
                } else if (fragment instanceof MineFragment) {
                    mineFragment = (MineFragment) fragment;
                }
            }
            // 解决重叠问题
            getSupportFragmentManager().beginTransaction()
                    .show(beautifulImageFragment)
                    .hide(mineFragment)
                    .commit();
        } else {  // 正常时
            beautifulImageFragment = new BeautifulImageFragment();
            mineFragment = new MineFragment();

            // 这里add时，tag可传可不传
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, beautifulImageFragment)
                    .add(R.id.container, mineFragment)
                    .hide(mineFragment)
                    .commit();
        }
    }

    private void initView() {
        tabBeautifulImage = getViewById(R.id.tv_activity_main_bottom_text1);
        tabMine = getViewById(R.id.tv_activity_main_bottom_text2);

        tabBeautifulImage.setOnClickListener(this);
        tabMine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int tabId = v.getId();
        switch (tabId) {
            case R.id.tv_activity_main_bottom_text1:
            case R.id.tv_activity_main_bottom_text2:
                changeTab(tabId);
                break;
        }
    }

    /** 切换Fragment
     * */
    private void changeTab(int tabId){
        switch (tabId) {
            case R.id.tv_activity_main_bottom_text1:
                getSupportFragmentManager().beginTransaction()
                        .show(beautifulImageFragment)
                        .hide(mineFragment)
                        .commit();
                break;
            case R.id.tv_activity_main_bottom_text2:
                getSupportFragmentManager().beginTransaction()
                        .show(mineFragment)
                        .hide(beautifulImageFragment)
                        .commit();
                break;
        }
    }
}
