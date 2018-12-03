//package com.hbxunluo.installapk.widegt;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.widget.Toast;
//
//import com.hbxunluo.installapk.CalcTime;
//import com.hbxunluo.installapk.MainActivity;
//import com.hbxunluo.installapk.log.MyLogUitls;
//import com.hbxunluo.installapk.utils.FullScreenUtils;
//import com.hbxunluo.installapk.utils.MyPluginUtils;
//
//
///**
// * Note：开机自启动我们的App
// * Created by Liuguodong on 2018/11/3 16:38
// * E-Mail Address：986850427@qq.com
// */
//public class BootReceiver extends BroadcastReceiver {
//  private static final String TAG = "BootReceiver";
//
//  CalcTime calcTime = new CalcTime();
//
//  @Override
//  public void onReceive(Context context, Intent intent) {
//    calcTime.printResult("action" + intent.getAction());
//
//
//    if(!MainActivity.isLaunched){
//
//      Toast.makeText(context, "开机自启动成功!", 0).show();
//
//      MyLogUitls.print(TAG, "开机自启动成功!");
//      //设置全屏
//      FullScreenUtils.setStillInScreen(context, true);
//
//      MyPluginUtils.launchPackgeMainActivity(context, context.getPackageName());
//    }
//  }
//}
