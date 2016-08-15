package potato.demo.data.result;

import org.json.JSONArray;
import org.json.JSONObject;

import potato.demo.chips.api.YKCallback;
import potato.demo.data.bean.YKCategoryBean;

public class YKVideoCategoryEntity extends YKCallback.YKResultEntity {


    @Override
    public YKVideoCategoryEntity parse(String json) {
        try {
            JSONObject root = new JSONObject(json);

            code = 0;

            if (root.has("error")) {
                code = root.optJSONObject("error").optInt("code");
                message = root.optJSONObject("error").optString("description");
            }

            JSONArray array = root.getJSONArray("categories");
            list = YKCategoryBean.jsonArray2BeanList(array);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            code = -1;
        }
        return this;
    }
}

/*{
        "categories" :
        [
        {
        "term" : "Sports",
        "label" : "体育",
        "lang" : "zh_CN"
        }
        ...
        ]
        }
  */
