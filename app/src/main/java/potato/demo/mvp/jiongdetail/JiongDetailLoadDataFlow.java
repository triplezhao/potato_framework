package potato.demo.mvp.jiongdetail;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.lzy.okhttputils.cache.CacheMode;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import potato.demo.chips.api.JiongtuCallback;
import potato.demo.data.request.JiongtuApi;
import potato.demo.data.result.JiongtuPhotoListEntity;

/**
 * Created by ztw on 2016/6/2.
 */
public class JiongDetailLoadDataFlow implements JiongDetailContract.LoadDataFlow {

    JiongDetailActivity activity;

    public JiongDetailLoadDataFlow(JiongDetailActivity activity) {
        this.activity = activity;
    }

    public void loadData(String mAlbumId) {
        if (!TextUtils.isEmpty(mAlbumId)) {// 来自囧图图册列表
            JiongtuApi.getPhotoListRequest(CacheMode.REQUEST_FAILED_READ_CACHE, mAlbumId, new JiongtuCallback<JiongtuPhotoListEntity>() {
                @Override
                public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {

                }

                @Override
                public void onResponse(boolean isFromCache, JiongtuPhotoListEntity baseResultEntity, Request request, @Nullable Response response) {
                    onRefreshSucc(baseResultEntity);
                }
            });
        }
    }


    public void onRefreshSucc(JiongtuPhotoListEntity jiongtuPhotoListEntity) {
        activity.mPhotos = jiongtuPhotoListEntity.list;
        activity.mAdapter.setDataList(activity.mPhotos);
        activity.mAdapter.notifyDataSetChanged();
    }

}
