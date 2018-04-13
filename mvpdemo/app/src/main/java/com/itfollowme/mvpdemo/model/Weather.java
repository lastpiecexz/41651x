package com.itfollowme.mvpdemo.model;

import java.io.Serializable;

/**
 * Created by notre on 2018/4/11.
 */

public class Weather implements Serializable {
  private WeatherInfo weatherInfo;

  public WeatherInfo getWeatherInfo() {
    return weatherInfo;
  }

  public void setWeatherInfo(WeatherInfo weatherInfo) {
    this.weatherInfo = weatherInfo;
  }
}
