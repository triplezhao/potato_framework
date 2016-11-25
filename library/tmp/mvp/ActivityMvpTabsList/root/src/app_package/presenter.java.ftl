package ${packageName};

import javax.inject.Inject;

final class ${presenterClass} implements ${mvpClass}.P {


    private ${mvpClass}.V view;

    /**
     * Dagger strictly enforces that arguments not marked with {@code @Nullable} are not injected
     * with {@code @Nullable} values.
     */
    @Inject
    ${presenterClass}(
            ${mvpClass}.V view) {
        this.view = view;
    }


    @Override
    public void start() {

    }


    @Override
    public void loadData() {

       JiongtuApi.getSectionListRequest(CacheMode.REQUEST_FAILED_READ_CACHE, new JiongtuCallback<JiongtuSectionListEntity>() {
            @Override
            public void send(boolean isFromCache, JiongtuSectionListEntity entity, Request request, @Nullable Response response) {
                view.render(entity);
            }

            @Override
            public void onError(boolean isFromCache, Call send, @Nullable Response response, @Nullable Exception e) {

            }
        });

    }
}