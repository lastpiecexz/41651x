package com.itfollowme.looklook.presenter.impl;

import android.content.Context;
import com.itfollowme.looklook.api.ApiManager;
import com.itfollowme.looklook.api.MeiziApis;
import com.itfollowme.looklook.bean.MeiziResult;
import com.itfollowme.looklook.presenter.IMeiziPresenter;
import com.itfollowme.looklook.view.IMeiziFragment;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by notre on 2018/5/23.
 */

public class MeiziPresenterImpl implements IMeiziPresenter {

  private Context context;
  private IMeiziFragment meiziFragment;

  //构造函数，负责将V层传递过来
  public MeiziPresenterImpl(Context context,
      IMeiziFragment meiziFragment) {
    this.context = context;
    this.meiziFragment = meiziFragment;
  }

  @Override
  public void getNewMeiziPhotoes() {
    MeiziApis meiziApis = ApiManager.getInstance().getMeiziApis();
    Flowable<MeiziResult> flowable = meiziApis.listMeizi(10,1);
    flowable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<MeiziResult>() {
          @Override
          public void accept(MeiziResult meiziResult) throws Exception {
            //调用图形界面接口更新数据
            meiziFragment.updatePhotos(meiziResult);
          }
        });
  }
}
