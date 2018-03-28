package com.itfollowme.njcit01;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(ZhihuApi.URL).
                addConverterFactory(FastJsonConverterFactory.create()).
                build();

        ZhihuApi zhihuApi = retrofit.create(ZhihuApi.class);
        Call<DetailBean> call = zhihuApi.getZhihuDetail(9675159);
        call.enqueue(new Callback<DetailBean>() {
            @Override
            public void onResponse(Call<DetailBean> call, Response<DetailBean> response) {
               Log.i("retrofit","-------------"+response.isSuccessful()+response.toString());
               Log.i("retrofit",response.body().getBody());
            }

            @Override
            public void onFailure(Call<DetailBean> call, Throwable t) {
                Log.i("retrofit","错误");
            }
        });

    }
}
