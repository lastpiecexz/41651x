package com.itfollowme.looklook.activity;



import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.itfollowme.looklook.R;
import com.itfollowme.looklook.fragment.MeiziFragment;
import com.itfollowme.looklook.presenter.IMeiziPresenter;
import com.itfollowme.looklook.presenter.impl.MeiziPresenterImpl;

public class MeiziActivity extends AppCompatActivity {

  private MeiziFragment meiziFragment;

  private IMeiziPresenter meiziPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_meizi);
    meiziFragment = new MeiziFragment();
    meiziPresenter = new MeiziPresenterImpl(this,meiziFragment);
    meiziFragment.setMeiziPresenter(meiziPresenter);

    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();
    ft.replace(R.id.ll_meizi,meiziFragment);
    ft.commit();

  }
}
