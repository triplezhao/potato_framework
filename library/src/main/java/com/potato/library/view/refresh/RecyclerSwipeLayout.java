/*
package com.potato.library.view.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsRecyclerView;
import android.widget.RecyclerView;

public class RecyclerSwipeLayout extends BaseSwipeLayout {


    public RecyclerSwipeLayout(Context context) {
        this(context, null);
        init(context);
    }

    public RecyclerSwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public void init(Context context) {
        final DisplayMetrics metrics = getResources().getDisplayMetrics();
    }

    private int mTouchSlop;
    private RecyclerView  mRecyclerView;
    private OnLoadListener mOnLoadListener;
    private View mRecyclerViewFooter;

    private int mYDown;
    private int mLastY;

    private boolean isLoading = false;
    private int mEnd;
    private boolean mEnableLoad;

    //set the footer of the RecyclerView with a ProgressBar in it
    public void setFooterView(Context context, RecyclerView mRecyclerView, int layoutId) {
        setLoadEnable(true);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mRecyclerViewFooter = LayoutInflater.from(context).inflate(layoutId, null,
                false);
        mRecyclerView.addFooterView(mRecyclerViewFooter);
        mRecyclerView.setFooterDividersEnabled(false);
        this.mRecyclerView = mRecyclerView;
        mRecyclerView.setOnScrollListener(this);
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
                    //you can add view or hint here when pulling up the RecyclerView
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

    public void setLoadEnable(boolean enableLoad){
        mEnableLoad = enableLoad;
        if(!mEnableLoad){
            mRecyclerView.removeFooterView(mRecyclerViewFooter);
        }
    }

    private boolean canLoad() {
        if(mRecyclerView.getAdapter()==null||mRecyclerView.getAdapter().getCount()<1)
            return false;
        return isBottom() && !isLoading && isPullingUp();
    }

    private boolean isBottom() {
        if (mRecyclerView.getCount() > 0) {
            if (mRecyclerView.getLastVisiblePosition() == mRecyclerView.getAdapter().getCount() - 1 ){
                if(mRecyclerView.getFooterViewsCount() == 0){
                    if(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1).getBottom() <= mRecyclerView.getHeight()) {
                        return true;
                    }
                }else{
                    if(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 2).getBottom() <= mRecyclerView.getHeight()) {
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
            if (mRecyclerView.getFooterViewsCount() == 0) {
                mRecyclerView.addFooterView(mRecyclerViewFooter);
//                mRecyclerView.setSelection(mRecyclerView.getAdapter().getCount() - 1);
            } else {
                mRecyclerViewFooter.setVisibility(VISIBLE);
                //mRecyclerView.addFooterView(mRecyclerViewFooter);
            }
        } else {
//            if (mRecyclerView.getAdapter() instanceof HeaderViewListAdapter) {
//                mRecyclerView.removeFooterView(mRecyclerViewFooter);
//            } else {
            mRecyclerViewFooter.setVisibility(View.GONE);
//            }
            mYDown = 0;
            mLastY = 0;
        }
    }

    public void setOnLoadListener(OnLoadListener loadListener) {
        mOnLoadListener = loadListener;
    }

    @Override
    public void onScrollStateChanged(AbsRecyclerView absRecyclerView, int i) {
        if(canLoad()){
            loadData();
        }
    }

    @Override
    public void onScroll(AbsRecyclerView absRecyclerView, int i, int i1, int i2) {

    }

    public static interface OnLoadListener {
        public void onLoad();
    }
}*/
