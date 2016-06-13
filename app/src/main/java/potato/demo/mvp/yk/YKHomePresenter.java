package potato.demo.mvp.yk;

import android.support.annotation.Nullable;

import com.lzy.okhttputils.cache.CacheMode;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import potato.demo.chips.api.YKCallback;
import potato.demo.data.request.YKApi;
import potato.demo.data.result.YKVideoCategoryEntity;

final class YKHomePresenter implements YKHome.P {


    private YKHome.V view;

    /**
     * Dagger strictly enforces that arguments not marked with {@code @Nullable} are not injected
     * with {@code @Nullable} values.
     */
    @Inject
    YKHomePresenter(
            YKHome.V view) {
        this.view = view;
    }


    @Override
    public void start() {

    }


    @Override
    public void loadData() {

        YKApi.videosCategory(CacheMode.REQUEST_FAILED_READ_CACHE, new YKCallback<YKVideoCategoryEntity>() {
            @Override
            public void onResponse(boolean isFromCache, YKVideoCategoryEntity entity, Request request, @Nullable Response response) {
                view.render(entity);
            }

            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {

            }
        });

    }
}