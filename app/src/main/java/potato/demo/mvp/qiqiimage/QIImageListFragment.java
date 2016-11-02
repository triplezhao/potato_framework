package potato.demo.mvp.qiqiimage;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.potato.library.adapter.PotatoBaseRecyclerViewAdapter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import potato.demo.R;
import potato.demo.chips.base.BaseDefaultListFragment;
import potato.demo.chips.util.ImageLoaderUtil;
import potato.demo.data.bean.QIImageBean;

public class QIImageListFragment extends BaseDefaultListFragment implements QIImageList.V {

    public static final String TAG = QIImageListFragment.class.getSimpleName();

    public static final String EXTRARS_SECTION_ID = "EXTRARS_SECTION_ID";
    public static final String EXTRARS_TITLE = "EXTRARS_TITLE";
    public String mSectionId;
    public String mTitle;
    @Inject QIImageListPresenter presenter;


    public static QIImageListFragment instance(Context context, String sectionId, String title) {
        Bundle args = new Bundle();
        args.putString(QIImageListFragment.EXTRARS_SECTION_ID, sectionId);
        args.putString(QIImageListFragment.EXTRARS_TITLE, title);
        QIImageListFragment pageFragement = (QIImageListFragment) Fragment.instantiate(context, QIImageListFragment.class.getName(), args);
        return pageFragement;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_qiimage_list, container, false);

        ButterKnife.bind(this, view);

        DaggerQIImageList_C.builder().module(new QIImageList.Module(this)).build().inject(this);

        mSectionId = getArguments() == null ? "" : getArguments().getString(EXTRARS_SECTION_ID);
        mTitle = getArguments() == null ? "" : getArguments().getString(EXTRARS_TITLE);

        initListView();

        reqRefresh();

        return view;
    }

    @Override
    public PotatoBaseRecyclerViewAdapter getAdapter() {
        return mAdapter = new AAdapter(mContext);
    }

    @Override
    public void reqRefresh() {
        mSwipeContainer.showProgress();
        mPage = 1;
        presenter.reqRefresh(mSectionId, "1", pageSize);
    }

    @Override
    public void reqLoadMore() {
        presenter.reqLoadMore(mSectionId, mPage + 1 + "", pageSize);
    }


    @Override
    public String getCid() {
        return mSectionId;
    }


    @Override
    public void onClick(View v) {

    }

    public static class AAdapter extends PotatoBaseRecyclerViewAdapter<AAdapter.VH> {

        public AAdapter(Context context) {
            super(context);
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(
                    R.layout.item_qiimage_list,
                    parent,
                    false);
            VH holder = new VH(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(final VH vh, int position) {
            final QIImageBean bean = (QIImageBean) mData.get(position);

            vh.tv_title.setText(bean.getImage_name());
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Context context = v.getContext();
                    PageCtrl.startJiongTuDetailActivity(context, bean);*/
                }
            });
            ImageLoaderUtil.displayImage(bean.getImage_pic(), vh.iv_pic, R.drawable.def_gray_big);

        }


        static class VH extends RecyclerView.ViewHolder {

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

}