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

package potato.demo.mvp.jiongtulist;


import potato.demo.data.result.JiongtuAlbumListEntity;
import potato.demo.mvp.util.BasePresenter;
import potato.demo.mvp.util.BaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
interface JiongListContract {

    interface View extends BaseView {

        void onRefreshSucc(JiongtuAlbumListEntity entity);

        void onRefreshFail(String err);

        void onLoadMoreSucc(JiongtuAlbumListEntity entity);

        void onLoadMoreFail(String err);

        void onCacheLoaded(JiongtuAlbumListEntity entity);

    }

    interface Presenter extends BasePresenter {
        void reqRefresh();

        void reqLoadMore(long maxPublicDate);
    }
}
