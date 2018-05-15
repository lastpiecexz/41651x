package com.itfollowme.meizi.api;

import com.itfollowme.meizi.model.MeiziResult;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by notre on 2018/5/15.
 */

public interface MeiziApis {
  //http://gank.io/api/data/福利/10/1
  String MEIZI_URL = "http://gank.io/";

  @GET("/api/data/福利/{pageSize}/{page}")
  Flowable<MeiziResult> listMeizi(@Path("pageSize") int pageSize,@Path("page") int page);
}
