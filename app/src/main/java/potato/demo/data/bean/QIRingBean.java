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
import potato.demo.data.db.QIRingBeanDao;

public class QIRingBean extends BaseBean implements Serializable {

    //keypslist
    private String ring_id;
    private String ring_cid;
    private String ring_name;
    private String ring_pic;
    private String ring_url;
    private String ring_des;
    private String ring_content;
    private String ring_status;
    private String listorder;
    private String ring_playcnt;
    private String ring_artist;
    private String ring_duration;

    //other
    //createFromCursor
    public static QIRingBean cursor2Bean(Cursor cursor) {
        if (cursor == null) return null;
        QIRingBean bean = new QIRingBean();
        bean.setRing_id(cursor.getString(cursor.getColumnIndex(QIRingBeanDao.Columns.ring_id)));
        bean.setRing_cid(cursor.getString(cursor.getColumnIndex(QIRingBeanDao.Columns.ring_cid)));
        bean.setRing_name(cursor.getString(cursor.getColumnIndex(QIRingBeanDao.Columns.ring_name)));
        bean.setRing_pic(cursor.getString(cursor.getColumnIndex(QIRingBeanDao.Columns.ring_pic)));
        bean.setRing_url(cursor.getString(cursor.getColumnIndex(QIRingBeanDao.Columns.ring_url)));
        bean.setRing_des(cursor.getString(cursor.getColumnIndex(QIRingBeanDao.Columns.ring_des)));
        bean.setRing_content(cursor.getString(cursor.getColumnIndex(QIRingBeanDao.Columns.ring_content)));
        bean.setRing_status(cursor.getString(cursor.getColumnIndex(QIRingBeanDao.Columns.ring_status)));
        bean.setListorder(cursor.getString(cursor.getColumnIndex(QIRingBeanDao.Columns.listorder)));
        bean.setRing_playcnt(cursor.getString(cursor.getColumnIndex(QIRingBeanDao.Columns.ring_playcnt)));
        bean.setRing_artist(cursor.getString(cursor.getColumnIndex(QIRingBeanDao.Columns.ring_artist)));
        bean.setRing_duration(cursor.getString(cursor.getColumnIndex(QIRingBeanDao.Columns.ring_duration)));
        return bean;
    }

    //createFromJSON
    public static QIRingBean json2Bean(JSONObject json) throws JSONException {
        if (json == null) return null;
        QIRingBean bean = new QIRingBean();
        bean.setRing_id(json.optString("ring_id"));
        bean.setRing_cid(json.optString("ring_cid"));
        bean.setRing_name(json.optString("ring_name"));
        bean.setRing_pic(json.optString("ring_pic"));
        bean.setRing_url(json.optString("ring_url"));
        bean.setRing_des(json.optString("ring_des"));
        bean.setRing_content(json.optString("ring_content"));
        bean.setRing_status(json.optString("ring_status"));
        bean.setListorder(json.optString("listorder"));
        bean.setRing_playcnt(json.optString("ring_playcnt"));
        bean.setRing_artist(json.optString("ring_artist"));
        bean.setRing_duration(json.optString("ring_duration"));
        return bean;
    }

    //createFromJSONArray
    public static ArrayList<QIRingBean> jsonArray2BeanList(JSONArray jsonArray) throws JSONException {

        if (jsonArray == null) return null;

        ArrayList<QIRingBean> list = new ArrayList<QIRingBean>();

        int count = jsonArray.length();
        for (int i = 0; i < count; i++) {
            JSONObject jsonObj = jsonArray.optJSONObject(i);
            QIRingBean entity = QIRingBean.json2Bean(jsonObj);
            list.add(entity);
        }
        return list;
    }

    //buildContentValues
    public static ContentValues bean2CV(BaseBean baseBean) {
        QIRingBean bean = new QIRingBean();

        if (baseBean != null) {
            bean = (QIRingBean) baseBean;
        }
        ContentValues values = new ContentValues();
        values.put(QIRingBeanDao.Columns.ring_id, bean.getRing_id());
        values.put(QIRingBeanDao.Columns.ring_cid, bean.getRing_cid());
        values.put(QIRingBeanDao.Columns.ring_name, bean.getRing_name());
        values.put(QIRingBeanDao.Columns.ring_pic, bean.getRing_pic());
        values.put(QIRingBeanDao.Columns.ring_url, bean.getRing_url());
        values.put(QIRingBeanDao.Columns.ring_des, bean.getRing_des());
        values.put(QIRingBeanDao.Columns.ring_content, bean.getRing_content());
        values.put(QIRingBeanDao.Columns.ring_status, bean.getRing_status());
        values.put(QIRingBeanDao.Columns.listorder, bean.getListorder());
        values.put(QIRingBeanDao.Columns.ring_playcnt, bean.getRing_playcnt());
        values.put(QIRingBeanDao.Columns.ring_artist, bean.getRing_artist());
        values.put(QIRingBeanDao.Columns.ring_duration, bean.getRing_duration());
        return values;
    }

    public String getRing_id() {
        return this.ring_id;
    }

    //set get list
    public void setRing_id(String ring_id) {
        this.ring_id = ring_id;
    }

    public String getRing_cid() {
        return this.ring_cid;
    }

    public void setRing_cid(String ring_cid) {
        this.ring_cid = ring_cid;
    }

    public String getRing_name() {
        return this.ring_name;
    }

    public void setRing_name(String ring_name) {
        this.ring_name = ring_name;
    }

    public String getRing_pic() {
        return this.ring_pic;
    }

    public void setRing_pic(String ring_pic) {
        this.ring_pic = ring_pic;
    }

    public String getRing_url() {
        return this.ring_url;
    }

    public void setRing_url(String ring_url) {
        this.ring_url = ring_url;
    }

    public String getRing_des() {
        return this.ring_des;
    }

    public void setRing_des(String ring_des) {
        this.ring_des = ring_des;
    }

    public String getRing_content() {
        return this.ring_content;
    }

    public void setRing_content(String ring_content) {
        this.ring_content = ring_content;
    }

    public String getRing_status() {
        return this.ring_status;
    }

    public void setRing_status(String ring_status) {
        this.ring_status = ring_status;
    }

    public String getListorder() {
        return this.listorder;
    }

    public void setListorder(String listorder) {
        this.listorder = listorder;
    }

    public String getRing_playcnt() {
        return this.ring_playcnt;
    }

    public void setRing_playcnt(String ring_playcnt) {
        this.ring_playcnt = ring_playcnt;
    }

    public String getRing_artist() {
        return this.ring_artist;
    }

    public void setRing_artist(String ring_artist) {
        this.ring_artist = ring_artist;
    }

    public String getRing_duration() {
        return this.ring_duration;
    }

    public void setRing_duration(String ring_duration) {
        this.ring_duration = ring_duration;
    }

}