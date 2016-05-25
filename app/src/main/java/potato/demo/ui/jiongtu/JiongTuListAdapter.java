package potato.demo.ui.jiongtu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.potato.library.adapter.PotatoBaseRecyclerViewAdapter;
import com.potato.library.adapter.PotatoBaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import potato.demo.R;
import potato.demo.chips.common.PageCtrl;
import potato.demo.chips.util.ImageLoaderUtil;
import potato.demo.data.bean.JiongtuAlbum;

/**
 * Created by ztw on 2015/9/21.
 */
public class JiongTuListAdapter extends PotatoBaseRecyclerViewAdapter {

    public JiongTuListAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(
                R.layout.item_jiongtu_list,
                parent,
                false);
        VH holder = new VH(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VH vh = ((VH) holder);
        final JiongtuAlbum bean = (JiongtuAlbum) mData.get(position);

        vh.tv_title.setText(bean.getTitle());
        vh.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                PageCtrl.startJiongTuDetailActivity(context, bean);
            }
        });
        ImageLoaderUtil.displayImage(bean.getBigCover(), vh.iv_pic, R.drawable.def_gray_big);

    }


    public static class VH extends PotatoBaseViewHolder {

        @Bind(R.id.iv_pic)
        ImageView iv_pic;
        @Bind(R.id.tv_title)
        TextView tv_title;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
