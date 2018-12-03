package com.hbxunluo.installapk.log;


import com.hbxunluo.installapk.AppConfig;

public class MyLogUitls {
//    Android.util.Log常用的方法有以下5个：Log.v() ,Log.d() ,Log.i() ,Log.w() ,Log.e() 。按照日志级别从高到低为ERROR, WARN, INFO, DEBUG, VERBOSE.至于日志级别本身的含义.
//
//    1.下面是对各种日志级别的输出介绍:
//
//     1、Log.v 的输出颜色为黑色的，输出大于或等于VERBOSE日志级别的信息
//
//    　2、Log.d的输出颜色是蓝色的，输出大于或等于DEBUG日志级别的信息
//
//    　3、Log.i的输出为绿色，输出大于或等于INFO日志级别的信息
//
//    　4、Log.w的输出为橙色, 输出大于或等于WARN日志级别的信息
//
//    　5、Log.e的输出为红色，仅输出ERROR日志级别的信息.

    private final static ILogAdapter logAdaper;

    static {
        if (AppConfig.isDebug) {
            logAdaper = new OutLogAdapter();
        } else {
          logAdaper = new EmptyLogAdapter();
        }
    }

    /***
     * 打印一个可对象，优先判定是否是json，如果是json，打印成json格式
     *
     * @param tag
     * @param o
     */
    public static void print(String tag, Object o) {
        logAdaper.print(tag, o);
    }

    /***
     * 缺省tag 打印日志
     *
     * @param o
     */
    public static void print(Object o) {
        logAdaper.print(o);
    }


    /***
     * 打印字符串
     *
     * @param tag
     */
    public static void printString(String tag, String string) {
        logAdaper.printString(tag, string);
    }

}
