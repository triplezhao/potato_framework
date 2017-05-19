
package ${packageName};
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;
import java.util.ArrayList;
/**
 * create by freemaker
 */

public class  ${objName}Entity extends ${app_name}ResultEntity {
    public static final String TAG = "${objName}Entity";

    @Override
    public ${app_name}ResultEntity parse(String json) {
        try {
            JSONObject root = new JSONObject(json);
            code = root.optString("respCode");
            message = root.optString("respMsg");

            JSONObject data = root.optJSONObject("respResult");

            total = data.optInt("count");
            list = new Gson().fromJson(data.optJSONArray("list").toString(),
            new TypeToken<ArrayList<${objName}Bean>>() { }.getType());
            //bean = new Gson().fromJson(data.toString(), ${objName}Bean.class);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }
        return this;
    }

}