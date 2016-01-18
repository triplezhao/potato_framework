package potato.demo.mvvm.v.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import potato.demo.chips.util.ImageLoaderUtil;
import potato.demo.R;
import potato.demo.mvvm.m.bean.ABean;
import potato.demo.mvvm.v.act.ADetailActivity;
import potato.demo.databinding.ItemABinding;
import com.potato.library.adapter.PotatoBaseListAdapter;
import com.potato.library.adapter.PotatoBaseViewHolder;
import com.potato.library.util.L;

/**
 * Created by ztw on 2015/9/21.
 */
public class AListAdapter extends PotatoBaseListAdapter {

    public AListAdapter(Context context) {
        super(context);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int type) {

        ItemABinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_a,
                parent,
                false);
        VH holder = new VH(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,int position) {
        final ItemABinding binding = (ItemABinding) ((VH)holder).getBinding();
        final ABean bean = (ABean) mData.get(position);
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

    public static class VH extends PotatoBaseViewHolder {

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
