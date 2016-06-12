package potato.demo.data;

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


/**
 * create by freemaker
 */
public class xxxDao {
    public static final String TAG = "xxxDao";
    public static final String TABLE_NAME = "xxxTB";
    public SQLiteOpenHelper mOpenHelper;
    public String[] allkeyjection = new String[]{
            Columns._ID,
            Columns.id,
            Columns.name,
            Columns.level,
            Columns.status,
            Columns.parentId,
            Columns.orderNum,
            Columns.optional

    };

    public xxxDao(Context context) {
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
                + Columns.id + " text, "
                + Columns.name + " text, "
                + Columns.level + " text, "
                + Columns.status + " text, "
                + Columns.parentId + " text, "
                + Columns.orderNum + " text, "
                + Columns.optional + " text "
                + ");");
    }

    public long insert(xxx bean) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        ContentValues values = xxx.bean2CV(bean);
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

    public void update(String columnsName, xxx bean) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        ContentValues values = xxx.bean2CV(bean);
        String selection = columnsName + " = ?";
        String[] selectionArgs = {"id"};

        db.update(TABLE_NAME, values, selection, selectionArgs);
        db.close();
    }

    public xxx getxxxByKey(String columnsName, String key) {
        xxx bean = null;
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        String selection = columnsName + " = ?";
        String[] selectionArgs = {key};
        Cursor c = db.query(
                TABLE_NAME, allkeyjection, selection, selectionArgs, null, null, null);
        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                bean = xxx.cursor2Bean(c);
                break;
            }
        }
        if (c != null) {
            c.close();
        }
        db.close();
        return bean;
    }

    public List<xxx> getxxxList() {
        List<xxx> list = new ArrayList<xxx>();
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        Cursor c = db.query(
                TABLE_NAME, allkeyjection, null, null, null, null, null);
        if (c != null && c.getCount() > 0) {
            xxx bean = null;
            while (c.moveToNext()) {
                bean = xxx.cursor2Bean(c);
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
        public static final String id = "id";
        public static final String name = "name";
        public static final String level = "level";
        public static final String status = "status";
        public static final String parentId = "parentId";
        public static final String orderNum = "orderNum";
        public static final String optional = "optional";
    }

}

