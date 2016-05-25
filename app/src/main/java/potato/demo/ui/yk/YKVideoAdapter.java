package potato.demo.ui.yk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import potato.demo.data.bean.YKVideo;

/**
 * Created by ztw on 2015/9/21.
 */
public class YKVideoAdapter extends PotatoBaseRecyclerViewAdapter {

    public YKVideoAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(
                R.layout.item_yk_video,
                parent,
                false);
        VH holder = new VH(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final VH vh = ((VH) holder);
        final YKVideo bean = (YKVideo) mData.get(position);
        vh.tv_title.setText(bean.getTitle());
        ImageLoaderUtil.displayImage(bean.getThumbnail(), vh.iv_pic, R.drawable.def_gray_big);
        vh.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
//                Uri content_url = Uri.parse(bean.getLink());
                Uri content_url = Uri.parse("https://shop108703695.taobao.com");
                intent.setData(content_url);
//                binding.getRoot().getContext().startActivity(intent);

                PageCtrl.start2WebViewActivity(vh.view.getContext(), "https://shop108703695.taobao.com");
//                PageCtrl.start2SchemaPage(content_url);
//                https://shop108703695.taobao.com
            }
        });
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
