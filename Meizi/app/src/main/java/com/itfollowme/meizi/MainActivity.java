package com.itfollowme.meizi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import com.itfollowme.meizi.adapter.MeiziAdapter;
import com.itfollowme.meizi.api.MeiziApis;
import com.itfollowme.meizi.model.MeiziResult;
import com.itfollowme.meizi.model.MeiziResult.MeiziPhoto;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

public class MainActivity extends AppCompatActivity {

  private RecyclerView recyclerView;
  private MeiziAdapter meiziAdapter;
  private LayoutManager mLayoutManager;
  private List<MeiziPhoto> list;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    recyclerView = findViewById(R.id.rv_photos);

    list = new ArrayList<>();
    int spanCount = 2;
    mLayoutManager = new StaggeredGridLayoutManager(
        spanCount,
        StaggeredGridLayoutManager.VERTICAL);
    meiziAdapter = new MeiziAdapter(MainActivity.this, list);
    mLayoutManager.setItemPrefetchEnabled(false);
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.setAdapter(meiziAdapter);

    //测试下是否能访问下载成功
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(MeiziApis.MEIZI_URL)
        .addConverterFactory(FastJsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
    MeiziApis meiziApis = retrofit.create(MeiziApis.class);
    Flowable<MeiziResult> flowable = meiziApis.listMeizi(10,1);
    flowable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<MeiziResult>() {
          @Override
          public void accept(MeiziResult meiziResult) throws Exception {
            List<MeiziPhoto> results = meiziResult.getResults();
            for(MeiziPhoto m : results) {
              System.out.println(m.getUrl());
              list.add(m);
            }
            meiziAdapter.notifyDataSetChanged();
          }
        });


  }
}
