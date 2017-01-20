package iamtaxi.dmi.com.imtaxi.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import iamtaxi.dmi.com.imtaxi.R;
import iamtaxi.dmi.com.imtaxi.activity.BaseActivity;

/**
 * Created by sahil kumar on 20/01/2017.
 */

public class CabFormActivity extends BaseActivity implements View.OnClickListener {
    private EditText mEmployeeName, mEmployeeId, mContact;
    private EditText mPorjectCode;
    private EditText mDestination;
    private Button submitBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cab_form_activity);
        setUpToolbar(getString(R.string.text_cab_request), true);
        initViews();

    }

    /**
     * Method used to initialise Views from Xml
     */
    private void initViews() {
        mEmployeeName = (EditText) findViewById(R.id.name);
        mEmployeeId = (EditText) findViewById(R.id.id);
        mContact = (EditText) findViewById(R.id.contact_number);
        mPorjectCode = (EditText) findViewById(R.id.bp_code);
        mDestination = (EditText) findViewById(R.id.destination);
        submitBtn = (Button) findViewById(R.id.btn_submit);
        submitBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

    }
}
