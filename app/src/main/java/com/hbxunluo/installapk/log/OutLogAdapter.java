package com.hbxunluo.installapk.log;

import android.util.Log;

/**
 * Created by Administrator on 2018/1/25.
 */

public class OutLogAdapter implements ILogAdapter {
    @Override
    public void print(String tag, Object o) {
        if (o == null) {
          Log.e(tag,"NULL");
        }
        else {
          if(o instanceof String){
            Log.e(tag,o+"");
          }
          else {
            Log.e(tag,o+"");
          }
        }
    }

    @Override
    public void print(Object o) {
        print("lgdx", o);
    }

    @Override
    public void printString(String tag, String string) {
        Log.e(tag, string);
    }
}
