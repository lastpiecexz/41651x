package com.itfollowme.zhihu.api;

import com.itfollowme.zhihu.ui.model.DailyListBean;
import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by notre on 2018/4/24.
 */

public interface ZhihuApis {
  String ZHIHU_BASE_URL = "https://news-at.zhihu.com/";

  @GET("api/4/news/latest")
  Flowable<DailyListBean> getLatestDailyListBean();

}
