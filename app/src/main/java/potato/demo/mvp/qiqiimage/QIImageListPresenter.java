package potato.demo.mvp.qiqiimage;

import android.support.annotation.Nullable;

import com.lzy.okhttputils.cache.CacheMode;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import potato.demo.chips.api.QICallback;
import potato.demo.data.request.QIApi;
import potato.demo.data.result.QIImageEntity;

final class QIImageListPresenter implements QIImageList.P {


    private QIImageList.V view;

    /**
     * Dagger strictly enforces that arguments not marked with {@code @Nullable} are not injected
     * with {@code @Nullable} values.
     */
    @Inject
    QIImageListPresenter(
            QIImageList.V view) {
        this.view = view;
    }


    @Override
    public void start() {

    }


    @Override
    public void reqRefresh(String id, String page, String pageSize) {
        QIApi.getImages(CacheMode.REQUEST_FAILED_READ_CACHE, id, page, pageSize, new QICallback<QIImageEntity>() {
            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                if (e != null)
                    view.onRefreshFail(e.getMessage());
            }

            @Override
            public void onResponse(boolean isFromCache, QIImageEntity icImageCatEntity, Request request, @Nullable Response response) {
                view.onRefreshSucc(icImageCatEntity);
            }
        });
    }

    @Override
    public void reqLoadMore(String id, String page, String pageSize) {

        QIApi.getImages(CacheMode.REQUEST_FAILED_READ_CACHE, id, page, pageSize, new QICallback<QIImageEntity>() {
            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                if (e != null)
                    view.onLoadMoreFail(e.getMessage());
            }

            @Override
            public void onResponse(boolean isFromCache, QIImageEntity icImageCatEntity, Request request, @Nullable Response response) {
                view.onLoadMoreSucc(icImageCatEntity);
            }
        });

    }
}