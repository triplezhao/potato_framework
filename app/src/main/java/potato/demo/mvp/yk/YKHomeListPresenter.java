package potato.demo.mvp.yk;

import android.support.annotation.Nullable;

import com.lzy.okhttputils.cache.CacheMode;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import potato.demo.chips.api.YKCallback;
import potato.demo.data.request.YKApi;
import potato.demo.data.result.YKVideosByCategoryEntity;

final class YKHomeListPresenter implements YKHomeList.P {


    private YKHomeList.V view;

    /**
     * Dagger strictly enforces that arguments not marked with {@code @Nullable} are not injected
     * with {@code @Nullable} values.
     */
    @Inject
    YKHomeListPresenter(
            YKHomeList.V view) {
        this.view = view;
    }


    @Override
    public void start() {

    }


    @Override
    public void reqRefresh(String category) {
        YKApi.videosByCategory(CacheMode.REQUEST_FAILED_READ_CACHE, category, "0", "20", new YKCallback<YKVideosByCategoryEntity>() {
            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {

                if (e != null)
                    view.onRefreshFail(e.getMessage());
            }

            @Override
            public void onResponse(boolean isFromCache, YKVideosByCategoryEntity entity, Request request, @Nullable Response response) {
                view.onRefreshSucc(entity);
            }
        });
    }

    @Override
    public void reqLoadMore(String category, String page) {
        YKApi.videosByCategory(CacheMode.DEFAULT, category, page, "20", new YKCallback<YKVideosByCategoryEntity>() {
            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response
                    response, @Nullable Exception e) {
                if (e != null)
                    view.onLoadMoreFail(e.getMessage());
            }

            @Override
            public void onResponse(boolean isFromCache, YKVideosByCategoryEntity entity, Request
                    request, @Nullable Response response) {
                view.onLoadMoreSucc(entity);

            }
        });
    }
}