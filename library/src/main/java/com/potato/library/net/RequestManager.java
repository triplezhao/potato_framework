package com.potato.library.net;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.potato.library.util.L;
import com.potato.library.util.NetUtil;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ztw tianwuzhao@cyou-inc.com
 * @ClassName: RequestManager
 * @Description: TODO(这里只保存激活方法、发出请求方法、和缓存逻辑。方法都是静态调用，context使用的appContext，使用时候传递act等上下文)
 * @date 2015-4-8 下午7:45:53
 */
public class RequestManager {
    private static final String TAG = "RequestManager";

    private static final int TIMEOUT = 5 * 1000;
    public static final String DEF_CONTENTTYPE = "application/json";

    //缓存相关
    public static final int DEFAULT_REQ_TIME_SPACE = 30 * 60 * 1000; // 两次相同请求间隔，30分钟

    public static final int CACHE_TYPE_NORMAL = 500; // 先加载缓存数据，如果缓存时间大于半小时，则请求网络
    public static final int CACHE_TYPE_IGNORE_TIME = 501; // 先加载缓存数据，然后请求网络
    public static final int CACHE_TYPE_FIRST_REQUEST = 502; // 先加载缓存数据，如果是启动应用后的第一次请求，则请求网络
    public static final int CACHE_TYPE_NETWORK_FIRST = 503; // 先请求网络，如果失败，则加载缓存数据
    public static final int CACHE_TYPE_NOCACHE = 504; // 只加载网络数据
    public static final int CACHE_TYPE_CACHEONLY = 505; // 只加载缓存数据

    // 缓存数据的时间，客户端退出是清空此map
    private static Map<String, Long> mCacheTimes = new HashMap<String, Long>();
    private static String[] mProjection = new String[]{RequestCacheProvider.Columns._ID,
            RequestCacheProvider.Columns.responseStr,
            RequestCacheProvider.Columns.time};

    private static Context mContext;
    private static OkHttpClient asyncHttpClient;
    private static Handler mDelivery;

    //只能在Application中做初始化
    public static RequestManager init(Context cxt) {

        if (cxt instanceof Activity) {
            mContext = ((Activity) cxt).getApplicationContext();
        } else if (cxt != null) {
            mContext = cxt;
        }
        mDelivery = new Handler(Looper.getMainLooper());
        if (asyncHttpClient == null) {
            asyncHttpClient = new OkHttpClient();
        }
        return new RequestManager();
    }

    public static void requestData(RequestWraper request, int cacheType, DataLoadListener dataListener) {
        requestData(request, cacheType, DEFAULT_REQ_TIME_SPACE, dataListener);
    }

    public static void requestData(RequestWraper request,
                                   int cacheType, int cacheTimeOutSeconds, DataLoadListener dataListener) {
        requestDataBasic(request, dataListener, cacheType,
                cacheTimeOutSeconds);
    }


