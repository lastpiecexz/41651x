package com.itfollowme.zhihu.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.itfollowme.zhihu.R;
import com.itfollowme.zhihu.ui.adapter.DailyAdapter;
import com.itfollowme.zhihu.ui.model.DailyListBean;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyFragment extends Fragment {

    private RecyclerView mRecyclerView;

    private List<String> items = new ArrayList<>();

    DailyAdapter dailyAdapter;

    public DailyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        parseJSON();

   // Inflate the layout for this fragment
        for(int i=0;i<100;i++){
            items.add("新闻"+i);
        }

        dailyAdapter = new DailyAdapter(items,getContext());
        View view = inflater.inflate(R.layout.fragment_daily, container, false);
        mRecyclerView = view.findViewById(R.id.rv_daily_items);
        mRecyclerView.setAdapter(dailyAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        return view;
    }

    public void parseJSON(){
        try {
            InputStream inputStream = getContext().getAssets().open("news.json");
            int size = inputStream.available();
            byte[] buf = new byte[size];
            inputStream.read(buf);
            String text = new String(buf,"UTF-8");
            DailyListBean dailyListBean = JSON.parseObject(text, DailyListBean.class);

            List<DailyListBean.StoryBean> stories = dailyListBean.getStories();
            for(int i=0;i<stories.size();i++){
                Log.i("title",stories.get(i).getTitle());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
