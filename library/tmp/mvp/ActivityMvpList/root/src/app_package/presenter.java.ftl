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
    public void reqRefresh() {
        JiongtuApi.getAlbumListRequest(CacheMode.REQUEST_FAILED_READ_CACHE, view.getSectionId(), 0, new JiongtuCallback<JiongtuAlbumListEntity>() {
            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {

                if (e != null)
                    view.onRefreshFail(e.getMessage());
            }

            @Override
            public void onResponse(boolean isFromCache, JiongtuAlbumListEntity entity, Request request, @Nullable Response response) {
                view.onRefreshSucc(entity);
            }
        });
    }

    @Override
    public void reqLoadMore(int page) {
        JiongtuApi.getAlbumListRequest(CacheMode.DEFAULT, view.getSectionId(), page, new JiongtuCallback<JiongtuAlbumListEntity>() {
            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                if (e != null)
                    view.onLoadMoreFail(e.getMessage());
            }

            @Override
            public void onResponse(boolean isFromCache, JiongtuAlbumListEntity entity, Request request, @Nullable Response response) {
                view.onLoadMoreSucc(entity);

            }
        });
    }

   

}