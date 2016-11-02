/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package potato.demo.mvp.jiongtu;

import android.support.annotation.Nullable;

import com.lzy.okhttputils.cache.CacheMode;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import potato.demo.chips.api.JiongtuCallback;
import potato.demo.data.request.JiongtuApi;
import potato.demo.data.result.JiongtuAlbumListEntity;

/**
 * Listens to user actions from the UI ({@link }), retrieves the data and updates
 * the UI as required.
 * <p/>
 * Dagger generated code doesn't require public access to the constructor or class, and
 * therefore, to ensure the developer doesn't instantiate the class manually and bypasses Dagger,
 * it's good practice minimise the visibility of the class/constructor as much as possible.
 */
final class JiongListPresenter implements JiongList.P {


    private JiongList.V mJiongListView;

    /**
     * Dagger strictly enforces that arguments not marked with {@code @Nullable} are not injected
     * with {@code @Nullable} values.
     */
    @Inject
    JiongListPresenter(
            JiongList.V jiongListView) {
        mJiongListView = jiongListView;
    }


    @Override
    public void start() {

    }
    @Override
    public void reqRefresh() {
        JiongtuApi.getAlbumListRequest(CacheMode.REQUEST_FAILED_READ_CACHE, mJiongListView.getSectionId(), 0, new JiongtuCallback<JiongtuAlbumListEntity>() {
            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {

                if (e != null)
                    mJiongListView.onRefreshFail(e.getMessage());
            }

            @Override
            public void onResponse(boolean isFromCache, JiongtuAlbumListEntity entity, Request request, @Nullable Response response) {
                mJiongListView.onRefreshSucc(entity);
            }
        });
    }

    @Override
    public void reqLoadMore(long maxPublicDate) {
        JiongtuApi.getAlbumListRequest(CacheMode.DEFAULT, mJiongListView.getSectionId(), maxPublicDate, new JiongtuCallback<JiongtuAlbumListEntity>() {
            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                if (e != null)
                    mJiongListView.onLoadMoreFail(e.getMessage());
            }

            @Override
            public void onResponse(boolean isFromCache, JiongtuAlbumListEntity entity, Request request, @Nullable Response response) {
                mJiongListView.onLoadMoreSucc(entity);

            }
        });
    }
}
