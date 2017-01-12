package potato.demo.mvp.baidu;

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
import potato.demo.data.bean.BaiduImageBean;

public class BaiduListFragment extends BaseDefaultListFragment implements BaiduList.V {

    public static final String TAG = BaiduListFragment.class.getSimpleName();

    public static final String EXTRARS_SECTION_ID = "EXTRARS_SECTION_ID";
    public static final String EXTRARS_TITLE = "EXTRARS_TITLE";
    public String mTitle;
    @Inject BaiduListPresenter presenter;


    public static BaiduListFragment instance(Context context, String id, String title) {
        Bundle args = new Bundle();
        args.putString(BaiduListFragment.EXTRARS_SECTION_ID, id);
        args.putString(BaiduListFragment.EXTRARS_TITLE, title);
        BaiduListFragment pageFragement = (BaiduListFragment) Fragment.instantiate(context, BaiduListFragment.class.getName(), args);
        return pageFragement;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_baidu_list, container, false);

        ButterKnife.bind(this, view);

        DaggerBaiduList_C.builder().module(new BaiduList.Module(this)).build().inject(this);

        mId = getArguments() == null ? "" : getArguments().getString(EXTRARS_SECTION_ID);
        mTitle = getArguments() == null ? "" : getArguments().getString(EXTRARS_TITLE);

        initListView();

        if(mId.equals("美女")){
            mSwipeContainer.setRefreshEnable(false);
            mSwipeContainer.showProgressOffset(0,80);
        }else
        if(mId.equals("动漫")){
            mSwipeContainer.setRefreshEnable(false);
//            mSwipeContainer.showProgressOffset(10000,-10000);
//            mSwipeContainer.setDistanceToTriggerSync(-1);
        }else
        if(mId.equals("明星")){
//            mSwipeContainer.setRefreshEnable(false);
//            mSwipeContainer.showProgressOffset(-30000,-50000);
//            mSwipeContainer.setDistanceToTriggerSync(-1);
        }

//        mSwipeContainer.showEmptyViewProgress();
        mSwipeContainer.showProgress();
        mPage = 1;
        presenter.reqRefresh(mId, "1", pageSize);
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
        presenter.reqRefresh(mId, "1", pageSize);
    }

    @Override
    public void reqLoadMore() {
        presenter.reqLoadMore(mId, mPage + 1 + "", pageSize);
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
                    R.layout.item_jiongtu_list,
                    parent,
                    false);
            VH holder = new VH(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(final VH vh, int position) {
            final BaiduImageBean bean = (BaiduImageBean) mData.get(position);

            vh.tv_title.setText(bean.getTitle());
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
//                    PageCtrl.startJiongTuDetailActivity(context, bean);
                }
            });
            ImageLoaderUtil.displayImage(bean.getImageUrl(), vh.iv_pic, R.drawable.def_gray_big);

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