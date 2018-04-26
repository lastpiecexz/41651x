package com.itfollowme.baidufanyidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("MD5");
      byte[] inputArray = sign.getBytes("UTF-8");
      messageDigest.update(inputArray);
      // 转换并返回结果，也是字节数组，包含16个元素
      byte[] resultByteArray = messageDigest.digest();
      // 字符数组转换成字符串返回
      sign =  byteArrayToHex(resultByteArray);

    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

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
        .subscribe(new Consumer<Translate>() {
          @Override
          public void accept(Translate translate) throws Exception {
            mTvTarget.setText(translate.toString());
          }
        });

  }

  private static String byteArrayToHex(byte[] byteArray) {
    // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
    char[] resultCharArray = new char[byteArray.length * 2];
    // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
    int index = 0;
    for (byte b : byteArray) {
      resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
      resultCharArray[index++] = hexDigits[b & 0xf];
    }

    // 字符数组组合成字符串返回
    return new String(resultCharArray);

  }
  private static final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
      'e', 'f' };
}
