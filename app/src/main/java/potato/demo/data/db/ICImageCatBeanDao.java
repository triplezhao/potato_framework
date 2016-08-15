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
import potato.demo.data.bean.QIImageCatBean;


/**
 * create by freemaker
 */
public class ICImageCatBeanDao {
    public static final String TAG = "ICImageCatBeanDao";
    public static final String TABLE_NAME = "ICImageCatBeanTB";
    public SQLiteOpenHelper mOpenHelper;
    public String[] allkeyjection = new String[]{
            Columns._ID,
            Columns.cid,
            Columns.cat_name,
            Columns.cat_idname,
            Columns.cat_remark,
            Columns.cat_status

    };

    public ICImageCatBeanDao(Context context) {
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
                + Columns.cid + " text, "
                + Columns.cat_name + " text, "
                + Columns.cat_idname + " text, "
                + Columns.cat_remark + " text, "
                + Columns.cat_status + " text "
                + ");");
    }

    public long insert(QIImageCatBean bean) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        ContentValues values = QIImageCatBean.bean2CV(bean);
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

    public void update(String columnsName, QIImageCatBean bean) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        ContentValues values = QIImageCatBean.bean2CV(bean);
        String selection = columnsName + " = ?";
        String[] selectionArgs = {"id"};

        db.update(TABLE_NAME, values, selection, selectionArgs);
        db.close();
    }

    public QIImageCatBean getICImageCatBeanByKey(String columnsName, String key) {
        QIImageCatBean bean = null;
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        String selection = columnsName + " = ?";
        String[] selectionArgs = {key};
        Cursor c = db.query(
                TABLE_NAME, allkeyjection, selection, selectionArgs, null, null, null);
        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                bean = QIImageCatBean.cursor2Bean(c);
                break;
            }
        }
        if (c != null) {
            c.close();
        }
        db.close();
        return bean;
    }

    public List<QIImageCatBean> getICImageCatBeanList() {
        List<QIImageCatBean> list = new ArrayList<QIImageCatBean>();
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        Cursor c = db.query(
                TABLE_NAME, allkeyjection, null, null, null, null, null);
        if (c != null && c.getCount() > 0) {
            QIImageCatBean bean = null;
            while (c.moveToNext()) {
                bean = QIImageCatBean.cursor2Bean(c);
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
        public static final String cid = "cid";
        public static final String cat_name = "cat_name";
        public static final String cat_idname = "cat_idname";
        public static final String cat_remark = "cat_remark";
        public static final String cat_status = "cat_status";
    }

}

