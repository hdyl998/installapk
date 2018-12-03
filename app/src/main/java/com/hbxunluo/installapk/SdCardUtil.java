//package com.hbxunluo.installapk;
//
///**
// * Note：None
// * Created by Liuguodong on 2018/11/8 12:48
// * E-Mail Address：986850427@qq.com
// */
//
//import android.os.Environment;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Travis1022 on 2017/8/8.
// */
//
//public class SdCardUtil {
//  /**
//   * 获取内置SD卡路径
//   *
//   * @return
//   */
//  public static String getInnerSDCardPath() {
//    return Environment.getExternalStorageDirectory().getPath();
//  }
//
//  /**
//   * 获取存储路径
//   * @return 所有可用于存储的不同的卡的位置，用一个List来保存
//   */
//  public static List<String> getExtSDCardPathList() {
//    List<String> paths = new ArrayList<>();
//    String extFileStatus = Environment.getExternalStorageState();
//    File extFile = Environment.getExternalStorageDirectory();
//    //首先判断一下外置SD卡的状态，处于挂载状态才能获取的到
//    if (extFileStatus.equals(Environment.MEDIA_MOUNTED) && extFile.exists() && extFile.isDirectory() && extFile.canWrite()) {
//      //外置SD卡的路径
//      paths.add(extFile.getAbsolutePath());
//    }
//    try {
//      Runtime runtime = Runtime.getRuntime();
//      Process process = runtime.exec("mount");
//      InputStream is = process.getInputStream();
//      InputStreamReader isr = new InputStreamReader(is);
//      BufferedReader br = new BufferedReader(isr);
//      String line = null;
//      int mountPathIndex = 1;
//      while ((line = br.readLine()) != null) {
//        // format of sdcard file system: vfat/fuse
//        if ((!line.contains("fat") && !line.contains("fuse") && !line
//          .contains("storage"))
//          || line.contains("secure")
//          || line.contains("asec")
//          || line.contains("firmware")
//          || line.contains("shell")
//          || line.contains("obb")
//          || line.contains("legacy") || line.contains("data")) {
//          continue;
//        }
//        String[] parts = line.split(" ");
//        int length = parts.length;
//        if (mountPathIndex >= length) {
//          continue;
//        }
//        String mountPath = parts[mountPathIndex];
//        if (!mountPath.contains("/") || mountPath.contains("data")
//          || mountPath.contains("Data")) {
//          continue;
//        }
//        File mountRoot = new File(mountPath);
//        if (!mountRoot.exists() || !mountRoot.isDirectory()
//          || !mountRoot.canWrite()) {
//          continue;
//        }
//        boolean equalsToPrimarySD = mountPath.equals(extFile.getAbsolutePath());
//        if (equalsToPrimarySD) {
//          continue;
//        }
//        //扩展存储卡即TF卡或者SD卡路径
//        paths.add(mountPath);
//      }
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    return paths;
//  }
//
//}
