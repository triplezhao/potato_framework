package com.potato.library.view.refresh;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;

import com.potato.library.util.L;
import com.potato.library.view.hfrecyclerview.HFRecyclerViewAdapter;


public class HFRecyclerSwipeLayout extends BaseSwipeLayout {

    public final String TAG = HFRecyclerSwipeLayout.class.getSimpleName();
    public RecyclerView mRecyclerView;
    private OnLoadListener mOnLoadListener;

    private boolean isLoading = false;

    private boolean mEnableLoad;

    private static final int CIRCLE_DIAMETER_LARGE = 56;
    private View mListViewFooter;
    private View mListViewHeader;

    public ScrollStateLisener ScrollStateLisener;

    public HFRecyclerSwipeLayout(Context context) {
        this(context, null);
        init(context);
    }

    public HFRecyclerSwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public void init(Context context) {
        super.init(context);

    }

    public ScrollStateLisener getScrollStateLisener() {
        return ScrollStateLisener;
    }

    public void setScrollStateLisener(ScrollStateLisener ScrollStateLisener) {
        this.ScrollStateLisener = ScrollStateLisener;
    }

    public void setLoadEnable(boolean enableLoad) {
        RecyclerView.Adapter outerAdapter = mRecyclerView.getAdapter();

        if (outerAdapter == null || !(outerAdapter instanceof HFRecyclerViewAdapter)) {
            return;
        }

        HFRecyclerViewAdapter headerAndFooterAdapter = (HFRecyclerViewAdapter) outerAdapter;

        mEnableLoad = enableLoad;

        View footerView = headerAndFooterAdapter.getFooterView();
        if (footerView != null) {
            if (!mEnableLoad) {
                headerAndFooterAdapter.removeFooterView(footerView);
            }
        }


    }


    public void loadData() {
        if (!mEnableLoad) return;
        if (mOnLoadListener != null) {
            setLoading(true);
            mOnLoadListener.onLoad();
        }
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        if (isLoading) {
            if (isRefreshing()) setRefreshing(false);
            mListViewFooter.setVisibility(VISIBLE);
//            mRecyclerView.scrollToPosition(mRecyclerView.getAdapter().getItemCount() - 1);
        } else {
            mListViewFooter.setVisibility(GONE);
        }
    }

    public void setOnLoadListener(OnLoadListener loadListener) {
        mOnLoadListener = loadListener;
    }


    public static interface OnLoadListener {
        public void onLoad();
    }

    public interface ScrollStateLisener {
        public void pause();

        public void resume();
    }


    ///////////////////////////////hf code /////////////////////////////////////////////////
    /**
     * 当前RecyclerView类型
     */
    protected LayoutManagerType layoutManagerType;

    /**
     * 最后一个的位置
     */
    private int[] lastPositions;

    /**
     * 最后一个可见的item的位置
     */
    private int lastVisibleItemPosition;

    /**
     * 当前滑动的状态
     */
    private int currentScrollState = 0;


