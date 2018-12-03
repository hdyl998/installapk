//package com.hbxunluo.installapk.widegt;
//
//import android.app.Service;
//import android.content.Context;
//import android.content.Intent;
//import android.os.IBinder;
//
//import com.hbxunluo.installapk.utils.FullScreenUtils;
//
///**
// * Note：None
// * Created by Liuguodong on 2018/11/12 09:28
// * E-Mail Address：986850427@qq.com
// */
//public class HandleSystemService extends Service {
//
//  public final static String CMD_CLICK_BACK="backclick";
//  public final static String CMD_PULL_TRUE="fullscreen_true";
//  public final static String CMD_PULL_FALSE="fullscreen_false";
//  Context mContext;
//  @Override
//  public void onCreate() {
//    super.onCreate();
//    mContext=this;
//  }
//
//  @Override
//  public int onStartCommand(Intent intent, int flags, int startId) {
//    String cmd=intent.getStringExtra("order");
//    switch (cmd){
//      case CMD_CLICK_BACK:
//        break;
//      case CMD_PULL_TRUE:
//        FullScreenUtils.setStillInScreen(mContext,true);
//        break;
//      case CMD_PULL_FALSE:
//        break;
//    }
//    return super.onStartCommand(intent, flags, startId);
//  }
//
//  @Override
//  public IBinder onBind(Intent intent) {
//    return null;
//  }
//}
