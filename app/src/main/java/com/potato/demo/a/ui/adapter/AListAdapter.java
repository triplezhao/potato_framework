package com.potato.demo.a.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.potato.chips.base.BaseListAdapter;
import com.potato.chips.base.BaseViewHolder;
import com.potato.chips.util.ImageLoaderUtil;
import com.potato.demo.R;
import com.potato.demo.a.data.bean.ABean;
import com.potato.demo.a.ui.act.ADetailActivity;
import com.potato.demo.databinding.ItemABinding;
import com.potato.library.util.L;

/**
 * Created by ztw on 2015/9/21.
 */
public class AListAdapter extends BaseListAdapter{

    public AListAdapter(Context context) {
        super(context);
    }

    @Override
    public AViewHolder onCreateViewHolder(int position, int type, ViewGroup parent) {

        ItemABinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_a,
                parent,
                false);
        AViewHolder holder = new AViewHolder(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, Object object, int position, int type) {
        final ItemABinding binding = (ItemABinding) ((AViewHolder)holder).getBinding();
        final ABean bean = (ABean) object;
        binding.setBean(bean);
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ADetailActivity.class);
                intent.putExtra(ADetailActivity.EXTRA_NAME, bean.getTitle());
                context.startActivity(intent);
            }
        });
        L.i("with", bean.getIcon());

        ImageLoaderUtil.displayImage(bean.getIcon(), binding.avatar, R.drawable.def_gray_small);
    }

    public static class AViewHolder extends BaseViewHolder {

        private ViewDataBinding binding;

        public AViewHolder(View itemView) {
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
