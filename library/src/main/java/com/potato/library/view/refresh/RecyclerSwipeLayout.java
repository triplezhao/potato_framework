package com.potato.library.view.refresh;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import com.potato.library.util.L;


public class RecyclerSwipeLayout extends BaseSwipeLayout {

    public final String TAG = RecyclerSwipeLayout.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private OnLoadListener mOnLoadListener;
    private View mRecyclerViewFooter;

    private int mYDown;
    private int mLastY;

    private boolean isLoading = false;
    private int mEnd;
    private boolean mEnableLoad;
    private static final int CIRCLE_DIAMETER_LARGE = 56;
    private int mFooter_height;

    public RecyclerSwipeLayout(Context context) {
        this(context, null);
        init(context);
    }

    public RecyclerSwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public void init(Context context) {
        super.init(context);
        final DisplayMetrics metrics = getResources().getDisplayMetrics();
        mFooter_height= (int) (CIRCLE_DIAMETER_LARGE * metrics.density);
    }

    //set the footer of the RecyclerView with a ProgressBar in it
    public void setFooterView(Context context, RecyclerView mRecyclerView, int layoutId) {
        setLoadEnable(true);

        mRecyclerViewFooter = LayoutInflater.from(context).inflate(layoutId, null,
                false);
        this.mRecyclerView = mRecyclerView;
        this.addView(mRecyclerViewFooter);
        mRecyclerView.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (canLoad()) {
                    loadData();
                }
            }
        });
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

    public void setLoadEnable(boolean enableLoad) {
        mEnableLoad = enableLoad;
        if (!mEnableLoad) {
            if (mRecyclerViewFooter != null)
                this.removeView(mRecyclerViewFooter);
            mRecyclerViewFooter = null;
        }
    }

    private boolean canLoad() {
        if (mRecyclerView.getAdapter() == null && mRecyclerView.getAdapter().getItemCount() < 1)
            return false;
        return mEnableLoad && !isLoading && isPullingUp() && isBottom();
    }

    private boolean isBottom() {
        if (mRecyclerView.getAdapter().getItemCount() > 0) {
            if (((LinearLayoutManager) mRecyclerView.getLayoutManager()).findLastVisibleItemPosition() == mRecyclerView.getAdapter().getItemCount() - 1) {
                if (mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1).getBottom() <= mRecyclerView.getHeight()) {
                    return true;
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
            if (mRecyclerViewFooter == null) {
                this.addView(mRecyclerViewFooter);
            } else {
                mRecyclerViewFooter.setVisibility(VISIBLE);
                L.i(TAG, "setLoading");
            }
            mRecyclerView.setPadding(0, 0, 0, mFooter_height);
            mRecyclerView.scrollToPosition(mRecyclerView.getAdapter().getItemCount()-1);
        } else {
            mRecyclerViewFooter.setVisibility(View.GONE);
            mRecyclerView.setPadding(0,0,0,0);
            mYDown = 0;
            mLastY = 0;
        }
    }

    public void setOnLoadListener(OnLoadListener loadListener) {
        mOnLoadListener = loadListener;
    }


    public static interface OnLoadListener {
        public void onLoad();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        L.i(TAG, "onLayout");
        if (mRecyclerViewFooter != null) {
            L.i(TAG, "onLayout+mRecyclerView.getBottom()"+mRecyclerView.getBottom());
            L.i(TAG, "onLayout+mRecyclerViewFooter.getHeight()"+mRecyclerViewFooter.getHeight());
            L.i(TAG, "onLayout+mRecyclerViewFooter.getWidth()"+mRecyclerViewFooter.getWidth());
            final int width = getMeasuredWidth();
            final int height = getMeasuredHeight();
            int footerWidth = mRecyclerViewFooter.getMeasuredWidth();
            int footereHeight = mRecyclerViewFooter.getMeasuredHeight();
            mRecyclerViewFooter.layout(left, mRecyclerView.getBottom()-mFooter_height,
                    right, mRecyclerView.getBottom());
        }
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mRecyclerViewFooter != null) {
            L.i(TAG, "onMeasure"+mFooter_height);
            L.i(TAG, "MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.EXACTLY)" + MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.EXACTLY));
            mRecyclerViewFooter.measure(MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(mFooter_height, MeasureSpec.EXACTLY));
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
