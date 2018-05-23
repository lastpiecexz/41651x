package com.itfollowme.looklook.api;

import com.itfollowme.looklook.util.WebUtils;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by notre on 2018/5/22.
 */

public class ApiManager {
  //使用单态设计模式去管理ApiManager
  private ApiManager(){}
  private static ApiManager instance = null;
  static {
    if (instance == null) {
      synchronized (ApiManager.class) {
        if (instance == null) {
          instance = new ApiManager();
        }
      }
    }
  }

  public static final ApiManager getInstance(){
    return instance;
  }
  private ZhihuApis zhihuApis;
  private Object lock = new Object();

  public ZhihuApis getZhihuApis(){
    if(zhihuApis == null) {
      synchronized (lock) {
        if(zhihuApis == null) {
          Retrofit retrofit = new Retrofit.Builder()
              .baseUrl(WebUtils.ZHIHU_BASE_URL)
              .addConverterFactory(FastJsonConverterFactory.create())
              .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
              .build();
          zhihuApis = retrofit.create(ZhihuApis.class);
        }
      }
    }
    return zhihuApis;
  }

  private MeiziApis meiziApis;

  public MeiziApis getMeiziApis(){
    if (meiziApis == null ) {
      synchronized (lock) {
        if (meiziApis == null) {
          Retrofit retrofit = new Retrofit.Builder()
              .baseUrl(WebUtils.MEIZI_URL)
              .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
              .addConverterFactory(FastJsonConverterFactory.create())
              .build();
          meiziApis = retrofit.create(MeiziApis.class);
        }
      }
    }
    return meiziApis;
  }
}
