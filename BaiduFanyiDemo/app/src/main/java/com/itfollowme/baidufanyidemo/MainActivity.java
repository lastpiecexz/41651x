package com.itfollowme.baidufanyidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

public class MainActivity extends AppCompatActivity {

  private EditText mEtSrc;
  private TextView mTvTarget;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mEtSrc = findViewById(R.id.et_src);
    mTvTarget = findViewById(R.id.tv_target);

  }

  public void transalate(View view) {
    String q = mEtSrc.getText().toString();
    String from  = "en";
    String to = "zh";
    String appid = "替换成你的";
    String salt = String.valueOf(System.currentTimeMillis());
    String securityKey = "替换成你的";
    String sign = appid+q+salt+securityKey;

    sign = Utils.MD5(sign);

    Map<String, String> params = new HashMap<>();
    params.put("q",q);
    params.put("from",from);
    params.put("to",to);
    params.put("appid",appid);
    params.put("salt",salt);
    params.put("sign",sign);

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BaiduFanyiApi.URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(FastJsonConverterFactory.create())
        .build();

    BaiduFanyiApi fanyiApi = retrofit.create(BaiduFanyiApi.class);
    Flowable<Translate> flowable = fanyiApi.fanyi(params);
    flowable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(translate ->{
            mTvTarget.setText(translate.toString());
        });

  }


}
