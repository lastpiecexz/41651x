package com.itfollowme.mvpdemo.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.itfollowme.mvpdemo.R;
import com.itfollowme.mvpdemo.ui.WeatherContract.WeatherPresenter;

public class MainActivity extends AppCompatActivity {

  private WeatherFragment weatherFragment;
  private WeatherPresenter weatherPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    weatherFragment = new WeatherFragment();
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.add(R.id.fl_content,weatherFragment);
    ft.commit();

    weatherPresenter = new WeatherPresenterImpl(weatherFragment);
    weatherFragment.setPresenter(weatherPresenter);

  }

  public void search(View view) {
    weatherFragment.searchWeather();
  }
}
