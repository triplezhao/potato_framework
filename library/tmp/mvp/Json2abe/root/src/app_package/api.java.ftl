<#assign text>${json}</#assign>
<#assign jsonobj=text?eval />
<#assign keys = jsonobj?keys> 

package ${packageName};

import com.lzy.okhttputils.cache.CacheMode;
import com.lzy.okhttputils.model.HttpParams;


public class ${objName}Api extends ${app_name}Api{
    /*public  void req(CacheMode cacheMode,String id,final DetailViewPage view) {
    //请求回来后使用的解析器
    ${app_name}Callback callback = new  ${app_name}Callback<${objName}Entity>() {
        @Override
        public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
            if (e != null)
                 view.onReqDetailFail(e.getMessage());
            else {
                  view.onReqDetailFail("fail");
            }
        }
        @Override
        public void onResponse(boolean isFromCache, ${objName}Entity entity, Request request, @Nullable Response response) {
                 view.onReqDetailSucc(entity);
            }
    };*/

    public  void req(CacheMode cacheMode,String id, final String page, String pagesize, final ListViewPage view) {
     //请求回来后使用的解析器
     ${app_name}Callback callback = new  ${app_name}Callback<${objName}Entity>() {
            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                    //if (e != null) {
                    //      view.onRefreshFail(e.getMessage());
                    // }
                if (e != null) {
                    if (page.equals("0") || page.equals("1")) {
                            view.onRefreshFail(e.getMessage());
                    } else {
                         view.onLoadMoreFail(e.getMessage());
                    }
                }
            }

            @Override
            public void onResponse(boolean isFromCache, ${objName}Entity entity, Request request, @Nullable Response response) {
                //view.onRefreshSucc(entity);
                if (page.equals("0") || page.equals("1")) {
                      view.onRefreshSucc(entity);
                } else {
                      view.onLoadMoreSucc(entity);
                }
            }
        };
        //请求地址
        String url = url_${objName?lower_case};
        //请求回来后使用的解析器
        callback.setEntity(new ${objName}Entity());
        //参数列表
        HttpParams map = new HttpParams();
        map.put("id", id);
        map.put("page", page);
        map.put("pagesize", pagesize);
        //登录的话，添加uid和userid
        if (!addUid(map, callback)) return;
        baseReq(url, map, callback, cacheMode);
    }
}