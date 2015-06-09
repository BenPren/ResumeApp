package ben.prendergast.resumeapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ben on 6/9/2015.
 */
public class SQLDatabaseHandler extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Inventory.db";

    private SQLiteDatabase mDb;

    public SQLDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private void ensureDatabaseIsOpen() {
        if(mDb == null || !mDb.isOpen()) {
            mDb = getWritableDatabase();
        }
    }

    @Override
    public synchronized void close() {
        if(mDb != null && mDb.isOpen()) {
            mDb.close();
        }
        mDb = null;
        super.close();
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(InventoryContract.QUERY_CREATE_INVENTORY_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This means we will lose all data on an upgrade
        db.execSQL(InventoryContract.QUERY_DELETE_INVENTORY_TABLE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /**
     * Either creates a new row with the given information or
     * updates the count of the item with the given name.
     *
     * @param itemName
     * @param itemCount
     * @return
     */
    public boolean addOrUpdateItem(String itemName, int itemCount) {
        ensureDatabaseIsOpen();
        mDb.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(InventoryContract.InventoryColumns.COLUMN_NAME_ITEM_NAME, itemName);
        values.put(InventoryContract.InventoryColumns.COLUMN_NAME_COUNT, itemCount);
        long newRowId = mDb.insertWithOnConflict(InventoryContract.InventoryColumns.TABLE_NAME, null,
                values, SQLiteDatabase.CONFLICT_REPLACE);
        // check the return value from the insert, any non negative number is a success.
        boolean success = newRowId > -1;
        if(success) {
            mDb.setTransactionSuccessful();
        }
        mDb.endTransaction();
        return success;
    }

    /**
     * Returns all items in the database sorted by name.
     *
     * @return
     */
    public Cursor getAllItems() {
        ensureDatabaseIsOpen();
        String sortOrder = InventoryContract.InventoryColumns.COLUMN_NAME_ITEM_NAME + " DESC";
        // Since no set columns were set, we will receive all columns
        Cursor c = mDb.query(InventoryContract.InventoryColumns.TABLE_NAME, null, null, null,
                null, null, sortOrder);
        return c;
    }

    /**
     * Deletes the item with the given name.
     *
     * @param name
     * @return
     */
    public boolean deleteItem(String name) {
        ensureDatabaseIsOpen();
        mDb.beginTransaction();
        // Exact matches only
        String selection = InventoryContract.InventoryColumns.COLUMN_NAME_ITEM_NAME + " = ?";
        // Doing the "" + bit in case someone passes null
        String[] selectionArgs = { "" + name };
        int deleteCount = mDb.delete(InventoryContract.InventoryColumns.TABLE_NAME, selection, selectionArgs);
        // If we deleted anything then return true for a success.
        boolean success = deleteCount > 0;
        if(success) {
            mDb.setTransactionSuccessful();
        }
        mDb.endTransaction();
        return success;
    }
}
