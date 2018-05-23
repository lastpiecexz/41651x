package com.itfollowme.looklook.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.itfollowme.looklook.R;

/**
 * Created by notre on 2018/5/15.
 */

public class MeiziViewHolder extends RecyclerView.ViewHolder{
  public ImageView ivGirl;
  public MeiziViewHolder(@NonNull View itemView) {
    super(itemView);
    ivGirl = itemView.findViewById(R.id.iv_photo);
  }

}
