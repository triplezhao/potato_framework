package com.potato.library.net;

import android.text.TextUtils;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.potato.library.net.RequestManager.DataLoadListener;
import com.potato.library.util.NetUtil;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.client.HttpResponseException;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * @author ztw tianwuzhao@cyou-inc.com
 * @ClassName: DefaultRequest
 * @Description: TODO(默认的请求类。用户可以基础，重写请求方法，和key过滤方法。这里过滤方法去掉了vts等参数，给173用。)
 * @date 2015-4-10 下午6:50:38
 */
public class DefaultRequest extends Request {

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

    @Override
    public void doRequest(
            final AsyncHttpResponseHandler responseHandler,
            final DataLoadListener dataListener, final int cacheType,
            final int cacheTimeoutSeconds) {
        // TODO Auto-generated method stub
        /*RequestManager.request(
                RequestUtil.getParamedUrl(DefaultRequest.this, null),
                request.body, responseHandler, request.reqMethod);*/

        String url = RequestUtil.getParamedUrl(DefaultRequest.this, null);

        if (NetUtil.isNetworkAvailable(RequestManager.mContext)) {
            try {
                if (RequestManager.asyncHttpClient == null) {
                    responseHandler.onFailure(0, null, "asyncHttpClient为空".getBytes(), new Throwable("asyncHttpClient为 空"));
                    return;
                }

                HttpEntity httpEntity = null;
                if (!TextUtils.isEmpty(this.body)) {
                    httpEntity = new StringEntity(this.body, HTTP.UTF_8);
                }

                if (this.reqMethod == Request.REQ_METHOD_GET) {
                    RequestManager.asyncHttpClient.get(url, responseHandler);
                } else if (this.reqMethod == Request.REQ_METHOD_POST) {
                    RequestManager.asyncHttpClient.post(null, url, httpEntity, RequestManager.DEF_CONTENTTYPE,
                            responseHandler);
                }

            } catch (Exception e) {
//                e.printStackTrace();
                responseHandler.onFailure(0, null, "entity utf-8 UnsupportedEncodingException".getBytes(), new Throwable(e.getMessage()));
            }
        } else {
            // 表示网络被禁止
            responseHandler.onFailure(0, null, "Network is Forbid 2G/3G,Wifin".getBytes(), new HttpResponseException(9999, "Network is Forbid 2G/3G,Wifi"));
        }

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