    /**
     * 取数组中最大值
     *
     * @param lastPositions
     * @return
     */
    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }

        return max;
    }


    public static enum LayoutManagerType {
        LinearLayout,
        StaggeredGridLayout,
        GridLayout
    }

    public HFRecyclerViewAdapter getHFAdapter() {
        RecyclerView.Adapter outerAdapter = mRecyclerView.getAdapter();

        if (outerAdapter == null || !(outerAdapter instanceof HFRecyclerViewAdapter)) {
            return null;
        }

        return (HFRecyclerViewAdapter) outerAdapter;
    }

    /**
     * 设置HeaderView
     *
     * @param recyclerView
     * @param view
     */
    public void setHeaderView(RecyclerView recyclerView, View view) {
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();

        if (outerAdapter == null || !(outerAdapter instanceof HFRecyclerViewAdapter)) {
            return;
        }

        HFRecyclerViewAdapter headerAndFooterAdapter = (HFRecyclerViewAdapter) outerAdapter;
        if (headerAndFooterAdapter.getHeaderViewsCount() == 0) {
            headerAndFooterAdapter.addHeaderView(view);
            mListViewHeader = view;
        }
    }

    /**
     * 设置FooterView
     *
     * @param recyclerView
     * @param view
     */
    public void setFooterView(RecyclerView recyclerView, View view) {
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();

        if (outerAdapter == null || !(outerAdapter instanceof HFRecyclerViewAdapter)) {
            return;
        }
        HFRecyclerViewAdapter headerAndFooterAdapter = (HFRecyclerViewAdapter) outerAdapter;
        if (headerAndFooterAdapter.getFooterViewsCount() == 0) {
            headerAndFooterAdapter.addFooterView(view);
            mListViewFooter = view;
            setLoadEnable(true);
        }
    }

    /**
     * 设置FooterView
     *
     * @param recyclerView
     * @param view
     */
    public void setFooterView(RecyclerView recyclerView, int layoutId) {
        View view = inflate(getContext(), layoutId, null);
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();

        if (outerAdapter == null || !(outerAdapter instanceof HFRecyclerViewAdapter)) {
            return;
        }
        HFRecyclerViewAdapter headerAndFooterAdapter = (HFRecyclerViewAdapter) outerAdapter;
        if (headerAndFooterAdapter.getFooterViewsCount() == 0) {
            headerAndFooterAdapter.addFooterView(view);
            mListViewFooter = view;
            setLoadEnable(true);
        }
    }

    /**
     * 移除FooterView
     *
     * @param recyclerView
     */
    public void removeFooterView(RecyclerView recyclerView) {

        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();

        if (outerAdapter != null && outerAdapter instanceof HFRecyclerViewAdapter) {

            int footerViewCounter = ((HFRecyclerViewAdapter) outerAdapter).getFooterViewsCount();
            if (footerViewCounter > 0) {
                View footerView = ((HFRecyclerViewAdapter) outerAdapter).getFooterView();
                ((HFRecyclerViewAdapter) outerAdapter).removeFooterView(footerView);
            }
        }
    }

    /**
     * 移除HeaderView
     *
     * @param recyclerView
     */
    public void removeHeaderView(RecyclerView recyclerView) {

        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();

        if (outerAdapter != null && outerAdapter instanceof HFRecyclerViewAdapter) {

            int headerViewCounter = ((HFRecyclerViewAdapter) outerAdapter).getHeaderViewsCount();
            if (headerViewCounter > 0) {
                View headerView = ((HFRecyclerViewAdapter) outerAdapter).getHeaderView();
                ((HFRecyclerViewAdapter) outerAdapter).removeFooterView(headerView);
            }
        }
    }

    /**
     * 请使用本方法替代RecyclerView.ViewHolder的getLayoutPosition()方法
     *
     * @param recyclerView
     * @param holder
     * @return
     */
    public int getLayoutPosition(RecyclerView recyclerView, RecyclerView.ViewHolder holder) {
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && outerAdapter instanceof HFRecyclerViewAdapter) {

            int headerViewCounter = ((HFRecyclerViewAdapter) outerAdapter).getHeaderViewsCount();
            if (headerViewCounter > 0) {
                return holder.getLayoutPosition() - headerViewCounter;
            }
        }

        return holder.getLayoutPosition();
    }

    /**
     * 请使用本方法替代RecyclerView.ViewHolder的getAdapterPosition()方法
     *
     * @param recyclerView
     * @param holder
     * @return
     */
    public int getAdapterPosition(RecyclerView recyclerView, RecyclerView.ViewHolder holder) {
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && outerAdapter instanceof HFRecyclerViewAdapter) {

            int headerViewCounter = ((HFRecyclerViewAdapter) outerAdapter).getHeaderViewsCount();
            if (headerViewCounter > 0) {
                return holder.getAdapterPosition() - headerViewCounter;
            }
        }

        return holder.getAdapterPosition();
    }

    public void setRecyclerView(RecyclerView recyclerView,RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {
        mRecyclerView = recyclerView;
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                L.i(TAG, "onScrollStateChanged");
                if (ScrollStateLisener != null) {
                    switch (newState) {

                        case RecyclerView.SCROLL_STATE_IDLE://停止滚动
                        {
                            //设置为停止滚动
                            ScrollStateLisener.resume();
                            break;
                        }
                        case RecyclerView.SCROLL_STATE_DRAGGING://滚动做出了抛的动作
                        {
                            //设置为正在滚动
                            ScrollStateLisener.pause();
                            break;
                        }

                        case RecyclerView.SCROLL_STATE_SETTLING://正在滚动
                        {
                            //设置为正在滚动
                            ScrollStateLisener.pause();
                            break;
                        }
                    }
                }
                currentScrollState = newState;
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
//                int totalItemCount = layoutManager.getItemCount() - getHFAdapter().getHeaderViewsCount()-getHFAdapter().getFooterViewsCount();

                L.i(TAG, "visibleItemCount=" + visibleItemCount + "totalItemCount" + totalItemCount);
                if ((visibleItemCount > 0 && currentScrollState == RecyclerView.SCROLL_STATE_IDLE && (lastVisibleItemPosition) >= totalItemCount - 1)) {
                    loadData();
                }

            }


            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                L.i(TAG, "onScrolled");
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                if (layoutManagerType == null) {
                    if (layoutManager instanceof LinearLayoutManager) {
                        layoutManagerType = LayoutManagerType.LinearLayout;
                    } else if (layoutManager instanceof GridLayoutManager) {
                        layoutManagerType = LayoutManagerType.GridLayout;
                    } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                        layoutManagerType = LayoutManagerType.StaggeredGridLayout;
                    } else {
                        throw new RuntimeException(
                                "Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
                    }
                }

                switch (layoutManagerType) {
                    case LinearLayout:
                        lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                        break;
                    case GridLayout:
                        lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                        break;
                    case StaggeredGridLayout:
                        StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                        if (lastPositions == null) {
                            lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                        }
                        staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                        lastVisibleItemPosition = findMax(lastPositions);
                        break;
                }
            }

        });

        HFRecyclerViewAdapter hfRecyclerViewAdapter = new HFRecyclerViewAdapter(adapter);
        mRecyclerView.setAdapter(hfRecyclerViewAdapter);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layout) {
        if (mRecyclerView == null) {
            throw new NullPointerException("call this func,please call setRecyclerView before ");
        }
        mRecyclerView.setLayoutManager(layout);
    }
}
