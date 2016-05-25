package potato.demo.ui.yk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.lzy.okhttputils.cache.CacheMode;
import com.potato.library.adapter.PotatoBaseRecyclerViewAdapter;
import com.potato.library.view.hfrecyclerview.HFGridlayoutSpanSizeLookup;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import potato.demo.R;
import potato.demo.chips.api.BaseResultEntity;
import potato.demo.chips.api.YKCallback;
import potato.demo.chips.app.GlobleConstant;
import potato.demo.chips.base.BaseDefaultListActivity;
import potato.demo.data.parser.YKVideosByUserEntity;
import potato.demo.data.request.YKApi;

/**
 * Created by ztw on 2015/7/3.
 */
public class YKActivity extends BaseDefaultListActivity {

    public static final String TAG = YKActivity.class.getSimpleName();

    private PotatoBaseRecyclerViewAdapter mAdapter;

    @Bind(R.id.include_a)
    LinearLayout include_a;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yk_videos);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        mAdapter = new YKVideoAdapter(mContext);

        initListView(include_a);

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
    public void reqRefresh() {
        YKApi.videosByUser(CacheMode.REQUEST_FAILED_READ_CACHE, GlobleConstant.youku_client_id, GlobleConstant.youku_def_uid, "", "", "1", "", "", new YKCallback<YKVideosByUserEntity>() {
            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                if (e != null)
                    onRefreshFail(e.getMessage());
            }

            @Override
            public void onResponse(boolean isFromCache, BaseResultEntity entity, Request request, @Nullable Response response) {
                onRefreshSucc(entity);
                mPage = ((YKVideosByUserEntity) entity).page;

            }
        });
    }

    @Override
    public void reqLoadMore() {
        YKApi.videosByUser(CacheMode.DEFAULT, GlobleConstant.youku_client_id, GlobleConstant.youku_def_uid, "", "", mPage + 1 + "", "", "", new YKCallback<YKVideosByUserEntity>() {
            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                if (e != null)
                onLoadMoreFail(e.getMessage());
            }

            @Override
            public void onResponse(boolean isFromCache, BaseResultEntity entity, Request request, @Nullable Response response) {
                onLoadMoreSucc(entity);
                mPage = ((YKVideosByUserEntity) entity).page;
            }
        });
    }


}
