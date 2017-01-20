package iamtaxi.dmi.com.imtaxi.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import iamtaxi.dmi.com.imtaxi.R;
import iamtaxi.dmi.com.imtaxi.utill.AppConstants;

/**
 * Created by Ankit on 20-01-2017.
 *
 * @author Ankit Jindal
 */

public class BaseActivity extends AppCompatActivity implements AppConstants {


    public void setUpToolbar(String title, boolean showBack) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        if (showBack) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }
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

    /**
     * Method used to show AlertDialog
     *
     * @param title
     * @param message
     * @param positiveButtonText
     * @param negativeButtonText
     * @param positiveClickListener
     * @param negativeClickListener
     */
    public void showAlertDialog(String title, String message, String positiveButtonText, String negativeButtonText, DialogInterface.OnClickListener
            positiveClickListener, DialogInterface.OnClickListener negativeClickListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveButtonText, positiveClickListener);
        if (!TextUtils.isEmpty(negativeButtonText))
            builder.setNegativeButton(negativeButtonText, negativeClickListener);
        builder.show();
    }
}
