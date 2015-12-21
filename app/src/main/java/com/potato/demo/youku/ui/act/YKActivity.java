package com.potato.demo.youku.ui.act;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.potato.chips.app.GlobleConstant;
import com.potato.demo.R;
import com.potato.demo.databinding.ActivityYkVideosBinding;
import com.potato.demo.youku.data.bean.YKVideo;
import com.potato.demo.youku.data.parser.YKVideosByUserParser;
import com.potato.demo.youku.data.request.YKRequestBuilder;
import com.potato.demo.youku.ui.adapter.YKVideoAdapter;
import com.potato.library.act.PotatoDefListActivity;
import com.potato.library.adapter.BaseRecyclerViewAdapter;
import com.potato.library.data.PotatoBaseParser;
import com.potato.library.net.Request;
import com.potato.library.view.hfrecyclerview.HFGridlayoutSpanSizeLookup;

import java.util.ArrayList;

/**
 * Created by ztw on 2015/7/3.
 */
public class YKActivity extends PotatoDefListActivity {

    public static final String TAG = YKActivity.class.getSimpleName();

    private ArrayList<YKVideo> mList = new ArrayList<YKVideo>();
    private BaseRecyclerViewAdapter mAdapter;
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
    public BaseRecyclerViewAdapter getAdapter() {
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

    public PotatoBaseParser getParser(String json) {
        return new YKVideosByUserParser(json);
    }


}
