package com.hbxunluo.installapk;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.hbxunluo.installapkservice.R;

/**
 * Note：None
 * Created by Liuguodong on 2019/1/24 15:40
 * E-Mail Address：986850427@qq.com
 */
public class WaittingActivity extends BaseActivity {

    TextView textView;

    public final static String KEY_DATA = "data";

    @Override
    public void initViews() {
        textView = findViewById(R.id.textView);
        textView.setText("请稍后...");
        handIntent(getIntent());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_install_waiting;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        handIntent(intent);

    }

    void handIntent(Intent intent) {
        String data = intent.getStringExtra(KEY_DATA);
        AssistApkConfig config = JSON.parseObject(data, AssistApkConfig.class);
        if (config.isLoading) {
            if (!TextUtils.isEmpty(config.text)) {
                textView.setText(config.text);
            }
            if (config.delayCloseMillis != 0) {
                textView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, config.delayCloseMillis);
            }
        } else {
            finish();
        }
    }


    public static class AssistApkConfig {
        public boolean isLoading;
        public String text;
        public int delayCloseMillis;//延时关闭,单位毫秒
    }
}
