package potato.demo.data.result;

import org.json.JSONArray;
import org.json.JSONObject;

import potato.demo.chips.api.QICallback;
import potato.demo.data.bean.QIRingCatBean;

public class QIRingCatEntity extends QICallback.QIResultEntity {

   public int nowpage = 1;

    @Override
    public QICallback.QIResultEntity parse(String json) {
        try {
            JSONObject root = new JSONObject(json);

            code = root.optInt("code");
            message = root.optString("msg");

            if (isSucc()){
                JSONArray array = root.getJSONArray("data");
                list = QIRingCatBean.jsonArray2BeanList(array);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            code = -1;
        }
        return this;
    }
}
