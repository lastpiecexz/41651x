package com.itfollowme.gliderdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
public class MainActivity extends AppCompatActivity {

  private ImageView mIvMeinv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mIvMeinv = findViewById(R.id.iv_meinv);
    String bigImgUrl = "https://desk-fd.zol-img.com.cn/t_s2880x1800c5/g5/M00/0E/01/ChMkJlnJ5u6ICodHAMbEg1lM-tEAAgzIgK3oP4AxsSb066.jpg";
    String thumbUrl = "http://7xi8d6.com1.z0.glb.clouddn.com/20180129074038_O3ydq4_Screenshot.jpeg";
    //String url = "http://storage.slide.news.sina.com.cn/slidenews/77_ori/2018_19/74766_822277_623320.gif";
    GlideApp.with(this)
        .load(bigImgUrl)
        .thumbnail(0.1f)
        .into(mIvMeinv);
  }
}
