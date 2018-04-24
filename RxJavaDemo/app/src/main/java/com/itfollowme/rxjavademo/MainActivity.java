package com.itfollowme.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.itfollowme.rxjavademo.api.WeatherApi;
import com.itfollowme.rxjavademo.model.Weather;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

public class MainActivity extends AppCompatActivity {

  private EditText mEtCityId;
  private TextView mTvResult;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mEtCityId = findViewById(R.id.et_cityId);
    mTvResult = findViewById(R.id.tv_result);
  }

  public void search(View view) {
    Retrofit retrofit = new Retrofit.Builder().
        baseUrl(WeatherApi.WEATHER_URL).
        addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
        addConverterFactory(FastJsonConverterFactory.create()).
        build();
    WeatherApi weatherApi = retrofit.create(WeatherApi.class);
    String cityId = mEtCityId.getText().toString();
    Observable<Weather> observable = weatherApi.getWeather(cityId);
    observable
        .map(weather-> {
            return weather.getWeatherInfo().toString();
          }
        )
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(s -> {
            mTvResult.setText(s);
        });
  }
}
