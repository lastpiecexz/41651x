package com.itfollowme.mvpdemo.api;


import com.itfollowme.mvpdemo.model.Weather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by notre on 2018/4/11.
 */

public interface WeatherApi {
  String WEATHER_URL = "http://www.weather.com.cn/";
  @GET("/data/cityinfo/{cityId}.html")
  Call<Weather> getWeather(@Path("cityId") String cityId);
}
