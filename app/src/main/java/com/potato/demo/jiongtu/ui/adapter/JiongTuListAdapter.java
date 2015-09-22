package com.potato.demo.jiongtu.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.potato.chips.base.BaseListAdapter;
import com.potato.chips.base.BaseViewHolder;
import com.potato.chips.common.PageCtrl;
import com.potato.chips.util.ImageLoaderUtil;
import com.potato.demo.R;
import com.potato.demo.databinding.ItemJiongtuListBinding;
import com.potato.demo.jiongtu.data.bean.JiongtuAlbum;

/**
 * Created by ztw on 2015/9/21.
 */
public class JiongTuListAdapter extends BaseListAdapter{

    public JiongTuListAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(int position, int type, ViewGroup parent) {

        ItemJiongtuListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_jiongtu_list,
                parent,
                false);
        ViewHolder holder = new ViewHolder(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, Object object, int position, int type) {
        ItemJiongtuListBinding binding = (ItemJiongtuListBinding) ((ViewHolder)holder).getBinding();
        final JiongtuAlbum bean = (JiongtuAlbum)object;
        binding.setBean(bean);
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                PageCtrl.startJiongTuDetailActivity(context, bean);
            }
        });
        ImageLoaderUtil.displayImage(bean.getBigCover(), binding.ivPic, R.drawable.def_gray_big);
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
