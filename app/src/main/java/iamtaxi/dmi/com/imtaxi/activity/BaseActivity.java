package iamtaxi.dmi.com.imtaxi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import iamtaxi.dmi.com.imtaxi.R;
import iamtaxi.dmi.com.imtaxi.data.AppConstants;

/**
 * Created by Ankit on 20-01-2017.
 *
 * @author Ankit Jindal
 */

public class BaseActivity extends AppCompatActivity implements AppConstants {


    public void setUpToolbar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    /**
     * Method used to switch Activity
     *
     * @param destClass
     * @param bundle
     */
    public void switchActivity(Class<?> destClass, Bundle bundle) {

        Intent intent = new Intent(this, destClass);
        if (bundle != null)
            intent.putExtra(KEY_BUNDLE, bundle);
        startActivity(intent);
    }
}
