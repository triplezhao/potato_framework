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
import potato.demo.data.db.ICImageBeanDao;

public class ICImageBean extends BaseBean implements Serializable {

    //keypslist
    private String image_id;
    private String image_cid;
    private String image_name;
    private String image_pic;
    private String image_url;
    private String image_des;
    private String image_content;
    private String image_status;
    private String listorder;

    //other
    //createFromCursor
    public static ICImageBean cursor2Bean(Cursor cursor) {
        if (cursor == null) return null;
        ICImageBean bean = new ICImageBean();
        bean.setImage_id(cursor.getString(cursor.getColumnIndex(ICImageBeanDao.Columns.image_id)));
        bean.setImage_cid(cursor.getString(cursor.getColumnIndex(ICImageBeanDao.Columns.image_cid)));
        bean.setImage_name(cursor.getString(cursor.getColumnIndex(ICImageBeanDao.Columns.image_name)));
        bean.setImage_pic(cursor.getString(cursor.getColumnIndex(ICImageBeanDao.Columns.image_pic)));
        bean.setImage_url(cursor.getString(cursor.getColumnIndex(ICImageBeanDao.Columns.image_url)));
        bean.setImage_des(cursor.getString(cursor.getColumnIndex(ICImageBeanDao.Columns.image_des)));
        bean.setImage_content(cursor.getString(cursor.getColumnIndex(ICImageBeanDao.Columns.image_content)));
        bean.setImage_status(cursor.getString(cursor.getColumnIndex(ICImageBeanDao.Columns.image_status)));
        bean.setListorder(cursor.getString(cursor.getColumnIndex(ICImageBeanDao.Columns.listorder)));
        return bean;
    }

    //createFromJSON
    public static ICImageBean json2Bean(JSONObject json) throws JSONException {
        if (json == null) return null;
        ICImageBean bean = new ICImageBean();
        bean.setImage_id(json.optString("image_id"));
        bean.setImage_cid(json.optString("image_cid"));
        bean.setImage_name(json.optString("image_name"));
        bean.setImage_pic(json.optString("image_pic"));
        bean.setImage_url(json.optString("image_url"));
        bean.setImage_des(json.optString("image_des"));
        bean.setImage_content(json.optString("image_content"));
        bean.setImage_status(json.optString("image_status"));
        bean.setListorder(json.optString("listorder"));
        return bean;
    }

    //createFromJSONArray
    public static ArrayList<ICImageBean> jsonArray2BeanList(JSONArray jsonArray) throws JSONException {

        if (jsonArray == null) return null;

        ArrayList<ICImageBean> list = new ArrayList<ICImageBean>();

        int count = jsonArray.length();
        for (int i = 0; i < count; i++) {
            JSONObject jsonObj = jsonArray.optJSONObject(i);
            ICImageBean entity = ICImageBean.json2Bean(jsonObj);
            list.add(entity);
        }
        return list;
    }

    //buildContentValues
    public static ContentValues bean2CV(BaseBean baseBean) {
        ICImageBean bean = new ICImageBean();

        if (baseBean != null) {
            bean = (ICImageBean) baseBean;
        }
        ContentValues values = new ContentValues();
        values.put(ICImageBeanDao.Columns.image_id, bean.getImage_id());
        values.put(ICImageBeanDao.Columns.image_cid, bean.getImage_cid());
        values.put(ICImageBeanDao.Columns.image_name, bean.getImage_name());
        values.put(ICImageBeanDao.Columns.image_pic, bean.getImage_pic());
        values.put(ICImageBeanDao.Columns.image_url, bean.getImage_url());
        values.put(ICImageBeanDao.Columns.image_des, bean.getImage_des());
        values.put(ICImageBeanDao.Columns.image_content, bean.getImage_content());
        values.put(ICImageBeanDao.Columns.image_status, bean.getImage_status());
        values.put(ICImageBeanDao.Columns.listorder, bean.getListorder());
        return values;
    }

    public String getImage_id() {
        return this.image_id;
    }

    //set get list
    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getImage_cid() {
        return this.image_cid;
    }

    public void setImage_cid(String image_cid) {
        this.image_cid = image_cid;
    }

    public String getImage_name() {
        return this.image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getImage_pic() {
        return this.image_pic;
    }

    public void setImage_pic(String image_pic) {
        this.image_pic = image_pic;
    }

    public String getImage_url() {
        return this.image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_des() {
        return this.image_des;
    }

    public void setImage_des(String image_des) {
        this.image_des = image_des;
    }

    public String getImage_content() {
        return this.image_content;
    }

    public void setImage_content(String image_content) {
        this.image_content = image_content;
    }

    public String getImage_status() {
        return this.image_status;
    }

    public void setImage_status(String image_status) {
        this.image_status = image_status;
    }

    public String getListorder() {
        return this.listorder;
    }

    public void setListorder(String listorder) {
        this.listorder = listorder;
    }

}