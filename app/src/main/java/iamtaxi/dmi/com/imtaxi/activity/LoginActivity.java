package iamtaxi.dmi.com.imtaxi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import iamtaxi.dmi.com.imtaxi.R;
import iamtaxi.dmi.com.imtaxi.utill.AppConstants;
import iamtaxi.dmi.com.imtaxi.utill.ImTaxtPrefs;

/**
 * Created by Ankit on 20-01-2017.
 *
 * @author Ankit Jindal
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEditUserName, mEditPassword;
    private RadioButton mRadioEmp, mRadioManager, mRadioGuard;
    private Button loginBtn;
    private RadioGroup typeRadioGroup;
    private String loginType = "";
    private ImTaxtPrefs mPref;
    private String userName;
    private String userPassword;

    private Button mLoginButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpToolbar(getString(R.string.text_login), false);
        mPref = ImTaxtPrefs.getInstance(this);
        if (mPref.isLoggedIn()) {
            String userType = mPref.getUserType();
            if (userType.equals(AppConstants.EMP_TYPE)) {
                startActivity(new Intent(this, TripDetailsActivity.class));
            } else if (userType.equals(AppConstants.GUARD_TYPE)) {
                startActivity(new Intent(this, TripDetailsActivity.class));
            } else if (userType.equals(AppConstants.MNGR_TYPE)) {
                startActivity(new Intent(this, TripDetailsActivity.class));
            }
            finish();
        } else {
            initViews();
        }
    }

    /**
     * Method used to initialise Views from Xml
     */
    private void initViews() {
        mEditUserName = (EditText) findViewById(R.id.edit_username);
        mEditPassword = (EditText) findViewById(R.id.edit_password);
        mRadioGuard = (RadioButton) findViewById(R.id.radio_guard);
        mRadioEmp = (RadioButton) findViewById(R.id.radio_employee);
        mRadioManager = (RadioButton) findViewById(R.id.radio_manager);
        mLoginButton = (Button) findViewById(R.id.btn_login);
        mLoginButton.setOnClickListener(this);

        typeRadioGroup = (RadioGroup) findViewById(R.id.typeRadioGroup);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_login:

                if (checkValidation()) {
                    getInputDeatils();
                    callApi();
                }
                break;
        }
    }

    private void callApi() {
        launchActivity();
    }

    private void launchActivity() {
        saveUserDetailsInPref();
        startActivity(new Intent(this, TripDetailsActivity.class));
        finish();
    }

    private boolean checkValidation() {
        userName = mEditUserName.getText().toString().trim();
        userPassword = mEditPassword.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            mEditUserName.setError("Enter username");
            return false;
        }
        if (TextUtils.isEmpty(userPassword)) {
            mEditPassword.setError("Enter password");
            return false;
        }

        return (!TextUtils.isEmpty(userName)) && (!TextUtils.isEmpty(userPassword));
    }

    private void getInputDeatils() {
        typeRadioGroup.getCheckedRadioButtonId();
        switch (typeRadioGroup.getCheckedRadioButtonId()) {
            case R.id.radio_employee:
                loginType = AppConstants.EMP_TYPE;
                break;
            case R.id.radio_manager:
                loginType = AppConstants.MNGR_TYPE;
                break;
            default:
                loginType = AppConstants.GUARD_TYPE;

        }
    }

    private void saveUserDetailsInPref() {
        mPref.setUserId(mEditUserName.getText().toString());
        mPref.setLoggedIn(true);
        mPref.setUserType(loginType);
    }
}
