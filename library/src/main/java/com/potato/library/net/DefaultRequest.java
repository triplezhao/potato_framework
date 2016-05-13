package com.potato.library.net;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author ztw tianwuzhao@cyou-inc.com
 * @ClassName: DefaultRequest
 * @Description: TODO(默认的请求类。用户可以基础，重写请求方法，和key过滤方法。这里过滤方法去掉了vts等参数，给173用。)
 * @date 2015-4-10 下午6:50:38
 */
public class DefaultRequest extends RequestWraper {

    private static final String TAG = "DefaultRequest";

    /**
     * 返回这个请求的缓存 key
     */
    @Override
    public String getCacheKey() {
        // url+过滤过的参数列表
        int urlHash = getUrlHash();
        int bodyHash = getBodyHash();

        String cacheKey = urlHash + "_" + bodyHash;

        return cacheKey;
    }

    public int getUrlHash() {
        // TODO Auto-generated method stub
        int hash = -1;
        String url = RequestUtil.getParamedUrl(DefaultRequest.this,
                RequestUtil.getCommonFilteredParams());
        if (!TextUtils.isEmpty(url)) {
            hash = url.hashCode();
        }
        return hash;
    }

    public int getBodyHash() {
        // TODO Auto-generated method stub
        int hash = -1;
        String jsonStr = null;

        if (!TextUtils.isEmpty(body)) {
            try {
                JSONObject json = new JSONObject(body);
                if (json.has("cts")) {
                    json.remove("cts");
                }
                if (json.has("ccts")) {
                    json.remove("ccts");
                }
                if (json.has("vts")) {
                    json.remove("vts");
                }
                jsonStr = json.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (!TextUtils.isEmpty(jsonStr)) {
                hash = jsonStr.hashCode();
            }
        }
        return hash;
    }
}
