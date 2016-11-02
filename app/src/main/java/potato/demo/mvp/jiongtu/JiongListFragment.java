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

package potato.demo.mvp.jiongtu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.potato.library.adapter.PotatoBaseRecyclerViewAdapter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import potato.demo.R;
import potato.demo.chips.base.BaseDefaultListFragment;
import potato.demo.chips.common.PageCtrl;
import potato.demo.chips.util.ImageLoaderUtil;
import potato.demo.data.bean.JiongtuAlbum;

public class JiongListFragment extends BaseDefaultListFragment implements JiongList.V {
    private static final String TAG = "ListFragmentJiongtu";
    /**
     * extrars
     */
    public static final String EXTRARS_SECTION_ID = "EXTRARS_SECTION_ID";
    public static final String EXTRARS_TITLE = "EXTRARS_TITLE";
    public long mSectionId;
    public String mTitle;
    @Inject JiongListPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mSectionId = getArguments() == null ? 0 : getArguments().getLong(EXTRARS_SECTION_ID);
        mTitle = getArguments() == null ? "" : getArguments().getString(EXTRARS_TITLE);

        View view = inflater.inflate(R.layout.fragment_jiongtu_list, container, false);

        ButterKnife.bind(this, view);
        DaggerJiongList_C.builder().module(new JiongList.Module(this)).build().inject(this);

        initListView();
        reqRefresh();

        return view;
    }

    @Override
    public PotatoBaseRecyclerViewAdapter getAdapter() {
        return mAdapter = new AAdapter(mContext);
    }

    @Override
    public void reqRefresh() {
        mSwipeContainer.showProgress();
        mPage = 1;
        presenter.reqRefresh();
    }

    @Override
    public void reqLoadMore() {
        presenter.reqLoadMore(0);
    }

    @Override
    public long getSectionId() {
        return mSectionId;
    }


    @Override
    public void onClick(View v) {

    }

    public static class AAdapter extends PotatoBaseRecyclerViewAdapter<AAdapter.VH> {

        public AAdapter(Context context) {
            super(context);
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(
                    R.layout.item_jiongtu_list,
                    parent,
                    false);
            VH holder = new VH(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(VH vh, int position) {
            final JiongtuAlbum bean = (JiongtuAlbum) mData.get(position);

            vh.tv_title.setText(bean.getTitle());
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    PageCtrl.startJiongTuDetailActivity(context, bean);
                }
            });
            ImageLoaderUtil.displayImage(bean.getBigCover(), vh.iv_pic, R.drawable.def_gray_big);

        }


        static class VH extends RecyclerView.ViewHolder {

            @Bind(R.id.iv_pic)
            ImageView iv_pic;
            @Bind(R.id.tv_title)
            TextView tv_title;

            public VH(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
