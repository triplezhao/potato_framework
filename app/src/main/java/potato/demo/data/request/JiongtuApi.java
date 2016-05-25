package potato.demo.data.request;


import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheMode;

import potato.demo.chips.api.JiongtuCallback;
import potato.demo.chips.api.ApiUrls;
import potato.demo.data.parser.JiongtuAlbumListEntity;
import potato.demo.data.parser.JiongtuPhotoListEntity;
import potato.demo.data.parser.JiongtuSectionListEntity;


public class JiongtuApi implements ApiUrls {
    //测试地址
    public static final String URL_TEST = BaseURL
            + "/rest/collection/collectGameInfo"; //给服务器发送包名信息
    public static final String URL_JIONG_SECTION_LIST = BaseJIONGURL
            + "/api/section/list"; // 获取囧图栏目列表
    public static final String URL_JIONG_ALBUM_LIST = BaseJIONGURL
            + "/api/photos/list"; // 获取囧图某栏目下图册列表
    public static final String URL_JIONG_PHOTO_LIST = BaseJIONGURL
            + "/api/photos/detail"; // 获取囧图某图册下图片列表

    /**
     * 囧图图册列表请求
     *
     * @param cacheMode
     * @param sectionId     囧图栏目ID
     * @param maxPublicDate 本地已有数据的最早更新时间
     */
    public static void getAlbumListRequest(CacheMode cacheMode, long sectionId, long maxPublicDate, JiongtuCallback<JiongtuAlbumListEntity> callback) {
        callback.setEntity(new JiongtuAlbumListEntity());
        OkHttpUtils.get(URL_JIONG_ALBUM_LIST)//
                .tag("getAlbumListRequest")//
                .cacheKey(URL_JIONG_SECTION_LIST + sectionId)//
                .cacheMode(cacheMode)//
                .params("platCode", "ANDROID")//
                .params("sections", sectionId + "")
                .params("maxPublicDate", maxPublicDate + "")
                .execute(callback);
    }

    /**
     * Code": 0,
     * "Message": "操作成功",
     * "Data":{
     * "Total": 6,
     * "List":[
     *
     * @param cacheMode
     * @param callback
     */
    public static void getSectionListRequest(CacheMode cacheMode, JiongtuCallback<JiongtuSectionListEntity> callback) {

        //设置存储结果的实体
        callback.setEntity(new JiongtuSectionListEntity());
        OkHttpUtils.get(URL_JIONG_SECTION_LIST)//
                .tag("getSectionListRequest")//
                .cacheKey(URL_JIONG_SECTION_LIST)//
                .cacheMode(cacheMode)//
                .params("platCode", "ANDROID")//
                .params("pageSize", "-1")
                .execute(callback);

    }

    public static void getPhotoListRequest(CacheMode cacheMode, String albumId, JiongtuCallback<JiongtuPhotoListEntity> callback) {
        //设置存储结果的实体
        callback.setEntity(new JiongtuPhotoListEntity());
        OkHttpUtils.get(URL_JIONG_PHOTO_LIST)//
                .tag("getPhotoListRequest")//
                .cacheKey(URL_JIONG_PHOTO_LIST + albumId)//
                .cacheMode(cacheMode)//
                .params("platCode", "ANDROID")//
                .params("photosID", albumId)
                .execute(callback);
    }
}
