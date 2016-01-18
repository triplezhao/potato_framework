package com.potato.demo.v.act;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.potato.chips.app.GlobleConstant;
import com.potato.chips.base.BaseDefaultListActivity;
import com.potato.chips.base.BaseParser;
import com.potato.demo.R;
import com.potato.demo.databinding.ActivityYkVideosBinding;
import com.potato.demo.m.parser.YKVideosByUserParser;
import com.potato.demo.m.request.YKRequestBuilder;
import com.potato.demo.v.adapter.YKVideoAdapter;
import com.potato.library.adapter.PotatoBaseRecyclerViewAdapter;
import com.potato.library.net.Request;
import com.potato.library.view.hfrecyclerview.HFGridlayoutSpanSizeLookup;

/**
 * Created by ztw on 2015/7/3.
 */
public class YKActivity extends BaseDefaultListActivity {

    public static final String TAG = YKActivity.class.getSimpleName();

    private PotatoBaseRecyclerViewAdapter mAdapter;
    private ActivityYkVideosBinding mBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_yk_videos);

        setSupportActionBar(mBinding.toolbar);

        mAdapter = new YKVideoAdapter(mContext);

        initListView(mBinding.includeA.llSwipe);

        GridLayoutManager manager = new GridLayoutManager(mContext, 3);
        manager.setSpanSizeLookup(new HFGridlayoutSpanSizeLookup(mSwipeContainer.getHFAdapter(), manager.getSpanCount()) {
            @Override
            public int getSpanSize(int position) {
                boolean isHeaderOrFooter = adapter.isHeader(position) || adapter.isFooter(position);
                if (isHeaderOrFooter) {
                    return mSpanSize;
                } else {
                    return (3 - position % 3);
                }
            }
        });
        mSwipeContainer.setLayoutManager(manager);
        mSwipeContainer.showProgress();
        reqRefresh();
    }

    @Override
    public PotatoBaseRecyclerViewAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public Request getRefreshRequest() {
        return YKRequestBuilder.videosByUser(GlobleConstant.youku_client_id, GlobleConstant.youku_def_uid, "", "", "1", "", "");
    }

    @Override
    public Request getLoadMoreRequest() {
        return YKRequestBuilder.videosByUser(GlobleConstant.youku_client_id, GlobleConstant.youku_def_uid, "", "", mPage + 1 + "", "", "");
    }

    public BaseParser getParser(String json) {
        return new YKVideosByUserParser(json);
    }


}
