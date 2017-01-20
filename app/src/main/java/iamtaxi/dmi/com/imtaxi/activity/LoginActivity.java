package iamtaxi.dmi.com.imtaxi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import iamtaxi.dmi.com.imtaxi.CabFormActivity;
import iamtaxi.dmi.com.imtaxi.R;

/**
 * Created by Ankit on 20-01-2017.
 *
 * @author Ankit Jindal
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEditUserName, mEditPassword;
    private RadioButton mRadioEmp, mRadioManager, mRadioGuard;
    private Button loginBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpToolbar(getString(R.string.text_login));
        initViews();
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
        loginBtn = (Button) findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_login:
                startActivity(new Intent(this, CabFormActivity.class));

                break;
        }
    }
}
