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
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lzy.okhttputils.cache.CacheMode;
import com.potato.library.adapter.PotatoBaseListAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import potato.demo.R;
import potato.demo.chips.api.JiongtuCallback;
import potato.demo.chips.base.BaseActivity;
import potato.demo.data.bean.JiongtuAlbum;
import potato.demo.data.bean.JiongtuPhoto;
import potato.demo.data.result.JiongtuPhotoListEntity;
import potato.demo.data.request.JiongtuApi;

public class JiongTuDetailActivity extends BaseActivity {

    public static final String TAG = "JiongTuDetailActivity";

    /**
     * extrars
     */
    public static final String EXTRA_ALBUM = "EXTRAS_ALBUM";
    /**
     * adapters
     */
    private PotatoBaseListAdapter mAdapter;
    /** data */
    /**
     * 图片数据集合
     */
    private ArrayList<JiongtuPhoto> mPhotos;
    private JiongtuAlbum mCurrentAlbum;
    private String mAlbumId;

    @Bind(R.id.lv_list)
    ListView lv_list;
    @Bind(R.id.tv_title)
    TextView tv_title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiongtu_detail);
        ButterKnife.bind(this);
        mCurrentAlbum = (JiongtuAlbum) getIntent().getSerializableExtra(EXTRA_ALBUM);
        if (mCurrentAlbum != null) {// 来自囧图图册列表
            mAlbumId = String.valueOf(mCurrentAlbum.getId());
            tv_title.setText(mCurrentAlbum.getTitle());
        }

        mAdapter = new JiongTuDetailAdapter(mContext);
        mPhotos = new ArrayList<>();
        mAdapter.setDataList(mPhotos);
        lv_list.setAdapter(mAdapter);

        bindData();

    }


    public void bindData() {
        if (!TextUtils.isEmpty(mAlbumId)) {// 来自囧图图册列表
            JiongtuApi.getPhotoListRequest(CacheMode.REQUEST_FAILED_READ_CACHE, mAlbumId, new JiongtuCallback<JiongtuPhotoListEntity>() {
                @Override
                public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {

                }

                @Override
                public void onResponse(boolean isFromCache, JiongtuPhotoListEntity baseResultEntity, Request request, @Nullable Response response) {
                    onRefreshSucc( baseResultEntity);
                }
            });
        }
    }


    private void onRefreshSucc(JiongtuPhotoListEntity jiongtuPhotoListEntity) {
        mPhotos = jiongtuPhotoListEntity.list;
        mAdapter.setDataList(mPhotos);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
