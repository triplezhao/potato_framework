package potato.demo.ui.ab;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.potato.library.adapter.PotatoBaseListAdapter;
import com.potato.library.util.L;

import butterknife.Bind;
import butterknife.ButterKnife;
import potato.demo.R;
import potato.demo.chips.util.ImageLoaderUtil;
import potato.demo.data.bean.ABean;

/**
 * Created by ztw on 2015/9/21.
 */
public class AListAdapter extends PotatoBaseListAdapter<AListAdapter.VH> {


    public AListAdapter(Context context) {
        super(context);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int type) {

        View view = mInflater.inflate(
                R.layout.item_a,
                parent,
                false);
        VH holder = new VH(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(VH vh, int position) {
        final ABean bean = (ABean) mData.get(position);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ADetailActivity.class);
                intent.putExtra(ADetailActivity.EXTRA_NAME, bean.getTitle());
                context.startActivity(intent);
            }
        });
        L.i("with", bean.getIcon());

        ImageLoaderUtil.displayImage(bean.getIcon(), vh.avatar, R.drawable.def_gray_small);
    }

    public static class VH extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_title)
        TextView tv_title;
        @Bind(R.id.tv_content)
        TextView tv_content;
        @Bind(R.id.avatar)
        ImageView avatar;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
