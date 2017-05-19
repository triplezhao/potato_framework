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
    public void reqDetail(String id) {

        ${mvpClass?cap_first}Api.req(CacheMode.NET_ONLY, id, new ${app_name}Callback<${mvpClass}Entity>() {
            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {

                if (e != null)
                view.onReqFail(e.getMessage());
            }

            @Override
            public void onResponse(boolean isFromCache, ${mvpClass}Entity entity, Request request, @Nullable Response response) {
                    view.onReqSucc(entity);
                    }
            });
       

    }
}