package potato.demo.mvp.icimage;

import android.support.annotation.Nullable;

import com.lzy.okhttputils.cache.CacheMode;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import potato.demo.chips.api.ICCallback;
import potato.demo.data.request.ICApi;
import potato.demo.data.result.ICImageEntity;

final class ICImageListPresenter implements ICImageList.P {


    private ICImageList.V view;

    /**
     * Dagger strictly enforces that arguments not marked with {@code @Nullable} are not injected
     * with {@code @Nullable} values.
     */
    @Inject
    ICImageListPresenter(
            ICImageList.V view) {
        this.view = view;
    }


    @Override
    public void start() {

    }


    @Override
    public void reqRefresh() {
        ICApi.getImages(CacheMode.REQUEST_FAILED_READ_CACHE,view.getSectionId()+"","1","2" ,new ICCallback<ICImageEntity>() {
            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                if (e != null)
                    view.onRefreshFail(e.getMessage());
            }

            @Override
            public void onResponse(boolean isFromCache, ICImageEntity icImageCatEntity, Request request, @Nullable Response response) {
                view.onRefreshSucc(icImageCatEntity);
            }
        });
    }

    @Override
    public void reqLoadMore(int page) {

        ICApi.getImages(CacheMode.REQUEST_FAILED_READ_CACHE,view.getSectionId()+"",page+"","2" ,new ICCallback<ICImageEntity>() {
            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                if (e != null)
                    view.onLoadMoreFail(e.getMessage());
            }

            @Override
            public void onResponse(boolean isFromCache, ICImageEntity icImageCatEntity, Request request, @Nullable Response response) {
                view.onLoadMoreSucc(icImageCatEntity);
            }
        });

    }
}