package com.itfollowme.zhihu.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.itfollowme.zhihu.R;
import com.itfollowme.zhihu.ui.model.DailyListBean;
import com.itfollowme.zhihu.util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by notre on 2018/1/15.
 */

public class DailyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    DailyListBean dailyListBean;

    Context context;

    private OnItemClickListener mItemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public DailyAdapter(DailyListBean dailyListBean,Context context) {
        this.dailyListBean = dailyListBean;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == 0){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily_top,parent,false);
            Banner banner = view.findViewById(R.id.banner);
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            banner.setImageLoader(new GlideImageLoader());
            List<String> titles = new ArrayList<>();
            List<String> images = new ArrayList<>();
           for(int i=0;i<5;i++){
                titles.add(dailyListBean.getTopStories().get(i).getTitle());
                images.add(dailyListBean.getTopStories().get(i).getImage());
            }
            banner.setImages(images);
            banner.setBannerTitles(titles);
            banner.start();
            //view.setOnClickListener(this);
            return new TopDailyViewHolder(view);
        }else {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily, parent, false);
            DailyViewHolder dailyViewHolder = new DailyViewHolder(view);
            view.setOnClickListener(this);
            return dailyViewHolder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  DailyViewHolder) {
            DailyViewHolder dvHolder = (DailyViewHolder) holder;
            dvHolder.mTvTitle.setText(dailyListBean.getStories().get(position).getTitle());
            Glide.with(context).load(dailyListBean.getStories().get(position).getImages().get(0)).into(dvHolder.mIvImage);
            dvHolder.mIvImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            dvHolder.itemView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        return dailyListBean.getStories().size();
    }



    public void onClick(View v) {
        if (mItemClickListener!=null){
            mItemClickListener.onItemClick((Integer) v.getTag());
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
