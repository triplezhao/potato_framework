package potato.demo.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.potato.library.util.L;

import java.util.ArrayList;
import java.util.List;

import potato.demo.chips.common.DatabaseHelper;
import potato.demo.data.bean.QIRingBean;


/**
 * create by freemaker
 */
public class QIRingBeanDao {
    public static final String TAG = "QIRingBeanDao";
    public static final String TABLE_NAME = "QIRingBeanTB";
    public SQLiteOpenHelper mOpenHelper;
    public String[] allkeyjection = new String[]{
            Columns._ID,
            Columns.ring_id,
            Columns.ring_cid,
            Columns.ring_name,
            Columns.ring_pic,
            Columns.ring_url,
            Columns.ring_des,
            Columns.ring_content,
            Columns.ring_status,
            Columns.listorder,
            Columns.ring_playcnt,
            Columns.ring_artist,
            Columns.ring_duration

    };

    public QIRingBeanDao(Context context) {
        mOpenHelper = DatabaseHelper.getInstance(context);
        L.d(TAG, "In onCreate method, create the keyvider: " + this
                + ", and DatabaseHelper: " + mOpenHelper);
    }

    /**
     * Use this static method to create the table
     * It will be called by {@link DatabaseHelper} during first launch time to create DB.
     *
     * @param db
     */
    public static void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + Columns._ID + " integer primary key autoincrement, "
                + Columns.ring_id + " text, "
                + Columns.ring_cid + " text, "
                + Columns.ring_name + " text, "
                + Columns.ring_pic + " text, "
                + Columns.ring_url + " text, "
                + Columns.ring_des + " text, "
                + Columns.ring_content + " text, "
                + Columns.ring_status + " text, "
                + Columns.listorder + " text, "
                + Columns.ring_playcnt + " text, "
                + Columns.ring_artist + " text, "
                + Columns.ring_duration + " text "
                + ");");
    }

    public long insert(QIRingBean bean) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        ContentValues values = QIRingBean.bean2CV(bean);
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public void delete(String columnsName, String value) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        String selection = columnsName + " = ?";
        String[] selectionArgs = {value};
        db.delete(TABLE_NAME, selection, selectionArgs);
        db.close();
    }

    public void update(String columnsName, QIRingBean bean) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        ContentValues values = QIRingBean.bean2CV(bean);
        String selection = columnsName + " = ?";
        String[] selectionArgs = {"id"};

        db.update(TABLE_NAME, values, selection, selectionArgs);
        db.close();
    }

    public QIRingBean getQIRingBeanByKey(String columnsName, String key) {
        QIRingBean bean = null;
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        String selection = columnsName + " = ?";
        String[] selectionArgs = {key};
        Cursor c = db.query(
                TABLE_NAME, allkeyjection, selection, selectionArgs, null, null, null);
        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                bean = QIRingBean.cursor2Bean(c);
                break;
            }
        }
        if (c != null) {
            c.close();
        }
        db.close();
        return bean;
    }

    public List<QIRingBean> getQIRingBeanList() {
        List<QIRingBean> list = new ArrayList<QIRingBean>();
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        Cursor c = db.query(
                TABLE_NAME, allkeyjection, null, null, null, null, null);
        if (c != null && c.getCount() > 0) {
            QIRingBean bean = null;
            while (c.moveToNext()) {
                bean = QIRingBean.cursor2Bean(c);
                list.add(bean);
            }
        }
        if (c != null) {
            c.close();
        }
        db.close();
        return list;
    }

    public static class Columns implements BaseColumns {
        public static final String ring_id = "ring_id";
        public static final String ring_cid = "ring_cid";
        public static final String ring_name = "ring_name";
        public static final String ring_pic = "ring_pic";
        public static final String ring_url = "ring_url";
        public static final String ring_des = "ring_des";
        public static final String ring_content = "ring_content";
        public static final String ring_status = "ring_status";
        public static final String listorder = "listorder";
        public static final String ring_playcnt = "ring_playcnt";
        public static final String ring_artist = "ring_artist";
        public static final String ring_duration = "ring_duration";
    }

}

