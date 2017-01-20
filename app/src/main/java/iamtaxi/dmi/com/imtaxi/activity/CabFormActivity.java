package iamtaxi.dmi.com.imtaxi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import iamtaxi.dmi.com.imtaxi.R;
import iamtaxi.dmi.com.imtaxi.model.CabRequest;
import iamtaxi.dmi.com.imtaxi.rest.ApiClient;
import iamtaxi.dmi.com.imtaxi.rest.ApiInterface;
import iamtaxi.dmi.com.imtaxi.utill.AppConstants;
import iamtaxi.dmi.com.imtaxi.utill.ImTaxtPrefs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private String empName;
    private String empId;
    private String contact;
    private String projectCode;
    private String destination;
    private ImTaxtPrefs mPref;
    private String managerEmail;
    private String time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cab_form_activity);
        setUpToolbar(getString(R.string.text_cab_request), true);

        mPref = ImTaxtPrefs.getInstance(this);
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

        mEmployeeName.setText(mPref.getUserId());
        mEmployeeId.setText(mPref.getUserId());
        mContact.setText("9891333990");
        mPorjectCode.setText("PROJECT_200");
        mDestination.setText("Model Town");
        mManagerEmail.setText("nitish@dminc.com");
        mPurposeOfTravel.setText("Hackathon");
        mTime.setText("8:00 PM");


    }

    @Override
    public void onClick(View view) {
        getDetails();
    }

    private void getDetails() {
        CabRequest cabRequest = new CabRequest();
        empName = mEmployeeName.getText().toString().trim();
        empId = mEmployeeId.getText().toString().trim();
        contact = mContact.getText().toString();
        projectCode = mPorjectCode.getText().toString().trim();
        destination = mDestination.getText().toString().trim();
        managerEmail = mManagerEmail.getText().toString().trim();
        time = mTime.getText().toString().trim();

        cabRequest.setEmpEmail(empId);
        cabRequest.setSource("DMI Noida");
        cabRequest.setDestination(destination);
        cabRequest.setEmployeeType(mPref.getUserType());
        cabRequest.setManagerEmail(managerEmail);
        cabRequest.setStatus(AppConstants.PENDING_STATUS);
        cabRequest.setTime(time);

        callApi(cabRequest);
    }

    private void callApi(CabRequest cabRequest) {
        ApiInterface apiService = ApiClient.createClient();
        Call<List<CabRequest>> call = apiService.createCabRequest(cabRequest);
        showDialog("Please wait...");
        call.enqueue(new Callback<List<CabRequest>>() {
            @Override
            public void onResponse(Call<List<CabRequest>> call, Response<List<CabRequest>> response) {
                dissmissDialog();
                try {
                    TripDetailsActivity.cabRequests = (ArrayList<CabRequest>) response.body();
                    Intent intent = new Intent();
                    setResult(2, intent);
                    finish();
                } catch (Exception e) {
                    Toast.makeText(CabFormActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<CabRequest>> call, Throwable t) {
                dissmissDialog();
                // Log error here since request failed
                Toast.makeText(CabFormActivity.this, "ERROR::" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
