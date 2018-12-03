//package com.hbxunluo.installapk.widegt;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.view.KeyEvent;
//
//import com.hbxunluo.installapk.log.MyLogUitls;
//import com.hbxunluo.installapk.utils.FullScreenUtils;
//
///**
// * Note：系统命令广播
// * Created by Liuguodong on 2018/11/12 20:01
// * E-Mail Address：986850427@qq.com
// */
//public class SystemCmdReceiver extends BroadcastReceiver {
//  public final static String ACTION="com.hbxunluo.com";
//
//  private static final String TAG = "SystemCmdReceiver";
//
//
//  public final static String CMD_CLICK_BACK="clickback";//点击返回键
//  public final static String CMD_START_STILL_IN_SCREEN="start_still_in_screen";//开始霸屏
//  public final static String CMD_EXIT_STILL_IN_SCREEN="exit_still_in_screen";//结束霸屏
//
//  @Override
//  public void onReceive(Context context, Intent intent) {
//    String cmd=intent.getStringExtra("data");
//    MyLogUitls.print(TAG, "on receive action="+intent.getAction()+" data="+cmd);
//    String action = intent.getAction();
//    if (ACTION.equals(action)) {
//      switch (cmd){
//        case CMD_CLICK_BACK:
//          pressBack();
//          break;
//        case CMD_START_STILL_IN_SCREEN:
//          FullScreenUtils.setStillInScreen(context,true);
//          break;
//        case CMD_EXIT_STILL_IN_SCREEN:
//          FullScreenUtils.setStillInScreen(context,false);
//          break;
//      }
//    }
//  }
//
//  private void pressBack(){
//    try {
//      Runtime runtime = Runtime.getRuntime();
//      runtime.exec("input keyevent " + KeyEvent.KEYCODE_BACK);
//    } catch (Exception e) {
//      e.printStackTrace();
//      MyLogUitls.print("runtime", "error");
//    }
//  }
//
//}
