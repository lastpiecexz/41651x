package com.itfollowme.zhihu.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.itfollowme.zhihu.R;
import com.itfollowme.zhihu.api.ZhihuApis;
import com.itfollowme.zhihu.ui.activity.DetailActivity;
import com.itfollowme.zhihu.ui.adapter.DailyAdapter;
import com.itfollowme.zhihu.ui.model.DailyListBean;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.io.InputStream;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyFragment extends Fragment implements DailyAdapter.OnItemClickListener {

  private RecyclerView mRecyclerView;

  private DailyListBean dailyListBean = new DailyListBean();

  DailyAdapter dailyAdapter;

  public DailyFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    //parseJSON();
    loadData();


    View view = inflater.inflate(R.layout.fragment_daily, container, false);
    mRecyclerView = view.findViewById(R.id.rv_daily_items);

    mRecyclerView.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

    return view;
  }


  public void loadData() {
    Retrofit retrofit = new Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(FastJsonConverterFactory.create())
        .baseUrl(ZhihuApis.ZHIHU_BASE_URL)
        .build();

    ZhihuApis zhihuApis = retrofit.create(ZhihuApis.class);
    Flowable<DailyListBean> flowable = zhihuApis.getLatestDailyListBean();
    flowable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<DailyListBean>() {
          @Override
          public void accept(DailyListBean dailyListBean) throws Exception {
            dailyAdapter = new DailyAdapter(dailyListBean, getContext());

            dailyAdapter.setItemClickListener(DailyFragment.this);
            mRecyclerView.setAdapter(dailyAdapter);
            dailyAdapter.notifyDataSetChanged();
          }
        });

  }


  public void parseJSON() {
    try {
      InputStream inputStream = getContext().getAssets().open("news.json");
      int size = inputStream.available();
      byte[] buf = new byte[size];
      inputStream.read(buf);
      String text = new String(buf, "UTF-8");
      dailyListBean = JSON.parseObject(text, DailyListBean.class);
/*            Log.i("topStorySize",dailyListBean.getTopStories().size()+"");
            for(DailyListBean.TopStoryBean topStoryBean : dailyListBean.getTopStories()){
                Log.i("",topStoryBean.getTitle()+" "+topStoryBean.getImage());
            }*/

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onItemClick(int position) {
    Intent intent = new Intent(getContext(), DetailActivity.class);
    DailyListBean.StoryBean storyBean = dailyListBean.getStories().get(position);
    intent.putExtra("id", storyBean.getId());
    startActivity(intent);
  }
}
