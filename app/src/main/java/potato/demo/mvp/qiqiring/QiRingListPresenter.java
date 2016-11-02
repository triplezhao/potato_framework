package potato.demo.mvp.qiqiring;

import android.support.annotation.Nullable;

import com.lzy.okhttputils.cache.CacheMode;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import potato.demo.chips.api.QICallback;
import potato.demo.data.request.QIApi;
import potato.demo.data.result.QIRingEntity;

final class QiRingListPresenter implements QiRingList.P {


    private QiRingList.V view;

    /**
     * Dagger strictly enforces that arguments not marked with {@code @Nullable} are not injected
     * with {@code @Nullable} values.
     */
    @Inject
    QiRingListPresenter(
            QiRingList.V view) {
        this.view = view;
    }


    @Override
    public void start() {

    }


    @Override
    public void reqRefresh(String id, String page, String pageSize) {
        QIApi.getRings(CacheMode.NET_ONLY, id, page, pageSize, new QICallback<QIRingEntity>() {
            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                if (e != null)
                    view.onRefreshFail(e.getMessage());
            }

            @Override
            public void onResponse(boolean isFromCache, QIRingEntity icRingCatEntity, Request request, @Nullable Response response) {
                view.onRefreshSucc(icRingCatEntity);
            }
        });
    }

    @Override
    public void reqLoadMore(String id, String page, String pageSize) {

        QIApi.getRings(CacheMode.NET_ONLY, id, page, pageSize, new QICallback<QIRingEntity>() {
            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                if (e != null)
                    view.onLoadMoreFail(e.getMessage());
            }

            @Override
            public void onResponse(boolean isFromCache, QIRingEntity icRingCatEntity, Request request, @Nullable Response response) {
                view.onLoadMoreSucc(icRingCatEntity);
            }
        });

    }
}