package com.hbxunluo.installapk;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.hbxunluo.installapk.floatview.FloatManager;
import com.hbxunluo.installapk.utils.FullScreenUtils;
import com.hbxunluo.installapkservice.R;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";
  public static boolean isLaunched = false;
  Activity mContext;
  FloatManager floatManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mContext = this;
    floatManager = new FloatManager(mContext);
    FullScreenUtils.setStillInScreen(mContext, false);
    FullScreenUtils.setFullScreen(getWindow());
    findViewById(R.id.btnExit).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });


  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    floatManager.allowDropDown();

    isLaunched = false;
  }
}
