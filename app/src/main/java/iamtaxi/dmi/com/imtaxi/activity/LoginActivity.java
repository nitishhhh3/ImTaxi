package iamtaxi.dmi.com.imtaxi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import iamtaxi.dmi.com.imtaxi.R;
import iamtaxi.dmi.com.imtaxi.model.LoginResponse;
import iamtaxi.dmi.com.imtaxi.rest.ApiClient;
import iamtaxi.dmi.com.imtaxi.rest.ApiInterface;
import iamtaxi.dmi.com.imtaxi.utill.AppConstants;
import iamtaxi.dmi.com.imtaxi.utill.ImTaxtPrefs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            startActivity(new Intent(this, TripDetailsActivity.class));
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
        ApiInterface apiService = ApiClient.createClient();
        Call<String> call = apiService.getLoginAuth(userName, userPassword, loginType);
        showDialog("Please wait...");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                dissmissDialog();
                String message = response.body();
                if (message != null && message.equalsIgnoreCase("Employee")) {
                    launchActivity();
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                dissmissDialog();
                // Log error here since request failed
                Toast.makeText(LoginActivity.this, "ERROR::" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
