package potato.demo.mvp.qiqiring;

import android.content.Context;
import android.content.Intent;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import potato.demo.R;
import potato.demo.chips.base.BaseFragment;
import potato.demo.chips.event.MusicEvent;
import potato.demo.data.bean.QIRingBean;
import potato.demo.data.result.QIRingEntity;

public class QiRingListFragment extends BaseFragment implements QiRingList.V {

    public static final String TAG = QiRingListFragment.class.getSimpleName();

    public static final String EXTRARS_SECTION_ID = "EXTRARS_SECTION_ID";
    public static final String EXTRARS_TITLE = "EXTRARS_TITLE";
    public String mSectionId;
    public String mTitle;
    public int mTotal = 0;
    public int mPage = 0;
    public ArrayList<QIRingEntity> mList = new ArrayList<QIRingEntity>();
    public PotatoBaseRecyclerViewAdapter mAdapter;
    public QIRingEntity mEntity;

    @Bind(R.id.swipe_container)
    PotatoRecyclerSwipeLayout mSwipeContainer;
    @Bind(R.id.list)
    RecyclerView listview;
    @Bind(R.id.empty_view)
    NormalEmptyView mNormalEmptyView;
    @Bind(R.id.include_a)
    LinearLayout include_a;
    @Inject QiRingListPresenter presenter;


    public static QiRingListFragment instance(Context context, String sectionId, String title) {
        Bundle args = new Bundle();
        args.putString(QiRingListFragment.EXTRARS_SECTION_ID, sectionId);
        args.putString(QiRingListFragment.EXTRARS_TITLE, title);
        QiRingListFragment pageFragement = (QiRingListFragment) Fragment.instantiate(context, QiRingListFragment.class.getName(), args);
        return pageFragement;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);  //注册
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消注册
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_qi_ring_list, container, false);

        ButterKnife.bind(this, view);

        DaggerQiRingList_C.builder().module(new QiRingList.Module(this)).build().inject(this);

        mSectionId = getArguments() == null ? "" : getArguments().getString(EXTRARS_SECTION_ID);
        mTitle = getArguments() == null ? "" : getArguments().getString(EXTRARS_TITLE);

        mAdapter = new QiRingListAdapter(mContext);
        mEntity = new QIRingEntity();

        initListView();

        mSwipeContainer.showProgress();
        presenter.reqRefresh();

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
                presenter.reqRefresh();
            }
        });
        mSwipeContainer.setOnLoadListener(new PotatoRecyclerSwipeLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                presenter.reqLoadMore(mEntity.nowpage + 1);
            }
        });

        mSwipeContainer.setEmptyView(mNormalEmptyView);
        mNormalEmptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSwipeContainer.showProgress();
                presenter.reqRefresh();
            }
        });

        GridLayoutManager manager = new GridLayoutManager(mContext, 1);
        manager.setSpanSizeLookup(new HFGridlayoutSpanSizeLookup(mSwipeContainer.getHFAdapter(), manager.getSpanCount()) {
            @Override
            public int getSpanSize(int position) {
                boolean isHeaderOrFooter = adapter.isHeader(position) || adapter.isFooter(position);
                if (isHeaderOrFooter) {
                    return mSpanSize;
                } else if (position % 3 == 0) {
                    return mSpanSize;
                } else {
                    return 1;
                }
            }
        });
        mSwipeContainer.setLayoutManager(manager);

    }


    @Override
    public void onRefreshSucc(final QIRingEntity entity) {
        mSwipeContainer.postDelayed(new Runnable() {
            @Override
            public void run() {
                mEntity = entity;
                mSwipeContainer.showSucc();
                mList = entity.list;
                mAdapter.setDataList(mList);
                mAdapter.notifyDataSetChanged();
                mSwipeContainer.autoShowByTotal(mEntity.total);
            }
        }, 2000);
    }

    @Override
    public void onRefreshFail(String err) {
        mSwipeContainer.showEmptyViewFail();
    }

    @Override
    public void onLoadMoreSucc(final QIRingEntity entity) {
        mSwipeContainer.postDelayed(new Runnable() {
            @Override
            public void run() {
                mEntity = entity;
                ArrayList<QIRingEntity> moreData = entity.list;
                mList.addAll(moreData);
                mAdapter.setDataList(mList);
                mAdapter.notifyDataSetChanged();
                mSwipeContainer.autoShowByTotal(mEntity.total);

            }
        }, 2000);

    }

    @Override
    public void onLoadMoreFail(String err) {
        mSwipeContainer.setLoadEnable(false);
        mSwipeContainer.setRefreshing(false);
    }

    @Override
    public void onCacheLoaded(QIRingEntity entity) {

    }

    @Override
    public String getCid() {
        return mSectionId;
    }

    @Override
    public void onClick(View v) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MusicEvent event) {
//        Toast.makeText(getActivity(), event.message, Toast.LENGTH_SHORT).show();
        mAdapter.notifyDataSetChanged();
    }

    public static class QiRingListAdapter extends PotatoBaseRecyclerViewAdapter<QiRingListAdapter.VH> {

        public QiRingListAdapter(Context context) {
            super(context);
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(
                    R.layout.item_qi_ring_list,
                    parent,
                    false);
            VH holder = new VH(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(final VH vh, int position) {
            final QIRingBean bean = (QIRingBean) mData.get(position);

            vh.tv_title.setText(bean.getRing_name());

            vh.iv_pic.setImageResource(R.drawable.playbar_btn_play);
            if (bean.getRing_pic().equals(MusicService.mPlayingUrl)) {
                vh.iv_pic.setImageResource(R.drawable.playbar_btn_pause);
                vh.iv_pic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                   /* Context context = v.getContext();
                    PageCtrl.startJiongTuDetailActivity(context, bean);*/

                        Intent musicSer = new Intent(mContext, MusicService.class);
                        musicSer.putExtra("action", 3);
                        musicSer.putExtra("url", bean.getRing_pic());
//                    musicSer.putExtra("url","http://125.39.66.163/files/7099000002B49876/file.kuyinyun.com/group1/M00/65/02/rBBGdFWiomGECilcAAAAABqIEBk213.mp3");
                        mContext.startService(musicSer);
                    }
                });
            } else {
                vh.iv_pic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                   /* Context context = v.getContext();
                    PageCtrl.startJiongTuDetailActivity(context, bean);*/

                        Intent musicSer = new Intent(mContext, MusicService.class);
                        musicSer.putExtra("action", 0);
                        musicSer.putExtra("url", bean.getRing_pic());
//                    musicSer.putExtra("url","http://125.39.66.163/files/7099000002B49876/file.kuyinyun.com/group1/M00/65/02/rBBGdFWiomGECilcAAAAABqIEBk213.mp3");
                        mContext.startService(musicSer);
                    }
                });
            }


//            ImageLoaderUtil.displayImage(bean.getRing_pic(), vh.iv_pic, R.drawable.def_gray_big);

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