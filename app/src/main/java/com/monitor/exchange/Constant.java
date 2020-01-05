package com.monitor.exchange;

public interface Constant {
  // TODO 存储api链接的几个方式：string内新建一个xml
  String API_URL = "http://v-api.testcadae.top/mobile_api/v2/";
  String HOME_API = API_URL + "home/initialize";

  String LOG_TAG = "myLog";

  // 网络请求相关常量
  int CONNECTION_TIMEOUT = 5;
  int IO_TIMEOUT = 10;
}
