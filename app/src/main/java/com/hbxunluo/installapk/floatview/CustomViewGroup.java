package com.hbxunluo.installapk.floatview;



import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;


/**
 * Note：None
 * Created by Liuguodong on 2018/11/6 20:01
 * E-Mail Address：986850427@qq.com
 */
public class CustomViewGroup extends ViewGroup {


  public CustomViewGroup(Context context) {
    super(context);
  }

  public CustomViewGroup(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    return true;
  }
}
