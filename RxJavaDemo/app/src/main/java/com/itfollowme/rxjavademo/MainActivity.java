package com.itfollowme.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);
    //创建一个上游 Observable：
    Observable.create(new ObservableOnSubscribe<Integer>() {
      @Override
      public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

        Log.i("RxJavaDemo1",Thread.currentThread().getName());

        emitter.onNext(1);

        emitter.onComplete();
      }
    }).subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Integer>() {
      @Override
      public void onSubscribe(Disposable d) {
        Log.i("RxJavaDemo1", "OnSubscribe");
      }

      @Override
      public void onNext(Integer value) {
        Log.i("RxJavaDemo1", Thread.currentThread().getName()+": " + value);
      }

      @Override
      public void onError(Throwable e) {
        Log.i("RxJavaDemo1", "error");
      }

      @Override
      public void onComplete() {
        Log.i("RxJavaDemo1", "complete");
      }
    });



  }
}
