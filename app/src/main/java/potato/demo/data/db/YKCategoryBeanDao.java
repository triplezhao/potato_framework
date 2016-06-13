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
import potato.demo.data.bean.YKCategoryBean;


/**
 * create by freemaker
 */
public class YKCategoryBeanDao {
    public static final String TAG = "YKCategoryBeanDao";
    public static final String TABLE_NAME = "YKCategoryBeanTB";
    public SQLiteOpenHelper mOpenHelper;
    public String[] allkeyjection = new String[]{
            Columns._ID,
            Columns.term,
            Columns.label,
            Columns.lang

    };

    public YKCategoryBeanDao(Context context) {
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
                + Columns.term + " text, "
                + Columns.label + " text, "
                + Columns.lang + " text "
                + ");");
    }

    public long insert(YKCategoryBean bean) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        ContentValues values = YKCategoryBean.bean2CV(bean);
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

    public void update(String columnsName, YKCategoryBean bean) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        ContentValues values = YKCategoryBean.bean2CV(bean);
        String selection = columnsName + " = ?";
        String[] selectionArgs = {"id"};

        db.update(TABLE_NAME, values, selection, selectionArgs);
        db.close();
    }

    public YKCategoryBean getYKCategoryBeanByKey(String columnsName, String key) {
        YKCategoryBean bean = null;
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        String selection = columnsName + " = ?";
        String[] selectionArgs = {key};
        Cursor c = db.query(
                TABLE_NAME, allkeyjection, selection, selectionArgs, null, null, null);
        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                bean = YKCategoryBean.cursor2Bean(c);
                break;
            }
        }
        if (c != null) {
            c.close();
        }
        db.close();
        return bean;
    }

    public List<YKCategoryBean> getYKCategoryBeanList() {
        List<YKCategoryBean> list = new ArrayList<YKCategoryBean>();
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        Cursor c = db.query(
                TABLE_NAME, allkeyjection, null, null, null, null, null);
        if (c != null && c.getCount() > 0) {
            YKCategoryBean bean = null;
            while (c.moveToNext()) {
                bean = YKCategoryBean.cursor2Bean(c);
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
        public static final String term = "term";
        public static final String label = "label";
        public static final String lang = "lang";
    }

}

