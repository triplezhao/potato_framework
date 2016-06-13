package potato.demo.mvp.yk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.potato.library.adapter.PotatoBaseRecyclerViewAdapter;
import com.potato.library.view.NormalEmptyView;
import com.potato.library.view.hfrecyclerview.HFGridlayoutSpanSizeLookup;
import com.potato.library.view.refresh.PotatoRecyclerSwipeLayout;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import potato.demo.R;
import potato.demo.chips.base.BaseFragment;
import potato.demo.chips.common.PageCtrl;
import potato.demo.chips.util.ImageLoaderUtil;
import potato.demo.data.bean.YKVideo;
import potato.demo.data.result.YKVideosByCategoryEntity;

public class YKHomeListFragment extends BaseFragment implements YKHomeList.V {

    public static final String TAG = YKHomeListFragment.class.getSimpleName();

    public static final String EXTRARS_CATEGORY = "EXTRARS_CATEGORY";
    public static final String EXTRARS_TITLE = "EXTRARS_TITLE";
    public String mCategory;
    public String mTitle;
    public ArrayList<Object> mList = new ArrayList<Object>();
    public PotatoBaseRecyclerViewAdapter mAdapter;
    public YKVideosByCategoryEntity mEntity;

    @Bind(R.id.swipe_container)
    PotatoRecyclerSwipeLayout mSwipeContainer;
    @Bind(R.id.list)
    RecyclerView listview;
    @Bind(R.id.empty_view)
    NormalEmptyView mNormalEmptyView;
    @Bind(R.id.include_a)
    LinearLayout include_a;
    @Inject YKHomeListPresenter presenter;


    public static YKHomeListFragment instance(Context context, String category, String title) {
        Bundle args = new Bundle();
        args.putString(YKHomeListFragment.EXTRARS_CATEGORY, category);
        args.putString(YKHomeListFragment.EXTRARS_TITLE, title);
        YKHomeListFragment pageFragement = (YKHomeListFragment) Fragment.instantiate(context, YKHomeListFragment.class.getName(), args);
        return pageFragement;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ykhome_list, container, false);

        ButterKnife.bind(this, view);

        DaggerYKHomeList_C.builder().module(new YKHomeList.Module(this)).build().inject(this);

        mCategory = getArguments() == null ? "" : getArguments().getString(EXTRARS_CATEGORY);
        mTitle = getArguments() == null ? "" : getArguments().getString(EXTRARS_TITLE);

        mAdapter = new YKHomeListAdapter(mContext);
        mEntity = new YKVideosByCategoryEntity();

        initListView();

        mSwipeContainer.showProgress();
        presenter.reqRefresh(mCategory);

        return view;
    }

    public void initListView() {

        mSwipeContainer.setRecyclerView(listview, mAdapter);
        mSwipeContainer.setLayoutManager(new LinearLayoutManager(mContext));
       /*
       瀑布流
       * //setLayoutManager
        ExStaggeredGridLayoutManager manager = new ExStaggeredGridLayoutManager (2, StaggeredGridLayoutManager.VERTICAL);
        manager.setSpanSizeLookup(new HeaderSpanSizeLookup((HeaderAndFooterRecyclerViewAdapter) mRecyclerView.getAdapter(), manager.getSpanCount()));
        mSwipeContainer.setLayoutManager(manager);

       网格
        //setLayoutManager
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new HeaderSpanSizeLookup((HeaderAndFooterRecyclerViewAdapter) mRecyclerView.getAdapter(), manager.getSpanCount()));
        mRecyclerView.setLayoutManager(manager);
       * */
        mSwipeContainer.setFooterView(listview, com.potato.library.R.layout.potato_listview_footer);

        mSwipeContainer.setScrollStateLisener(new PotatoRecyclerSwipeLayout.ScrollStateLisener() {
            @Override
            public void pause() {
                ImageLoader.getInstance().pause();
            }

            @Override
            public void resume() {
                ImageLoader.getInstance().resume();
            }
        });
        mSwipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.reqRefresh(mCategory);
            }
        });
        mSwipeContainer.setOnLoadListener(new PotatoRecyclerSwipeLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                presenter.reqLoadMore(mCategory, mEntity.page + "");
            }
        });

        mSwipeContainer.setEmptyView(mNormalEmptyView);
        mNormalEmptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSwipeContainer.showProgress();
                presenter.reqRefresh(mCategory);
            }
        });

        GridLayoutManager manager = new GridLayoutManager(mContext, 2);
        manager.setSpanSizeLookup(new HFGridlayoutSpanSizeLookup(mSwipeContainer.getHFAdapter(), manager.getSpanCount()) {
            @Override
            public int getSpanSize(int position) {
                boolean isHeaderOrFooter = adapter.isHeader(position) || adapter.isFooter(position);
                if (isHeaderOrFooter) {
                    return mSpanSize;
                } else {
                    return 1;
                }
            }
        });
        mSwipeContainer.setLayoutManager(manager);

    }


    @Override
    public void onRefreshSucc(YKVideosByCategoryEntity entity) {
        mEntity = entity;
        mSwipeContainer.showSucc();
        mList = entity.list;
        mAdapter.setDataList(mList);
        mAdapter.notifyDataSetChanged();
        mSwipeContainer.setRefreshing(false);
        if (mList != null && mList.size() != 0) {
            mSwipeContainer.setLoadEnable(true);
        }

    }

    @Override
    public void onRefreshFail(String err) {
        mSwipeContainer.setLoadEnable(false);
        mSwipeContainer.setRefreshing(false);
    }

    @Override
    public void onLoadMoreSucc(YKVideosByCategoryEntity entity) {
        mEntity = entity;
        mSwipeContainer.setLoading(false);
        ArrayList<Object> moreData = entity.list;
        if (moreData == null || moreData.size() == 0) {
            mSwipeContainer.setLoadEnable(false);
            return;
        }
        mList.addAll(moreData);
        mAdapter.setDataList(mList);
        mAdapter.notifyItemInserted(mList.size() - 1);
    }

    @Override
    public void onLoadMoreFail(String err) {
        mSwipeContainer.setLoadEnable(false);
        mSwipeContainer.setRefreshing(false);
    }

    @Override
    public void onCacheLoaded(YKVideosByCategoryEntity entity) {

    }

    @Override
    public void onClick(View v) {

    }

    public static class YKHomeListAdapter extends PotatoBaseRecyclerViewAdapter<YKHomeListAdapter.VH> {

        public YKHomeListAdapter(Context context) {
            super(context);
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(
                    R.layout.item_yk_video,
                    parent,
                    false);
            VH holder = new VH(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(final VH vh, int position) {
            final YKVideo bean = (YKVideo) mData.get(position);
            vh.tv_title.setText(bean.getTitle());
            ImageLoaderUtil.displayImage(bean.getThumbnail(), vh.iv_pic, R.drawable.def_gray_big);
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
//                Uri content_url = Uri.parse(bean.getLink());
                    Uri content_url = Uri.parse("https://shop108703695.taobao.com");
                    intent.setData(content_url);
//                binding.getRoot().getContext().startActivity(intent);

                    PageCtrl.start2WebViewActivity(vh.itemView.getContext(), "https://shop108703695.taobao.com");
//                PageCtrl.start2SchemaPage(content_url);
//                https://shop108703695.taobao.com
                }
            });

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