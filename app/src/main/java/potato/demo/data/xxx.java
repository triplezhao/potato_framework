package potato.demo.data;

//import

import android.content.ContentValues;
import android.database.Cursor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import potato.demo.chips.base.BaseBean;

public class xxx extends BaseBean implements Serializable {

    //keypslist
    private String id;
    private String name;
    private String level;
    private String status;
    private String parentId;
    private String orderNum;
    private String optional;

    //other
    //createFromCursor
    public static xxx cursor2Bean(Cursor cursor) {
        if (cursor == null) return null;
        xxx bean = new xxx();
        bean.setId(cursor.getString(cursor.getColumnIndex(xxxDao.Columns.id)));
        bean.setName(cursor.getString(cursor.getColumnIndex(xxxDao.Columns.name)));
        bean.setLevel(cursor.getString(cursor.getColumnIndex(xxxDao.Columns.level)));
        bean.setStatus(cursor.getString(cursor.getColumnIndex(xxxDao.Columns.status)));
        bean.setParentId(cursor.getString(cursor.getColumnIndex(xxxDao.Columns.parentId)));
        bean.setOrderNum(cursor.getString(cursor.getColumnIndex(xxxDao.Columns.orderNum)));
        bean.setOptional(cursor.getString(cursor.getColumnIndex(xxxDao.Columns.optional)));
        return bean;
    }

    //createFromJSON
    public static xxx json2Bean(JSONObject json) throws JSONException {
        if (json == null) return null;
        xxx bean = new xxx();
        bean.setId(json.optString("id"));
        bean.setName(json.optString("name"));
        bean.setLevel(json.optString("level"));
        bean.setStatus(json.optString("status"));
        bean.setParentId(json.optString("parentId"));
        bean.setOrderNum(json.optString("orderNum"));
        bean.setOptional(json.optString("optional"));
        return bean;
    }

    //createFromJSONArray
    public static ArrayList<xxx> jsonArray2BeanList(JSONArray jsonArray) throws JSONException {

        if (jsonArray == null) return null;

        ArrayList<xxx> list = new ArrayList<xxx>();

        int count = jsonArray.length();
        for (int i = 0; i < count; i++) {
            JSONObject jsonObj = jsonArray.optJSONObject(i);
            xxx entity = xxx.json2Bean(jsonObj);
            list.add(entity);
        }
        return list;
    }

    //buildContentValues
    public static ContentValues bean2CV(BaseBean baseBean) {
        xxx bean = new xxx();

        if (baseBean != null) {
            bean = (xxx) baseBean;
        }
        ContentValues values = new ContentValues();
        values.put(xxxDao.Columns.id, bean.getId());
        values.put(xxxDao.Columns.name, bean.getName());
        values.put(xxxDao.Columns.level, bean.getLevel());
        values.put(xxxDao.Columns.status, bean.getStatus());
        values.put(xxxDao.Columns.parentId, bean.getParentId());
        values.put(xxxDao.Columns.orderNum, bean.getOrderNum());
        values.put(xxxDao.Columns.optional, bean.getOptional());
        return values;
    }

    public String getId() {
        return this.id;
    }

    //set get list
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getOrderNum() {
        return this.orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOptional() {
        return this.optional;
    }

    public void setOptional(String optional) {
        this.optional = optional;
    }

}