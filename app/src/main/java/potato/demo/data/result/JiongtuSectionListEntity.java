package potato.demo.data.result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import potato.demo.chips.api.JiongtuResultEntity;
import potato.demo.data.bean.JiongtuSection;

/**
 * 囧图-栏目列表数据解析器
 *
 * @author zhaobingfeng
 */
public class JiongtuSectionListEntity extends JiongtuResultEntity {


    public  JiongtuSectionListEntity parse(String jsonStr) {
        JiongtuSectionListEntity entity = new JiongtuSectionListEntity();
        try {
            JSONObject obj = new JSONObject(jsonStr);
            entity.code = obj.optInt("Code");
            entity.message = obj.optString("Message");
            if (!obj.isNull("Data")) {
                JSONObject jo = obj.getJSONObject("Data");
                if (jo != null) {
                    entity.list = new ArrayList<JiongtuSection>();
                    JSONObject objData = obj.getJSONObject("Data");
                    entity.total = objData.optInt("Total");
                    JSONArray array = objData.getJSONArray("List");
                    int count = array.length();
                    for (int i = 0; i < count; i++) {
                        JSONObject jsonObj = array.optJSONObject(i);
                        JiongtuSection section = JiongtuSection.createFromJSON(jsonObj);
                        entity.list.add(section);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return entity;
    }

}