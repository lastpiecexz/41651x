package com.itfollowme.baidufanyidemo;

import io.reactivex.Flowable;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by notre on 2018/4/25.
 */

public interface BaiduFanyiApi {
  String URL = "http://api.fanyi.baidu.com/";

  @GET("api/trans/vip/translate")
  Flowable<Translate> fanyi(@QueryMap Map<String,String> params);
}
