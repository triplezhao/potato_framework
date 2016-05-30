package potato.demo.data.result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import potato.demo.chips.api.BaseResultEntity;
import potato.demo.chips.api.JiongtuResultEntity;
import potato.demo.data.bean.JiongtuAlbum;

/**
 * 囧图-图册列表数据解析器
 *
 * @author zhaobingfeng
 */
public class JiongtuAlbumListEntity extends JiongtuResultEntity {

    public long minPublicDate;//最近的时间
    //	public long maxPublicDate=System.currentTimeMillis();//最远的时间
    public long maxPublicDate = Long.MAX_VALUE;//最远的时间
    public long total;    //数据总条数

    public void reset() {
        minPublicDate = 0l;
        maxPublicDate = Long.MAX_VALUE;
    }

    @Override
    public BaseResultEntity parse(String json) {
        list = new ArrayList<JiongtuAlbum>();
        try {
            JSONObject obj = new JSONObject(json);
            code = obj.optInt("Code");
            if (!obj.isNull("Data")) {//Data不为null时
                JSONObject objData = obj.getJSONObject("Data");
                JSONArray array = objData.getJSONArray("List");
                total = objData.optLong("Total");
                if (array != null) {
                    int count = array.length();
//					L.e("计算开始：minPublicDate="+minPublicDate+",maxPublicDate="+maxPublicDate);
                    for (int i = 0; i < count; i++) {
                        JSONObject jsonObj = array.optJSONObject(i);
                        if (jsonObj.has("AD") && jsonObj.getBoolean("AD")) {//如果AD字段为true,忽略该图册
                            continue;
                        }
                        JiongtuAlbum entity = JiongtuAlbum.createFromJSON(jsonObj);
                        list.add(entity);
                        long publicDate = entity.getPublicDate();
//						L.d("publicDate="+publicDate);
                        if (publicDate > minPublicDate) {//最近的时间
                            minPublicDate = publicDate;
                        }
                        if (publicDate < maxPublicDate) {//最远的时间
                            maxPublicDate = publicDate;
                        }
                    }
//					L.e("计算结束：minPublicDate="+minPublicDate+",maxPublicDate="+maxPublicDate);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }
}
