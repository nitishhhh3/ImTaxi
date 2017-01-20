package iamtaxi.dmi.com.imtaxi.activity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

import iamtaxi.dmi.com.imtaxi.R;

/**
 * Created by Ankit on 20-01-2017.
 *
 * @author Ankit Jindal
 */

public class CarDetailsActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEditTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        setUpToolbar(getString(R.string.text_car_details), true);
        setUpViews();
    }

    /**
     * Method used to setUp views
     */
    private void setUpViews() {

        mEditTime = (EditText) findViewById(R.id.edit_time);
        mEditTime.setOnClickListener(this);
    }

    /**
     * Method used to showTimePicker
     */
    private void showTimePicker() {

        Calendar calendar = Calendar.getInstance();

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mEditTime.setText(hourOfDay + ":" + minute);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);

        timePickerDialog.show();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.edit_time:
                showTimePicker();
                break;
        }
    }
}
