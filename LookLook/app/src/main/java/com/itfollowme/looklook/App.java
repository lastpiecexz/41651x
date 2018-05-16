package com.itfollowme.looklook;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.itfollowme.looklook.activity.BaseActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by notre on 2018/5/16.
 */

public class App extends Application {



  //创建一个App对象
  private static App instance ;
  public static final App getInstance(){
    return instance;
  }

  private static int mainTid;
  @Override
  public void onCreate() {
    super.onCreate();
    instance = this;
    //初始化装载所有Activities的集合
    activities = new ArrayList<>();
    //初始化手机的屏幕数据
    getScreenSize();
    //设置本app的线程id
    mainTid = android.os.Process.myTid();
  }

  private List<BaseActivity> activities;
  //管理所有的Activities

  /**
   * 添加Activity
   * @param activity
   */
  public void addActivity(BaseActivity activity){
    activities.add(activity);
  }

  /**
   *  移除Activity
   * @param activity
   */
  public void removeActivity(BaseActivity activity) {
    activities.remove(activity);
  }




  //获取手机的基础信息，屏幕宽度，高度。。。
  public static int SCREEN_WIDTH = -1;
  public static int SCREEN_HEIGHT = -1;
  public static float DIMEN_RATE = -1.0F;
  public static int DIMEN_DPI = -1;

  public void getScreenSize() {
    WindowManager windowManager = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
    DisplayMetrics dm = new DisplayMetrics();
    Display display = windowManager.getDefaultDisplay();
    display.getMetrics(dm);
    DIMEN_RATE = dm.density / 1.0F;
    DIMEN_DPI = dm.densityDpi;
    SCREEN_WIDTH = dm.widthPixels;
    SCREEN_HEIGHT = dm.heightPixels;
    if(SCREEN_WIDTH > SCREEN_HEIGHT) {
      int t = SCREEN_HEIGHT;
      SCREEN_HEIGHT = SCREEN_WIDTH;
      SCREEN_WIDTH = t;
    }
  }

  //初始化数据库的功能

  //夜间模式的基础设置
  static {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
  }

  //退出App
  /**
   * 结束当前所有Activity
   */
  public void clearActivities() {
    ListIterator<BaseActivity> iterator = activities.listIterator();
    BaseActivity activity;
    while (iterator.hasNext()) {
      activity = iterator.next();
      if (activity != null) {
        activity.finish();
      }
    }
  }

  /**
   * 退出应运程序
   */
  public void quiteApplication() {
    clearActivities();
    System.exit(0);
  }
}