    private static void requestDataBasic(RequestWraper request,
                                         final DataLoadListener dataListener, final int cacheType,
                                         int cacheTimeoutSeconds) {
        final String url = request.url;
        final String bodyContent = request.body;

        String selection = RequestUtil.getCacheSelection(request);

        final String paramedUrl = RequestUtil.getParamedUrl(request, null);

        L.d(TAG, "RequestData, url: " + paramedUrl + ", bodyContent: "
                + bodyContent + ", loadCache: " + cacheType + ", selection: "
                + selection);

        final String cacheKey = request.getCacheKey();

        long cacheTime = 0;
        String cacheResult = null;
        boolean hasCache = false;

        // 从缓存中取数据
        RequestCacheDAO.Cache cache = new RequestCacheDAO(mContext).getCacheByKey(request.getCacheKey());
        if (cache != null) {
            hasCache = true;
            cacheTime = cache.time;
            cacheResult = cache.responseStr;
        }
        dataListener.setCacheTime(cacheTime);

        if (hasCache) {
            if (cacheType == CACHE_TYPE_NORMAL) {
                L.d(TAG, "Return result from cache, url: " + paramedUrl
                        + ", cacheType: " + cacheType + ", result: "
                        + cacheResult);
                dataListener.onCacheLoaded(cacheResult);

                if (System.currentTimeMillis() - cacheTime < cacheTimeoutSeconds)
                    return;

            } else if (cacheType == CACHE_TYPE_IGNORE_TIME) {
                L.d(TAG, "Return result from cache, url: " + url
                        + ", cacheType: " + cacheType + ", result: "
                        + cacheResult);
                dataListener.onCacheLoaded(cacheResult);
            } else if (cacheType == CACHE_TYPE_FIRST_REQUEST) {
                L.d(TAG, "Return result from cache, url: " + url
                        + ", cacheType: " + cacheType + ", result: "
                        + cacheResult);
                dataListener.onCacheLoaded(cacheResult);
                if (mCacheTimes.get(cacheKey) != null
                        && mCacheTimes.get(cacheKey) > 0) {
                    L.d(TAG,
                            "This request has been responded from server, do not send the request again");
                    return;
                }
            } else if (cacheType == CACHE_TYPE_CACHEONLY) {
                L.d(TAG, "Return result from cache, url: " + url
                        + ", cacheType: " + cacheType + ", result: "
                        + cacheResult);
                dataListener.onCacheLoaded(cacheResult);
                return;
            }
        }

        if (cacheType == CACHE_TYPE_CACHEONLY)
            return;

        if (cacheType == CACHE_TYPE_NOCACHE) {
            dataListener.setShouldSaveCache(false);
        }

        final String cacheContent = cacheResult;
        final boolean isInCache = hasCache;
        Callback responseHandler = new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e) {
                L.d(TAG, "Request failed, url: " + url + ", error: " + e.getMessage());
                mDelivery.post(new Runnable() {
                    @Override
                    public void run() {
                        String content = e.getMessage();
                        dataListener.onFailure(e, content);
                        if (isInCache && cacheType == CACHE_TYPE_NETWORK_FIRST) {
                            dataListener.onCacheLoaded(cacheContent);
                        }
                    }
                });
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                final String responseBody = response.body().string();
                mDelivery.post(new Runnable() {
                    @Override
                    public void run() {
                        String content = new String(responseBody);
                        if (TextUtils.isEmpty(content)) {
                            dataListener.onFailure(null, content);
                            return;
                        }
                        if (response.code() != 200) {
                            dataListener.onFailure(null, content);
                            return;
                        }
                        if (!dataListener.onPreCheck(content)) {
                            dataListener.onFailure(null, content);
                            return;
                        }

                        dataListener.onSuccess(response.code(), content);

                        if (!dataListener.isShouldSaveCache()) {
                            L.d(TAG,
                                    "Return result from server without saving in cache, url: "
                                            + paramedUrl + ", cacheType: " + cacheType
                                            + ", result: " + content);
                            return;
                        }
                        L.d(TAG, "Return result from server and save in cache, url: "
                                + paramedUrl + ", cacheType: " + cacheType + ", result: "
                                + content);

                        long curTime = System.currentTimeMillis();
                        mCacheTimes.put(cacheKey, curTime);

                        RequestCacheDAO.Cache cache1 = new RequestCacheDAO.Cache();
                        cache1.requestStr = cacheKey;
                        cache1.responseStr = content;
                        cache1.time = curTime;
                        new RequestCacheDAO(mContext).insert(cache1);
                    }
                });

            }
        };


        // 网络不可用，不发送网络请求
        if (!NetUtil.isNetworkAvailable(mContext)) {
            dataListener.onFailure(new Exception(
                    "Network not avilable!"), "Network not avilable!");
            if (hasCache && cacheType == CACHE_TYPE_NETWORK_FIRST) {
                dataListener.onCacheLoaded(cacheContent);
            }
            return;
        }

        //根据Request中配置的 Request方式发送请求。默认有一种，如果用户有特殊要求，则可以执行自定义的请求方式，
        //比如畅言用的自定义CyanClient.getInstance().requestData(request.reqType, request.body,responseHandler);。
//        request.doRequest(responseHandler, dataListener, cacheType, cacheTimeoutSeconds);
        asyncHttpClient.newCall(request.buildRequest()).enqueue(responseHandler);
    }

    public static void clearCacheTime() {
        mCacheTimes.clear();
    }

    public static long getCacheTime(RequestWraper request) {

        String cacheKey = request.getCacheKey();
        if (mCacheTimes.containsKey(cacheKey)) {
            return mCacheTimes.get(cacheKey);
        }

        String selection = RequestUtil.getCacheSelection(request);
        long time = -1;

        Cursor cursor = mContext.getContentResolver().query(
                RequestCacheProvider.CONTENT_URI,
                new String[]{RequestCacheProvider.Columns.time}, selection,
                null, null);

        if (cursor != null && cursor.moveToFirst()) {
            time = cursor.getLong(cursor
                    .getColumnIndex(RequestCacheProvider.Columns.time));
        }
        if (cursor != null)
            cursor.close();
        return time;
    }


    /**
     * 异步请求的回调接口，成功得到响应时回调onSuccess函数，请求失败时回调onFailure函数
     */
    public static abstract class DataLoadListener {
        private long id;
        private long cacheTime;

        private boolean shouldSaveCache = true;

        public boolean isShouldSaveCache() {
            return shouldSaveCache;
        }

        public void setShouldSaveCache(boolean shouldSaveCache) {
            this.shouldSaveCache = shouldSaveCache;
        }

        protected long getId() {
            return id;
        }

        protected void setId(long id) {
            this.id = id;
        }

        public long getCacheTime() {
            return cacheTime;
        }

        public void setCacheTime(long cacheTime) {
            this.cacheTime = cacheTime;
        }

        /**
         * 缓存数据加载完毕会回调此方法
         *
         * @param content
         */
        public abstract void onCacheLoaded(String content);

        /**
         * 网络数据加载完毕会回调此方法
         */
        public abstract void onSuccess(int statusCode, String content);

        /**
         * 加载网络数据失败完毕会回调此方法
         */
        public abstract void onFailure(Throwable error, String errMsg);

        public boolean onPreCheck(String content) {
            if (TextUtils.isEmpty(content))
                return true;
            try {
                JSONObject json = new JSONObject(content);
                if (json.has("error_code")) {
                    return false;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }


}
