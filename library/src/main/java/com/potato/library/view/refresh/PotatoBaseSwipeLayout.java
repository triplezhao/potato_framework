package com.potato.library.view.refresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

import com.potato.library.util.L;
import com.potato.library.view.NormalEmptyView;

public class PotatoBaseSwipeLayout extends SwipeRefreshLayout {

    private int mEnd;
    protected int mTouchSlop;

    public PotatoBaseSwipeLayout(Context context) {
        this(context, null);
        init(context);
    }

    public PotatoBaseSwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public void init(Context context) {
        final DisplayMetrics metrics = getResources().getDisplayMetrics();
        mEnd = (int) (24 * metrics.density);
        L.i("BaseSwipeLayout","mEnd="+mEnd);
        this.setColorSchemeResources(com.potato.library.R.color.google_blue,
                com.potato.library.R.color.google_green,
                com.potato.library.R.color.google_red,
                com.potato.library.R.color.google_yellow);
    }

    //设置刷新动画最大位置
    public void setEnd(int mEnd) {
        this.mEnd = mEnd;
    }

    public void setRefreshing(boolean isLoading) {
        setProgressViewOffset(false, 0, mEnd);
        super.setRefreshing(isLoading);
    }


    private NormalEmptyView mEmptyview;



    public void setEmptyView(NormalEmptyView emptyView){
        mEmptyview = emptyView;
    }

    public void showProgress(){
        if(mEmptyview!=null){
            mEmptyview.setEmptyType(NormalEmptyView.EMPTY_TYPE_GONE);
        }
        setRefreshing(true);
    }
    public void showSucc(){
        if(mEmptyview!=null){
            mEmptyview.setEmptyType(NormalEmptyView.EMPTY_TYPE_GONE);
        }
        setRefreshing(false);
    }
    public void showEmptyViewFail(){
        if(mEmptyview!=null) {
            mEmptyview.setEmptyType(NormalEmptyView.EMPTY_TYPE_ERROR);
        }
        setRefreshing(false);
    }
    public void showEmptyViewNoContent(){
        if(mEmptyview!=null) {
            mEmptyview.setEmptyType(NormalEmptyView.EMPTY_TYPE_NOCONTENT);
            setRefreshing(false);
        }
    }



}