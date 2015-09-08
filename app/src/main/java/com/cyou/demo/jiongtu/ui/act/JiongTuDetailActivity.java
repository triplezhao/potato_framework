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

package com.cyou.demo.jiongtu.ui.act;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

import com.cyou.demo.R;
import com.cyou.demo.databinding.ActivityJiongtuDetailBinding;
import com.cyou.demo.jiongtu.data.bean.JiongtuAlbum;
import com.cyou.demo.jiongtu.data.bean.JiongtuPhoto;
import com.cyou.demo.jiongtu.data.parser.JiongtuPhotoListParser;
import com.cyou.demo.jiongtu.data.request.JiongtuRequestBuilder;
import com.cyou.demo.jiongtu.ui.viewbinder.JiongTuDetailViewBinder;
import com.cyou.frame.base.BaseActivity;
import com.cyou.frame.base.BaseListAdapter;
import com.cyou.model.library.net.Request;
import com.cyou.model.library.net.RequestManager;

import java.util.ArrayList;

public class JiongTuDetailActivity extends BaseActivity {

    public static final String TAG = "JiongTuDetailActivity";

    /** extrars */
    public static final String EXTRA_ALBUM = "EXTRAS_ALBUM";
    /** views */
    private ListView lv_list;
    /** adapters */
    private BaseListAdapter mAdapter;
    /** data */
    /** 图片数据集合 */
    private ArrayList<JiongtuPhoto> mPhotos;
    private JiongtuAlbum mCurrentAlbum;
    private String mAlbumId;
    private ActivityJiongtuDetailBinding mBingding;
    /**
     * logic
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         mBingding = DataBindingUtil.setContentView(
                this, R.layout.activity_jiongtu_detail);

        mCurrentAlbum = (JiongtuAlbum) getIntent().getSerializableExtra(EXTRA_ALBUM);
        if (mCurrentAlbum != null) {// 来自囧图图册列表
            mAlbumId = String.valueOf(mCurrentAlbum.getId());
            mBingding.setBean(mCurrentAlbum);
        }


        lv_list = mBingding.lvList;

        mAdapter = new BaseListAdapter(mContext,new JiongTuDetailViewBinder());
        mPhotos = new ArrayList<>();
        mAdapter.setDataList(mPhotos);
        lv_list.setAdapter(mAdapter);

        bindData();

    }


    public void bindData() {
        if (!TextUtils.isEmpty(mAlbumId)) {// 来自囧图图册列表
            Request request = JiongtuRequestBuilder.getPhotoListRequest(mAlbumId);
            RequestManager.requestData(request,
                    new RequestManager.DataLoadListener() {
                        @Override
                        public void onSuccess(int statusCode, String content) {
                            onRefreshSucc(content);
                        }

                        @Override
                        public void onFailure(Throwable error, String errMsg) {
                        }

                        @Override
                        public void onCacheLoaded(String content) {
                            onRefreshSucc(content);
                        }
                    }, RequestManager.CACHE_TYPE_NORMAL);// 使用缓存
        }
    }


    private void onRefreshSucc(String content) {
        mPhotos = JiongtuPhotoListParser.parseToPhotoList(content);
        mAdapter.setDataList(mPhotos);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
