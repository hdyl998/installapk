package com.hbxunluo.installapk.utils;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * Note：None
 * Created by Liuguodong on 2018/11/8 15:19
 * E-Mail Address：986850427@qq.com
 */
public class FullScreenUtils {


  /**
   * 设置全屏
   *
   * @param window
   */
  public static void setFullScreen(Window window) {
    int mode = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_FULLSCREEN;
    WindowManager.LayoutParams params = window.getAttributes();
    params.systemUiVisibility = params.systemUiVisibility | mode;
    window.setAttributes(params);
  }


  public static void clearFullScreen(Window window) {
    int mode = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
    WindowManager.LayoutParams params = window.getAttributes();
    params.systemUiVisibility = params.systemUiVisibility & ~mode;
    window.setAttributes(params);
  }

  /***
   * 系统级应用的API
   * @param mContext
   * @param isInScreen true始终在屏中,那么不可以下拉
   */
  public static void setStillInScreen(Context mContext, boolean isInScreen) {
    int value = StatusBarManager.DISABLE_NONE;
    if (isInScreen) {
      value = StatusBarManager.DISABLE_NAVIGATION2;
    }
    try {
      Object service = mContext.getSystemService("statusbar");
      Class<?> claz = Class.forName("android.app.StatusBarManager");
      Method expand = claz.getMethod("disable", int.class);
      expand.invoke(service, value);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
