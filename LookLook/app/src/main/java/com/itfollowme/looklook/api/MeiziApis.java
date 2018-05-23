package com.itfollowme.looklook.api;

import com.itfollowme.looklook.bean.MeiziResult;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by notre on 2018/5/23.
 */

public interface MeiziApis {

  @GET("/api/data/福利/{pageSize}/{page}")
  Flowable<MeiziResult> listMeizi(@Path("pageSize") int pageSize,@Path("page") int page);

}
