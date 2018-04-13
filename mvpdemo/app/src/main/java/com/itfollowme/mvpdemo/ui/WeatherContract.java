package com.itfollowme.mvpdemo.ui;

import com.itfollowme.mvpdemo.model.Weather;

/**
 * Created by notre on 2018/4/11.
 */

public interface WeatherContract {
  public interface WeatherView {
    void setPresenter(WeatherPresenter presenter);
    void searchWeather();
    void showWeather(Weather weather);
  }

  public interface WeatherPresenter {
    void loadWeather(String cityId);
  }
}
