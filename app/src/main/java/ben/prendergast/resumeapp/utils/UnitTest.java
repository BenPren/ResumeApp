package ben.prendergast.resumeapp.utils;

import android.content.Context;
import android.database.Cursor;

import ben.prendergast.resumeapp.data.SQLDatabaseHandler;

/**
 * Created by Ben on 6/9/2015.
 */
public class UnitTest {

    /**
     * Tests all functions withing the {@link: SQLDatabaseHandler}
     *
     * @param context
     * @return
     */
    public static boolean testSQLFunctions(Context context) {
        try {
            SQLDatabaseHandler handler = new SQLDatabaseHandler(context);
            // Reasonably sure this will never be entered...
            String itemName = "test" + System.currentTimeMillis();
            // Add test
            if (!handler.addOrUpdateItem(itemName, 10)) {
                handler.close();
                return false;
            }
            // Getting the count so we can compare for the delete
            Cursor cursor = handler.getAllItems();
            int beforeDeleteCount = cursor.getCount();
            cursor.close();
            // Ensure the query succeeded
            if (beforeDeleteCount < 1) {
                handler.close();
                return false;
            }
            // Delete test
            if(!handler.deleteItem(itemName)) {
                handler.close();
                return false;
            }
            cursor = handler.getAllItems();
            int afterDeleteCount = cursor.getCount();
            cursor.close();
            // Ensure only one was removed
            if (beforeDeleteCount - afterDeleteCount != 1) {
                handler.close();
                return false;
            }
            handler.close();
        }catch(Exception e) {
            // General exception failure
            e.printStackTrace();
            return false;
        }
        // Success!
        return true;
    }
}
