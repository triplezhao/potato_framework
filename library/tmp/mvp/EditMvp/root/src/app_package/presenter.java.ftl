package ${packageName};

import javax.inject.Inject;

final class ${presenterName} implements ${mvpClass}.P {


    private ${mvpClass}.V view;

    /**
     * Dagger strictly enforces that arguments not marked with {@code @Nullable} are not injected
     * with {@code @Nullable} values.
     */
    @Inject
    ${presenterName}(
            ${mvpClass}.V view) {
        this.view = view;
    }


    @Override
    public void start() {

    }

    @Override
    public void reqCreate(${mvpClass}ParamBean bean) {
    ${mvpClass?cap_first}CreateApi.req(CacheMode.NET_ONLY, bean, new AiyouyunCallback<AiyouyunResultEntity>() {
        @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {

            if (e != null)
            view.onReqFail(e.getMessage());
        }

        @Override
        public void onResponse(boolean isFromCache, AiyouyunResultEntity entity, Request request, @Nullable Response response) {
        view.onReqSucc(entity);
        }
        });
        }

    @Override
    public void reqEdit(${mvpClass}ParamBean bean) {
    ${mvpClass?cap_first}EditApi.req(CacheMode.NET_ONLY, bean, new AiyouyunCallback<AiyouyunResultEntity>() {
        @Override
        public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {

        if (e != null)
        view.onReqFail(e.getMessage());
        }

        @Override
        public void onResponse(boolean isFromCache, AiyouyunResultEntity entity, Request request, @Nullable Response response) {
        view.onReqSucc(entity);
        }
        });
        }
}