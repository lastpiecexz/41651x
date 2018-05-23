package com.itfollowme.looklook.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.itfollowme.looklook.R;
import com.itfollowme.looklook.adapter.MeiziAdapter;
import com.itfollowme.looklook.bean.MeiziResult;
import com.itfollowme.looklook.presenter.IMeiziPresenter;
import com.itfollowme.looklook.view.IMeiziFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeiziFragment extends Fragment implements IMeiziFragment{

  private RecyclerView recyclerView;

  private StaggeredGridLayoutManager mLayoutManager;

  private MeiziAdapter meiziAdapter;

  private IMeiziPresenter meiziPresenter;
  public MeiziFragment() {
    // Required empty public constructor
  }

  public void setMeiziPresenter(IMeiziPresenter meiziPresenter) {
    this.meiziPresenter = meiziPresenter;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    //调用presenter去获取数据
    meiziPresenter.getNewMeiziPhotoes();
    View view = inflater.inflate(R.layout.fragment_meizi, container, false);
    recyclerView = view.findViewById(R.id.rv_photos);
    recyclerView.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

    int spanCount = 2;
    mLayoutManager = new StaggeredGridLayoutManager(
        spanCount,
        StaggeredGridLayoutManager.VERTICAL);
    mLayoutManager.setItemPrefetchEnabled(false);
    recyclerView.setLayoutManager(mLayoutManager);

    return view;
  }

  @Override
  public void showProgressDialog() {

  }

  @Override
  public void hidProgressDialog() {

  }

  @Override
  public void showError(String error) {

  }

  @Override
  public void updatePhotos(MeiziResult meiziResult) {
    //先加载进度条
    //加载数据
    meiziAdapter = new MeiziAdapter(getContext(),meiziResult.getResults());
    recyclerView.setAdapter(meiziAdapter);
    //关闭进度条
  }
}
