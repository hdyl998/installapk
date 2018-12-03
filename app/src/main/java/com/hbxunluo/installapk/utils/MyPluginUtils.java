package com.hbxunluo.installapk.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

import com.hbxunluo.installapk.log.MyLogUitls;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;


/**
 * Note：None
 * Created by Liuguodong on 2018/11/1 17:10
 * E-Mail Address：986850427@qq.com
 */
public class MyPluginUtils {


  // 如需要小写则把ABCDEF改成小写
  private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

  /***
   * 关闭
   * @param closeable
   */
  public static void quietlyClose(Closeable closeable) {
    try {
      if (closeable != null) {
        closeable.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 是否禁用下拉
   *
   * @param isEnable
   */
  public static void enableTouchOutSide(boolean isEnable) {

  }

  /**
   * 应用程序运行命令获取 Root权限，设备必须已破解(获得ROOT权限)
   *
   * @return 应用程序是/否获取Root权限
   */
  public static boolean requestRootPermission(Context mContext) {
    Process process = null;
    DataOutputStream os = null;
    try {
      String cmd = "chmod 777 " + mContext.getPackageCodePath();
      process = Runtime.getRuntime().exec("su"); // 切换到root帐号
      os = new DataOutputStream(process.getOutputStream());
      os.writeBytes(cmd + "\n");
      os.writeBytes("exit\n");
      os.flush();
      process.waitFor();
    } catch (Exception e) {
      return false;
    } finally {
      try {
        if (os != null) {
          os.close();
        }
        process.destroy();
      } catch (Exception e) {
      }
    }
    return true;
  }

  /***
   * 判断设备是否ROOT
   * @return
   */
  public static boolean isRoot() {

    boolean root = false;

    try {
      if ((!new File("/system/bin/su").exists())
        && (!new File("/system/xbin/su").exists())) {
        root = false;
      } else {
        root = true;
      }
    } catch (Exception e) {
    }
    return root;
  }

  /**
   * 判断app是否获得root权限
   *
   * @return
   */

  public static boolean isAppRoot() {
    try {
      Process process = Runtime.getRuntime().exec("su");
      DataOutputStream os = new DataOutputStream(process.getOutputStream());
      os.writeBytes("ls /data/data/\n");
      os.writeBytes("exit\n");
      os.flush();

      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      StringBuilder builder = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        builder.append(line);
        builder.append(System.getProperty("line.separator"));
      }
      String result = builder.toString();
      MyLogUitls.print("tag", result);
      MyLogUitls.print("tag", "after Log string buffer");
      if (result.contains("com.android.phone")) {
        return true;
      }
    } catch (IOException e) {
      return false;
    }
    return false;
  }

  /***
   * 重新启动Activity 以前的intent是保存的
   * @param mContext
   */
  public static void restartActivitySelf(Activity mContext) {
    Intent intent = mContext.getIntent();
    mContext.overridePendingTransition(0, 0);
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    mContext.finish();
    mContext.overridePendingTransition(0, 0);
    mContext.startActivity(intent);
  }

  /**
   * 检测应用程序是否是用"CN=Android Debug,O=Android,C=US"的debug信息来签名的 * 判断签名是debug签名还是release签名
   */
//private final static X500Principal DEBUG_DN = new X500Principal( "CN=Android Debug,O=Android,C=US"); /** * 进行转换 */
  public static String toHexString(byte[] bData) {
    StringBuilder sb = new StringBuilder(bData.length * 2);
    for (int i = 0; i < bData.length; i++) {
      sb.append(HEX_DIGITS[(bData[i] & 0xf0) >>> 4]);
      sb.append(HEX_DIGITS[bData[i] & 0x0f]);
    }
    return sb.toString();
  }

  /**
   * 返回MD5
   */
  public static String packgeInfo2SignatureMD5(PackageInfo packageInfo) {
    try {
      MessageDigest digest = MessageDigest.getInstance("MD5");
      if (packageInfo != null) {
        for (Signature s : packageInfo.signatures)
          digest.update(s.toByteArray());
      }
      return toHexString(digest.digest());
    } catch (Exception e) {
      return "";
    }
  }

  /***
   * 获取对应包的签名
   * @param mContext
   * @param packgeName
   * @return
   */
  public static PackageInfo getPackageInfo(Context mContext, String packgeName) {
    try {
      PackageManager pm = mContext.getPackageManager();
      return pm.getPackageInfo(packgeName, PackageManager.GET_SIGNATURES);
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }        /******* 通过返回的包信息获得签名数组 *******/
    return null;
  }
  /**
   * 获取apk对应的包名
   * @return
   */
  public static PackageInfo getApkPackgeInfo(Context mContext,String archiveFilePath) {
    PackageManager pm = mContext.getPackageManager();
    PackageInfo info = pm.getPackageArchiveInfo(archiveFilePath, PackageManager.GET_SIGNATURES);
    if (info != null) {
      return info;
    }
    return null;
  }

  /***
   * 进入activity的主界面
   * @param mContext
   * @param apkPackgeName
   */
  public static void launchPackgeMainActivity(Context mContext,String apkPackgeName){
    Intent remoteIntent = mContext.getPackageManager().getLaunchIntentForPackage(apkPackgeName);
    remoteIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    mContext.startActivity(remoteIntent);
  }

}
