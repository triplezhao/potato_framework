package potato.demo.data.result;

import org.json.JSONArray;
import org.json.JSONObject;

import potato.demo.chips.api.ICCallback;
import potato.demo.data.bean.ICImageBean;

public class ICImageEntity extends ICCallback.ICResultEntity {

    public int nowpage = 1;

    @Override
    public ICCallback.ICResultEntity parse(String json) {
        try {
            JSONObject root = new JSONObject(json);

            code = root.optInt("code");
            message = root.optString("msg");

            if (isSucc()){
                JSONObject data = root.getJSONObject("data");
                total = data.optInt("total");
                nowpage = data.optInt("nowpage");
                JSONArray array = data.getJSONArray("list");
                list = ICImageBean.jsonArray2BeanList(array);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            code = -1;
        }
        return this;
    }
}
