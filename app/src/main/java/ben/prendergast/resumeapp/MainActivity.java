package ben.prendergast.resumeapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import ben.prendergast.resumeapp.fragments.HomeFragment;
import ben.prendergast.resumeapp.fragments.MaterialFragment;
import ben.prendergast.resumeapp.utils.UnitTest;


public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup the header button
        findViewById(R.id.menuClickable).setOnClickListener(this);

        // Setup the menu buttons
        findViewById(R.id.homeMenuOption).setOnClickListener(this);
        findViewById(R.id.materialMenuOption).setOnClickListener(this);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.contentLayout, new HomeFragment());
        transaction.commit();

        mUnitTests.execute(null, null);
    }

    @Override
    public void onClick(View v) {
        DrawerLayout drawerContainer = (DrawerLayout)findViewById(R.id.drawerContainer);
        switch(v.getId()) {
            case R.id.menuClickable:
                if(drawerContainer.isDrawerOpen(Gravity.LEFT)) {
                    drawerContainer.closeDrawer(Gravity.LEFT);
                } else {
                    drawerContainer.openDrawer(Gravity.LEFT);
                }
                break;
            case R.id.homeMenuOption:
                drawerContainer.closeDrawer(Gravity.LEFT);
                gotoHome();
                break;
            case R.id.materialMenuOption:
                drawerContainer.closeDrawer(Gravity.LEFT);
                gotoMaterial();
                break;
        }
    }

    //**********************************************************************************************
    //********************************Functions for fragment navigation*****************************
    //**********************************************************************************************
    /**
     * Clears the fragment stack, effectively going to the home screen.
     */
    public void gotoHome() {
        getFragmentManager().popBackStackImmediate(R.id.contentLayout, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void gotoMaterial() {
        addFragment(new MaterialFragment());
    }

    private void addFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.animator.slide_in, R.animator.slide_in,
                R.animator.slide_in, R.animator.slide_in);
        transaction.addToBackStack(fragment.getClass().getName());
        transaction.add(R.id.contentLayout, fragment);
        transaction.commit();
    }
    //**********************************************************************************************
    //**********************************************************************************************
    /**
     *
     */
    AsyncTask<Object, Object, Object> mUnitTests = new AsyncTask<Object, Object, Object>() {

        @Override
        protected Object doInBackground(Object... params) {
            return UnitTest.testSQLFunctions(MainActivity.this);
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            // We failed our SQL Unit test.  Show a simple error.
            if(!(Boolean)o) {
                Toast.makeText(MainActivity.this, R.string.failed_unit_test_sql,
                        Toast.LENGTH_LONG).show();
            }
        }
    };
}
