package com.hbxunluo.installapk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.hbxunluo.installapk.floatview.FloatManager;
import com.hbxunluo.installapk.log.MyLogUitls;
import com.hbxunluo.installapk.utils.FullScreenUtils;
import com.hbxunluo.installapk.utils.MyPluginUtils;
import com.hbxunluo.installapk.utils.UpdateUtils;
import com.hbxunluo.installapkservice.R;

import java.io.File;

/**
 * Note：安装的apk的Activity
 * Created by Liuguodong on 2018/11/6 10:42
 * E-Mail Address：986850427@qq.com
 */
public class InstallWaittingActivity extends Activity {

  private static final String TAG = "InstallWaittingActivity";

  FloatManager floatManager;
  Context mContext;
  Handler handler = new Handler();
  private Runnable runnableWork = new Runnable() {
    @Override
    public void run() {
      handInstallWork(getIntent());
      finish();
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_install_waiting);
    mContext = this;
    FullScreenUtils.setFullScreen(getWindow());
    new Thread(runnableWork).start();
    FullScreenUtils.setStillInScreen(mContext,true);
    floatManager=new FloatManager(mContext);
  }

  @Override
  public void onBackPressed() {
  }

  private void handInstallWork(Intent intent) {
    try {
      MyLogUitls.print(TAG, "install 开始");
      String apkFullPath = intent.getStringExtra("path");
      MyLogUitls.print(TAG, "data " + apkFullPath);
      File fileApk = new File(apkFullPath);
      if (!fileApk.exists()) {
        MyLogUitls.print(TAG, "apk 不存在" + apkFullPath);
        return;
      }
      PackageInfo apkPackgeInfo = MyPluginUtils.getApkPackgeInfo(mContext, apkFullPath);
      if (apkPackgeInfo == null) {
        MyLogUitls.print("apk 里面没有包名");
        return;
      }
      String apkPackgeName = apkPackgeInfo.packageName;
      MyLogUitls.print("apk 里面包名" + apkPackgeName);

      CalcTime calcTime = new CalcTime();
      //检查是否安装了
      if (UpdateUtils.checkAppExist(mContext, apkPackgeName)) {
        //检查本机安装的apk的签名
        PackageInfo packageInfo = MyPluginUtils.getPackageInfo(mContext, apkPackgeName);
        if (isNeedUnstall(apkPackgeInfo, packageInfo)) {
          boolean isSuccess = UpdateUtils.uninstallApkSliently(apkPackgeName);
          calcTime.printResult("卸载耗时");
          MyLogUitls.print("静默卸载 result" + isSuccess);
        } else {
          MyLogUitls.print("不需要卸载");
        }
      } else {
        MyLogUitls.print("当前设备没有安装 " + apkPackgeName);
      }
      final boolean isSuccess = UpdateUtils.installApkSliently(apkFullPath);
      calcTime.printResult("installTime");
      MyLogUitls.print("安装结果 " + isSuccess);
      //安装成功重启自已
      if (isSuccess) {
        if (apkPackgeName != null) {
          //启动首页
          MyPluginUtils.launchPackgeMainActivity(mContext,apkPackgeName);
        } else {
          MyLogUitls.print("重启动" + apkPackgeName);
        }
      }
      MyLogUitls.print("install 结束");

      handler.post(new Runnable() {
        @Override
        public void run() {
          Toast.makeText(mContext, isSuccess ? "更新成功！" : "更新安装失败，请联系服务商！", 0).show();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  /***
   * 判断是否需要卸载apk
   * @param packageInfoApk
   * @param packageInfoThis
   * @return
   */
  private boolean isNeedUnstall(PackageInfo packageInfoApk, PackageInfo packageInfoThis) {
    String packSignApk = MyPluginUtils.packgeInfo2SignatureMD5(packageInfoApk);
    String packSignThis = MyPluginUtils.packgeInfo2SignatureMD5(packageInfoThis);

    MyLogUitls.print("appSingnature apk " + packSignApk);
    MyLogUitls.print("appSingnature this " + packSignThis);
    //签名不相同需要卸载
    if (!packSignApk.equals(packSignThis)) {
      return true;
    }
    MyLogUitls.print("appversionCode apk " + packageInfoApk.versionCode);
    MyLogUitls.print("appversionCode this " + packageInfoThis.versionCode);

    //签名相同但想装回老版本，则需要卸载
    if (packageInfoApk.versionCode < packageInfoThis.versionCode) {
      return true;
    }
    return false;

  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    floatManager.allowDropDown();
  }
}
