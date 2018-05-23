package com.itfollowme.looklook.presenter.impl;

import android.content.Context;
import com.itfollowme.looklook.api.ApiManager;
import com.itfollowme.looklook.api.ZhihuApis;
import com.itfollowme.looklook.bean.DailyListBean;
import com.itfollowme.looklook.presenter.IZhihuPresenter;
import com.itfollowme.looklook.view.IZhihuFragment;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class ZhihuPresenterImpl implements IZhihuPresenter {

  private IZhihuFragment mZhihuFragment;
  private Context context;

  public ZhihuPresenterImpl(Context context, IZhihuFragment zhihuFragment) {
    this.context = context;
    mZhihuFragment = zhihuFragment;
  }

  /**
   * 加载最新的知乎日报
   */
  @Override
  public void getLastZhihuNews() {
    //让View显示进度条
    mZhihuFragment.showProgressDialog();
    ZhihuApis zhihuApis = ApiManager.getInstance().getZhihuApis();
    Flowable<DailyListBean> flowable = zhihuApis.getLatestNews();
    flowable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<DailyListBean>() {
          @Override
          public void accept(DailyListBean dailyListBean) throws Exception {
            mZhihuFragment.updateList(dailyListBean);
            mZhihuFragment.hidProgressDialog();
          }
        });
  }

  @Override
  public void getTheDaily(String date) {

  }

  @Override
  public void getLastFromCache() {

  }
}
