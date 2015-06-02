package ben.prendergast.resumeapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ben.prendergast.resumeapp.fragments.HomeFragment;


public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup the header button
        findViewById(R.id.menuClickable).setOnClickListener(this);

        // Setup the menu buttons
        findViewById(R.id.homeMenuOption).setOnClickListener(this);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.contentLayout, new HomeFragment());
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.menuClickable:
                DrawerLayout drawerContainer = (DrawerLayout)findViewById(R.id.drawerContainer);
                break;
            case R.id.homeMenuOption:
                gotoHome();
                break;
        }
    }

    //**********************************************************************************************
    //********************************Functions for fragment navigation*****************************
    //**********************************************************************************************
    public void gotoHome() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        getFragmentManager().popBackStackImmediate(R.id.contentLayout, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
    //**********************************************************************************************
    //**********************************************************************************************
}
