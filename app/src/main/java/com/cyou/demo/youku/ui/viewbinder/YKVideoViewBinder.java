package com.cyou.demo.youku.ui.viewbinder;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cyou.demo.R;
import com.cyou.demo.databinding.ItemYkVideoBinding;
import com.cyou.demo.youku.data.bean.YKVideo;
import com.cyou.frame.base.BaseViewBinder;
import com.cyou.frame.base.BaseViewHolder;
import com.cyou.frame.util.ImageLoaderUtil;

/**
 * Created by ztw on 2015/7/22.
 */
public class YKVideoViewBinder extends BaseViewBinder<YKVideoViewBinder.ViewHolder> {

    public YKVideoViewBinder() {
    }

    @Override
    public ViewHolder onCreateViewHolder(int position, int type, ViewGroup parent) {

        ItemYkVideoBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_yk_video,
                parent,
                false);
        ViewHolder holder = new ViewHolder(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, Object object, int position, int type) {
        ItemYkVideoBinding binding = (ItemYkVideoBinding) holder.getBinding();
        final YKVideo bean = (YKVideo)object;
        binding.setBean(bean);
        ImageLoaderUtil.displayImage(bean.getThumbnail(), binding.ivPic, R.drawable.def_gray_big);
    }

    public static class ViewHolder extends BaseViewHolder {

        private ViewDataBinding binding;

        public ViewHolder(View itemView) {
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
