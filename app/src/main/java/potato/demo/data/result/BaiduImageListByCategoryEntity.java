package potato.demo.data.result;

import org.json.JSONArray;
import org.json.JSONObject;

import potato.demo.chips.api.BaiduCallback;
import potato.demo.data.bean.BaiduImageBean;

public class BaiduImageListByCategoryEntity extends BaiduCallback.BaiduResultEntity {

    public int startIndex;
    public int returnNumber;

    @Override
    public BaiduCallback.BaiduResultEntity parse(String json) {
        try {
            JSONObject root = new JSONObject(json);

            code = 0;

            if (root.has("error")) {
                code = root.optJSONObject("error").optInt("code");
                message = root.optJSONObject("error").optString("description");
            }

            total = root.optInt("totalNum");
            startIndex = root.optInt("startIndex");
            returnNumber = root.optInt("returnNumber");
            JSONArray array = root.getJSONArray("imgs");
            list = BaiduImageBean.jsonArray2BeanList(array);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            code = -1;
        }
        return this;
    }
}
/*
        "col": "美女",
        "tag": "全部",
        "tag3": "",
        "sort": "0",
        "totalNum": 16851,
        "startIndex": 20,
        "returnNumber": 20,
        "imgs": [
        {
        "id": "9408299999",
        "desc": "90后校花变身花仙子 妩媚撩人秒杀宅男_高清图集_新浪网",
        "tags": [
        "校花"*/
