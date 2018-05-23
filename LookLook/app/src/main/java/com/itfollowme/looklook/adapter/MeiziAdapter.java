package com.itfollowme.looklook.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.itfollowme.looklook.App;
import com.itfollowme.looklook.R;
import com.itfollowme.looklook.bean.MeiziResult.MeiziPhoto;
import com.itfollowme.looklook.util.GlideApp;
import java.util.List;


/**
 * Created by notre on 2018/5/15.
 */

public class MeiziAdapter extends RecyclerView.Adapter<MeiziViewHolder> {

  private List<MeiziPhoto> photos;
  private Context context;

  public MeiziAdapter(Context context,List<MeiziPhoto> photos) {
    this.context = context;
    this.photos = photos;
  }

  @NonNull
  @Override
  public MeiziViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_photo,parent,false);
    return new MeiziViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull MeiziViewHolder holder, int position) {
    String url = photos.get(position).getUrl();


    GlideApp.with(context).load(url).diskCacheStrategy(
        DiskCacheStrategy.ALL)
        .into(new SimpleTarget<Drawable>() {
          @Override
          public void onResourceReady(@NonNull Drawable resource,
              @Nullable Transition<? super Drawable> transition) {

            if(holder.getAdapterPosition() != RecyclerView.NO_POSITION) {

                int width = resource.getIntrinsicWidth();
                int height = resource.getIntrinsicHeight();
                int realHeight = (App.SCREEN_WIDTH / 2) * height / width;
                ViewGroup.LayoutParams lp = holder.ivGirl.getLayoutParams();
                lp.height = realHeight;
              }
              holder.ivGirl.setImageDrawable(resource);

          }
        });


  }

  @Override
  public int getItemCount() {
    return photos.size();
  }
}
