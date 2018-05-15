package com.itfollowme.meizi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.itfollowme.meizi.R;
import com.itfollowme.meizi.model.MeiziResult.MeiziPhoto;
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
    View view = holder.itemView;
    ImageView imageView = (ImageView) view.findViewById(R.id.iv_photo);

    //手动更改高度，不同位置的高度有所不同
    //imageView.setMaxHeight(100 + (position % 3) * 30);
    Glide.with(context).load(url).into(imageView);
  }

  @Override
  public int getItemCount() {
    return photos.size();
  }
}
