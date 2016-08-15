package potato.demo.data.bean;

//import

import android.content.ContentValues;
import android.database.Cursor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import potato.demo.chips.base.BaseBean;
import potato.demo.data.db.QIRingCatBeanDao;

public class QIRingCatBean extends BaseBean implements Serializable {

    //keypslist
    private String cid;
    private String cat_name;
    private String cat_idname;
    private String cat_remark;
    private String cat_status;

    //other
    //createFromCursor
    public static QIRingCatBean cursor2Bean(Cursor cursor) {
        if (cursor == null) return null;
        QIRingCatBean bean = new QIRingCatBean();
        bean.setCid(cursor.getString(cursor.getColumnIndex(QIRingCatBeanDao.Columns.cid)));
        bean.setCat_name(cursor.getString(cursor.getColumnIndex(QIRingCatBeanDao.Columns.cat_name)));
        bean.setCat_idname(cursor.getString(cursor.getColumnIndex(QIRingCatBeanDao.Columns.cat_idname)));
        bean.setCat_remark(cursor.getString(cursor.getColumnIndex(QIRingCatBeanDao.Columns.cat_remark)));
        bean.setCat_status(cursor.getString(cursor.getColumnIndex(QIRingCatBeanDao.Columns.cat_status)));
        return bean;
    }

    //createFromJSON
    public static QIRingCatBean json2Bean(JSONObject json) throws JSONException {
        if (json == null) return null;
        QIRingCatBean bean = new QIRingCatBean();
        bean.setCid(json.optString("cid"));
        bean.setCat_name(json.optString("cat_name"));
        bean.setCat_idname(json.optString("cat_idname"));
        bean.setCat_remark(json.optString("cat_remark"));
        bean.setCat_status(json.optString("cat_status"));
        return bean;
    }

    //createFromJSONArray
    public static ArrayList<QIRingCatBean> jsonArray2BeanList(JSONArray jsonArray) throws JSONException {

        if (jsonArray == null) return null;

        ArrayList<QIRingCatBean> list = new ArrayList<QIRingCatBean>();

        int count = jsonArray.length();
        for (int i = 0; i < count; i++) {
            JSONObject jsonObj = jsonArray.optJSONObject(i);
            QIRingCatBean entity = QIRingCatBean.json2Bean(jsonObj);
            list.add(entity);
        }
        return list;
    }

    //buildContentValues
    public static ContentValues bean2CV(BaseBean baseBean) {
        QIRingCatBean bean = new QIRingCatBean();

        if (baseBean != null) {
            bean = (QIRingCatBean) baseBean;
        }
        ContentValues values = new ContentValues();
        values.put(QIRingCatBeanDao.Columns.cid, bean.getCid());
        values.put(QIRingCatBeanDao.Columns.cat_name, bean.getCat_name());
        values.put(QIRingCatBeanDao.Columns.cat_idname, bean.getCat_idname());
        values.put(QIRingCatBeanDao.Columns.cat_remark, bean.getCat_remark());
        values.put(QIRingCatBeanDao.Columns.cat_status, bean.getCat_status());
        return values;
    }

    public String getCid() {
        return this.cid;
    }

    //set get list
    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCat_name() {
        return this.cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCat_idname() {
        return this.cat_idname;
    }

    public void setCat_idname(String cat_idname) {
        this.cat_idname = cat_idname;
    }

    public String getCat_remark() {
        return this.cat_remark;
    }

    public void setCat_remark(String cat_remark) {
        this.cat_remark = cat_remark;
    }

    public String getCat_status() {
        return this.cat_status;
    }

    public void setCat_status(String cat_status) {
        this.cat_status = cat_status;
    }

}