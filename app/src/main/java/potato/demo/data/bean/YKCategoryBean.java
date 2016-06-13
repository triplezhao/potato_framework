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
import potato.demo.data.db.YKCategoryBeanDao;

public class YKCategoryBean extends BaseBean implements Serializable {

    //keypslist
    private String term;
    private String label;
    private String lang;

    //other
    //createFromCursor
    public static YKCategoryBean cursor2Bean(Cursor cursor) {
        if (cursor == null) return null;
        YKCategoryBean bean = new YKCategoryBean();
        bean.setTerm(cursor.getString(cursor.getColumnIndex(YKCategoryBeanDao.Columns.term)));
        bean.setLabel(cursor.getString(cursor.getColumnIndex(YKCategoryBeanDao.Columns.label)));
        bean.setLang(cursor.getString(cursor.getColumnIndex(YKCategoryBeanDao.Columns.lang)));
        return bean;
    }

    //createFromJSON
    public static YKCategoryBean json2Bean(JSONObject json) throws JSONException {
        if (json == null) return null;
        YKCategoryBean bean = new YKCategoryBean();
        bean.setTerm(json.optString("term"));
        bean.setLabel(json.optString("label"));
        bean.setLang(json.optString("lang"));
        return bean;
    }

    //createFromJSONArray
    public static ArrayList<YKCategoryBean> jsonArray2BeanList(JSONArray jsonArray) throws JSONException {

        if (jsonArray == null) return null;

        ArrayList<YKCategoryBean> list = new ArrayList<YKCategoryBean>();

        int count = jsonArray.length();
        for (int i = 0; i < count; i++) {
            JSONObject jsonObj = jsonArray.optJSONObject(i);
            YKCategoryBean entity = YKCategoryBean.json2Bean(jsonObj);
            list.add(entity);
        }
        return list;
    }

    //buildContentValues
    public static ContentValues bean2CV(BaseBean baseBean) {
        YKCategoryBean bean = new YKCategoryBean();

        if (baseBean != null) {
            bean = (YKCategoryBean) baseBean;
        }
        ContentValues values = new ContentValues();
        values.put(YKCategoryBeanDao.Columns.term, bean.getTerm());
        values.put(YKCategoryBeanDao.Columns.label, bean.getLabel());
        values.put(YKCategoryBeanDao.Columns.lang, bean.getLang());
        return values;
    }

    public String getTerm() {
        return this.term;
    }

    //set get list
    public void setTerm(String term) {
        this.term = term;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLang() {
        return this.lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

}