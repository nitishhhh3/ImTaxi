package iamtaxi.dmi.com.imtaxi.activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.ArrayList;

import iamtaxi.dmi.com.imtaxi.R;
import iamtaxi.dmi.com.imtaxi.adapters.TripDetailsAdapter;
import iamtaxi.dmi.com.imtaxi.listeners.OnRecyclerItemClickListener;
import iamtaxi.dmi.com.imtaxi.model.CabRequest;
import iamtaxi.dmi.com.imtaxi.utill.AppConstants;
import iamtaxi.dmi.com.imtaxi.utill.ImTaxtPrefs;

/**
 * Created by Ankit on 20-01-2017.
 *
 * @author Ankit Jindal
 */

public class TripDetailsActivity extends BaseActivity implements OnRecyclerItemClickListener {

    private RecyclerView mRecyclerView;
    private TripDetailsAdapter mTripDetailsAdapter;
    private int CREATE_NEW_REQUEST = 1001;
    private ImTaxtPrefs mPref;
    private List<CabRequest> cabRequestList;

    public static ArrayList<CabRequest> cabRequests;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);
        setUpToolbar(getString(R.string.text_trip_details), false);
        initViews();
    }

    /**
     * Method used to initialise views from Xml
     */
    private void initViews() {
        mTripDetailsAdapter = new TripDetailsAdapter(this, this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_trip_details);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mTripDetailsAdapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(TripDetailsActivity.this, CabFormActivity.class), CREATE_NEW_REQUEST);
            }
        });

        if (ImTaxtPrefs.getInstance(TripDetailsActivity.this).getUserType().equalsIgnoreCase(GUARD_TYPE) ||
                ImTaxtPrefs.getInstance(TripDetailsActivity.this).getUserType().equalsIgnoreCase(AppConstants.MNGR_TYPE))
            findViewById(R.id.fab).setVisibility(View.GONE);
        else
            findViewById(R.id.fab).setVisibility(View.VISIBLE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_trip_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            performLogout();
            return true;
        } else if (id == R.id.action_refresh) {

            performRefresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void performRefresh() {

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    private void performLogout() {
        mPref = ImTaxtPrefs.getInstance(this);
        mPref.setLoggedIn(false);
        Intent mIntent = new Intent(this, LoginActivity.class);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(mIntent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_NEW_REQUEST && data != null) {
            ArrayList<CabRequest> myList = new ArrayList<>();
            myList.addAll(cabRequests);
            updateUi(myList);
        }
    }

    private void updateUi(ArrayList<CabRequest> myList) {
        if (null != mTripDetailsAdapter && myList.size() > 0) {
            mTripDetailsAdapter.setData(myList);
            mTripDetailsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRecyclerItemClick(int position, View view) {

        DialogInterface.OnClickListener positiveOnclickListner = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sendSMS("+91 9582461360", "Your request has been sent");
            }
        };

        if (ImTaxtPrefs.getInstance(TripDetailsActivity.this).getUserType().equalsIgnoreCase(GUARD_TYPE))
            switchActivity(CarDetailsActivity.class, null);
        else if (ImTaxtPrefs.getInstance(TripDetailsActivity.this).getUserType().equalsIgnoreCase(AppConstants.MNGR_TYPE))
            showAlertDialog(getString(R.string.text_are_u_sure), getString(R.string.text_approve_request),
                    getString(R.string.text_approve), getString(R.string.text_reject), positiveOnclickListner, null);
    }


    private void sendSMS(String phoneNumber, String message) {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
                new Intent(SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
                new Intent(DELIVERED), 0);

        //---when the SMS has been sent---
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS sent",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic failure",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No service",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "Null PDU",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Radio off",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));

        //---when the SMS has been delivered---
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS not delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
    }

}
