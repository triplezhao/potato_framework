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

package potato.demo.mvp.jiongdetail;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.lzy.okhttputils.cache.CacheMode;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import potato.demo.chips.api.JiongtuCallback;
import potato.demo.data.request.JiongtuApi;
import potato.demo.data.result.JiongtuPhotoListEntity;
import potato.demo.mvp.jiongtu.JiongHomeActivity;

/**
 * Listens to user actions from the UI ({@link JiongHomeActivity}), retrieves the data and updates
 * the UI as required.
 * <p/>
 * Dagger generated code doesn't require public access to the constructor or class, and
 * therefore, to ensure the developer doesn't instantiate the class manually and bypasses Dagger,
 * it's good practice minimise the visibility of the class/constructor as much as possible.
 */
final class JiongDetailPresenter implements JiongDetail.P {


    private JiongDetail.V view;

    /**
     * Dagger strictly enforces that arguments not marked with {@code @Nullable} are not injected
     * with {@code @Nullable} values.
     */
    @Inject
    JiongDetailPresenter(
            JiongDetail.V view) {
        this.view = view;
    }


    @Override
    public void start() {

    }


    @Override
    public void loadData(String id) {

        if (!TextUtils.isEmpty(id)) {// 来自囧图图册列表
            JiongtuApi.getPhotoListRequest(CacheMode.REQUEST_FAILED_READ_CACHE, id, new JiongtuCallback<JiongtuPhotoListEntity>() {
                @Override
                public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {

                }

                @Override
                public void onResponse(boolean isFromCache, JiongtuPhotoListEntity baseResultEntity, Request request, @Nullable Response response) {
                    view.renderDetail(baseResultEntity);
                }
            });
        }

    }
}
