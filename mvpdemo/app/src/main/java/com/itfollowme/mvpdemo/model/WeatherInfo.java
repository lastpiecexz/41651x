package com.itfollowme.mvpdemo.model;

import java.io.Serializable;

/**
 * Created by notre on 2018/4/11.
 * city: "武汉",
 * cityid: "101200101",
 * temp1: "7℃",
 * temp2: "19℃",
 * weather: "小雨转多云",
 * img1: "n7.gif",
 * img2: "d1.gif",
 * ptime: "18:00"
 */

public class WeatherInfo implements Serializable {
  private String city;
  private String cityId;
  private String temp1;
  private String temp2;
  private String weather;
  private String img1;
  private String img2;
  private String ptime;

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCityId() {
    return cityId;
  }

  public void setCityId(String cityId) {
    this.cityId = cityId;
  }

  public String getTemp1() {
    return temp1;
  }

  public void setTemp1(String temp1) {
    this.temp1 = temp1;
  }

  public String getTemp2() {
    return temp2;
  }

  public void setTemp2(String temp2) {
    this.temp2 = temp2;
  }

  public String getWeather() {
    return weather;
  }

  public void setWeather(String weather) {
    this.weather = weather;
  }

  public String getImg1() {
    return img1;
  }

  public void setImg1(String img1) {
    this.img1 = img1;
  }

  public String getImg2() {
    return img2;
  }

  public void setImg2(String img2) {
    this.img2 = img2;
  }

  public String getPtime() {
    return ptime;
  }

  public void setPtime(String ptime) {
    this.ptime = ptime;
  }

  @Override
  public String toString() {
    return "WeatherInfo{" +
        "city='" + city + '\'' +
        ", cityId='" + cityId + '\'' +
        ", temp1='" + temp1 + '\'' +
        ", temp2='" + temp2 + '\'' +
        ", weather='" + weather + '\'' +
        ", img1='" + img1 + '\'' +
        ", img2='" + img2 + '\'' +
        ", ptime='" + ptime + '\'' +
        '}';
  }
}
