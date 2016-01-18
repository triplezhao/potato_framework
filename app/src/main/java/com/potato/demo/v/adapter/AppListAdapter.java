package com.potato.demo.v.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mozillaonline.downloadprovider.DownLoadUtil;
import com.mozillaonline.providers.downloads.ui.DownloadListActivity;
import com.potato.chips.util.ImageLoaderUtil;
import com.potato.demo.R;
import com.potato.demo.m.bean.AppBean;
import com.potato.demo.databinding.ItemAppListBinding;
import com.potato.library.adapter.PotatoBaseRecyclerViewAdapter;
import com.potato.library.util.L;

/**
 * Created by ztw on 2015/9/21.
 */
public class AppListAdapter extends PotatoBaseRecyclerViewAdapter {

    public AppListAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemAppListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_app_list,
                parent,
                false);
        VH holder = new VH(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ItemAppListBinding binding = (ItemAppListBinding) ((VH) holder).getBinding();
        final AppBean bean = (AppBean) mData.get(position);
        binding.setBean(bean);
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, DownloadListActivity.class);
                mContext.startActivity(intent);
            }
        });
        binding.tvDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                DownLoadUtil.startDownload(context, bean.getApkUrl(), bean.getTitle());
            }
        });
        L.i("with", bean.getIcon());

        ImageLoaderUtil.displayImage(bean.getIcon(), binding.ivPic, R.drawable.def_gray_small);
    }


    public static class VH extends ViewHolder {

        private ViewDataBinding binding;

        public VH(View itemView) {
            super(itemView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }
    }
}
