package com.itfollowme.mvpdemo.ui;

import android.util.Log;
import com.itfollowme.mvpdemo.api.WeatherApi;
import com.itfollowme.mvpdemo.model.Weather;
import com.itfollowme.mvpdemo.ui.WeatherContract.WeatherPresenter;
import com.itfollowme.mvpdemo.ui.WeatherContract.WeatherView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by notre on 2018/4/11.
 */

public class WeatherPresenterImpl implements WeatherPresenter{

  private WeatherView weatherView;
  public WeatherPresenterImpl(WeatherView weatherView) {
    this.weatherView = weatherView;
  }

  @Override
  public void loadWeather(String cityId) {

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(WeatherApi.WEATHER_URL)
        .addConverterFactory(FastJsonConverterFactory.create())
        .build();
    WeatherApi weatherApi = retrofit.create(WeatherApi.class);
    Call<Weather> call = weatherApi.getWeather("101200101");
    call.enqueue(new Callback<Weather>() {
      @Override
      public void onResponse(Call<Weather> call, Response<Weather> response) {
        Log.i("weather",response.body().getWeatherInfo().toString());
        Weather weather = response.body();
        weatherView.showWeather(weather);
      }

      @Override
      public void onFailure(Call<Weather> call, Throwable t) {
        //weatherView.showWeather(weather);
      }
    });
  }
}
