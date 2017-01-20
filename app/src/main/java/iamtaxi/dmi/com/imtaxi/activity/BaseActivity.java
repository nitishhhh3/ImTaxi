package iamtaxi.dmi.com.imtaxi.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import iamtaxi.dmi.com.imtaxi.R;

/**
 * Created by Ankit on 20-01-2017.
 *
 * @author Ankit Jindal
 */

public class BaseActivity extends AppCompatActivity {


    public void setUpToolbar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
}
