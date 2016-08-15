package potato.demo.mvp.icimage;

import android.support.annotation.Nullable;

import com.lzy.okhttputils.cache.CacheMode;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import potato.demo.chips.api.ICCallback;
import potato.demo.data.request.ICApi;
import potato.demo.data.result.ICImageCatEntity;

final class ICImagePresenter implements ICImage.P {


    private ICImage.V view;

    /**
     * Dagger strictly enforces that arguments not marked with {@code @Nullable} are not injected
     * with {@code @Nullable} values.
     */
    @Inject
    ICImagePresenter(
            ICImage.V view) {
        this.view = view;
    }


    @Override
    public void start() {

    }


    @Override
    public void loadData() {

        ICApi.getImageCats(CacheMode.REQUEST_FAILED_READ_CACHE, new ICCallback<ICImageCatEntity>() {
            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {

            }

            @Override
            public void onResponse(boolean isFromCache, ICImageCatEntity icImageCatEntity, Request request, @Nullable Response response) {
                view.render(icImageCatEntity);
            }
        });

    }
}