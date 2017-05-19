<#assign text>${json}</#assign>
<#assign jsonobj=text?eval />
<#assign keys = jsonobj?keys> 

package ${packageName};

import com.lzy.okhttputils.cache.CacheMode;
import com.lzy.okhttputils.model.HttpParams;


public class ${objName}Api extends ${app_name}Api{
    public static void req(CacheMode cacheMode,String id,String page,String pagesize, ${app_name}Callback<${objName}Entity> callback) {
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
