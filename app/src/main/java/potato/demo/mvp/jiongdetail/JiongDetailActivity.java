package potato.demo.mvp.jiongdetail;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.potato.library.adapter.PotatoBaseListAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import potato.demo.R;
import potato.demo.chips.base.BaseActivity;
import potato.demo.data.bean.JiongtuAlbum;
import potato.demo.data.bean.JiongtuPhoto;
import potato.demo.data.result.JiongtuPhotoListEntity;

/**
 * Created by ztw on 2016/6/2.
 */
public class JiongDetailActivity extends BaseActivity implements JiongDetail.V {

    public static final String TAG = JiongDetailActivity.class.getSimpleName();
    /** extrars */
    public static final String EXTRA_ALBUM = "EXTRAS_ALBUM";
    /** adapters */
    public PotatoBaseListAdapter mAdapter;
    /** 图片数据集合 */
    public ArrayList<JiongtuPhoto> mPhotos;
    public JiongtuAlbum mCurrentAlbum;
    public String mAlbumId;

    @Bind(R.id.lv_list) ListView lv_list;
    @Bind(R.id.tv_title) TextView tv_title;

    @Inject JiongDetailPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiongtu_detail);
        ButterKnife.bind(this);

        DaggerJiongDetail_C.builder().module(new JiongDetail.Module(this)).build().inject(this);
        mCurrentAlbum = (JiongtuAlbum) getIntent().getSerializableExtra(EXTRA_ALBUM);
        if (mCurrentAlbum != null) {// 来自囧图图册列表
            mAlbumId = String.valueOf(mCurrentAlbum.getId());
            tv_title.setText(mCurrentAlbum.getTitle());
        }
        mAdapter = new JiongDetailAdapter(mContext);
        mPhotos = new ArrayList<>();
        mAdapter.setDataList(mPhotos);
        lv_list.setAdapter(mAdapter);

        presenter.loadData(mAlbumId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void renderDetail(JiongtuPhotoListEntity jiongtuPhotoListEntity) {
        mPhotos = jiongtuPhotoListEntity.list;
        mAdapter.setDataList(mPhotos);
        mAdapter.notifyDataSetChanged();
    }
}
