package com.itfollowme.njcit01;

import org.junit.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by notre on 2018/3/27.
 */

public class TestRetrofit {
    public static final String API_URL = "https://api.github.com/";
    public static class Contributor {
        public final String login;
        public final int contributions;

        public Contributor(String login, int contributions) {
            this.login = login;
            this.contributions = contributions;
        }
    }

    public interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        Call<List<Contributor>> contributors(
                @Path("owner") String owner,
                @Path("repo") String repo);
    }

//    @Test
//    public void testConn()  {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(API_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        // Create an instance of our GitHub API interface.
//        GitHub github = retrofit.create(GitHub.class);
//
//        // Create a call instance for looking up Retrofit contributors.
//        Call<List<Contributor>> call = github.contributors("square", "retrofit");
//
//        // Fetch and print a list of the contributors to the library.
//        List<Contributor> contributors = null;
//        try {
//            contributors = call.execute().body();
//            for (Contributor contributor : contributors) {
//                System.out.println(contributor.login + " (" + contributor.contributions + ")");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }


    @Test
    public void testZhihu(){
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(ZhihuApi.URL).
                addConverterFactory(FastJsonConverterFactory.create()).
                build();

        ZhihuApi zhihuApi = retrofit.create(ZhihuApi.class);
        Call<DetailBean> call = zhihuApi.getZhihuDetail(9675159);
        call.enqueue(new Callback<DetailBean>() {
            @Override
            public void onResponse(Call<DetailBean> call, Response<DetailBean> response) {
                System.out.println("-------------"+response.body().getBody());
            }

            @Override
            public void onFailure(Call<DetailBean> call, Throwable t) {
                System.out.println("错误");
            }
        });

    }
}
