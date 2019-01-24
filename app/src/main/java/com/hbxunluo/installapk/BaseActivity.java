package com.hbxunluo.installapk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.hbxunluo.installapk.floatview.FloatManager;
import com.hbxunluo.installapk.utils.FullScreenUtils;

/**
 * Note：None
 * Created by Liuguodong on 2019/1/24 15:41
 * E-Mail Address：986850427@qq.com
 */
public abstract class BaseActivity extends Activity {

    Activity mContext;
    FloatManager floatManager;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getLayoutId());
        FullScreenUtils.setFullScreen(getWindow());
        FullScreenUtils.setStillInScreen(mContext, true);
        floatManager = new FloatManager(mContext);
        initViews();
    }


    public abstract void initViews();

    public abstract int getLayoutId();

    @Override
    public void onBackPressed() {
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        floatManager.allowDropDown();
    }
}
