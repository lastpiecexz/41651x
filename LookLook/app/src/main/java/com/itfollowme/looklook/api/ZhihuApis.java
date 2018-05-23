package com.itfollowme.looklook.api;

import com.itfollowme.looklook.bean.DailyListBean;
import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by notre on 2018/5/22.
 */

public interface ZhihuApis {

  @GET("api/4/news/latest")
  Flowable<DailyListBean> getLatestNews();
}
