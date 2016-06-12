package potato.demo.mvp.jiongdetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.potato.library.adapter.PotatoBaseListAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import potato.demo.R;
import potato.demo.chips.util.ImageLoaderUtil;
import potato.demo.chips.util.ShareUtil;
import potato.demo.data.bean.JiongtuPhoto;

/**
 * Created by ztw on 2015/9/21.
 */
public class JiongDetailAdapter extends PotatoBaseListAdapter<JiongDetailAdapter.VH> {

    public JiongDetailAdapter(Context context) {
        super(context);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int type) {

        View view = mInflater.inflate(
                R.layout.item_jiongtu_detail,
                parent,
                false);
        VH holder = new VH(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(VH vh, int position) {
        final JiongtuPhoto bean = (JiongtuPhoto) mData.get(position);
//        L.i("Picasso", "URL," + bean.getBigUrl());
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareUtil.shareImage(view.getContext(), bean.getBigUrl());
            }
        });
        ImageLoaderUtil.displayImage(bean.getBigUrl(), vh.iv_pic, R.drawable.def_gray_big);

    }

    public static class VH extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_pic)
        ImageView iv_pic;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
