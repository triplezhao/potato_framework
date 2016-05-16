package com.potato.library.net;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.util.HashMap;
import java.util.Map;


public abstract class RequestWraper {

    public com.squareup.okhttp.Request request;

    public static final int REQ_METHOD_GET = 1;
    public static final int REQ_METHOD_POST = 2;

    /**
     * 可能是拼接处来的。比如：/rest/cont/strategy/channel/{0} 这里的参数不是?后面的。
     */
    public String url;
    public Map<String, Object> params = new HashMap<String, Object>();
    protected Map<String, String> headers = new HashMap<String, String>();
    public String body;
    /**
     * post或者get等
     */
    public int reqMethod;

    protected Request buildRequest() {
        Headers okHeaders = Headers.of(this.headers);
        //获取带有参数列表的 url，第二个参数是 忽略的参数，比如vts等，这里不能忽略。只有在缓存逻辑中会忽略vts等
        String paramedUrl = RequestUtil.getParamedUrl(this, null);
        if (reqMethod == REQ_METHOD_GET) {
            request = new Request.Builder().headers(okHeaders).url(paramedUrl).get().build();
        } else if (reqMethod == REQ_METHOD_POST) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain; charset=utf-8"), this.body);
            request = new Request.Builder().headers(okHeaders).url(paramedUrl).post(requestBody).build();
        }
        return request;
    }

    /**
     * 返回这个请求的缓存 key
     */
    public abstract String getCacheKey();
}


