/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.hbxunluo.installapk.utils;


public class StatusBarManager {
  public static final int DISABLE_EXPAND  = 0x00010000;
  public static final int DISABLE_NOTIFICATION_ICONS = 0x00020000;
  public static final int DISABLE_NOTIFICATION_ALERTS =  0x00040000;
  public static final int DISABLE_NOTIFICATION_TICKER = 0x00080000;
  public static final int DISABLE_SYSTEM_INFO =0x00100000;
  public static final int DISABLE_HOME = 0x00200000;
  public static final int DISABLE_RECENT = 0x01000000;
  public static final int DISABLE_BACK = 0x00400000;
  public static final int DISABLE_CLOCK = 0x00800000;
  public static final int DISABLE_SEARCH = 0x02000000;


  public static final int DISABLE_NAVIGATION = DISABLE_HOME|DISABLE_RECENT|StatusBarManager.DISABLE_BACK;



  public static final int DISABLE_NAVIGATION2 = StatusBarManager.DISABLE_EXPAND

    | StatusBarManager.DISABLE_NOTIFICATION_ICONS

    | StatusBarManager.DISABLE_NOTIFICATION_ALERTS

    | StatusBarManager.DISABLE_NOTIFICATION_TICKER

    | StatusBarManager.DISABLE_SYSTEM_INFO

    | StatusBarManager.DISABLE_NAVIGATION

    | StatusBarManager.DISABLE_CLOCK;

  public static final int DISABLE_NONE = 0x00000000;

}
