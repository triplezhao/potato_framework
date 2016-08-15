package potato.demo.mvp.qiqiring;

import android.support.annotation.Nullable;

import com.lzy.okhttputils.cache.CacheMode;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import potato.demo.chips.api.QICallback;
import potato.demo.data.request.QIApi;
import potato.demo.data.result.QIRingCatEntity;

final class QiRingPresenter implements QiRing.P {


    private QiRing.V view;

    /**
     * Dagger strictly enforces that arguments not marked with {@code @Nullable} are not injected
     * with {@code @Nullable} values.
     */
    @Inject
    QiRingPresenter(
            QiRing.V view) {
        this.view = view;
    }


    @Override
    public void start() {

    }


    @Override
    public void loadData() {

        QIApi.getRingCats(CacheMode.REQUEST_FAILED_READ_CACHE, new QICallback<QIRingCatEntity>() {
            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {

            }

            @Override
            public void onResponse(boolean isFromCache, QIRingCatEntity qiRingCatEntity, Request request, @Nullable Response response) {
                view.render(qiRingCatEntity);
            }
        });

    }
}