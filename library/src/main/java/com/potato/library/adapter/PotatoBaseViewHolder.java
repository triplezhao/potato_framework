package com.potato.library.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class PotatoBaseViewHolder extends RecyclerView.ViewHolder{
    public View view;

    public PotatoBaseViewHolder(View itemView) {
        super(itemView);
        this.view = itemView;
        view.setTag(this);
    }

}