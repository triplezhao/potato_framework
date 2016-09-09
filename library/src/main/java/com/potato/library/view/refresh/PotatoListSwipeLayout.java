package com.potato.library.view.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

public class PotatoListSwipeLayout extends PotatoBaseSwipeLayout implements AbsListView.OnScrollListener {

    public ListView mListView;
    private OnLoadListener mOnLoadListener;
    private View mListViewFooter;

    private int mYDown;
    private int mLastY;

    private boolean isLoading = false;
    private int mEnd;
    private boolean mEnableLoad;

    public ScrollLisener scrollLisener;

    public PotatoListSwipeLayout(Context context) {
        this(context, null);
    }

    public PotatoListSwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(Context context) {
        super.init(context);

    }

    public ScrollLisener getScrollLisener() {
        return scrollLisener;
    }

    public void setScrollLisener(ScrollLisener scrollLisener) {
        this.scrollLisener = scrollLisener;
    }

    //set the footer of the ListView with a ProgressBar in it
    public void setFooterView(Context context, ListView mListView, int layoutId) {
        setLoadEnable(true);

        mListViewFooter = LayoutInflater.from(context).inflate(layoutId, null,
                false);
        mListView.addFooterView(mListViewFooter);
        mListView.setFooterDividersEnabled(false);
        this.mListView = mListView;
        mListView.setOnScrollListener(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mYDown = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                mLastY = (int) event.getRawY();
                if (isPullingUp())
                    //you can add view or hint here when pulling up the ListView
                    break;

            case MotionEvent.ACTION_UP:
//                if (canLoad()) {
//                    loadData();
//                }
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(event);
    }

    public void setLoadEnable(boolean enableLoad) {
        mEnableLoad = enableLoad;
        if (!mEnableLoad) {
            mListView.removeFooterView(mListViewFooter);
        }
    }

    private boolean canLoad() {
        if (mListView.getAdapter() == null || mListView.getAdapter().getCount() < 1)
            return false;
        return isBottom() && !isLoading && isPullingUp();
    }

    private boolean isBottom() {
        if (mListView.getCount() > 0) {
            if (mListView.getLastVisiblePosition() == mListView.getAdapter().getCount() - 1) {
                if (mListView.getFooterViewsCount() == 0) {
                    if (mListView.getChildAt(mListView.getChildCount() - 1).getBottom() <= mListView.getHeight()) {
                        return true;
                    }
                } else {
                    if (mListView.getChildAt(mListView.getChildCount() - 2).getBottom() <= mListView.getHeight()) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    private boolean isPullingUp() {
        return (mYDown - mLastY) >= mTouchSlop;
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
            if (mListView.getFooterViewsCount() == 0) {
                mListView.addFooterView(mListViewFooter);
//                mListView.setSelection(mListView.getAdapter().getCount() - 1);
            } else {
                mListViewFooter.setVisibility(VISIBLE);
                //mListView.addFooterView(mListViewFooter);
            }
        } else {
//            if (mListView.getAdapter() instanceof HeaderViewListAdapter) {
//                mListView.removeFooterView(mListViewFooter);
//            } else {
            mListViewFooter.setVisibility(View.GONE);
//            }
            mYDown = 0;
            mLastY = 0;
        }
    }

    public void setOnLoadListener(OnLoadListener loadListener) {
        mOnLoadListener = loadListener;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (scrollLisener != null) {
            switch (i) {

                case AbsListView.OnScrollListener.SCROLL_STATE_IDLE://停止滚动
                {
                    //设置为停止滚动
                    scrollLisener.resume();
                    break;
                }
                case AbsListView.OnScrollListener.SCROLL_STATE_FLING://滚动做出了抛的动作
                {
                    //设置为正在滚动
                    scrollLisener.pause();
                    break;
                }

                case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL://正在滚动
                {
                    //设置为正在滚动
                    scrollLisener.pause();
                    break;
                }
            }
        }

        if (canLoad()) {
            loadData();
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }

    public interface OnLoadListener {
        public void onLoad();
    }

    public interface ScrollLisener {
        public void pause();

        public void resume();
    }


}