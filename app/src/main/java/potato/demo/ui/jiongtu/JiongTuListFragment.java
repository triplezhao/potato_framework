/*
 * Copyright (C) 2015 The Android Open Source Project
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

package potato.demo.ui.jiongtu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lzy.okhttputils.cache.CacheMode;
import com.potato.library.adapter.PotatoBaseRecyclerViewAdapter;
import com.potato.library.view.hfrecyclerview.HFGridlayoutSpanSizeLookup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import potato.demo.R;
import potato.demo.chips.api.BaseResultEntity;
import potato.demo.chips.api.JiongtuCallback;
import potato.demo.chips.base.BaseDefaultListFragment;
import potato.demo.data.bean.JiongtuAlbum;
import potato.demo.data.parser.JiongtuAlbumListEntity;
import potato.demo.data.request.JiongtuApi;

public class JiongTuListFragment extends BaseDefaultListFragment {
    private static final String TAG = "ListFragmentJiongtu";
    /**
     * extrars
     */
    public static final String EXTRARS_SECTION_ID = "EXTRARS_SECTION_ID";
    public static final String EXTRARS_TITLE = "EXTRARS_TITLE";
    private long mSectionId;
    private String mTitle;
    private ArrayList<JiongtuAlbum> mList = new ArrayList<JiongtuAlbum>();
    private PotatoBaseRecyclerViewAdapter mAdapter;
    private JiongtuAlbumListEntity mEntity;
    private View mView;

    @Bind(R.id.include_a)
    LinearLayout include_a;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mSectionId = getArguments() == null ? 0 : getArguments().getLong(EXTRARS_SECTION_ID);
        mTitle = getArguments() == null ? "" : getArguments().getString(EXTRARS_TITLE);

        mView = inflater.inflate(
                R.layout.fragment_jiongtu_list,
                container,
                false);

        ButterKnife.bind(this, mView);

        mAdapter = new JiongTuListAdapter(mContext);
        mEntity = new JiongtuAlbumListEntity();

        initListView(include_a);

        GridLayoutManager manager = new GridLayoutManager(mContext, 2);
        manager.setSpanSizeLookup(new HFGridlayoutSpanSizeLookup(mSwipeContainer.getHFAdapter(), manager.getSpanCount()) {
            @Override
            public int getSpanSize(int position) {
                boolean isHeaderOrFooter = adapter.isHeader(position) || adapter.isFooter(position);
                if (isHeaderOrFooter) {
                    return mSpanSize;
                } else if (position % 3 == 0) {
                    return mSpanSize;
                } else {
                    return 1;
                }
            }
        });
        mSwipeContainer.setLayoutManager(manager);
        mSwipeContainer.showProgress();
        reqRefresh();

        return mView;
    }

    @Override
    public PotatoBaseRecyclerViewAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void reqRefresh() {
        JiongtuApi.getAlbumListRequest(CacheMode.REQUEST_FAILED_READ_CACHE, mSectionId, 0, new JiongtuCallback<JiongtuAlbumListEntity>() {
            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {

                if (e != null)
                    onRefreshFail(e.getMessage());
            }

            @Override
            public void onResponse(boolean isFromCache, BaseResultEntity entity, Request request, @Nullable Response response) {
                onRefreshSucc((JiongtuAlbumListEntity) entity);
            }
        });
    }

    @Override
    public void reqLoadMore() {
        JiongtuApi.getAlbumListRequest(CacheMode.DEFAULT, mSectionId, mEntity.maxPublicDate, new JiongtuCallback<JiongtuAlbumListEntity>() {
            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                if (e != null)
                    onLoadMoreFail(e.getMessage());
            }

            @Override
            public void onResponse(boolean isFromCache, BaseResultEntity entity, Request request, @Nullable Response response) {
                onLoadMoreSucc((JiongtuAlbumListEntity) entity);


            }
        });
    }


    public void onRefreshSucc(JiongtuAlbumListEntity entity) {
        mEntity = entity;
        mSwipeContainer.showSucc();
        mList = entity.list;
        mAdapter.setDataList(mList);
        mAdapter.notifyDataSetChanged();
        mSwipeContainer.setRefreshing(false);
        if (mList != null && mList.size() != 0) {
            mSwipeContainer.setLoadEnable(true);
        }

    }

    public void onLoadMoreSucc(JiongtuAlbumListEntity entity) {
        mEntity = entity;
        mSwipeContainer.setLoading(false);
        ArrayList<JiongtuAlbum> moreData = entity.list;
        if (moreData == null || moreData.size() == 0) {
            mSwipeContainer.setLoadEnable(false);
            return;
        }
        mList.addAll(moreData);
        mAdapter.setDataList(mList);
        mAdapter.notifyItemInserted(mList.size() - 1);
    }

    @Override
    public void onClick(View view) {

    }
}
