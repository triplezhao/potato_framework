package potato.demo.chips.base;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.potato.library.adapter.PotatoBaseRecyclerViewAdapter;
import com.potato.library.view.NormalEmptyView;
import com.potato.library.view.refresh.PotatoRecyclerSwipeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import potato.demo.R;
import potato.demo.chips.api.BaiduCallback;
import potato.demo.chips.api.BaseResultEntity;
import potato.demo.mvp.util.BaseListView;

public abstract class BaseDefaultListFragment extends BaseFragment implements View.OnClickListener, Handler.Callback, BaseListView {

    public static final String TAG = BaseDefaultListFragment.class.getSimpleName();

    @Bind(R.id.include_a) public LinearLayout include_a;
    @Bind(R.id.swipe_container) public PotatoRecyclerSwipeLayout mSwipeContainer;
    @Bind(R.id.list) public RecyclerView listview;
    @Bind(R.id.empty_view) public NormalEmptyView mNormalEmptyView;
    public String mId = "美女";
    public int mPage = 0;
    public String pageSize = "5";
    public List mList = new ArrayList();
    public PotatoBaseRecyclerViewAdapter mAdapter;
    public BaseResultEntity mEntity;

    public abstract PotatoBaseRecyclerViewAdapter getAdapter();

    public abstract void reqRefresh();

    public abstract void reqLoadMore();

    @Override
    public void initListView() {
        mAdapter = getAdapter();
        mEntity = new BaiduCallback.BaiduResultEntity() {
            @Override
            public BaseResultEntity parse(String json) {
                return null;
            }
        };
        mSwipeContainer.setRecyclerView(listview, mAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        //  瀑布流
        //setLayoutManager
        /*ExStaggeredGridLayoutManager manager = new ExStaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        manager.setSpanSizeLookup(new HFGridlayoutSpanSizeLookup((HFRecyclerViewAdapter) listview.getAdapter(), manager.getSpanCount()));
        mSwipeContainer.setLayoutManager(manager);*/

        // 网格
        //setLayoutManager
//        GridLayoutManager manager = new GridLayoutManager(this, 2);
//        manager.setSpanSizeLookup(new HFGridlayoutSpanSizeLookup((HFRecyclerViewAdapter) listview.getAdapter(), manager.getSpanCount()));
        mSwipeContainer.setLayoutManager(manager);

        /*View headerview = getLayoutInflater().inflate(R.layout.header_bshop_home, null);
        mSwipeContainer.setHeaderView(listview, headerview);*/

        mSwipeContainer.setColorSchemeResources(R.color.google_red, R.color.google_red, R.color.google_red, R.color.google_red);
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
                reqRefresh();
            }
        });
        mSwipeContainer.setOnLoadListener(new PotatoRecyclerSwipeLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                reqLoadMore();
            }
        });
        mSwipeContainer.setEmptyView(mNormalEmptyView);
        mNormalEmptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reqRefresh();
            }
        });

    }

    @Override
    public void onRefreshSucc(BaseResultEntity entity) {
        if (!entity.isSucc()) {
            mSwipeContainer.showEmptyViewFail();
            return;
        }
        if (entity.list == null || entity.list.size() == 0) {
            mSwipeContainer.showEmptyViewNoContent();
            return;
        }
        mEntity = entity;
        mList = entity.list;
        mSwipeContainer.showSucc();
        mAdapter.setDataList(mList);
        mAdapter.notifyDataSetChanged();
        mSwipeContainer.autoShowByTotal(mEntity.total);
    }

    @Override
    public void onRefreshFail(String err) {
        mSwipeContainer.showEmptyViewFail();
    }

    @Override
    public void onLoadMoreSucc(BaseResultEntity entity) {

        if (!entity.isSucc()) {
            mSwipeContainer.autoShowByTotal(mEntity.total);
            return;
        }
        if (entity.list == null || entity.list.size() == 0) {
            mSwipeContainer.autoShowByTotal(mEntity.total);
            return;
        }
        mPage = mPage + 1;
        mEntity = entity;
        mList.addAll(entity.list);
        mAdapter.setDataList(mList);
        mAdapter.notifyDataSetChanged();
        mSwipeContainer.autoShowByTotal(mEntity.total);
    }

    @Override
    public void onLoadMoreFail(String err) {
        mSwipeContainer.setRefreshing(false);
        mSwipeContainer.autoShowByTotal(mEntity.total);
    }

    @Override
    public void onCacheLoaded(BaseResultEntity entity) {

    }

}
