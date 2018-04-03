package com.itfollowme.njcit01;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by notre on 2018/3/28.
 */

public interface ZhihuApi {

//    String URL = "https://news-at.zhihu.com/
    String URL = "http://172.18.36.68/";

    @GET("/api/4/news/{id}")
    Call<DetailBean> getZhihuDetail(@Path("id") Integer id);

    @FormUrlEncoded
    @POST("/zhihu/login")
    Call<String> login(@Field("username") String username,@Field("password") String password);
}
