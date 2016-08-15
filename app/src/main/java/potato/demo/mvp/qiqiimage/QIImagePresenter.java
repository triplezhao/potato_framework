package potato.demo.mvp.qiqiimage;

import android.support.annotation.Nullable;

import com.lzy.okhttputils.cache.CacheMode;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import potato.demo.chips.api.QICallback;
import potato.demo.data.request.QIApi;
import potato.demo.data.result.QIImageCatEntity;

final class QIImagePresenter implements QIImage.P {


    private QIImage.V view;

    /**
     * Dagger strictly enforces that arguments not marked with {@code @Nullable} are not injected
     * with {@code @Nullable} values.
     */
    @Inject
    QIImagePresenter(
            QIImage.V view) {
        this.view = view;
    }


    @Override
    public void start() {

    }


    @Override
    public void loadData() {

        QIApi.getImageCats(CacheMode.REQUEST_FAILED_READ_CACHE, new QICallback<QIImageCatEntity>() {
            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {

            }

            @Override
            public void onResponse(boolean isFromCache, QIImageCatEntity icImageCatEntity, Request request, @Nullable Response response) {
                view.render(icImageCatEntity);
            }
        });

    }
}