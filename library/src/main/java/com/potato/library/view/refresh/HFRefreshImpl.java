package com.potato.library.view.refresh;

import android.view.View;

/**
 * Created by ztw on 2015/12/18.
 */
public interface HFRefreshImpl {


    public void initListView(View view);

    public void onRefreshSucc(String json);

    public void onRefreshFail(String err);

    public void onLoadMoreSucc(String json);

    public void onLoadMoreFail(String err);

    public void onCacheLoaded(String json);

    public void reqRefresh();

    public void reqLoadMore();

}
