package potato.demo.data.request;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheMode;
import com.lzy.okhttputils.callback.StringCallback;

import potato.demo.chips.api.ApiUrls;
import potato.demo.chips.api.QICallback;
import potato.demo.data.result.QIImageCatEntity;
import potato.demo.data.result.QIImageEntity;
import potato.demo.data.result.QIRingCatEntity;
import potato.demo.data.result.QIRingEntity;

public class QIApi implements ApiUrls {
    //获取用户发布的视频. 命名，取api接口的后两位单词
    public static final String URL_IMAGECATS = BaseICURL;
    //版本更新
    public static final String url_version = "http://api.fir.im/apps/latest/";
    /**
     * 分類
     * http://localhost/think/index.php?g=api&m=apiImage&a=getImageCats
     */
    public static void getImageCats(CacheMode cacheMode, QICallback<QIImageCatEntity> callback) {
        callback.setEntity(new QIImageCatEntity());
        OkHttpUtils.get(URL_IMAGECATS)//
                .tag("getImageCats")//
                .cacheKey("getImageCats"+URL_IMAGECATS )//
                .cacheMode(cacheMode)//
                .params("a", "getImageCats")
                .params("g", "api")
                .params("m", "apiImage")
                .execute(callback);

    }
    /**
     * 圖片列表  按分類id
     * http://localhost/think/index.php?g=api&m=apiImage&a=getImages&page=1&pagesize=5&cid=1
     */
    public static void getImages(CacheMode cacheMode,String cid,String page, String pageSize, QICallback<QIImageEntity> callback) {
        callback.setEntity(new QIImageEntity());
        OkHttpUtils.get(URL_IMAGECATS)//
                .tag("getImages")//
                .cacheKey("getImages"+cid+URL_IMAGECATS + page + pageSize )//
                .cacheMode(cacheMode)//
                .params("cid", cid)
                .params("page", page)
                .params("pagesize", pageSize)
                .params("a", "getImages")
                .params("g", "api")
                .params("m", "apiImage")
                .execute(callback);

    }
    /**
     * 圖片列表 所有的
     * http://api.platform.ifsee.cn/index.php?g=api&m=apiImage&a=getAllImages&page=1&pagesize=5
     */
    public static void getAllImages(CacheMode cacheMode,QICallback<QIImageEntity> callback) {
        callback.setEntity(new QIImageEntity());
        OkHttpUtils.get(URL_IMAGECATS)//
                .tag("getAllImages")//
                .cacheKey("getAllImages"+URL_IMAGECATS)//
                .cacheMode(cacheMode)//
                .params("a", "getAllImages")
                .params("g", "api")
                .params("m", "apiImage")
                .execute(callback);

    }



    /**
     * 分類
     * http://localhost/think/index.php?g=api&m=apiRing&a=getRingCats
     */
    public static void getRingCats(CacheMode cacheMode, QICallback<QIRingCatEntity> callback) {
        callback.setEntity(new QIRingCatEntity());
        OkHttpUtils.get(URL_IMAGECATS)//
                .tag("getRingCats")//
                .cacheKey("getRingCats"+URL_IMAGECATS )//
                .cacheMode(cacheMode)//
                .params("a", "getRingCats")
                .params("g", "api")
                .params("m", "apiRing")
                .execute(callback);

    }
    /**
     * 圖片列表  按分類id
     * http://localhost/think/index.php?g=api&m=apiRing&a=getRings&page=1&pagesize=5&cid=1
     */
    public static void getRings(CacheMode cacheMode,String cid,String page, String pageSize, QICallback<QIRingEntity> callback) {
        callback.setEntity(new QIRingEntity());
        OkHttpUtils.get(URL_IMAGECATS)//
                .tag("getRings")//
                .cacheKey("getRings"+cid+URL_IMAGECATS + page + pageSize )//
                .cacheMode(cacheMode)//
                .params("cid", cid)
                .params("page", page)
                .params("pagesize", pageSize)
                .params("a", "getRings")
                .params("g", "api")
                .params("m", "apiRing")
                .execute(callback);

    }
    /**
     * 圖片列表 所有的
     * http://api.platform.ifsee.cn/index.php?g=api&m=apiRing&a=getAllRings&page=1&pagesize=5
     */
    public static void getAllRings(CacheMode cacheMode,QICallback<QIRingEntity> callback) {
        callback.setEntity(new QIRingEntity());
        OkHttpUtils.get(URL_IMAGECATS)//
                .tag("getAllRings")//
                .cacheKey("getAllRings"+URL_IMAGECATS)//
                .cacheMode(cacheMode)//
                .params("a", "getAllRings")
                .params("g", "api")
                .params("m", "apiRing")
                .execute(callback);

    }


    //57bfe2d2959d697acf000e43?api_token=ec98c72cd81b87a9d62d095a3b1f10f6&type=android
    public static void checkVersion(CacheMode cacheMode, String id, String api_token, StringCallback callback) {

        OkHttpUtils.get(url_version+id)//
                .tag("url_version")//
                .cacheKey(url_version + id + api_token)//
                .cacheMode(cacheMode)//
                .params("api_token", api_token)
                .params("type", "android")
                .execute(callback);
    }
}
