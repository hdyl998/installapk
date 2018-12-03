//package com.hbxunluo.installapk;
//
//import android.app.Service;
//import android.content.Intent;
//import android.content.pm.ApplicationInfo;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.os.IBinder;
//
//import com.hbxunluo.installapk.log.MyLogUitls;
//
//import java.io.File;
//
///**
// * Note：安装apk的服务
// * Created by Liuguodong on 2018/11/6 09:22
// * E-Mail Address：986850427@qq.com
// */
//public class InstallApkSlientlyService extends Service {
//
//
//  @Override
//  public void onCreate() {
//    super.onCreate();
//    MyLogUitls.print(TAG,"onCreate");
//  }
//
//  @Override
//  public IBinder onBind(Intent intent) {
//    return null;
//  }
//
//  private static final String TAG = "InstallApkSlientlyServi";
//
//
//  @Override
//  public int onStartCommand(Intent intent, int flags, int startId) {
//    MyLogUitls.print(TAG,"onStartCommand");
//    try {
//      MyLogUitls.print(TAG,"install 开始");
//      String apkFullPath=intent.getData().getPath();
//      MyLogUitls.print(TAG,"data"+apkFullPath);
//      File fileApk=new File(apkFullPath);
//      if(!fileApk.exists()){
//        MyLogUitls.print(TAG,"apk 不存在"+apkFullPath);
//        return super.onStartCommand(intent, flags, startId);
//      }
//      CalcTime calcTime=new CalcTime();
//      boolean isSuccess= UpdateUtils.installApkSliently(apkFullPath);
//      calcTime.printResult("installTime");
//      MyLogUitls.print("install result"+isSuccess);
//      //安装成功重启自已
//      if(isSuccess){
//        String remotePackgeName=getApkPackgeName(apkFullPath);
//        if(remotePackgeName!=null) {
//
//          //启动首页
//          Intent remoteIntent = getApplication().getPackageManager().getLaunchIntentForPackage(remotePackgeName);
//          remoteIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//          getApplication().startActivity(remoteIntent);
//        }
//        else {
//          MyLogUitls.print(TAG,"APK remotePackgeName  IS"+remotePackgeName);
//        }
//      }
//      MyLogUitls.print("install 结束");
//    }
//    catch (Exception e){
//      e.printStackTrace();
//    }
//
//    return super.onStartCommand(intent, flags, startId);
//  }
//
//  /**
//   * 获取apk对应的包名
//   *
//   * @return
//   */
//  private String  getApkPackgeName(String archiveFilePath) {
//    PackageManager pm = getApplication().getPackageManager();
//    PackageInfo info = pm.getPackageArchiveInfo(archiveFilePath, PackageManager.GET_ACTIVITIES);
//    if (info != null) {
//      ApplicationInfo appInfo = info.applicationInfo;
//      return appInfo.packageName;
//    }
//    return null;
//  }
//
//
//}
