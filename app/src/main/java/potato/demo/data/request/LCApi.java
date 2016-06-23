package potato.demo.data.request;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheMode;

import potato.demo.chips.api.ApiUrls;
import potato.demo.chips.api.BaiduCallback;
import potato.demo.data.result.BaiduImageListByCategoryEntity;

public class LCApi implements ApiUrls {
    //获取用户发布的视频. 命名，取api接口的后两位单词
    public static final String imageList = BaseBaiduURL;

    /**
     * http://image.baidu.com/data/imgs?col=%E7%BE%8E%E5%A5%B3&tag=%E5%85%A8%E9%83%A8&pn=1&rn=20&from=1
     */
    public static void imageList(CacheMode cacheMode, String col, String startIndex, String pageSize, BaiduCallback<BaiduImageListByCategoryEntity> callback) {
        callback.setEntity(new BaiduImageListByCategoryEntity());
        OkHttpUtils.get(imageList)//
                .tag("imageList")//
                .cacheKey(imageList + col + startIndex + pageSize)//
                .cacheMode(cacheMode)//
                .params("col", col)
                .params("tag", "全部")
                .params("pn", startIndex)
                .params("rn", pageSize)
                .params("from", "1")
                .execute(callback);
    }

}
