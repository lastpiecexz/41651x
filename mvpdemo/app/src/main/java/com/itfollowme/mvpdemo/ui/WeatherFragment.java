package com.itfollowme.mvpdemo.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.itfollowme.mvpdemo.R;
import com.itfollowme.mvpdemo.model.Weather;
import com.itfollowme.mvpdemo.ui.WeatherContract.WeatherPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment implements WeatherContract.WeatherView {

  private WeatherPresenter weatherPresenter;

  private EditText mEtCityId;

  private TextView mTvWeather;

  public WeatherFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_weather, container, false);
    mEtCityId = view.findViewById(R.id.et_cityId);
    mTvWeather = view.findViewById(R.id.tv_weather);
    return view;
  }


  @Override
  public void setPresenter(WeatherPresenter presenter) {
    this.weatherPresenter = presenter;
  }

  @Override
  public void searchWeather() {
    String cityId = mEtCityId.getText().toString();
    weatherPresenter.loadWeather(cityId);
  }

  @Override
  public void showWeather(Weather weather) {
    mTvWeather.setText(weather.getWeatherInfo().toString());
  }
}
