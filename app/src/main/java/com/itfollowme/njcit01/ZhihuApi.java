package com.itfollowme.njcit01;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by notre on 2018/3/28.
 */

public interface ZhihuApi {

    String URL = "https://news-at.zhihu.com/";
    @GET("/api/4/news/{id}")
    Call<DetailBean> getZhihuDetail(@Path("id") Integer id);

}
