package ben.prendergast.resumeapp.data;

import android.provider.BaseColumns;

/**
 * This defines the rules for the inventory table
 *
 * Created by Ben on 6/9/2015.
 */
public class InventoryContract {

    protected static final String QUERY_CREATE_INVENTORY_TABLE =
            "CREATE TABLE " + InventoryColumns.TABLE_NAME + " (" +
                    InventoryColumns._ID + " INTEGER PRIMARY KEY," +
                    InventoryColumns.COLUMN_NAME_ITEM_NAME + " TEXT UNIQUE NOT NULL, " +
                    InventoryColumns.COLUMN_NAME_COUNT + " INTEGER )";

    protected static final String QUERY_DELETE_INVENTORY_TABLE =
            "DROP TABLE IF EXISTS " + InventoryColumns.TABLE_NAME;

    private InventoryContract() {}

    public static abstract class InventoryColumns implements BaseColumns {

        public static final String TABLE_NAME = "inventoryTable";

        public static final String COLUMN_NAME_ITEM_NAME = "itemName";
        public static final String COLUMN_NAME_COUNT = "count";
    }
}
