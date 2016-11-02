package potato.demo.mvp.baidu;

import android.support.annotation.Nullable;

import com.lzy.okhttputils.cache.CacheMode;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import potato.demo.chips.api.BaiduCallback;
import potato.demo.data.request.BaiduApi;
import potato.demo.data.result.BaiduImageListByCategoryEntity;

final class BaiduListPresenter implements BaiduList.P {


    private BaiduList.V view;

    /**
     * Dagger strictly enforces that arguments not marked with {@code @Nullable} are not injected
     * with {@code @Nullable} values.
     */
    @Inject
    BaiduListPresenter(
            BaiduList.V view) {
        this.view = view;
    }


    @Override
    public void start() {

    }


    @Override
    public void reqRefresh(String id, String page, String pageSize) {
        BaiduApi.imageList(CacheMode.NET_ONLY, id, page, pageSize, new BaiduCallback<BaiduImageListByCategoryEntity>() {
            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {

                if (e != null)
                    view.onRefreshFail(e.getMessage());
            }

            @Override
            public void onResponse(boolean isFromCache, BaiduImageListByCategoryEntity entity, Request request, @Nullable Response response) {
                view.onRefreshSucc(entity);
            }
        });
    }

    @Override
    public void reqLoadMore(String id, String page, String pageSize) {
        BaiduApi.imageList(CacheMode.NET_ONLY, id, page, pageSize, new BaiduCallback<BaiduImageListByCategoryEntity>() {
            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                if (e != null)
                    view.onLoadMoreFail(e.getMessage());
            }

            @Override
            public void onResponse(boolean isFromCache, BaiduImageListByCategoryEntity entity, Request request, @Nullable Response response) {
                view.onLoadMoreSucc(entity);

            }
        });
    }
}