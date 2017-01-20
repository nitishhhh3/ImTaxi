package iamtaxi.dmi.com.imtaxi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import iamtaxi.dmi.com.imtaxi.R;

/**
 * Created by sahil kumar on 20/01/2017.
 */

public class CabFormActivity extends BaseActivity implements View.OnClickListener {
    private EditText mEmployeeName, mEmployeeId, mContact;
    private EditText mPorjectCode;
    private EditText mDestination;
    private Button submitBtn;
    private EditText mManagerEmail;
    private EditText mTime;
    private EditText mPurposeOfTravel;

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
        mManagerEmail = (EditText) findViewById(R.id.manager_email);
        mTime = (EditText) findViewById(R.id.time_cab_required);
        mPurposeOfTravel = (EditText) findViewById(R.id.purpose_travel);
        submitBtn.setOnClickListener(this);

        mEmployeeName.setText("Sahil Kumar");
        mEmployeeId.setText("sahilkumar@dminc.com");
        mContact.setText("9891333990");
        mPorjectCode.setText("PROJECT_200");
        mDestination.setText("Model Town");
        mManagerEmail.setText("nitish@dminc.com");
        mPurposeOfTravel.setText("Hackathon");
        mTime.setText("8:00 PM");


    }

    @Override
    public void onClick(View view) {

    }
}
