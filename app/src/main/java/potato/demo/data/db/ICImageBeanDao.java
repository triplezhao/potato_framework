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
import potato.demo.data.bean.ICImageBean;


/**
 * create by freemaker
 */
public class ICImageBeanDao {
    public static final String TAG = "ICImageBeanDao";
    public static final String TABLE_NAME = "ICImageBeanTB";
    public SQLiteOpenHelper mOpenHelper;
    public String[] allkeyjection = new String[]{
            Columns._ID,
            Columns.image_id,
            Columns.image_cid,
            Columns.image_name,
            Columns.image_pic,
            Columns.image_url,
            Columns.image_des,
            Columns.image_content,
            Columns.image_status,
            Columns.listorder

    };

    public ICImageBeanDao(Context context) {
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
                + Columns.image_id + " text, "
                + Columns.image_cid + " text, "
                + Columns.image_name + " text, "
                + Columns.image_pic + " text, "
                + Columns.image_url + " text, "
                + Columns.image_des + " text, "
                + Columns.image_content + " text, "
                + Columns.image_status + " text, "
                + Columns.listorder + " text "
                + ");");
    }

    public long insert(ICImageBean bean) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        ContentValues values = ICImageBean.bean2CV(bean);
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

    public void update(String columnsName, ICImageBean bean) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        ContentValues values = ICImageBean.bean2CV(bean);
        String selection = columnsName + " = ?";
        String[] selectionArgs = {"id"};

        db.update(TABLE_NAME, values, selection, selectionArgs);
        db.close();
    }

    public ICImageBean getICImageBeanByKey(String columnsName, String key) {
        ICImageBean bean = null;
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        String selection = columnsName + " = ?";
        String[] selectionArgs = {key};
        Cursor c = db.query(
                TABLE_NAME, allkeyjection, selection, selectionArgs, null, null, null);
        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                bean = ICImageBean.cursor2Bean(c);
                break;
            }
        }
        if (c != null) {
            c.close();
        }
        db.close();
        return bean;
    }

    public List<ICImageBean> getICImageBeanList() {
        List<ICImageBean> list = new ArrayList<ICImageBean>();
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        Cursor c = db.query(
                TABLE_NAME, allkeyjection, null, null, null, null, null);
        if (c != null && c.getCount() > 0) {
            ICImageBean bean = null;
            while (c.moveToNext()) {
                bean = ICImageBean.cursor2Bean(c);
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
        public static final String image_id = "image_id";
        public static final String image_cid = "image_cid";
        public static final String image_name = "image_name";
        public static final String image_pic = "image_pic";
        public static final String image_url = "image_url";
        public static final String image_des = "image_des";
        public static final String image_content = "image_content";
        public static final String image_status = "image_status";
        public static final String listorder = "listorder";
    }

}

