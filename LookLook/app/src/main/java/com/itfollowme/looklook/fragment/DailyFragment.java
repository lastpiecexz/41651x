package com.itfollowme.looklook.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.itfollowme.looklook.R;
import com.itfollowme.looklook.adapter.DailyAdapter;
import com.itfollowme.looklook.bean.DailyListBean;
import com.itfollowme.looklook.presenter.IZhihuPresenter;
import com.itfollowme.looklook.view.IZhihuFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class DailyFragment extends Fragment implements IZhihuFragment,DailyAdapter.OnItemClickListener {

  private IZhihuPresenter zhihuPresenter;

  public void setZhihuPresenter(IZhihuPresenter zhihuPresenter) {
    this.zhihuPresenter = zhihuPresenter;
  }

  private RecyclerView mRecyclerView;

  private DailyListBean dailyListBean = new DailyListBean();

  private DailyAdapter dailyAdapter;

  public DailyFragment() {

  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    zhihuPresenter.getLastZhihuNews();
    View view = inflater.inflate(R.layout.fragment_daily, container, false);
    mRecyclerView = view.findViewById(R.id.rv_daily_items);

    mRecyclerView.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

    return view;
  }






  @Override
  public void onItemClick(int position) {
  /*  Intent intent = new Intent(getContext(), DetailActivity.class);
    DailyListBean.StoryBean storyBean = dailyListBean.getStories().get(position);
    intent.putExtra("id", storyBean.getId());
    startActivity(intent);*/
  }

  @Override
  public void showProgressDialog() {
    Toast.makeText(getContext(),"正在加载",Toast.LENGTH_LONG).show();
  }

  @Override
  public void hidProgressDialog() {
    Toast.makeText(getContext(),"加载完毕",Toast.LENGTH_LONG).show();
  }

  @Override
  public void showError(String error) {

  }

  @Override
  public void updateList(DailyListBean dailyListBean) {
    dailyAdapter = new DailyAdapter(getContext(),dailyListBean);
    mRecyclerView.setAdapter(dailyAdapter);
  }
}
