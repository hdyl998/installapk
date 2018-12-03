//package com.hbxunluo.installapk;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.widget.Toast;
//
//import com.hbxunluo.installapk.log.MyLogUitls;
//
//import java.lang.reflect.Array;
//import java.util.Arrays;
//
///**
// * Note：None
// * Created by Liuguodong on 2018/11/8 12:42
// * E-Mail Address：986850427@qq.com
// */
//
//public class SDStatusReceiver extends BroadcastReceiver {
//
//
//  private String ACTION_CARD_READY="android.intent.action.MEDIA_MOUNTED";
//  private String ACTION_CARD_REMOVED="android.intent.action.MEDIA_REMOVED";
//  private String ACTION_CARD_UNMOUNTED="android.intent.action.MEDIA_UNMOUNTED";
//
//
//  @Override
//
//  public void onReceive(Context context, Intent intent) {
//
//    //判断收到的到底是什么广播
//
//    String action = intent.getAction();
//
//    if(ACTION_CARD_READY.equals(action)){
////      Toast.makeText(context, "SD卡可用", 0).show();
//      MyLogUitls.print("SD卡可用");
//      MyLogUitls.print(SdCardUtil.getInnerSDCardPath());
//      for (String s:SdCardUtil.getExtSDCardPathList()){
//        MyLogUitls.print("my"+s);
//      }
//    }
//    else if(ACTION_CARD_REMOVED.equals(action)){
////      Toast.makeText(context, "SD卡拔出", 0).show();
//    }
//    else if(ACTION_CARD_UNMOUNTED.equals(action)){
////      Toast.makeText(context, "SD卡不可用", 0).show();
//    }
//
//  }
//
//}
