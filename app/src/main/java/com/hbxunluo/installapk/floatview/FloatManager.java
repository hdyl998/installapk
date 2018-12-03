package com.hbxunluo.installapk.floatview;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.hbxunluo.installapk.log.MyLogUitls;


/**
 * Note：添加一个悬浮窗防止用户下拉菜单
 * Created by Liuguodong on 2018/11/6 20:02
 * E-Mail Address：986850427@qq.com
 */
public class FloatManager {

  View view;
  WindowManager manager;


  public FloatManager(Context mContext) {
    mContext=mContext.getApplicationContext();
    manager = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE));
    WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams();
    localLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
    localLayoutParams.gravity = Gravity.TOP;
    localLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|
      // this is to enable the notification to recieve touch events
      WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
      // Draws over status bar
      WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
    localLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
    int statusHeight=getStatusHeight(mContext);
    localLayoutParams.height = statusHeight;
    MyLogUitls.print("getStatusHeight"+statusHeight);

    localLayoutParams.format = PixelFormat.TRANSPARENT;
    view = new CustomViewGroup(mContext);
    manager.addView(view, localLayoutParams);
  }

  /**
   * 获得状态栏的高度
   *
   * @param context
   * @return
   */
  public static int getStatusHeight(Context context) {
    int statusHeight = -1;
    try {
      Class<?> clazz = Class.forName("com.android.internal.R$dimen");
      Object object = clazz.newInstance();
      int height = Integer.parseInt(clazz.getField("status_bar_height")
        .get(object).toString());
      statusHeight = context.getResources().getDimensionPixelSize(height);
    } catch (Exception e)
    {
      e.printStackTrace();
    }
    return statusHeight;
  }



  public void allowDropDown(){
    manager.removeView(view);
  }
}
