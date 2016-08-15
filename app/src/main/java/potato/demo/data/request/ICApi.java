package potato.demo.data.request;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheMode;

import potato.demo.chips.api.ApiUrls;
import potato.demo.chips.api.ICCallback;
import potato.demo.data.result.ICImageCatEntity;
import potato.demo.data.result.ICImageEntity;

public class ICApi implements ApiUrls {
    //获取用户发布的视频. 命名，取api接口的后两位单词
    public static final String URL_IMAGECATS = BaseICURL;

    /**
     * 分類
     * http://localhost/think/index.php?g=api&m=apiImage&a=getImageCats
     */
    public static void getImageCats(CacheMode cacheMode, ICCallback<ICImageCatEntity> callback) {
        callback.setEntity(new ICImageCatEntity());
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
    public static void getImages(CacheMode cacheMode,String cid,String page, String pageSize, ICCallback<ICImageEntity> callback) {
        callback.setEntity(new ICImageEntity());
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
    public static void getAllImages(CacheMode cacheMode,ICCallback<ICImageEntity> callback) {
        callback.setEntity(new ICImageEntity());
        OkHttpUtils.get(URL_IMAGECATS)//
                .tag("getAllImages")//
                .cacheKey("getAllImages"+URL_IMAGECATS)//
                .cacheMode(cacheMode)//
                .params("a", "getAllImages")
                .params("g", "api")
                .params("m", "apiImage")
                .execute(callback);

    }

}
