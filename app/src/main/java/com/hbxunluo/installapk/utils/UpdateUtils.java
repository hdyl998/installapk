package com.hbxunluo.installapk.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.text.TextUtils;

import com.hbxunluo.installapk.log.MyLogUitls;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.MessageDigest;

/**
 * Created by liugd on 2017/8/2.
 */

public class UpdateUtils {


  /**
   * 安装APK文件
   */
  public static void installApkBySystem(Context mContext, File apkfile) {
    if (apkfile.exists()) {
      Uri mUri = Uri.fromFile(apkfile);
      // 通过Intent安装APK文件
      Intent i = new Intent(Intent.ACTION_VIEW);
      i.setDataAndType(mUri, "application/vnd.android.package-archive");
      i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//加上这句话，不会导致装APP 到一半时闪退
      mContext.startActivity(i);
    }
  }

  /***
   * 判断app是否安装了
   * @param context
   * @param packageName
   * @return
   */
  public static boolean checkAppExist(Context context, String packageName) {
    if (TextUtils.isEmpty(packageName))
      return false;
    try {
      ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName, PackageManager.MATCH_UNINSTALLED_PACKAGES);
      return true;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    return false;
  }

  /**
   * 执行具体的静默安装逻辑，需要手机ROOT。
   *
   * @param apkPath 要安装的apk文件的路径
   * @return 安装成功返回true，安装失败返回false。
   */
  public static boolean installApkSliently(String apkPath) {
    MyLogUitls.print("静默安装" + apkPath);
    PrintWriter PrintWriter = null;
    Process process = null;
    try {
      process = Runtime.getRuntime().exec("su");
      PrintWriter = new PrintWriter(process.getOutputStream());
      PrintWriter.println("chmod 777 " + apkPath);
      PrintWriter.println("export LD_LIBRARY_PATH=/vendor/lib:/system/lib");
      PrintWriter.println("pm install -r " + apkPath);
      //PrintWriter.println("exit");
      PrintWriter.flush();
      PrintWriter.close();
      int value = process.waitFor();


      BufferedReader errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
      String msg = "";
      String line;
      // 读取命令的执行结果
      while ((line = errorStream.readLine()) != null) {
        msg += line;
      }
      MyLogUitls.print("TAG", "install msg is " + msg);

      return value == 0;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (process != null) {
        process.destroy();
      }
    }
    return false;
  }

  /**
   * 114      * 静默卸载
   * 115
   */
  public static boolean uninstallApkSliently(String packageName) {
    PrintWriter PrintWriter = null;
    Process process = null;
    try {
      process = Runtime.getRuntime().exec("su");
      PrintWriter = new PrintWriter(process.getOutputStream());
      PrintWriter.println("LD_LIBRARY_PATH=/vendor/lib:/system/lib ");
      PrintWriter.println("pm uninstall " + packageName);
      PrintWriter.flush();
      PrintWriter.close();
      int value = process.waitFor();
      return value == 0;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (process != null) {
        process.destroy();
      }
    }
    return false;
  }
}
