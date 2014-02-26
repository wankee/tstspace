package com.refactech.driibo.dao;

import com.refactech.driibo.type.dribble.Category;
import com.refactech.driibo.type.dribble.Device;
import com.refactech.driibo.type.dribble.Shot;
import com.refactech.driibo.util.database.Column;
import com.refactech.driibo.util.database.SQLiteTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.content.CursorLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Issac on 7/18/13.
 */
public class DevicesDataHelper extends BaseDataHelper {
    private Category mCategory;

    public DevicesDataHelper(Context context, Category category) {
        super(context);
        mCategory = category;
    }

    @Override
    protected Uri getContentUri() {
        return DeviceProvider.DEVICES_CONTENT_URI;
    }

    private ContentValues getContentValues(Device device) {
        ContentValues values = new ContentValues();
        values.put(DevicesDBInfo.ID, device.getId());
        values.put(DevicesDBInfo.CATEGORY, mCategory.ordinal());
        values.put(DevicesDBInfo.JSON, device.toJson());
        return values;
    }

    public Device query(long id) {
        Device device = null;
        Cursor cursor = query(null, DevicesDBInfo.CATEGORY + "=?" + " AND " + DevicesDBInfo.ID + "= ?",
                new String[] {
                        String.valueOf(mCategory.ordinal()), String.valueOf(id)
                }, null);
        if (cursor.moveToFirst()) {
            device = Device.fromCursor(cursor);
        }
        cursor.close();
        return device;
    }

    public void bulkInsert(List<Device> devices) {
    	System.out.println("In bulkInsert");
        ArrayList<ContentValues> contentValues = new ArrayList<ContentValues>();
        for (Device device : devices) {
        	//System.out.println("shot:"+shot);
            ContentValues values = getContentValues(device);
            //System.out.println("ContentValues:"+values);
            contentValues.add(values);
        }
        ContentValues[] valueArray = new ContentValues[contentValues.size()];
        bulkInsert(contentValues.toArray(valueArray));
    }

    public int deleteAll() {
    	System.out.println("in delete all");
        synchronized (DeviceProvider.DBLock) {
            DeviceProvider.DBHelper mDBHelper = DeviceProvider.getDBHelper();
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            int row = db.delete(DevicesDBInfo.TABLE_NAME, DevicesDBInfo.CATEGORY + "=?", new String[] {
                String.valueOf(mCategory.ordinal())
            });
            return row;
        }
    }

    public CursorLoader getCursorLoader() {
        return new CursorLoader(getContext(), getContentUri(), null, DevicesDBInfo.CATEGORY + "=?",
                new String[] {
                    String.valueOf(mCategory.ordinal())
                }, DevicesDBInfo._ID + " ASC");
    }

    public static final class DevicesDBInfo implements BaseColumns {
        private DevicesDBInfo() {
        }

        public static final String TABLE_NAME = "devices";

        public static final String ID = "id";

        public static final String CATEGORY = "category";

        public static final String JSON = "json";

        public static final SQLiteTable TABLE = new SQLiteTable(TABLE_NAME)
                .addColumn(ID, Column.DataType.INTEGER)
                .addColumn(CATEGORY, Column.DataType.INTEGER).addColumn(JSON, Column.DataType.TEXT);
    }
}
